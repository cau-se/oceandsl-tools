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
package org.oceandsl.tools.cmi.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.type.TypePackage;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.architecture.RepositoryUtils;

public class CheckSourceMissingLabelStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        this.logger.info("Check whether source labels are missing for model elements");

        final SourceModel sourceModel = repository.getModel(SourcePackage.Literals.SOURCE_MODEL);

        long errors = 0;
        for (final Entry<EClass, List<Class<? extends EObject>>> modelConfig : this.configureModels().entrySet()) {
            errors += this.checkForSourcesForAllModelElements(modelConfig.getKey().getInstanceTypeName(), sourceModel,
                    repository.getModel(repository.getModelDescriptor(modelConfig.getKey()).getRootClass())
                            .eAllContents(),
                    modelConfig.getValue());
        }
        this.logger.info("Number of missing source labels {}", errors);

        this.outputPort.send(repository);
    }

    private Map<EClass, List<Class<? extends EObject>>> configureModels() {
        final Map<EClass, List<Class<? extends EObject>>> modelConfigs = new HashMap<>();
        modelConfigs.put(TypePackage.Literals.TYPE_MODEL, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(AssemblyPackage.Literals.ASSEMBLY_MODEL, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(DeploymentPackage.Literals.DEPLOYMENT_MODEL, new ArrayList<Class<? extends EObject>>());
        final List<Class<? extends EObject>> executionIgnoreList = new ArrayList<>();
        executionIgnoreList.add(Tuple.class);
        modelConfigs.put(ExecutionPackage.Literals.EXECUTION_MODEL, executionIgnoreList);

        return modelConfigs;
    }

    private long checkForSourcesForAllModelElements(final String modelName, final SourceModel model,
            final TreeIterator<EObject> treeIterator, final List<Class<? extends EObject>> ignoreList) {
        this.logger.info("Source model entries {}", model.getSources().size());
        long errorCount = 0;
        long objectCount = 0;
        while (treeIterator.hasNext()) {
            final EObject object = treeIterator.next();
            if (!(object instanceof BasicEMap.Entry)) {
                if (!this.isOnIgnoreList(ignoreList, object)) { // NOPMD
                    objectCount++;
                    if (model.getSources().get(object) == null) {
                        this.logger.info("Missing source reference for"); // NOPMD
                        RepositoryUtils.print(object, "  ");
                        this.logger.info("----");
                        errorCount++;
                    }
                }
            }
        }

        this.logger.info("Objects in model {} {}", modelName, objectCount);
        return errorCount;
    }

    private boolean isOnIgnoreList(final List<Class<? extends EObject>> ignoreList, final EObject object) {
        for (final Class<? extends EObject> element : ignoreList) {
            if (element.isAssignableFrom(object.getClass())) {
                return true;
            }
        }
        return false;
    }

}
