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

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreEMap;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;

/**
 * @author Reiner Jung
 *
 */
public class ModelUtils {

    public static boolean areObjectsEqual(final EObject left, final EObject right) {
        if (left.getClass().equals(right.getClass())) {
            if (right instanceof DeployedOperation) {
                return ModelUtils.isEqual((DeployedOperation) right, (DeployedOperation) left);
            } else if (right instanceof DeployedComponent) {
                return ModelUtils.isEqual((DeployedComponent) right, (DeployedComponent) left);
            } else if (right instanceof DeploymentContext) {
                return ModelUtils.isEqual((DeploymentContext) right, (DeploymentContext) left);
            } else if (right instanceof AssemblyOperation) {
                return ModelUtils.isEqual((AssemblyOperation) right, (AssemblyOperation) left);
            } else if (right instanceof AssemblyComponent) {
                return ModelUtils.isEqual((AssemblyComponent) right, (AssemblyComponent) left);
            } else if (right instanceof AssemblyOperation) {
                return ModelUtils.isEqual((AssemblyOperation) right, (AssemblyOperation) left);
            } else if (right instanceof ComponentType) {
                return ModelUtils.isEqual((ComponentType) right, (ComponentType) left);
            } else if (right instanceof OperationType) {
                return ModelUtils.isEqual((OperationType) right, (OperationType) left);
            } else if (right instanceof StorageType) {
                return ModelUtils.isEqual((StorageType) right, (StorageType) left);
            } else if (right instanceof AggregatedInvocation) {
                return ModelUtils.isEqual((AggregatedInvocation) right, (AggregatedInvocation) left);
            } else if (right instanceof AggregatedStorageAccess) {
                return ModelUtils.isEqual((AggregatedStorageAccess) right, (AggregatedStorageAccess) left);
            } else {
                System.err.println("Missing support for " + right.getClass().getCanonicalName());
            }
        }
        return false;
    }

    public static boolean isEqual(final AggregatedStorageAccess leftStorageAccess,
            final AggregatedStorageAccess storageAccess) {
        return ModelUtils.isEqual(leftStorageAccess.getCode(), storageAccess.getCode())
                && leftStorageAccess.getDirection().equals(storageAccess.getDirection())
                && ModelUtils.isEqual(leftStorageAccess.getStorage(), storageAccess.getStorage());
    }

    public static boolean isEqual(final DeployedStorage leftStorage, final DeployedStorage storage) {
        return ModelUtils.isEqual(leftStorage.getAssemblyStorage(), storage.getAssemblyStorage())
                && ModelUtils.isEqual(leftStorage.getComponent(), storage.getComponent());
    }

    public static boolean isEqual(final AssemblyStorage leftAssemblyStorage, final AssemblyStorage assemblyStorage) {
        return ModelUtils.isEqual(leftAssemblyStorage.getStorageType(), assemblyStorage.getStorageType()) && ModelUtils
                .isEqual(leftAssemblyStorage.getAssemblyComponent(), assemblyStorage.getAssemblyComponent());
    }

    public static boolean isEqual(final AggregatedInvocation leftInvocation, final AggregatedInvocation invocation) {
        return ModelUtils.isEqual(leftInvocation.getSource(), invocation.getSource())
                && ModelUtils.isEqual(leftInvocation.getTarget(), invocation.getTarget());
    }

    public static boolean isEqual(final DeployedOperation leftKey, final DeployedOperation key) {
        return ModelUtils.isEqual(leftKey.getComponent(), key.getComponent())
                && ModelUtils.isEqual(leftKey.getAssemblyOperation(), key.getAssemblyOperation());
    }

    public static boolean isEqual(final AssemblyOperation leftAssemblyOperation,
            final AssemblyOperation assemblyOperation) {
        return ModelUtils.isEqual(leftAssemblyOperation.getAssemblyComponent(),
                assemblyOperation.getAssemblyComponent())
                && ModelUtils.isEqual(leftAssemblyOperation.getOperationType(), assemblyOperation.getOperationType());
    }

    public static boolean isEqual(final OperationType leftOperationType, final OperationType operationType) {
        return leftOperationType.getSignature().equals(operationType.getSignature())
                && ModelUtils.isEqual(leftOperationType.getComponentType(), operationType.getComponentType());
    }

    public static boolean isEqual(final StorageType leftStorageType, final StorageType storageType) {
        return leftStorageType.getName().equals(storageType.getName());
        // && ModelUtils.isEqual(leftStorageType.getComponentType(),
        // storageType.getComponentType());
    }

    public static boolean isEqual(final AssemblyComponent leftAssemblyComponent,
            final AssemblyComponent assemblyComponent) {
        return leftAssemblyComponent.getSignature().equals(assemblyComponent.getSignature())
                && ModelUtils.isEqual(leftAssemblyComponent.getComponentType(), assemblyComponent.getComponentType());
    }

    public static boolean isEqual(final ComponentType leftComponentType, final ComponentType componentType) {
        return leftComponentType.getSignature().equals(componentType.getSignature());
    }

    public static boolean isEqual(final DeployedComponent leftComponent, final DeployedComponent component) {
        return ModelUtils.isEqual(leftComponent.getSignature(), component.getSignature())
                && ModelUtils.isEqual(leftComponent.getDeploymentContext(), component.getDeploymentContext());
    }

    public static boolean isEqual(final String leftSignature, final String signature) {
        if (leftSignature == null) {
            return signature == null;
        } else {
            if (signature == null) {
                return false;
            } else {
                return leftSignature.equals(signature);
            }
        }
    }

    public static boolean isEqual(final DeploymentContext leftDeploymentContext,
            final DeploymentContext deploymentContext) {
        return leftDeploymentContext.getName().equals(deploymentContext.getName());
    }

    /** -- debug output -- */

    public static void printTree(final EObject object, final String indent) {
        final EList<EAttribute> attributes = object.eClass().getEAllAttributes();
        System.err.println(String.format("%s%s", indent, object.getClass().getName()));
        for (final EAttribute attribute : attributes) {
            final Object result = object.eGet(attribute);
            System.err.println(String.format("%s  %s:%s = %s", indent, attribute.getEType().getName(),
                    attribute.getName(), result));
        }

        final EList<EReference> containments = object.eClass().getEAllContainments();
        for (final EReference containment : containments) {
            final Object result = object.eGet(containment);
            System.err.println(String.format("%s  %s", indent, containment.getName()));
            if (result instanceof EcoreEMap) {
                ModelUtils.printMap((EcoreEMap<?, ?>) result, indent + "  ");
            } else if (result instanceof EObject) {
                ModelUtils.printTree((EObject) result, indent + "    ");
            }
        }
    }

    private static void printMap(final EcoreEMap<?, ?> map, final String indent) {
        for (final Entry<?, ?> entry : map) {
            System.err.println(String.format("%s%s :", indent, entry.getKey().toString()));
            ModelUtils.printTree((EObject) entry.getValue(), indent + "  ");
        }
    }

}
