package org.oceandsl.tools.sar.bsc.dataflow;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.impl.DeploymentFactoryImpl;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.TypeModel;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.Settings;
import org.oceandsl.tools.sar.bsc.dataflow.model.ComponentLookup;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.bsc.dataflow.stages.*;
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

    public TeetimeBscDataflowConfiguration(final Logger logger,final Settings settings,final ModelRepository modelRepository) throws IOException, ValueConversionErrorException {
        super();
        this.logger = logger;
        logger.info("Successfully started a Teetime Config");

        logger.info("Starting to read component file.");
        final ComponentLookup componentLookup = writeLookUpObject(settings);
        if(componentLookup.getSizeOfTable()>0){
            logger.info("components successfully retrieved.");
        } else {
            logger.error("Unable to retrieve component content.");
        }

        final DeploymentModel deploymentModel = modelRepository.getModel(DeploymentModel.class);
        final DeploymentFactory deploymentFactory = new DeploymentFactoryImpl();
        final DeploymentContext deploymentContext = deploymentFactory.createDeploymentContext();
        String deploymentContextKey = "bsc-default-hostname";
        if(settings.getHostname()!= null){
            deploymentContextKey = settings.getHostname();
        }
        deploymentContext.setName(deploymentContextKey);
        deploymentModel.getContexts().put(deploymentContextKey,deploymentContext);

        /* -- ReaderSetup -- */
        OutputPort<DataTransferObject> readerDataflowPort;
        final CSVBscDataflowReaderStage readDataflowStage = new CSVBscDataflowReaderStage(settings.getBscDataflowInputFile(),";");
        readerDataflowPort = readDataflowStage.getOutputPort();

        /* -- call based modeling -- */
        final PreConfigurationStage preConfigurationStage = new PreConfigurationStage(componentLookup, modelRepository.getModel(SourceModel.class), settings.getSourceLabel());
        final TypeModelStage typeModelStage = new TypeModelStage(modelRepository.getModel(TypeModel.class), modelRepository.getModel(SourceModel.class),settings.getSourceLabel());
        final AssemblyModelStage assemblyModelStage = new AssemblyModelStage(modelRepository.getModel(TypeModel.class), modelRepository.getModel(AssemblyModel.class), modelRepository.getModel(SourceModel.class), settings.getSourceLabel());
        final DeploymentModelStage deploymentModelStage = new DeploymentModelStage(modelRepository.getModel(AssemblyModel.class), deploymentModel, modelRepository.getModel(SourceModel.class), settings.getSourceLabel());
        final ExecutionModelStage executionModelStage = new ExecutionModelStage(modelRepository.getModel(ExecutionModel.class), deploymentModel,modelRepository.getModel(SourceModel.class), settings.getSourceLabel());

        /* connecting ports. */
        logger.info("connecting ports");
        this.connectPorts(readerDataflowPort, preConfigurationStage.getInputPort());
        this.connectPorts(preConfigurationStage.getOutputPort(), typeModelStage.getInputPort());
        this.connectPorts(typeModelStage.getOutputPort(), assemblyModelStage.getInputPort());
        this.connectPorts(assemblyModelStage.getOutputPort(), deploymentModelStage.getInputPort());
        this.connectPorts(deploymentModelStage.getOutputPort(), executionModelStage.getInputPort());
    }

    public ComponentLookup writeLookUpObject(final Settings settings){
        try{
            //read component csv
            BufferedReader reader = Files.newBufferedReader(settings.getComponentBscInputFile());
            String line;
            final ComponentLookup componentLookup = new ComponentLookup();
            while((line= reader.readLine())!=null){
                final String[] values = line.split(";");
                if(values.length == 3){
                    componentLookup.putOperationsToComponent(values[0],values[1]);

                } else {
                    logger.error("Invalid line '{}'. 3 Values needed ", line);
                }
            }
            reader = Files.newBufferedReader(settings.getPackageBscInputFile());
            while((line= reader.readLine())!=null){
                final String[] values = line.split(";");
                if(values.length == 2){
                    componentLookup.setPackageToComponent(values[1],values[0]);

                } else {
                    logger.error("Invalid line '{}'. 2 Values needed ", line);
                }
            }
            reader.close();

            return componentLookup;
        }catch(IOException e){
            logger.error("Unable to read Path for component File.");
            return null;
        }
    }

}
