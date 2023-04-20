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
package org.oceandsl.tools.fxca.stages.calls;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
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
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.utils.NodeUtils;
import org.oceandsl.tools.fxca.utils.Pair;

/**
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 * @since 1.3.0
 */
public class ProcessOperationCallStage extends AbstractFilter<FortranProject> {

    private final OutputPort<Table> notFoundOutputPort = this.createOutputPort(Table.class);

    public ProcessOperationCallStage() {
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table notFoundTable = new Table("not-found", new StringValueHandler("caller-path"),
                new StringValueHandler("caller-module"), new StringValueHandler("caller-operation"),
                new StringValueHandler("callee-operation"));

        project.getModules().values().stream().filter(module -> module != project.getDefaultModule())
                .forEach(module -> {
                    final Element element = module.getDocument().getDocumentElement();
                    this.processSubroutines(project, module, element, notFoundTable);
                    this.processFunctions(project, module, element, notFoundTable);
                });

        this.outputPort.send(project);
        this.notFoundOutputPort.send(notFoundTable);
    }

    private void processSubroutines(final FortranProject project, final FortranModule module, final Element element,
            final Table notFoundTable) {
        try {
            final List<Pair<String, String>> calls = NodeUtils.findSubroutineCalls(element);
            this.processCalls(project, module, calls, notFoundTable);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.logger.error("Processing subroutine calls in file {} failed: {}", element.getBaseURI(),
                    e.getLocalizedMessage());
        }
    }

    private void processFunctions(final FortranProject project, final FortranModule module, final Element element,
            final Table notFoundTable) {
        try {
            final List<Pair<String, String>> calls = NodeUtils.findFunctionCalls(element);
            this.processCalls(project, module, calls, notFoundTable);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.logger.error("Processing subroutine calls in file {} failed: {}", element.getBaseURI(),
                    e.getLocalizedMessage());
        }

    }

    private void processCalls(final FortranProject project, final FortranModule module,
            final List<Pair<String, String>> calls, final Table notFoundTable) {
        calls.forEach(call -> {
            final Pair<FortranModule, FortranOperation> caller = this.findOperation(project.getModules().values(),
                    call.getFirst());
            final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                    call.getSecond());
            if (caller == null) {
                this.logger.info("Caller not found for {}", call.getFirst());
            }
            if (callee == null) {
                if (!this.isCommonBlockVariable(module, call.getSecond())
                        && !this.isVariableReference(module, call.getSecond())
                        && !caller.getSecond().getVariables().containsKey(call.getSecond())
                        && !caller.getSecond().getParameters().containsKey(call.getSecond())) {
                    final FortranOperation dummyOperation = new FortranOperation(call.getSecond(), null, false);
                    project.getDefaultModule().getOperations().put(call.getSecond(), dummyOperation);
                    final Pair<FortranModule, FortranOperation> defaultCallee = new Pair<>(project.getDefaultModule(),
                            dummyOperation);
                    project.getCalls().add(new Pair<>(caller, defaultCallee));

                    try {
                        notFoundTable.addRow(caller.first.getFileName(), caller.first.getModuleName(), caller.second,
                                call.second);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add row to callee not found table: {}", e.getLocalizedMessage());
                    }
                    this.logger.info("Callee not found for {}::{} -> {}", caller.getFirst().getFileName(),
                            caller.getSecond(), call.getSecond());
                }
            } else {
                project.getCalls().add(new Pair<>(caller, callee));
            }
        });
    }

    private boolean isVariableReference(final FortranModule module, final String variableName) {
        return module.getVariables().keySet().contains(variableName.toLowerCase(Locale.getDefault()));
    }

    private boolean isCommonBlockVariable(final FortranModule module, final String variableName) {
        return module.getCommonBlocks().values().stream().anyMatch(
                block -> block.getVariables().keySet().contains(variableName.toLowerCase(Locale.getDefault())));
    }

    public OutputPort<Table> getNotFoundOutputPort() {
        return this.notFoundOutputPort;
    }

    private Pair<FortranModule, FortranOperation> findOperation(final Collection<FortranModule> modules,
            final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream()
                .filter(module -> module.getOperations().containsKey(signature)).findFirst();
        if (moduleOptional.isPresent()) {
            final FortranModule module = moduleOptional.get();
            return new Pair<>(module, module.getOperations().get(signature));
        } else {
            return null;
        }
    }

}
