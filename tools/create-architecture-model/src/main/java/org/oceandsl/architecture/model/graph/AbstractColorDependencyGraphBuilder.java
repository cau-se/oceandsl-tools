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
package org.oceandsl.architecture.model.graph;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.graph.dependency.AbstractDependencyGraphBuilder;
import kieker.analysis.model.ModelRepository;
import kieker.model.analysismodel.sources.SourceModel;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public abstract class AbstractColorDependencyGraphBuilder extends AbstractDependencyGraphBuilder {

    private final SourceModel sourcesModel;

    public AbstractColorDependencyGraphBuilder(final ModelRepository repository) {
        super(repository);
        this.sourcesModel = repository.getModel(SourceModel.class);
    }

    protected String selectForegroundColor(final EObject object) {
        final EList<String> sources = this.sourcesModel.getSources().get(object);
        if (this.contains(sources, "static", "dynamic")) {
            return "#000000";
        } else if (this.contains(sources, "static")) {
            return "#0000ff"; // blue for static
        } else if (this.contains(sources, "dynamic")) {
            return "#00ff00"; // green on dynamic
        } else {
            return "#ff00ff"; // pink on error
        }
    }

    protected String selectBackgroundColor(final EObject object) {
        final EList<String> sources = this.sourcesModel.getSources().get(object);
        if (this.contains(sources, "static", "dynamic")) {
            return "#ffffff";
        } else if (this.contains(sources, "static")) {
            return "#a0a0ff"; // blue for static
        } else if (this.contains(sources, "dynamic")) {
            return "#90ff90"; // green on dynamic
        } else {
            return "#ffa0ff"; // pink on error
        }
    }

    private boolean contains(final EList<String> operationSource, final String... labels) {
        boolean result = true;
        for (final String label : labels) {
            result &= operationSource.contains(label);
        }
        return result;
    }
}
