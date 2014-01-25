/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.software;

import org.emftext.language.java.commons.Commentable;

import org.splevo.vpm.software.JavaSoftwareElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ja Mo PP Software Element</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A referencing object to the original JaMoPP software
 * element. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement#getJamoppElement
 * <em>Jamopp Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.vpm.software.softwarePackage#getJaMoPPSoftwareElement()
 * @model
 * @generated
 */
public interface JaMoPPSoftwareElement extends JavaSoftwareElement {
	/**
	 * Returns the value of the '<em><b>Jamopp Element</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
	 * JaMoPP element representing the software element itself. <!--
	 * end-model-doc -->
	 *
	 * @return the value of the '<em>Jamopp Element</em>' reference.
	 * @see #setJamoppElement(Commentable)
	 * @see org.splevo.jamopp.vpm.software.softwarePackage#getJaMoPPSoftwareElement_JamoppElement()
	 * @model required="true"
	 * @generated
	 */
	Commentable getJamoppElement();

	/**
	 * Sets the value of the '
	 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement#getJamoppElement
	 * <em>Jamopp Element</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @param value
	 *            the new value of the '<em>Jamopp Element</em>' reference.
	 * @see #getJamoppElement()
	 * @generated
	 */
	void setJamoppElement(Commentable value);

} // JaMoPPSoftwareElement
