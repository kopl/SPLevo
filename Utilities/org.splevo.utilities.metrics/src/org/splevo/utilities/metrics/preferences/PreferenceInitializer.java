package org.splevo.utilities.metrics.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.splevo.utilities.metrics.Activator;
import org.splevo.utilities.metrics.util.Constants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * Initializes the default Preferences. 
	 * Metrics View is activated by default.
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(Constants.METRICS_VIEW_ACTIVE, true);
	}

}
