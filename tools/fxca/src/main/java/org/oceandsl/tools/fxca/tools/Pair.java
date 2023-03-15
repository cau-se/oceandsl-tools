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

import java.util.Comparator;

/**
 * Class for a tuple.
 *
 * @author Henning Schnoor
 *
 * @param <T1>
 *            first element type
 * @param <T2>
 *            second element type
 *
 * @since 1.3.0
 */
public class Pair<T1, T2> {

    public T1 first;
    public T2 second;

    public Pair(final T1 first, final T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        return this.getFirst().hashCode() + this.getSecond().hashCode();
    }

    @Override
    public boolean equals(final Object compareto) {
        if (this == compareto) {
            return true;
        }
        if (compareto == null) {
            return false;
        }
        if (!(compareto instanceof Pair<?, ?>)) {
            return false;
        }

        final var comparePair = (Pair<?, ?>) compareto;

        return (comparePair.first.getClass().equals(this.first.getClass()))
                && (comparePair.second.getClass().equals(this.second.getClass()))
                && (comparePair.first.equals(this.first)) && (comparePair.second.equals(this.second));
    }

    public static <T extends Comparable<T>, S extends Comparable<S>> Comparator<Pair<S, T>> getComparatorFirstSecond() {
        return (pair1, pair2) -> {
            final int result = pair1.first.compareTo(pair2.first);
            if (result != 0) {
                return result;
            }
            return pair1.second.compareTo(pair2.second);
        };
    }

    @Override
    public String toString() {
        return this.getFirst() + " -> " + this.getSecond();
    }
}
