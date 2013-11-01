package org.splevo.vpm;

/**
 * An extension of the vpm model providing capabilities to manage its life cycle. 
 */
public interface VPMExtension {

    /** Initialize the variation point model extension. */
    void init();
    
    /** 
     * Get the name of the extension.
     * @return The name of the extension which should be unique. 
     */
    String getName();
}
