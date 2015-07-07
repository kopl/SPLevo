/**
 */
package org.splevo.jamopp.vpm.software.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.commons.layout.ReferenceLayoutInformation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.java.IJavaLocationMap;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaDevNullLocationMap;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;

import com.google.common.collect.Maps;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Commentable Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl#getCompilationUnit
 * <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CommentableSoftwareElementImpl extends
		MinimalEObjectImpl.Container implements CommentableSoftwareElement {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCompilationUnit()
	 * <em>Compilation Unit</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCompilationUnit()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit compilationUnit;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CommentableSoftwareElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return softwarePackage.Literals.COMMENTABLE_SOFTWARE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompilationUnit getCompilationUnit() {
		if (compilationUnit != null && compilationUnit.eIsProxy()) {
			InternalEObject oldCompilationUnit = (InternalEObject) compilationUnit;
			compilationUnit = (CompilationUnit) eResolveProxy(oldCompilationUnit);
			if (compilationUnit != oldCompilationUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT,
							oldCompilationUnit, compilationUnit));
			}
		}
		return compilationUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompilationUnit basicGetCompilationUnit() {
		return compilationUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCompilationUnit(CompilationUnit newCompilationUnit) {
		CompilationUnit oldCompilationUnit = compilationUnit;
		compilationUnit = newCompilationUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT,
					oldCompilationUnit, compilationUnit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public String getLabel() {
		return JaMoPPElementUtil.getLabel(getJamoppElement());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public String getName() {
		return JaMoPPElementUtil.getName(getJamoppElement());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public SourceLocation getSourceLocation() {
		Commentable element = null;

		try {
			element = getJamoppElement();
			if (!(element.eResource() instanceof JavaResource)) {
				return null;
			}
		} catch (NullPointerException ex) {
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

	private Commentable getJamoppElement() {
		for (ConcreteClassifier classifier : getCompilationUnit()
				.getClassifiers()) {
			for (Method method : classifier.getMethods()) {
				if (method instanceof ClassMethod) {
					final TreeIterator<EObject> contentIterator = method
							.eAllContents();
					while (contentIterator.hasNext()) {
						final EObject next = contentIterator.next();
						if (next instanceof Commentable) {
							Commentable commentable = (Commentable) next;
							//if(0 != commentable.getLayoutInformations().size()) {
								//LayoutInformation lf = commentable.getLayoutInformations().get(0);
								EList<String> comments = commentable.getComments();
								if (null != comments && comments.contains(getId())) {
									return getStatementOf(commentable.eContainer());
								}
								//if((lf instanceof ReferenceLayoutInformation) && lf.getHiddenTokenText().contains(getId())) {
								//	return getStatementOf(commentable.eContainer());
								//}
							//}
						}
					}
				}
			}
		}

		throw new NullPointerException();
	}

	private Commentable getStatementOf(EObject commentable) {
		if (commentable instanceof Statement) {
			return (Commentable) commentable;
		}
		return getStatementOf(commentable.eContainer());
	}

	private SourceLocation buildLocation(Commentable element,
			JavaResource resource) {
		SourceLocation location = SoftwareFactory.eINSTANCE
				.createSourceLocation();
		location.setFilePath(resource.getURI().toFileString());

		IJavaLocationMap locationMap = resource.getLocationMap();
		location.setStartLine(locationMap.getLine(element));
		location.setStartPosition(locationMap.getCharStart(element));
		location.setEndPosition(locationMap.getCharEnd(element));
		return location;
	}

	/**
	 * To get source locations for resources previously loaded with disabled
	 * location maps, try to reload the according resource, find the current
	 * element in it and return the according source location.
	 * 
	 * @param element
	 *            The element to get the location info for.
	 * @return The source location map for the element.
	 */
	private SourceLocation reloadLocation(Commentable element,
			JavaResource resource) {

		JavaResource reloadedResource = (JavaResource) resource
				.getResourceSet().createResource(resource.getURI());
		LinkedHashMap<String, Object> options = Maps.newLinkedHashMap();
		options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
		try {
			reloadedResource.load(options);
		} catch (IOException e) {
			return null;
		}
		String uriFragment = resource.getURIFragment(element);
		Commentable reloadedElement = (Commentable) reloadedResource
				.getEObject(uriFragment);

		return buildLocation(reloadedElement, reloadedResource);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public EObject getWrappedElement() {
		return getJamoppElement();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID:
			return getId();
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT:
			if (resolve)
				return getCompilationUnit();
			return basicGetCompilationUnit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID:
			setId((String) newValue);
			return;
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT:
			setCompilationUnit((CompilationUnit) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID:
			setId(ID_EDEFAULT);
			return;
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT:
			setCompilationUnit((CompilationUnit) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT:
			return compilationUnit != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT___GET_LABEL:
			return getLabel();
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT___GET_NAME:
			return getName();
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION:
			return getSourceLocation();
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT:
			return getWrappedElement();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} // CommentableSoftwareElementImpl
