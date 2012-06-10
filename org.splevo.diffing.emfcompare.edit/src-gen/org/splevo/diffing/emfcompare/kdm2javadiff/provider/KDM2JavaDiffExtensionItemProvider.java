/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.provider;


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

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class KDM2JavaDiffExtensionItemProvider
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
	public KDM2JavaDiffExtensionItemProvider(AdapterFactory adapterFactory) {
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
		KDM2JavaDiffExtension kdm2JavaDiffExtension = (KDM2JavaDiffExtension)object;
		return getString("_UI_KDM2JavaDiffExtension_type") + " " + kdm2JavaDiffExtension.isIsCollapsed();
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

		switch (notification.getFeatureID(KDM2JavaDiffExtension.class)) {
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__CONFLICTING:
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__KIND:
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
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
				 KDM2JavaDiffFactory.eINSTANCE.createStatementOrderChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementMove()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassModifierChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createImportInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createImportDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodModifierChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createReturnTypeChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodParameterChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createCompilationUnitChange()));

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
		return KDM2JavaDiffEditPlugin.INSTANCE;
	}

}
