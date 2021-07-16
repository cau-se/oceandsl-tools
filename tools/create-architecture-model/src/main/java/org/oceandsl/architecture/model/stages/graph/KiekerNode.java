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
package org.oceandsl.architecture.model.stages.graph;

import java.util.Objects;

import org.eclipse.emf.ecore.EObject;

import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.graphs.Node;

/**
 * Central node class for graphs
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class KiekerNode<T, R extends EObject> implements Node<T> {

    private final R member;

    /** @param member */
    public KiekerNode(final R member) {
        this.member = member;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(this.member);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KiekerNode)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        final KiekerNode<T, R> other = (KiekerNode<T, R>) obj;
        return Objects.equals(this.member, other.member);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getModule() {
        if (this.member != null) {
            return (T) this.member.eContainer();
        } else {
            return null;
        }
    }

    public R getMember() {
        return this.member;
    }
}
