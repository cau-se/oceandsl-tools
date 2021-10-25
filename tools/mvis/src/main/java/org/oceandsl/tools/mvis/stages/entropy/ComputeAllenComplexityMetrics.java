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
package org.oceandsl.tools.mvis.stages.entropy;

import java.util.HashMap;
import java.util.Map;

import com.google.common.graph.Graph;

import org.mosim.refactorlizar.architecture.evaluation.CalculationMode;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.CodeMetric;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Cohesion;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Complexity;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Coupling;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.HyperGraphSize;
import org.mosim.refactorlizar.architecture.evaluation.cohesion.HyperGraphCohesionCalculator;
import org.mosim.refactorlizar.architecture.evaluation.complexity.HyperGraphComplexityCalculator;
import org.mosim.refactorlizar.architecture.evaluation.coupling.HyperGraphInterModuleCouplingGenerator;
import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;
import org.mosim.refactorlizar.architecture.evaluation.graphs.SystemGraphUtils;
import org.mosim.refactorlizar.architecture.evaluation.size.HyperGraphSizeCalculator;
import org.oceandsl.tools.mvis.stages.metrics.KiekerArchitectureModelSystemGraphUtils;

import kieker.model.analysismodel.deployment.DeployedComponent;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class ComputeAllenComplexityMetrics
        extends AbstractTransformation<Graph<Node<DeployedComponent>>, Map<Class<? extends CodeMetric>, CodeMetric>> {

    @Override
    protected void execute(final Graph<Node<DeployedComponent>> graph) throws Exception {
        final CalculationMode mode = CalculationMode.NO_OFFSET;
        final SystemGraphUtils<DeployedComponent> systemGraphUtils = new KiekerArchitectureModelSystemGraphUtils();

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
            final SystemGraphUtils<DeployedComponent> systemGraphUtils, final Graph<Node<DeployedComponent>> graph) {
        return new HyperGraphSize(new HyperGraphSizeCalculator<DeployedComponent>(mode)
                .calculate(systemGraphUtils.convertToSystemGraph(graph)));
    }

}
