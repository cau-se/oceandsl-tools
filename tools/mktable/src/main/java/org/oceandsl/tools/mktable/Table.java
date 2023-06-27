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
package org.oceandsl.tools.mktable;

import java.util.ArrayList;
import java.util.List;

/**
 * Table consisting of a list of records and an origin label.
 *
 * @author Reiner Jung
 * @since 2.0.0
 *
 * @param <R>
 * @param <T>
 */
public class Table<R, T> {

    private final List<T> list = new ArrayList<>();
    private final R origin;

    public Table(final R origin) {
        this.origin = origin;
    }

    public List<T> getList() {
        return this.list;
    }

    public R getOrigin() {
        return this.origin;
    }

}
