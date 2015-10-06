/**
 */
package org.splevo.jamopp.vpm.software.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Commentable Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl#getId <em>Id</em>}</li>
 * <li>{@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl#getCompilationUnit
 * <em>Compilation Unit</em>}</li>
 * <li>{@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl#getType <em>Type
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CommentableSoftwareElementImpl extends JaMoPPJavaSoftwareElementImpl implements CommentableSoftwareElement {

    private static final String COMMENT_PREFIX = "SPLEVO_REF";

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getCompilationUnit() <em>Compilation Unit</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCompilationUnit()
     * @generated
     * @ordered
     */
    protected CompilationUnit compilationUnit;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getType()
     * @generated
     * @ordered
     */
    protected Class<? extends Commentable> type;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated not
     */
    protected CommentableSoftwareElementImpl() {
        super();
        type = Commentable.class;
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
            eNotify(new ENotificationImpl(this, Notification.SET, softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID,
                    oldId, id));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT, oldCompilationUnit,
                            compilationUnit));
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
            eNotify(new ENotificationImpl(this, Notification.SET,
                    softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT, oldCompilationUnit, compilationUnit));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Class<? extends Commentable> getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated not
     */
    public void setType(Class<? extends Commentable> newType) {
        Class<? extends Commentable> oldType = type;

        // normalize given type
        if (Statement.class.isAssignableFrom(newType) && !Member.class.isAssignableFrom(newType)) {
            type = Statement.class;
        } else {
            type = newType;
        }

        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__TYPE,
                    oldType, type));
    }

    /**
     * Builds the comment text that is added to the comments of an element.
     */
    public static String buildReferencingCommentText(String id) {
        return String.format("%s %s", COMMENT_PREFIX, id);
    }

    private boolean containsWantedComment(Commentable commentable) {
        return Iterables.any(commentable.getComments(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.contains(buildReferencingCommentText(getId()));
            }
        });
    }

    private Optional<Statement> findStatementInContainer(Commentable element) {
        EObject currentElement = element;
        while (currentElement.eContainer() != null) {
            EObject parent = currentElement.eContainer();
            if (parent instanceof StatementListContainer || parent instanceof StatementContainer) {
                return Optional.of((Statement) currentElement);
            }
            currentElement = parent;
        }
        return Optional.absent();
    }

    private Optional<? extends Commentable> findParentOfType(Commentable child, Class<? extends Commentable> type) {
        if (type == Statement.class) {
            return findStatementInContainer(child);
        }

        Commentable currentObject = child;
        while (currentObject != null) {
            if (type.isAssignableFrom(currentObject.getClass())) {
                return Optional.of(currentObject);
            }
            currentObject = (Commentable) currentObject.eContainer();
        }
        return Optional.absent();
    }

    @Override
    public Commentable resolveJaMoPPElement() {
        if (isReferencedElement(getCompilationUnit()).isPresent()) {
            return getCompilationUnit();
        }
        TreeIterator<EObject> treeIter = getCompilationUnit().eAllContents();
        while (treeIter.hasNext()) {
            EObject nextItem = treeIter.next();
            if (!(nextItem instanceof Commentable)) {
                treeIter.prune();
                continue;
            }
            Commentable commentable = (Commentable) nextItem;
            Optional<? extends Commentable> foundElement = isReferencedElement(commentable);
            if (!foundElement.isPresent()) {
                continue;
            }
            return foundElement.get();
        }
        return null;
    }

    private Optional<? extends Commentable> isReferencedElement(Commentable commentable) {
        if (!containsWantedComment(commentable)) {
            return Optional.absent();
        }
        return findParentOfType(commentable, getType());
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
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__TYPE:
            return getType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__ID:
            setId((String) newValue);
            return;
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT:
            setCompilationUnit((CompilationUnit) newValue);
            return;
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__TYPE:
            setType((Class<? extends Commentable>) newValue);
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
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__TYPE:
            setType((Class<? extends Commentable>) null);
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
        case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT__TYPE:
            return type != null;
        }
        return super.eIsSet(featureID);
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
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

    @Override
    public Commentable getJamoppElement() {
        return resolveJaMoPPElement();
    }

} // CommentableSoftwareElementImpl
