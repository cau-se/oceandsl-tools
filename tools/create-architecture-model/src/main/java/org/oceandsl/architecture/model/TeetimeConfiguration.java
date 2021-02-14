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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.dependency.AssemblyLevelComponentDependencyGraphBuilderFactory;
import kieker.analysis.graph.dependency.AssemblyLevelOperationDependencyGraphBuilderFactory;
import kieker.analysis.graph.dependency.DependencyGraphCreatorStage;
import kieker.analysis.graph.export.dot.DotFileWriterStage;
import kieker.analysis.graph.export.graphml.GraphMLFileWriterStage;
import kieker.analysis.graph.util.FileExtension;
import kieker.analysis.model.AssemblyModelAssemblerStage;
import kieker.analysis.model.DeploymentModelAssemblerStage;
import kieker.analysis.model.TypeModelAssemblerStage;
import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.analysis.signature.IOperationSignatureExtractor;
import kieker.analysis.signature.NameBuilder;
import kieker.analysis.statistics.StatisticsModel;
import kieker.analysis.util.stage.trigger.Trigger;
import kieker.analysis.util.stage.trigger.TriggerOnTerminationStage;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyModel;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentModel;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;
import kieker.analysisteetime.model.analysismodel.type.ComponentType;
import kieker.analysisteetime.model.analysismodel.type.OperationType;
import kieker.analysisteetime.model.analysismodel.type.TypeModel;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;
import teetime.stage.InstanceOfFilter;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final ArchitectureModelSettings parameterConfiguration, final TypeModel typeModel,
            final AssemblyModel assemblyModel, final DeploymentModel deploymentModel,
            final ExecutionModel executionModel, final StatisticsModel statisticsModel) throws IOException {

        final kieker.common.configuration.Configuration configuration = new kieker.common.configuration.Configuration();
        configuration.setProperty(LogsReaderCompositeStage.LOG_DIRECTORIES,
                parameterConfiguration.getInputFile().getCanonicalPath());

        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(configuration);

        final RewriteBeforeAndAfterEventsStage processor = new RewriteBeforeAndAfterEventsStage(
                parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable(),
                parameterConfiguration.getPrefix());

        final ProduceBeforeAndAfterEventsFromOperationCalls produceEvents = new ProduceBeforeAndAfterEventsFromOperationCalls(
                "localhost");

        final InstanceOfFilter<IMonitoringRecord, IFlowRecord> instanceOfFilter = new InstanceOfFilter<>(
                IFlowRecord.class);

        final CountEventsStage<IFlowRecord> counter = new CountEventsStage<>(1000000);

        final IComponentSignatureExtractor componentSignatureExtractor = new IComponentSignatureExtractor() {

            @Override
            public void extract(final ComponentType componentType) {
                String signature = componentType.getSignature();
                if (signature == null) {
                    signature = "-- none --";
                }
                final Path path = Paths.get(signature);
                final String name = path.getName(path.getNameCount() - 1).toString();
                final String rest = (path.getParent() != null) ? path.getParent().toString() : "<<unknown>>";
                componentType.setName(name);
                componentType.setPackage(rest);
            }
        };
        final IOperationSignatureExtractor operationSignatureExtractor = new IOperationSignatureExtractor() {

            @Override
            public void extract(final OperationType operationType) {
                final String name = operationType.getSignature();
                operationType.setName(name);
                operationType.setReturnType("unknown");
            }

        };
        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(typeModel,
                componentSignatureExtractor, operationSignatureExtractor);
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(typeModel,
                assemblyModel);
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                assemblyModel, deploymentModel);

        final CreateCallsStage callsStage = new CreateCallsStage(deploymentModel);
        final ExecutionModelGenerationStage executionModelGenerationStage = new ExecutionModelGenerationStage(
                executionModel);
        final CountUniqueCalls countUniqueCalls = new CountUniqueCalls(statisticsModel, executionModel);

        final TriggerOnTerminationStage triggerOnTerminationStage = new TriggerOnTerminationStage();

        final Distributor<Trigger> distributor = new Distributor<>(new CopyByReferenceStrategy());

        final ModelSerializerStage executionModelSerializerStage = new ModelSerializerStage(typeModel, assemblyModel,
                deploymentModel, executionModel, statisticsModel, parameterConfiguration.getOutputFile());

        final DependencyGraphCreatorStage operationDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                executionModel, statisticsModel, new AssemblyLevelOperationDependencyGraphBuilderFactory());
        final DotFileWriterStage dotFileOperationDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(parameterConfiguration.getOutputFile().getPath(), "operation",
                        FileExtension.DOT),
                new DotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelOperationDependencyGraph(false));

        final Distributor<IGraph> distributorGraphs = new Distributor<>(new CopyByReferenceStrategy());

        final DependencyGraphCreatorStage componentDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                executionModel, statisticsModel, new AssemblyLevelComponentDependencyGraphBuilderFactory());
        final DotFileWriterStage dotFileComponentDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(parameterConfiguration.getOutputFile().getPath(), "component",
                        FileExtension.DOT),
                new DotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelComponentDependencyGraph(false));

        final GraphMLFileWriterStage graphMLFileWriterStage = new GraphMLFileWriterStage(
                parameterConfiguration.getOutputFile().getPath());

        this.connectPorts(reader.getOutputPort(), processor.getInputPort());
        this.connectPorts(processor.getOutputPort(), produceEvents.getInputPort());
        this.connectPorts(produceEvents.getOutputPort(), instanceOfFilter.getInputPort());
        this.connectPorts(instanceOfFilter.getMatchedOutputPort(), counter.getInputPort());
        this.connectPorts(counter.getOutputPort(), typeModelAssemblerStage.getInputPort());
        this.connectPorts(typeModelAssemblerStage.getOutputPort(), assemblyModelAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelAssemblerStage.getOutputPort(), deploymentModelAssemblerStage.getInputPort());
        this.connectPorts(deploymentModelAssemblerStage.getOutputPort(), callsStage.getInputPort());
        this.connectPorts(callsStage.getOutputPort(), executionModelGenerationStage.getInputPort());
        this.connectPorts(executionModelGenerationStage.getOutputPort(), countUniqueCalls.getInputPort());
        this.connectPorts(countUniqueCalls.getOutputPort(), triggerOnTerminationStage.getInputPort());
        this.connectPorts(triggerOnTerminationStage.getOutputPort(), distributor.getInputPort());

        this.connectPorts(distributor.getNewOutputPort(), operationDependencyGraphCreatorStage.getInputPort());
        this.connectPorts(distributor.getNewOutputPort(), componentDependencyGraphCreatorStage.getInputPort());
        this.connectPorts(distributor.getNewOutputPort(), executionModelSerializerStage.getInputPort());

        this.connectPorts(operationDependencyGraphCreatorStage.getOutputPort(), distributorGraphs.getInputPort());
        this.connectPorts(distributorGraphs.getNewOutputPort(), dotFileOperationDependencyWriterStage.getInputPort());
        this.connectPorts(distributorGraphs.getNewOutputPort(), graphMLFileWriterStage.getInputPort());

        this.connectPorts(componentDependencyGraphCreatorStage.getOutputPort(),
                dotFileComponentDependencyWriterStage.getInputPort());

    }
}
