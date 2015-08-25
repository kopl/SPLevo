/**
 */
package org.splevo.jamopp.vpm.software;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.splevo.vpm.software.JavaSoftwareElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ja Mo PP Java Software Element</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.splevo.jamopp.vpm.software.softwarePackage#getJaMoPPJavaSoftwareElement()
 * @model abstract="true"
 * @generated
 */
public interface JaMoPPJavaSoftwareElement extends JavaSoftwareElement {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" required="true"
     * @generated
     */
    Commentable getJamoppElement();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel body='return org.splevo.jamopp.util.JaMoPPElementUtil.getLabel(resolveJaMoPPElement());'"
     * @generated
     */
    String getLabel();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel body='return org.splevo.jamopp.util.JaMoPPElementUtil.getName(resolveJaMoPPElement());'"
     * @generated
     */
    String getName();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model kind="operation"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return resolveJaMoPPElement();'"
     * @generated
     */
    EObject getWrappedElement();
} // JaMoPPJavaSoftwareElement
