package org.splevo.vpm;

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

    /** The id of the vpm extension extension point. */
    private static final String VPM_EXTENSION_EXTENSION_POINT_ID = "org.splevo.vpm.extension";

    /** The id of the implementing class attribute of the vpm extension extension point. */
    private static final String EXTENSION_POINT_ATTR_EXTENSION_CLASS = "extension.class";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(Activator.class);

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
        loadVPMModelExtensions();
    }

    /**
     * Load all variation point model extensions and trigger their initialization.
     */
    private void loadVPMModelExtensions() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(VPM_EXTENSION_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + VPM_EXTENSION_EXTENSION_POINT_ID);
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_EXTENSION_CLASS);
                    if ((o != null) && (o instanceof VPMExtension)) {
                        VPMExtension vpmExtension = (VPMExtension) o;
                        vpmExtension.init();
                        logger.debug(String.format("VPM Extension %s intialized", vpmExtension.getName()));
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load and initialize VPM extension", e);
                }
            }
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }

}
