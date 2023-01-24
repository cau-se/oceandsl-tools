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
package org.oceandsl.analysis.architecture;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import kieker.analysis.architecture.repository.ModelRepository;
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
        System.out.println("Print models"); // NOPMD testing tool
        for (final EObject model : repository.getModels().values()) {
            System.out.println("\n\n---------------------"); // NOPMD testing tool
            RepositoryUtils.print(model, "");
        }
        System.out.println("---------------------"); // NOPMD testing tool
    }

    public static void print(final EObject object, final String offset) {
        object.eCrossReferences();
        System.out.println(offset + object.getClass().getCanonicalName()); // NOPMD testing tool
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
                    System.out.printf("%sref %s = {\n", offset, reference.getName()); // NOPMD
                    for (final Object element : list) {
                        if (element instanceof EObject) {
                            ((EObject) element).eCrossReferences();
                            final boolean proxy = ((EObject) element).eIsProxy();
                            System.out.printf("%s %b %s\n", offset, proxy, RepositoryUtils.getName((EObject) element)); // NOPMD
                        }
                    }
                    System.out.printf("%s}\n", offset); // NOPMD testing tool
                } else if (result instanceof EObject) {
                    ((EObject) result).eCrossReferences();
                    final boolean proxy = ((EObject) result).eIsProxy();
                    System.out.printf("%sref %s = %b %s\n", offset, reference.getName(), proxy, // NOPMD
                            RepositoryUtils.getName((EObject) result));
                } else {
                    System.out.printf("%sERROR\n", offset); // NOPMD
                }
            }
        }
    }

    public static Object getName(final EObject result) {
        if (result instanceof DeployedOperation) {
            final DeployedOperation operation = (DeployedOperation) result;
            return String.format("%s::%s::[%s]%s", RepositoryUtils.getName(operation.getComponent().getContext()),
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
                System.out.printf("%s%s = {\n", offset, contained.getName()); // NOPMD
                for (final Object element : list) {
                    if (element instanceof EObject) {
                        RepositoryUtils.print((EObject) element, "  " + offset);
                    }
                }
                System.out.printf("%s}\n", offset); // NOPMD
            } else if (result instanceof EObject) {
                System.out.printf("%s%s = \n", offset, contained.getName()); // NOPMD
                RepositoryUtils.print((EObject) result, "  " + offset);
            } else {
                System.out.printf("%sERROR\n", offset); // NOPMD
            }
        }
    }

    private static void printAttributes(final EObject object, final String offset) {
        for (final EAttribute attribute : object.eClass().getEAllAttributes()) {
            final Object value = object.eGet(attribute);
            if (value != null) {
                System.out.printf("%s%s = '%s'\n", offset, attribute.getName(), value.toString()); // NOPMD
            } else {
                System.out.printf("%s%s = null\n", offset, attribute.getName()); // NOPMD
            }

        }
    }

}
