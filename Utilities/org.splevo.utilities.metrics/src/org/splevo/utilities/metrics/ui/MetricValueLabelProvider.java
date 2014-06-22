package org.splevo.utilities.metrics.ui;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Label provider to present the name of a metric.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricValueLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(Object element) {
        @SuppressWarnings("unchecked")
        Entry<String, Object> metricItem = (Entry<String, Object>) element;
        return metricItem.getValue().toString();
    }
}
