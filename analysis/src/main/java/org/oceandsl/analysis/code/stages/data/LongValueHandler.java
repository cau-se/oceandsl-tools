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

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class LongValueHandler implements IValueHandler<Long> {

    private final String label;

    public LongValueHandler(final String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Long checkValue(final Object value) throws ValueConversionErrorException {
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        } else if (value instanceof Long) {
            return (Long) value;
        } else {
            throw new ValueConversionErrorException(value + " is not an long value.");
        }
    }

    @Override
    public String convertToString(final Object value) throws ValueConversionErrorException {
        if (value instanceof Long) {
            return String.valueOf(value);
        } else {
            throw new ValueConversionErrorException(value + " is not an long value.");
        }
    }

}
