package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Strings;

/**
 * With the dialog new licenses can be added.
 */
public class AddLicenseDialog extends TitleAreaDialog {
	private final String licenseConstantText;
	private Text constantTextField = null;
	private Text licenseTextField = null;
	
	/**
	 * Constructor.
	 * @param parentShell
	 * 				represents the parent shell.
	 * @param variant
	 * 				represents the variant id.
	 */
	protected AddLicenseDialog(Shell parentShell, String variant) {
		super(parentShell);
		this.licenseConstantText = variant;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
	    initLabels(container);
	    initTextFields(container);
	    this.setTitle("Add license");
	    
	    return container;
	 }
	
	private void initLabels(Composite container) {
		org.eclipse.swt.widgets.Label label = new org.eclipse.swt.widgets.Label(container, SWT.NULL);
		label.setText("Variant:");
		
		label = new org.eclipse.swt.widgets.Label(container, SWT.NULL);
		label.setText("License:");
	}
	
	private void initTextFields(Composite container) {
		constantTextField = new Text(container, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		constantTextField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		constantTextField.setText(this.licenseConstantText);
		  
		licenseTextField = new Text(container, SWT.SINGLE | SWT.BORDER);
		licenseTextField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	}
	
	@Override
	protected void okPressed() {
		String license = licenseTextField.getText();
		CASLicenseHandlerConfiguration config = CASLicenseHandlerConfiguration.getInstance();
		
		if (Strings.isNullOrEmpty(license)) {
			MessageDialog.openInformation(new Shell(), "Information", "No license was specified");
			return;
		} 
		
		if (!config.addVariantLicensePair(licenseConstantText, license)) {
			MessageDialog.openInformation(new Shell(), "Information", "The license is already assigned to a variant. Please choose a different.");
			return;
		}
		
		JaMoPPRoutines.addConstantLicenseFieldTo(config.getLicenseConstant(), license);
		close();
	}

}
