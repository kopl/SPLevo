package org.splevo.utilities.metrics.calculator;

import java.net.URI;
import java.util.Map;

/**
 * The metrics for a single analyzed item.
 * @author Benjamin Klatt
 * 
 */
public interface MetricResultItem extends Map<String, Object> {

    /**
     * Get a string representation of the item.
     * @return The item name.
     */
    String getItemName();
    
    /**
     * Get the URI of the item.
     * @return The item uri.
     */
    URI getItemURI();
}
