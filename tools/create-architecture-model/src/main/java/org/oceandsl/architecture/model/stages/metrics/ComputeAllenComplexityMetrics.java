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
package org.oceandsl.architecture.model.stages.metrics;

import java.util.HashMap;
import java.util.Map;

import com.google.common.graph.Graph;

import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.CalculationMode;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.codemetrics.CodeMetric;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.codemetrics.Cohesion;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.codemetrics.Complexity;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.codemetrics.Coupling;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.codemetrics.HyperGraphSize;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.cohesion.HyperGraphCohesionCalculator;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.complexity.HyperGraphComplexityCalculator;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.coupling.HyperGraphInterModuleCouplingGenerator;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.graphs.Node;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.graphs.SystemGraphUtils;
import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.size.HyperGraphSizeCalculator;
import kieker.model.analysismodel.deployment.DeployedOperation;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class ComputeAllenComplexityMetrics extends AbstractConsumerStage<Graph<Node<DeployedOperation>>> {

    private final OutputPort<Map<Class<? extends CodeMetric>, CodeMetric>> outputPort = this.createOutputPort();

    @Override
    protected void execute(final Graph<Node<DeployedOperation>> graph) throws Exception {
        final CalculationMode mode = CalculationMode.REINER;
        final SystemGraphUtils<DeployedOperation> systemGraphUtils = new KiekerArchitectureModelSystemGraphUtils();

        final HyperGraphSize size = this.calculateHyperGraphSize(mode, systemGraphUtils, graph);
        final Complexity graphComplexity = new HyperGraphComplexityCalculator<>(mode, systemGraphUtils)
                .calculate(graph);
        final Coupling graphCoupling = new HyperGraphInterModuleCouplingGenerator<>(mode, systemGraphUtils)
                .calculate(graph);
        final Cohesion cohesion = new HyperGraphCohesionCalculator<>(mode, systemGraphUtils).calculate(graph);

        final Map<Class<? extends CodeMetric>, CodeMetric> result = new HashMap<>();
        result.put(HyperGraphSize.class, size);
        result.put(Complexity.class, graphComplexity);
        result.put(Coupling.class, graphCoupling);
        result.put(Cohesion.class, cohesion);

        this.outputPort.send(result);
    }

    private HyperGraphSize calculateHyperGraphSize(final CalculationMode mode,
            final SystemGraphUtils<DeployedOperation> systemGraphUtils, final Graph<Node<DeployedOperation>> graph) {
        return new HyperGraphSize(new HyperGraphSizeCalculator<DeployedOperation>(mode)
                .calculate(systemGraphUtils.convertToSystemGraph(graph)));
    }

}
