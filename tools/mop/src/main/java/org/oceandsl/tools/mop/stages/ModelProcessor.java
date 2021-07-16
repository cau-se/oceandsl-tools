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
package org.oceandsl.tools.mop.stages;

import java.util.List;

import org.oceandsl.tools.mop.EOperation;
import org.oceandsl.tools.mop.EStrategy;
import org.oceandsl.tools.mop.operations.ModelRepositoryDiffer;
import org.oceandsl.tools.mop.operations.ModelRepositoryMerger;
import org.oceandsl.tools.mop.operations.ModelRepositorySubtracter;

import kieker.analysis.stage.model.ModelRepository;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author Reiner Jung
 *
 */
public class ModelProcessor extends AbstractConsumerStage<ModelRepository> {

    private final OutputPort<ModelRepository> outputPort = this.createOutputPort(ModelRepository.class);

    private ModelRepository lastModel = null;

    private final List<EOperation> operations;

    private final List<EStrategy> strategies;

    int task = 0;

    public ModelProcessor(final List<EOperation> operations, final List<EStrategy> strategies) {
        this.operations = operations;
        this.strategies = strategies;
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        if (this.lastModel == null) {
            this.lastModel = element;
        } else {
            switch (this.operations.get(this.task)) {
            case DIFF:
                this.diffModel(element, this.strategies.get(this.task));
                break;
            case MERGE:
                this.mergeModel(element, this.strategies.get(this.task));
                break;
            case SUBTRACT:
                this.subtractModel(element, this.strategies.get(this.task));
                break;
            }
            this.task++;
        }
    }

    private void diffModel(final ModelRepository element, final EStrategy strategy) {
        ModelRepositoryDiffer.perform(this.lastModel, element, strategy);
    }

    private void mergeModel(final ModelRepository element, final EStrategy strategy) {
        ModelRepositoryMerger.perform(this.lastModel, element, strategy);
    }

    private void subtractModel(final ModelRepository element, final EStrategy strategy) {
        ModelRepositorySubtracter.perform(this.lastModel, element, strategy);
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.lastModel);
        super.onTerminating();
    }

}
