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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;
import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;
import org.oceandsl.architecture.model.stages.CountEventsStage;
import org.slf4j.Logger;

import kieker.analysis.signature.AbstractSignatureCleaner;
import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.analysis.signature.IOperationSignatureExtractor;
import kieker.analysis.signature.NullSignatureCleaner;
import kieker.analysis.stage.model.AssemblyModelAssemblerStage;
import kieker.analysis.stage.model.CallEvent2OperationCallStage;
import kieker.analysis.stage.model.DeploymentModelAssemblerStage;
import kieker.analysis.stage.model.ExecutionModelAssembler;
import kieker.analysis.stage.model.ExecutionModelAssemblerStage;
import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.stage.model.OperationAndCallGeneratorStage;
import kieker.analysis.stage.model.TypeModelAssemblerStage;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeModel;
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

    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        OutputPort<? extends IMonitoringRecord> readerPort;

        logger.info("Processing Kieker log");
        final List<File> files = new ArrayList<>();
        files.add(parameterConfiguration.getInputFile().toFile());
        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(files, false, 8192);

        if (parameterConfiguration.getModelExecutable() != null) {
            final RewriteBeforeAndAfterEventsStage rewriteBeforeAndAfterEventsStage = new RewriteBeforeAndAfterEventsStage(
                    parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable(),
                    parameterConfiguration.getCaseInsensitive());
            this.connectPorts(reader.getOutputPort(), rewriteBeforeAndAfterEventsStage.getInputPort());
            readerPort = rewriteBeforeAndAfterEventsStage.getOutputPort();
        } else {
            readerPort = reader.getOutputPort();
        }

        final AbstractSignatureCleaner componentSignatureCleaner;

        if (parameterConfiguration.getComponentMapFile() != null) {
            logger.info("Map based component definition");
            componentSignatureCleaner = new MapBasedSignatureCleaner(parameterConfiguration.getComponentMapFile(),
                    parameterConfiguration.getCaseInsensitive());
        } else {
            logger.info("File based component definition");
            componentSignatureCleaner = new FileBasedSignatureCleaner(parameterConfiguration.getCaseInsensitive());
        }

        final InstanceOfFilter<IMonitoringRecord, IFlowRecord> instanceOfFilter = new InstanceOfFilter<>(
                IFlowRecord.class);

        final CountEventsStage<IFlowRecord> counter = new CountEventsStage<>(1000000);

        final IComponentSignatureExtractor componentSignatureExtractor = new IComponentSignatureExtractor() {

            @Override
            public void extract(final ComponentType componentType) {
                String signature = componentType.getSignature();
                if (signature == null) {
                    signature = "-- none --";
                }
                final Path path = Paths.get(signature);
                final String name = path.getName(path.getNameCount() - 1).toString();
                final String rest = (path.getParent() != null)
                        ? parameterConfiguration.getExperimentName() + "." + path.getParent().toString()
                        : parameterConfiguration.getExperimentName();
                componentType.setName(name);
                componentType.setPackage(rest);
            }
        };
        final IOperationSignatureExtractor operationSignatureExtractor = new IOperationSignatureExtractor() {

            @Override
            public void extract(final OperationType operationType) {
                final String name = operationType.getSignature();
                operationType.setName(name);
                operationType.setReturnType("unknown");
            }

        };
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
                componentSignatureCleaner, new NullSignatureCleaner(parameterConfiguration.getCaseInsensitive()));
        final CallEvent2OperationCallStage callEvent2OperationCallStage = new CallEvent2OperationCallStage(
                repository.getModel(DeploymentModel.class));

        final ExecutionModelAssemblerStage executionModelGenerationStage = new ExecutionModelAssemblerStage(
                new ExecutionModelAssembler(repository.getModel(ExecutionModel.class),
                        repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel()));

        /** connecting ports. */
        this.connectPorts(readerPort, instanceOfFilter.getInputPort());
        this.connectPorts(instanceOfFilter.getMatchedOutputPort(), counter.getInputPort());
        this.connectPorts(counter.getOutputPort(),

                operationAndCallStage.getInputPort());
        this.connectPorts(operationAndCallStage.getOperationOutputPort(), typeModelAssemblerStage.getInputPort());
        this.connectPorts(typeModelAssemblerStage.getOutputPort(), assemblyModelAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelAssemblerStage.getOutputPort(), deploymentModelAssemblerStage.getInputPort());

        this.connectPorts(operationAndCallStage.getCallOutputPort(), callEvent2OperationCallStage.getInputPort());
        this.connectPorts(callEvent2OperationCallStage.getOutputPort(), executionModelGenerationStage.getInputPort());
    }
}
