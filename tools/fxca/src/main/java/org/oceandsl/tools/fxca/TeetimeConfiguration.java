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
package org.oceandsl.tools.fxca;

import teetime.framework.Configuration;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

import org.oceandsl.analysis.generic.stages.DirectoryProducer;
import org.oceandsl.analysis.generic.stages.DirectoryScannerStage;
import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.stages.CreateCallTableStage;
import org.oceandsl.tools.fxca.stages.CreateOperationTableStage;
import org.oceandsl.tools.fxca.stages.ProcessModuleStructureStage;
import org.oceandsl.tools.fxca.stages.ProcessOperationCallStage;
import org.oceandsl.tools.fxca.stages.ReadDomStage;
import org.oceandsl.tools.fxca.tools.PatternUriProcessor;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class TeetimeConfiguration extends Configuration {

    private static final String OPERATION_DEFINITIONS = "operation-definitions.csv";
    private static final String CALL_TABLE = "calltable.csv";
    private static final String NOT_FOUND = "notfound.csv";
    private static final String SEARCH_PATTERN = "^file:\\/.*\\/([^.]*\\.[Ff][0-9]*)\\.xml$";
    private static final String REPLACEMENT_PATTERN = "$1";

    public TeetimeConfiguration(final Settings settings) {
        final PatternUriProcessor uriProcessor = new PatternUriProcessor(TeetimeConfiguration.SEARCH_PATTERN,
                TeetimeConfiguration.REPLACEMENT_PATTERN);

        final DirectoryProducer producer = new DirectoryProducer(settings.getInputDirectoryPaths());
        final DirectoryScannerStage directoryScannerStage = new DirectoryScannerStage(true, o -> true,
                o -> o.getFileName().toString().endsWith(".xml"));

        /** computing stages. */
        final ReadDomStage readDomStage = new ReadDomStage();
        final ProcessModuleStructureStage processModuleStructureStage = new ProcessModuleStructureStage(uriProcessor,
                BuiltInFunctionsUtils.createOperations());
        final ProcessOperationCallStage processOperationCallStage = new ProcessOperationCallStage(
                settings.getDefaultComponent());

        final Distributor<FortranProject> projectDistributor = new Distributor<>(new CopyByReferenceStrategy());

        final CreateCallTableStage callTableStage = new CreateCallTableStage();
        final CreateOperationTableStage operationTableStage = new CreateOperationTableStage();

        /** output stages */
        final TableCSVSink operationTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.OPERATION_DEFINITIONS), true);
        final TableCSVSink callTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.CALL_TABLE), true);
        final TableCSVSink notFoundSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.NOT_FOUND), true);

        this.connectPorts(producer.getOutputPort(), directoryScannerStage.getInputPort());
        this.connectPorts(directoryScannerStage.getOutputPort(), readDomStage.getInputPort());
        this.connectPorts(readDomStage.getOutputPort(), processModuleStructureStage.getInputPort());
        this.connectPorts(processModuleStructureStage.getOutputPort(), processOperationCallStage.getInputPort());

        this.connectPorts(processOperationCallStage.getOutputPort(), projectDistributor.getInputPort());
        this.connectPorts(processOperationCallStage.getNotFoundOutputPort(), notFoundSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), callTableStage.getInputPort());
        this.connectPorts(callTableStage.getOutputPort(), callTableSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), operationTableStage.getInputPort());
        this.connectPorts(operationTableStage.getOutputPort(), operationTableSink.getInputPort());
    }
}
