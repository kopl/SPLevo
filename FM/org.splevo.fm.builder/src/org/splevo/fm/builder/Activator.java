package org.splevo.fm.builder;

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
 * The activator of this OSGi Bundle.
 * It is capable to handle any life-cycle phases.
 */
public class Activator implements BundleActivator {

    private static Logger logger = Logger.getLogger(Activator.class);

    /** The id of the extension point to look up registered builders. */
    private static final String BUILDER_EXTENSION_POINT_ID = "org.splevo.fm.builder";

    /** The extension attribute specifying the builder class to instantiate. */
    private static final String EXTENSION_POINT_ATTR_BUILDER_CLASS = "builder.class";
    
    
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
		loadRegisteredFeatureModelBuilders();
	}

	/**
	 * Stop the plug-in life-cycle.
	 * {@inheritDoc}
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
    /**
     * Try to access the eclipse extension registry and load the extensions available.
     */
    private void loadRegisteredFeatureModelBuilders() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry != null) {

            IExtensionPoint extensionPoint = registry.getExtensionPoint(BUILDER_EXTENSION_POINT_ID);
            if (extensionPoint != null) {
                IExtension[] extensions = extensionPoint.getExtensions();
                for (IExtension extension : extensions) {
                    IConfigurationElement[] configurations = extension.getConfigurationElements();
                    for (IConfigurationElement element : configurations) {
                        try {
                            Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_BUILDER_CLASS);
                            if ((o != null) && (o instanceof FeatureModelBuilder<?>)) {
                                @SuppressWarnings("unchecked")
                                FeatureModelBuilder<Object> extractor = (FeatureModelBuilder<Object>) o;
                                FeatureModelBuilderRegistry.getInstance().registerElement(extractor);
                            }
                        } catch (CoreException e) {
                            logger.error("Failed to load feature model builder extension", e);
                        }
                    }
                }
            }
        }
    }

}
