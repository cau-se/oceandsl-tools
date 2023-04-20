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
package org.oceandsl.tools.fxca.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class FortranVariable implements IContainable, IDataflowEndpoint {

    @Getter
    String name;

    @Getter
    @Setter
    String type;

    @Getter
    @Setter
    EDirection direction;

    @Getter
    @Setter
    Object parent;

    @Getter
    Set<IDataflowEndpoint> sources = new HashSet<>();

    public FortranVariable(final String name) {
        this.direction = EDirection.NONE;
        this.name = name;
    }

    public void addDirection(final EDirection value) {
        switch (this.direction) {
        case NONE:
            this.direction = value;
            break;
        case READ:
            if (value == EDirection.WRITE || value == EDirection.BOTH) {
                this.direction = EDirection.BOTH;
            } else {
                this.direction = EDirection.READ;
            }
            break;
        case WRITE:
            if (value == EDirection.READ || value == EDirection.BOTH) {
                this.direction = EDirection.BOTH;
            } else {
                this.direction = EDirection.WRITE;
            }
            break;
        case BOTH:
            this.direction = EDirection.BOTH;
            break;
        }
    }

}
