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
package org.oceandsl.tools.mop.operations;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.oceandsl.tools.mop.EStrategy;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeModel;

/**
 * Diff two models and store the result in the last model repository. The difference is defined as
 * all elements in last model which are not in second model, and all elements in second model which
 * are not in last model. diffModel = lastModel\secondModel combined secondModel\lastModel.
 *
 * For entities with properties they are equal when their name is the same. The other properties are
 * ignored. Contained objects are equal when their name is identical and they belong to the same
 * parent. Entities without a name element are not diffed.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class ModelRepositoryDiffer {

    public static void perform(final ModelRepository lastModelRepository, final ModelRepository secondModelRepository,
            final EStrategy strategy) {
        ModelRepositoryDiffer.diffTypeModel(lastModelRepository.getModel(TypeModel.class),
                secondModelRepository.getModel(TypeModel.class));
    }

    private static void diffTypeModel(final TypeModel firstModel, final TypeModel secondModel) {
        final Map<String, ComponentType> firstComponentMap = ModelRepositoryDiffer.makeMap(firstModel);
        final Map<String, ComponentType> secondComponentMap = ModelRepositoryDiffer.makeMap(secondModel);

        // removeType();
    }

    private static Map<String, ComponentType> makeMap(final TypeModel model) {
        final Map<String, ComponentType> componentMap = new HashMap<>();
        for (final Entry<String, ComponentType> entry : model.getComponentTypes().entrySet()) {
            componentMap.put(entry.getKey(), entry.getValue());
        }
        return componentMap;
    }

}
