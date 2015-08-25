package org.splevo.ui.sourceconnection.helper;

import java.io.File;

public class FileID {
    private File file;
    private String id;

    public FileID(File file, String id) {
        this.file = file;
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public String getID() {
        return id;
    }
}
