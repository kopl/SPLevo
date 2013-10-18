package org.splevo.vpm.software;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.software.SourceLocation#getFilePath <em>File Path</em>}</li>
 *   <li>{@link org.splevo.vpm.software.SourceLocation#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link org.splevo.vpm.software.SourceLocation#getStartPosition <em>Start Position</em>}</li>
 *   <li>{@link org.splevo.vpm.software.SourceLocation#getEndLine <em>End Line</em>}</li>
 *   <li>{@link org.splevo.vpm.software.SourceLocation#getEndPosition <em>End Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation()
 * @model
 * @generated
 */
public interface SourceLocation extends EObject {
    /**
     * Returns the value of the '<em><b>File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The path to the file containing the software element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>File Path</em>' attribute.
     * @see #setFilePath(String)
     * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation_FilePath()
     * @model
     * @generated
     */
    String getFilePath();

    /**
     * Sets the value of the '{@link org.splevo.vpm.software.SourceLocation#getFilePath <em>File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Path</em>' attribute.
     * @see #getFilePath()
     * @generated
     */
    void setFilePath(String value);

    /**
     * Returns the value of the '<em><b>Start Line</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The line in which the software element's code representation starts.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Start Line</em>' attribute.
     * @see #setStartLine(int)
     * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation_StartLine()
     * @model
     * @generated
     */
    int getStartLine();

    /**
     * Sets the value of the '{@link org.splevo.vpm.software.SourceLocation#getStartLine <em>Start Line</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Line</em>' attribute.
     * @see #getStartLine()
     * @generated
     */
    void setStartLine(int value);

    /**
     * Returns the value of the '<em><b>Start Position</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The character position within a line at which the software element's code representation starts.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Start Position</em>' attribute.
     * @see #setStartPosition(int)
     * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation_StartPosition()
     * @model
     * @generated
     */
    int getStartPosition();

    /**
     * Sets the value of the '{@link org.splevo.vpm.software.SourceLocation#getStartPosition <em>Start Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Position</em>' attribute.
     * @see #getStartPosition()
     * @generated
     */
    void setStartPosition(int value);

    /**
     * Returns the value of the '<em><b>End Line</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The line in which the software element's code representation ends.
     * <!-- end-model-doc -->
     * @return the value of the '<em>End Line</em>' attribute.
     * @see #setEndLine(int)
     * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation_EndLine()
     * @model
     * @generated
     */
    int getEndLine();

    /**
     * Sets the value of the '{@link org.splevo.vpm.software.SourceLocation#getEndLine <em>End Line</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Line</em>' attribute.
     * @see #getEndLine()
     * @generated
     */
    void setEndLine(int value);

    /**
     * Returns the value of the '<em><b>End Position</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The character position within a line at which the software element's code representation ends.
     * <!-- end-model-doc -->
     * @return the value of the '<em>End Position</em>' attribute.
     * @see #setEndPosition(int)
     * @see org.splevo.vpm.software.SoftwarePackage#getSourceLocation_EndPosition()
     * @model
     * @generated
     */
    int getEndPosition();

    /**
     * Sets the value of the '{@link org.splevo.vpm.software.SourceLocation#getEndPosition <em>End Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Position</em>' attribute.
     * @see #getEndPosition()
     * @generated
     */
    void setEndPosition(int value);

} // SourceLocation
