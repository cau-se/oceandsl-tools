package org.oceandsl.tools.esm.util;

public class FileContentsEntry {

    private String File;
    private String Identifier;
    private String Type;

    public FileContentsEntry() {

    }

    public FileContentsEntry(final String file, final String id, final String type) {
        this.setFile(file);
        this.setIdentifier(id);
        this.setType(type);
    }

    public String getFile() {
        return this.File;
    }

    public void setFile(final String file) {
        this.File = file;
    }

    public String getIdentifier() {
        return this.Identifier;
    }

    public void setIdentifier(final String identifier) {
        this.Identifier = identifier;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(final String type) {
        this.Type = type;
    }
}
