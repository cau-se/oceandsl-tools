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
package org.oceandsl.analysis.graph.selector;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.sources.SourceModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.analysis.graph.IGraphElementSelector;

import java.util.Arrays;
import java.util.List;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class SubtractSelector implements IGraphElementSelector {

    private SourceModel sourceModel;
    private final List<String> partitions;
    private String filePrefix; // NOPMD ImmutableField, not possible

    public SubtractSelector(final String[] partitions) {
        this.partitions = Arrays.asList(partitions);
        this.filePrefix = "subtract";
        for (final String partition : partitions) {
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

    @Override
    public boolean edgeIsSelected(OperationDataflow value) {
        return false;
    }

    @Override
    public boolean edgeIsSelected(StorageDataflow value) {
        return false;
    }

    private boolean isSelected(final EList<String> sources) {
        if (sources.size() == this.partitions.size()) {
            if (sources.stream() // NOPMD
                    .allMatch(source -> this.partitions.stream().anyMatch(element -> element.equals(source)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getFilePrefix() {
        return this.filePrefix;
    }

    @Override
    public boolean isColorGroup(final EList<String> sources, final int group) {
        if (group == 0) {
            return this.isGroupSelected(sources, this.partitions);
        } else {
            return false;
        }
    }

    private boolean isGroupSelected(final EList<String> sources, final List<String> group) {
        if (sources.size() == group.size()) {
            if (sources.stream().allMatch(source -> group.stream().anyMatch(element -> element.equals(source)))) { // NOPMD
                return true;
            }
        }
        return false;
    }
}
