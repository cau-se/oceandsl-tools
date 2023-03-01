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

import org.oceandsl.analysis.generic.stages.DirectoryProducer;
import org.oceandsl.analysis.generic.stages.DirectoryScannerStage;
import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.fxca.stages.ProcessDomStage;
import org.oceandsl.tools.fxca.stages.ReadDomStage;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class TeetimeConfiguration extends Configuration {

    private static final String OPERATION_DEFINITIONS = "operation-definitions.csv";
    private static final String CALL_TABLE = "calltable.csv";
    private static final String NOT_FOUND = "notfound.csv";

    public TeetimeConfiguration(final Settings settings) {
        final DirectoryProducer producer = new DirectoryProducer(settings.getInputDirectoryPaths());
        final DirectoryScannerStage directoryScannerStage = new DirectoryScannerStage(true, o -> true,
                o -> o.endsWith(".xml"));

        /** computing stages. */
        final ReadDomStage readDomStage = new ReadDomStage();
        final ProcessDomStage processDomStage = new ProcessDomStage();

        /** output stages */
        final TableCSVSink operationDefinitionsSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.OPERATION_DEFINITIONS), true);
        final TableCSVSink callTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.CALL_TABLE), true);
        final TableCSVSink notfoundSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.NOT_FOUND), true);

        this.connectPorts(producer.getOutputPort(), directoryScannerStage.getInputPort());
        this.connectPorts(directoryScannerStage.getOutputPort(), readDomStage.getInputPort());
    }
}
