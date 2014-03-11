package org.splevo.refactoring;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        RefactoringRegistry.registerRefactorings();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        RefactoringRegistry.unegisterRefactorings();
    }
}
