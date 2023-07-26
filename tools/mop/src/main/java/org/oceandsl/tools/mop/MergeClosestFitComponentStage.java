/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.mop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

import teetime.stage.basic.AbstractFilter;

public class MergeClosestFitComponentStage extends AbstractFilter<ModelRepository> {

    private static final double THRESHOLD = 0.4;

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        boolean run = true;
        do {
            // compute component similarities
            final TypeModel typeModel = repository.getModel(TypePackage.Literals.TYPE_MODEL);
            final ComponentType[] components = typeModel.getComponentTypes().values()
                    .toArray(new ComponentType[typeModel.getComponentTypes().values().size()]);
            final List<SimilarityEntry> entries = new ArrayList<>();
            for (int i = 0; i < components.length; i++) {
                final ComponentType left = components[i];
                for (int j = i + 1; j < components.length; j++) {
                    final ComponentType right = components[j];
                    entries.add(new SimilarityEntry(left, right, this.computeSimilarity(left, right)));
                }
            }

            // sort best fit
            entries.sort((a, b) -> {
                if (a.similarity < b.similarity) {
                    return 1;
                } else if (a.similarity > b.similarity) {
                    return -1;
                } else {
                    return 0;
                }
            });

            entries.forEach(entry -> {
                System.err.printf("left: %s right: %s %f\n", entry.left.getSignature(), entry.right.getSignature(),
                        entry.similarity);
            });

            // select best merger
            this.mergeComponent(repository, entries.get(0));
            run = (entries.size() > 1) && (entries.get(1).similarity > MergeClosestFitComponentStage.THRESHOLD);
        } while (run);

        this.outputPort.send(repository);
    }

    private void mergeComponent(final ModelRepository repository, final SimilarityEntry similarityEntry) {
        if (this.isNumber(similarityEntry.left.getSignature()) || this.isHash(similarityEntry.left.getSignature())) {
            this.mergeComponent(repository, similarityEntry.right, similarityEntry.left);
        } else {
            this.mergeComponent(repository, similarityEntry.left, similarityEntry.right);
        }
    }

    private boolean isNumber(final String signature) {
        try {
            Integer.parseInt(signature);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private boolean isHash(final String signature) {
        if (signature.length() == 32) {
            return signature.matches("[a-f0-9]*");
        } else {
            return false;
        }
    }

    private void mergeComponent(final ModelRepository repository, final ComponentType left, final ComponentType right) {
        final TypeModel typeModel = repository.getModel(TypePackage.Literals.TYPE_MODEL);
        final AssemblyModel assemblyModel = repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);
        final DeploymentModel deploymentModel = repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);

        final Collection<OperationType> moveOperation = new ArrayList<>();
        right.getProvidedOperations().values().forEach(rOp -> {
            if (!left.getProvidedOperations().containsKey(rOp.getSignature())) {
                moveOperation.add(rOp);
            }
        });
        moveOperation.forEach(op -> {
            left.getProvidedOperations().put(op.getSignature(), op);
        });

        final AssemblyComponent leftAssembly = assemblyModel.getComponents().get(left.getSignature());
        final AssemblyComponent rightAssembly = assemblyModel.getComponents().get(right.getSignature());

        deploymentModel.getContexts().values().forEach(context -> {
            final DeployedComponent leftDeployment = context.getComponents().get(left.getSignature());
            final DeployedComponent rightDeployment = context.getComponents().get(right.getSignature());

        });
    }

    private double computeSimilarity(final ComponentType left, final ComponentType right) {
        final Set<String> leftSet = left.getProvidedOperations().keySet();
        final Set<String> rightSet = right.getProvidedOperations().keySet();

        final double leftMatch = leftSet.stream().filter(l -> rightSet.contains(l)).count();
        final double rightMatch = rightSet.stream().filter(r -> leftSet.contains(r)).count();

        return ((leftMatch / leftSet.size()) + (rightMatch / rightSet.size())) / 2.0d;
    }

    private class SimilarityEntry {

        private final double similarity;
        private final ComponentType right;
        private final ComponentType left;

        public SimilarityEntry(final ComponentType left, final ComponentType right, final double similarity) {
            this.left = left;
            this.right = right;
            this.similarity = similarity;
        }

    }
}
