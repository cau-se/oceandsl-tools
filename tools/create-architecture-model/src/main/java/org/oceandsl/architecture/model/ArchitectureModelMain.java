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
import java.util.Collections;
import java.util.Map;

import com.beust.jcommander.JCommander;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.oceandsl.architecture.model.stages.data.ValueConversionErrorException;

import kieker.analysis.model.ModelRepository;
import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.sources.SourcesFactory;
import kieker.model.analysismodel.sources.SourcesPackage;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;
import kieker.tools.common.AbstractService;
import kieker.tools.common.ParameterEvaluationUtils;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ArchitectureModelMain extends AbstractService<TeetimeConfiguration, ArchitectureModelSettings> {

    private static final String TYPE_MODEL_NAME = "type-model.xmi";

    private static final String ASSEMBLY_MODEL_NAME = "assembly-model.xmi";

    private static final String DEPLOYMENT_MODEL_NAME = "deployment-model.xmi";

    private static final String EXECUTION_MODEL_NAME = "execution-model.xmi";

    private static final String STATISTICS_MODEL_NAME = "statistics-model.xmi";

    private static final String SOURCES_MODEL_NAME = "sources-model.xmi";

    private ModelRepository repository;

    public static void main(final String[] args) {
        final ArchitectureModelMain main = new ArchitectureModelMain();
        final int exitCode = main.run("architecture modeler", "arch-mod", args, new ArchitectureModelSettings());

        java.lang.System.exit(exitCode);
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        try {
            this.repository = new ModelRepository(
                    String.format("%s-%s", this.parameterConfiguration.getExperimentName(),
                            this.parameterConfiguration.getComponentMapFile() != null ? "map" : "file"));
            if (this.parameterConfiguration.getInputArchitectureModelDirectory() == null) {
                this.repository.register(TypeModel.class, TypeFactory.eINSTANCE.createTypeModel());
                this.repository.register(AssemblyModel.class, AssemblyFactory.eINSTANCE.createAssemblyModel());
                this.repository.register(DeploymentModel.class, DeploymentFactory.eINSTANCE.createDeploymentModel());
                this.repository.register(ExecutionModel.class, ExecutionFactory.eINSTANCE.createExecutionModel());
                this.repository.register(StatisticsModel.class, StatisticsFactory.eINSTANCE.createStatisticsModel());
                this.repository.register(SourceModel.class, SourcesFactory.eINSTANCE.createSourceModel());
            } else {
                final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
                final Map<String, Object> m = reg.getExtensionToFactoryMap();
                m.put("xmi", new XMIResourceFactoryImpl());

                final ResourceSet resourceSet = new ResourceSetImpl();

                final EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
                packageRegistry.put(TypePackage.eNS_URI, TypePackage.eINSTANCE);
                packageRegistry.put(AssemblyPackage.eNS_URI, AssemblyPackage.eINSTANCE);
                packageRegistry.put(DeploymentPackage.eNS_URI, DeploymentPackage.eINSTANCE);
                packageRegistry.put(ExecutionPackage.eNS_URI, ExecutionPackage.eINSTANCE);
                packageRegistry.put(StatisticsPackage.eNS_URI, ArchitectureModelMain.STATISTICS_MODEL_NAME);
                packageRegistry.put(SourcesPackage.eNS_URI, ArchitectureModelMain.SOURCES_MODEL_NAME);

                this.readModel(resourceSet, this.repository, TypeModel.class, ArchitectureModelMain.TYPE_MODEL_NAME);
                this.readModel(resourceSet, this.repository, AssemblyModel.class,
                        ArchitectureModelMain.ASSEMBLY_MODEL_NAME);
                this.readModel(resourceSet, this.repository, DeploymentModel.class,
                        ArchitectureModelMain.DEPLOYMENT_MODEL_NAME);
                this.readModel(resourceSet, this.repository, ExecutionModel.class,
                        ArchitectureModelMain.EXECUTION_MODEL_NAME);
                this.readModel(resourceSet, this.repository, StatisticsModel.class,
                        ArchitectureModelMain.STATISTICS_MODEL_NAME);
                this.readModel(resourceSet, this.repository, SourceModel.class,
                        ArchitectureModelMain.SOURCES_MODEL_NAME);
            }
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
        if (this.parameterConfiguration.getInputType().equals(EInputType.KIEKER)) {
            if (!this.parameterConfiguration.getInputFile().isDirectory()) {
                this.logger.error("Input directory {} is not directory", this.parameterConfiguration.getInputFile());
                return false;
            }
        } else {
            if (!this.parameterConfiguration.getInputFile().isFile()) {
                this.logger.error("Input file {} is not file", this.parameterConfiguration.getInputFile());
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
                    if (!ParameterEvaluationUtils.isFileReadable(
                            this.createReadModelFileHandle(ArchitectureModelMain.TYPE_MODEL_NAME), "type model",
                            commander)
                            || !ParameterEvaluationUtils.isFileReadable(
                                    this.createReadModelFileHandle(ArchitectureModelMain.ASSEMBLY_MODEL_NAME),
                                    "assembly model", commander)
                            || !ParameterEvaluationUtils.isFileReadable(
                                    this.createReadModelFileHandle(ArchitectureModelMain.DEPLOYMENT_MODEL_NAME),
                                    "deployment model", commander)
                            || !ParameterEvaluationUtils.isFileReadable(
                                    this.createReadModelFileHandle(ArchitectureModelMain.EXECUTION_MODEL_NAME),
                                    "execution model", commander)
                            || !ParameterEvaluationUtils.isFileReadable(
                                    this.createReadModelFileHandle(ArchitectureModelMain.STATISTICS_MODEL_NAME),
                                    "statistics model", commander)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void shutdownService() {
        if (this.parameterConfiguration.getOutputArchitectureModelDirectory() != null) {

            final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
            final Map<String, Object> m = reg.getExtensionToFactoryMap();
            m.put("xmi", new XMIResourceFactoryImpl());

            // store models
            final ResourceSet resourceSet = new ResourceSetImpl();

            this.parameterConfiguration.getOutputArchitectureModelDirectory().mkdirs();

            this.writeModel(resourceSet, ArchitectureModelMain.TYPE_MODEL_NAME,
                    this.repository.getModel(TypeModel.class));
            this.writeModel(resourceSet, ArchitectureModelMain.ASSEMBLY_MODEL_NAME,
                    this.repository.getModel(AssemblyModel.class));
            this.writeModel(resourceSet, ArchitectureModelMain.DEPLOYMENT_MODEL_NAME,
                    this.repository.getModel(DeploymentModel.class));
            this.writeModel(resourceSet, ArchitectureModelMain.EXECUTION_MODEL_NAME,
                    this.repository.getModel(ExecutionModel.class));
            this.writeModel(resourceSet, ArchitectureModelMain.STATISTICS_MODEL_NAME,
                    this.repository.getModel(StatisticsModel.class));
            this.writeModel(resourceSet, ArchitectureModelMain.SOURCES_MODEL_NAME,
                    this.repository.getModel(SourceModel.class));
        }
    }

    private File createReadModelFileHandle(final String filename) {
        return new File(this.parameterConfiguration.getInputArchitectureModelDirectory().getAbsolutePath()
                + File.separator + filename);
    }

    private File createWriteModelFileHandle(final String filename) {
        return new File(this.parameterConfiguration.getOutputArchitectureModelDirectory().getAbsolutePath()
                + File.separator + filename);
    }

    private <T extends EObject> void readModel(final ResourceSet resourceSet, final ModelRepository repository,
            final Class<T> type, final String filename) throws ConfigurationException {
        this.logger.info("Loading model {}", filename);
        final File modelFile = this.createReadModelFileHandle(filename);
        if (modelFile.exists()) {
            final Resource resource = resourceSet.getResource(URI.createFileURI(modelFile.getAbsolutePath()), true);
            for (final Diagnostic error : resource.getErrors()) {
                this.logger.error("Error loading '{}' of {}:{}  {}", filename, error.getLocation(), error.getLine(),
                        error.getMessage());
            }
            for (final Diagnostic error : resource.getWarnings()) {
                this.logger.error("Warning loading '{}' of {}:{}  {}", filename, error.getLocation(), error.getLine(),
                        error.getMessage());
            }
            repository.register(type, resource.getContents().get(0));
        } else {
            this.logger.error("Error reading model file {}. File does not exist.", modelFile.getAbsoluteFile());
            throw new ConfigurationException(
                    String.format("Error reading model file %s. File does not exist.", modelFile.getAbsoluteFile()));
        }
    }

    private <T extends EObject> void writeModel(final ResourceSet resourceSet, final String filename, final T model) {
        this.logger.info("Saving model {}", filename);

        final File modelFile = this.createWriteModelFileHandle(filename);

        final Resource resource = resourceSet.createResource(URI.createURI(modelFile.getAbsolutePath()));
        resource.getContents().add(model);

        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (final IOException e) {
            this.logger.error("Cannot write {} model to storage. Cause: {}", modelFile.getAbsoluteFile(),
                    e.getLocalizedMessage());
        }
    }
}
