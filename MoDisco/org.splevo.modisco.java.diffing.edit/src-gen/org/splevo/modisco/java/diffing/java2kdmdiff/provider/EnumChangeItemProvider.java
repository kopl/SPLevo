/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.splevo.modisco.java.diffing.edit.images.ImageUtil;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumChangeItemProvider extends Java2KDMDiffExtensionItemProvider implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EnumChangeItemProvider(AdapterFactory adapterFactory) {
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

            addChangedEnumPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Changed Enum feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addChangedEnumPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_EnumChange_changedEnum_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_EnumChange_changedEnum_feature",
                        "_UI_EnumChange_type"), Java2KDMDiffPackage.Literals.ENUM_CHANGE__CHANGED_ENUM, true, false,
                true, null, null, null));
    }

    /**
     * This returns ClassDelete.gif. <!-- begin-user-doc --> Customized to provide a type specific
     * delete icon. {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getImage(Object object) {

        EnumChange enumChange = (EnumChange) object;
        if (enumChange.getKind() == DifferenceKind.DELETE) {
            if (enumChange.getChangedEnum() != null) {
                return ImageUtil.getASTDeleteIcon(enumChange.getChangedEnum(), this);
            } else {
                return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_ENUM);
            }

        } else if (enumChange.getKind() == DifferenceKind.ADD) {
            if (enumChange.getChangedEnum() != null) {
                return ImageUtil.getASTInsertIcon(enumChange.getChangedEnum(), this);
            } else {
                return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_ENUM);
            }
        } else {
            return super.getImage(object);
        }
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * {@inheritDoc}
     * @generated not
     */
    @Override
    public String getText(Object object) {
        EnumChange enumChange = (EnumChange) object;

        String label = null;
        if (enumChange.getChangedEnum() != null) {
            label = enumChange.getChangedEnum().getName();
        }

        if (enumChange.getKind() == DifferenceKind.DELETE) {
            return getString("_UI_EnumDelete_type") + " " + label;
        } else if (enumChange.getKind() == DifferenceKind.ADD) {
            return getString("_UI_EnumInsert_type") + " " + label;
        } else {
            return getString("_UI_EnumChange_type") + " " + label;
        }
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
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
