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
package org.oceandsl.architecture.model.data.table;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class IntegerValueHandler implements IValueHandler<Integer> {

    private final String label;

    public IntegerValueHandler(final String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Integer checkValue(final Object value) throws ValueConversionErrorException {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Long) {
            return ((Long) value).intValue();
        } else {
            throw new ValueConversionErrorException(value + " is not an integer value.");
        }
    }

    @Override
    public String convertToString(final Object value) throws ValueConversionErrorException {
        if (value instanceof Integer) {
            return String.valueOf(value);
        } else {
            throw new ValueConversionErrorException(value + " is not an long value.");
        }
    }

}
