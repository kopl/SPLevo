package org.splevo.ui.commons.util;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ComboViewer;
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

/**
 * Base class for a combobox selection composite. The composite consists of a combobox for selecting
 * elements, a button to accept the choice, and an explanation label.
 * 
 * Subclasses have to specify a label and element provider, as well as an action handler that reacts
 * on a selection.
 */
public abstract class ComboBoxSelectionComposite extends Composite {

    private static final Logger LOGGER = Logger.getLogger(ComboBoxSelectionComposite.class);
    private ComboViewer comboViewer;
    private Button acceptButton;
    private final String explanation;
    private final String buttonLabel;

    /**
     * Constructs the composite.
     * 
     * @param parent
     *            The parent element as required by the composite pattern of SWT.
     * @param explanation
     *            The explanation to be displayed above the selection.
     * @param buttonLabel
     *            The label for the button next to the combobox.
     */
    protected ComboBoxSelectionComposite(Composite parent, String explanation, String buttonLabel) {
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

    /**
     * Resets the composite by reloading the input for the content provider.
     */
    public void reset() {
        comboViewer.getControl().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                comboViewer.setInput(getComboViewerInput());
                acceptButton.setEnabled(false);
            }
        });
    }

    /**
     * Provides the label provider for the content items.
     * 
     * @return The label provider.
     */
    protected abstract ILabelProvider getLabelProvider();

    /**
     * Provides the content provider for items.
     * 
     * @return The content provider.
     */
    protected abstract SingleLevelElementProvider getComboViewerInput();

    /**
     * Called after the user selected an item and pressed the button.
     * 
     * @param cvSelection
     *            The selection element of the combobox.
     */
    protected abstract void handleSelectionAfterAccept(IStructuredSelection cvSelection);

}
