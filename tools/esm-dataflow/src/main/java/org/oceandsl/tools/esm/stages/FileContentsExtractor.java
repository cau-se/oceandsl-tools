package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.oceandsl.tools.esm.util.FileContents;
import org.oceandsl.tools.esm.util.FileContentsEntry;
import org.oceandsl.tools.esm.util.FileContentsStageOutput;

import teetime.framework.AbstractConsumerStage;
import teetime.stage.basic.AbstractTransformation;
import cau.agse.hs.staticfortran.model.FortranModuleModel;
import cau.agse.hs.staticfortran.model.FortranProjectModel;

public class FileContentsExtractor extends AbstractTransformation<List<File>, FileContentsStageOutput>{


	/*	
	

	@Override
	protected void execute(List<File> files) throws Exception {
		FortranProjectModel projectModel = new FortranProjectModel();
		FileContents fileContents = new FileContents();
		for(File file : files) {
			projectModel.addModule(file.toPath());	
	}
		for (FortranModuleModel module : projectModel) {
			FileContentsEntry entry = new FileContentsEntry();
			entry.setFile(module.getName());
			Set<String> funcs = module.computeFunctionDeclarations();
			
			for(String s:funcs){
				entry.setIdentifier(getId());
				entry.setType("FUNCTION");
			}
			Set<String> subRouts = module.computeSubroutineDeclarations();
			
			for(String s:subRouts){
				entry.setIdentifier(getId());
				entry.setType("SUBROUTINE");
			}
			fileContents.add(entry);
		}
	//forward the contents
	this.outputPort.send(new FileContentsStageOutput(fileContents, files));
		
	


}
}
*/
