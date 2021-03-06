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
package org.oceandsl.tools.mvis;

import java.util.Iterator;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;

/**
 * @author Reiner Jung
 * @since 1.2.0
 *
 */
public final class FullyQualifiedNamesFactory {

    private FullyQualifiedNamesFactory() {
        // factory
    }

    public static String createFullyQualifiedName(final DeployedOperation operation) {
        return String.format("%s.%s", FullyQualifiedNamesFactory.createFullyQualifiedName(operation.getComponent()),
                operation.getAssemblyOperation().getOperationType().getSignature());
    }

    public static String createFullyQualifiedName(final DeployedComponent component) {
        return String.format("%s@%s_%d", component.getContext().getName(),
                FullyQualifiedNamesFactory.createFullyQualifiedName(component.getAssemblyComponent()),
                FullyQualifiedNamesFactory.findIndexNumber(component));
    }

    public static String createFullyQualifiedName(final AssemblyOperation operation) {
        return String.format("%s.%s", FullyQualifiedNamesFactory.createFullyQualifiedName(operation.getComponent()),
                operation.getOperationType().getSignature());
    }

    public static String createFullyQualifiedName(final AssemblyStorage storage) {
        return String.format("%s.%s", FullyQualifiedNamesFactory.createFullyQualifiedName(storage.getComponent()),
                storage.getStorageType().getName());
    }

    public static String createFullyQualifiedName(final AssemblyComponent component) {
        return String.format("%s_%d", component.getComponentType().getSignature(),
                FullyQualifiedNamesFactory.findIndexNumber(component));
    }

    public static int findIndexNumber(final AssemblyComponent component) {
        final Iterator<AssemblyComponent> iterator = ((AssemblyModel) (component.eContainer().eContainer()))
                .getComponents().values().iterator();
        final int numberOfComponent = 0;
        while (iterator.hasNext()) {
            final AssemblyComponent value = iterator.next();
            if (value.equals(component)) {
                return numberOfComponent;
            }
        }
        return -1;
    }

    public static int findIndexNumber(final DeployedComponent component) {
        final Iterator<DeployedComponent> iterator = component.getContext().getComponents().values().iterator();
        final int numberOfComponent = 0;
        while (iterator.hasNext()) {
            final DeployedComponent value = iterator.next();
            if (value.equals(component)) {
                return numberOfComponent;
            }
        }
        return -1;
    }

}
