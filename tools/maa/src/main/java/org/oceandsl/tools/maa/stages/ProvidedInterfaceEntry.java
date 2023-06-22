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
package org.oceandsl.tools.maa.stages;

import org.csveed.annotations.CsvCell;

public class ProvidedInterfaceEntry {

    @CsvCell(columnIndex = 1, columnName = "component-type")
    private final String componentType;
    @CsvCell(columnIndex = 2, columnName = "provided-interface")
    private final String providedInterface;
    @CsvCell(columnIndex = 3, columnName = "operation")
    private final String operation;
    @CsvCell(columnIndex = 4, columnName = "caller-component-types")
    private final String callerComponentTypes;

    public ProvidedInterfaceEntry(final String componentType, final String providedInterface, final String operation,
            final String callerComponentTypes) {
        this.componentType = componentType;
        this.providedInterface = providedInterface;
        this.operation = operation;
        this.callerComponentTypes = callerComponentTypes;
    }

    public String getComponentType() {
        return this.componentType;
    }

    public String getProvidedInterface() {
        return this.providedInterface;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getCallerComponentTypes() {
        return this.callerComponentTypes;
    }

}
