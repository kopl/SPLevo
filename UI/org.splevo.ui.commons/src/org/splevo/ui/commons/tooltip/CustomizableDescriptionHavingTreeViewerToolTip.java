package org.splevo.ui.commons.tooltip;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Event;
import org.splevo.vpm.variability.CustomizableDescriptionHaving;

import com.google.common.base.Strings;

/**
 * A tooltip provider for CustomizableDescriptionHaving items located in a tree viewer.
 */
public class CustomizableDescriptionHavingTreeViewerToolTip extends TreeViewerToolTip {

    /**
     * Constructs a new tooltip provider.
     * 
     * @param viewer
     *            The tree viewer to be monitored.
     */
    public CustomizableDescriptionHavingTreeViewerToolTip(TreeViewer viewer) {
        super(viewer, CustomizableDescriptionHaving.class);
    }

    @Override
    protected boolean shouldCreateToolTip(Event event) {
        if (!super.shouldCreateToolTip(event)) {
            return false;
        }

        CustomizableDescriptionHaving item = getCorrectlyTypedItem(event, CustomizableDescriptionHaving.class);
        if (Strings.isNullOrEmpty(item.getDescription())) {
            return false;
        }

        return true;
    }

    @Override
    protected String getText(Event event) {
        CustomizableDescriptionHaving item = getCorrectlyTypedItem(event, CustomizableDescriptionHaving.class);
        return item.getDescription();
    }

    @Override
    protected String getToolTipHeading(Event event) {
        return "Description";
    }

}
