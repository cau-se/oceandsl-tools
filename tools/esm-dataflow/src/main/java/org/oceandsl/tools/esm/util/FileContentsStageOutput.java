package org.oceandsl.tools.esm.util;

import java.io.File;
import java.util.List;

public class FileContentsStageOutput {
	
	private FileContents fileContents;
	private List<File> files;
	
	
	public FileContentsStageOutput(FileContents fileContents, List<File> files) {
		this.fileContents = fileContents;
		this.files = files;
	}


	public FileContents getFileContents() {
		return fileContents;
	}


	public List<File> getFiles() {
		return files;
	}
	
	
	
}
