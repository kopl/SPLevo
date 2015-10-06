package org.splevo.ui.sourceconnection.helper;

import java.io.File;

/**
 * Helper class that encapsulates a file and a line number.
 * 
 * @author André Wengert
 */
public class FileLineNumberPair {
    private File file;
    private int lineNumber;

    /**
     * Constructs an instance of class {@link FileLineNumberPair}.
     * 
     * @param file
     *            a file.
     * @param lineNumber
     *            a line number.
     */
    public FileLineNumberPair(File file, int lineNumber) {
        this.file = file;
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the file.
     * 
     * @return the file.
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets the line number.
     * 
     * @return the line number.
     */
    public int getLineNumber() {
        return lineNumber;
    }
}
