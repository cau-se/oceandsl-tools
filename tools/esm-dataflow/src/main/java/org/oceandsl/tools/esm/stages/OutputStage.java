package org.oceandsl.tools.esm.stages;


import java.io.File;
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
			 File filedf = new File(outputPath.toString()+"/"+"dataflow.txt");
			 filedf.createNewFile();
			 File filefc = new File(outputPath.toString()+"/"+"filecontent.txt");
			 filefc.createNewFile();
			 File filecb = new File(outputPath.toString()+"/"+"commonblocks.txt");
			 filecb.createNewFile();
	         FileWriter writerdf = new FileWriter(filedf);
	         FileWriter writerfc = new FileWriter(filefc);
	         FileWriter writercb = new FileWriter(filecb);
	            for (String line : element.getDataflow()) {
	                writerdf.write(line + System.lineSeparator());
	            }
	          //  writerdf.close();
	            for (String line : element.getFileContent()) {
	                writerfc.write(line + System.lineSeparator());
	            }
	            
	            for (String line : element.getCommonBlocks()) {
	                writercb.write(line + System.lineSeparator());
	            }
	            writerdf.close();
	            writerfc.close();
	            writercb.close();
	            
	            System.out.println("Successfully wrote lines to files.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file.");
	            e.printStackTrace();
	        }

	
		
		
	}
}