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
package org.oceandsl.tools.fxca.stages;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import teetime.stage.basic.AbstractFilter;

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.StatementNode;
import org.oceandsl.tools.fxca.tools.Pair;

/**
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 */
public class ProcessOperationCallStage extends AbstractFilter<FortranProject> {

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().forEach(module -> {
            final Element element = module.getDocument().getDocumentElement();
            try {
                final List<Pair<String, String>> calls = StatementNode.subroutineCalls(element);
                calls.forEach(call -> {
                    final Pair<FortranModule, String> caller = this.findOperation(project.getModules().values(),
                            call.getFirst());
                    final Pair<FortranModule, String> callee = this.findOperation(project.getModules().values(),
                            call.getSecond());
                    if (caller == null) {
                        this.logger.debug("Caller not found for {}", call.getFirst());
                    }
                    if (callee == null) {
                        this.logger.debug("Callee not found for {}", call.getSecond());
                    }
                    module.getCalls().add(new Pair<>(caller, callee));
                });
            } catch (ParserConfigurationException | SAXException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        this.outputPort.send(project);
    }

    private Pair<FortranModule, String> findOperation(final Collection<FortranModule> modules, final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream()
                .filter(module -> module.getSpecifiedOperations().contains(signature)).findFirst();
        if (moduleOptional.isPresent()) {
            return new Pair<>(moduleOptional.get(), signature);
        } else {
            return null;
        }
    }

}
