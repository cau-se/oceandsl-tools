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
package org.oceandsl.tools.mop.merge;

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
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public final class ModelUtils {

    private ModelUtils() {
        // Utility class
    }

    public static boolean areObjectsEqual(final EObject left, final EObject right) {
        if (left.getClass().equals(right.getClass())) {
            if (left instanceof DeployedOperation) {
                return ModelUtils.isEqual((DeployedOperation) left, (DeployedOperation) right);
            } else if (left instanceof DeployedStorage) {
                return ModelUtils.isEqual((DeployedStorage) left, (DeployedStorage) right);
            } else if (left instanceof DeployedComponent) {
                return ModelUtils.isEqual((DeployedComponent) left, (DeployedComponent) right);
            } else if (left instanceof DeploymentContext) {
                return ModelUtils.isEqual((DeploymentContext) left, (DeploymentContext) right);
            } else if (left instanceof AssemblyOperation) {
                return ModelUtils.isEqual((AssemblyOperation) left, (AssemblyOperation) right);
            } else if (left instanceof AssemblyComponent) {
                return ModelUtils.isEqual((AssemblyComponent) left, (AssemblyComponent) right);
            } else if (left instanceof AssemblyOperation) {
                return ModelUtils.isEqual((AssemblyOperation) left, (AssemblyOperation) right);
            } else if (left instanceof AssemblyStorage) {
                return ModelUtils.isEqual((AssemblyStorage) left, (AssemblyStorage) right);
            } else if (left instanceof ComponentType) {
                return ModelUtils.isEqual((ComponentType) left, (ComponentType) right);
            } else if (left instanceof OperationType) {
                return ModelUtils.isEqual((OperationType) left, (OperationType) right);
            } else if (left instanceof StorageType) {
                return ModelUtils.isEqual((StorageType) left, (StorageType) right);
            } else if (left instanceof AggregatedInvocation) {
                return ModelUtils.isEqual((AggregatedInvocation) left, (AggregatedInvocation) right);
            } else if (left instanceof AggregatedStorageAccess) {
                return ModelUtils.isEqual((AggregatedStorageAccess) left, (AggregatedStorageAccess) right);
            } else if (left instanceof Tuple) {
                return ModelUtils.isEqual((Tuple<?, ?>) left, (Tuple<?, ?>) right);
            } else {
                throw new InternalError("Missing support for " + left.getClass().getCanonicalName());
            }
        }
        return false;
    }

    public static boolean isEqual(final Tuple<?, ?> leftTuple, final Tuple<?, ?> rightTuple) {
        return ModelUtils.areObjectsEqual((EObject) leftTuple.getFirst(), (EObject) rightTuple.getFirst())
                && ModelUtils.areObjectsEqual((EObject) leftTuple.getSecond(), (EObject) rightTuple.getSecond());
    }

    public static boolean isEqual(final AggregatedStorageAccess leftStorageAccess,
            final AggregatedStorageAccess rightStorageAccess) {
        ModelUtils.check(leftStorageAccess, "AggregatedStorageAccess leftStorageAccess");
        ModelUtils.check(rightStorageAccess, "AggregatedStorageAccess rightStorageAccess");
        return ModelUtils.isEqual(leftStorageAccess.getCode(), rightStorageAccess.getCode())
                && ModelUtils.compareDirections(leftStorageAccess.getDirection(), rightStorageAccess.getDirection())
                && ModelUtils.isEqual(leftStorageAccess.getStorage(), rightStorageAccess.getStorage());
    }

    private static boolean compareDirections(final EDirection leftDirection, final EDirection rightDirection) {
        switch (leftDirection) {
        case READ:
            return rightDirection == EDirection.READ;
        case WRITE:
            return rightDirection == EDirection.WRITE;
        case BOTH:
            return true;
        default:
            throw new InternalError("Found unsupported Direction " + leftDirection.getName());
        }
    }

    public static boolean isEqual(final DeployedStorage leftStorage, final DeployedStorage storage) {
        ModelUtils.check(leftStorage, "DeployedStorage leftStorage");
        ModelUtils.check(storage, "DeployedStorage storage");
        return ModelUtils.isEqual(leftStorage.getAssemblyStorage(), storage.getAssemblyStorage())
                && ModelUtils.isEqual(leftStorage.getComponent(), storage.getComponent());
    }

    public static boolean isEqual(final AssemblyStorage leftAssemblyStorage, final AssemblyStorage assemblyStorage) {
        ModelUtils.check(leftAssemblyStorage, "AssemblyStorage leftAssemblyStorage");
        ModelUtils.check(assemblyStorage, "AssemblyStorage assemblyStorage");
        return ModelUtils.isEqual(leftAssemblyStorage.getStorageType(), assemblyStorage.getStorageType())
                && ModelUtils.isEqual(leftAssemblyStorage.getComponent(), assemblyStorage.getComponent());
    }

    public static boolean isEqual(final AggregatedInvocation leftInvocation,
            final AggregatedInvocation rightInvocation) {
        ModelUtils.check(leftInvocation, "AggregatedInvocation leftInvocation");
        ModelUtils.check(rightInvocation, "AggregatedInvocation rightInvocation");
        return ModelUtils.isEqual(leftInvocation.getSource(), rightInvocation.getSource())
                && ModelUtils.isEqual(leftInvocation.getTarget(), rightInvocation.getTarget());
    }

    public static boolean isEqual(final DeployedOperation leftKey, final DeployedOperation rightKey) {
        ModelUtils.check(leftKey, "DeployedOperation leftKey");
        ModelUtils.check(rightKey, "DeployedOperation rightKey");
        return ModelUtils.isEqual(leftKey.getComponent(), rightKey.getComponent())
                && ModelUtils.isEqual(leftKey.getAssemblyOperation(), rightKey.getAssemblyOperation());
    }

    public static boolean isEqual(final AssemblyOperation leftAssemblyOperation,
            final AssemblyOperation rightAssemblyOperation) {
        return ModelUtils.isEqual(leftAssemblyOperation.getComponent(), rightAssemblyOperation.getComponent())
                && ModelUtils.isEqual(leftAssemblyOperation.getOperationType(),
                        rightAssemblyOperation.getOperationType());
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
        ModelUtils.check(leftAssemblyComponent, "AssemblyComponent leftAssemblyComponent");
        ModelUtils.check(assemblyComponent, "AssemblyComponent assemblyComponent");
        return leftAssemblyComponent.getSignature().equals(assemblyComponent.getSignature())
                && ModelUtils.isEqual(leftAssemblyComponent.getComponentType(), assemblyComponent.getComponentType());
    }

    public static boolean isEqual(final ComponentType leftComponentType, final ComponentType componentType) {
        return leftComponentType.getSignature().equals(componentType.getSignature());
    }

    public static boolean isEqual(final DeployedComponent leftComponent, final DeployedComponent rightComponent) {
        return ModelUtils.isEqual(leftComponent.getSignature(), rightComponent.getSignature())
                && ModelUtils.isEqual(leftComponent.getContext(), rightComponent.getContext());
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

    /** -- debug output. -- */

    /**
     * Print a model tree starting with the given object.
     *
     * @param object
     *            root element
     * @param indent
     *            indent
     */
    public static void printTree(final EObject object, final String indent) {
        final EList<EAttribute> attributes = object.eClass().getEAllAttributes();
        System.err.println(String.format("%s%s", indent, object.getClass().getName())); // NOPMD
        for (final EAttribute attribute : attributes) {
            final Object result = object.eGet(attribute);
            System.err.println(String.format("%s  %s:%s = %s", indent, attribute.getEType().getName(), // NOPMD
                    attribute.getName(), result));
        }

        final EList<EReference> containments = object.eClass().getEAllContainments();
        for (final EReference containment : containments) {
            final Object result = object.eGet(containment);
            System.err.println(String.format("%s  %s", indent, containment.getName())); // NOPMD
            if (result instanceof EcoreEMap) {
                ModelUtils.printMap((EcoreEMap<?, ?>) result, indent + "  ");
            } else if (result instanceof EObject) {
                ModelUtils.printTree((EObject) result, indent + "    ");
            }
        }
    }

    private static void printMap(final EcoreEMap<?, ?> map, final String indent) {
        for (final Entry<?, ?> entry : map) {
            System.err.println(String.format("%s%s :", indent, entry.getKey().toString())); // NOPMD
            ModelUtils.printTree((EObject) entry.getValue(), indent + "  ");
        }
    }

    private static void check(final EObject object, final String name) {
        if (object == null) {
            throw new InternalError("Object of type " + name + " is null");
        }
        if (object.eIsProxy()) {
            throw new InternalError("Object of type " + name + " could not be resolved.");
        }
    }
}
