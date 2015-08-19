/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Anton Huck
 *******************************************************************************/
package org.splevo.ui.refinementbrowser.listener;

import java.util.Set;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector.ConnectionMethod;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Sets;


/**
 * Listener to update the differences view panel.
 */
public class RefinementDiffSelectionListener implements
		ISelectionChangedListener {

	private Browser refinementSourceView;
	private IWorkbenchPartSite site;
	
	/**
	 *  Default Constructor
	 * @param target The target element, to place result in.
	 * @param site current workbenchPartSite
	 */
	public RefinementDiffSelectionListener(Browser target, IWorkbenchPartSite site) {
		refinementSourceView = target;
		this.site = site;
	}
		
    @Override
    public void selectionChanged(SelectionChangedEvent event) {

        IEditorPart activeEditor = site.getPage().getActiveEditor();
        VPMRefinementBrowser splevoProjectEditor = (VPMRefinementBrowser) activeEditor;
        SPLevoProject splevoProject = splevoProjectEditor.getSPLevoProjectEditor().getSplevoProject();

        Set<Variant> variants = Sets.newLinkedHashSet();
        Set<VariationPoint> vps = Sets.newLinkedHashSet();
        for (Object selectedElement : ((StructuredSelection) event.getSelection()).toList()) {
            if (selectedElement instanceof VariationPoint) {
                variants.addAll(((VariationPoint) selectedElement).getVariants());
                vps.add((VariationPoint) selectedElement);
            } else if (selectedElement instanceof Variant) {
                variants.add((Variant) selectedElement);
            }
        }

        // create unified difference with UnifiedDiffConnector
        UnifiedDiffConnector diffConnector = new UnifiedDiffConnector(splevoProject, variants);
        diffConnector.connect(ConnectionMethod.BY_CHUNKS);
        refinementSourceView.setText(diffConnector.getUnifiedText());
		}
	}
