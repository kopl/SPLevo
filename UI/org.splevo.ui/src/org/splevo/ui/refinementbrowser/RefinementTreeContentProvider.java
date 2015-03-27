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
import org.splevo.ui.listeners.EObjectChangedListener;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.ObjectArrays;

/**
 * The Content provider for the refinement tree.
 *
 * In the current version, the tree consists of a single level only. A hierarchy might be used later
 * on to present related refinements.
 */
public class RefinementTreeContentProvider implements ITreeContentProvider {

    /**
     * Adapter for EObjects that refreshes a given viewer as soon as a refinement has been changed.
     */
    private static class EObjectChangedAdapter extends EObjectChangedListener {
        
        private final Viewer viewer;
        
        public EObjectChangedAdapter(Viewer viewer) {
            super(new Predicate<Notification>() {
                @Override
                public boolean apply(Notification arg0) {
                    return arg0.getNotifier() instanceof Refinement;
                } });
            this.viewer = viewer;
        }

        @Override
        protected void reactOnChange(Notification notification) {
            if (!viewer.getControl().isDisposed()) {
                viewer.getControl().getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        viewer.refresh();
                    }
                });                    
            }
        }
 
    }
    
    /** The refinements to be performed. */
    private RefinementModel refinementModel;

    @Override
    public void dispose() {
        if (refinementModel != null) {
            removeChangeListeningAdapterFromEObject(refinementModel);            
        }
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (oldInput != null) {
            removeChangeListeningAdapterFromEObject((EObject) oldInput);            
        }
        if (newInput != null) {
            refinementModel = (RefinementModel) newInput;
            refinementModel.eAdapters().add(new EObjectChangedAdapter(viewer));            
        }
    }
    
    private void removeChangeListeningAdapterFromEObject(EObject obj) {
        Iterables.removeIf(obj.eAdapters(), new Predicate<Adapter>() {
            @Override
            public boolean apply(Adapter arg0) {
                return arg0 instanceof EObjectChangedAdapter;
            }
        });
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (refinementModel == null) {
            return ObjectArrays.newArray(Object.class, 0);
        }
        return refinementModel.getRefinements().toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Refinement) {
            Refinement refinement = (Refinement) parentElement;
            return refinement.getSubRefinements().toArray();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Refinement) {
            Refinement refinement = (Refinement) element;
            return refinement.getParent();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

}
