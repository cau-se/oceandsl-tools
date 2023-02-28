package org.oceandsl.tools.esm.stages;

import java.io.File;

import org.oceandsl.tools.esm.util.FileContentsStageOutput;

import teetime.framework.AbstractConsumerStage;

public class EsmDataFlowAnalysisStage extends AbstractConsumerStage<FileContentsStageOutput> {

	private Object dataflow;
	@Override
	protected void execute(FileContentsStageOutput element) throws Exception {
		for(File file : element.getFiles()) {
			
			analyzeCallStatements();
			anaLyzeIfStatements();
			analyzeLoops();
			analyzeSelect();
			analyzeAssignment();
			
			//writeToDataFlow
			
		}
		
	}

	private void analyzeAssignment() {
		// TODO Auto-generated method stub
		
	}

	private void analyzeSelect() {
		// TODO Auto-generated method stub
		
	}

	private void analyzeLoops() {
		// TODO Auto-generated method stub
		
	}

	private void anaLyzeIfStatements() {
		// TODO Auto-generated method stub
		
	}

	private void analyzeCallStatements() {
		// TODO Auto-generated method stub
		
	}



}
