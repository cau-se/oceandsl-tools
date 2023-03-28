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
            final File filedf = new File(outputPath.toString() + "/" + "dataflow.csv");
            filedf.createNewFile();
            final File filefc = new File(outputPath.toString() + "/" + "filecontent.csv");
            filefc.createNewFile();
            final File filecb = new File(outputPath.toString() + "/" + "commonblocks.csv");
            filecb.createNewFile();
            final FileWriter writerdf = new FileWriter(filedf);
            final FileWriter writerfc = new FileWriter(filefc);
            final FileWriter writercb = new FileWriter(filecb);
            for (final String line : element.getDataflow()) {
                writerdf.write(line + System.lineSeparator());
            }
            // writerdf.close();
            for (final String line : element.getFileContent()) {
                writerfc.write(line + System.lineSeparator());
            }

            for (final String line : element.getCommonBlocks()) {
                writercb.write(line + System.lineSeparator());
            }
            writerdf.close();
            writerfc.close();
            writercb.close();

            System.out.println("Successfully wrote lines to files.");
        } catch (final IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }
}
