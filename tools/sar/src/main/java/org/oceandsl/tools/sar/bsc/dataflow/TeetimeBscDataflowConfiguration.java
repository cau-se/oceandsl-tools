package org.oceandsl.tools.sar.bsc.dataflow;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.Settings;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.bsc.dataflow.model.FunctionLookup;
import org.oceandsl.tools.sar.bsc.dataflow.stages.CSVBscDataflowReaderStage;
import org.oceandsl.tools.sar.bsc.dataflow.stages.PreConfigurationStage;
import org.slf4j.Logger;
import teetime.framework.Configuration;
import teetime.framework.OutputPort;


import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class TeetimeBscDataflowConfiguration extends Configuration {

    Logger logger;

    public TeetimeBscDataflowConfiguration(Logger logger, Settings settings, ModelRepository modelRepository) throws IOException, ValueConversionErrorException {
        super();
        this.logger = logger;
        logger.info("Successfully started a Teetime Config");

        logger.info("Starting to read component file.");
        FunctionLookup functionLookup = writeLookUpFile(settings);
        if(functionLookup.getSizeOfTable()>0){
            logger.info("components successfully retrieved.");
        } else {
            logger.error("Unable to retrieve component content.");
        }

        /* -- ReaderSetup -- */
        OutputPort<DataTransferObject> readerDataflowPort;
        final CSVBscDataflowReaderStage readDataflowStage = new CSVBscDataflowReaderStage(settings.getBscDataflowInputFile(),";");
        readerDataflowPort = readDataflowStage.getOutputPort();

        /* -- call based modeling -- */
        final PreConfigurationStage preConfigurationStage = new PreConfigurationStage(functionLookup, modelRepository.getModel(SourceModel.class), settings.getSourceLabel());

        /* connecting ports. */
        logger.info("connecting ports");
        this.connectPorts(readerDataflowPort, preConfigurationStage.getInputPort());

    }

    public FunctionLookup writeLookUpFile(Settings settings){
        try{
            //read component csv
            BufferedReader reader = Files.newBufferedReader(settings.getComponentBscInputFile());
            String line;
            FunctionLookup functionLookup = new FunctionLookup();
            while((line= reader.readLine())!=null){
                final String[] values = line.split(";");
                if(values.length > 1){
                    functionLookup.putContentToComponent(values[0],values[1]);
                }
            }
            reader.close();

            //write properties file
            File componentsFile = new File("components.properties");
            BufferedWriter writer = new BufferedWriter(new FileWriter(componentsFile.getName()));
            List<String> components = functionLookup.getComponents();
            for(String component: components){
                String out = component + "=";
                List<String> contents = functionLookup.getContentOfComponent(component);
                for(String content: contents){
                    out += content + ";";
                }
                out+= "\n";
                writer.write(out);
            }

            writer.close();
            logger.info("Components located at " + componentsFile.getAbsolutePath());
            return functionLookup;
        }catch(IOException e){
            logger.error("Unable to read Path for component File.");
            return null;
        }
    }

}
