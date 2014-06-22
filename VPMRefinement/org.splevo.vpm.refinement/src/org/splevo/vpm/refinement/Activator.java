package org.splevo.vpm.refinement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator controlling the life cycle of the VPM refinement plugin.
 */
public class Activator implements BundleActivator {

	/** The context the plugin is started in. */
	private static BundleContext context;

	/**
     * Accessory for the plugins runtime context.
     * 
     * @return the context
     */
	static BundleContext getContext() {
		return context;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 * {@inheritDoc}
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     * {@inheritDoc}
     */
    @Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
