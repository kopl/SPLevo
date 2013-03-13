package org.splevo.utilities.metrics.calculator;

import java.util.Map;

/**
 * The metrics for a single analyzed item.
 * @author Benjamin Klatt
 *
 */
public interface MetricResultItem {

    /**
     * Get a string representation of the item.
     * @return The item name.
     */
    String getItemName();
    
    /**
     * Get a map of the calculated maps.
     * The key of the map represents the key of the metric.
     * The value is the calculated metric value.
     * @return The metrics map.
     */
    Map<String, Object> getMetrics();
}
