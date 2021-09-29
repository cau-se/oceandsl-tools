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
import org.oceandsl.architecture.model.stages.graph.AllenDeployedArchitectureGraphStage;
import org.oceandsl.architecture.model.stages.graph.FunctionCallGraphStage;
import org.oceandsl.architecture.model.stages.graph.ModuleCallGraphStage;
import org.oceandsl.architecture.model.stages.metrics.ComputeAllenComplexityMetrics;
import org.oceandsl.architecture.model.stages.metrics.FunctionNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.metrics.ModuleNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.metrics.NumberOfCallsStage;
import org.oceandsl.architecture.model.stages.sinks.TableCSVSink;
import org.oceandsl.architecture.model.stages.utils.DedicatedFileNameMapper;
import org.oceandsl.tools.mvis.graph.ColorAssemblyLevelComponentDependencyGraphBuilderFactory;
import org.oceandsl.tools.mvis.graph.ColorAssemblyLevelOperationDependencyGraphBuilderFactory;
import org.oceandsl.tools.mvis.graph.ColoredDotExportConfigurationFactory;
import org.oceandsl.tools.mvis.stages.ModelRepositoryProducerStage;
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

    private static final String FUNCTION_CALLS_CSV = "function-calls.csv";
    private static final String DISTINCT_FUNCTION_DEGREE_CSV = "distinct-function-degree.csv";
    private static final String DISTINCT_MODULE_DEGREE_CSV = "distinct-module-degree.csv";

    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        final ModelRepositoryProducerStage readerStage = new ModelRepositoryProducerStage(
                parameterConfiguration.getInputDirectory());

        final TriggerOnTerminationStage triggerStage = new TriggerOnTerminationStage();
        final Distributor<Trigger> triggerDistributor = new Distributor<>(new CopyByReferenceStrategy());

        final DotFileWriterStage dotFileOperationDependencyWriterStage = new DotFileWriterStage(
                new DedicatedFileNameMapper(parameterConfiguration.getOutputDirectory(), "operation",
                        FileExtension.DOT),
                new ColoredDotExportConfigurationFactory(NameBuilder.forJavaShortOperations())
                        .createForAssemblyLevelOperationDependencyGraph(false));

        final Distributor<ModelRepository> statisticsDistributor = new Distributor<>(new CopyByReferenceStrategy());

        /** Stages for statistics. */
        final NumberOfCallsStage numberOfCallsStage = new NumberOfCallsStage();
        final FunctionCallGraphStage functionCallGraphStage = new FunctionCallGraphStage(
                parameterConfiguration.getSelector());
        final FunctionNodeCountCouplingStage functionNodeCouplingStage = new FunctionNodeCountCouplingStage();
        final ModuleCallGraphStage moduleCallGraphStage = new ModuleCallGraphStage(
                parameterConfiguration.getSelector());
        final ModuleNodeCountCouplingStage moduleNodeCouplingStage = new ModuleNodeCountCouplingStage();

        /** Sinks for metrics writing to CSV files. */
        final TableCSVSink functionCallSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                String.format("%s-%s", parameterConfiguration.getSelector().getFilePrefix(),
                        TeetimeConfiguration.FUNCTION_CALLS_CSV));
        final TableCSVSink distinctFunctionDegreeSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                String.format("%s-%s", parameterConfiguration.getSelector().getFilePrefix(),
                        TeetimeConfiguration.DISTINCT_FUNCTION_DEGREE_CSV));
        final TableCSVSink distinctModuleDegreeSink = new TableCSVSink(parameterConfiguration.getOutputDirectory(),
                String.format("%s-%s", parameterConfiguration.getSelector().getFilePrefix(),
                        TeetimeConfiguration.DISTINCT_MODULE_DEGREE_CSV));

        final GraphMLFileWriterStage graphMLFileWriterStage = new GraphMLFileWriterStage(
                parameterConfiguration.getOutputDirectory());

        /** connecting ports. */
        this.connectPorts(statisticsDistributor.getNewOutputPort(), triggerStage.getInputPort());
        this.connectPorts(triggerStage.getOutputPort(), triggerDistributor.getInputPort());

        /** operation graph. */
        if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_OP)
                || parameterConfiguration.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
            final DependencyGraphCreatorStage operationDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                    repository, new ColorAssemblyLevelOperationDependencyGraphBuilderFactory());
            final Distributor<IGraph> graphsDistributor = new Distributor<>(new CopyByReferenceStrategy());

            this.connectPorts(triggerDistributor.getNewOutputPort(),
                    operationDependencyGraphCreatorStage.getInputPort());
            this.connectPorts(operationDependencyGraphCreatorStage.getOutputPort(), graphsDistributor.getInputPort());
            if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_OP)) {
                this.connectPorts(graphsDistributor.getNewOutputPort(),
                        dotFileOperationDependencyWriterStage.getInputPort());
            }
            if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
                this.connectPorts(graphsDistributor.getNewOutputPort(), graphMLFileWriterStage.getInputPort());
            }
        }

        /** component graph. */
        if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_COMPONENT)) {
            final DependencyGraphCreatorStage componentDependencyGraphCreatorStage = new DependencyGraphCreatorStage(
                    repository, new ColorAssemblyLevelComponentDependencyGraphBuilderFactory());
            final DotFileWriterStage componentDependencyDotFileWriterStage = new DotFileWriterStage(
                    new DedicatedFileNameMapper(parameterConfiguration.getOutputDirectory(), "component",
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
                parameterConfiguration.getSelector());
        final ComputeAllenComplexityMetrics computeAllenComplexityStage = new ComputeAllenComplexityMetrics();
        final SaveAllenDataStage saveAllenDataStage = new SaveAllenDataStage(
                parameterConfiguration.getOutputDirectory());

        /** connect stages. */
        this.connectPorts(readerStage.getOutputPort(), statisticsDistributor.getInputPort());

        /** Statistics. */
        this.connectPorts(statisticsDistributor.getNewOutputPort(), allenArchitectureModularGraphStage.getInputPort());
        this.connectPorts(allenArchitectureModularGraphStage.getOutputPort(),
                computeAllenComplexityStage.getInputPort());
        this.connectPorts(computeAllenComplexityStage.getOutputPort(), saveAllenDataStage.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), numberOfCallsStage.getInputPort());
        this.connectPorts(numberOfCallsStage.getOutputPort(), functionCallSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), functionCallGraphStage.getInputPort());
        this.connectPorts(functionCallGraphStage.getOutputPort(), functionNodeCouplingStage.getInputPort());
        this.connectPorts(functionNodeCouplingStage.getOutputPort(), distinctFunctionDegreeSink.getInputPort());

        this.connectPorts(statisticsDistributor.getNewOutputPort(), moduleCallGraphStage.getInputPort());
        this.connectPorts(moduleCallGraphStage.getOutputPort(), moduleNodeCouplingStage.getInputPort());
        this.connectPorts(moduleNodeCouplingStage.getOutputPort(), distinctModuleDegreeSink.getInputPort());
    }
}
