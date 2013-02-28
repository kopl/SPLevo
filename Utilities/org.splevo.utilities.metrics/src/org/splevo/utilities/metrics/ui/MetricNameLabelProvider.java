package org.splevo.utilities.metrics.ui;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Label provider to present the name of a metric.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricNameLabelProvider extends ColumnLabelProvider {

    @SuppressWarnings("unchecked")
    @Override
    public String getText(Object element) {
        Entry<String, Object> metricItem = (Entry<String, Object>) element;
        return metricItem.getKey();
    }
}
