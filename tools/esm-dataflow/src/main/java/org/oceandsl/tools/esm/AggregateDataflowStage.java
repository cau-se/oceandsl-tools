/***************************************************************************
 * Copyright 2023 Kieker Project (http://kieker-monitoring.net)
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
package org.oceandsl.tools.esm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import teetime.stage.basic.AbstractFilter;

import org.oceandsl.tools.esm.stages.CallerCalleeDataflow;
import org.oceandsl.tools.esm.stages.CommonBlockArgumentDataflow;
import org.oceandsl.tools.esm.stages.IDataflowEntry;

/**
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class AggregateDataflowStage extends AbstractFilter<IDataflowEntry> {

    List<CallerCalleeDataflow> callerCalleeDataflows = new ArrayList<>();

    @Override
    protected void execute(final IDataflowEntry entry) throws Exception {
        if (entry instanceof CallerCalleeDataflow) {
            final CallerCalleeDataflow callerCalleeDataflow = (CallerCalleeDataflow) entry;
            this.insertOrMergeDuplicate(callerCalleeDataflow);
        } else if (entry instanceof CommonBlockArgumentDataflow) {
            System.err.println("Missing code");
        }
    }

    @Override
    protected void onTerminating() {
        this.callerCalleeDataflows.forEach(cc -> this.outputPort.send(cc));
        super.onTerminating();
    }

    private void insertOrMergeDuplicate(final CallerCalleeDataflow callerCalleeDataflow) {
        final Optional<CallerCalleeDataflow> ccOptional = this.callerCalleeDataflows.stream()
                .filter(cc -> cc.getSourceFileName().equals(callerCalleeDataflow.getSourceFileName())
                        && cc.getSourceModuleName().equals(callerCalleeDataflow.getSourceModuleName())
                        && cc.getSourceOperationName().equals(callerCalleeDataflow.getSourceOperationName())
                        && cc.getTargetFileName().equals(callerCalleeDataflow.getTargetFileName())
                        && cc.getTargetModuleName().equals(callerCalleeDataflow.getTargetModuleName())
                        && cc.getTargetOperatioName().equals(callerCalleeDataflow.getTargetOperatioName()))
                .findFirst();
        if (ccOptional.isPresent()) {
            ccOptional.get().merge(callerCalleeDataflow.getDirection());
        } else {
            this.callerCalleeDataflows.add(callerCalleeDataflow);
        }
    }

}
