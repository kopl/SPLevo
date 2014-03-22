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
package org.splevo.ui.editors.listener;

import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * Change listener which marks the editor as dirty for each occurred event.
 */
public class MarkDirtyListener implements IChangeListener, IListChangeListener {

	/** the editor to mark as dirty. */
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor requiring the reference to the editor to mark as dirty.
	 * 
	 * @param splevoProjectEditor
	 *            The editor reference.
	 */
	public MarkDirtyListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void handleChange(ChangeEvent event) {
		splevoProjectEditor.markAsDirty();
	}

	@Override
	public void handleListChange(ListChangeEvent event) {
		splevoProjectEditor.markAsDirty();
	}

}
