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

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Node;

import lombok.Getter;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CommonBlock implements IDataflowEndpoint {

    @Getter
    private final String name;

    @Getter
    private final Set<String> elements = new HashSet<>();

    @Getter
    private final Node node;

    public CommonBlock(final String name, final Node node) {
        this.name = name;
        this.node = node;
    }

}
