package org.splevo.ui.listeners;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.splevo.ui.wizards.VPMAnalyzerSelectionPage;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerService;

/**
 * Listener to open a dialog for selecting a vpm analysis to be performed.
 */
public class VPMAnalyzerSelectionDialogListener extends MouseAdapter {

    /** The wizard page to update. */
    private VPMAnalyzerSelectionPage page = null;
    
    /** The analyzer service to work with. */
    private VPMAnalyzerService vpmAnalyzerService = new DefaultVPMAnalyzerService();

    /**
     * Constructor requiring a reference to the listed to be filled.
     * 
     * @param page The page to open.
     */
    public VPMAnalyzerSelectionDialogListener(VPMAnalyzerSelectionPage page) {
        this.page = page;
    }

    /**
     * Mouse handler to open the dialog.
     * 
     * @param e
     *            The mouse event to process.
     */
    @Override
    public void mouseUp(MouseEvent e) {

        Shell shell = e.widget.getDisplay().getShells()[0];
        List<VPMAnalyzer> availableAnalyzer = vpmAnalyzerService.getAvailableAnalyzers();

        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, new VPMAnalyzerLabelProvider());
        dialog.setTitle("VPM Analyzer Selection");
        dialog.setMessage("Select an analyzer by name (* = any string, ? = any char):");
        dialog.setElements(availableAnalyzer.toArray());
        dialog.setMultipleSelection(false);
        int result = dialog.open();
        if (result == Window.OK) {
            Object analyzerObject = dialog.getFirstResult();
            if (analyzerObject != null) {
                VPMAnalyzer analyzer = (VPMAnalyzer) analyzerObject;
                page.addAnalyzer(analyzer);
            }
        }
    }

}
