package org.splevo.ui.editors.listener;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * A listener for a checkbox button to (de-)activate a differ.
 */
public class DifferCheckBoxListener extends SelectionAdapter {

	private Button checkBox = null;
	private String differId = null;
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor to set up the listener dependencies.
	 *
	 * @param checkBox
	 *            The check box to listen on.
	 * @param differId
	 *            The differ managed by the box.
	 * @param splevoProjectEditor
	 *            The editor instance to update in case of selection change.
	 */
	public DifferCheckBoxListener(Button checkBox, String differId,
			SPLevoProjectEditor splevoProjectEditor) {
		this.checkBox = checkBox;
		this.differId = differId;
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (checkBox.getSelection()) {
			splevoProjectEditor.getSplevoProject().getDifferIds().add(differId);
		} else {
			splevoProjectEditor.getSplevoProject().getDifferIds().remove(differId);
		}
		splevoProjectEditor.markAsDirty();
	}

}
