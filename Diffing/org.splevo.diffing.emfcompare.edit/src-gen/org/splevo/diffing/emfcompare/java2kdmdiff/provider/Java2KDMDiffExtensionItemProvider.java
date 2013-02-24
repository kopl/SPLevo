/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.compare.diff.provider.AbstractDiffExtensionItemProvider;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffExtension;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffExtension} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffExtensionItemProvider
	extends AbstractDiffExtensionItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffExtensionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

	/**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addIsHiddenByPropertyDescriptor(object);
            addConflictingPropertyDescriptor(object);
            addKindPropertyDescriptor(object);
            addRemotePropertyDescriptor(object);
            addRequiresPropertyDescriptor(object);
            addRequiredByPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Is Hidden By feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addIsHiddenByPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_isHiddenBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_isHiddenBy_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__IS_HIDDEN_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Conflicting feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addConflictingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_conflicting_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_conflicting_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__CONFLICTING,
                 false,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Kind feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addKindPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_kind_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_kind_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__KIND,
                 false,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Remote feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addRemotePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_remote_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_remote_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__REMOTE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Requires feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addRequiresPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_requires_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_requires_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__REQUIRES,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Required By feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addRequiredByPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DiffElement_requiredBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DiffElement_requiredBy_feature", "_UI_DiffElement_type"),
                 DiffPackage.Literals.DIFF_ELEMENT__REQUIRED_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS);
        }
        return childrenFeatures;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        Java2KDMDiffExtension java2KDMDiffExtension = (Java2KDMDiffExtension)object;
        return getString("_UI_Java2KDMDiffExtension_type") + " " + java2KDMDiffExtension.isIsCollapsed();
    }

	/**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Java2KDMDiffExtension.class)) {
            case Java2KDMDiffPackage.JAVA2_KDM_DIFF_EXTENSION__CONFLICTING:
            case Java2KDMDiffPackage.JAVA2_KDM_DIFF_EXTENSION__KIND:
            case Java2KDMDiffPackage.JAVA2_KDM_DIFF_EXTENSION__REMOTE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case Java2KDMDiffPackage.JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

	/**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createStatementChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createImportInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createImportDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createImplementsInterfaceInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createImplementsInterfaceDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createFieldInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createFieldDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createClassInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createClassDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createPackageInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createPackageDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createMethodInsert()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 Java2KDMDiffFactory.eINSTANCE.createMethodDelete()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createConflictingDiffElement()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createDiffGroup()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createModelElementChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createModelElementChangeLeftTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createModelElementChangeRightTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createUpdateModelElement()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createMoveModelElement()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createUpdateContainmentFeature()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createAttributeChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createAttributeChangeLeftTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createAttributeChangeRightTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createAttributeOrderChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createUpdateAttribute()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createReferenceChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createReferenceChangeLeftTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createReferenceChangeRightTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createUpdateReference()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createReferenceOrderChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createResourceDiff()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createResourceDependencyChange()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createResourceDependencyChangeLeftTarget()));

        newChildDescriptors.add
            (createChildParameter
                (DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
                 DiffFactory.eINSTANCE.createResourceDependencyChangeRightTarget()));
    }

	/**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ResourceLocator getResourceLocator() {
        return Java2KDMDiffEditPlugin.INSTANCE;
    }

}
