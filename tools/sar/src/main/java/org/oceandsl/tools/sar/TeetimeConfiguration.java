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

import org.oceandsl.analysis.CSVFunctionCallReaderStage;
import org.oceandsl.analysis.CallerCallee;
import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;
import org.oceandsl.architecture.model.stages.CSVFixPathStage;
import org.oceandsl.architecture.model.stages.CSVMapperStage;
import org.oceandsl.architecture.model.stages.CountUniqueCallsStage;
import org.oceandsl.tools.sar.stages.FileBasedCleanupComponentSignatureStage;
import org.oceandsl.tools.sar.stages.MapBasedCleanupComponentSignatureStage;
import org.oceandsl.tools.sar.stages.OperationAndCall4StaticDataStage;
import org.oceandsl.tools.sar.stages.dataflow.AssemblyModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.CSVDataflowReaderStage;
import org.oceandsl.tools.sar.stages.dataflow.DeploymentModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.TypeModelDataflowAssemblerStage;
import org.slf4j.Logger;

import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.analysis.signature.IOperationSignatureExtractor;
import kieker.analysis.stage.model.AssemblyModelAssemblerStage;
import kieker.analysis.stage.model.CallEvent2OperationCallStage;
import kieker.analysis.stage.model.DeploymentModelAssemblerStage;
import kieker.analysis.stage.model.ExecutionModelAssembler;
import kieker.analysis.stage.model.ExecutionModelAssemblerStage;
import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.stage.model.TypeModelAssemblerStage;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeModel;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {
    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException, ValueConversionErrorException {

        OutputPort<CallerCallee> readerPort;

        logger.info("Processing static call log");
        final CSVFunctionCallReaderStage readCsvStage = new CSVFunctionCallReaderStage(
                parameterConfiguration.getOperationCallInputFile());

        final CSVDataflowReaderStage readDataflowStage = new CSVDataflowReaderStage(
                parameterConfiguration.getDataflowInputFile());

        readerPort = readCsvStage.getOutputPort();
        if ((parameterConfiguration.getFunctionNameFiles() != null)
                && !parameterConfiguration.getFunctionNameFiles().isEmpty()) {
            final CSVFixPathStage fixPathStage = new CSVFixPathStage(parameterConfiguration.getFunctionNameFiles());
            this.connectPorts(readerPort, fixPathStage.getInputPort());
            readerPort = fixPathStage.getOutputPort();
        }
        final CSVMapperStage mapperStage = new CSVMapperStage(parameterConfiguration.getCaseInsensitive());
        this.connectPorts(readerPort, mapperStage.getInputPort());
        readerPort = mapperStage.getOutputPort();

        if (parameterConfiguration.getComponentMapFile() != null) {
            logger.info("Map based component definition");
            final MapBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new MapBasedCleanupComponentSignatureStage(
                    parameterConfiguration.getComponentMapFile(), parameterConfiguration.getCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());
            readerPort = cleanupComponentSignatureStage.getOutputPort();
        } else {
            logger.info("File based component definition");
            final FileBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new FileBasedCleanupComponentSignatureStage(
                    parameterConfiguration.getCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());
            readerPort = cleanupComponentSignatureStage.getOutputPort();
        }

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

        final OperationAndCall4StaticDataStage operationAndCallStage = new OperationAndCall4StaticDataStage(
                parameterConfiguration.getHostname());
        /** -- call based modeling -- */
        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(SourceModel.class),
                parameterConfiguration.getSourceLabel(), componentSignatureExtractor, operationSignatureExtractor);
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(AssemblyModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                repository.getModel(AssemblyModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());

        final CallEvent2OperationCallStage callEvent2OperationCallStage = new CallEvent2OperationCallStage(
                repository.getModel(DeploymentModel.class));

        final ExecutionModelAssemblerStage executionModelGenerationStage = new ExecutionModelAssemblerStage(
                new ExecutionModelAssembler(repository.getModel(ExecutionModel.class),
                        repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel()));

        final CountUniqueCallsStage countUniqueCalls = new CountUniqueCallsStage(
                repository.getModel(StatisticsModel.class), repository.getModel(ExecutionModel.class));

        final TypeModelDataflowAssemblerStage typeModelDataflowAssemblerStage = new TypeModelDataflowAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(SourceModel.class),
                parameterConfiguration.getSourceLabel());
        final AssemblyModelDataflowAssemblerStage assemblyModelDataflowAssemblerStage = new AssemblyModelDataflowAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(AssemblyModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());
        final DeploymentModelDataflowAssemblerStage deploymentModelDataflowAssemblerStage = new DeploymentModelDataflowAssemblerStage(
                repository.getModel(AssemblyModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), parameterConfiguration.getSourceLabel());

        /** connecting ports. */
        this.connectPorts(readerPort, operationAndCallStage.getInputPort());
        this.connectPorts(operationAndCallStage.getOperationOutputPort(), typeModelAssemblerStage.getInputPort());
        this.connectPorts(typeModelAssemblerStage.getOutputPort(), assemblyModelAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelAssemblerStage.getOutputPort(), deploymentModelAssemblerStage.getInputPort());

        this.connectPorts(readDataflowStage.getOutputPort(), typeModelDataflowAssemblerStage.getInputPort());
        this.connectPorts(typeModelDataflowAssemblerStage.getOutputPort(),
                assemblyModelDataflowAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelDataflowAssemblerStage.getOutputPort(),
                deploymentModelDataflowAssemblerStage.getInputPort());

        this.connectPorts(operationAndCallStage.getCallOutputPort(), callEvent2OperationCallStage.getInputPort());
        this.connectPorts(callEvent2OperationCallStage.getOutputPort(), executionModelGenerationStage.getInputPort());

        this.connectPorts(executionModelGenerationStage.getOutputPort(), countUniqueCalls.getInputPort());
    }
}
