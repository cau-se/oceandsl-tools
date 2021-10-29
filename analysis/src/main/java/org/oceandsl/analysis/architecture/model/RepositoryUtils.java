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
package org.oceandsl.analysis.architecture.model;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedOperation;

/**
 * @author Reiner Jung
 *
 */
public final class RepositoryUtils {

    private RepositoryUtils() {
        // private constructor
    }

    public static void print(final ModelRepository repository) {
        System.out.println("Print models");
        for (final EObject model : repository.getModels().values()) {
            System.out.println("\n\n---------------------");
            RepositoryUtils.print(model, "");
        }
        System.out.println("---------------------");
    }

    public static void print(final EObject object, final String offset) {
        object.eCrossReferences();
        System.out.println(offset + object.getClass().getCanonicalName());
        RepositoryUtils.printAttributes(object, offset + "  ");
        RepositoryUtils.printContainments(object, offset + "  ");
        RepositoryUtils.printReferences(object, offset + "  ");
    }

    private static void printReferences(final EObject object, final String offset) {
        for (final EReference reference : object.eClass().getEAllReferences()) {
            if (!reference.isContainment()) {
                final Object result = object.eGet(reference, true);
                if (result instanceof List<?>) {
                    final List<?> list = (List<?>) result;
                    System.out.println(String.format("%sref %s = {", offset, reference.getName()));
                    for (final Object element : list) {
                        if (element instanceof EObject) {
                            ((EObject) element).eCrossReferences();
                            final boolean proxy = ((EObject) element).eIsProxy();
                            System.out.println(String.format("%s %b %s", offset, proxy,
                                    RepositoryUtils.getName((EObject) element)));
                        }
                    }
                    System.out.println(offset + "}");
                } else {
                    if (result instanceof EObject) {
                        ((EObject) result).eCrossReferences();
                        final boolean proxy = ((EObject) result).eIsProxy();
                        System.out.println(String.format("%sref %s = %b %s", offset, reference.getName(), proxy,
                                RepositoryUtils.getName((EObject) result)));
                    } else {
                        System.out.println(offset + "ERROR");
                    }
                }
            }
        }
    }

    public static Object getName(final EObject result) {
        if (result instanceof DeployedOperation) {
            final DeployedOperation operation = (DeployedOperation) result;
            return String.format("%s::%s::[%s]%s",
                    RepositoryUtils.getName(operation.getComponent().getDeploymentContext()),
                    RepositoryUtils.getName(operation.getComponent()), result.getClass().getSimpleName(),
                    RepositoryUtils.getName(operation.getAssemblyOperation().getOperationType()));

        }
        final EClass clazz = result.eClass();
        for (final EAttribute attribute : clazz.getEAllAttributes()) {
            if ("signature".equals(attribute.getName())) {
                return "signature " + result.eGet(attribute);
            } else if ("name".equals(attribute.getName())) {
                return "name " + result.eGet(attribute);
            }
        }
        return result.toString();
    }

    private static void printContainments(final EObject object, final String offset) {
        for (final EReference contained : object.eClass().getEAllContainments()) {
            final Object result = object.eGet(contained, true);
            if (result instanceof List<?>) {
                final List<?> list = (List<?>) result;
                System.out.println(String.format("%s%s = {", offset, contained.getName()));
                for (final Object element : list) {
                    if (element instanceof EObject) {
                        RepositoryUtils.print((EObject) element, "  " + offset);
                    }
                }
                System.out.println(offset + "}");
            } else {
                if (result instanceof EObject) {
                    System.out.println(String.format("%s%s = ", offset, contained.getName()));
                    RepositoryUtils.print((EObject) result, "  " + offset);
                } else {
                    System.out.println(offset + "ERROR");
                }
            }
        }
    }

    private static void printAttributes(final EObject object, final String offset) {
        for (final EAttribute attribute : object.eClass().getEAllAttributes()) {
            final Object value = object.eGet(attribute);
            if (value != null) {
                System.out.println(String.format("%s%s = '%s'", offset, attribute.getName(), value.toString()));
            } else {
                System.out.println(String.format("%s%s = null", offset, attribute.getName()));
            }

        }
    }

}
