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
package org.splevo.ui.sourceconnection.handler;

import java.util.List;
import java.util.Set;

import org.eclipse.ui.IWorkbenchPart;
import org.splevo.ui.sourceconnection.SourceEditorConnector;
import org.splevo.vpm.variability.Variant;

import com.google.common.collect.Lists;

/**
 * Action to open the implementing locations of a selected variant in the java editor.
 */
public class OpenSourceLocationHandler extends OpenSourceHandlerBase {

    @Override
    protected void handle(Set<Variant> variants, IWorkbenchPart sendingPart) {
        List<String> filesResetBefore = Lists.newArrayList();
        for (Variant v : variants) {
            SourceEditorConnector.openVariant(v, filesResetBefore);            
        }
    }
    
}
