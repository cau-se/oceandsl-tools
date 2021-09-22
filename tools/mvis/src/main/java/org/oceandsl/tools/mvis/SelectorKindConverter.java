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

import com.beust.jcommander.IStringConverter;

import org.oceandsl.architecture.model.stages.graph.ISelector;

/**
 * Convert string to selector used in graph selection.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class SelectorKindConverter implements IStringConverter<ISelector> {

    private static final String DEFAULT_SELECTOR = "default";
    private static final String DIFF_SELECTOR = "diff";
    private static final String SUBTRACT_SELECTOR = "subtract";
    private static final String INTERSECT_SELECTOR = "intersect";

    @Override
    public ISelector convert(final String value) {
        final String parts[] = value.split(":");
        final String command = parts[0];
        if (SelectorKindConverter.DEFAULT_SELECTOR.equals(command)) {
            return new DefaultSelector();
        } else if (SelectorKindConverter.DIFF_SELECTOR.equals(command)) {
            return new DiffSelector(parts[1].split(","));
        } else if (SelectorKindConverter.SUBTRACT_SELECTOR.equals(command)) {
            return new SubtractSelector(parts[1]);
        } else if (SelectorKindConverter.INTERSECT_SELECTOR.equals(command)) {
            return new IntersectSelector(parts[1].split(","));
        } else {
            return new DefaultSelector();
        }
    }

}
