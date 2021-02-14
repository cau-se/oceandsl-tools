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
package org.oceandsl.architecture.model;

import java.io.File;
import java.io.IOException;

import com.beust.jcommander.JCommander;

import kieker.analysis.statistics.StatisticsModel;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyFactory;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyModel;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentFactory;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentModel;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionFactory;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;
import kieker.analysisteetime.model.analysismodel.type.TypeFactory;
import kieker.analysisteetime.model.analysismodel.type.TypeModel;
import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ArchitectureModelMain extends AbstractService<TeetimeConfiguration, ArchitectureModelSettings> {

    private final TypeModel typeModel = TypeFactory.eINSTANCE.createTypeModel();
    private final AssemblyModel assemblyModel = AssemblyFactory.eINSTANCE.createAssemblyModel();
    private final DeploymentModel deploymentModel = DeploymentFactory.eINSTANCE.createDeploymentModel();
    private final ExecutionModel executionModel = ExecutionFactory.eINSTANCE.createExecutionModel();
    private final StatisticsModel statisticsModel = new StatisticsModel();

    public static void main(final String[] args) {
        final ArchitectureModelMain main = new ArchitectureModelMain();
        final int exitCode = main.run("Kieker Log ELF Rewriter", "log-rewriter", args, new ArchitectureModelSettings());

        java.lang.System.exit(exitCode);
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            return new TeetimeConfiguration(this.parameterConfiguration, this.typeModel, this.assemblyModel,
                    this.deploymentModel, this.executionModel, this.statisticsModel);
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
        if (!this.parameterConfiguration.getInputFile().isDirectory()) {
            this.logger.error("Input directory {} is not directory", this.parameterConfiguration.getInputFile());
            return false;
        }
        if (!this.parameterConfiguration.getOutputFile().isDirectory()) {
            this.logger.error("Output directory {} is not directory", this.parameterConfiguration.getOutputFile());
            return false;
        }
        if (this.parameterConfiguration.getPrefix() == null) {
            this.parameterConfiguration.setPrefix("");
        }
        return true;
    }

    @Override
    protected void shutdownService() {
        // TODO Auto-generated method stub

    }
}
