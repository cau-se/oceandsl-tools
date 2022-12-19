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
package org.oceandsl.tools.aul;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.beust.jcommander.JCommander;

import org.slf4j.LoggerFactory;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.aul.stages.NullNetworkCreator;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class AllenUpperLimitMain extends AbstractService<TeetimeConfiguration, Settings> {

    public static void main(final String[] args) {
        final AllenUpperLimitMain main = new AllenUpperLimitMain();
        try {
            final int exitCode = main.run("Compute allen upper limit", "aul", args, new Settings());
            System.exit(exitCode);
        } catch (final IllegalArgumentException e) {
            LoggerFactory.getLogger(AllenUpperLimitMain.class).error("Configuration error: {}",
                    e.getLocalizedMessage());
            System.exit(1);
        }
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            return new TeetimeConfiguration(this.parameterConfiguration);
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
        if (this.parameterConfiguration.getInputDirectory() == null && this.parameterConfiguration.getNodes() == null) {
            this.logger.error("Must specify either a model or the number of nodes for an generated model.");
            return false;
        }
        if (!Files.isDirectory(this.parameterConfiguration.getOutputDirectory())) {
            this.logger.error("Output path {} is not directory", this.parameterConfiguration.getOutputDirectory());
            return false;
        }
        if (this.parameterConfiguration.getInputDirectory() != null) {
            if (!Files.isDirectory(this.parameterConfiguration.getInputDirectory())) {
                this.logger.error("Input path {} is not directory", this.parameterConfiguration.getInputDirectory());
                return false;
            }
        }
        if (this.parameterConfiguration.getNodes() != null) {
            if (this.parameterConfiguration.getCreatorMode() == null) {
                this.logger.error("No creator mode for the test graph has been specified.");
                return false;
            } else if (this.parameterConfiguration.getCreatorMode() instanceof NullNetworkCreator) {
                this.logger.warn(
                        "Specified node creator mode is not supported, using fallback which does not create edges.");
            }
        }

        return true;
    }

    @Override
    protected void shutdownService() {
        // No special shutdown function required
    }

}
