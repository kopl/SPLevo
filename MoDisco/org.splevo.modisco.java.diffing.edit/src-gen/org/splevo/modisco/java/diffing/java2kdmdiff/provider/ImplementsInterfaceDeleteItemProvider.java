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

import org.splevo.modisco.java.diffing.edit.images.ImageUtil;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ImplementsInterfaceDeleteItemProvider extends
		ClassChangeItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementsInterfaceDeleteItemProvider(AdapterFactory adapterFactory) {
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

			addImplementedInterfacePropertyDescriptor(object);
			addChangedClassPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Implemented Interface feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addImplementedInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_ImplementsInterfaceDelete_implementedInterface_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_ImplementsInterfaceDelete_implementedInterface_feature",
								"_UI_ImplementsInterfaceDelete_type"),
						Java2KDMDiffPackage.Literals.IMPLEMENTS_INTERFACE_DELETE__IMPLEMENTED_INTERFACE,
						true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Changed Class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChangedClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_ImplementsInterfaceDelete_changedClass_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_ImplementsInterfaceDelete_changedClass_feature",
								"_UI_ImplementsInterfaceDelete_type"),
						Java2KDMDiffPackage.Literals.IMPLEMENTS_INTERFACE_DELETE__CHANGED_CLASS,
						true, false, true, null, null, null));
	}

	/**
	 * This returns ImplementsInterfaceDelete.gif. <!-- begin-user-doc --> Customized to provide
	 * type specific insert icon. 
	 * {@inheritDoc}
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public Object getImage(Object object) {
		return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_INTERFACE);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		ImplementsInterfaceDelete implementsInterfaceDelete = (ImplementsInterfaceDelete) object;
		return getString("_UI_ImplementsInterfaceDelete_type") + " "
				+ implementsInterfaceDelete.isIsCollapsed();
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
