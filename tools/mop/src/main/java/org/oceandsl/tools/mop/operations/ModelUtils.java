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

/**
 * @author Reiner Jung
 *
 */
public class ModelUtils {

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
                ModelUtils.printMap((EcoreEMap) result, indent + "  ");
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
