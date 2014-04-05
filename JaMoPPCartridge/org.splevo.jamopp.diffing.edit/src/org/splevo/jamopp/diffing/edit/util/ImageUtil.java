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
 *******************************************************************************/
package org.splevo.jamopp.diffing.edit.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.provider.DiffItemProvider;
import org.eclipse.emf.edit.provider.ComposedImage;

/**
 * Utility class to handle images for the model elements.
 */
public class ImageUtil {

    /** The relative path to the insert overlay icon. */
    private static final String OVERLAY_ICON_INSERT = "overlays/insert";

    /** The relative path to the delete overlay icon. */
    private static final String OVERLAY_ICON_DELETE = "overlays/delete";

    /** The relative path to the change overlay icon. */
    private static final String OVERLAY_ICON_CHANGE = "overlays/change";

    /**
     * Compose two images to derive a combined icon.
     *
     * @param baseImage
     *            The base image to combine.
     * @param itemProvider
     *            The item provider to retrieve the resource locator.
     * @param imagePath
     *            The path to the image to be overlaid. (relative to /icons/)
     * @return The composed image object.
     */
    public static Object composeImage(Object baseImage, DiffItemProvider itemProvider, String imagePath) {
        List<Object> images = new ArrayList<Object>(2);
        images.add(baseImage);
        images.add(itemProvider.getResourceLocator().getImage(imagePath));
        return new ComposedImage(images);
    }

    /**
     * Overlay a provided image as an insert icon.
     *
     * @param baseImage
     *            The base image to generate an overlay for.
     * @param itemProvider
     *            The item provider to access the resources.
     * @return The prepared insert icon.
     */
    public static Object makeInsertIcon(Object baseImage, DiffItemProvider itemProvider) {
        return ImageUtil.composeImage(baseImage, itemProvider, OVERLAY_ICON_INSERT);
    }

    /**
     * Overlay a provided image as a change icon.
     *
     * @param baseImage
     *            The base image to generate an overlay for.
     * @param itemProvider
     *            The item provider to access the resources.
     * @return The prepared change icon.
     */
    public static Object makeChangeIcon(Object baseImage, DiffItemProvider itemProvider) {
        return ImageUtil.composeImage(baseImage, itemProvider, OVERLAY_ICON_CHANGE);
    }

    /**
     * Overlay a provided image as a delete icon.
     *
     * @param baseImage
     *            The base image to generate an overlay for.
     * @param itemProvider
     *            The item provider to access the resources.
     * @return The prepared change icon.
     */
    public static Object makeDeleteIcon(Object baseImage, DiffItemProvider itemProvider) {
        return ImageUtil.composeImage(baseImage, itemProvider, OVERLAY_ICON_DELETE);
    }

    /**
     * Create a change type specific overlay for the provided base image. This assumes the provided
     * object is of type Diff.
     *
     * @param baseImage
     *            The base image to overlay.
     * @param diff
     *            The diff element to get the kind for.
     * @param itemProvider
     *            The item provider to generate the overlay.
     * @return The prepared change image.
     */
    public static Object overlayChangeType(Object baseImage, Diff diff, DiffItemProvider itemProvider) {
        switch (diff.getKind()) {
        case ADD:
            return ImageUtil.makeInsertIcon(baseImage, itemProvider);

        case DELETE:
            return ImageUtil.makeDeleteIcon(baseImage, itemProvider);

        case CHANGE:
        case MOVE:
        default:
            return ImageUtil.makeChangeIcon(baseImage, itemProvider);
        }
    }

}
