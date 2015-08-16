package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.emftext.language.java.resource.java.mopp.JavaPlugin;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

public class MappingDialog extends Dialog {
	
  private VariationPoint variationPoint = null;
  private int counter = 0;
  private Text variantTextField = null;
  private Combo combo = null;
	
  public MappingDialog(Shell parentShell, VariationPoint variationPoint) {
    super(parentShell);
    this.variationPoint = variationPoint;
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
	    		  updateVariantToLicenseMapper();
	    		  incrementCounter();
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
	    			  org.eclipse.jdt.internal.ui.JavaPlugin.getActiveWorkbenchShell(), 
	    			  variationPoint.getVariants().get(counter));
	    	  
	    	  dialog.open();
	    	  
	    	  incrementCounter();
	      }
	    });
  }
  
  private void initComboBox(Composite container) {
	  combo = new Combo(container, SWT.READ_ONLY);
	  combo.setBounds(50, 50, 150, 65);
	  combo.setItems(LicenseConstants.getAllLicenses());
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
  
  private void updateVariantToLicenseMapper() {
	  Variant variant = this.variationPoint.getVariants().get(counter);
	  String license = combo.getText();
	  
	  VariantToLicenseMapper.addVariantLicensePair(variant, license);
  }
  
  private void incrementCounter() {
	  counter++;
	  
	  if(counter < variationPoint.getVariants().size()) {
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
