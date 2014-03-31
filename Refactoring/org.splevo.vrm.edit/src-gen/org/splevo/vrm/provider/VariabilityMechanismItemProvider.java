/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vrm.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.splevo.vrm.VariabilityMechanism;
import org.splevo.vrm.vrmPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.vrm.VariabilityMechanism} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class VariabilityMechanismItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public VariabilityMechanismItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addRefactoringIdPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addSupportedBindingTimesPropertyDescriptor(object);
            addSupportedExtensibilitiesPropertyDescriptor(object);
            addSupportedVariabilityTypesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Refactoring Id feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addRefactoringIdPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_refactoringId_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_VariabilityMechanism_refactoringId_feature",
                        "_UI_VariabilityMechanism_type"), vrmPackage.Literals.VARIABILITY_MECHANISM__REFACTORING_ID,
                true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_name_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_VariabilityMechanism_name_feature",
                        "_UI_VariabilityMechanism_type"), vrmPackage.Literals.VARIABILITY_MECHANISM__NAME, true, false,
                false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_description_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_VariabilityMechanism_description_feature",
                        "_UI_VariabilityMechanism_type"), vrmPackage.Literals.VARIABILITY_MECHANISM__DESCRIPTION, true,
                false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Supported Binding Times feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSupportedBindingTimesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_supportedBindingTimes_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_VariabilityMechanism_supportedBindingTimes_feature", "_UI_VariabilityMechanism_type"),
                vrmPackage.Literals.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Supported Extensibilities feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSupportedExtensibilitiesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_supportedExtensibilities_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_VariabilityMechanism_supportedExtensibilities_feature", "_UI_VariabilityMechanism_type"),
                vrmPackage.Literals.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Supported Variability Types feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSupportedVariabilityTypesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_VariabilityMechanism_supportedVariabilityTypes_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_VariabilityMechanism_supportedVariabilityTypes_feature", "_UI_VariabilityMechanism_type"),
                vrmPackage.Literals.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns VariabilityMechanism.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/VariabilityMechanism"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((VariabilityMechanism) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_VariabilityMechanism_type")
                : getString("_UI_VariabilityMechanism_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(VariabilityMechanism.class)) {
        case vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
        case vrmPackage.VARIABILITY_MECHANISM__NAME:
        case vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION:
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES:
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES:
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return vrmEditPlugin.INSTANCE;
    }

}
