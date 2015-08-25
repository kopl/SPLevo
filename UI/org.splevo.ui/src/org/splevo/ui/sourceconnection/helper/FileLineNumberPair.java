package org.splevo.ui.sourceconnection.helper;

import java.io.File;

public class FileLineNumberPair {
    private File file;
    private int lineNumber;

    public FileLineNumberPair(File file, int lineNumber) {
        this.file = file;
        this.lineNumber = lineNumber;
    }

    public File getFile() {
        return file;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
