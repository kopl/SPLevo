package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.Iterator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.variability.Variant;

import com.google.common.base.Strings;

/**
 * The user can assign a variant to a given license with the mapping dialog.
 */
public class MappingDialog extends TitleAreaDialog {
	
  private Iterator<Variant> variants = null;
  private Variant currentVariant = null;
  private Text variantTextField = null;
  private Combo combo = null;
  private CASLicenseHandlerConfiguration config = null;
	
  /**
	 * The constructor.
	 * @param parentShell
	 * 				represents the parent shell.
	 */
  public MappingDialog(Shell parentShell) {
    super(parentShell);
    this.config = CASLicenseHandlerConfiguration.getInstance();
    this.variants = config.getVariationPoint().getVariants().iterator();
    this.currentVariant = this.variants.next();
  }

  @Override
  protected Control createDialogArea(Composite parent) {
	Composite container = (Composite) super.createDialogArea(parent);
	
    initButtons(container);
    initComboBox(container);
    initLabel(container);
    initTextField(container);
    this.setTitle("MappingDialog");
    
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
	    	  String license = combo.getText();
	    		  
	    	  if (Strings.isNullOrEmpty(license)) {
	    		  MessageDialog.openInformation(new Shell(), "Information", "No license was specified");
	    		  return;
	    	  }
	    		  
	    	  if (!updateVariantToLicenseMapper(license)) {
	    		  MessageDialog.openInformation(new Shell(), "Information", "The license is already assigned to a variant. Please choose a different.");
	    		  return;
	    	  }
	    		  
	    	  prepareNextAssignment();
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
	    			  currentVariant.getId());
	    	  
	    	  if (Window.OK == dialog.open()) {
	    	  	  combo.setItems(config.getAllLicenses());
	    	  	  combo.select(0);
	    	  	  
	    	  	  prepareNextAssignment();
	    	  }
	      }
	    });
  }
  
  private void initComboBox(Composite container) {
	  combo = new Combo(container, SWT.READ_ONLY);
	  combo.setBounds(50, 50, 150, 65);
	  combo.setItems(config.getAllLicenses());
	  combo.select(0);
  }
  
  private void initLabel(Composite container) {
	  Label label = new Label(container, SWT.NULL);
	  label.setText("Variant:");
  }
  
  private void initTextField(Composite container) {
	  variantTextField = new Text(container, SWT.SINGLE | SWT.BORDER);
	  variantTextField.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	  updateTextField();
  }
  
  private void updateTextField() {
	  variantTextField.setText(this.currentVariant.getId());
  }
  
  private boolean updateVariantToLicenseMapper(String license) {
	  return config.addVariantLicensePair(currentVariant.getId(), license);
  }
  
  private void prepareNextAssignment() {
	  if (this.variants.hasNext()) {
		  this.currentVariant = this.variants.next();
		  updateTextField();
	  } else {
		  MessageDialog.openInformation(this.getShell(), "Information", "All Variants are assigned");
		  close();
	  }
  }
 
  @Override
  protected Point getInitialSize() {
    return new Point(450, 300);
  }

} 
