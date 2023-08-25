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

import org.apache.commons.text.similarity.LevenshteinDistance;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.analysis.generic.data.MoveOperationEntry;

/**
 * @author reiner
 *
 */
public class SubstringOrderer {

    private final Table<String, MoveOperationEntry> table;

    public SubstringOrderer(final Table<String, MoveOperationEntry> table) {
        this.table = table;
    }

    public void computeDistances() {
        final LevenshteinDistance distance = new LevenshteinDistance();
        for (int i = 0; i < this.table.getRows().size(); i++) {
            final MoveOperationEntry a = this.table.getRows().get(i);
            for (int j = i + 1; j < this.table.getRows().size(); j++) {
                final MoveOperationEntry b = this.table.getRows().get(j);
                final Integer value = distance.apply(a.getOperationName(), b.getOperationName());
                System.err.printf("%s <-> %s : %d\n", a.getOperationName(), b.getOperationName(), value);
            }
        }
    }
}
