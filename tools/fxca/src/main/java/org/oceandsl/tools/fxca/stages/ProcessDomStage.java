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

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;

/**
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 */
public class ProcessDomStage extends AbstractTransformation<Document, FortranProject> {

    private final FortranProject project;

    public ProcessDomStage() {
        this.project = new FortranProject();
    }

    @Override
    protected void execute(final Document document) throws Exception {
        this.addModule(document);
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.project);
        super.onTerminating();
    }

    public void addModule(final Document document) throws ParserConfigurationException, SAXException, IOException {
        final FortranModule module = new FortranModule(document);
        this.project.getModules().put(module.getModuleName(), module);
    }
}
