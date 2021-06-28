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

import org.oceandsl.analysis.CSVFunctionCallReaderStage;
import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;
import org.oceandsl.architecture.model.graph.ColorAssemblyLevelComponentDependencyGraphBuilderFactory;
import org.oceandsl.architecture.model.graph.ColorAssemblyLevelOperationDependencyGraphBuilderFactory;
import org.oceandsl.architecture.model.graph.ColoredDotExportConfigurationFactory;
import org.oceandsl.architecture.model.stages.CSVMapperStage;
import org.oceandsl.architecture.model.stages.CountEventsStage;
import org.oceandsl.architecture.model.stages.CountUniqueCallsStage;
import org.oceandsl.architecture.model.stages.CreateCallsStage;
import org.oceandsl.architecture.model.stages.ExecutionModelGenerationStage;
import org.oceandsl.architecture.model.stages.FileBasedCleanupComponentSignatureStage;
import org.oceandsl.architecture.model.stages.MapBasedCleanupComponentSignatureStage;
import org.oceandsl.architecture.model.stages.ModelSerializerStage;
import org.oceandsl.architecture.model.stages.ProduceBeforeAndAfterEventsFromOperationCallsStage;
import org.oceandsl.architecture.model.stages.TriggerToModelSnapshotStage;
import org.oceandsl.architecture.model.stages.data.ValueConversionErrorException;
import org.oceandsl.architecture.model.stages.graph.ComputeExtraSubGraph;
import org.oceandsl.architecture.model.stages.graph.FunctionCallGraphStage;
import org.oceandsl.architecture.model.stages.graph.FunctionNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.graph.ModuleCallGraphStage;
import org.oceandsl.architecture.model.stages.graph.ModuleNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.metrics.NumberOfCallsStage;
import org.oceandsl.architecture.model.stages.utils.DedicatedFileNameMapper;
import org.slf4j.Logger;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.dependency.DependencyGraphCreatorStage;
import kieker.analysis.graph.export.dot.DotFileWriterStage;
import kieker.analysis.graph.export.graphml.GraphMLFileWriterStage;
import kieker.analysis.graph.util.FileExtension;
import kieker.analysis.model.AssemblyModelAssemblerStage;
import kieker.analysis.model.DeploymentModelAssemblerStage;
import kieker.analysis.model.ModelRepository;
import kieker.analysis.model.TypeModelAssemblerStage;
import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.analysis.signature.IOperationSignatureExtractor;
import kieker.analysis.signature.NameBuilder;
import kieker.analysis.util.stage.trigger.Trigger;
import kieker.analysis.util.stage.trigger.TriggerOnTerminationStage;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;
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

    private static final String DYNAMIC_FUNCTION_CALLS_CSV = "dynamic-function-calls.csv";
    private static final String DYNAMIC_DISTINCT_FUNCTION_DEGREE_CSV = "dynamic-distinct-function-degree.csv";
    private static final String STATIC_DISTINCT_FUNCTION_DEGREE_CSV = "static-distinct-function-degree.csv";
    private static final String DYNAMIC_DISTINCT_MODULE_DEGREE_CSV = "dynamic-distinct-module-degree.csv";
    private static final String STATIC_DISTINCT_MODULE_DEGREE_CSV = "static-distinct-module-degree.csv";
    private static final String DYNAMIC_EXTRA_EDGES_CSV = "dynamic-extra-edges.csv";
    private static final String STATIC_EXTRA_EDGES_CSV = "static-extra-edges.csv";

    public TeetimeConfiguration(final Logger logger, final ArchitectureModelSettings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        OutputPort<? extends IMonitoringRecord> readerPort;
        switch (parameterConfiguration.getInputType()) {
        case KIEKER:
            logger.info("Processing Kieker log");
            final kieker.common.configuration.Configuration configuration = new kieker.common.configuration.Configuration();
            configuration.setProperty(LogsReaderCompositeStage.LOG_DIRECTORIES,
                    parameterConfiguration.getInputFile().getCanonicalPath());

            final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(configuration);

            if (parameterConfiguration.getModelExecutable() != null) {
                final RewriteBeforeAndAfterEventsStage rewriteBeforeAndAfterEventsStage = new RewriteBeforeAndAfterEventsStage(
                        parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable(),
                        parameterConfiguration.getCaseInsensitive());
                this.connectPorts(reader.getOutputPort(), rewriteBeforeAndAfterEventsStage.getInputPort());
                readerPort = rewriteBeforeAndAfterEventsStage.getOutputPort();
            } else {
                readerPort = reader.getOutputPort();
            }

            break;
        case CSV:
            logger.info("Processing static call log");
            final CSVFunctionCallReaderStage readCsvStage = new CSVFunctionCallReaderStage(
                    parameterConfiguration.getInputFile().toPath());
            final CSVMapperStage mapperStage = new CSVMapperStage(parameterConfiguration.getHostname(),
                    parameterConfiguration.getCaseInsensitive());
            this.connectPorts(readCsvStage.getOutputPort(), mapperStage.getInputPort());
            readerPort = mapperStage.getOutputPort();
            break;
        default:
            readerPort = null;
            break;
        }

        if (parameterConfiguration.getComponentMapFile() != null) {
            logger.info("Map based component definition");
            final MapBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new MapBasedCleanupComponentSignatureStage(
                    parameterConfiguration.getComponentMapFile(), parameterConfiguration.getCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());
            readerPort = cleanupComponentSignatureStage.getOutputPort();
        } else {
            logger.info("File based component definition");
            final FileBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new FileBasedCleanupComponentSignatureStage(
                    parameterConfiguration.getCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());
            readerPort = cleanupComponentSignatureStage.getOutputPort();
        }

        final ProduceBeforeAndAfterEventsFromOperationCallsStage produceEvents = new ProduceBeforeAndAfterEventsFromOperationCallsStage(
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
                final String rest = (path.getParent() != null)
                        ? parameterConfiguration.getExperimentName() + "." + path.getParent().toString()
                        : parameterConfiguration.getExperimentName();
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
        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(SourceModel.class),
                parameterConfiguration.getSourceLabel(), componentSignatureExtractor, operationSignatureExtractor);
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(AssemblyModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                repository.getModel(AssemblyModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());

        final CreateCallsStage callsStage = new CreateCallsStage(repository.getModel(DeploymentModel.class));
        final ExecutionModelGenerationStage executionModelGenerationStage = new ExecutionModelGenerationStage(
                repository.getModel(ExecutionModel.class));
        final CountUniqueCallsStage countUniqueCalls = new CountUniqueCallsStage(
                repository.getModel(StatisticsModel.class), repository.getModel(ExecutionModel.class));

        final TriggerOnTerminationStage triggerOnTerminationStage = new TriggerOnTerminationStage();

        final Distributor<Trigger> distributor = new Distributor<>(new CopyByReferenceStrategy());

        final ModelSerializerStage modelSerializerStage = new ModelSerializerStage(repository,
                parameterConfiguration.getOutputDirectory());

        final DependencyGraphCreatorStage operationDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                repository, new ColorAssemblyLevelOperationDependencyGraphBuilderFactory());
        final DotFileWriterStage dotFileOperationDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(parameterConfiguration.getOutputDirectory(), "operation",
                        FileExtension.DOT),
                new ColoredDotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelOperationDependencyGraph(false));

        final Distributor<IGraph> distributorGraphs = new Distributor<>(new CopyByReferenceStrategy());

        final DependencyGraphCreatorStage componentDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                repository, new ColorAssemblyLevelComponentDependencyGraphBuilderFactory());
        final DotFileWriterStage dotFileComponentDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(parameterConfiguration.getOutputDirectory(), "component",
                        FileExtension.DOT),
                new ColoredDotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelComponentDependencyGraph(false));

        final TriggerToModelSnapshotStage<Trigger> triggerToModelSnapshotStage = new TriggerToModelSnapshotStage<>(
                repository);

        final Distributor<ModelRepository> statisticsDistributor = new Distributor<>(new CopyByReferenceStrategy());

        /** Stages for statistics. */
        final NumberOfCallsStage numberOfCallsStage = new NumberOfCallsStage();
        final FunctionCallGraphStage dynamicFunctionCallGraphStage = new FunctionCallGraphStage("dynamic");
        final FunctionNodeCountCouplingStage dynamicFunctionNodeCouplingStage = new FunctionNodeCountCouplingStage();
        final ModuleCallGraphStage dynamicModuleCallGraphStage = new ModuleCallGraphStage("dynamic");
        final ModuleNodeCountCouplingStage dynamicModuleNodeCouplingStage = new ModuleNodeCountCouplingStage();

        final FunctionCallGraphStage staticFunctionCallGraphStage = new FunctionCallGraphStage("static");
        final FunctionNodeCountCouplingStage staticFunctionNodeCouplingStage = new FunctionNodeCountCouplingStage();
        final ModuleCallGraphStage staticModuleCallGraphStage = new ModuleCallGraphStage("static");
        final ModuleNodeCountCouplingStage staticModuleNodeCouplingStage = new ModuleNodeCountCouplingStage();

        final ComputeExtraSubGraph dynamicComputeExtraSubGraph = new ComputeExtraSubGraph("dynamic");
        final FunctionNodeCountCouplingStage dynamicFunctionExtraNodeCouplingStage = new FunctionNodeCountCouplingStage();

        final ComputeExtraSubGraph staticComputeExtraSubGraph = new ComputeExtraSubGraph("static");
        final FunctionNodeCountCouplingStage staticFunctionExtraNodeCouplingStage = new FunctionNodeCountCouplingStage();

        /** Sinks for metrics writing to CSV files. */
        final TableCSVSink dynamicFunctionCallSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                TeetimeConfiguration.DYNAMIC_FUNCTION_CALLS_CSV);
        final TableCSVSink dynamicDistinctFunctionDegreeSink = new TableCSVSink(
                parameterConfiguration.getOutputDirectory(), TeetimeConfiguration.DYNAMIC_DISTINCT_FUNCTION_DEGREE_CSV);
        final TableCSVSink staticDistinctFunctionDegreeSink = new TableCSVSink(
                parameterConfiguration.getOutputDirectory(), TeetimeConfiguration.STATIC_DISTINCT_FUNCTION_DEGREE_CSV);
        final TableCSVSink dynamicDistinctModuleDegreeSink = new TableCSVSink(
                parameterConfiguration.getOutputDirectory(), TeetimeConfiguration.DYNAMIC_DISTINCT_MODULE_DEGREE_CSV);
        final TableCSVSink staticDistinctModuleDegreeSink = new TableCSVSink(
                parameterConfiguration.getOutputDirectory(), TeetimeConfiguration.STATIC_DISTINCT_MODULE_DEGREE_CSV);

        final TableCSVSink dynamicExtraEdgesSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                TeetimeConfiguration.DYNAMIC_EXTRA_EDGES_CSV);
        final TableCSVSink staticExtraEdgesSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                TeetimeConfiguration.STATIC_EXTRA_EDGES_CSV);

        final GraphMLFileWriterStage graphMLFileWriterStage = new GraphMLFileWriterStage(
                parameterConfiguration.getOutputDirectory());

        /** connecting ports. */
        this.connectPorts(readerPort, produceEvents.getInputPort());

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

        /** operation graph. */
        if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_OP)
                || parameterConfiguration.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
            this.connectPorts(distributor.getNewOutputPort(), operationDependencyGraphCreatorStage.getInputPort());
            this.connectPorts(operationDependencyGraphCreatorStage.getOutputPort(), distributorGraphs.getInputPort());
            if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_OP)) {
                this.connectPorts(distributorGraphs.getNewOutputPort(),
                        dotFileOperationDependencyWriterStage.getInputPort());
            }
            if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
                this.connectPorts(distributorGraphs.getNewOutputPort(), graphMLFileWriterStage.getInputPort());
            }
        }

        /** component graph. */
        if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_COMPONENT)) {
            this.connectPorts(componentDependencyGraphCreatorStage.getOutputPort(),
                    dotFileComponentDependencyWriterStage.getInputPort());
            this.connectPorts(distributor.getNewOutputPort(), componentDependencyGraphCreatorStage.getInputPort());
        }

        /** Serialize model. */
        this.connectPorts(distributor.getNewOutputPort(), modelSerializerStage.getInputPort());

        /** Statistics trigger. */
        this.connectPorts(distributor.getNewOutputPort(), triggerToModelSnapshotStage.getInputPort());
        this.connectPorts(triggerToModelSnapshotStage.getOutputPort(), statisticsDistributor.getInputPort());

        /** Statistics. */
        this.connectPorts(statisticsDistributor.getNewOutputPort(), numberOfCallsStage.getInputPort());
        this.connectPorts(numberOfCallsStage.getOutputPort(), dynamicFunctionCallSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), dynamicFunctionCallGraphStage.getInputPort());
        this.connectPorts(dynamicFunctionCallGraphStage.getOutputPort(),
                dynamicFunctionNodeCouplingStage.getInputPort());
        this.connectPorts(dynamicFunctionNodeCouplingStage.getOutputPort(),
                dynamicDistinctFunctionDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), dynamicModuleCallGraphStage.getInputPort());
        this.connectPorts(dynamicModuleCallGraphStage.getOutputPort(), dynamicModuleNodeCouplingStage.getInputPort());
        this.connectPorts(dynamicModuleNodeCouplingStage.getOutputPort(),
                dynamicDistinctModuleDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), staticFunctionCallGraphStage.getInputPort());
        this.connectPorts(staticFunctionCallGraphStage.getOutputPort(), staticFunctionNodeCouplingStage.getInputPort());
        this.connectPorts(staticFunctionNodeCouplingStage.getOutputPort(),
                staticDistinctFunctionDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), staticModuleCallGraphStage.getInputPort());
        this.connectPorts(staticModuleCallGraphStage.getOutputPort(), staticModuleNodeCouplingStage.getInputPort());
        this.connectPorts(staticModuleNodeCouplingStage.getOutputPort(), staticDistinctModuleDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), dynamicComputeExtraSubGraph.getInputPort());
        this.connectPorts(dynamicComputeExtraSubGraph.getOutputPort(),
                dynamicFunctionExtraNodeCouplingStage.getInputPort());
        this.connectPorts(dynamicFunctionExtraNodeCouplingStage.getOutputPort(), dynamicExtraEdgesSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), staticComputeExtraSubGraph.getInputPort());
        this.connectPorts(staticComputeExtraSubGraph.getOutputPort(),
                staticFunctionExtraNodeCouplingStage.getInputPort());
        this.connectPorts(staticFunctionExtraNodeCouplingStage.getOutputPort(), staticExtraEdgesSink.getInputPort());
    }
}
