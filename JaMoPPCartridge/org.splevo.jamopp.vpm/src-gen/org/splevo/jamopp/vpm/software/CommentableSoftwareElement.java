/**
 */
package org.splevo.jamopp.vpm.software;

import org.emftext.language.java.containers.CompilationUnit;

import org.splevo.vpm.software.JavaSoftwareElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Commentable Software Element</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getCompilationUnit
 * <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.jamopp.vpm.software.softwarePackage#getCommentableSoftwareElement()
 * @model
 * @generated
 */
public interface CommentableSoftwareElement extends JavaSoftwareElement {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.splevo.jamopp.vpm.software.softwarePackage#getCommentableSoftwareElement_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '
	 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Compilation Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Compilation Unit</em>' reference.
	 * @see #setCompilationUnit(CompilationUnit)
	 * @see org.splevo.jamopp.vpm.software.softwarePackage#getCommentableSoftwareElement_CompilationUnit()
	 * @model required="true"
	 * @generated
	 */
	CompilationUnit getCompilationUnit();

	/**
	 * Sets the value of the '
	 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getCompilationUnit
	 * <em>Compilation Unit</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Compilation Unit</em>' reference.
	 * @see #getCompilationUnit()
	 * @generated
	 */
	void setCompilationUnit(CompilationUnit value);

} // CommentableSoftwareElement
