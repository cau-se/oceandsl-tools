package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import teetime.framework.AbstractProducerStage;

public class ReadStage extends AbstractProducerStage<List<File>> {
	
	private final Path rootPath;

	
	public ReadStage(Path path) {
		this.rootPath = path;
	}
	@Override
	protected void execute() throws Exception {
		File folder = new File(rootPath.toAbsolutePath().toString());
		List<File> files =Arrays.asList(folder.listFiles());
		this.outputPort.send(files);
	}
	
	

}
