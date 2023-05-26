/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.fxca.stages.dataflow;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.tools.fxca.stages.dataflow.data.CommonBlockEntry;

/**
 * Create the call table for a fortran project.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CreateCommonBlocksTableStage extends AbstractTransformation<CommonBlockEntry, Table<GlobalDataEntry>> {

    private static final String COMMON_BLOCKS = "common-blocks";

    private final Table<GlobalDataEntry> commonBlocksTable = new Table<>(CreateCommonBlocksTableStage.COMMON_BLOCKS,
            "common-block", "files", "modules", "variables");

    @Override
    protected void execute(final CommonBlockEntry entry) throws Exception {
        final String files = entry.getModules().stream().map(module -> module.getFileName())
                .reduce((list, element) -> list += "," + element).get();
        final String modules = entry.getModules().stream().map(module -> module.getModuleName())
                .reduce((list, element) -> list += "," + element).get();
        if (entry.getVariables().isEmpty()) {
            this.logger.error("Internal error: Common block {} without variables.", entry.getName());
        } else {
            final String variables = entry.getVariables().stream().reduce((list, element) -> list += "," + element)
                    .get();
            this.commonBlocksTable.getRows().add(new GlobalDataEntry(entry.getName(), files, modules, variables));
        }
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.commonBlocksTable);
        super.onTerminating();
    }

}
