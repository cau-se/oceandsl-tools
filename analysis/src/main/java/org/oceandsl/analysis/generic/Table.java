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
package org.oceandsl.analysis.generic;

import java.util.ArrayList;
import java.util.List;

import org.csveed.api.Header;
import org.csveed.row.HeaderImpl;
import org.csveed.row.LineWithInfo;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class Table<T> {

    private final String name;
    private final List<T> rows = new ArrayList<>();
    private final Header header;

    public Table(final String name, final String... columnLabels) {
        this.name = name;

        final LineWithInfo line = new LineWithInfo();
        for (final String label : columnLabels) {
            line.addCell(label);
        }

        this.header = new HeaderImpl(line);
    }

    public List<T> getRows() {
        return this.rows;
    }

    public String getName() {
        return this.name;
    }

    public Header getHeader() {
        return this.header;
    }

}
