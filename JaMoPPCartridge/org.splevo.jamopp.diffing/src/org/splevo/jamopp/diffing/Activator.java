package org.splevo.jamopp.diffing;

import org.emftext.commons.layout.LayoutPackage;
import org.emftext.language.java.JavaPackage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * The Class Activator.
 */
public class Activator implements BundleActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.splevo.jamopp.diffing"; //$NON-NLS-1$

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
		JavaPackage.eINSTANCE.eClass();
		LayoutPackage.eINSTANCE.eClass();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
