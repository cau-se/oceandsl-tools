package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import teetime.framework.AbstractConsumerStage;

import org.oceandsl.tools.esm.util.Output;

public class OutputStage extends AbstractConsumerStage<Output> {

    private final Path outputPath;

    public OutputStage(final Path output) {
        this.outputPath = output;
    }

    @Override
    protected void execute(final Output element) throws Exception {

        try {
            final File filedf = new File(this.outputPath.toString() + "/" + "dataflow.txt");
            filedf.createNewFile();
            final File filefc = new File(this.outputPath.toString() + "/" + "filecontent.txt");
            filefc.createNewFile();
            final FileWriter writerdf = new FileWriter(filedf);
            final FileWriter writerfc = new FileWriter(filefc);
            for (final String line : element.getDataflow()) {
                writerdf.write(line + System.lineSeparator());
            }
            // writerdf.close();
            for (final String line : element.getFileContent()) {
                writerfc.write(line + System.lineSeparator());
            }
            writerdf.close();
            writerfc.close();

            System.out.println("Successfully wrote lines to files.");
        } catch (final IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }
}