package org.oceandsl.architecture.model;

import java.util.function.Function;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.util.FileExtension;

public class DedicatedFileNameMapper implements Function<IGraph, String> {

    private final String outputDirectory;
    private final FileExtension fileExtension;
    private final String outputFilename;

    /**
     * Create a simple file mapper.
     *
     * @param outputDirectory
     *            output directory path
     * @param outputFilename
     *            filename
     * @param fileExtension
     *            file extension for the graph
     */
    public DedicatedFileNameMapper(final String outputDirectory, final String outputFilename,
            final FileExtension fileExtension) {
        this.outputDirectory = outputDirectory;
        this.outputFilename = outputFilename;
        this.fileExtension = fileExtension;
    }

    @Override
    public String apply(final IGraph graph) {
        return this.outputDirectory + '/' + this.outputFilename + "-" + graph.getName() + '.' + this.fileExtension;
    }
}
