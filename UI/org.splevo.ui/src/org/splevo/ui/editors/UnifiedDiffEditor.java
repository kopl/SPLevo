package org.splevo.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * Implementation of a unified diff editor. At the moment, this is only a non editable viewer for
 * the unified diff.
 */
public class UnifiedDiffEditor extends EditorPart {

    /**
     * The editor input of the UnifiedDiffEditor. Basically, it is a wrapper for HTML code.
     */
    public static class UnifiedDiffEditorInput implements IEditorInput {

        private String html;

        /**
         * Constructs the editor input.
         * 
         * @param html
         *            The html code to encapsulate.
         */
        public UnifiedDiffEditorInput(String html) {
            this.html = html;
        }

        /**
         * Gets the encapsulated HTML code.
         * 
         * @return The HTML code.
         */
        public String getHTML() {
            return html;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Object getAdapter(Class adapter) {
            return null;
        }

        @Override
        public boolean exists() {
            return true;
        }

        @Override
        public ImageDescriptor getImageDescriptor() {
            return null;
        }

        @Override
        public String getName() {
            return "Unified Diff";
        }

        @Override
        public IPersistableElement getPersistable() {
            return null;
        }

        @Override
        public String getToolTipText() {
            return "Unified Diff";
        }

    }

    /** The id of the editor. */
    public static final String ID = "org.splevo.ui.editors.UnifiedDiffEditor"; //$NON-NLS-1$

    private Browser browser = null;

    @Override
    public void doSave(IProgressMonitor monitor) {
        monitor.done();
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof UnifiedDiffEditorInput)) {
            throw new PartInitException(String.format("The given editor input has to be of type %s.",
                    UnifiedDiffEditorInput.class.getName()));
        }
        setSite(site);
        setInput(input);
        setHTMLTextIfAvailable();
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        if (browser != null) {
            browser.dispose();
        }
        browser = new Browser(parent, SWT.NONE);
        setHTMLTextIfAvailable();
    }

    @Override
    public void setFocus() {
    }

    private void setHTMLTextIfAvailable() {
        if (browser != null && getEditorInput() != null && getEditorInput() instanceof UnifiedDiffEditorInput) {
            browser.setText(((UnifiedDiffEditorInput) getEditorInput()).getHTML());
        }
    }
}
