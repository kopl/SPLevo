/**
 *
 */
package org.splevo.jamopp.vpm.analyzer.semantic;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.software.SoftwareElement;

import com.google.common.collect.Lists;

/**
 * Content provider for semantic information from JaMoPP software elements.
 */
public class JaMoPPSemanticContentProvider implements SemanticContentProvider {


	@Override
	public SemanticContent getRelevantContent(SoftwareElement element,
			boolean matchComments) throws UnsupportedSoftwareElementException {

		if (element == null) {
			throw new IllegalArgumentException();
		}

		if (!(element instanceof JaMoPPSoftwareElement)) {
			throw new UnsupportedSoftwareElementException(element);
		}

		return getContentFromChildren((JaMoPPSoftwareElement) element, matchComments);
	}

	/**
	 * Extracts the content from all children of the specified root element and
	 * stores that in a SemanticContainer.
	 *
	 * @param softwareElement
	 *            The root element.
	 * @param matchComments
	 *            Indicates whether to extract comments or ignore them.
	 * @return The {@link SemanticContent} containing the relevant content.
	 */
	private SemanticContent getContentFromChildren(JaMoPPSoftwareElement softwareElement,
			boolean matchComments) {
		SemanticContent content = new SemanticContent();

		Commentable element = softwareElement.getJamoppElement();

		if(element == null) {
			return content;
		}

		List<EObject> childElements = Lists.newArrayList(element.eAllContents());
		for (EObject child : childElements) {

			if(!(child instanceof Commentable)) {
				continue;
			}

			Commentable commentableChild = (Commentable) child;
			String text = getRelevantTextFromElement(commentableChild);
			content.addCode(text);

			if (matchComments) {
				StringBuilder builder = new StringBuilder();
				for(String comment : commentableChild.getComments()) {
					builder.append(comment);
				}
				content.addComment(builder.toString());
			}

		}
		return content;
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

		return "";
	}

}