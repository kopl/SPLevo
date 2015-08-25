package org.splevo.ui.editors;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.part.FileEditorInput;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorContent;

/**
 * The editor input of the UnifiedDiffEditor.
 * 
 * @author André Wengert
 */
public class UnifiedDiffEditorInput extends FileEditorInput {
    /** A Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(UnifiedDiffEditorInput.class);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return unifedDiffConnector.getProccessedFileName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToolTipText() {
        return "Unified Difference of " + unifedDiffConnector.getProccessedFileName();
    }
    
    /**
     * Gets the unified difference connector content.
     * 
     * @return the unified difference connector content.
     */
    public UnifiedDiffConnectorContent getUnfiedConnectorContent() {
        return unifedDiffConnector.getConnectorContent();
    }
    
    /**
     * Delegates the deletion of the unified difference working copy to the connector content.
     * 
     * @throws CoreException
     *             in case the unified difference working copy could not be deleted.
     */
    public void deleteWorkingCopy() throws CoreException {
        unifedDiffConnector.getConnectorContent().deleteWorkingCopy();
    }
}