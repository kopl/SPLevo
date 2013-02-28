package org.splevo.utilities.metrics;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "org.splevo.utilities.metrics"; //$NON-NLS-1$

	/** The plugin. */
	private static Activator plugin;
	
	/**
     * The constructor.
     */
	public Activator() {
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
	public static Activator getDefault() {
		return plugin;
	}

}