package org.splevo.jamopp.diffing.util;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;

/**
 * Utility class to handle JaMoPP model elements.
 */
public class JaMoPPModelUtil {

	/**
	 * Build the package path for a given element. Either the element itself is
	 * aware of it's name space or the closest aware container is used.
	 * 
	 * @param element
	 *            The element to get the package for.
	 * @return The identified name space or null if none could be found.
	 */
	public static String buildNamespacePath(EObject element) {

		while (element != null) {
			if (element instanceof NamespaceAwareElement) {

				String namespace = ((NamespaceAwareElement) element)
						.getNamespacesAsString();
				if (namespace.lastIndexOf('$') != -1) {
					namespace = namespace.substring(0,
							namespace.lastIndexOf('$'));
				}
				if (namespace.charAt(namespace.length() - 1) == '.') {
					namespace = namespace.substring(0, namespace.length() - 1);
				}
				return namespace;
			}

			element = element.eContainer();
		}

		return null;

	}

}
