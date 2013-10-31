package org.splevo.modisco.java.vpm.semantic.contentprovider;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.software.SoftwareElement;

/**
 * This is the content provider for Modisco-specific SoftwareElements.
 * 
 * @author Daniel Kojic
 * 
 */
public class MoDiscoSemanticContentProvider implements SemanticContentProvider {

	@Override
	public SemanticContent getRelevantContent(SoftwareElement element,
			boolean matchComments) throws UnsupportedSoftwareElementException {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (!(element instanceof MoDiscoJavaSoftwareElement)) {
			throw new UnsupportedSoftwareElementException(element);
		}

		return getContentFromChildren(element, matchComments);
	}

	/**
	 * Extracts the content from all children of the specified root element and
	 * stores that in a SemanticContainer.
	 * 
	 * @param element
	 *            The root element.
	 * @param matchComments
	 *            Indicates whether to extract comments or ignore them.
	 * @return The {@link SemanticContent} containing the relevant content.
	 */
	private SemanticContent getContentFromChildren(SoftwareElement element,
			boolean matchComments) {
		SemanticContent content = new SemanticContent();

		ASTNode astNode = ((MoDiscoJavaSoftwareElement) element).getAstNode();

		if(astNode == null) {
			return content;
		}
		
		TreeIterator<EObject> allContents = EcoreUtil.getAllContents(astNode
				.eContents());

		while (allContents.hasNext()) {
			EObject next = allContents.next();

			if (!isComment(next)) {
				String text = getRelevantTextFromElement(next);
				content.addCode(text);
			} else if (matchComments) {
				String text = getRelevantTextFromElement(next);
				content.addComment(text);
			}
		}
		return content;
	}

	/**
	 * Checks whether an element is a comment or not.
	 * 
	 * @param element The element.
	 * @return True if comment; false otherwise.
	 */
	private boolean isComment(EObject element) {
		return element instanceof Comment;
	}

	/**
	 * extracts relevant text from a given object.
	 * 
	 * @param eObject The object to extract the information from.
	 * @return The extracted text.
	 */
	private String getRelevantTextFromElement(EObject eObject) {
		if (eObject instanceof NamedElement) {
			return ((NamedElement) eObject).getName();
		}

		if (eObject instanceof TextElement) {
			return ((TextElement) eObject).getText();
		}

		if (eObject instanceof Comment) {
			return ((Comment) eObject).getContent();
		}

		return null;
	}

}
