/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.dar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import com.beust.jcommander.JCommander;

import org.slf4j.LoggerFactory;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.analysis.generic.EModuleMode;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class DynamicArchitectureRecoveryMain extends AbstractService<TeetimeConfiguration, Settings> {

    private ModelRepository repository;

    public static void main(final String[] args) {
        final DynamicArchitectureRecoveryMain main = new DynamicArchitectureRecoveryMain();
        try {
            final int exitCode = main.run("dynamic architecture recovery", "dar", args, new Settings());
            System.exit(exitCode);
        } catch (final IllegalArgumentException e) {
            LoggerFactory.getLogger(DynamicArchitectureRecoveryMain.class).error("Configuration error: {}",
                    e.getLocalizedMessage());
            System.exit(1);
        }
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            this.repository = ArchitectureModelManagementUtils.createModelRepository(
                    this.createRepositoryName(this.settings.getExperimentName(), this.settings.getModuleModes()));

            return new TeetimeConfiguration(this.logger, this.settings, this.repository);
        } catch (final IOException | ValueConversionErrorException e) {
            this.logger.error("Error reading files. Cause: {}", e.getLocalizedMessage());
            throw new ConfigurationException(e);
        }
    }

    private String createRepositoryName(final String experimentName, final List<EModuleMode> moduleModes) {
        return String.format("%s-%s", experimentName, this.createModuleModesString(moduleModes));
    }

    private String createModuleModesString(final List<EModuleMode> moduleModes) {
        if (!moduleModes.isEmpty()) {
            String modes = null;
            for (final EModuleMode mode : moduleModes) {
                final String modeName = mode.name().toLowerCase(Locale.getDefault()).substring(0,
                        mode.name().indexOf('_'));
                if (modes == null) {
                    modes = modeName;
                } else {
                    modes += "-" + modeName;
                }
            }
            return modes;
        } else {
            return "ERROR no mode";
        }
    }

    @Override
    protected Path getConfigurationPath() {
        // we do not use a configuration file
        return null;
    }

    @Override
    protected boolean checkConfiguration(final Configuration configuration, final JCommander commander) {
        return true;
    }

    @Override
    protected boolean checkParameters(final JCommander commander) throws ConfigurationException {
        if (this.settings.getModelExecutable() != null) {
            if (!this.settings.getAddrlineExecutable().canExecute()) {
                this.logger.error("Addr2line file {} is not executable", this.settings.getAddrlineExecutable());
                return false;
            }
            if (!this.settings.getModelExecutable().canExecute()) {
                this.logger.error("Model file {} is not executable", this.settings.getModelExecutable());
                return false;
            }
        } else {
            this.settings.getInputPaths().forEach(path -> {
                if (!Files.isDirectory(path)) {
                    this.logger.error("Input path {} is not a directory", path.toString());
                } else {
                    this.settings.getInputFiles().add(path.toFile());
                }
            });
            if (this.settings.getInputFiles().isEmpty()) {
                this.logger.error("No valid input directories found.");
                return false;
            }
        }
        if (this.settings.getOutputDirectory() != null) {
            if (this.settings.getOutputDirectory().getParent() == null) {
                if (!Files.isDirectory(this.settings.getOutputDirectory())) {
                    this.logger.error("Output path {} is not a directory", this.settings.getOutputDirectory());
                    return false;
                }
            } else {
                if (!Files.isDirectory(this.settings.getOutputDirectory().getParent())) {
                    this.logger.error("Output path {} is not a directory", this.settings.getOutputDirectory());
                    return false;
                }
            }
        }
        if (this.settings.getModuleModes().contains(EModuleMode.MAP_MODE)) {
            if (this.settings.getComponentMapFiles() != null && this.settings.getComponentMapFiles().size() > 0) {
                for (final Path path : this.settings.getComponentMapFiles()) {
                    if (!Files.isReadable(path)) {
                        this.logger.error("Cannot read map file: {}", path.toString());
                        return false;
                    }
                }
            } else {
                this.logger.error("Using map mode, but no map specified.");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void shutdownService() {
        try {
            ArchitectureModelManagementUtils.writeModelRepository(this.settings.getOutputDirectory(), this.repository);
        } catch (final IOException e) {
            this.logger.error("Error saving model: {}", e.getLocalizedMessage());
        }
    }

}
