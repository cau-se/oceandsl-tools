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

import org.slf4j.Logger;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.TypeModel;

import teetime.framework.Configuration;
import teetime.framework.OutputPort;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.stages.dataflow.AssemblyModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.CSVDataflowReaderStage;
import org.oceandsl.tools.sar.stages.dataflow.CountUniqueDataflowCallsStage;
import org.oceandsl.tools.sar.stages.dataflow.DataAccess;
import org.oceandsl.tools.sar.stages.dataflow.DeploymentModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.ExecutionModelDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.FileBasedCleanupInDataflowSignatureStage;
import org.oceandsl.tools.sar.stages.dataflow.MapBasedCleanupInDataflowSignatureStage;
import org.oceandsl.tools.sar.stages.dataflow.TypeModelDataflowAssemblerStage;

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

        OutputPort<DataAccess> readerDataflowPort;

        logger.info("Processing static call log");
        final CSVDataflowReaderStage readDataflowStage = new CSVDataflowReaderStage(settings.getDataflowInputFile(),
                settings.getDataflowSplitSymbol());

        readerDataflowPort = readDataflowStage.getOutputPort();

        if (settings.getComponentMapFiles() == null) {
            logger.info("File based component definition");
            final FileBasedCleanupInDataflowSignatureStage cleanupComponentDataflowSignatureStage = new FileBasedCleanupInDataflowSignatureStage(
                    settings.isCaseInsensitive());

            this.connectPorts(readerDataflowPort, cleanupComponentDataflowSignatureStage.getInputPort());

            readerDataflowPort = cleanupComponentDataflowSignatureStage.getOutputPort();
        } else {
            logger.info("Map based component definition");
            final MapBasedCleanupInDataflowSignatureStage cleanupComponentDataflowSignatureStage = new MapBasedCleanupInDataflowSignatureStage(
                    settings.getComponentMapFiles(), settings.getCallSplitSymbol(), settings.isCaseInsensitive());

            this.connectPorts(readerDataflowPort, cleanupComponentDataflowSignatureStage.getInputPort());
            readerDataflowPort = cleanupComponentDataflowSignatureStage.getOutputPort();
        }

        /** -- call based modeling -- */
        final TypeModelDataflowAssemblerStage typeModelDataflowAssemblerStage = new TypeModelDataflowAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(SourceModel.class),
                settings.getSourceLabel());
        final AssemblyModelDataflowAssemblerStage assemblyModelDataflowAssemblerStage = new AssemblyModelDataflowAssemblerStage(
                repository.getModel(TypeModel.class), repository.getModel(AssemblyModel.class),
                repository.getModel(SourceModel.class), settings.getSourceLabel());
        final DeploymentModelDataflowAssemblerStage deploymentModelDataflowAssemblerStage = new DeploymentModelDataflowAssemblerStage(
                repository.getModel(AssemblyModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), settings.getSourceLabel());
        final ExecutionModelDataflowAssemblerStage executionModelDataflowGenerationStage = new ExecutionModelDataflowAssemblerStage(
                repository.getModel(ExecutionModel.class), repository.getModel(DeploymentModel.class),
                repository.getModel(SourceModel.class), settings.getSourceLabel());
        final CountUniqueDataflowCallsStage countUniqueDataflowCalls = new CountUniqueDataflowCallsStage(
                repository.getModel(StatisticsModel.class), repository.getModel(ExecutionModel.class));

        /** connecting ports. */
        this.connectPorts(readerDataflowPort, typeModelDataflowAssemblerStage.getInputPort());
        this.connectPorts(typeModelDataflowAssemblerStage.getOutputPort(),
                assemblyModelDataflowAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelDataflowAssemblerStage.getOutputPort(),
                deploymentModelDataflowAssemblerStage.getInputPort());
        this.connectPorts(deploymentModelDataflowAssemblerStage.getOutputPort(),
                executionModelDataflowGenerationStage.getInputPort());
        this.connectPorts(executionModelDataflowGenerationStage.getOutputPort(),
                countUniqueDataflowCalls.getInputPort());
    }
}
