package org.splevo.diffing.java.modisco;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;

/**
 * The Class Activator.
 */
public class Activator implements BundleActivator {

	/** The context. */
	private static BundleContext context;

	/**
     * Gets the context.
     * 
     * @return the context
     */
	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
        Java2KDMDiffPackage.eINSTANCE.eClass();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
