package org.oceandsl.tools.esm.util;

import java.util.List;

public class Output {

	private List<String>dataflow;
	private List<String>fileContent;
	private List<String>commonBlocks;
	
	public List<String> getDataflow() {
		return dataflow;
	}
	public void setDataflow(List<String> dataflow) {
		this.dataflow = dataflow;
	}
	public List<String> getFileContent() {
		return fileContent;
	}
	public void setFileContent(List<String> fileContent) {
		this.fileContent = fileContent;
	}
	public List<String> getCommonBlocks() {
		return commonBlocks;
	}
	public void setCommonBlocks(List<String> commonBlocks) {
		this.commonBlocks = commonBlocks;
	}
	
}
