package org.splevo.utilities.metrics.calculator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.splevo.utilities.metrics.calculator.MetricResultItem;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;

/**
 * An implementation of a metrics result.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricsResultSetImpl implements MetricsResultSet {

    /** The stored metrics. */
    private ArrayList<MetricResultItem> metricResultItems = new ArrayList<MetricResultItem>();
    
    /** The list of available metrics. */
    private Set<String> availableMetrics = new HashSet<String>();

    /** The map of calculated total metrics. */
    private Map<String, Object> totalMetrics = new HashMap<String, Object>();

    /** The id of the resultset. */
    private String id = null;

    /**
     * Constructor requiring to set the result set id.
     * 
     * @param id
     *            The id of the result set.
     */
    public MetricsResultSetImpl(String id) {
        this.id = id;
    }

    /**
     * Add a metric result item to the result set.
     * This method should be used by a calculator to add a calculated result.
     * 
     * @param item
     *            The result to add.
     */
    public void addMetricResultItem(MetricResultItem item) {
        metricResultItems.add(item);
    }
    
    /**
     * Add the id of an available metric.
     * This should be used by the calculator to add the information
     * that he has calculated a specific metric.
     * 
     * @param metricId The id of the calculated metric.
     */
    public void addAvailableMetric(String metricId) {
        this.availableMetrics.add(metricId);
    }

    @Override
    public Set<String> getAvailableMetrics() {
        return availableMetrics;
    }

    @Override
    public List<MetricResultItem> getMetricResultItems() {
        return this.metricResultItems;
    }

    @Override
    public Map<String, Object> getTotalMetrics() {
        return this.totalMetrics;
    }

    @Override
    public String getId() {
        return id;
    }
}
