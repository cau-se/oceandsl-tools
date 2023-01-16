package org.oceandsl.tools.esm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileContents {
	List<FileContentsEntry> fileContents = new ArrayList();

	
	public FileContents() {
		
	}
	
	public List<FileContentsEntry> entriesWithFileName(String filename){
		List<FileContentsEntry> filtered = this.fileContents.stream().filter(entry ->entry.getFile().equals(filename)).collect(Collectors.toList());
		return filtered;

	}
	
	public List<FileContentsEntry> entriesWithId(String id){
		List<FileContentsEntry> filtered = this.fileContents.stream().filter(entry ->entry.getIdentifier().equals(id)).collect(Collectors.toList());
		return filtered;

	}
	
	public List<FileContentsEntry> entriesWithType(String type){
		List<FileContentsEntry> filtered = this.fileContents.stream().filter(entry ->entry.getType().equals(type)).collect(Collectors.toList());
		return filtered;

	}
	
	public void add(FileContentsEntry e) {
		fileContents.add(e);
	}
}
