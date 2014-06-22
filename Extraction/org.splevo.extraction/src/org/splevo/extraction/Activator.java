package org.splevo.extraction;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator of this OSGi Bundle.
 * It is capable to handle any life-cycle phases.
 */
public class Activator implements BundleActivator {

	private static BundleContext context;

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	static BundleContext getContext() {
		return context;
	}

	/**
	 * Start up the plug-in life-cycle.
	 * {@inheritDoc}
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/**
	 * Stop the plug-in life-cycle.
	 * {@inheritDoc}
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
