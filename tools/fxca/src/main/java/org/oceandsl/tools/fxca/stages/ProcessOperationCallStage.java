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

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractFilter;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Pair;

/**
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 */
public class ProcessOperationCallStage extends AbstractFilter<FortranProject> {

    private final OutputPort<Table> notFoundOutputPort = this.createOutputPort(Table.class);

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table notFoundTable = new Table("not-found", new StringValueHandler("caller-path"),
                new StringValueHandler("caller-module"), new StringValueHandler("caller-operation"),
                new StringValueHandler("callee-operation"));

        project.getModules().values().forEach(module -> {
            final Element element = module.getDocument().getDocumentElement();
            this.processSubroutines(project, module, element, notFoundTable);
            // this.processFunctions(project, module, element, notFoundTable);
        });

        this.outputPort.send(project);
        this.notFoundOutputPort.send(notFoundTable);
    }

    private void processSubroutines(final FortranProject project, final FortranModule module, final Element element,
            final Table notFoundTable) {
        try {
            final List<Pair<String, String>> calls = NodeProcessingUtils.subroutineCalls(element);
            this.processCalls(project, module, calls, notFoundTable);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.logger.error("Processing subroutine calls in file {} failed: {}", element.getBaseURI(),
                    e.getLocalizedMessage());
        }
    }

    private void processFunctions(final FortranProject project, final FortranModule module, final Element element,
            final Table notFoundTable) {
        try {
            final List<Pair<String, String>> calls = NodeProcessingUtils.functionCalls(element);
            this.processCalls(project, module, calls, notFoundTable);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.logger.error("Processing subroutine calls in file {} failed: {}", element.getBaseURI(),
                    e.getLocalizedMessage());
        }

    }

    private void processCalls(final FortranProject project, final FortranModule module,
            final List<Pair<String, String>> calls, final Table notFoundTable) {
        calls.forEach(call -> {
            final Pair<FortranModule, String> caller = this.findOperation(project.getModules().values(),
                    call.getFirst());
            final Pair<FortranModule, String> callee = this.findOperation(project.getModules().values(),
                    call.getSecond());
            if (caller == null) {
                this.logger.info("Caller not found for {}", call.getFirst());
            }
            if (callee == null) {
                try {
                    notFoundTable.addRow(caller.first.getDocument().getBaseURI(), caller.first.getModuleName(),
                            caller.second, call.second);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add row to callee not found table: {}", e.getLocalizedMessage());
                }
                this.logger.info("Callee not found for {}", call.getSecond());
            }
            module.getCalls().add(new Pair<>(caller, callee));
        });
    }

    public OutputPort<Table> getNotFoundOutputPort() {
        return this.notFoundOutputPort;
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
