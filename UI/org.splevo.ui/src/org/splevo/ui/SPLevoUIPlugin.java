/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class SPLevoUIPlugin extends AbstractUIPlugin {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SPLevoUIPlugin.class);

    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "org.splevo.ui"; //$NON-NLS-1$

    /** The shared instance. **/
    private static SPLevoUIPlugin plugin;

    /** Pre-loaded representation of the KoPL icon. */
    public static final Image KOPL_ICON = getImageDescriptor("icons/kopl_circle_only.png").createImage();

    /** KoPL logo color orange. */
    public static final Color COLOR_ORANGE = new Color(Display.getCurrent(), 234, 103, 40);

    /** KoPL logo color light blue. */
    public static final Color COLOR_LIGHT_BLUE = new Color(Display.getCurrent(), 196, 211, 219);

    /** KoPL logo color dark blue. */
    public static final Color COLOR_DARK_BLUE = new Color(Display.getCurrent(), 35, 72, 92);

    /** KoPL logo color medium blue. */
    public static final Color COLOR_MEDIUM_BLUE = new Color(Display.getCurrent(), 120, 143, 154);

    /**
     * The constructor.
     */
    public SPLevoUIPlugin() {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        // Setup logging
        URL confURL = getBundle().getEntry("log4j.properties");
        PropertyConfigurator.configure(FileLocator.toFileURL(confURL).getFile());
        logger.info("Logging using log4j and configuration " + FileLocator.toFileURL(confURL).getFile());

        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static SPLevoUIPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     *
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
