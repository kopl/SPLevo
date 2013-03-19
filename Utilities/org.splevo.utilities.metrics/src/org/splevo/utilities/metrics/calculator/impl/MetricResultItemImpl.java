/**
 * 
 */
package org.splevo.utilities.metrics.calculator.impl;

import java.net.URI;
import java.util.HashMap;

import org.splevo.utilities.metrics.calculator.MetricResultItem;

/**
 * A metric item to store the calculated metric values for an item.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricResultItemImpl extends HashMap<String, Object> implements MetricResultItem {

    /** The id of serializing a metric result item. */
    private static final long serialVersionUID = -4036007740994724989L;

    /** The URI of the analyzed item. */
    private URI itemUri = null;
    
    /** The name of the analyzed item. */
    private String itemName = null;

    /**
     * Constructor requiring the name of the metric.
     * 
     * @param itemName
     *            The name of the analyzed item.
     * @param itemUri
     *            The uri of the analyzed item.
     */
    public MetricResultItemImpl(String itemName, URI itemUri) {
        this.itemUri = itemUri;
        this.itemName = itemName;
    }

    @Override
    public URI getItemURI() {
        return this.itemUri;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

}
