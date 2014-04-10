/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.mihalis.opal.itemSelector;

import java.util.LinkedHashSet;

/**
 * Listener notified about newly selected or de-selected items.
 */
public interface DualListSelectionChangedListener {
    /**
     * Handle items that were moved to the selected item table.
     *
     * @param selectedItems
     *            The items moved.
     */
    public void itemsSelected(LinkedHashSet<DLItem> selectedItems);

    /**
     * Handle items that were removed from the selected item table.
     *
     * @param deselectedItems
     *            The items de-selected.
     */
    public void itemsDeSelected(LinkedHashSet<DLItem> deselectedItems);
}
