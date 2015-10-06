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
 *    Stephan Seifermann
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
import org.splevo.ui.commons.util.LabelUtils;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.ui.vpexplorer.featureoutline.content.VariantWrapper;
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
            return getImageForFile(file);
        } else if (element instanceof VPExplorerContent) {
            return null;
        } else if (element instanceof FileWrapper) {
            File file = ((FileWrapper) element).getFile();
            return getImageForFile(file);
        }
        if (element instanceof VariantWrapper) {
            element = ((VariantWrapper) element).getVariant();
        }
        Image image = getItemProviderImage(element);
        if (image != null) {
            return image;
        }

        logger.warn("Unsupported tree node element");
        return null;
    }

    private Image getImageForFile(File file) {
        if (file.isDirectory()) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        } else if (file.getName().endsWith(".java")) {
            return JavaUI.getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CUNIT);
        } else {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        }
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
            return LabelUtils.getItemProviderText(element);
        } else if (element instanceof VariationPoint) {
            return LabelUtils.getItemProviderText(element);
        } else if (element instanceof Variant) {
            return "Variant: " + ((Variant) element).getId();
        } else if (element instanceof VariantWrapper) {
            return "Variant: " + ((VariantWrapper) element).getVariant().getId();
        } else if (element instanceof SoftwareElement) {        
            return ((SoftwareElement) element).getLabel();
        } else if (element instanceof File) {
            File file = (File) element;
            String label = file.getName();
            if (label != null && label.length() > 0) {

                int numberOfVPs = VPExplorerContentProvider.getVPInFile(file).size();
                if (numberOfVPs > 0) {
                    return String.format("%s [%s VP]", label, numberOfVPs);
                } else {
                    return label;
                }

            } else {
                return file.getPath();
            }

        } else if (element instanceof FileWrapper) {
            File file = ((FileWrapper) element).getFile();
            String label = file.getName();
            if (label != null && label.length() > 0) {

                // Decide whether the number of VPs should be displayed. This would make another
                // public static method in VPExplorerContentProvider necessary.
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

}
