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
package org.oceandsl.tools.fxca.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Henning Schnoor
 *
 * @since 1.3.0
 */
public class ListTools {

    // mutable versions of List.of
    public static <T> List<T> ofM() {
        return new ArrayList<>();
    }

    public static <T> List<T> ofM(final T element) {
        final List<T> result = ListTools.ofM();
        result.add(element);
        return result;
    }

    public static <T> List<T> ofM(final List<T> list1, final List<T> list2, final Comparator<T> comparator) {
        final List<T> newList = (list1 == null) ? ListTools.ofM() : ListTools.ofM(list1);
        if (list2 != null) {
            newList.addAll(list2);
        }
        Collections.sort(newList, comparator);
        return newList;
    }

    public static <T> List<T> ofM(final Iterable<T> elements) {
        return ListTools.ofM(elements.iterator());
    }

    public static <T> List<T> ofM(final Iterable<T> elements, final Comparator<T> comparator) {
        final List<T> result = ListTools.ofM(elements.iterator());
        Collections.sort(result, comparator);
        return result;
    }

    public static <T> List<T> ofM(final Iterator<T> iterator) {
        final List<T> newList = ListTools.ofM();
        iterator.forEachRemaining(newList::add);
        return newList;
    }

    public static <T> T getUniqueElement(final Collection<T> collection) {
        if (collection.size() != 1) {
            final HashSet<T> checkForDoubles = new HashSet<>(collection);
            checkForDoubles.forEach(System.out::println);
            if (checkForDoubles.size() == 1) {
                return ListTools.getUniqueElement(checkForDoubles);
            }
            throw new IllegalArgumentException("Unique element exists only for singletons.");

        }

        return collection.iterator().next();
    }

    public static <T> T getUniqueElementIfNonEmpty(final Collection<T> collection, final T alternative) {
        return collection.isEmpty() ? alternative : ListTools.getUniqueElement(collection);
    }

}
