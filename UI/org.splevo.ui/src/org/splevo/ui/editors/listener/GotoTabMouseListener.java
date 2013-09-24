package org.splevo.ui.editors.listener;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TabFolder;

/**
 * A mouse adapter to be used as mouse event listener and switch to a specific ui tab.
 */
public class GotoTabMouseListener extends MouseAdapter {

	/** The tab folder to manipulate. */
	private TabFolder tabFolder = null;
	
	/** The index of the tab to go to. */
	private int tabIndex = 0;
	
	/**
	 * Constructor requiring the necessary references.
	 * @param tabFolder The folder containing the tabs to navigate.
	 * @param tabIndex The index of the tab to be opened by this listener.
	 */
	public GotoTabMouseListener(TabFolder tabFolder, int tabIndex) {
		this.tabFolder = tabFolder;
		this.tabIndex = tabIndex;
	}
	
	@Override
	public void mouseUp(MouseEvent e) {
		tabFolder.setSelection(tabIndex);
	}

}
