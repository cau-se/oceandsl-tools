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

import org.oceandsl.tools.mvis.graph.IColorDependencyGraphBuilderConfiguration;

import kieker.analysis.stage.model.ModelRepository;

/**
 * @author reiner
 *
 */
public class ColorDependencyGraphBuilderConfiguration implements IColorDependencyGraphBuilderConfiguration {

    private final ModelRepository modelRepository;
    private final String[] groupA;
    private final String[] groupB;

    public ColorDependencyGraphBuilderConfiguration(final ModelRepository modelRepository, final String[] groupA,
            final String[] groupB) {
        this.modelRepository = modelRepository;
        this.groupA = groupA;
        this.groupB = groupB;
    }

    @Override
    public ModelRepository getModelRepository() {
        return this.modelRepository;
    }

    @Override
    public String[] getGroupA() {
        return this.groupA;
    }

    @Override
    public String[] getGroupB() {
        return this.groupB;
    }

}
