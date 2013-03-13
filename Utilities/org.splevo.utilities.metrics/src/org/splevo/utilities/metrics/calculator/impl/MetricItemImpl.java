/**
 * 
 */
package org.splevo.utilities.metrics.calculator.impl;

import java.util.HashMap;
import java.util.Map;

import org.splevo.utilities.metrics.calculator.MetricResultItem;

/**
 * A metric item to store the calculated metric values for an item.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricItemImpl implements MetricResultItem {

    /** The name of the analyzed item. */
    private String itemName = null;

    /** The map of calculated metrics. */
    private Map<String, Object> metrics = new HashMap<String, Object>();

    /**
     * Constructor requiring the name of the metric.
     * 
     * @param name
     *            The name to set.
     */
    public MetricItemImpl(String name) {
        this.itemName = name;
    }

    /**
     * Set a metric value in the item.
     * 
     * @param metricKey
     *            The identifier of the metric.
     * @param value
     *            The value calculated for the item.
     */
    public void setMetric(String metricKey, Object value) {
        metrics.put(metricKey, value);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.splevo.utilities.metrics.calculator.MetricResultItem#getItemName()
     */
    @Override
    public String getItemName() {
        return this.itemName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.splevo.utilities.metrics.calculator.MetricResultItem#getMetrics()
     */
    @Override
    public Map<String, Object> getMetrics() {
        return this.metrics;
    }

}
