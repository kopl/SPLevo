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
package org.splevo.project.provider;

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
import org.splevo.project.ProjectFactory;
import org.splevo.project.ProjectPackage;
import org.splevo.project.SPLevoProject;

/**
 * This is the item provider adapter for a {@link org.splevo.project.SPLevoProject} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SPLevoProjectItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SPLevoProjectItemProvider(AdapterFactory adapterFactory) {
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

            addNamePropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addSourceModelPathLeadingPropertyDescriptor(object);
            addSourceModelPathIntegrationPropertyDescriptor(object);
            addLeadingProjectsPropertyDescriptor(object);
            addIntegrationProjectsPropertyDescriptor(object);
            addWorkspacePropertyDescriptor(object);
            addVariantNameLeadingPropertyDescriptor(object);
            addVariantNameIntegrationPropertyDescriptor(object);
            addDiffingModelPathPropertyDescriptor(object);
            addVpmModelPathsPropertyDescriptor(object);
            addDiffingFilterRulesPropertyDescriptor(object);
            addDifferIdsPropertyDescriptor(object);
            addDifferOptionsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_name_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_name_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Description feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_description_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_description_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__DESCRIPTION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Source Model Path Leading feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addSourceModelPathLeadingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_sourceModelPathLeading_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_sourceModelPathLeading_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Source Model Path Integration feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addSourceModelPathIntegrationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_sourceModelPathIntegration_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_sourceModelPathIntegration_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Leading Projects feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addLeadingProjectsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_leadingProjects_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_leadingProjects_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__LEADING_PROJECTS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Integration Projects feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addIntegrationProjectsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_integrationProjects_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_integrationProjects_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__INTEGRATION_PROJECTS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Workspace feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addWorkspacePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_workspace_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_workspace_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__WORKSPACE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Variant Name Leading feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addVariantNameLeadingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_variantNameLeading_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_variantNameLeading_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__VARIANT_NAME_LEADING,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Variant Name Integration feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addVariantNameIntegrationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_variantNameIntegration_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_variantNameIntegration_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Diffing Model Path feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDiffingModelPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_diffingModelPath_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_diffingModelPath_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__DIFFING_MODEL_PATH,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Vpm Model Paths feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addVpmModelPathsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_vpmModelPaths_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_vpmModelPaths_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__VPM_MODEL_PATHS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Diffing Filter Rules feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDiffingFilterRulesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_diffingFilterRules_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_diffingFilterRules_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__DIFFING_FILTER_RULES,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Differ Ids feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addDifferIdsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_differIds_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_differIds_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__DIFFER_IDS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Differ Options feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addDifferOptionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SPLevoProject_differOptions_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SPLevoProject_differOptions_feature", "_UI_SPLevoProject_type"),
                 ProjectPackage.Literals.SP_LEVO_PROJECT__DIFFER_OPTIONS,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(ProjectPackage.Literals.SP_LEVO_PROJECT__SPL_PROFILE);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns SPLevoProject.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/SPLevoProject"));
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((SPLevoProject)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_SPLevoProject_type") :
            getString("_UI_SPLevoProject_type") + " " + label;
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

        switch (notification.getFeatureID(SPLevoProject.class)) {
            case ProjectPackage.SP_LEVO_PROJECT__NAME:
            case ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION:
            case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING:
            case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION:
            case ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS:
            case ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS:
            case ProjectPackage.SP_LEVO_PROJECT__WORKSPACE:
            case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING:
            case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION:
            case ProjectPackage.SP_LEVO_PROJECT__DIFFING_MODEL_PATH:
            case ProjectPackage.SP_LEVO_PROJECT__VPM_MODEL_PATHS:
            case ProjectPackage.SP_LEVO_PROJECT__DIFFING_FILTER_RULES:
            case ProjectPackage.SP_LEVO_PROJECT__DIFFER_IDS:
            case ProjectPackage.SP_LEVO_PROJECT__DIFFER_OPTIONS:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProjectPackage.SP_LEVO_PROJECT__SPL_PROFILE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (ProjectPackage.Literals.SP_LEVO_PROJECT__SPL_PROFILE,
                 ProjectFactory.eINSTANCE.createSPLProfile()));
    }

	/**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ResourceLocator getResourceLocator() {
        return ProjectEditPlugin.INSTANCE;
    }

}
