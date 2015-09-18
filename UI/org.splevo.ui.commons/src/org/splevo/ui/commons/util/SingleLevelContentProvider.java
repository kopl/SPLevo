package org.splevo.ui.commons.util;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SingleLevelContentProvider<T extends SingleLevelElementProvider> implements IStructuredContentProvider {

    private static final Logger LOGGER = Logger.getLogger(SingleLevelContentProvider.class);
    private T provider;
    private Class<T> clazz;

    public SingleLevelContentProvider(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(final Viewer viewer, Object oldInput, Object newInput) {
        if (newInput == null) {
            return;
        }

        if (!clazz.isAssignableFrom(newInput.getClass())) {
            LOGGER.warn(String.format("The given input is of type %s instead of %s.", newInput.getClass()
                    .getSimpleName(), clazz.getSimpleName()));
            return;
        }

        provider = (T) newInput;
        viewer.getControl().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                viewer.refresh();
            }
        });
    }

    @Override
    public void dispose() {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (provider != null) {
            return provider.getElements();
        }
        return new Object[0];
    }

}
