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
