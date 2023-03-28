package org.oceandsl.tools.esm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileContents {
    List<FileContentsEntry> fileContents = new ArrayList<>();

    public FileContents() {

    }

    public List<FileContentsEntry> entriesWithFileName(final String filename) {
        final List<FileContentsEntry> filtered = this.fileContents.stream()
                .filter(entry -> entry.getFile().equals(filename)).collect(Collectors.toList());
        return filtered;

    }

    public List<FileContentsEntry> entriesWithId(final String id) {
        final List<FileContentsEntry> filtered = this.fileContents.stream()
                .filter(entry -> entry.getIdentifier().equals(id)).collect(Collectors.toList());
        return filtered;

    }

    public List<FileContentsEntry> entriesWithType(final String type) {
        final List<FileContentsEntry> filtered = this.fileContents.stream()
                .filter(entry -> entry.getType().equals(type)).collect(Collectors.toList());
        return filtered;

    }

    public void add(final FileContentsEntry e) {
        this.fileContents.add(e);
    }
}
