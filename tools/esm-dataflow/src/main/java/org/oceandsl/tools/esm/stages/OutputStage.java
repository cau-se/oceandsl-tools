package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.oceandsl.tools.esm.util.Output;

import kieker.analysis.architecture.repository.ModelRepository;
import teetime.framework.AbstractConsumerStage;

public class OutputStage extends AbstractConsumerStage<Output>{

	
	private final Path outputPath;
	public OutputStage(Path output) {
		this.outputPath=output;
	}
	@Override
	protected void execute(Output element) throws Exception {
		FileUtils.writeLines(new File("dataflow.txt"),  element.getDataflow());
		FileUtils.writeLines(new File("filecontent.txt"),  element.getFileContent());
		
		
		
	}
}