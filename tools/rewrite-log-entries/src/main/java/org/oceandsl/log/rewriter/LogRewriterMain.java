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
package org.oceandsl.log.rewriter;

import java.io.File;
import java.io.IOException;

import com.beust.jcommander.JCommander;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

/**
 * Main class for the operation and class name rewriter used for logs of ELF binaries.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class LogRewriterMain extends AbstractService<TeetimeConfiguration, Settings> {

    public static void main(final String[] args) {
        java.lang.System
                .exit(new LogRewriterMain().run("Kieker Log ELF Rewriter", "log-rewriter", args, new Settings()));
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            return new TeetimeConfiguration(this.parameterConfiguration);
        } catch (final IOException e) {
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
        if (!this.parameterConfiguration.getAddrlineExecutable().canExecute()) {
            this.logger.error("Addr2line file {} is not executable",
                    this.parameterConfiguration.getAddrlineExecutable());
            return false;
        }
        if (!this.parameterConfiguration.getModelExecutable().canExecute()) {
            this.logger.error("Model file {} is not executable", this.parameterConfiguration.getModelExecutable());
            return false;
        }
        for (final File inputFile : this.parameterConfiguration.getInputFiles()) {
            if (!inputFile.isDirectory()) {
                this.logger.error("Input directory {} is not directory", inputFile);
                return false;
            }
        }
        if (!this.parameterConfiguration.getOutputFile().isDirectory()) {
            this.logger.error("Output directory {} is not directory", this.parameterConfiguration.getOutputFile());
            return false;
        }
        return true;
    }

    @Override
    protected void shutdownService() {
        // TODO Auto-generated method stub

    }
}
