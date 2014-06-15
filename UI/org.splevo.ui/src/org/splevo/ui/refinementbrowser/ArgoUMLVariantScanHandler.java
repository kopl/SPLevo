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
package org.splevo.ui.refinementbrowser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

/**
 * Action to scan the selected variation points for indicators which feature the variant
 * implementing software elements belong to.
 */
public class ArgoUMLVariantScanHandler extends AbstractHandler {

    private static final String MARKER = "//@#$LPS-";
    private static final int MARKER_LENGTH = MARKER.length();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            List<VariationPoint> vps = Lists.newArrayList();
            for (Object selectedItem : selection.toList()) {
                if (selectedItem instanceof VariationPoint) {
                    vps.add((VariationPoint) selectedItem);
                }
            }

            scanForIncludedFeatures(vps);

        }
        return null;
    }

    private void scanForIncludedFeatures(List<VariationPoint> vps) {

        Multiset<String> identifiedFeatues = HashMultiset.create();
        List<String> errors = Lists.newArrayList();

        for (VariationPoint vp : vps) {

            Set<SoftwareElement> elements = getNotLeadingImplementingElements((VariationPoint) vp);
            if (elements.size() == 0) {
                identifiedFeatues.add("{NONE}");
            }
            for (SoftwareElement element : elements) {

                SourceLocation sourceLocation = element.getSourceLocation();
                String path = sourceLocation.getFilePath();
                List<String> lines = null;
                try {
                    lines = FileUtils.readLines(new File(path));

                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                int markerLineIndex = getMarkerLineIndex(vp, sourceLocation, lines);
                if (markerLineIndex == -1) {
                    errors.add("No marker found for " + path.substring(path.length() - 20));
                    continue;
                }

                String featureId = getFeatureId(lines, markerLineIndex);

                if (isMarkerLine(lines, markerLineIndex - 1)) {
                    featureId = getFeatureId(lines, markerLineIndex - 1) + " + " + featureId;
                } else if (isMarkerLine(lines, markerLineIndex + 1)) {
                    featureId += " + " + getFeatureId(lines, markerLineIndex + 1);
                }

                identifiedFeatues.add(featureId);
            }
        }

        if (errors.size() > 0) {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Marker Detection Errors", Joiner.on("\n")
                    .join(errors));
        }

        StringBuilder message = new StringBuilder();
        message.append("VP Count Total: ");
        message.append(vps.size());
        for (String featureId : identifiedFeatues.elementSet()) {
            message.append("\n");
            message.append(identifiedFeatues.count(featureId));
            message.append(" x ");
            message.append(featureId);
        }
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info", message.toString());
    }

    private String getFeatureId(List<String> lines, int lineIndex) {
        String line = lines.get(lineIndex).trim();
        return line.substring(MARKER_LENGTH, line.indexOf(":", MARKER_LENGTH));
    }

    private int getMarkerLineIndex(VariationPoint vp, SourceLocation sourceLocation, List<String> lines) {
        int elementIdentation = getIdentation(lines, sourceLocation.getStartLine() - 1);

        int markerLineIndex = -1;
        if (vp.getVariants().size() == 1) {
            for (int lineIndex = sourceLocation.getStartLine(); lineIndex >= 0; lineIndex--) {
                if (isMarkerLine(lines, lineIndex) && getIdentation(lines, lineIndex) == elementIdentation) {
                    markerLineIndex = lineIndex;
                    break;
                }
            }
        } else {
            for (int lineIndex = sourceLocation.getStartLine(); lineIndex < lines.size(); lineIndex++) {
                if (isMarkerLine(lines, lineIndex)) {
                    markerLineIndex = lineIndex;
                    break;
                }
            }
        }
        return markerLineIndex;
    }

    private int getIdentation(List<String> lines, int index) {
        String line = lines.get(index);
        int identation = line.length() - line.replaceFirst("^\\s*", "").length();
        return identation;
    }

    /**
     * Check a line if it matches the feature annotation used by couto et al.
     *
     * They used different comments for different information.<br>
     * The one of interest here includes the granularity type of the feature specific code:<br>
     * <code>//@#$LPS-COGNITIVE:GranularityType:MethodCall</code>
     *
     * @param lines
     *            The list of lines.
     * @param lineIndex
     *            The index of the line to check.
     * @return True if the line contains a marker of interest.
     */
    private boolean isMarkerLine(List<String> lines, int lineIndex) {
        return lines.get(lineIndex).trim().matches("//@#\\$LPS-.*:GranularityType:.*");
    }

    private Set<SoftwareElement> getNotLeadingImplementingElements(VariationPoint vp) {
        Set<SoftwareElement> elements = Sets.newLinkedHashSet();
        for (Variant v : vp.getVariants()) {
            if (v.getLeading()) {
                continue;
            }
            EList<SoftwareElement> implementingElements = v.getImplementingElements();
            elements.addAll(implementingElements);
        }
        return elements;
    }
}
