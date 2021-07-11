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
package org.oceandsl.tools.mvis;

import java.io.File;
import java.io.IOException;

import com.beust.jcommander.JCommander;

import org.oceandsl.architecture.model.ArchitectureModelManagementFactory;
import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;
import org.slf4j.LoggerFactory;

import kieker.analysis.stage.model.ModelRepository;
import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ModelVisualizationMain extends AbstractService<TeetimeConfiguration, Settings> {

    private ModelRepository repository;

    public static void main(final String[] args) {
        final ModelVisualizationMain main = new ModelVisualizationMain();
        try {
            final int exitCode = main.run("architecture modeler", "arch-mod", args, new Settings());
            java.lang.System.exit(exitCode);
        } catch (final IllegalArgumentException e) {
            LoggerFactory.getLogger(ModelVisualizationMain.class).error("Configuration error: {}",
                    e.getLocalizedMessage());
            java.lang.System.exit(1);
        }
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            this.repository = ArchitectureModelManagementFactory.loadModelRepository(
                    this.parameterConfiguration.getInputArchitectureModelDirectory(),
                    this.parameterConfiguration.getExperimentName(),
                    this.parameterConfiguration.getComponentMapFile() != null);
            return new TeetimeConfiguration(this.logger, this.parameterConfiguration, this.repository);
        } catch (final IOException | ValueConversionErrorException e) {
            this.logger.error("Error reading files. Cause: {}", e.getLocalizedMessage());
            throw new ConfigurationException(e);
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
        }

        if (!this.parameterConfiguration.getOutputDirectory().toFile().isDirectory()) {
            this.logger.error("Output directory {} is not directory", this.parameterConfiguration.getOutputDirectory());
            return false;
        }
        if (this.parameterConfiguration.getInputArchitectureModelDirectory() != null) {
            if (!this.parameterConfiguration.getInputArchitectureModelDirectory().exists()) {
                this.logger.error("Cannot find input architecture model directory {}",
                        this.parameterConfiguration.getInputArchitectureModelDirectory().getAbsoluteFile());
                return false;
            } else {
                if (!this.parameterConfiguration.getInputArchitectureModelDirectory().isDirectory()) {
                    this.logger.error("Input architecture model directory path does not refer to a directory {}",
                            this.parameterConfiguration.getInputArchitectureModelDirectory().getAbsoluteFile());
                    return false;
                } else {
                    // check completeness of files
                }
            }
        }
        return true;
    }

    @Override
    protected void shutdownService() {

    }

}
