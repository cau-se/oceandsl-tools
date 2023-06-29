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

/**
 * Component statistics class.
 *
 * @author Reiner Jung
 * @since 2.0.0
 */
public class ComponentStatistics {

    @CsvCell(columnIndex = 1, columnName = "component")
    private String componentName;
    @CsvCell(columnIndex = 2, columnName = "operations")
    private int operations;
    @CsvCell(columnIndex = 3, columnName = "provided-operations")
    private long providedOperations;
    @CsvCell(columnIndex = 4, columnName = "requires-operations")
    private long requiredOperations;

    public ComponentStatistics(final String componentName, final int operations, final long providedOperations,
            final long requiredOperations) {
        this.componentName = componentName;
        this.operations = operations;
        this.providedOperations = providedOperations;
        this.requiredOperations = requiredOperations;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public void setComponentName(final String componentName) {
        this.componentName = componentName;
    }

    public int getOperations() {
        return this.operations;
    }

    public void setOperations(final int operations) {
        this.operations = operations;
    }

    public long getProvidedOperations() {
        return this.providedOperations;
    }

    public void setProvidedOperations(final long providedOperations) {
        this.providedOperations = providedOperations;
    }

    public long getRequiredOperations() {
        return this.requiredOperations;
    }

    public void setRequiredOperations(final long requiredOperations) {
        this.requiredOperations = requiredOperations;
    }

}
