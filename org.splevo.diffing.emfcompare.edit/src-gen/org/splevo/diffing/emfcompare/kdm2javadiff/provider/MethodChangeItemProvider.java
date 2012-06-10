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

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.compare.diff.provider.DiffGroupItemProvider;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MethodChangeItemProvider
	extends DiffGroupItemProvider
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
	public MethodChangeItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE);
			childrenFeatures.add(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES);
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
	 * This returns MethodChange.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MethodChange"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		MethodChange methodChange = (MethodChange)object;
		return getString("_UI_MethodChange_type") + " " + methodChange.isConflicting();
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

		switch (notification.getFeatureID(MethodChange.class)) {
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGE:
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
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
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodModifierChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE,
				 KDM2JavaDiffFactory.eINSTANCE.createReturnTypeChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodParameterChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodInsert()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodDelete()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementOrderChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementInsert()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementDelete()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementMove()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS ||
			childFeature == KDM2JavaDiffPackage.Literals.METHOD_CHANGE__STATEMENT_CHANGES ||
			childFeature == KDM2JavaDiffPackage.Literals.METHOD_CHANGE__METHOD_DECLARATION_CHANGE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
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
