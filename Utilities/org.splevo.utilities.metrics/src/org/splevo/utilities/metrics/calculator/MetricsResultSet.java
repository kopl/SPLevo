package org.splevo.utilities.metrics.calculator;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A result object of a metrics calculation.
 * 
 * @author Benjamin Klatt
 * 
 */
public interface MetricsResultSet {

    /**
     * Get the identifier of the result set.
     * 
     * @return The identifier string.
     */
    String getId();

    /**
     * Get a set of the available metrics in the result set.
     * 
     * @return The set of metric identifiers.
     */
    Set<String> getAvailableMetrics();

    /**
     * Get the metrics per analyzed item.
     * 
     * @return The list of metric items.
     */
    List<MetricResultItem> getMetricResultItems();

    /**
     * Get the metrics for the total analyzed item set.
     * 
     * @return The metric values identified by their key.
     */
    Map<String, Object> getTotalMetrics();
}
