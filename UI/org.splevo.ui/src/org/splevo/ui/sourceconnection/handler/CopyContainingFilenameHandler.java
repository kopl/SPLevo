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
package org.splevo.ui.sourceconnection.handler;

import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Sets;

/**
 * Action to copy the name of the containing file to the clipboard.
 */
public class CopyContainingFilenameHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection == null || !(curSelection instanceof IStructuredSelection)) {
            return null;
        }

        IStructuredSelection selection = (IStructuredSelection) curSelection;
        LinkedHashMultiset<String> filePaths = collectFilePaths(selection);
        String clipboardContent = buildClipboardContent(filePaths);
        copyToClipboard(clipboardContent);

        return null;
    }

    private void copyToClipboard(String clipboardContent) {
        Display display = Display.getCurrent();
        Clipboard clipboard = new Clipboard(display);
        TextTransfer textTransfer = TextTransfer.getInstance();
        Transfer[] transfers = new Transfer[] { textTransfer };
        Object[] data = new Object[] { clipboardContent };
        clipboard.setContents(data, transfers);
        clipboard.dispose();
    }

    private String buildClipboardContent(LinkedHashMultiset<String> filePaths) {
        StringBuilder copyContent = new StringBuilder();
        for (String file : filePaths.elementSet()) {
            copyContent.append(FilenameUtils.getName(file));
            copyContent.append(",");
            copyContent.append(filePaths.count(file));
            copyContent.append("\r\n");
        }
        return copyContent.toString();
    }

    /**
     * Collect the file paths of the variation points of the selected elements.
     *
     * Each variation point is registered only once, even if several variants of the same variation
     * point have been selected.
     *
     * @param selection
     *            The current selection
     * @return The multi-set of variation point file paths.
     */
    private LinkedHashMultiset<String> collectFilePaths(IStructuredSelection selection) {
        LinkedHashMultiset<String> filePaths = LinkedHashMultiset.create();

        Set<VariationPoint> vps = Sets.newLinkedHashSet();

        for (Object selectedItem : selection.toList()) {
            if (selectedItem instanceof Variant) {
                vps.add(((Variant) selectedItem).getVariationPoint());
            } else if (selectedItem instanceof VariationPoint) {
                vps.add((VariationPoint) selectedItem);
            }
        }

        for (VariationPoint vp : vps) {
            if (vp != null) {
                filePaths.add(getFile(vp));
            }
        }

        return filePaths;
    }

    private String getFile(VariationPoint vp) {
        return vp.getLocation().getSourceLocation().getFilePath();
    }
}
