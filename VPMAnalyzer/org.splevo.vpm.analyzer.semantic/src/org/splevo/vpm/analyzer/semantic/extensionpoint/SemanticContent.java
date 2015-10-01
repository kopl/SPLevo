/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.extensionpoint;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Container to store textual code and comments.
 */
public class SemanticContent {
	private Set<String> code;
	private List<String> comments;

	/**
	 * The default constructor.
	 */
	public SemanticContent() {
		this.code = Sets.newHashSet();
		this.comments = new LinkedList<String>();
	}

	/**
	 * Gets the code.
	 *
	 * @return A {@link List} containing the words.
	 */
	public Collection<String> getCode() {
		return code;
	}

	/**
	 * Gets the comments.
	 *
	 * @return A {@link List} containing the words.
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * Adds a single word to the code container.
	 *
	 * @param codeString
	 *            The word as {@link String}.
	 */
	public void addCode(String codeString) {
		if (codeString == null || codeString.length() == 0) {
			return;
		}

		this.code.add(codeString);
	}

	/**
	 * Adds a single word to the comments container.
	 *
	 * @param commentString
	 *            The word as {@link String}.
	 */
	public void addComment(String commentString) {
		if (commentString == null || commentString.length() == 0) {
			return;
		}

		this.comments.add(commentString);
	}
}
