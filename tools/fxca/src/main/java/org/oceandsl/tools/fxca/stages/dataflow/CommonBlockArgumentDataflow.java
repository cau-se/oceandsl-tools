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

import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.stages.dataflow.data.IDataflowEntry;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class CommonBlockArgumentDataflow implements IDataflowEntry {

    @Getter
    private final String commonBlockName;

    @Getter
    private final String fileName;

    @Getter
    private final String moduleName;

    @Getter
    private final String operationName;

    @Getter
    @Setter
    private EDirection direction;

    public CommonBlockArgumentDataflow(final String commonBlockName, final String fileName, final String moduleName,
            final String operationName, final EDirection direction) {
        this.commonBlockName = commonBlockName;
        this.fileName = fileName;
        this.moduleName = moduleName;
        this.operationName = operationName;
        this.direction = direction;
    }

}
