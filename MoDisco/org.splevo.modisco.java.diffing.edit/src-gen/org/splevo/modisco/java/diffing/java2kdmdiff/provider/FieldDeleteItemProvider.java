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
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldDeleteItemProvider extends FieldChangeItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeleteItemProvider(AdapterFactory adapterFactory) {
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

			addFieldRightPropertyDescriptor(object);
			addLeftContainerPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Field Right feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFieldRightPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_FieldDelete_fieldRight_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_FieldDelete_fieldRight_feature",
						"_UI_FieldDelete_type"),
				Java2KDMDiffPackage.Literals.FIELD_DELETE__FIELD_RIGHT, true,
				false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Left Container feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLeftContainerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_FieldDelete_leftContainer_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_FieldDelete_leftContainer_feature",
						"_UI_FieldDelete_type"),
				Java2KDMDiffPackage.Literals.FIELD_DELETE__LEFT_CONTAINER,
				true, false, true, null, null, null));
	}

	/**
	 * This returns FieldDelete.gif. 
	 * <!-- begin-user-doc --> 
	 * Customized to provide type specific delete icon.  
	 * {@inheritDoc}
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public Object getImage(Object object) {
		FieldDelete fieldDelete = (FieldDelete) object;
		if (fieldDelete.getFieldRight() != null) {
			return ImageUtil
					.getASTDeleteIcon(fieldDelete.getFieldRight(), this);
		} else {
			return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_FIELD);
		}
	}

	/**
	 * This returns the label text for the adapted class. 
	 * <!-- begin-user-doc --> 
	 * Customized to provide a type specific label. 
	 * {@inheritDoc}
	 * <!-- end-user-doc
	 * -->
	 * 
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		FieldDelete fieldDelete = (FieldDelete) object;

		String fieldName = null;
		if (fieldDelete.getFieldRight() != null) {
			fieldName = fieldDelete.getFieldRight().getName();
		}

		return getString("_UI_FieldDelete_type") + " " + fieldName;
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