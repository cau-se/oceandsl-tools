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
public class StringValueHandler implements IValueHandler<String> {

    private final String label;

    public StringValueHandler(final String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String checkValue(final Object value) throws ValueConversionErrorException {
        if (value instanceof String) {
            return (String) value;
        } else {
            return value.toString();
        }
    }

    @Override
    public String convertToString(final Object value) throws ValueConversionErrorException {
        if (value instanceof String) {
            return String.format("\"%s\"", ((String) value).replaceAll("\\\"", "\\\\\""));
        } else {
            throw new ValueConversionErrorException(value + " is not an long value.");
        }
    }

}
