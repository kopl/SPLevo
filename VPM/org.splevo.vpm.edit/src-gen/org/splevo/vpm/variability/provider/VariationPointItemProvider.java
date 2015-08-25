/**
 */
package org.splevo.vpm.variability.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;
import org.splevo.vpm.variability.variabilityPackage;

import com.google.common.base.Strings;

/**
 * This is the item provider adapter for a {@link org.splevo.vpm.variability.VariationPoint} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class VariationPointItemProvider extends IdentifierItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public VariationPointItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addLocationPropertyDescriptor(object);
			addVariabilityTypePropertyDescriptor(object);
			addBindingTimePropertyDescriptor(object);
			addExtensibilityPropertyDescriptor(object);
			addRefactoredPropertyDescriptor(object);
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
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_CustomizableNameHaving_name_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_CustomizableNameHaving_name_feature",
						"_UI_CustomizableNameHaving_type"),
				variabilityPackage.Literals.CUSTOMIZABLE_NAME_HAVING__NAME,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_CustomizableDescriptionHaving_description_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_CustomizableDescriptionHaving_description_feature",
								"_UI_CustomizableDescriptionHaving_type"),
						variabilityPackage.Literals.CUSTOMIZABLE_DESCRIPTION_HAVING__DESCRIPTION,
						true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Location feature.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void addLocationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_VariationPoint_location_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_VariationPoint_location_feature",
						"_UI_VariationPoint_type"),
				variabilityPackage.Literals.VARIATION_POINT__LOCATION, true,
				false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Variability Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVariabilityTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_VariationPoint_variabilityType_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_VariationPoint_variabilityType_feature",
						"_UI_VariationPoint_type"),
				variabilityPackage.Literals.VARIATION_POINT__VARIABILITY_TYPE,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Binding Time feature.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void addBindingTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_VariationPoint_bindingTime_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_VariationPoint_bindingTime_feature",
						"_UI_VariationPoint_type"),
				variabilityPackage.Literals.VARIATION_POINT__BINDING_TIME,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Extensibility feature.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void addExtensibilityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_VariationPoint_extensibility_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_VariationPoint_extensibility_feature",
						"_UI_VariationPoint_type"),
				variabilityPackage.Literals.VARIATION_POINT__EXTENSIBILITY,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Refactored feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRefactoredPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_VariationPoint_refactored_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_VariationPoint_refactored_feature",
						"_UI_VariationPoint_type"),
				variabilityPackage.Literals.VARIATION_POINT__REFACTORED, true,
				false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null,
				null));
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
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures
					.add(variabilityPackage.Literals.VARIATION_POINT__VARIANTS);
			childrenFeatures
					.add(variabilityPackage.Literals.VARIATION_POINT__VARIABILITY_MECHANISM);
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
	 * This returns VariationPoint.gif. <!-- begin-user-doc --> <!-- end-user-doc --> {@inheritDoc}
	 *
	 * @generated not
	 */
	@Override
	public Object getImage(Object object) {
		VariationPoint vp = (VariationPoint) object;
		Object baseImage;
		if (vp.getVariabilityType() == VariabilityType.XOR
				|| vp.getVariabilityType() == VariabilityType.OPTXOR) {
			baseImage = overlayImage(
					object,
					getResourceLocator().getImage(
							"full/obj16/VariationPointXOR"));
		} else {
			baseImage = overlayImage(object,
					getResourceLocator().getImage("full/obj16/VariationPoint"));
		}
		return overlayVariantCount(baseImage, vp.getVariants().size());
	}

	/**
	 * Compose two images to derive a combined icon.
	 *
	 * @param baseImage
	 *            The base image to combine.
	 * @param count
	 *            The number to select an overlay for.
	 * @return The composed image object.
	 */
	private Object overlayVariantCount(Object baseImage, int count) {
		List<Object> images = new ArrayList<Object>(2);
		images.add(baseImage);
		images.add(getResourceLocator().getImage("overlay/count-" + count));
		return new ComposedImage(images);
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> {@inheritDoc} <!--
	 * end-user-doc -->
	 *
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		StringBuilder label = new StringBuilder();

		VariationPoint vp = (VariationPoint) object;
		if (Strings.isNullOrEmpty(vp.getName())) {
			label.append(getString("_UI_VariationPoint_type"));
			label.append(" in ");
			SoftwareElement enclosingElement = vp.getLocation();
			if (enclosingElement != null) {
				label.append(enclosingElement.getLabel());
			} else {
				label.append("[TOP LEVEL]");
			}
		} else {
			label.append(vp.getName());
		}

		return label.toString();
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

		switch (notification.getFeatureID(VariationPoint.class)) {
		case variabilityPackage.VARIATION_POINT__NAME:
		case variabilityPackage.VARIATION_POINT__DESCRIPTION:
		case variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE:
		case variabilityPackage.VARIATION_POINT__BINDING_TIME:
		case variabilityPackage.VARIATION_POINT__EXTENSIBILITY:
		case variabilityPackage.VARIATION_POINT__REFACTORED:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case variabilityPackage.VARIATION_POINT__VARIANTS:
		case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				variabilityPackage.Literals.VARIATION_POINT__VARIANTS,
				variabilityFactory.eINSTANCE.createVariant()));

		newChildDescriptors
				.add(createChildParameter(
						variabilityPackage.Literals.VARIATION_POINT__VARIABILITY_MECHANISM,
						RealizationFactory.eINSTANCE
								.createVariabilityMechanism()));
	}

}
