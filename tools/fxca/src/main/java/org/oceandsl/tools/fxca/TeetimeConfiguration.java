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
package org.oceandsl.tools.fxca;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import teetime.framework.Configuration;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

import org.oceandsl.analysis.generic.stages.DirectoryProducer;
import org.oceandsl.analysis.generic.stages.DirectoryScannerStage;
import org.oceandsl.analysis.generic.stages.TableCsvSink;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.stages.ProcessModuleStructureStage;
import org.oceandsl.tools.fxca.stages.ReadDomStage;
import org.oceandsl.tools.fxca.stages.calls.CallEntry;
import org.oceandsl.tools.fxca.stages.calls.CreateCallTableStage;
import org.oceandsl.tools.fxca.stages.calls.CreateOperationTableStage;
import org.oceandsl.tools.fxca.stages.calls.FileOperationEntry;
import org.oceandsl.tools.fxca.stages.calls.NotFoundEntry;
import org.oceandsl.tools.fxca.stages.calls.ProcessOperationCallStage;
import org.oceandsl.tools.fxca.stages.dataflow.AggregateCommonBlocksStage;
import org.oceandsl.tools.fxca.stages.dataflow.AggregateDataflowStage;
import org.oceandsl.tools.fxca.stages.dataflow.CommonBlockArgumentDataflow;
import org.oceandsl.tools.fxca.stages.dataflow.ComputeDirectionalityOfParametersStage;
import org.oceandsl.tools.fxca.stages.dataflow.CreateCallerCalleeDataflowTableStage;
import org.oceandsl.tools.fxca.stages.dataflow.CreateCommonBlockDataflowTableStage;
import org.oceandsl.tools.fxca.stages.dataflow.CreateCommonBlocksTableStage;
import org.oceandsl.tools.fxca.stages.dataflow.DataFlowAnalysisStage;
import org.oceandsl.tools.fxca.stages.dataflow.DataflowEntry;
import org.oceandsl.tools.fxca.stages.dataflow.GlobalDataEntry;
import org.oceandsl.tools.fxca.utils.PatternUriProcessor;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class TeetimeConfiguration extends Configuration {

    private static final String OPERATION_DEFINITIONS = "operation-definitions.csv";
    private static final String CALL_TABLE = "calltable.csv";
    private static final String NOT_FOUND = "notfound.csv";

    private static final String DATAFLOW = "dataflow-cc.csv";
    private static final String COMMON_BLOCKS = "common-blocks.csv";
    private static final String DATAFLOW_COMMON_BLOCKS = "dataflow-cb.csv";

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
                this.createModules(settings.getLibraryFunctionsPaths()), settings.getDefaultComponent());
        final ProcessOperationCallStage processOperationCallStage = new ProcessOperationCallStage();

        final ComputeDirectionalityOfParametersStage computeDirectionalityOfParametersStage = new ComputeDirectionalityOfParametersStage();

        final Distributor<FortranProject> projectDistributor = new Distributor<>(new CopyByReferenceStrategy());

        final CreateCallTableStage callTableStage = new CreateCallTableStage();
        final CreateOperationTableStage operationTableStage = new CreateOperationTableStage();

        /** dataflow. */
        final DataFlowAnalysisStage dataFlowAnalysisStage = new DataFlowAnalysisStage();

        final AggregateCommonBlocksStage aggregateCommonBlocksStage = new AggregateCommonBlocksStage();
        final AggregateDataflowStage aggregateDataflowStage = new AggregateDataflowStage();

        /** tables. */
        final CreateCallerCalleeDataflowTableStage callerCalleeDataflowTableStage = new CreateCallerCalleeDataflowTableStage();
        final CreateCommonBlockDataflowTableStage commonBlockDataflowTableStage = new CreateCommonBlockDataflowTableStage();
        final CreateCommonBlocksTableStage commonBlocksTableStage = new CreateCommonBlocksTableStage();

        /** output stages. */
        final TableCsvSink<FileOperationEntry> operationTableSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.OPERATION_DEFINITIONS), true);
        final TableCsvSink<CallEntry> callTableSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.CALL_TABLE), true);
        final TableCsvSink<NotFoundEntry> notFoundSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.NOT_FOUND), true);

        final TableCsvSink<DataflowEntry> callerCalleeDataflowTableSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.DATAFLOW), true);
        final TableCsvSink<CommonBlockArgumentDataflow> commonBlockDataflowTableSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.DATAFLOW_COMMON_BLOCKS), true);
        final TableCsvSink<GlobalDataEntry> commonBlocksTableSink = new TableCsvSink<>(
                o -> settings.getOutputDirectoryPath().resolve(TeetimeConfiguration.COMMON_BLOCKS), true);

        /** connections. */
        this.connectPorts(producer.getOutputPort(), directoryScannerStage.getInputPort());
        this.connectPorts(directoryScannerStage.getOutputPort(), readDomStage.getInputPort());
        this.connectPorts(readDomStage.getOutputPort(), processModuleStructureStage.getInputPort());
        this.connectPorts(processModuleStructureStage.getOutputPort(), processOperationCallStage.getInputPort());

        this.connectPorts(processOperationCallStage.getOutputPort(), projectDistributor.getInputPort());
        this.connectPorts(processOperationCallStage.getNotFoundOutputPort(), notFoundSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), computeDirectionalityOfParametersStage.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), callTableStage.getInputPort());
        this.connectPorts(callTableStage.getOutputPort(), callTableSink.getInputPort());

        this.connectPorts(projectDistributor.getNewOutputPort(), operationTableStage.getInputPort());
        this.connectPorts(operationTableStage.getOutputPort(), operationTableSink.getInputPort());

        this.connectPorts(computeDirectionalityOfParametersStage.getOutputPort(), dataFlowAnalysisStage.getInputPort());

        this.connectPorts(dataFlowAnalysisStage.getCommonBlockOutputPort(), aggregateCommonBlocksStage.getInputPort());
        this.connectPorts(aggregateCommonBlocksStage.getOutputPort(), commonBlocksTableStage.getInputPort());

        this.connectPorts(dataFlowAnalysisStage.getDataflowOutputPort(), aggregateDataflowStage.getInputPort());
        this.connectPorts(aggregateDataflowStage.getCallerCalleeDataflowOutputPort(),
                callerCalleeDataflowTableStage.getInputPort());
        this.connectPorts(aggregateDataflowStage.getCommonBlockDataflowOutputPort(),
                commonBlockDataflowTableStage.getInputPort());

        this.connectPorts(commonBlocksTableStage.getOutputPort(), commonBlocksTableSink.getInputPort());
        this.connectPorts(callerCalleeDataflowTableStage.getOutputPort(), callerCalleeDataflowTableSink.getInputPort());
        this.connectPorts(commonBlockDataflowTableStage.getOutputPort(), commonBlockDataflowTableSink.getInputPort());
    }

    private List<FortranModule> createModules(final List<Path> libraryFunctionsPaths) {
        final List<FortranModule> modules = new ArrayList<>();
        libraryFunctionsPaths.forEach(path -> this.createModules(path, modules));

        return modules;
    }

    private void createModules(final Path path, final List<FortranModule> modules) {
        try (final BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] values = this.processLine(line);
                final Optional<FortranModule> moduleOptional = modules.stream()
                        .filter(m -> m.getModuleName().equals(values[0])).findFirst();
                FortranModule module;
                if (moduleOptional.isEmpty()) {
                    module = new FortranModule(values[0], values[0], false, null);
                    modules.add(module);
                } else {
                    module = moduleOptional.get();
                }
                this.createEntry(module, values[1], values[2], values[3]);
            }
        } catch (final IOException e) {
            System.err.println(">> cannot read " + path.toString()); // TODO improve with logger
        }
    }

    private String[] processLine(final String line) {
        final List<String> result = new ArrayList<>();
        int mode = 0;
        int marker = 0;
        for (int i = 0; i < line.length(); i++) {
            final char ch = line.charAt(i);
            if (mode == 0) {
                if (ch == '"') {
                    mode = 1;
                    marker = i + 1;
                } else {
                    mode = 2;
                    marker = i;
                }
            } else if (mode == 1) {
                if (ch == '"') {
                    result.add(line.substring(marker, i));
                    mode = 3;
                }
            } else if (mode == 2) {
                if (ch == ',') {
                    result.add(line.substring(marker, i));
                    mode = 0;
                }
            } else if (mode == 3) {
                if (ch == ',') {
                    mode = 0;
                }
            }
        }
        result.add(line.substring(marker, line.length()));
        return result.toArray(new String[result.size()]);
    }

    private void createEntry(final FortranModule module, final String name, final String sizeString,
            final String modeString) {
        final int size = Integer.parseInt(sizeString);
        FortranOperation operation;
        if ("fixed".equals(modeString)) {
            operation = new FortranOperation(name, null, true);
        } else if ("variable".equals(modeString)) {
            operation = new FortranOperation(name, null, true, true);
        } else {
            operation = new FortranOperation(name, null, true);
        }
        for (int i = 0; i < size; i++) {
            final FortranParameter parameter = new FortranParameter("p" + i, i);
            operation.getParameters().put(parameter.getName(), parameter);
        }
        module.getOperations().put(name, operation);
        operation.setModule(module);
    }
}
