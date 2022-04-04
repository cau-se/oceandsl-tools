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
package org.oceandsl.tools.aul.stages.graph;

import java.util.Objects;

import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;

import kieker.model.analysismodel.deployment.DeployedOperation;

/**
 * Central node class for graphs.
 *
 * @param <T>
 *            type representing modules
 * @param <R>
 *            type representing nodes
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class KiekerNode<T> implements Node<T> {

    private final DeployedOperation member;

    /** @param member */
    public KiekerNode(final DeployedOperation member) {
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
        final KiekerNode<DeployedOperation> other = (KiekerNode<DeployedOperation>) obj;
        return Objects.equals(this.member, other.member);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getModule() {
        if (this.member != null) {
            return (T) this.member.eContainer().eContainer();
        } else {
            return null;
        }
    }

    public DeployedOperation getMember() {
        return this.member;
    }
}
