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
package org.splevo.ui.sourceconnection.jdt;

import java.util.LinkedList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE.SharedImages;
import org.splevo.ui.sourceconnection.SourceEditorConnector;
import org.splevo.vpm.variability.Variant;

/**
 * A quick fix providing a link to an alternative implementation.
 */
class VartiantLinkQuickFix implements IMarkerResolution2 {

    private Variant variant;

    /**
     * Constructor to specify the quick fix.
     *
     * @param variant
     *            The variant to link with.
     */
    public VartiantLinkQuickFix(Variant variant) {
        this.variant = variant;
    }

    @Override
    public String getLabel() {
        return String.format("Alternative Variant: %s", variant.getVariantId());
    }

    /**
     * Jump to the alternative implementation.
     *
     * {@inheritDoc}
     */
    public void run(IMarker marker) {
        SourceEditorConnector.openVariant(variant, new LinkedList<String>());
    }

    @Override
    public String getDescription() {

        StringBuilder desc = new StringBuilder();
        desc.append("<b>Double click to jump to the alternative variant implementation</b>");
        for (String code : SourceEditorConnector.getCodeForVariant(variant)) {
            desc.append("<br/><br/><code>");
            desc.append(code);
            desc.append("</code>");
        }
        return desc.toString();
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(SharedImages.IMG_OPEN_MARKER);
    }
}