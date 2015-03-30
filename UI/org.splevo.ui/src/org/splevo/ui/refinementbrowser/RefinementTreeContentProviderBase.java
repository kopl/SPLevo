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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.splevo.ui.listeners.EObjectChangedListener;
import org.splevo.vpm.refinement.Refinement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.ObjectArrays;

/**
 * Base class for refinement tree content providers. The class automatically refreshes the TreeView
 * after changes of the given top level element.
 * 
 * @param <T>
 *            The type of the top level element.
 */
public abstract class RefinementTreeContentProviderBase<T extends EObject> implements ITreeContentProvider {

    /**
     * Adapter for EObjects that refreshes a given viewer as soon as a refinement has been changed.
     */
    private static class EObjectChangedAdapter extends EObjectChangedListener {

        private final Viewer viewer;
        private final int ownerId;

        public EObjectChangedAdapter(Viewer viewer, int ownerId) {
            super(new Predicate<Notification>() {
                @Override
                public boolean apply(Notification arg0) {
                    return arg0.getNotifier() instanceof Refinement;
                }
            });
            this.viewer = viewer;
            this.ownerId = ownerId;
        }

        @Override
        protected void reactOnChange(Notification notification) {
            // We don't want to use defaultDisplay() but the appropriate display. Unfortunately we
            // can not know whether the control is disposed or not (race conditions might occur
            // between checking and using the getDisplay() method). So, we simply catch an occurring
            // exception.
            Display viewerDisplay;
            try {
                viewerDisplay = viewer.getControl().getDisplay();
            } catch (SWTException e) {
                return;
            }
            viewerDisplay.syncExec(new Runnable() {
                @Override
                public void run() {
                    if (!viewer.getControl().isDisposed()) {
                        viewer.refresh();
                    }
                }
            });
        }

        public int getOwnerId() {
            return ownerId;
        }

    }

    protected T topLevelElement;

    @Override
    public void dispose() {
        if (topLevelElement != null) {
            removeChangeListeningAdapterFromEObject(topLevelElement);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (oldInput != null) {
            removeChangeListeningAdapterFromEObject((EObject) oldInput);
        }
        if (newInput != null) {
            topLevelElement = (T) newInput;
            topLevelElement.eAdapters().add(new EObjectChangedAdapter(viewer, hashCode()));
        }
    }

    private void removeChangeListeningAdapterFromEObject(EObject obj) {
        Iterables.removeIf(obj.eAdapters(), new Predicate<Adapter>() {
            @Override
            public boolean apply(Adapter arg0) {
                if (!(arg0 instanceof EObjectChangedAdapter)) {
                    return false;
                }
                return ((EObjectChangedAdapter) arg0).getOwnerId() == hashCode();
            }
        });
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (topLevelElement == null) {
            return ObjectArrays.newArray(Object.class, 0);
        }
        return getElements();
    }

    /**
     * @return All top level elements to be shown in the TreeView.
     */
    protected abstract Object[] getElements();

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

}
