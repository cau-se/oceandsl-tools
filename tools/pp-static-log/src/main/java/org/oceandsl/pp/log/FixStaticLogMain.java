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
package org.oceandsl.pp.log;

import java.io.File;
import java.io.IOException;

import com.beust.jcommander.JCommander;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

/**
 * Fix static log which misses the component value of the callee..
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class FixStaticLogMain extends AbstractService<TeetimeConfiguration, Settings> {

    public static void main(final String[] args) {
        final FixStaticLogMain main = new FixStaticLogMain();
        final int exitCode = main.run("preprocess static log", "pp-static-log", args, new Settings());

        java.lang.System.exit(exitCode);
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            return new TeetimeConfiguration(this.parameterConfiguration);
        } catch (final IOException e) {
            this.logger.error("IO error. Cause: {}", e.getLocalizedMessage());
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
        if (!this.parameterConfiguration.getInputFile().isFile()) {
            this.logger.error("Input file {} is not file", this.parameterConfiguration.getInputFile());
            return false;
        }

        return true;
    }

    @Override
    protected void shutdownService() {

    }

}
