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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.splevo.jamopp.vpm.software.softwarePackage
 * @generated
 */
public interface softwareFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	softwareFactory eINSTANCE = org.splevo.jamopp.vpm.software.impl.softwareFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Ja Mo PP Software Element</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Ja Mo PP Software Element</em>'.
	 * @generated
	 */
	JaMoPPSoftwareElement createJaMoPPSoftwareElement();

	/**
	 * Returns a new object of class '<em>Commentable Software Element</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Commentable Software Element</em>'.
	 * @generated
	 */
	CommentableSoftwareElement createCommentableSoftwareElement();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	softwarePackage getsoftwarePackage();

} // softwareFactory
