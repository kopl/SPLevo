package org.splevo.utilities.metrics.ui;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.splevo.utilities.metrics.calculator.MetricResultItem;

/**
 * Label provider to present the name of an analzyed item.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ItemLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(Object element) {
        MetricResultItem metricItem = (MetricResultItem) element;
        return metricItem.getItemName();
    }
}
