package org.splevo.ui.commons.util;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

/**
 * Utility class to work with item labels.
 */
public final class LabelUtils {

    private LabelUtils() {
        
    }
    
    /**
     * Get the default label for an object provided by any EMF ItemProvider registered for the
     * according meta model element.
     *
     * @param element
     *            The element to search an icon for.
     * @return The identified icon or null if none exists.
     */
    public static String getItemProviderText(Object element) {
        ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        return labelProvider.getText(element);
    }
    
}
