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
package org.oceandsl.tools.mvis;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.architecture.model.stages.graph.ISelector;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.sources.SourceModel;

/**
 * @author Reiner Jung
 *
 */
public class SubtractSelector implements ISelector {

    private SourceModel sourceModel;
    private final String partition;
    private final String filePrefix;

    public SubtractSelector(final String partition) {
        this.partition = partition;
        this.filePrefix = "subtract" + partition;
    }

    @Override
    public void setRepository(final ModelRepository repository) {
        this.sourceModel = repository.getModel(SourceModel.class);
    }

    @Override
    public boolean nodeIsSelected(final EObject value) {
        final EList<String> sources = this.sourceModel.getSources().get(value);
        if (sources.size() == 1) {
            if (this.partition.equals(sources.get(0))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean edgeIsSelected(final AggregatedInvocation value) {
        final EList<String> sources = this.sourceModel.getSources().get(value);
        if (sources.size() == 1) {
            if (this.partition.equals(sources.get(0))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getFilePrefix() {
        return this.filePrefix;
    }
}
