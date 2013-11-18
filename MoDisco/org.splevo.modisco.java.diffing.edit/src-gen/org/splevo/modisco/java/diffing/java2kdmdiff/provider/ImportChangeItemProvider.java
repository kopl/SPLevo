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
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ImportChangeItemProvider extends Java2KDMDiffExtensionItemProvider implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImportChangeItemProvider(AdapterFactory adapterFactory) {
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

            addChangedImportPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Changed Import feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addChangedImportPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ImportChange_changedImport_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_ImportChange_changedImport_feature",
                        "_UI_ImportChange_type"), Java2KDMDiffPackage.Literals.IMPORT_CHANGE__CHANGED_IMPORT, true,
                false, true, null, null, null));
    }

    /**
     * This returns ClassDelete.gif. <!-- begin-user-doc --> Customized to provide a type specific
     * delete icon. {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getImage(Object object) {

        ImportChange importChange = (ImportChange) object;
        if (importChange.getKind() == DifferenceKind.DELETE) {
            if (importChange.getChangedImport() != null) {
                return ImageUtil.getASTDeleteIcon(importChange.getChangedImport(), this);
            } else {
                return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_IMPORT);
            }

        } else if (importChange.getKind() == DifferenceKind.ADD) {
            if (importChange.getChangedImport() != null) {
                return ImageUtil.getASTInsertIcon(importChange.getChangedImport(), this);
            } else {
                return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_IMPORT);
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
        ImportChange fieldChange = (ImportChange) object;

        String label = null;
        if (fieldChange.getChangedImport() != null) {
            label = fieldChange.getChangedImport().getImportedElement().getName();
        }

        if (fieldChange.getKind() == DifferenceKind.DELETE) {
            return getString("_UI_ImportDelete_type") + " " + label;
        } else if (fieldChange.getKind() == DifferenceKind.ADD) {
            return getString("_UI_ImportInsert_type") + " " + label;
        } else {
            return getString("_UI_ImportChange_type") + " " + label;
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