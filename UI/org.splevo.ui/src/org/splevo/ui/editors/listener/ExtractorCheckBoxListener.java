package org.splevo.ui.editors.listener;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * A listener for a checkbox button to (de-)activate an extractor.
 */
public class ExtractorCheckBoxListener extends SelectionAdapter {

	private Button checkBox = null;
	private String extractorId = null;
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor to set up the listener dependencies.
	 * 
	 * @param checkBox
	 *            The check box to listen on.
	 * @param extractorId
	 *            The extractor managed by the box.
	 * @param splevoProjectEditor
	 *            The editor instance to update in case of selection change.
	 */
	public ExtractorCheckBoxListener(Button checkBox, String extractorId,
			SPLevoProjectEditor splevoProjectEditor) {
		this.checkBox = checkBox;
		this.extractorId = extractorId;
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (checkBox.getSelection()) {
			splevoProjectEditor.getSplevoProject().getExtractorIds().add(extractorId);
		} else {
			splevoProjectEditor.getSplevoProject().getExtractorIds().remove(extractorId);
		}
		splevoProjectEditor.markAsDirty();
	}

}
