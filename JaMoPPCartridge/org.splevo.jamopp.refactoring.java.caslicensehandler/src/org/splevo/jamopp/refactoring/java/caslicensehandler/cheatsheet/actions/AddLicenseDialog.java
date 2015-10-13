package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Strings;

/**
 * With the dialog new licenses can be added.
 */
public class AddLicenseDialog extends TitleAreaDialog {
	private Text text;
	
	/**
	 * Constructor.
	 * @param parentShell
	 * 				represents the parent shell.
	 */
	protected AddLicenseDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
    public void create() {
        super.create();
        setTitle("Add New License Constant");
    }
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		label.setText("New License Constant: ");
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));	
		   
	    return container;
	 }
		
	@Override
	protected void okPressed() {
		String license = text.getText();
		CASLicenseHandlerConfiguration config = CASLicenseHandlerConfiguration.getInstance();
		
		if (Strings.isNullOrEmpty(license)) {
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
			        "Information", "No license was specified.");
			return;
		} 
		
		if (Character.isDigit(license.charAt(0))) {
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
			        "Information", "The first letter can't be a number.");
			return;
		}
		
		JaMoPPRoutines.addConstantLicenseFieldTo(config.getLicenseConstantClassifier(), license);
		close();
	}
}
