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

import org.slf4j.Logger;

import kieker.analysis.signature.AbstractSignatureCleaner;
import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.analysis.signature.IOperationSignatureExtractor;
import kieker.analysis.signature.JavaComponentSignatureExtractor;
import kieker.analysis.signature.JavaOperationSignatureExtractor;
import kieker.analysis.stage.model.AssemblyModelAssemblerStage;
import kieker.analysis.stage.model.CallEvent2OperationCallStage;
import kieker.analysis.stage.model.DeploymentModelAssemblerStage;
import kieker.analysis.stage.model.ExecutionModelAssembler;
import kieker.analysis.stage.model.ExecutionModelAssemblerStage;
import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.stage.model.OperationAndCallGeneratorStage;
import kieker.analysis.stage.model.TypeModelAssemblerStage;
import kieker.analysis.statistics.CallStatisticsStage;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.TypeModel;
import kieker.tools.source.LogsReaderCompositeStage;

import teetime.framework.Configuration;
import teetime.framework.OutputPort;
import teetime.stage.InstanceOfFilter;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.analysis.generic.stages.CountEventsStage;
import org.oceandsl.analysis.generic.stages.RewriteBeforeAndAfterEventsStage;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        final OutputPort<? extends IMonitoringRecord> readerPort;

        logger.info("Processing Kieker log");
        final List<File> files = new ArrayList<>();
        files.add(parameterConfiguration.getInputFile().toFile());
        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(files, false, 8192);

        if (parameterConfiguration.getModelExecutable() != null) {
            final RewriteBeforeAndAfterEventsStage rewriteBeforeAndAfterEventsStage = new RewriteBeforeAndAfterEventsStage(
                    parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable(),
                    parameterConfiguration.isCaseInsensitive());
            this.connectPorts(reader.getOutputPort(), rewriteBeforeAndAfterEventsStage.getInputPort());
            readerPort = rewriteBeforeAndAfterEventsStage.getOutputPort();
        } else {
            readerPort = reader.getOutputPort();
        }

        final AbstractSignatureCleaner componentSignatureCleaner;
        final AbstractSignatureCleaner operationSignatureCleaner;

        if (parameterConfiguration.getComponentMapFiles() != null) {
            logger.info("Map based component definition");
            componentSignatureCleaner = new MapBasedSignatureCleaner(parameterConfiguration.getComponentMapFiles(),
                    parameterConfiguration.isCaseInsensitive(), ";");
            operationSignatureCleaner = new MapBasedOperationSignatureCleaner(
                    parameterConfiguration.isCaseInsensitive());
        } else {
            logger.info("File based component definition");
            componentSignatureCleaner = new FileBasedSignatureCleaner(parameterConfiguration.isCaseInsensitive());
            operationSignatureCleaner = new FileBasedOperationSignatureCleaner(
                    parameterConfiguration.isCaseInsensitive());
        }

        final InstanceOfFilter<IMonitoringRecord, IFlowRecord> instanceOfFilter = new InstanceOfFilter<>(
                IFlowRecord.class);

        final CountEventsStage<IFlowRecord> counter = new CountEventsStage<>(1000000);

        final IComponentSignatureExtractor componentSignatureExtractor = this.selectComponentSignaturExtractor(
                parameterConfiguration.getSignatureExtractor(), parameterConfiguration.getExperimentName());
        final IOperationSignatureExtractor operationSignatureExtractor = this
                .selectOperationSignaturExtractor(parameterConfiguration.getSignatureExtractor());

        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(SourceModel.class),
                parameterConfiguration.getSourceLabel(), componentSignatureExtractor, operationSignatureExtractor);
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(AssemblyModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                repository.getModel(AssemblyModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());

        final OperationAndCallGeneratorStage operationAndCallStage = new OperationAndCallGeneratorStage(true,
                componentSignatureCleaner, operationSignatureCleaner);
        final CallEvent2OperationCallStage callEvent2OperationCallStage = new CallEvent2OperationCallStage(
                repository.getModel(DeploymentModel.class));

        final ExecutionModelAssemblerStage executionModelGenerationStage = new ExecutionModelAssemblerStage(
                new ExecutionModelAssembler(repository.getModel(ExecutionModel.class),
                        repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel()));

        final CallStatisticsStage callStatisticsStage = new CallStatisticsStage(
                repository.getModel(StatisticsModel.class), repository.getModel(ExecutionModel.class));

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
