/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldDeclarationChangeItemProvider extends FieldChangeItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclarationChangeItemProvider(AdapterFactory adapterFactory) {
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

			addFieldLeftPropertyDescriptor(object);
			addFieldRightPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Field Left feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFieldLeftPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_FieldDeclarationChange_fieldLeft_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_FieldDeclarationChange_fieldLeft_feature",
								"_UI_FieldDeclarationChange_type"),
						Java2KDMDiffPackage.Literals.FIELD_DECLARATION_CHANGE__FIELD_LEFT,
						true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Field Right feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFieldRightPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_FieldDeclarationChange_fieldRight_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_FieldDeclarationChange_fieldRight_feature",
								"_UI_FieldDeclarationChange_type"),
						Java2KDMDiffPackage.Literals.FIELD_DECLARATION_CHANGE__FIELD_RIGHT,
						true, false, true, null, null, null));
	}

	/**
	 * This returns FieldDeclarationChange.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(
				object,
				getResourceLocator().getImage(
						"full/obj16/FieldDeclarationChange"));
	}

	/**
	 * This returns the label text for the adapted class. 
	 * <!-- begin-user-doc --> 
	 *  Generate a field specific label taking the fields name into account.
	 * {@inheritDoc}
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		FieldDeclarationChange fieldDeclarationChange = (FieldDeclarationChange) object;

		StringBuilder nameBuilder = new StringBuilder();
		if (fieldDeclarationChange.getFieldLeft() != null) {

			if (fieldDeclarationChange.getFieldLeft().getName() != null) {
				nameBuilder.append(fieldDeclarationChange.getFieldLeft()
						.getName());
			}
			if (fieldDeclarationChange.getFieldLeft().getFragments().size() > 0) {
				for (VariableDeclarationFragment fragment : fieldDeclarationChange
						.getFieldLeft().getFragments()) {

					if (nameBuilder.length() > 0) {
						nameBuilder.append(", ");
					}
					nameBuilder.append(fragment.getName());
				}
			}
		}

		return getString("_UI_FieldDeclarationChange_type") + " "
				+ nameBuilder.toString();
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
