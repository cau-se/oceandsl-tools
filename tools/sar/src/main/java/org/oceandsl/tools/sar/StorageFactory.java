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
package org.oceandsl.tools.sar;

import org.oceandsl.analysis.code.stages.data.ICsvRecordFactory;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class StorageFactory implements ICsvRecordFactory<Storage> {

    @Override
    public Storage createRecord(final String[] headerLabels, final String[] values) {
        final Storage storage = new Storage(values[0]);
        for (final String file : values[1].split(",")) {
            storage.getFiles().add(file);
        }
        for (final String module : values[2].split(",")) {
            storage.getModules().add(module);
        }
        for (final String variable : values[3].split(",")) {
            storage.getVariables().add(variable);
        }
        return storage;
    }

}
