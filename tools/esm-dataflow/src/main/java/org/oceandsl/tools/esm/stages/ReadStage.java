package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import teetime.framework.AbstractProducerStage;

public class ReadStage extends AbstractProducerStage<List<File>> {

    private final Path rootPath;

    public ReadStage(final Path list) {
        this.rootPath = list;
    }

    @Override
    protected void execute() throws Exception {

        final File folder = new File(this.rootPath.toAbsolutePath().toString());
        final List<File> files = Arrays.asList(folder.listFiles());
        // System.out.println("Files sent: "+ files.size());
        this.outputPort.send(files);
        this.workCompleted();

    }

}
