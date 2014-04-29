package org.splevo.ui.vpexplorer.providers;

import java.io.File;

import org.splevo.vpm.variability.VariationPointGroup;

/**
 * The FileWrapper class wraps a file and a group into one object.
 * 
 */
public class FileWrapper {

    private final Object parent;
    private final File file;
    private final VariationPointGroup group;

    /**
     * The standard constructor of the FileWrapper class.
     * 
     * @param parent
     *            The parent object of this FileWrapper.
     * @param file
     *            The file to be wrapped.
     * @param group
     *            The group of the file to be wrapped.
     */
    public FileWrapper(Object parent, File file, VariationPointGroup group) {
        this.parent = parent;
        this.file = file;
        this.group = group;
    }

    /**
     * 
     * @return the parent object.
     */
    public Object getParent() {
        return parent;
    }

    /**
     * 
     * @return the wrapped file.
     */
    public File getFile() {
        return file;
    }

    /**
     * 
     * @return the group of the wrapped file.
     */
    public VariationPointGroup getGroup() {
        return group;
    }
}
