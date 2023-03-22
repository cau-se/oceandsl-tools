package org.oceandsl.tools.restructuring;

import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class Settings { // NOPMD data class

    @Parameter(names = { "-i",
            "--input" }, required = true, variableArity = true, converter = PathConverter.class, description = "Input architecture model directories")
    private List<Path> inputModelPaths;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output architecture model directory")
    private Path outputDirectory;

    @Parameter(names = { "-e", "--experiment" }, required = true, description = "Experiment name")
    private String experimentName;
    
    @Parameter(names = { "-s", "--strat" }, required = true, description = "Strat name")
    private String mappingStrat;

    public List<Path> getInputModelPaths() {
        return this.inputModelPaths;
    }

    public Path getOutputDirectory() {
        return this.outputDirectory;
    }

    public String getExperimentName() {
        return this.experimentName;
    }
    
    public String getMappingStrat() {
    	return this.mappingStrat;
    }

}