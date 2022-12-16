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
package org.oceandsl.tools.sar.bsc.dataflow;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.deployment.impl.DeploymentFactoryImpl;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.Settings;
import org.oceandsl.tools.sar.bsc.dataflow.model.ComponentLookup;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.bsc.dataflow.stages.*;
import org.oceandsl.tools.sar.stages.dataflow.CSVDataflowReaderStage;
import org.slf4j.Logger;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Configuration File for Bachelor Thesis Model Generation
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class TeetimeBscDataflowConfiguration extends Configuration {

    private final Logger logger;

    public TeetimeBscDataflowConfiguration(final Logger logger, final Settings settings,
            final ModelRepository modelRepository) throws IOException, ValueConversionErrorException {
        super();
        this.logger = logger;
        logger.info("Successfully started a Teetime Config");

        logger.info("Starting to read component file.");
        final ComponentLookup componentLookup = writeLookUpObject(settings);
        if (componentLookup.getSizeOfTable() > 0) {
            logger.info("components successfully retrieved.");
        } else {
            logger.error("Unable to retrieve component content.");
        }

        final DeploymentModel deploymentModel = modelRepository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);
        final DeploymentFactory deploymentFactory = new DeploymentFactoryImpl();
        final DeploymentContext deploymentContext = deploymentFactory.createDeploymentContext();
        String deploymentContextKey = "bsc-default-hostname";
        if (settings.getHostname() != null) {
            deploymentContextKey = settings.getHostname();
        }
        deploymentContext.setName(deploymentContextKey);
        deploymentModel.getContexts().put(deploymentContextKey, deploymentContext);

        /* -- ReaderSetup -- */
        OutputPort<DataTransfer> readerDataflowPort;
        final CSVDataflowReaderStage readDataflowStage = new CSVDataflowReaderStage(settings.getBscDataflowInputFile(),
                ";");
        readerDataflowPort = readDataflowStage.getOutputPort();

        /* -- Modeling Setup -- */
        final PreConfigurationStage preConfigurationStage = new PreConfigurationStage(componentLookup,
                modelRepository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final TypeModelStage typeModelStage = new TypeModelStage(
                modelRepository.getModel(TypePackage.Literals.TYPE_MODEL),
                modelRepository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final AssemblyModelStage assemblyModelStage = new AssemblyModelStage(
                modelRepository.getModel(TypePackage.Literals.TYPE_MODEL),
                modelRepository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                modelRepository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final DeploymentModelStage deploymentModelStage = new DeploymentModelStage(
                modelRepository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL), deploymentModel,
                modelRepository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());
        final ExecutionModelStage executionModelStage = new ExecutionModelStage(
                modelRepository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL), deploymentModel,
                modelRepository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel());

        /* connecting ports. */
        this.connectPorts(readerDataflowPort, preConfigurationStage.getInputPort());
        this.connectPorts(preConfigurationStage.getOutputPort(), typeModelStage.getInputPort());
        this.connectPorts(typeModelStage.getOutputPort(), assemblyModelStage.getInputPort());
        this.connectPorts(assemblyModelStage.getOutputPort(), deploymentModelStage.getInputPort());
        this.connectPorts(deploymentModelStage.getOutputPort(), executionModelStage.getInputPort());
    }

    public ComponentLookup writeLookUpObject(final Settings settings) {
        try {
            // read component csv
            BufferedReader reader = Files.newBufferedReader(settings.getComponentBscInputFile());
            String line;
            final ComponentLookup componentLookup = new ComponentLookup();
            while ((line = reader.readLine()) != null) {
                final String[] values = line.split(";");
                if (values.length == 3) {
                    componentLookup.putOperationsToComponent(values[0], values[1]);

                } else {
                    logger.error("Invalid line '{}'. 3 Values needed ", line);
                }
            }
            // read package map file csv
            reader = Files.newBufferedReader(settings.getPackageBscInputFile());
            while ((line = reader.readLine()) != null) {
                final String[] values = line.split(";");
                if (values.length == 2) {
                    componentLookup.setPackageToComponent(values[1], values[0]);

                } else {
                    logger.error("Invalid line '{}'. 2 Values needed ", line);
                }
            }
            reader.close();

            return componentLookup;
        } catch (IOException e) {
            logger.error("Unable to read Path for component File.");
            return null;
        }
    }

}
