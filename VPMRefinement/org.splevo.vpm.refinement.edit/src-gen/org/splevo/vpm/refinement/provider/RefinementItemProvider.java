/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.refinement.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementType;

import org.splevo.vpm.variability.provider.CustomizableDescriptionHavingItemProvider;
import com.google.common.base.Strings;

/**
 * This is the item provider adapter for a {@link org.splevo.vpm.refinement.Refinement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class RefinementItemProvider extends CustomizableDescriptionHavingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public RefinementItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addIdPropertyDescriptor(object);
            addTypePropertyDescriptor(object);
            addVariationPointsPropertyDescriptor(object);
            addSourcePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Id feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIdPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Refinement_id_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Refinement_id_feature", "_UI_Refinement_type"),
                RefinementPackage.Literals.REFINEMENT__ID, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Refinement_type_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Refinement_type_feature", "_UI_Refinement_type"),
                RefinementPackage.Literals.REFINEMENT__TYPE, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Variation Points feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVariationPointsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Refinement_variationPoints_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Refinement_variationPoints_feature",
                        "_UI_Refinement_type"), RefinementPackage.Literals.REFINEMENT__VARIATION_POINTS, true, false,
                true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Source feature.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected void addSourcePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Refinement_source_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Refinement_source_feature",
                                "_UI_Refinement_type"), RefinementPackage.Literals.REFINEMENT__SOURCE, true, false,
                        false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate
     * feature for an {@link org.eclipse.emf.edit.command.AddCommand},
     * {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(RefinementPackage.Literals.REFINEMENT__SUB_REFINEMENTS);
            childrenFeatures.add(RefinementPackage.Literals.REFINEMENT__REASONS);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * --> {@inheritDoc}
     *
     * @generated not
     */
    @Override
    public String getText(Object object) {
        if (object instanceof Refinement) {
            Refinement refinement = (Refinement) object;
            StringBuilder labelBuilder = new StringBuilder();

            if (Strings.isNullOrEmpty(refinement.getId())) {
                if (refinement.getType() == RefinementType.GROUPING) {
                    labelBuilder.append("Grouping");
                } else {
                    labelBuilder.append("Merge");
                }

                labelBuilder.append(" (");
                labelBuilder.append(refinement.getSubRefinements().size());
                labelBuilder.append("/");
                labelBuilder.append(refinement.getVariationPoints().size());
                labelBuilder.append(")");
            } else {
                labelBuilder.append(refinement.getId());
            }

            return labelBuilder.toString();
        } else {
            throw new RuntimeException("Unknown Refinement Type: " + object.getClass());
        }
    }

    /**
     * <!-- begin-user-doc -->
     *
     * This returns an icon for the refinement depending on the type of refinement.
     *
     * {@inheritDoc}
     *
     * <!-- end-user-doc -->
     *
     * @generated not
     */
    @Override
    public Object getImage(Object object) {
        if (object instanceof Refinement) {
            if (((Refinement) object).getType().equals(RefinementType.GROUPING)) {
                return overlayImage(object, getResourceLocator().getImage("full/obj16/Grouping"));
            } else {
                return overlayImage(object, getResourceLocator().getImage("full/obj16/Merge"));
            }
        } else {
            return super.getImage(object);
        }
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Refinement.class)) {
        case RefinementPackage.REFINEMENT__ID:
        case RefinementPackage.REFINEMENT__TYPE:
        case RefinementPackage.REFINEMENT__SOURCE:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
        case RefinementPackage.REFINEMENT__REASONS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(RefinementPackage.Literals.REFINEMENT__SUB_REFINEMENTS,
                RefinementFactory.eINSTANCE.createRefinement()));

        newChildDescriptors.add(createChildParameter(RefinementPackage.Literals.REFINEMENT__REASONS,
                RefinementFactory.eINSTANCE.createRefinementReason()));
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return RefinementEditPlugin.INSTANCE;
    }

}
