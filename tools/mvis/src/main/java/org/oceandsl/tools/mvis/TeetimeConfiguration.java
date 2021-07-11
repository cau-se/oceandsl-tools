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

import org.oceandsl.architecture.model.EOutputGraph;
import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;
import org.oceandsl.architecture.model.graph.ColorAssemblyLevelComponentDependencyGraphBuilderFactory;
import org.oceandsl.architecture.model.graph.ColorAssemblyLevelOperationDependencyGraphBuilderFactory;
import org.oceandsl.architecture.model.graph.ColoredDotExportConfigurationFactory;
import org.oceandsl.architecture.model.stages.TriggerToModelSnapshotStage;
import org.oceandsl.architecture.model.stages.graph.ComputeExtraSubGraphStage;
import org.oceandsl.architecture.model.stages.graph.FunctionCallGraphStage;
import org.oceandsl.architecture.model.stages.graph.ModuleCallGraphStage;
import org.oceandsl.architecture.model.stages.metrics.FunctionNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.metrics.ModuleNodeCountCouplingStage;
import org.oceandsl.architecture.model.stages.metrics.NumberOfCallsStage;
import org.oceandsl.architecture.model.stages.sinks.TableCSVSink;
import org.oceandsl.architecture.model.stages.utils.DedicatedFileNameMapper;
import org.slf4j.Logger;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.dependency.DependencyGraphCreatorStage;
import kieker.analysis.graph.export.dot.DotFileWriterStage;
import kieker.analysis.graph.export.graphml.GraphMLFileWriterStage;
import kieker.analysis.graph.util.FileExtension;
import kieker.analysis.signature.NameBuilder;
import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.util.stage.trigger.Trigger;
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

    private static final String DYNAMIC_FUNCTION_CALLS_CSV = "dynamic-function-calls.csv";
    private static final String DYNAMIC_DISTINCT_FUNCTION_DEGREE_CSV = "dynamic-distinct-function-degree.csv";
    private static final String STATIC_DISTINCT_FUNCTION_DEGREE_CSV = "static-distinct-function-degree.csv";
    private static final String DYNAMIC_DISTINCT_MODULE_DEGREE_CSV = "dynamic-distinct-module-degree.csv";
    private static final String STATIC_DISTINCT_MODULE_DEGREE_CSV = "static-distinct-module-degree.csv";
    private static final String DYNAMIC_EXTRA_EDGES_CSV = "dynamic-extra-edges.csv";
    private static final String STATIC_EXTRA_EDGES_CSV = "static-extra-edges.csv";

    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

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

        final ComputeExtraSubGraphStage dynamicComputeExtraSubGraph = new ComputeExtraSubGraphStage("dynamic");
        final FunctionNodeCountCouplingStage dynamicFunctionExtraNodeCouplingStage = new FunctionNodeCountCouplingStage();

        final ComputeExtraSubGraphStage staticComputeExtraSubGraph = new ComputeExtraSubGraphStage("static");
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

        /** operation graph. */
        if (parameterConfiguration.getOutputGraphs().contains(EOutputGraph.DOT_OP)
                || parameterConfiguration.getOutputGraphs().contains(EOutputGraph.GRAPHML)) {
            // this.connectPorts(distributor.getNewOutputPort(),
            // operationDependencyGraphCreatorStage.getInputPort());
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
            // this.connectPorts(distributor.getNewOutputPort(),
            // componentDependencyGraphCreatorStage.getInputPort());
        }

        /** Statistics trigger. */
        // this.connectPorts(distributor.getNewOutputPort(),
        // triggerToModelSnapshotStage.getInputPort());
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
