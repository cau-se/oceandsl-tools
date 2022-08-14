package org.oceandsl.tools.sar.bsc.dataflow.stages;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.EDirection;
import teetime.framework.AbstractProducerStage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVBscDataflowReaderStage extends AbstractProducerStage<DataTransferObject> {
    private final BufferedReader reader;
    private final String splitSymbol;

    /**
     * Read a single CSV file.
     *
     * @param path
     *            file path
     * @param splitSymbol
     *            string containing the split symbol for the CSV file
     * @throws IOException
     *             when a stream could not be opened.
     */
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
            if (values.length == 4) {
                try {
                    this.outputPort.send(new DataTransferObject(values[0].trim(), values[1].trim(),
                            EDirection.getValue(values[2].trim()), values[3].trim(), ""));
                } catch (final InternalError e) {
                    this.logger.error("Line format error '{}', {}", line, e.getMessage());
                }
            } else if(values.length == 5){
                try {
                    this.outputPort.send(new DataTransferObject(values[0].trim(), values[1].trim(),
                            EDirection.getValue(values[2].trim()), values[3].trim(), values[4].trim()));
                } catch (final InternalError e) {
                    this.logger.error("Line format error '{}', {}", line, e.getMessage());
                }
            } else {
                this.logger.error("Line needs at least 4 values. :{}:", line);
            }
        }

        this.reader.close();
        this.workCompleted();
    }
}
