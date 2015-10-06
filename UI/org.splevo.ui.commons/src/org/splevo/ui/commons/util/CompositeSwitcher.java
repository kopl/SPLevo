package org.splevo.ui.commons.util;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Helper class for switching the displayed composite.
 */
public class CompositeSwitcher extends Composite {
    
    private static final Logger LOGGER = Logger.getLogger(CompositeSwitcher.class);
    private final StackLayout layout = new StackLayout();

    /**
     * Constructs the composite.
     * 
     * @param parent
     *            The parent as required by the composite pattern of SWT.
     */
    public CompositeSwitcher(Composite parent) {
        super(parent, SWT.NONE);
        setLayoutData(new GridData(GridData.FILL_BOTH));
        setLayout(layout);
    }

    /**
     * Switches the displayed composite to the one with the given index.
     * @param index The index of the composite to show.
     */
    public void switchToElement(int index) {
        if (index < 0 || getChildren().length <= index) {
            LOGGER.warn("Composite switch requested, but the index (" + index + ") is out of bounds.");
            return;
        }
        
        for (Control child : getChildren()) {
            child.setVisible(false);
        }
        this.layout.topControl = getChildren()[index];
        this.layout.topControl.setVisible(true);
        layout(true);
    }
}