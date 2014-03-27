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
 *    Benjamin Klatt
 *******************************************************************************/

package org.splevo.ui.vpexplorer.providers;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * This LabelProvider provides Labels for elements of a VPM.
 */
public class VPExplorerLabelProvider extends LabelProvider {

    private static Logger logger = Logger.getLogger(VPExplorerLabelProvider.class);

    @Override
    public Image getImage(Object element) {

        if (element instanceof File) {
            File file = (File) element;
            if (file.isDirectory()) {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
            } else if (file.getName().endsWith(".java")) {
                return JavaUI.getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CUNIT);
            } else {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
            }
        } else if (element instanceof VPExplorerContent) {
            return null;
        }

        Image image = getItemProviderImage(element);
        if (image != null) {
            return image;
        }

        logger.warn("Unsupported tree node element");
        return null;
    }

    private Image getItemProviderImage(Object element) {
        ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        return labelProvider.getImage(element);
    }

    @Override
    public String getText(Object element) {

        if (element instanceof VariationPointGroup) {
            return "Variation Point Group: " + ((VariationPointGroup) element).getGroupId();
        } else if (element instanceof VariationPoint) {
            return buildVariationPointLabel((VariationPoint) element);
        } else if (element instanceof Variant) {
            return "Variant: " + ((Variant) element).getVariantId();
        } else if (element instanceof SoftwareElement) {
            return ((SoftwareElement) element).getLabel();
        } else if (element instanceof File) {
            File file = (File) element;
            String label = file.getName();
            if (label != null && label.length() > 0) {
                return label;
            } else {
                return file.getPath();
            }

        } else if (element instanceof VPExplorerContent) {
            return null;
        }
        logger.warn("Unsupported tree node element");
        return null;
    }

    /**
     * Builds the variation point label.
     *
     * @param variationPoint
     *            the element
     * @return the string
     */
    private String buildVariationPointLabel(final VariationPoint variationPoint) {
        final SoftwareElement softwareElement = variationPoint.getLocation();
        return String.format("VariationPoint in %s", softwareElement.getLabel());
    }

}
