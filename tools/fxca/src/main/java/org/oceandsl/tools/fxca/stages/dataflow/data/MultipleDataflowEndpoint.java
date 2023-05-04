/***************************************************************************
 * Copyright 2023 Kieker Project (http://kieker-monitoring.net)
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
package org.oceandsl.tools.fxca.stages.dataflow.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.oceandsl.tools.fxca.model.IDataflowEndpoint;

import lombok.Getter;

/**
 * @author Reiner Jung
 *
 */
public class MultipleDataflowEndpoint implements IDataflowEndpoint {

    @Getter
    private final String name = "<composed>";

    @Getter
    private final List<DataflowEndpoint> endpoints = new ArrayList<>();

    public void add(final IDataflowEndpoint newEndpoint) {
        if (newEndpoint instanceof DataflowEndpoint) {
            this.add((DataflowEndpoint) newEndpoint);
        } else if (newEndpoint instanceof MultipleDataflowEndpoint) {
            ((MultipleDataflowEndpoint) newEndpoint).endpoints.forEach(endpoint -> this.add(endpoint));
        }
    }

    private void add(final DataflowEndpoint newEndpoint) {
        final Optional<DataflowEndpoint> selectedEndpoint = this.endpoints.stream()
                .filter(endpoint -> endpoint.getModule().equals(newEndpoint.getModule())
                        && endpoint.getOperation().equals(newEndpoint.getOperation()))
                .findFirst();

        if (selectedEndpoint.isPresent()) {
            final DataflowEndpoint endpoint = selectedEndpoint.get();
            endpoint.setDirection(endpoint.getDirection().merge(newEndpoint.getDirection()));
        } else {
            this.endpoints.add(newEndpoint);
        }
    }

}
