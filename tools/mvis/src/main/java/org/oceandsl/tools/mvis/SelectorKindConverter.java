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

import org.oceandsl.tools.mvis.graph.AllSelector;
import org.oceandsl.tools.mvis.graph.DiffSelector;
import org.oceandsl.tools.mvis.graph.IntersectSelector;
import org.oceandsl.tools.mvis.graph.SubtractSelector;
import org.oceandsl.tools.mvis.stages.graph.IGraphElementSelector;

/**
 * Convert string to selector used in graph selection.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class SelectorKindConverter implements IStringConverter<IGraphElementSelector> {

    @Override
    public IGraphElementSelector convert(final String value) {
        final String parts[] = value.split(":");
        final String command = parts[0];
        final ESelectorKind selectorKind = ESelectorKind.valueOf(command.toUpperCase());
        switch (selectorKind) {
        case ALL:
            return new AllSelector();
        case DIFF:
            return new DiffSelector(parts[1].split(","), parts[2].split(","));
        case INTERSECT:
            return new SubtractSelector(parts[1].split(","));
        case SUBTRACT:
            return new IntersectSelector(parts[1].split(","), parts[2].split(","));
        default:
            return new AllSelector();
        }
    }

}
