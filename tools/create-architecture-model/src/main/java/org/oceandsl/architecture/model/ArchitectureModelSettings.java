/**
 *
 */
package org.oceandsl.architecture.model;

import java.io.File;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

/**
 * @author reiner
 *
 */
public class ArchitectureModelSettings {

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = FileConverter.class, description = "Input Kieker log directory")
    private File inputFile;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = FileConverter.class, description = "Output directory where to put the graphics")
    private File outputFile;

    @Parameter(names = { "-a",
            "--addrline" }, required = true, converter = FileConverter.class, description = "Location of the addrline tool")
    private File addrlineExecutable;

    @Parameter(names = { "-e",
            "--executable" }, required = true, converter = FileConverter.class, description = "Location of the executable")
    private File modelExecutable;

    @Parameter(names = { "-p",
            "--prefix" }, required = false, description = "Path prefix to be removed from filenames in the visualization")
    private String prefix;

    public File getInputFile() {
        return this.inputFile;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public File getAddrlineExecutable() {
        return this.addrlineExecutable;
    }

    public File getModelExecutable() {
        return this.modelExecutable;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
