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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.oceandsl.tools.fxca.tools.Pair;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Henning Schnoor
 * @since 1.3.0
 */
public class FortranProject {

    /**
     * Actual list to which List-calls are delegated.
     */
    private final Map<String, FortranModule> modules;

    /** note this is a temporary hack until dataflow properly supports a default module. */
    @Getter
    @Setter
    private FortranModule defaultModule;

    @Getter
    private final Collection<Pair<Pair<FortranModule, IDataflowSource>, Pair<FortranModule, IDataflowSource>>> dataflows = new ArrayList<>();

    /**
     * Constructs Project Model with empty content.
     */
    public FortranProject() {
        this.modules = new ContainmentHashMap<>(this);
    }

    public Map<String, FortranModule> getModules() {
        return this.modules;
    }
}
