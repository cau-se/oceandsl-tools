/**
 * 
 */
package org.oceandsl.log.rewriter;

import java.io.File;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

/**
 * @author reiner
 *
 */
public class LogRewriterSettings {

	@Parameter(names = { "-i", "--input" }, required = true, converter = FileConverter.class, description = "Input Kieker log directory")
	private File inputFile;

	@Parameter(names = { "-o", "--output" }, required = true, converter = FileConverter.class, description = "Output directory where to put the Kieker log directory")
	private File outputFile;
	
	@Parameter(names = { "-a", "--addrline" }, required = true, converter = FileConverter.class, description = "Location of the addrline tool")
	private File addrlineExecutable;

	@Parameter(names = { "-m", "--model" }, required = true, converter = FileConverter.class, description = "Location of the model executable")
	private File modelExecutable;
	
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
}
