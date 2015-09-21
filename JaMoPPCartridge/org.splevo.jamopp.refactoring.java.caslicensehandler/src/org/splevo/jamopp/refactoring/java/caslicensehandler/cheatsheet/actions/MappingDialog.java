package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import com.google.common.base.Strings;

/**
 * The user can assign a variant to a given license with the mapping dialog.
 */
public class MappingDialog extends Dialog {
	
  private VariationPoint variationPoint = null;
  private int counter = 0;
  private Text variantTextField = null;
  private Combo combo = null;
	
  /**
	 * The ID of the view as specified by the extension.
	 * @param parentShell
	 * 				represents the parent shell.
	 */
  public MappingDialog(Shell parentShell) {
    super(parentShell);
    this.variationPoint = CASLicenseHandlerConfiguration.getVariationPoint();
  }

  @Override
  protected Control createDialogArea(Composite parent) {
	Composite container = (Composite) super.createDialogArea(parent);
	
    initButtons(container);
    initComboBox(container);
    initLabel(container);
    initTextField(container);
    
    return container;
  }
  
  private void initButtons(Composite container) {
	  Button nextButton = new Button(container, SWT.PUSH);
	    nextButton.setLayoutData(new GridData(SWT.END, SWT.RIGHT, false,
	        false));
	    nextButton.setText("Next");
	    nextButton.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {	  
	    	  if (counter < variationPoint.getVariants().size()) {
	    		  String license = combo.getText();
	    		  
	    		  if (Strings.isNullOrEmpty(license)) {
	    			  MessageDialog.openInformation(new Shell(), "Information", "No license was specified");
	    			  return;
	    		  }
	    		  
	    		  if (updateVariantToLicenseMapper(license)) {
	    			  MessageDialog.openInformation(new Shell(), "Information", "The license is already assigned to a variant. Please choose a different.");
	    			  return;
	    		  }
	    		  
	    		  prepareNextAssignment();
	    	  }
	      }
	    });
	    
	    Button addLicenseButton = new Button(container, SWT.PUSH);
	    addLicenseButton.setLayoutData(new GridData(SWT.END, SWT.RIGHT, false,
	        false));
	    addLicenseButton.setText("Add");
	    addLicenseButton.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	    	  AddLicenseDialog dialog = new AddLicenseDialog(
	    			  PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
	    			  variationPoint.getVariants().get(counter).getId());
	    	  
	    	  if (Window.OK == dialog.open()) {
	    		  prepareNextAssignment();
	    	  
	    	  	  combo.setItems(CASLicenseHandlerConfiguration.getAllLicenses());
	    	  	  combo.select(0);
	    	  }
	      }
	    });
  }
  
  private void initComboBox(Composite container) {
	  combo = new Combo(container, SWT.READ_ONLY);
	  combo.setBounds(50, 50, 150, 65);
	  combo.setItems(CASLicenseHandlerConfiguration.getAllLicenses());
	  combo.select(0);
  }
  
  private void initLabel(Composite container) {
	  org.eclipse.swt.widgets.Label label = new org.eclipse.swt.widgets.Label(container, SWT.NULL);
	  label.setText("Variant:");
  }
  
  private void initTextField(Composite container) {
	  variantTextField = new Text(container, SWT.SINGLE | SWT.BORDER);
	  variantTextField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	  variantTextField.setText(this.variationPoint.getVariants().get(counter).getId());
  }
  
  private boolean updateVariantToLicenseMapper(String license) {
	  Variant variant = this.variationPoint.getVariants().get(counter);
	  return CASLicenseHandlerConfiguration.addVariantLicensePair(variant.getId(), license);
  }
  
  private void prepareNextAssignment() {
	  counter++;
	  
	  if (counter < variationPoint.getVariants().size()) {
		  variantTextField.setText(variationPoint.getVariants().get(counter).getId());
	  } else {
		  MessageDialog.openInformation(new Shell(), "Information", "All Variants are assigned");
		  close();
	  }
  }

  @Override
  protected void configureShell(Shell newShell) {
    super.configureShell(newShell);
    newShell.setText("Mapping dialog");
  }

  @Override
  protected Point getInitialSize() {
    return new Point(450, 300);
  }

} 
