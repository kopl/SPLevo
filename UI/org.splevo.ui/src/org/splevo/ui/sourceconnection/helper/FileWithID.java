package org.splevo.ui.sourceconnection.helper;

import java.io.File;

/**
 * Helper class that encapsulates a file and an identifier.
 * 
 * @author André Wengert
 */
public class FileWithID {
    private File file;
    private String id;

    /**
     * Constructs an instance of class {@link FileWithID}.
     * 
     * @param file
     *            a file.
     * @param id
     *            an identifier.
     */
    public FileWithID(File file, String id) {
        this.file = file;
        this.id = id;
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
     * Gets the identifier.
     * 
     * @return the identifier.
     */
    public String getID() {
        return id;
    }
}
