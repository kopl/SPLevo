package org.splevo.vpm.analyzer.semantic.extensionpoint;

import org.splevo.vpm.software.SoftwareElement;

/**
 * This is the content provider interface for the semantic analyzer extension
 * point. All etensions registered to this point have to implement this class.
 * It allows the extraction of relevant code from a SoftwareElement.
 * 
 * @author Daniel Kojic
 * 
 */
public interface SemanticContentProvider {

	/**
	 * This method extracts semantic-relevant text fragments from a given
	 * {@link SoftwareElement}. Through a parameter it allows to either store
	 * comments or ignore them completely.
	 * 
	 * @param element
	 *            The {@link SoftwareElement} to extract the content from.
	 * @param matchComments
	 *            Indicates whether to store comments or not.
	 * @return A {@link SemanticContent} object that has all the
	 *         semantic-relevant text content.
	 * @throws UnsupportedSoftwareElementException
	 *             Thrown if the specific {@link SemanticContentProvider}
	 *             supports this {@link SoftwareElement}'s type.
	 */
	public SemanticContent getRelevantContent(SoftwareElement element,
			boolean matchComments) throws UnsupportedSoftwareElementException;
}
