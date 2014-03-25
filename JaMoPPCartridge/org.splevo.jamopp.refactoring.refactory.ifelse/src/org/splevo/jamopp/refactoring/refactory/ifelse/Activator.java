package org.splevo.jamopp.refactoring.refactory.ifelse;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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