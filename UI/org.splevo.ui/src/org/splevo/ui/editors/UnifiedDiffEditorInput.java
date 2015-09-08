package org.splevo.ui.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.part.FileEditorInput;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorModel;

/**
 * The editor input of the UnifiedDiffEditor.
 * 
 * @author André Wengert
 */
public class UnifiedDiffEditorInput extends FileEditorInput {
    /** A Logger instance. */
    //private static final Logger LOGGER = Logger.getLogger(UnifiedDiffEditorInput.class);
    
    /** A reference to the unified difference connector. */
    private UnifiedDiffConnector unifedDiffConnector;

    /**
     * Constructs an instance of class {@link UnifiedDiffEditorInput} from the given file.
     * 
     * @param iFile
     *            the given unified difference working copy file.
     * @param unifedDiffConnector
     *            a reference to the unified difference connector.
     */
    public UnifiedDiffEditorInput(IFile iFile, UnifiedDiffConnector unifedDiffConnector) {
        super(iFile);
        
        // initialize fields
        this.unifedDiffConnector = unifedDiffConnector;
    }

    @Override
    public String getName() {
        return unifedDiffConnector.getProccessedFileName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Unified Difference of " + unifedDiffConnector.getProccessedFileName();
    }
    
    /**
     * Gets the unified difference connector model.
     * 
     * @return the unified difference connector model.
     */
    public UnifiedDiffConnectorModel getDiffConnectorModel() {
        return unifedDiffConnector.getDiffConnectorModel();
    }
}