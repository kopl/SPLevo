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
package org.splevo.jamopp.algorithm.clones.baxtor.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.emftext.language.java.commons.Commentable;
import org.junit.Test;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetector;

public class CloneDetectorTest {

	@Test
	public void testSelfIsClone() {
		Commentable commentable = mock(Commentable.class);
		CloneDetector detector = new CloneDetector();
		boolean result = detector.isClone(commentable, commentable);
		assertThat("an commentable is a clone of itself", result, is(true));
	}

}
