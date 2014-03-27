/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/

package org.splevo.ui.vpexplorer.treeitems;

/**
 * The Class CULocationNameTreeItem.
 */
public class CULocationNameTreeItem {
    private final int hashCode;
    private String cuLocationName;
    
    /**
     * Instantiates a new software element tree item.
     * 
     * @param cuLocationName
     *            the cu location name
     */
    public CULocationNameTreeItem(String cuLocationName) {
        this.cuLocationName = cuLocationName;
        this.hashCode = 17 + 31 * cuLocationName.hashCode();
    }

    /**
     * Gets the CU location name.
     * 
     * @return the CU location name
     */
    public String getCULocationName() {
        return cuLocationName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CULocationNameTreeItem) {
            if (((CULocationNameTreeItem) obj).getCULocationName().equals(cuLocationName)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

}
