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
package org.oceandsl.pp.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oceandsl.analysis.CallerCallee;

import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class CorrectCallsStage extends AbstractTransformation<CallerCallee, CallerCallee> {

    Map<String, String> callerMap = new HashMap<>();
    Map<String, List<CallerCallee>> incompleteEventsMap = new HashMap<>();

    public CorrectCallsStage() {
        super();
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        if (this.callerMap.get(element.getCaller()) == null) {
            this.callerMap.put(element.getCaller(), element.getSourcePath());
            final List<CallerCallee> incompleteEvents = this.incompleteEventsMap.get(element.getCaller());
            if (incompleteEvents != null) {
                for (final CallerCallee event : incompleteEvents) {
                    event.setTargetPath(element.getSourcePath());
                    this.outputPort.send(event);
                }
                this.incompleteEventsMap.remove(element.getCaller());
            }
        }
        if ("".equals(element.getTargetPath())) {
            final String targetPath = this.callerMap.get(element.getCallee());
            if (targetPath == null) {
                final List<CallerCallee> incompleteEvents = this.incompleteEventsMap.get(element.getCallee());
                if (incompleteEvents == null) {
                    final List<CallerCallee> newList = new ArrayList<>();
                    newList.add(element);
                    this.incompleteEventsMap.put(element.getCallee(), newList);
                } else {
                    incompleteEvents.add(element);
                }
            } else {
                element.setTargetPath(targetPath);
                this.outputPort.send(element);
            }
        }
    }

    @Override
    protected void onTerminating() {
        for (final List<CallerCallee> unprocessedEvents : this.incompleteEventsMap.values()) {
            for (final CallerCallee event : unprocessedEvents) {
                event.setTargetPath("<UNKNOWN>");
                this.outputPort.send(event);
            }
        }
        super.onTerminating();
    }
}
