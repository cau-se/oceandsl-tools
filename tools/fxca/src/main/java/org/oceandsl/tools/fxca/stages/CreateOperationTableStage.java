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
package org.oceandsl.tools.fxca.stages;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.fxca.model.FortranProject;

/**
 * Create a table containing operations.
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 */
public class CreateOperationTableStage extends AbstractTransformation<FortranProject, Table> {

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table callsTable = new Table("operation", new StringValueHandler("file"),
                new StringValueHandler("operation"));
        project.getModules().values().forEach(module -> {
            final String path = module.getFileName();
            module.getSpecifiedOperations().forEach(operation -> {
                try {
                    callsTable.addRow(path, operation);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Error writing values to operation table: {}", e.getLocalizedMessage());
                }
            });
        });

        this.outputPort.send(callsTable);
    }

}
