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
package org.oceandsl.tools.fxca.stages;

import java.util.Optional;
import java.util.Set;

import teetime.stage.basic.AbstractFilter;

import org.oceandsl.tools.fxca.model.FortranProject;

/**
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class CheckProjectStage extends AbstractFilter<FortranProject> {

    @Override
    protected void execute(final FortranProject project) throws Exception {
        System.err.println("++ CheckProject");
        project.getModules().values().forEach(module -> module.getOperations().values().forEach(operation -> {
            final boolean duplicate = operation.getParameters().values().stream().map(p -> p.getName())
                    .anyMatch(v -> operation.getVariables().containsKey(v));
            if (duplicate) {
                System.err.printf("----------------------------------\n");
                System.err.printf("op %s\n", operation.getName());
                System.err.printf("  parameter " + this.makeStringSet(operation.getParameters().keySet()));
                System.err.printf("  variables " + this.makeStringSet(operation.getVariables().keySet()));
            }
        }));
        this.outputPort.send(project);
    }

    private String makeStringSet(final Set<String> set) {
        final Optional<String> result = set.stream().reduce((a, b) -> a + "," + b);
        if (result.isPresent()) {
            return result.get();
        } else {
            return "---";
        }
    }

}
