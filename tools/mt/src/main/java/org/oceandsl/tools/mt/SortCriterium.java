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
package org.oceandsl.tools.mt;

public class SortCriterium {

    String columnName;
    EOrder order;

    public SortCriterium(final String columnName, final EOrder order) {
        this.columnName = columnName;
        this.order = order;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public EOrder getOrder() {
        return this.order;
    }
}
