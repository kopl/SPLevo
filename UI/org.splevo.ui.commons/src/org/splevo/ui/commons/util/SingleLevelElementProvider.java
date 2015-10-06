package org.splevo.ui.commons.util;

/**
 * Element provider that provides elements without children.
 */
public interface SingleLevelElementProvider {

    /**
     * @return The list of all available elements.
     */
    Object[] getElements();
}
