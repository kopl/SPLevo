/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.refactoring;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The Class Activator.
 */
public class Activator implements BundleActivator {

    private static final String REFACTORING_EXTENSION_POINT_ID = "org.splevo.refactoring.refactoring";
    private static final String EXTENSION_POINT_ATTR_REFACTORING_CLASS = "refactoring.class";

    /** The logger for this class. */
    private static Logger logger = Logger.getLogger(Activator.class);

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        List<VariabilityRefactoring> refactorings = loadRefactorings();
        for (VariabilityRefactoring refactoring : refactorings) {
            VariabilityRefactoringRegistry.registerRefactoring(refactoring);
        }

    }

    /**
     * Load the {@link VariabilityRefactoring} implementations registered for the according
     * extension point.
     *
     * {@inheritDoc}
     */
    public List<VariabilityRefactoring> loadRefactorings() {
        List<VariabilityRefactoring> refactoringList = new LinkedList<VariabilityRefactoring>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(REFACTORING_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + REFACTORING_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_REFACTORING_CLASS);
                    if ((o != null) && (o instanceof VariabilityRefactoring)) {
                        VariabilityRefactoring refactoring = (VariabilityRefactoring) o;
                        refactoringList.add(refactoring);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load refactoring extension", e);
                }
            }
        }

        if (refactoringIdsNotUnique(refactoringList)) {
            logger.warn("Two or more refactorings with the same id loaded.");
        }

        if (refactoringLabelsNotUnique(refactoringList)) {
            logger.warn("Two or more refactorings with the same label loaded.");
        }

        return refactoringList;
    }

    /**
     * Check if there are two or more refactorings with the same id.
     *
     * @param refactorings
     *            The refactorings to check.
     * @return True if the same id is used more than once. False otherwise.
     */
    private boolean refactoringIdsNotUnique(List<VariabilityRefactoring> refactorings) {
        List<String> ids = new LinkedList<String>();
        for (VariabilityRefactoring refactoring : refactorings) {
            if (ids.contains(refactoring.getId())) {
                return true;
            }
            ids.add(refactoring.getId());
        }
        return false;

    }

    /**
     * Check if there are two or more refactorings with the same label.
     *
     * @param refactorings
     *            The refactorings to check.
     * @return True if the same id is used more than once. False otherwise.
     */
    private boolean refactoringLabelsNotUnique(List<VariabilityRefactoring> refactorings) {
        List<String> refactoringIds = new LinkedList<String>();
        for (VariabilityRefactoring refactoring : refactorings) {
            if (refactoringIds.contains(refactoring.getId())) {
                return true;
            }
            refactoringIds.add(refactoring.getId());
        }
        return false;

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }
}
