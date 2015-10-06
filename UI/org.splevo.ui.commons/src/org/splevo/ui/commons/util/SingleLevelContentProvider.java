package org.splevo.ui.commons.util;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider that only provides elements without children. Therefore, there is no hierarchy
 * in the provided information. This provider required an input of type
 * {@link SingleLevelElementProvider}.
 */
public class SingleLevelContentProvider implements IStructuredContentProvider {

    private static final Logger LOGGER = Logger.getLogger(SingleLevelContentProvider.class);
    private SingleLevelElementProvider provider;

    @Override
    public void inputChanged(final Viewer viewer, Object oldInput, Object newInput) {
        if (newInput == null) {
            return;
        }

        if (!(newInput instanceof SingleLevelElementProvider)) {
            LOGGER.warn(String.format("The given input is of type %s instead of %s.", newInput.getClass()
                    .getSimpleName(), SingleLevelElementProvider.class.getSimpleName()));
            return;
        }

        provider = (SingleLevelElementProvider) newInput;
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
