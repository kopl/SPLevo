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
import org.splevo.diffing.java.modisco.java2kdmdiff.ClassInsert;
import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.java.modisco.java2kdmdiff.ClassInsert} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassInsertItemProvider extends ClassChangeItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInsertItemProvider(AdapterFactory adapterFactory) {
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

			addClassLeftPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Class Left feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addClassLeftPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_ClassInsert_classLeft_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_ClassInsert_classLeft_feature",
						"_UI_ClassInsert_type"),
				Java2KDMDiffPackage.Literals.CLASS_INSERT__CLASS_LEFT, true,
				false, true, null, null, null));
	}

	/**
	 * This returns ClassInsert.gif. 
	 * <!-- begin-user-doc --> 
	 * Customized to provide a type specific
	 * insert icon.  
	 * {@inheritDoc}
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public Object getImage(Object object) {
		ClassInsert classInsert = (ClassInsert) object;
		if (classInsert.getClassLeft() != null) {
			return ImageUtil.getASTInsertIcon(classInsert.getClassLeft(), this);
		} else {
			return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_CLASS);
		}
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> Improved label
	 * creation detecting the real name of the class inserted. {@inheritDoc} <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		ClassInsert classInsert = (ClassInsert) object;

		String className = null;
		if (classInsert.getClassLeft() != null) {
			className = classInsert.getClassLeft().getName();
		}

		return getString("_UI_ClassInsert_type") + " " + className;
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
