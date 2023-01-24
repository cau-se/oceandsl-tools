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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.JCommander;

import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.analysis.generic.EModuleMode;
import org.slf4j.LoggerFactory;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

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
        if (moduleModes.size() > 0) {
            String modes = null;
            for (final EModuleMode mode : moduleModes) {
                final String modeName = mode.name().toLowerCase().substring(0, mode.name().indexOf('_'));
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
    protected File getConfigurationFile() {
        // we do not use a configuration file
        return null;
    }

    @Override
    protected boolean checkConfiguration(final Configuration configuration, final JCommander commander) {
        return true;
    }

    @Override
    protected boolean checkParameters(final JCommander commander) throws ConfigurationException {
        if (this.parameterConfiguration.getModelExecutable() != null) {
            if (!this.parameterConfiguration.getAddrlineExecutable().canExecute()) {
                this.logger.error("Addr2line file {} is not executable",
                        this.parameterConfiguration.getAddrlineExecutable());
                return false;
            }
            if (!this.parameterConfiguration.getModelExecutable().canExecute()) {
                this.logger.error("Model file {} is not executable", this.parameterConfiguration.getModelExecutable());
                return false;
            }
        } else if (!Files.isDirectory(this.parameterConfiguration.getInputFile())) {
            this.logger.error("Input path {} is not a directory", this.parameterConfiguration.getInputFile());
            return false;
        }
        if (this.parameterConfiguration.getOutputDirectory() != null) {
            if (this.parameterConfiguration.getOutputDirectory().getParent() == null) {
                if (!Files.isDirectory(this.parameterConfiguration.getOutputDirectory())) {
                    this.logger.error("Output path {} is not a directory",
                            this.parameterConfiguration.getOutputDirectory());
                    return false;
                }
            } else if (!Files.isDirectory(this.parameterConfiguration.getOutputDirectory().getParent())) {
                this.logger.error("Output path {} is not a directory",
                        this.parameterConfiguration.getOutputDirectory());
                return false;
            }
        }
        if (this.parameterConfiguration.getModuleModes().contains(EModuleMode.MAP_MODE)) {
            if ((this.parameterConfiguration.getComponentMapFiles() != null)
                    && (this.parameterConfiguration.getComponentMapFiles().size() > 0)) {
                for (final Path path : this.parameterConfiguration.getComponentMapFiles()) {
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
            ArchitectureModelManagementUtils.writeModelRepository(this.parameterConfiguration.getOutputDirectory(),
                    this.repository);
        } catch (final IOException e) {
            this.logger.error("Error saving model: {}", e.getLocalizedMessage());
        }
    }

}
