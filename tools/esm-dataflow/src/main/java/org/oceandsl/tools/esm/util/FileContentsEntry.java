package org.oceandsl.tools.esm.util;

public class FileContentsEntry{

	private  String File ;
	private  String Identifier;
	private  String Type;
	
	public FileContentsEntry() {
		
	}
	
	public FileContentsEntry(String file, String id, String type) {
		this.setFile(file);
		this.setIdentifier(id);
		this.setType(type);
	}

	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}

	public String getIdentifier() {
		return Identifier;
	}

	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}
