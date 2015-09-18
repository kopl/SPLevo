package org.splevo.ui.commons.util;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class ComboBoxSelectionComposite extends Composite {
    
    private static final Logger LOGGER = Logger.getLogger(ComboBoxSelectionComposite.class);
    private ComboViewer comboViewer;
    private Button acceptButton;
    private final String explanation;
    private final String buttonLabel;
    
    public ComboBoxSelectionComposite(Composite parent, String explanation, String buttonLabel) {
        super(parent, SWT.NONE);
        this.explanation = explanation;
        this.buttonLabel = buttonLabel;
        init();
    }
    
    private void init() {
        // basic setup
        setLayoutData(new GridData(GridData.FILL_BOTH));
        setLayout(new GridLayout(2, false));
        
        // create the controls
        Label explanationLabel = new Label(this, SWT.WRAP);
        comboViewer = new ComboViewer(this, SWT.READ_ONLY | SWT.DROP_DOWN);
        acceptButton = new Button(this, SWT.NONE);
        
        // initialize the explanation label
        explanationLabel.setText(explanation);
        explanationLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
        
        // initialize the VPM selection combo box
        comboViewer.setContentProvider(new SingleLevelContentProvider());
        comboViewer.setLabelProvider(getLabelProvider());
        comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                acceptButton.setEnabled(true);
            }
        });
        comboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        comboViewer.setInput(getComboViewerInput());
        
        // initialize the accept button
        acceptButton.setText(buttonLabel);
        acceptButton.setEnabled(false);
        acceptButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (!(comboViewer.getSelection() instanceof IStructuredSelection)) {
                    LOGGER.warn("No structured selection returned from combo box.");
                    return;
                }

                IStructuredSelection cvSelection = (IStructuredSelection) comboViewer.getSelection();
                handleSelectionAfterAccept(cvSelection);
            }
        });
    }
    
    public void reset() {
        comboViewer.getControl().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                comboViewer.setInput(getComboViewerInput());
                acceptButton.setEnabled(false);
            }});
    }
    
    protected abstract ILabelProvider getLabelProvider();
    protected abstract SingleLevelElementProvider getComboViewerInput();
    protected abstract void handleSelectionAfterAccept(IStructuredSelection cvSelection);

}
