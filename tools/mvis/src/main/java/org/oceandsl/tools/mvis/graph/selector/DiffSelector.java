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
package org.oceandsl.tools.mvis.graph.selector;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.tools.mvis.stages.graph.IGraphElementSelector;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.sources.SourceModel;

/**
 * @author Reiner Jung
 *
 */
public class DiffSelector implements IGraphElementSelector {

    private SourceModel sourceModel;
    private final List<String> groupA;
    private final List<String> groupB;
    private String filePrefix;

    public DiffSelector(final String[] groupA, final String[] groupB) {
        this.groupA = Arrays.asList(groupA);
        this.groupB = Arrays.asList(groupB);
        this.filePrefix = "diff";
        for (final String partition : groupA) {
            this.filePrefix += "-" + partition;
        }
        for (final String partition : groupB) {
            this.filePrefix += "-" + partition;
        }
    }

    @Override
    public void setRepository(final ModelRepository repository) {
        this.sourceModel = repository.getModel(SourceModel.class);
    }

    @Override
    public boolean nodeIsSelected(final EObject value) {
        final EList<String> sources = this.sourceModel.getSources().get(value);
        return this.isSelected(sources);
    }

    @Override
    public boolean edgeIsSelected(final AggregatedInvocation value) {
        final EList<String> sources = this.sourceModel.getSources().get(value);
        return this.isSelected(sources);
    }

    private boolean isSelected(final EList<String> sources) {
        return this.isGroupSelected(sources, this.groupA) || this.isGroupSelected(sources, this.groupB);
    }

    @Override
    public String getFilePrefix() {
        return this.filePrefix;
    }

    @Override
    public boolean isColorGroup(final EList<String> sources, final int group) {
        if (group == 0) {
            return this.isGroupSelected(sources, this.groupA) && this.isGroupSelected(sources, this.groupB);
        } else if (group == 1) {
            return this.isGroupSelected(sources, this.groupA);
        } else if (group == 2) {
            return this.isGroupSelected(sources, this.groupB);
        } else {
            return false;
        }
    }

    private boolean isGroupSelected(final EList<String> sources, final List<String> group) {
        if (sources.size() == group.size()) {
            if (sources.stream().allMatch(source -> group.stream().anyMatch(element -> element.equals(source)))) {
                return true;
            }
        }
        return false;
    }

}
