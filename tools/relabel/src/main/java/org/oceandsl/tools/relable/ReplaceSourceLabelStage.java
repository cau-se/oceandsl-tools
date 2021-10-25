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
package org.oceandsl.tools.relable;

import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.sources.SourceModel;
import teetime.stage.basic.AbstractFilter;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class ReplaceSourceLabelStage extends AbstractFilter<ModelRepository> {

    private final List<Replacement> replacements;

    public ReplaceSourceLabelStage(final List<Replacement> replacements) {
        this.replacements = replacements;
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final SourceModel sourceModel = element.getModel(SourceModel.class);
        for (final Replacement replacement : this.replacements) {
            this.processModel(sourceModel, replacement);
        }
        this.outputPort.send(element);
    }

    private void processModel(final SourceModel sourceModel, final Replacement replacement) {
        for (final Entry<EObject, EList<String>> entry : sourceModel.getSources()) {
            this.processReplacement(replacement, entry.getValue());
        }
    }

    private void processReplacement(final Replacement replacement, final EList<String> labels) {
        int matches = 0;
        for (final String find : replacement.getSources()) {
            if (labels.contains(find)) {
                matches++;
            }
        }
        if (matches == replacement.getSources().size()) {
            for (final String find : replacement.getSources()) {
                labels.remove(find);
            }
            if (!labels.contains(replacement.getTarget())) {
                labels.add(replacement.getTarget());
            }
        }
    }
}
