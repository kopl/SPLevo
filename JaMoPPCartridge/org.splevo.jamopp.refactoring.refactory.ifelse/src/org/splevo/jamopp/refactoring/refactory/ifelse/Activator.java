/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial version
 *******************************************************************************/
package org.splevo.jamopp.refactoring.refactory.ifelse;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.splevo.jamopp.refactoring.refactory.util.RefactoryUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        RefactoryUtil.initialize();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // nothing to do here
    }
}