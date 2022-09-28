package org.oceandsl.tools.sar.bsc.dataflow.stages;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.EDirection;
import teetime.framework.AbstractProducerStage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Stage to read in csv data according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */public class CSVBscDataflowReaderStage extends AbstractProducerStage<DataTransferObject> {
    private final BufferedReader reader;
    private final String splitSymbol;


    public CSVBscDataflowReaderStage(final Path path, final String splitSymbol) throws IOException {
        this.reader = Files.newBufferedReader(path);
        this.splitSymbol = splitSymbol;
    }

    @Override
    protected void execute() throws Exception {
        // ignore the header

        String line;
        //String type = line;


        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(this.splitSymbol);
            /*
                Structure of CSV defined in ESM-Dataflow-Analysis Repository ReadMe
             */
            if (values.length == 4) {
                try {
                    this.outputPort.send(new DataTransferObject(values[0].trim(), values[1].trim(),
                            EDirection.getValue(values[2].trim()), values[3].trim()));
                } catch (final InternalError e) {
                    this.logger.error("Line format error '{}', {}", line, e.getMessage());
                }
            } else {
                this.logger.error("Line needs at 4 values. :{}:", line);
            }
        }

        this.reader.close();
        this.workCompleted();
    }
}
