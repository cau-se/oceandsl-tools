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
package org.oceandsl.tools.esm.stages;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.tools.esm.util.Output;

/**
 * @author reiner
 *
 */
public class OutputStage extends AbstractConsumerStage<Output> {

    private final OutputPort<Table> dataflowPort = this.createOutputPort(Table.class);
    private final OutputPort<Table> commonBlocksPort = this.createOutputPort(Table.class);
    private final OutputPort<Table> fileContentPort = this.createOutputPort(Table.class);

    @Override
    protected void execute(final Output element) throws Exception {
        this.dataflowPort.send(element.getDataflow());
        this.fileContentPort.send(element.getFileContent());
    }

    public OutputPort<Table> getDataflowPort() {
        return this.dataflowPort;
    }

    public OutputPort<Table> getFileContentPort() {
        return this.fileContentPort;
    }

    public OutputPort<Table> getCommonBlocksPort() {
        return this.commonBlocksPort;
    }

}
