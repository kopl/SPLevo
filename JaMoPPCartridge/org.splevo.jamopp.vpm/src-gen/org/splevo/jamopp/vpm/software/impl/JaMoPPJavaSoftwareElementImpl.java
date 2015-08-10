/**
 */
package org.splevo.jamopp.vpm.software.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.resource.java.IJavaLocationMap;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaDevNullLocationMap;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;

import com.google.common.collect.Maps;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ja Mo PP Java Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public abstract class JaMoPPJavaSoftwareElementImpl extends MinimalEObjectImpl.Container implements
        JaMoPPJavaSoftwareElement {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPJavaSoftwareElementImpl.class);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected JaMoPPJavaSoftwareElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return softwarePackage.Literals.JA_MO_PP_JAVA_SOFTWARE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated not
     */
    public abstract Commentable getJamoppElement();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated not
     */
    public abstract Commentable resolveJaMoPPElement();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLabel() {
        return org.splevo.jamopp.util.JaMoPPElementUtil.getLabel(resolveJaMoPPElement());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return org.splevo.jamopp.util.JaMoPPElementUtil.getName(resolveJaMoPPElement());
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public SourceLocation getSourceLocation() {

        Commentable element = resolveJaMoPPElement();

        if (element == null) {
            LOGGER.warn("Could not resolve JaMoPPElement " + toString() + ".");
            return null;
        }

        if (!(element.eResource() instanceof JavaResource)) {
            return null;
        }

        JavaResource resource = (JavaResource) element.eResource();
        IJavaLocationMap locationMap = resource.getLocationMap();
        if (locationMap instanceof JavaDevNullLocationMap) {
            return reloadLocation(element, resource);
        } else {
            return buildLocation(element, resource);
        }
    }

    private SourceLocation buildLocation(Commentable element, JavaResource resource) {
        SourceLocation location = SoftwareFactory.eINSTANCE.createSourceLocation();
        location.setFilePath(resource.getURI().toFileString());

        IJavaLocationMap locationMap = resource.getLocationMap();
        location.setStartLine(locationMap.getLine(element));
        location.setStartPosition(locationMap.getCharStart(element));
        location.setEndPosition(locationMap.getCharEnd(element));
        return location;
    }

    /**
     * To get source locations for resources previously loaded with disabled location maps, try to
     * reload the according resource, find the current element in it and return the according source
     * location.
     * 
     * @param element
     *            The element to get the location info for.
     * @return The source location map for the element.
     */
    private SourceLocation reloadLocation(Commentable element, JavaResource resource) {

        JavaResource reloadedResource = (JavaResource) resource.getResourceSet().createResource(resource.getURI());
        LinkedHashMap<String, Object> options = Maps.newLinkedHashMap();
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        try {
            reloadedResource.load(options);
        } catch (IOException e) {
            return null;
        }
        String uriFragment = resource.getURIFragment(element);
        Commentable reloadedElement = (Commentable) reloadedResource.getEObject(uriFragment);

        return buildLocation(reloadedElement, reloadedResource);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject getWrappedElement() {
        return resolveJaMoPPElement();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
        case softwarePackage.JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_JAMOPP_ELEMENT:
            return getJamoppElement();
        case softwarePackage.JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_LABEL:
            return getLabel();
        case softwarePackage.JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_NAME:
            return getName();
        case softwarePackage.JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT:
            return getWrappedElement();
        case softwarePackage.JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION:
            return getSourceLocation();
        }
        return super.eInvoke(operationID, arguments);
    }

} // JaMoPPJavaSoftwareElementImpl
