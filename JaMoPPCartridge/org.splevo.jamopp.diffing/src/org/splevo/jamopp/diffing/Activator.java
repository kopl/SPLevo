/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
