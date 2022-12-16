/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStoreObject {

    private final String componentName;
    private String componentPackage = "Global";
    private List<String> implementedOperations = new ArrayList<>();

    public ComponentStoreObject(final String componentName) {
        this.componentName = componentName;
    }

    public List<String> getImplementedOperations() {
        return this.implementedOperations;
    }

    public void setImplementedOperations(final List<String> implementedOperations) {
        this.implementedOperations = implementedOperations;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public void addOperationToOperations(final String operationIdent) {
        this.implementedOperations.add(operationIdent);
    }

    public String getComponentPackage() {
        return this.componentPackage;
    }

    public void setComponentPackage(final String componentPackage) {
        this.componentPackage = componentPackage;
    }
}
