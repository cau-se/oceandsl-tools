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
package org.oceandsl.analysis.architecture.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.analysis.stage.model.ModelRepository;
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

/**
 * Reading and storing model repositories.
 *
 * Note: This should be merged with the repository class.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public final class ArchitectureModelManagementUtils {

    public static final String TYPE_MODEL_NAME = "type-model.xmi";

    public static final String ASSEMBLY_MODEL_NAME = "assembly-model.xmi";

    public static final String DEPLOYMENT_MODEL_NAME = "deployment-model.xmi";

    public static final String EXECUTION_MODEL_NAME = "execution-model.xmi";

    public static final String STATISTICS_MODEL_NAME = "statistics-model.xmi";

    public static final String SOURCES_MODEL_NAME = "sources-model.xmi";

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchitectureModelManagementUtils.class);

    private ArchitectureModelManagementUtils() {
        // TODO Auto-generated constructor stub
    }

    public static ModelRepository createModelRepository(final String experimentName, final boolean mapFile) {
        return ArchitectureModelManagementUtils
                .createModelRepository(String.format("%s-%s", experimentName, mapFile ? "map" : "file"));
    }

    public static ModelRepository createModelRepository(final String repositoryName) {
        final ModelRepository repository = new ModelRepository(repositoryName);
        repository.register(TypeModel.class, TypeFactory.eINSTANCE.createTypeModel());
        repository.register(AssemblyModel.class, AssemblyFactory.eINSTANCE.createAssemblyModel());
        repository.register(DeploymentModel.class, DeploymentFactory.eINSTANCE.createDeploymentModel());
        repository.register(ExecutionModel.class, ExecutionFactory.eINSTANCE.createExecutionModel());
        repository.register(StatisticsModel.class, StatisticsFactory.eINSTANCE.createStatisticsModel());
        repository.register(SourceModel.class, SourcesFactory.eINSTANCE.createSourceModel());

        return repository;
    }

    public static ModelRepository loadModelRepository(final Path path) throws ConfigurationException {
        final ModelRepository repository = new ModelRepository(path.getFileName().toString());

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("xmi", new XMIResourceFactoryImpl());

        final ResourceSet resourceSet = new ResourceSetImpl();

        final EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
        packageRegistry.put(TypePackage.eNS_URI, TypePackage.eINSTANCE);
        packageRegistry.put(AssemblyPackage.eNS_URI, AssemblyPackage.eINSTANCE);
        packageRegistry.put(DeploymentPackage.eNS_URI, DeploymentPackage.eINSTANCE);
        packageRegistry.put(ExecutionPackage.eNS_URI, ExecutionPackage.eINSTANCE);
        packageRegistry.put(StatisticsPackage.eNS_URI, ArchitectureModelManagementUtils.STATISTICS_MODEL_NAME);
        packageRegistry.put(SourcesPackage.eNS_URI, ArchitectureModelManagementUtils.SOURCES_MODEL_NAME);

        ArchitectureModelManagementUtils.readModel(resourceSet, repository, TypeModel.class, path,
                ArchitectureModelManagementUtils.TYPE_MODEL_NAME);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository, AssemblyModel.class, path,
                ArchitectureModelManagementUtils.ASSEMBLY_MODEL_NAME);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository, DeploymentModel.class, path,
                ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_NAME);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository, ExecutionModel.class, path,
                ArchitectureModelManagementUtils.EXECUTION_MODEL_NAME);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository, StatisticsModel.class, path,
                ArchitectureModelManagementUtils.STATISTICS_MODEL_NAME);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository, SourceModel.class, path,
                ArchitectureModelManagementUtils.SOURCES_MODEL_NAME);

        return repository;
    }

    private static <T extends EObject> void readModel(final ResourceSet resourceSet, final ModelRepository repository,
            final Class<T> type, final Path path, final String filename) throws ConfigurationException {
        ArchitectureModelManagementUtils.LOGGER.info("Loading model {}", filename);
        final File modelFile = ArchitectureModelManagementUtils.createReadModelFileHandle(path, filename);
        if (modelFile.exists()) {
            final Resource resource = resourceSet.getResource(URI.createFileURI(modelFile.getAbsolutePath()), true);
            for (final Diagnostic error : resource.getErrors()) {
                ArchitectureModelManagementUtils.LOGGER.error("Error loading '{}' of {}:{}  {}", filename,
                        error.getLocation(), error.getLine(), error.getMessage());
            }
            for (final Diagnostic error : resource.getWarnings()) {
                ArchitectureModelManagementUtils.LOGGER.error("Warning loading '{}' of {}:{}  {}", filename,
                        error.getLocation(), error.getLine(), error.getMessage());
            }
            repository.register(type, resource.getContents().get(0));
            final Iterator<EObject> i = resource.getAllContents();
            while (i.hasNext()) {
                i.next().eCrossReferences();
            }
        } else {
            ArchitectureModelManagementUtils.LOGGER.error("Error reading model file {}. File does not exist.",
                    modelFile.getAbsoluteFile());
            throw new ConfigurationException(
                    String.format("Error reading model file %s. File does not exist.", modelFile.getAbsoluteFile()));
        }
    }

    public static void writeModelRepository(final Path outputDirectory, final ModelRepository repository)
            throws IOException {

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("xmi", new XMIResourceFactoryImpl());

        // store models
        final ResourceSet resourceSet = new ResourceSetImpl();

        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }

        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.TYPE_MODEL_NAME, repository.getModel(TypeModel.class));
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.ASSEMBLY_MODEL_NAME, repository.getModel(AssemblyModel.class));
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_NAME, repository.getModel(DeploymentModel.class));
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.EXECUTION_MODEL_NAME, repository.getModel(ExecutionModel.class));
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.STATISTICS_MODEL_NAME, repository.getModel(StatisticsModel.class));
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.SOURCES_MODEL_NAME, repository.getModel(SourceModel.class));
    }

    private static <T extends EObject> void writeModel(final ResourceSet resourceSet, final Path outputDirectory,
            final String filename, final T model) {
        ArchitectureModelManagementUtils.LOGGER.info("Saving model {}", filename);

        final File modelFile = ArchitectureModelManagementUtils.createWriteModelFileHandle(outputDirectory, filename);

        final Resource resource = resourceSet.createResource(URI.createURI(modelFile.getAbsolutePath()));
        resource.getContents().add(model);

        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (final IOException e) {
            ArchitectureModelManagementUtils.LOGGER.error("Cannot write {} model to storage. Cause: {}",
                    modelFile.getAbsoluteFile(), e.getLocalizedMessage());
        }
    }

    private static File createReadModelFileHandle(final Path path, final String filename) {
        return new File(path.toString() + File.separator + filename);
    }

    private static File createWriteModelFileHandle(final Path path, final String filename) {
        return new File(path.toFile().getAbsolutePath() + File.separator + filename);
    }

}
