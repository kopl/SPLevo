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
package org.splevo.ui.vpexplorer.providers;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.ui.vpexplorer.treeitems.CULocationNameTreeItem;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.HashMultimap;

/**
 * The ContentProvider for the VPExplorer.
 */
public class VPExplorerContentProvider extends TreeNodeContentProvider {

    private static Logger logger = Logger.getLogger(VPExplorerContentProvider.class);
    private HashMultimap<CULocationNameTreeItem, VariationPoint> cuLocations = HashMultimap.create();

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        logger.info("New VPM Model received: " + newInput);
    }

    @Override
    public Object[] getChildren(Object parentElement) {

        if (parentElement instanceof VPExplorerContent) {
            VPExplorerContent vpContent = (VPExplorerContent) parentElement;
            if (vpContent.getVpm() != null) {
                populateCULocations(vpContent);
                return cuLocations.keySet().toArray();
            } else {
                return new Object[0];
            }

        } else if (parentElement instanceof CULocationNameTreeItem) {
            return cuLocations.get((CULocationNameTreeItem) parentElement).toArray();

        } else if (parentElement instanceof VariationPointModel) {
            VariationPointModel vpm = (VariationPointModel) parentElement;
            return vpm.getVariationPointGroups().toArray();

        } else if (parentElement instanceof VariationPointGroup) {
            VariationPointGroup group = (VariationPointGroup) parentElement;
            return group.getVariationPoints().toArray();

        } else if (parentElement instanceof VariationPoint) {
            return ((VariationPoint) parentElement).getVariants().toArray();

        } else if (parentElement instanceof Variant) {
            EList<SoftwareElement> implementingElements = ((Variant) parentElement).getImplementingElements();
            return implementingElements.toArray();

        } else {
            logger.warn("Unhandled Parent Element: " + parentElement.getClass().getSimpleName());
        }
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof VariationPoint) {
            return ((VariationPoint) element).getLocation();
        } else if (element instanceof Variant) {
            return ((Variant) element).getVariationPoint();
        } else if (element instanceof SoftwareElement) {
            return ((SoftwareElement) element).eContainer();
        } else if (element instanceof SourceLocation) {
            // FIXME that's not the parent within the tree but calculated on the fly!!
            return ((SourceLocation) element).eContainer();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof CULocationNameTreeItem) {
            return cuLocations.containsKey((CULocationNameTreeItem) element);
        } else if (element instanceof Variant) {
            return false;   // We currently do not expand the tree below Variants.
        }
        return getChildren(element).length > 0;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return this.getChildren(inputElement);
    }

    /**
     * Populates the CU locations map with all variation points and the location names of their
     * corresponding CUs.
     * 
     * @param vpContent
     *            the VPcontent to be used as population source
     */
    private void populateCULocations(VPExplorerContent vpContent) {
        EList<VariationPointGroup> vpGroups = vpContent.getVpm().getVariationPointGroups();
        String name = null;
        for (VariationPointGroup vpGroup : vpGroups) {
            EList<VariationPoint> vps = vpGroup.getVariationPoints();
            for (VariationPoint vp : vps) {
                SoftwareElement location = vp.getLocation();
                if (location instanceof JaMoPPSoftwareElement) {
                    name = ((JaMoPPSoftwareElement) location).getJamoppElement().getContainingCompilationUnit()
                            .getName();
                } else {
                    logger.error("Unexpected type of software element: " + location.getClass().getSimpleName());
                    return;
                }
                cuLocations.put(new CULocationNameTreeItem(name), vp);
            }
        }
    }

}
