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
package org.splevo.ui.wizards.vpmanalysis;

import org.eclipse.jface.viewers.LabelProvider;
import org.splevo.vpm.analyzer.VPMAnalyzer;

/**
 * Label provider for vpm refinement analyzers returning the name of the analyzer.
 */
public class VPMAnalyzerLabelProvider extends LabelProvider {

	/**
	 * Get the text for the label.
	 * @param element The object which is expected to be an VPMRefinementAnalyzer
	 * @return The name of the vpm refinement analyzer as the label to present..
	 */
	@Override
	public String getText(Object element) {
		return ((VPMAnalyzer) element).getName();
	}
}
