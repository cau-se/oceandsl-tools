package org.oceandsl.tools.esm.stages;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.oceandsl.tools.esm.util.Output;


import teetime.framework.AbstractConsumerStage;

public class OutputStage extends AbstractConsumerStage<Output>{

	
	private final Path outputPath;
	public OutputStage(Path output) {
		this.outputPath=output;
	}
	@Override
	protected void execute(Output element) throws Exception {
		
		 try {
	            FileWriter writerdf = new FileWriter(outputPath.toString()+"/"+"dataflow");
	            FileWriter writerfc = new FileWriter(outputPath.toString()+"/"+"filecontent");
	            for (String line : element.getDataflow()) {
	                writerdf.write(line + System.lineSeparator());
	            }
	            writerdf.close();
	            for (String line : element.getFileContent()) {
	                writerfc.write(line + System.lineSeparator());
	            }
	            writerdf.close();
	            writerfc.close();
	            System.out.println("Successfully wrote lines to files.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file.");
	            e.printStackTrace();
	        }

	
		
		
	}
}