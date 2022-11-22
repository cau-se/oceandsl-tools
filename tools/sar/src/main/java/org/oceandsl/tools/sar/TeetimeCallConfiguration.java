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
package org.oceandsl.tools.sar; // NOPMD ExecessiveImports

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.oceandsl.analysis.architecture.stages.CountUniqueCallsStage;
import org.oceandsl.analysis.code.stages.CsvMakeLowerCaseStage;
import org.oceandsl.analysis.code.stages.CsvReaderStage;
import org.oceandsl.analysis.code.stages.OperationCallFixPathStage;
import org.oceandsl.analysis.code.stages.data.CallerCallee;
import org.oceandsl.analysis.code.stages.data.CallerCalleeFactory;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.stages.FileBasedCleanupComponentSignatureStage;
import org.oceandsl.tools.sar.stages.MapBasedCleanupComponentSignatureStage;
import org.oceandsl.tools.sar.stages.OperationAndCall4StaticDataStage;
import org.oceandsl.tools.sar.stages.StringFileWriterSink;
import org.slf4j.Logger;

import kieker.analysis.architecture.recovery.AssemblyModelAssemblerStage;
import kieker.analysis.architecture.recovery.CallEvent2OperationCallStage;
import kieker.analysis.architecture.recovery.DeploymentModelAssemblerStage;
import kieker.analysis.architecture.recovery.ExecutionModelAssembler;
import kieker.analysis.architecture.recovery.ExecutionModelAssemblerStage;
import kieker.analysis.architecture.recovery.TypeModelAssemblerStage;
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
import kieker.model.analysismodel.type.TypePackage;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeCallConfiguration extends Configuration {

    public TeetimeCallConfiguration(final Logger logger, final Settings settings, final ModelRepository repository)
            throws IOException, ValueConversionErrorException {
        super();
        OutputPort<CallerCallee> readerPort;

        logger.info("Processing static call log");

        readerPort = this.createReaderStage(settings.getOperationCallInputFile(), settings.getCallSplitSymbol());

        readerPort = this.createOperationCallFixPath(readerPort, settings.getFunctionNameFiles(),
                settings.getCallSplitSymbol(), settings.getMissingFunctionsFile());

        final CsvMakeLowerCaseStage mapperStage = new CsvMakeLowerCaseStage(settings.isCaseInsensitive());
        this.connectPorts(readerPort, mapperStage.getInputPort());
        readerPort = mapperStage.getOutputPort();

        readerPort = this.createComponentMapping(readerPort, settings, logger);

        final OperationAndCall4StaticDataStage operationAndCallStage = new OperationAndCall4StaticDataStage(
                settings.getHostname());
        /** -- call based modeling -- */
        final TypeModelAssemblerStage typeModelAssemblerStage = new TypeModelAssemblerStage(
                repository.getModel(TypePackage.Literals.TYPE_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel(),
                this.createComponentSignatureExtractor(settings), this.createOperationSignatureExtractor());
        final AssemblyModelAssemblerStage assemblyModelAssemblerStage = new AssemblyModelAssemblerStage(
                repository.getModel(TypePackage.Literals.TYPE_MODEL),
                repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());

        final CallEvent2OperationCallStage callEvent2OperationCallStage = new CallEvent2OperationCallStage(
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL));

        final ExecutionModelAssemblerStage executionModelGenerationStage = new ExecutionModelAssemblerStage(
                new ExecutionModelAssembler(repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));

        final CountUniqueCallsStage countUniqueCalls = new CountUniqueCallsStage(
                repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL),
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));

        /** connecting ports. */
        this.connectPorts(readerPort, operationAndCallStage.getInputPort());
        this.connectPorts(operationAndCallStage.getOperationOutputPort(), typeModelAssemblerStage.getInputPort());
        this.connectPorts(typeModelAssemblerStage.getOutputPort(), assemblyModelAssemblerStage.getInputPort());
        this.connectPorts(assemblyModelAssemblerStage.getOutputPort(), deploymentModelAssemblerStage.getInputPort());

        this.connectPorts(operationAndCallStage.getCallOutputPort(), callEvent2OperationCallStage.getInputPort());
        this.connectPorts(callEvent2OperationCallStage.getOutputPort(), executionModelGenerationStage.getInputPort());

        this.connectPorts(executionModelGenerationStage.getOutputPort(), countUniqueCalls.getInputPort());
    }

    private OutputPort<CallerCallee> createComponentMapping(final OutputPort<CallerCallee> readerPort,
            final Settings settings, final Logger logger) throws IOException, ValueConversionErrorException {
        if (settings.getComponentMapFiles() == null) {
            logger.info("File based component definition");
            final FileBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new FileBasedCleanupComponentSignatureStage(
                    settings.isCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());

            return cleanupComponentSignatureStage.getOutputPort();
        } else {
            logger.info("Map based component definition");
            final MapBasedCleanupComponentSignatureStage cleanupComponentSignatureStage = new MapBasedCleanupComponentSignatureStage(
                    settings.getComponentMapFiles(), settings.getMissingMappingFile(), settings.getCallSplitSymbol(),
                    settings.isCaseInsensitive());

            this.connectPorts(readerPort, cleanupComponentSignatureStage.getInputPort());
            return cleanupComponentSignatureStage.getOutputPort();
        }
    }

    private OutputPort<CallerCallee> createOperationCallFixPath(final OutputPort<CallerCallee> readerPort,
            final List<Path> functionNameFiles, final String namesSplitSymbol, final Path missingFunctionsFile)
            throws IOException {
        if ((functionNameFiles != null) && !functionNameFiles.isEmpty()) {
            final OperationCallFixPathStage fixPathStage = new OperationCallFixPathStage(functionNameFiles,
                    namesSplitSymbol);
            if (missingFunctionsFile != null) {
                final StringFileWriterSink missingFunctionsListSink = new StringFileWriterSink(missingFunctionsFile);
                this.connectPorts(fixPathStage.getMissingOperationOutputPort(),
                        missingFunctionsListSink.getInputPort());
            }
            this.connectPorts(readerPort, fixPathStage.getInputPort());
            return fixPathStage.getOutputPort();
        }
        return readerPort;
    }

    private OutputPort<CallerCallee> createReaderStage(final Path operationCallInputFile, final String callSplitSymbol)
            throws IOException {
        final CsvReaderStage<CallerCallee> readCsvStage = new CsvReaderStage<>(operationCallInputFile, callSplitSymbol,
                true, new CallerCalleeFactory());
        return readCsvStage.getOutputPort();
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
