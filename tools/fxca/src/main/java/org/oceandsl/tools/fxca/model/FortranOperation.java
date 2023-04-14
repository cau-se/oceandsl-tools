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
package org.oceandsl.tools.fxca.model;

import java.util.Map;

import org.w3c.dom.Node;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class FortranOperation implements IDataflowSource, IContainable {

    @Getter
    private final String name;

    @Getter
    private final Map<String, CommonBlock> commonBlocks = new ContainmentHashMap<>(this);

    @Getter
    private final Map<String, FortranVariable> variables = new ContainmentHashMap<>(this);

    @Getter
    private final Map<String, FortranParameter> parameters = new ContainmentHashMap<>(this);

    @Getter
    private final Node node;

    @Getter
    @Setter
    private FortranModule module;

    @Getter
    @Setter
    private Object parent;

    @Getter
    @Setter
    private boolean implicit;

    @Getter
    private final boolean variableArguments;

    public FortranOperation(final String name, final Node node) {
        this.name = name;
        this.node = node;
        this.variableArguments = false;
    }

    public FortranOperation(final String name, final Node node, final boolean variableArguments) {
        this.name = name;
        this.node = node;
        this.variableArguments = variableArguments;
    }

    @Override
    public String toString() {
        return "op " + this.name;
    }
}
