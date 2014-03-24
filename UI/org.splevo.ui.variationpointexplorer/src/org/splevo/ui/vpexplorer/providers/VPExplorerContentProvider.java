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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

public class VPExplorerContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof VariationPoint) {
			return ((VariationPoint) parentElement).getVariants().toArray();
		} else if (parentElement instanceof Variant) {
			EList<SoftwareElement> implementingElements = ((Variant) parentElement).getImplementingElements();
			List<SourceLocation> locations = new LinkedList<SourceLocation>();
			for (SoftwareElement element : implementingElements) {
				locations.add(element.getSourceLocation());
			}
			return locations.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Variant) {
			return ((Variant)element).getVariationPoint();
		} else if (element instanceof SourceLocation) {
			return ((SourceLocation)element).eContainer();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof VariationPoint) {
			return true;
		} else if ( element instanceof Variant) {
			return true;
		}
		return false;
	}

}
