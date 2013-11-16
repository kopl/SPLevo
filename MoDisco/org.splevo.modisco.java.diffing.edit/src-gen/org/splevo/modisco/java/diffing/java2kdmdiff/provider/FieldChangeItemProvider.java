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
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldChangeItemProvider extends Java2KDMDiffExtensionItemProvider implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldChangeItemProvider(AdapterFactory adapterFactory) {
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

            addChangedFieldPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Changed Field feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addChangedFieldPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_FieldChange_changedField_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_FieldChange_changedField_feature",
                        "_UI_FieldChange_type"), Java2KDMDiffPackage.Literals.FIELD_CHANGE__CHANGED_FIELD, true, false,
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

        FieldChange fieldChange = (FieldChange) object;
        if (fieldChange.getKind() == DifferenceKind.DELETE) {
            if (fieldChange.getChangedField() != null) {
                return ImageUtil.getASTDeleteIcon(fieldChange.getChangedField(), this);
            } else {
                return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_FIELD);
            }

        } else if (fieldChange.getKind() == DifferenceKind.ADD) {
            if (fieldChange.getChangedField() != null) {
                return ImageUtil.getASTInsertIcon(fieldChange.getChangedField(), this);
            } else {
                return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_FIELD);
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
        FieldChange fieldChange = (FieldChange) object;

        String label = null;
        if (fieldChange.getChangedField() != null) {
            label = fieldChange.getChangedField().getName();
        }

        if (fieldChange.getKind() == DifferenceKind.DELETE) {
            return getString("_UI_FieldDelete_type") + " " + label;
        } else if (fieldChange.getKind() == DifferenceKind.ADD) {
            return getString("_UI_FieldInsert_type") + " " + label;
        } else {
            return getString("_UI_FieldChange_type") + " " + label;
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
