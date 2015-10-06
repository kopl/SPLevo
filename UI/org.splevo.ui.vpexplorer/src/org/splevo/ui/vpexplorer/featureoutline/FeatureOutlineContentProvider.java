/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/

package org.splevo.ui.vpexplorer.featureoutline;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.ui.vpexplorer.featureoutline.content.GetVariationPointGroupChildren;
import org.splevo.ui.vpexplorer.featureoutline.content.GetVariationPointGroupChildrenBase;
import org.splevo.ui.vpexplorer.featureoutline.content.VariantWrapper;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * This TreeNodeContentProvider expects a VPExplorerContent as input and will return its groupings
 * and variation points.
 * 
 */
public class FeatureOutlineContentProvider extends TreeNodeContentProvider {

    private static Logger logger = Logger.getLogger(FeatureOutlineContentProvider.class);
    
    private GetVariationPointGroupChildren vpgc = new GetVariationPointGroupChildrenBase();    

    @Override
    public Object[] getElements(Object inputElement) {
        return this.getChildren(inputElement);
    }

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    @Override
    public Object[] getChildren(Object parentElement) {

        if (parentElement instanceof VPExplorerContent) {
            return getChildren((VPExplorerContent) parentElement);

        } else if (parentElement instanceof VariationPointGroup) {
            return vpgc.getChildren((VariationPointGroup) parentElement);

        } else if (parentElement instanceof VariationPoint) {
            return getChildren((VariationPoint) parentElement);

        } else if (parentElement instanceof VariantWrapper) {
            return ((VariantWrapper) parentElement).getVariationPoints().toArray();
        } else {
            logger.warn("Unhandled Parent Element: " + parentElement.getClass().getSimpleName());
        }

        return new Object[0];
    }

    private Object[] getChildren(VPExplorerContent parentElement) {
        if (parentElement.getVpm() != null) {
            return parentElement.getVpm().getVariationPointGroups().toArray();
        } else {
            return new Object[0];
        }
    }


    private Object[] getChildren(VariationPoint parentElement) {
        return new Object[0];
    }
    
    @Override
    public Object getParent(Object element) {
        if (element instanceof VPExplorerContent) {
            return getParent((VPExplorerContent) element);

        } else if (element instanceof VariationPointGroup) {
            return getParent((VariationPointGroup) element);

        } else if (element instanceof VariationPoint) {
            return getParent((VariationPoint) element);

        } else {
            logger.warn("Unhandled Element in method getParent: " + element.getClass().getSimpleName());
            return null;
        }
    }

    private Object getParent(VPExplorerContent element) {
        return null;
    }

    private Object getParent(VariationPointGroup element) {
        return null;
    }

    private Object getParent(VariationPoint element) {
        return element.getGroup();
    }
    
    /**
     * Sets the context of the provider.
     * @param vpgc the context that is set
     */
    public void setGetChildren(GetVariationPointGroupChildren vpgc) {
        this.vpgc = vpgc;
    }
}
