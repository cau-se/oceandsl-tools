/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.analysis.code.stages.data;

/**
 * Record factory for a {@link CallerCallee} record supporting CSV file reader.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CallerCalleeFactory implements ICsvRecordFactory<CallerCallee> {

    @Override
    public CallerCallee createRecord(final String[] headerLabels, final String[] values) {
        return new CallerCallee(values[0], values[1], values[2], values[3], values[4], values[5]);
    }

}
