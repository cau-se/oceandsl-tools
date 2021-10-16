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
package org.oceandsl.tools.mvis;

import java.io.IOException;

import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;
import org.oceandsl.architecture.model.stages.sinks.TableCSVSink;
import org.oceandsl.architecture.model.stages.utils.DedicatedFileNameMapper;
import org.oceandsl.tools.mvis.graph.ColorAssemblyLevelComponentDependencyGraphBuilderFactory;
import org.oceandsl.tools.mvis.graph.ColorAssemblyLevelOperationDependencyGraphBuilderFactory;
import org.oceandsl.tools.mvis.graph.ColoredDotExportConfigurationFactory;
import org.oceandsl.tools.mvis.graph.IColorDependencyGraphBuilderConfiguration;
import org.oceandsl.tools.mvis.stages.ModelRepositoryProducerStage;
import org.oceandsl.tools.mvis.stages.SaveAllenDataStage;
import org.oceandsl.tools.mvis.stages.entropy.ComputeAllenComplexityMetrics;
import org.oceandsl.tools.mvis.stages.graph.AllenDeployedArchitectureGraphStage;
import org.oceandsl.tools.mvis.stages.graph.ColorDependencyGraphBuilderConfiguration;
import org.oceandsl.tools.mvis.stages.graph.ModuleCallGraphStage;
import org.oceandsl.tools.mvis.stages.graph.OperationCallGraphStage;
import org.oceandsl.tools.mvis.stages.metrics.ModuleNodeCountCouplingStage;
import org.oceandsl.tools.mvis.stages.metrics.NumberOfCallsStage;
import org.oceandsl.tools.mvis.stages.metrics.OperationNodeCountCouplingStage;
import org.slf4j.Logger;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.dependency.DependencyGraphCreatorStage;
import kieker.analysis.graph.export.dot.DotFileWriterStage;
import kieker.analysis.graph.export.graphml.GraphMLFileWriterStage;
import kieker.analysis.graph.util.FileExtension;
import kieker.analysis.signature.NameBuilder;
import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.util.stage.trigger.Trigger;
import kieker.analysis.util.stage.trigger.TriggerOnTerminationStage;
import teetime.framework.Configuration;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    private static final String OPERATION_CALLS_CSV = "operation-calls.csv";
    private static final String DISTINCT_OPERATION_DEGREE_CSV = "distinct-operation-degree.csv";
    private static final String DISTINCT_MODULE_DEGREE_CSV = "distinct-module-degree.csv";

    public TeetimeConfiguration(final Logger logger, final Settings settings,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        final ModelRepositoryProducerStage readerStage = new ModelRepositoryProducerStage(
                settings.getInputDirectory());

        final TriggerOnTerminationStage triggerStage = new TriggerOnTerminationStage();
        final Distributor<Trigger> triggerDistributor = new Distributor<>(new CopyByReferenceStrategy());

        final DotFileWriterStage dotFileOperationDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(settings.getOutputDirectory(), "operation",
                        FileExtension.DOT),
                new ColoredDotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelOperationDependencyGraph(false));

        final Distributor<ModelRepository> statisticsDistributor = new Distributor<>(new CopyByReferenceStrategy());

        /** Stages for statistics. */
        final NumberOfCallsStage numberOfCallsStage = new NumberOfCallsStage();
        final OperationCallGraphStage functionCallGraphStage = new OperationCallGraphStage(
                settings.getSelector(), settings.getGraphSelectionMode());
        final OperationNodeCountCouplingStage functionNodeCouplingStage = new OperationNodeCountCouplingStage();
        final ModuleCallGraphStage moduleCallGraphStage = new ModuleCallGraphStage(settings.getSelector(),
                settings.getGraphSelectionMode());
        final ModuleNodeCountCouplingStage moduleNodeCouplingStage = new ModuleNodeCountCouplingStage();

        /** Sinks for metrics writing to CSV files. */
        final TableCSVSink operationCallSink = new TableCSVSink(settings.getOutputDirectory(),
                String.format("%s-%s", settings.getSelector().getFilePrefix(),
                        TeetimeConfiguration.OPERATION_CALLS_CSV));
        final TableCSVSink distinctOperationDegreeSink = new TableCSVSink(settings.getOutputDirectory(),
                String.format("%s-%s", settings.getSelector().getFilePrefix(),
                        TeetimeConfiguration.DISTINCT_OPERATION_DEGREE_CSV));
        final TableCSVSink distinctModuleDegreeSink = new TableCSVSink(settings.getOutputDirectory(),
                String.format("%s-%s", settings.getSelector().getFilePrefix(),
                        TeetimeConfiguration.DISTINCT_MODULE_DEGREE_CSV));

        final GraphMLFileWriterStage graphMLFileWriterStage = new GraphMLFileWriterStage(
                settings.getOutputDirectory());

        /** connecting ports. */
        this.connectPorts(statisticsDistributor.getNewOutputPort(), triggerStage.getInputPort());
        this.connectPorts(triggerStage.getOutputPort(), triggerDistributor.getInputPort());

        final IColorDependencyGraphBuilderConfiguration configuration = new ColorDependencyGraphBuilderConfiguration(
                repository, settings.getSelector());

        /** operation graph. */
        if (settings.getOutputGraphs().contains(EOutputGraph.DOT_OP)
                || settings.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
            final DependencyGraphCreatorStage<IColorDependencyGraphBuilderConfiguration> operationDependencyGraphCreatorStage = new DependencyGraphCreatorStage<>(
                    configuration, new ColorAssemblyLevelOperationDependencyGraphBuilderFactory());
            final Distributor<IGraph> graphsDistributor = new Distributor<>(new CopyByReferenceStrategy());

            this.connectPorts(triggerDistributor.getNewOutputPort(),
                    operationDependencyGraphCreatorStage.getInputPort());
            this.connectPorts(operationDependencyGraphCreatorStage.getOutputPort(), graphsDistributor.getInputPort());
            if (settings.getOutputGraphs().contains(EOutputGraph.DOT_OP)) {
                this.connectPorts(graphsDistributor.getNewOutputPort(),
                        dotFileOperationDependencyWriterStage.getInputPort());
            }
            if (settings.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
                this.connectPorts(graphsDistributor.getNewOutputPort(), graphMLFileWriterStage.getInputPort());
            }
        }

        /** component graph. */
        if (settings.getOutputGraphs().contains(EOutputGraph.DOT_COMPONENT)) {
            final DependencyGraphCreatorStage<IColorDependencyGraphBuilderConfiguration> componentDependencyGraphCreatorStage = new DependencyGraphCreatorStage<>(
                    configuration, new ColorAssemblyLevelComponentDependencyGraphBuilderFactory());
            final DotFileWriterStage componentDependencyDotFileWriterStage = new DotFileWriterStage(
                    new DedicatedFileNameMapper(settings.getOutputDirectory(), "component",
                            FileExtension.DOT),
                    new ColoredDotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                            .createForAssemblyLevelComponentDependencyGraph(false));

            this.connectPorts(triggerDistributor.getNewOutputPort(),
                    componentDependencyGraphCreatorStage.getInputPort());
            this.connectPorts(componentDependencyGraphCreatorStage.getOutputPort(),
                    componentDependencyDotFileWriterStage.getInputPort());
        }

        /** setup allen metrics. */
        final AllenDeployedArchitectureGraphStage allenArchitectureModularGraphStage = new AllenDeployedArchitectureGraphStage(
                settings.getSelector(), settings.getGraphSelectionMode());
        final ComputeAllenComplexityMetrics computeAllenComplexityStage = new ComputeAllenComplexityMetrics();
        final SaveAllenDataStage saveAllenDataStage = new SaveAllenDataStage(
                settings.getOutputDirectory());

        /** connect stages. */
        this.connectPorts(readerStage.getOutputPort(), statisticsDistributor.getInputPort());

        /** Statistics. */
        this.connectPorts(statisticsDistributor.getNewOutputPort(), allenArchitectureModularGraphStage.getInputPort());
        this.connectPorts(allenArchitectureModularGraphStage.getOutputPort(),
                computeAllenComplexityStage.getInputPort());
        this.connectPorts(computeAllenComplexityStage.getOutputPort(), saveAllenDataStage.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), numberOfCallsStage.getInputPort());
        this.connectPorts(numberOfCallsStage.getOutputPort(), operationCallSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), functionCallGraphStage.getInputPort());
        this.connectPorts(functionCallGraphStage.getOutputPort(), functionNodeCouplingStage.getInputPort());
        this.connectPorts(functionNodeCouplingStage.getOutputPort(), distinctOperationDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), moduleCallGraphStage.getInputPort());
        this.connectPorts(moduleCallGraphStage.getOutputPort(), moduleNodeCouplingStage.getInputPort());
        this.connectPorts(moduleNodeCouplingStage.getOutputPort(), distinctModuleDegreeSink.getInputPort());
    }
}
