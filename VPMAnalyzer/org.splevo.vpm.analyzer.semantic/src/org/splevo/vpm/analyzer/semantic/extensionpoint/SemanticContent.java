package org.splevo.vpm.analyzer.semantic.extensionpoint;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a container class to store textual code and comments.
 * 
 * @author Daniel Kojic
 * 
 */
public class SemanticContent {
	private List<String> code;
	private List<String> comments;

	/**
	 * The default constructor.
	 */
	public SemanticContent() {
		this.code = new LinkedList<String>();
		this.comments = new LinkedList<String>();
	}

	/**
	 * Gets the code.
	 * 
	 * @return A {@link List} containing the words.
	 */
	public List<String> getCode() {
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
