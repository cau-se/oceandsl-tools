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
package org.oceandsl.analysis.architecture; // NOPMD excessiveImports

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

import kieker.analysis.architecture.repository.ModelDescriptor;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourceFactory;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.TypeFactory;
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

    public static final String SOURCE_MODEL_NAME = "source-model.xmi";

    public static final ModelDescriptor TYPE_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.TYPE_MODEL_NAME, TypePackage.Literals.TYPE_MODEL, TypeFactory.eINSTANCE);
    public static final ModelDescriptor ASSEMBLY_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.ASSEMBLY_MODEL_NAME, AssemblyPackage.Literals.ASSEMBLY_MODEL,
            AssemblyFactory.eINSTANCE);
    public static final ModelDescriptor DEPLOYMENT_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_NAME, DeploymentPackage.Literals.DEPLOYMENT_MODEL,
            DeploymentFactory.eINSTANCE);
    public static final ModelDescriptor EXECUTION_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.EXECUTION_MODEL_NAME, ExecutionPackage.Literals.EXECUTION_MODEL,
            ExecutionFactory.eINSTANCE);
    public static final ModelDescriptor STATISTICS_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.STATISTICS_MODEL_NAME, StatisticsPackage.Literals.STATISTICS_MODEL,
            StatisticsFactory.eINSTANCE);
    public static final ModelDescriptor SOURCE_MODEL_DESCRIPTOR = new ModelDescriptor(
            ArchitectureModelManagementUtils.SOURCE_MODEL_NAME, SourcePackage.Literals.SOURCE_MODEL,
            SourceFactory.eINSTANCE);

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchitectureModelManagementUtils.class);

    private ArchitectureModelManagementUtils() {
        // utility class do not instantiate
    }

    public static ModelRepository createModelRepository(final String repositoryName) {
        final ModelRepository repository = new ModelRepository(repositoryName);
        repository.register(ArchitectureModelManagementUtils.TYPE_MODEL_DESCRIPTOR,
                TypeFactory.eINSTANCE.createTypeModel());
        repository.register(ArchitectureModelManagementUtils.ASSEMBLY_MODEL_DESCRIPTOR,
                AssemblyFactory.eINSTANCE.createAssemblyModel());
        repository.register(ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_DESCRIPTOR,
                DeploymentFactory.eINSTANCE.createDeploymentModel());
        repository.register(ArchitectureModelManagementUtils.EXECUTION_MODEL_DESCRIPTOR,
                ExecutionFactory.eINSTANCE.createExecutionModel());
        repository.register(ArchitectureModelManagementUtils.STATISTICS_MODEL_DESCRIPTOR,
                StatisticsFactory.eINSTANCE.createStatisticsModel());
        repository.register(ArchitectureModelManagementUtils.SOURCE_MODEL_DESCRIPTOR,
                SourceFactory.eINSTANCE.createSourceModel());

        return repository;
    }

    public static ModelRepository loadModelRepository(final Path path) throws ConfigurationException {
        final ModelRepository repository = new ModelRepository(path.getFileName().toString());

        final Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
        extensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl());

        final ResourceSet resourceSet = new ResourceSetImpl();

        final EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
        packageRegistry.put(TypePackage.eNS_URI, TypePackage.eINSTANCE);
        packageRegistry.put(AssemblyPackage.eNS_URI, AssemblyPackage.eINSTANCE);
        packageRegistry.put(DeploymentPackage.eNS_URI, DeploymentPackage.eINSTANCE);
        packageRegistry.put(ExecutionPackage.eNS_URI, ExecutionPackage.eINSTANCE);
        packageRegistry.put(StatisticsPackage.eNS_URI, StatisticsPackage.eINSTANCE);
        packageRegistry.put(SourcePackage.eNS_URI, SourcePackage.eINSTANCE);

        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.TYPE_MODEL_DESCRIPTOR, path);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.ASSEMBLY_MODEL_DESCRIPTOR, path);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_DESCRIPTOR, path);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.EXECUTION_MODEL_DESCRIPTOR, path);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.STATISTICS_MODEL_DESCRIPTOR, path);
        ArchitectureModelManagementUtils.readModel(resourceSet, repository,
                ArchitectureModelManagementUtils.SOURCE_MODEL_DESCRIPTOR, path);

        return repository;
    }

    private static <T extends EObject> void readModel(final ResourceSet resourceSet, final ModelRepository repository,
            final ModelDescriptor modelDescriptor, final Path path) throws ConfigurationException {
        ArchitectureModelManagementUtils.LOGGER.info("Loading model {}", modelDescriptor.getFilename());
        final File modelFile = ArchitectureModelManagementUtils.createReadModelFileHandle(path,
                modelDescriptor.getFilename());
        if (modelFile.exists()) {
            final Resource resource = resourceSet.getResource(URI.createFileURI(modelFile.getAbsolutePath()), true);
            for (final Diagnostic error : resource.getErrors()) {
                ArchitectureModelManagementUtils.LOGGER.error("Error loading '{}' of {}:{}  {}",
                        modelDescriptor.getFilename(), error.getLocation(), error.getLine(), error.getMessage());
            }
            for (final Diagnostic error : resource.getWarnings()) {
                ArchitectureModelManagementUtils.LOGGER.error("Warning loading '{}' of {}:{}  {}",
                        modelDescriptor.getFilename(), error.getLocation(), error.getLine(), error.getMessage());
            }
            repository.register(modelDescriptor, resource.getContents().get(0));
            final Iterator<EObject> iterator = resource.getAllContents();
            while (iterator.hasNext()) {
                iterator.next().eCrossReferences();
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

        final Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
        extensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl());

        // store models
        final ResourceSet resourceSet = new ResourceSetImpl();

        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }

        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.TYPE_MODEL_DESCRIPTOR, repository);
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.ASSEMBLY_MODEL_DESCRIPTOR, repository);
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.DEPLOYMENT_MODEL_DESCRIPTOR, repository);
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.EXECUTION_MODEL_DESCRIPTOR, repository);
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.STATISTICS_MODEL_DESCRIPTOR, repository);
        ArchitectureModelManagementUtils.writeModel(resourceSet, outputDirectory,
                ArchitectureModelManagementUtils.SOURCE_MODEL_DESCRIPTOR, repository);
    }

    private static <T extends EObject> void writeModel(final ResourceSet resourceSet, final Path outputDirectory,
            final ModelDescriptor modelDescriptor, final ModelRepository repository) {
        ArchitectureModelManagementUtils.LOGGER.info("Saving model {}", modelDescriptor.getFilename());

        final File modelFile = ArchitectureModelManagementUtils.createWriteModelFileHandle(outputDirectory,
                modelDescriptor.getFilename());

        final Resource resource = resourceSet.createResource(URI.createFileURI(modelFile.getAbsolutePath()));
        resource.getContents().add(repository.getModel(modelDescriptor.getRootClass()));

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
