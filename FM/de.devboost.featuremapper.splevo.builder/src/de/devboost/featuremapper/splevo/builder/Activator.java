/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Wende (DevBoost GmbH) - initial API and implementation and/or initial documentation
 *******************************************************************************/

package de.devboost.featuremapper.splevo.builder;

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
