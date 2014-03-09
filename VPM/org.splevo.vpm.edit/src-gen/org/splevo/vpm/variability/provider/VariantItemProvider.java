/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.variability.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.emf.edit.provider.ViewerNotification;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * This is the item provider adapter for a {@link org.splevo.vpm.variability.Variant} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VariantItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariantItemProvider(AdapterFactory adapterFactory) {
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

            addChildFeaturePropertyDescriptor(object);
            addImplementingElementsPropertyDescriptor(object);
            addLeadingPropertyDescriptor(object);
            addVariantIdPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Child Feature feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addChildFeaturePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Variant_childFeature_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Variant_childFeature_feature",
                                "_UI_Variant_type"), variabilityPackage.Literals.VARIANT__CHILD_FEATURE, true, false,
                        true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Implementing Elements feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addImplementingElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Variant_implementingElements_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Variant_implementingElements_feature",
                        "_UI_Variant_type"), variabilityPackage.Literals.VARIANT__IMPLEMENTING_ELEMENTS, true, false,
                true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Leading feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addLeadingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Variant_leading_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Variant_leading_feature", "_UI_Variant_type"),
                variabilityPackage.Literals.VARIANT__LEADING, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Variant Id feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVariantIdPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Variant_variantId_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Variant_variantId_feature", "_UI_Variant_type"),
                variabilityPackage.Literals.VARIANT__VARIANT_ID, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns an icon for the variant element.
     * Depending on the leading flag, the icon is changed.
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    @Override
    public Object getImage(Object object) {
        if (object instanceof Variant) {
            if (((Variant) object).getLeading() == Boolean.FALSE) {
                return overlayImage(object, getResourceLocator().getImage("full/obj16/VariantIntegration"));
            }
        }
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Variant"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    @Override
    public String getText(Object object) {
        Variant variant = (Variant) object;
        return getString("_UI_Variant_type") + " (" + variant.getVariantId() + ")";
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

        switch (notification.getFeatureID(Variant.class)) {
        case variabilityPackage.VARIANT__LEADING:
        case variabilityPackage.VARIANT__VARIANT_ID:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ((IChildCreationExtender) adapterFactory).getResourceLocator();
    }

}
