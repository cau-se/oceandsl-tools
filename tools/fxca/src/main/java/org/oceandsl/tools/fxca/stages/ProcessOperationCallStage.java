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
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Pair;

/**
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 * @since 1.3.0
 */
public class ProcessOperationCallStage extends AbstractFilter<FortranProject> {

    private final OutputPort<Table> notFoundOutputPort = this.createOutputPort(Table.class);
    private FortranModule defaultModule;
    private final String defaultModuleName;

    public ProcessOperationCallStage(final String defaultModuleName) {
        this.defaultModuleName = defaultModuleName;
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table notFoundTable = new Table("not-found", new StringValueHandler("caller-path"),
                new StringValueHandler("caller-module"), new StringValueHandler("caller-operation"),
                new StringValueHandler("callee-operation"));

        if (this.defaultModuleName != null) {
            this.defaultModule = new FortranModule(this.defaultModuleName, this.defaultModuleName, true, null);
            project.getModules().put(this.defaultModuleName, this.defaultModule);
        }

        project.getModules().values().stream().filter(module -> !module.getModuleName().equals(this.defaultModuleName))
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
            final List<Pair<String, String>> calls = NodeProcessingUtils.findSubroutineCalls(element);
            this.processCalls(project, module, calls, notFoundTable);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.logger.error("Processing subroutine calls in file {} failed: {}", element.getBaseURI(),
                    e.getLocalizedMessage());
        }
    }

    private void processFunctions(final FortranProject project, final FortranModule module, final Element element,
            final Table notFoundTable) {
        try {
            final List<Pair<String, String>> calls = NodeProcessingUtils.findFunctionCalls(element);
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
                if (!this.isCommonBlockVariable(module, call.getSecond())
                        && !this.isVariableReference(module, call.getSecond())) {
                    if (this.defaultModule != null) {
                        this.defaultModule.getOperations().put(call.getSecond(),
                                new FortranOperation(call.getSecond(), null));
                        final Pair<FortranModule, String> defaultCallee = new Pair<>(this.defaultModule,
                                call.getSecond());
                        module.getCalls().add(new Pair<>(caller, defaultCallee));
                    }
                    try {
                        notFoundTable.addRow(caller.first.getFileName(), caller.first.getModuleName(), caller.second,
                                call.second);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add row to callee not found table: {}", e.getLocalizedMessage());
                    }
                    this.logger.info("Callee not found for {}", call.getSecond());
                }
            } else {
                module.getCalls().add(new Pair<>(caller, callee));
            }
        });
    }

    private boolean isVariableReference(final FortranModule module, final String variableName) {
        return module.getVariables().contains(variableName.toLowerCase(Locale.getDefault()));
    }

    private boolean isCommonBlockVariable(final FortranModule module, final String variableName) {
        return module.getCommonBlocks().values().stream()
                .anyMatch(block -> block.getElements().contains(variableName.toLowerCase(Locale.getDefault())));
    }

    public OutputPort<Table> getNotFoundOutputPort() {
        return this.notFoundOutputPort;
    }

    private Pair<FortranModule, String> findOperation(final Collection<FortranModule> modules, final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream().filter(module -> module.getOperations().values()
                .stream().anyMatch(operation -> operation.getName().equals(signature))).findFirst();
        if (moduleOptional.isPresent()) {
            return new Pair<>(moduleOptional.get(), signature);
        } else {
            return null;
        }
    }

}
