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
package org.oceandsl.tools.mop.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

import teetime.stage.basic.AbstractTransformation;

public class MergeClosestFitComponentStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    private static final double THRESHOLD = 0.4;

    private ModelRepository lastRepository;

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        repository.getModels().values().forEach(model -> EcoreUtil.resolveAll(model.eResource()));
        if (this.lastRepository == null) {
            this.lastRepository = repository;
        } else {
            // compute component similarities
            final TypeModel lastTypeModel = this.lastRepository.getModel(TypePackage.Literals.TYPE_MODEL);
            final TypeModel newTypeModel = repository.getModel(TypePackage.Literals.TYPE_MODEL);

            final ComponentType[] lastComponents = lastTypeModel.getComponentTypes().values()
                    .toArray(new ComponentType[lastTypeModel.getComponentTypes().values().size()]);
            final ComponentType[] newComponents = newTypeModel.getComponentTypes().values()
                    .toArray(new ComponentType[newTypeModel.getComponentTypes().values().size()]);
            final List<SimilarityEntry> entries = new ArrayList<>();
            for (int i = 0; i < lastComponents.length; i++) {
                final ComponentType left = lastComponents[i];
                for (int j = 0; j < newComponents.length; j++) {
                    final ComponentType right = newComponents[j];
                    entries.add(new SimilarityEntry(left, right, this.computeSimilarity(left, right)));
                }
            }

            // sort best fit
            entries.sort((a, b) -> { // NOCS this is how a comparator is implemented
                if (a.similarity < b.similarity) {
                    return 1;
                } else if (a.similarity > b.similarity) {
                    return -1;
                } else {
                    return 0;
                }
            });

            for (int i = 0; i < entries.size(); i++) {
                final SimilarityEntry current = entries.get(i);
                for (int j = i + 1; j < entries.size(); j++) {
                    final SimilarityEntry next = entries.get(j);
                    if (current.left == next.left || current.left == next.right || current.right == next.left
                            || current.right == next.right) {
                        entries.remove(j);
                        j--; // NOCS, NOPMD this is necessary due to list operations
                    }
                }
            }

            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).similarity < THRESHOLD) {
                    entries.remove(i);
                    i--; // NOCS
                }
            }

            System.err.println("----------------------------------------------");
            entries.forEach(entry -> {
                System.err.printf("left: %s right: %s %f\n", entry.left.getSignature(), entry.right.getSignature(),
                        entry.similarity);
            });

            entries.forEach(entry -> {
                if (this.isNumber(entry.right.getSignature()) || this.isHash(entry.right.getSignature())) {
                    this.fixDeploymentSignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    this.fixAssemblySignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    this.fixTypeSignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    entry.right.setSignature(entry.left.getSignature());
                } else if (this.isNumber(entry.left.getSignature()) || this.isHash(entry.left.getSignature())) {
                    this.fixDeploymentSignature(repository, entry.left.getSignature(), entry.right.getSignature());
                    this.fixAssemblySignature(repository, entry.left.getSignature(), entry.right.getSignature());
                    this.fixTypeSignature(repository, entry.left.getSignature(), entry.right.getSignature());
                    entry.left.setSignature(entry.right.getSignature());
                } else {
                    this.fixDeploymentSignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    this.fixAssemblySignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    this.fixTypeSignature(repository, entry.right.getSignature(), entry.left.getSignature());
                    entry.right.setSignature(entry.left.getSignature());
                }
            });

            System.err.println("----------------------------------------------");
            entries.forEach(entry -> {
                System.err.printf("left: %s right: %s %f\n", entry.left.getSignature(), entry.right.getSignature(),
                        entry.similarity);
            });

            this.outputPort.send(repository);
        }
    }

    private void fixTypeSignature(final ModelRepository repository, final String search, final String replacement) {
        final TypeModel model = repository.getModel(TypePackage.Literals.TYPE_MODEL);
        final ComponentType component = model.getComponentTypes().get(search);
        component.setSignature(replacement);
        component.setName(replacement);
        model.getComponentTypes().removeKey(search);
        model.getComponentTypes().put(replacement, component);
    }

    private void fixAssemblySignature(final ModelRepository repository, final String search, final String replacement) {
        final AssemblyModel model = repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);
        final AssemblyComponent component = model.getComponents().get(search);
        component.setSignature(replacement);
        model.getComponents().removeKey(search);
        model.getComponents().put(replacement, component);
    }

    private void fixDeploymentSignature(final ModelRepository repository, final String search,
            final String replacement) {
        final DeploymentModel model = repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);
        model.getContexts().values().forEach(context -> {
            final DeployedComponent component = context.getComponents().get(search);
            component.setSignature(replacement);
            context.getComponents().removeKey(search);
            context.getComponents().put(replacement, component);
        });
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

    private double computeSimilarity(final ComponentType left, final ComponentType right) {
        final Set<String> leftSet = left.getProvidedOperations().keySet();
        final Set<String> rightSet = right.getProvidedOperations().keySet();

        final double leftMatch = leftSet.stream().filter(l -> rightSet.contains(l)).count();
        final double rightMatch = rightSet.stream().filter(r -> leftSet.contains(r)).count();

        return (leftMatch / leftSet.size() + rightMatch / rightSet.size()) / 2.0d;
    }

    public class SimilarityEntry {

        private final double similarity;
        private final ComponentType right;
        private final ComponentType left;

        public SimilarityEntry(final ComponentType left, final ComponentType right, final double similarity) {
            this.left = left;
            this.right = right;
            this.similarity = similarity;
        }

        public ComponentType getLeft() {
            return this.left;
        }

        public ComponentType getRight() {
            return this.right;
        }

        public double getSimilarity() {
            return this.similarity;
        }
    }
}
