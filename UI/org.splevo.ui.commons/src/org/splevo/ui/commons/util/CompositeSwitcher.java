package org.splevo.ui.commons.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Helper class for switching the displayed composite.
 */
public class CompositeSwitcher extends Composite {
    private final StackLayout layout = new StackLayout();

    public CompositeSwitcher(Composite parent) {
        super(parent, SWT.NONE);
        setLayoutData(new GridData(GridData.FILL_BOTH));
        setLayout(layout);
    }

    public void switchToElement(int index) {
        for (Control child : getChildren()) {
            child.setVisible(false);
        }
        this.layout.topControl = getChildren()[index];
        this.layout.topControl.setVisible(true);
        layout(true);
    }
}