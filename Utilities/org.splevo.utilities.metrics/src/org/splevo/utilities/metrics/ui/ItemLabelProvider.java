package org.splevo.utilities.metrics.ui;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.splevo.utilities.metrics.calculator.MetricItem;

/**
 * Label provider to present the name of an analzyed item.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ItemLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(Object element) {
        MetricItem metricItem = (MetricItem) element;
        return metricItem.getItemName();
    }
}
