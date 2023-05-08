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

import java.util.Locale;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class InputModeValidator implements IParameterValidator {

    @Override
    public void validate(final String name, final String value) throws ParameterException {
        try {
            EInputMode.valueOf(value.toUpperCase(Locale.getDefault()));
        } catch (final IllegalArgumentException e) {
            String modes = null;
            for (final EInputMode mode : EInputMode.values()) {
                if (modes == null) {
                    modes = mode.name().toLowerCase(Locale.getDefault());
                } else {
                    modes += "," + mode.name().toLowerCase(Locale.getDefault());
                }
            }
            throw new ParameterException(String.format("Parameter %s: %s is not a valid input mode. Valid modes are %s",
                    name, value, modes));
        }
    }

}
