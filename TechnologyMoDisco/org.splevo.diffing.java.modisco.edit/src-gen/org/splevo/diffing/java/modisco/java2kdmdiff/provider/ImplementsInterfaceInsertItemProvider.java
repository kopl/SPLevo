/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff.provider;

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

import org.splevo.diffing.java.modisco.edit.images.ImageUtil;
import org.splevo.diffing.java.modisco.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.java.modisco.java2kdmdiff.ImplementsInterfaceInsert} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ImplementsInterfaceInsertItemProvider extends
		ClassChangeItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementsInterfaceInsertItemProvider(AdapterFactory adapterFactory) {
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
						getString("_UI_ImplementsInterfaceInsert_implementedInterface_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_ImplementsInterfaceInsert_implementedInterface_feature",
								"_UI_ImplementsInterfaceInsert_type"),
						Java2KDMDiffPackage.Literals.IMPLEMENTS_INTERFACE_INSERT__IMPLEMENTED_INTERFACE,
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
						getString("_UI_ImplementsInterfaceInsert_changedClass_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_ImplementsInterfaceInsert_changedClass_feature",
								"_UI_ImplementsInterfaceInsert_type"),
						Java2KDMDiffPackage.Literals.IMPLEMENTS_INTERFACE_INSERT__CHANGED_CLASS,
						true, false, true, null, null, null));
	}

	/**
	 * This returns ImplementsInterfaceInsert.gif. <!-- begin-user-doc --> Customized to provide
	 * type specific insert icon. 
	 * {@inheritDoc} <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public Object getImage(Object object) {
		return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_INTERFACE);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		ImplementsInterfaceInsert implementsInterfaceInsert = (ImplementsInterfaceInsert) object;
		return getString("_UI_ImplementsInterfaceInsert_type") + " "
				+ implementsInterfaceInsert.isIsCollapsed();
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
