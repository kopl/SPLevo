package org.splevo.utilities.metrics.ui;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.splevo.utilities.metrics.calculator.MetricResultItem;

/**
 * Label provider to present the value of a specific metric.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricLabelProvider extends ColumnLabelProvider {

    /** The key of the metric to display. */
    private String metricKey = null;

    /**
     * Constructor requiring to specify which metric to present.
     * 
     * @param metricKey
     *            The identifier for the metric.
     */
    public MetricLabelProvider(String metricKey) {
        this.metricKey = metricKey;
    }

    @Override
    public String getText(Object element) {
        MetricResultItem metricResultItem = (MetricResultItem) element;
        if (metricResultItem.containsKey(metricKey)) {
            return metricResultItem.get(metricKey).toString();
        }

        return "";
    }
}
