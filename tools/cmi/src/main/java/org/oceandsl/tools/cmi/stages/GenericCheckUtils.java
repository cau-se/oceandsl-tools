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
package org.oceandsl.tools.cmi.stages;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.slf4j.Logger;

import org.oceandsl.analysis.architecture.RepositoryUtils;

public final class GenericCheckUtils {

    private GenericCheckUtils() {
    }

    public static long checkReferences(final EClass modelClass, final TreeIterator<EObject> iterator) {
        System.out.printf("Model %s\n", modelClass.getName()); // NOPMD
        long errorCount = 0;
        while (iterator.hasNext()) {
            final EObject object = iterator.next();
            final EClass clazz = object.eClass();
            for (final EReference reference : clazz.getEAllReferences()) {
                final Object referencedObject = object.eGet(reference, true);
                if (referencedObject == null) {
                    System.out.printf("Missing referenced object: %s:%s -> %s:%s\n", // NOPMD
                            object.getClass().getSimpleName(), RepositoryUtils.getName(object),
                            reference.getEType().getName(), reference.getName());
                    errorCount++;

                }
            }
        }
        return errorCount;
    }

    public static long missingSignature(final TreeIterator<EObject> iterator, final Logger logger) {
        long errorCount = 0;
        while (iterator.hasNext()) {
            final EObject object = iterator.next();
            final EClass clazz = object.eClass();
            for (final EAttribute attribute : clazz.getEAllAttributes()) {
                if ("signature".equals(attribute.getName())) {
                    final Object value = object.eGet(attribute);
                    if (value == null) {
                        logger.info("Object {} of type {} has signature attribute, but no value", // NOPMD
                                print(object), clazz.getName());
                        errorCount++;
                    }
                }
            }
        }
        return errorCount;
    }

    private static String print(final EObject object) {
        final EClass clazz = object.eClass();
        String result = "class " + clazz.getName();
        for (final EAttribute attribute : clazz.getEAllAttributes()) {
            result += " " + attribute.getName() + "=" + object.eGet(attribute);
        }
        for (final EReference reference : clazz.getEAllReferences()) {
            result += " " + reference.getEReferenceType().getName() + ":" + reference.getName() + "="
                    + object.eGet(reference);
        }
        return result;
    }
}
