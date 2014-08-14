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
package org.splevo.jamopp.vpm.analyzer.clonedchange;

import org.emftext.language.java.commons.Commentable;

/**
 * The clone detector detects clones between Commentables.
 * 
 */
public class CloneDetector {

	/**
	 * Checks whether commentable1 and commentable2 are clones of each other.
	 * 
	 * @param commentable1
	 *            The first Commentable to check.
	 * @param commentable2
	 *            The second Commentable to check.
	 * @return true if the commentables are clones of each other.
	 */
	public boolean isClone(Commentable commentable1, Commentable commentable2) {
		// TODO: Add actual implementation
		return false;
	}

}
