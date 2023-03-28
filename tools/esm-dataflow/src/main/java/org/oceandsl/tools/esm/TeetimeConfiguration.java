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
package org.oceandsl.tools.esm;

import java.io.IOException;

import teetime.framework.Configuration;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

import org.oceandsl.analysis.generic.stages.DirectoryProducer;
import org.oceandsl.analysis.generic.stages.DirectoryScannerStage;
import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.esm.stages.CreateCommonBlocksTableStage;
import org.oceandsl.tools.esm.stages.CreateDataflowTableStage;
import org.oceandsl.tools.esm.stages.CreateFileContentsTableStage;
import org.oceandsl.tools.esm.stages.ProcessDataFlowAnalysisStage;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.stages.ProcessModuleStructureStage;
import org.oceandsl.tools.fxca.stages.ReadDomStage;
import org.oceandsl.tools.fxca.tools.PatternUriProcessor;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    private static final String SEARCH_PATTERN = "^file:\\/.*\\/([^.]*\\.[Ff][0-9]*)\\.xml$";
    private static final String REPLACEMENT_PATTERN = "$1";

    private static final String DATAFLOW = "dataflow.csv";
    private static final String FILE_CONENT = "file-content.csv";
    private static final String COMMON_BLOCKS = "common-blocks.csv";

    public TeetimeConfiguration(final Settings settings) throws IOException {
        final PatternUriProcessor uriProcessor = new PatternUriProcessor(TeetimeConfiguration.SEARCH_PATTERN,
                TeetimeConfiguration.REPLACEMENT_PATTERN);

        final DirectoryProducer producer = new DirectoryProducer(settings.getInputDirectoryPaths());
        final DirectoryScannerStage directoryScannerStage = new DirectoryScannerStage(true, o -> true,
                o -> o.getFileName().toString().endsWith(".xml"));

        final ReadDomStage readDomStage = new ReadDomStage();

        final ProcessModuleStructureStage processModuleStructureStage = new ProcessModuleStructureStage(uriProcessor);

        final ProcessDataFlowAnalysisStage dataFlowAnalysisStage = new ProcessDataFlowAnalysisStage(uriProcessor);

        final Distributor<FortranProject> projectDistributor = new Distributor<>(new CopyByReferenceStrategy());

        /** tables */
        final CreateDataflowTableStage dataflowTableStage = new CreateDataflowTableStage();
        final CreateFileContentsTableStage fileContentTableStage = new CreateFileContentsTableStage();
        final CreateCommonBlocksTableStage commonBlockTableStage = new CreateCommonBlocksTableStage();

        /** output stages */
        final TableCSVSink dataflowTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.DATAFLOW), true);
        final TableCSVSink fileContentTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.FILE_CONENT), true);
        final TableCSVSink commonBlocksTableSink = new TableCSVSink(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.COMMON_BLOCKS), true);

        /** connections */
        this.connectPorts(producer.getOutputPort(), directoryScannerStage.getInputPort());
        this.connectPorts(directoryScannerStage.getOutputPort(), readDomStage.getInputPort());
        this.connectPorts(readDomStage.getOutputPort(), processModuleStructureStage.getInputPort());

        this.connectPorts(processModuleStructureStage.getOutputPort(), dataFlowAnalysisStage.getInputPort());

        this.connectPorts(dataFlowAnalysisStage.getOutputPort(), projectDistributor.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), dataflowTableStage.getInputPort());
        this.connectPorts(dataflowTableStage.getOutputPort(), dataflowTableSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), fileContentTableStage.getInputPort());
        this.connectPorts(fileContentTableStage.getOutputPort(), fileContentTableSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), commonBlockTableStage.getInputPort());
        this.connectPorts(commonBlockTableStage.getOutputPort(), commonBlocksTableSink.getInputPort());
    }
}
