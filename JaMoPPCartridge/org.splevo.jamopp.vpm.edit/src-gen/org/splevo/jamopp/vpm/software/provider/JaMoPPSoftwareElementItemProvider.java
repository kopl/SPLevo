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
package org.splevo.jamopp.vpm.software.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.provider.JavaSoftwareElementItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 *
 * @generated
 */
public class JaMoPPSoftwareElementItemProvider extends JavaSoftwareElementItemProvider implements
        IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public JaMoPPSoftwareElementItemProvider(AdapterFactory adapterFactory) {
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

            addJamoppElementPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Jamopp Element feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addJamoppElementPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_JaMoPPSoftwareElement_jamoppElement_feature"),
                        getString("_UI_PropertyDescriptor_description",
                                "_UI_JaMoPPSoftwareElement_jamoppElement_feature", "_UI_JaMoPPSoftwareElement_type"),
                        softwarePackage.Literals.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT, true, false, true, null,
                        null, null));
    }

    /**
     * This returns the image of the JaMoPPSoftwareElement according to the wrapped JaMoPP element.
     * Internally, it makes use of the registered ItemProvider for the element.
     *
     * {@inheritDoc}
     *
     * @generated not
     */
    @Override
    public Object getImage(Object object) {

        Commentable jamoppElement = ((JaMoPPSoftwareElement) object).getJamoppElement();
        Image jamoppImage = getItemProviderImage(jamoppElement);
        if (jamoppImage != null) {
            return jamoppImage;
        } else {
            return overlayImage(object, getResourceLocator().getImage("full/obj16/JaMoPPSoftwareElement"));
        }
    }

    private Image getItemProviderImage(Object element) {
        ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        return labelProvider.getImage(element);
    }

    /**
     * This returns the label of the JaMoPPSoftwareElement according to the wrapped JaMoPP element.
     * Internally, it makes use of the {@link JaMoPPElementUtil} or the registered ItemProvider for
     * the element of the {@link JaMoPPElementUtil} is not able to provide a label.
     *
     * {@inheritDoc}
     *
     * @generated not
     */
    @Override
    public String getText(Object object) {

        Commentable jamoppElement = ((JaMoPPSoftwareElement) object).getJamoppElement();
        String label = JaMoPPElementUtil.getLabel(jamoppElement);
        if (label != null) {
            return label;
        }
        label = getItemProviderText(jamoppElement);
        if (label != null) {
            return label;
        }
        return "JaMoPP Element." + jamoppElement.toString();
    }

    private String getItemProviderText(Object element) {
        ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        return labelProvider.getText(element);
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
        return JaMoPPVPMEditPlugin.INSTANCE;
    }

}
