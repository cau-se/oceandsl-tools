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
package org.oceandsl.tools.sar;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.oceandsl.analysis.code.OperationStorage;
import org.oceandsl.analysis.code.stages.CsvReaderStage;
import org.oceandsl.analysis.code.stages.IStorageSignatureExtractor;
import org.oceandsl.analysis.code.stages.OperationStorageFactory;
import org.oceandsl.analysis.code.stages.OperationStorageFixPathStage;
import org.oceandsl.analysis.code.stages.OperationStorageMakeLowerCaseStage;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.analysis.generic.EModuleMode;
import org.oceandsl.tools.sar.signature.processor.AbstractSignatureProcessor;
import org.oceandsl.tools.sar.signature.processor.FileBasedSignatureProcessor;
import org.oceandsl.tools.sar.signature.processor.MapBasedSignatureProcessor;
import org.oceandsl.tools.sar.signature.processor.ModuleBasedSignatureProcessor;
import org.oceandsl.tools.sar.stages.calls.StringFileWriterSink;
import org.oceandsl.tools.sar.stages.dataflow.CleanupDataflowComponentSignatureStage;
import org.oceandsl.tools.sar.stages.dataflow.CountUniqueDataflowCallsStage;
import org.oceandsl.tools.sar.stages.dataflow.ElementAndDataflow4StaticDataStage;
import org.oceandsl.tools.sar.stages.dataflow.ExecutionModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.StorageAssemblyModelAssembler;
import org.oceandsl.tools.sar.stages.dataflow.StorageDeploymentModelAssembler;
import org.oceandsl.tools.sar.stages.dataflow.StorageEventModelAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.StorageTypeModelAssembler;
import org.slf4j.Logger;

import kieker.analysis.architecture.recovery.AssemblyModelAssembler;
import kieker.analysis.architecture.recovery.DeploymentModelAssembler;
import kieker.analysis.architecture.recovery.OperationEventModelAssemblerStage;
import kieker.analysis.architecture.recovery.TypeModelAssembler;
import kieker.analysis.architecture.recovery.signature.IComponentSignatureExtractor;
import kieker.analysis.architecture.recovery.signature.IOperationSignatureExtractor;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypePackage;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TeetimeDataflowConfiguration extends Configuration {
    public TeetimeDataflowConfiguration(final Logger logger, final Settings settings, final ModelRepository repository)
            throws IOException, ValueConversionErrorException {
        super();

        OutputPort<OperationStorage> readerPort;

        logger.info("Processing static call log");
        readerPort = this.createReaderStage(settings.getOperationCallInputFile(), settings.getCallSplitSymbol());

        readerPort = this.createOperationStorageFixPath(readerPort, settings.getFunctionNameFiles(),
                settings.getCallSplitSymbol(), settings.getMissingFunctionsFile());

        final OperationStorageMakeLowerCaseStage makeLowerCaseStage = new OperationStorageMakeLowerCaseStage(
                settings.isCaseInsensitive());

        final CleanupDataflowComponentSignatureStage cleanupComponentSignatureStage = new CleanupDataflowComponentSignatureStage(
                this.createProcessors(settings.getModuleModes(), settings, logger));

        final StringFileWriterSink errorMessageSink;
        if (settings.getMissingMappingsFile() != null) {
            errorMessageSink = new StringFileWriterSink(settings.getMissingMappingsFile());
        } else {
            errorMessageSink = null;
        }

        final ElementAndDataflow4StaticDataStage elementAndDataflow4StaticDataStage = new ElementAndDataflow4StaticDataStage(
                settings.getHostname());

        /** -- operation -- */
        final OperationEventModelAssemblerStage operationTypeModelAssemblerStage = new OperationEventModelAssemblerStage(
                new TypeModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel(),
                        this.createComponentSignatureExtractor(settings), this.createOperationSignatureExtractor()));
        final OperationEventModelAssemblerStage operationAssemblyModelAssemblerStage = new OperationEventModelAssemblerStage(
                new AssemblyModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));
        final OperationEventModelAssemblerStage operationDeploymentModelAssemblerStage = new OperationEventModelAssemblerStage(
                new DeploymentModelAssembler(repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));

        /** -- storage -- */
        final StorageEventModelAssemblerStage storageTypeModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageTypeModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel(),
                        this.createComponentSignatureExtractor(settings), this.createStorageSignatureExtractor()));
        final StorageEventModelAssemblerStage storageAssemblyModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageAssemblyModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));
        final StorageEventModelAssemblerStage storageDeploymentModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageDeploymentModelAssembler(repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));

        /** -- dataflow -- */
        final ExecutionModelDataflowAssemblerStage executionModelDataflowGenerationStage = new ExecutionModelDataflowAssemblerStage(
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL),
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final CountUniqueDataflowCallsStage countUniqueDataflowCalls = new CountUniqueDataflowCallsStage(
                repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL),
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));

        /** connecting ports. */
        this.connectPorts(readerPort, makeLowerCaseStage.getInputPort());
        this.connectPorts(makeLowerCaseStage.getOutputPort(), cleanupComponentSignatureStage.getInputPort());
        this.connectPorts(cleanupComponentSignatureStage.getOutputPort(),
                elementAndDataflow4StaticDataStage.getInputPort());
        if (errorMessageSink != null) {
            this.connectPorts(cleanupComponentSignatureStage.getErrorMessageOutputPort(),
                    errorMessageSink.getInputPort());
        }

        this.connectPorts(readerPort, elementAndDataflow4StaticDataStage.getInputPort());

        /** -- operation - */
        this.connectPorts(elementAndDataflow4StaticDataStage.getOperationOutputPort(),
                operationTypeModelAssemblerStage.getInputPort());
        this.connectPorts(operationTypeModelAssemblerStage.getOutputPort(),
                operationAssemblyModelAssemblerStage.getInputPort());
        this.connectPorts(operationAssemblyModelAssemblerStage.getOutputPort(),
                operationDeploymentModelAssemblerStage.getInputPort());

        /** -- storage - */
        this.connectPorts(elementAndDataflow4StaticDataStage.getStorageOutputPort(),
                storageTypeModelAssemblerStage.getInputPort());
        this.connectPorts(storageTypeModelAssemblerStage.getOutputPort(),
                storageAssemblyModelAssemblerStage.getInputPort());
        this.connectPorts(storageAssemblyModelAssemblerStage.getOutputPort(),
                storageDeploymentModelAssemblerStage.getInputPort());

        /** -- dataflow - */
        this.connectPorts(elementAndDataflow4StaticDataStage.getDataflowOutputPort(),
                executionModelDataflowGenerationStage.getInputPort());
        this.connectPorts(executionModelDataflowGenerationStage.getOutputPort(),
                countUniqueDataflowCalls.getInputPort());
    }

    private OutputPort<OperationStorage> createReaderStage(final Path inputFile, final String splitSymbol)
            throws IOException {
        final CsvReaderStage<OperationStorage> readCsvStage = new CsvReaderStage<>(inputFile, splitSymbol, true,
                new OperationStorageFactory());
        return readCsvStage.getOutputPort();
    }

    private OutputPort<OperationStorage> createOperationStorageFixPath(final OutputPort<OperationStorage> readerPort,
            final List<Path> functionNameFiles, final String namesSplitSymbol, final Path missingFunctionsFile)
            throws IOException {
        if ((functionNameFiles != null) && !functionNameFiles.isEmpty()) {
            final OperationStorageFixPathStage fixPathStage = new OperationStorageFixPathStage(functionNameFiles,
                    namesSplitSymbol);
            if (missingFunctionsFile != null) {
                final StringFileWriterSink missingFunctionsListSink = new StringFileWriterSink(missingFunctionsFile);
                this.connectPorts(fixPathStage.getMissingEntryOutputPort(), missingFunctionsListSink.getInputPort());
            }
            this.connectPorts(readerPort, fixPathStage.getInputPort());
            return fixPathStage.getOutputPort();
        }
        return readerPort;
    }

    private List<AbstractSignatureProcessor> createProcessors(final List<EModuleMode> modes, final Settings settings,
            final Logger logger) throws IOException {
        final List<AbstractSignatureProcessor> processors = new ArrayList<>();

        for (final EModuleMode mode : modes) {
            switch (mode) {
            case MAP_MODE:
                processors.add(this.createMapBasedProcessor(logger, settings));
                break;
            case MODULE_MODE:
                processors.add(this.createModuleBasedProcessor(logger, settings));
                break;
            case JAVA_CLASS_MODE:
                break;
            case PYTHON_CLASS_MODE:
                break;
            case FILE_MODE:
            default:
                processors.add(this.createFileBasedProcessor(logger, settings));
                break;
            }
        }

        return processors;
    }

    private AbstractSignatureProcessor createModuleBasedProcessor(final Logger logger, final Settings settings) {
        logger.info("Module based component definition");
        return new ModuleBasedSignatureProcessor(settings.isCaseInsensitive());
    }

    private AbstractSignatureProcessor createFileBasedProcessor(final Logger logger,
            final Settings parameterConfiguration) {
        logger.info("File based component definition");
        return new FileBasedSignatureProcessor(parameterConfiguration.isCaseInsensitive());
    }

    private AbstractSignatureProcessor createMapBasedProcessor(final Logger logger, final Settings settings)
            throws IOException {
        if (settings.getComponentMapFiles() != null) {
            logger.info("Map based component definition");
            return new MapBasedSignatureProcessor(settings.getComponentMapFiles(), settings.isCaseInsensitive(),
                    settings.getCallSplitSymbol());
        } else {
            logger.error("Missing map files for component identification.");
            return null;
        }
    }

    private IComponentSignatureExtractor createComponentSignatureExtractor(final Settings settings) {
        return new IComponentSignatureExtractor() {

            @Override
            public void extract(final ComponentType componentType) {
                String signature = componentType.getSignature();
                if (signature == null) {
                    signature = "-- none --";
                }
                final Path path = Paths.get(signature);
                final String name = path.getName(path.getNameCount() - 1).toString();
                final String rest = path.getParent() == null ? settings.getExperimentName()
                        : settings.getExperimentName() + "." + path.getParent().toString();
                componentType.setName(name);
                componentType.setPackage(rest);
            }
        };
    }

    private IStorageSignatureExtractor createStorageSignatureExtractor() {
        return new IStorageSignatureExtractor() {

            @Override
            public void extract(final StorageType storageType) {
                final String name = storageType.getName();
                storageType.setName(name);
            }

        };
    }

    private IOperationSignatureExtractor createOperationSignatureExtractor() {
        return new IOperationSignatureExtractor() {

            @Override
            public void extract(final OperationType operationType) {
                final String name = operationType.getSignature();
                operationType.setName(name);
                operationType.setReturnType("unknown");
            }

        };
    }
}
