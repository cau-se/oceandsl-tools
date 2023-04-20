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
package org.oceandsl.analysis.code.stages.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class Table {

    private final IValueHandler<?>[] valueHandlers;
    private final List<Object[]> rows = new ArrayList<>();
    private final String name;

    public Table(final String name, final IValueHandler<?>... valueHandlers) { // NOPMD
                                                                               // ArrayIsStoredDirectly
        this.valueHandlers = valueHandlers;
        this.name = name;
    }

    public void addRow(final Object... values) throws ValueConversionErrorException {
        if (values.length != this.valueHandlers.length) {
            throw new ValueConversionErrorException(String.format("Row must have %d values, but %d values found.",
                    this.valueHandlers.length, values.length));
        }
        final Object[] rowValues = new Object[this.valueHandlers.length];
        for (int i = 0; i < values.length; i++) {
            rowValues[i] = this.valueHandlers[i].checkValue(values[i]);
        }
        this.rows.add(rowValues);
    }

    public List<Object[]> getRows() {
        return this.rows;
    }

    public IValueHandler<?> getValueHandler(final int column) {
        return this.valueHandlers[column];
    }

    public IValueHandler<?>[] getValueHandlers() {
        return this.valueHandlers; // NOPMD exposing internal state necessary
    }

    public String getName() {
        return this.name;
    }

}
