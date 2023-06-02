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

import java.io.IOException;
import java.nio.file.Path;

import kieker.analysis.architecture.repository.ArchitectureModelRepositoryFactory;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.source.SourceFactory;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.type.TypeFactory;

/**
 * Reading and storing model repositories.
 *
 * Note: This should be merged with the repository class.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public final class ArchitectureModelManagementUtils {

    private ArchitectureModelManagementUtils() {
        // utility class do not instantiate
    }

    public static ModelRepository createModelRepository(final String experimentName, final boolean mapFile) {
        return ArchitectureModelManagementUtils
                .createModelRepository(String.format("%s-%s", experimentName, mapFile ? "map" : "file"));
    }

    public static ModelRepository createModelRepository(final String repositoryName) {
        final ModelRepository repository = new ModelRepository(repositoryName);
        repository.register(ArchitectureModelRepositoryFactory.TYPE_MODEL_DESCRIPTOR,
                TypeFactory.eINSTANCE.createTypeModel());
        repository.register(ArchitectureModelRepositoryFactory.ASSEMBLY_MODEL_DESCRIPTOR,
                AssemblyFactory.eINSTANCE.createAssemblyModel());
        repository.register(ArchitectureModelRepositoryFactory.DEPLOYMENT_MODEL_DESCRIPTOR,
                DeploymentFactory.eINSTANCE.createDeploymentModel());
        repository.register(ArchitectureModelRepositoryFactory.EXECUTION_MODEL_DESCRIPTOR,
                ExecutionFactory.eINSTANCE.createExecutionModel());
        repository.register(ArchitectureModelRepositoryFactory.STATISTICS_MODEL_DESCRIPTOR,
                StatisticsFactory.eINSTANCE.createStatisticsModel());
        repository.register(ArchitectureModelRepositoryFactory.SOURCE_MODEL_DESCRIPTOR,
                SourceFactory.eINSTANCE.createSourceModel());

        return repository;
    }

    public static ModelRepository loadModelRepository(final Path path) throws ConfigurationException {
        return ArchitectureModelRepositoryFactory.readModelRepository(path,
                ArchitectureModelRepositoryFactory.TYPE_MODEL_DESCRIPTOR,
                ArchitectureModelRepositoryFactory.ASSEMBLY_MODEL_DESCRIPTOR,
                ArchitectureModelRepositoryFactory.DEPLOYMENT_MODEL_DESCRIPTOR,
                ArchitectureModelRepositoryFactory.EXECUTION_MODEL_DESCRIPTOR,
                ArchitectureModelRepositoryFactory.STATISTICS_MODEL_DESCRIPTOR,
                ArchitectureModelRepositoryFactory.SOURCE_MODEL_DESCRIPTOR);
    }

    public static void writeModelRepository(final Path outputDirectory, final ModelRepository repository)
            throws IOException {
        ArchitectureModelRepositoryFactory.writeModelRepository(outputDirectory, repository);
    }
}
