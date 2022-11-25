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
package org.oceandsl.tools.dar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.analysis.generic.EModuleMode;
import org.oceandsl.analysis.generic.stages.CountEventsStage;
import org.oceandsl.analysis.generic.stages.RewriteBeforeAndAfterEventsStage;
import org.oceandsl.tools.dar.extractors.ELFComponentSignatureExtractor;
import org.oceandsl.tools.dar.extractors.ELFOperationSignatureExtractor;
import org.oceandsl.tools.dar.extractors.PythonComponentSignatureExtractor;
import org.oceandsl.tools.dar.extractors.PythonOperationSignatureExtractor;
import org.oceandsl.tools.dar.signature.processor.FileBasedSignatureProcessor;
import org.oceandsl.tools.dar.signature.processor.MapBasedSignatureProcessor;
import org.oceandsl.tools.dar.signature.processor.ModuleSignatureProcessor;
import org.oceandsl.tools.dar.stages.OperationAndCallGeneratorStage;
import org.slf4j.Logger;

import kieker.analysis.architecture.recovery.AssemblyModelAssemblerStage;
import kieker.analysis.architecture.recovery.CallEvent2OperationCallStage;
import kieker.analysis.architecture.recovery.DeploymentModelAssemblerStage;
import kieker.analysis.architecture.recovery.ExecutionModelAssembler;
import kieker.analysis.architecture.recovery.ExecutionModelAssemblerStage;
import kieker.analysis.architecture.recovery.TypeModelAssemblerStage;
import kieker.analysis.architecture.recovery.signature.AbstractSignatureProcessor;
import kieker.analysis.architecture.recovery.signature.IComponentSignatureExtractor;
import kieker.analysis.architecture.recovery.signature.IOperationSignatureExtractor;
import kieker.analysis.architecture.recovery.signature.JavaComponentSignatureExtractor;
import kieker.analysis.architecture.recovery.signature.JavaOperationSignatureExtractor;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.statistics.CallStatisticsStage;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.TypePackage;
import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;
import teetime.stage.InstanceOfFilter;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Logger logger, final Settings settings, final ModelRepository repository)
            throws IOException, ValueConversionErrorException {

        final OutputPort<? extends IMonitoringRecord> readerPort;

        logger.info("Processing Kieker log");
        final List<File> files = new ArrayList<>();
        files.add(settings.getInputFile().toFile());
        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(files, false, 8192);

        if (settings.getModelExecutable() != null) {
            final RewriteBeforeAndAfterEventsStage rewriteBeforeAndAfterEventsStage = new RewriteBeforeAndAfterEventsStage(
                    settings.getAddrlineExecutable(), settings.getModelExecutable(), settings.isCaseInsensitive());
            this.connectPorts(reader.getOutputPort(), rewriteBeforeAndAfterEventsStage.getInputPort());
            readerPort = rewriteBeforeAndAfterEventsStage.getOutputPort();
        } else {
            readerPort = reader.getOutputPort();
        }

        final InstanceOfFilter<IMonitoringRecord, IFlowRecord> instanceOfFilter = new InstanceOfFilter<>(
                IFlowRecord.class);

        final CountEventsStage<IFlowRecord> counter = new CountEventsStage<>(1000000);

        final OperationAndCallGeneratorStage operationAndCallStage = new OperationAndCallGeneratorStage(true,
                this.createProcessors(settings.getModuleModes(), settings, logger),
                !settings.isKeepMetaDataOnCompletedTraces());
        final CallEvent2OperationCallStage callEvent2OperationCallStage = new CallEvent2OperationCallStage(
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL));

        final IComponentSignatureExtractor componentSignatureExtractor = this
                .selectComponentSignaturExtractor(settings.getSignatureExtractor(), settings.getExperimentName());
        final IOperationSignatureExtractor operationSignatureExtractor = this
                .selectOperationSignaturExtractor(settings.getSignatureExtractor());

        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(
                repository.getModel(TypePackage.Literals.TYPE_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel(),
                componentSignatureExtractor, operationSignatureExtractor);
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(
                repository.getModel(TypePackage.Literals.TYPE_MODEL),
                repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());

        final ExecutionModelAssemblerStage executionModelGenerationStage = new ExecutionModelAssemblerStage(
                new ExecutionModelAssembler(repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));

        final CallStatisticsStage callStatisticsStage = new CallStatisticsStage(
                repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL),
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));

        /** connecting ports. */
        this.connectPorts(readerPort, instanceOfFilter.getInputPort());
        this.connectPorts(instanceOfFilter.getMatchedOutputPort(), counter.getInputPort());
        this.connectPorts(counter.getOutputPort(), operationAndCallStage.getInputPort());

        this.connectPorts(operationAndCallStage.getOperationOutputPort(), typeModelAssemblerStage.getInputPort());
        this.connectPorts(typeModelAssemblerStage.getOutputPort(), assemblyModelAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelAssemblerStage.getOutputPort(), deploymentModelAssemblerStage.getInputPort());

        this.connectPorts(operationAndCallStage.getCallOutputPort(), callEvent2OperationCallStage.getInputPort());
        this.connectPorts(callEvent2OperationCallStage.getOutputPort(), executionModelGenerationStage.getInputPort());
        this.connectPorts(executionModelGenerationStage.getOutputPort(), callStatisticsStage.getInputPort());
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
        return new ModuleSignatureProcessor(settings.isCaseInsensitive());
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
                    settings.getMapFileColumnSeparator());
        } else {
            logger.error("Missing map files for component identification.");
            return null;
        }
    }

    private IComponentSignatureExtractor selectComponentSignaturExtractor(final ESignatureExtractor signatureExtractor,
            final String experimentName) {
        switch (signatureExtractor) {
        case ELF:
            return new ELFComponentSignatureExtractor(experimentName);
        case PYTHON:
            return new PythonComponentSignatureExtractor();
        case JAVA:
            return new JavaComponentSignatureExtractor();
        default:
            return null;
        }
    }

    private IOperationSignatureExtractor selectOperationSignaturExtractor(
            final ESignatureExtractor signatureExtractor) {
        switch (signatureExtractor) {
        case ELF:
            return new ELFOperationSignatureExtractor();
        case PYTHON:
            return new PythonOperationSignatureExtractor();
        case JAVA:
            return new JavaOperationSignatureExtractor();
        default:
            return null;
        }
    }

}
