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
package org.oceandsl.tools.mvis;

import java.util.Locale;

import com.beust.jcommander.IStringConverter;

/**
 * @author Reiner Jung
 * @since 1.3
 *
 */
public class StatisticsConverter implements IStringConverter<EStatistics> {

    @Override
    public EStatistics convert(final String value) {
        for (final EStatistics outputGraph : EStatistics.values()) {
            if (outputGraph.name().toLowerCase(Locale.ROOT).replace("_", "-").equals(value)) {
                return outputGraph;
            }
        }
        throw new IllegalArgumentException(String.format("Statistics %s is not supported.", value));

    }

}
