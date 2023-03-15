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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Henning Schnoor
 * @since 1.3.0
 */
public class FortranProject {

    /**
     * Actual list to which List-calls are delegated.
     */
    private final Map<String, FortranModule> modules;

    /**
     * Constructs Project Model with empty content.
     */
    public FortranProject() {
        this.modules = new HashMap<>();
    }

    public Map<String, FortranModule> getModules() {
        return this.modules;
    }
}
