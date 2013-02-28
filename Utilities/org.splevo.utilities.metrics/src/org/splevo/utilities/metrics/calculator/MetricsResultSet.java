package org.splevo.utilities.metrics.calculator;

import java.util.List;
import java.util.Map;

/**
 * A result object of a metrics calculation.
 * @author Benjamin Klatt
 *
 */
public interface MetricsResultSet {

    /**
     * Get a list of the available metrics in the result set.
     * @return The list of metric identifiers.
     */
    List<String> getAvailableMetrics();
    
    /**
     * Get the metrics per analyzed item.
     * @return The list of metric items.
     */
    List<MetricItem> getMetrics();
    
    /**
     * Get the metrics for the total analyzed item set.
     * @return The metric values identified by their key.
     */
    Map<String, Object> getTotalMetrics();
}
