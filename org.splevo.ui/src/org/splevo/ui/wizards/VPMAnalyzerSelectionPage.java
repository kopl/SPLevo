package org.splevo.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.splevo.ui.listeners.VPMAnalyzerLabelProvider;
import org.splevo.ui.listeners.VPMAnalyzerSelectionDialogListener;
import org.eclipse.jface.viewers.ListViewer;

public class VPMAnalyzerSelectionPage extends WizardPage {

	/** A list viewer for configured analyses. */
	private ListViewer listViewerAnalysis = null;
	
	/**
	 * Create the wizard page to let the user select the analyses to be
	 * performed.
	 */
	public VPMAnalyzerSelectionPage() {
		super("wizardPage");
		setTitle("Analyzes Selection");
		setDescription("Select the variation point model analysis to be performed.");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);

		Label lblAvailableAnalayses = new Label(container, SWT.NONE);
		lblAvailableAnalayses.setBounds(10, 10, 211, 20);
		lblAvailableAnalayses.setText("Variation Point Analysis");
		
		listViewerAnalysis = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		listViewerAnalysis.setLabelProvider(new VPMAnalyzerLabelProvider());
		List list = listViewerAnalysis.getList();
		list.setBounds(10, 34, 222, 193);

		Button btnAdd = new Button(container, SWT.NONE);
		btnAdd.addMouseListener(new VPMAnalyzerSelectionDialogListener(listViewerAnalysis, this));
		btnAdd.setBounds(237, 36, 90, 30);
		btnAdd.setText("add");

		Button btnRemove = new Button(container, SWT.NONE);
		btnRemove.setBounds(237, 72, 90, 30);
		btnRemove.setText("remove");

		Button btnConfigure = new Button(container, SWT.NONE);
		btnConfigure.setToolTipText("Not available yet....");
		btnConfigure.setEnabled(false);
		btnConfigure.setText("configure");
		btnConfigure.setBounds(237, 108, 90, 30);
	}
	
	/**
	 * Check the ui content and update the page status.
	 */
	public void update() {
		if(listViewerAnalysis.getList().getItems().length > 0){
			this.setPageComplete(true);
		} else {
			this.setPageComplete(false);
		}
	}
	
	/**
	 * Get the list viewer providing the configured vpm analyzers
	 * @return The listViewer
	 */
	public ListViewer getListViewerAnalysis() {
		return listViewerAnalysis;
	}
}
