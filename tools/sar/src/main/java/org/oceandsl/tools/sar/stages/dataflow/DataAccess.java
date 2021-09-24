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
package org.oceandsl.tools.sar.stages.dataflow;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class DataAccess {

    private final String module;
    private final String operation;
    private final EDirection direction;
    private final String sharedData;

    public DataAccess(final String module, final String operation, final EDirection direction,
            final String sharedData) {
        this.module = module;
        this.operation = operation;
        this.direction = direction;
        this.sharedData = sharedData;
    }

    public String getModule() {
        return this.module;
    }

    public String getOperation() {
        return this.operation;
    }

    public EDirection getDirection() {
        return this.direction;
    }

    public String getSharedData() {
        return this.sharedData;
    }

}
