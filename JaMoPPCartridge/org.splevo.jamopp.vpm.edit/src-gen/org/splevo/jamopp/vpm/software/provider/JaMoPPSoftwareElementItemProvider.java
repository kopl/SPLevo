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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jdt.ui.JavaUI;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.provider.JavaSoftwareElementItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class JaMoPPSoftwareElementItemProvider extends
		JavaSoftwareElementItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public JaMoPPSoftwareElementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
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
	 * This adds a property descriptor for the Jamopp Element feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addJamoppElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_JaMoPPSoftwareElement_jamoppElement_feature"),
						getString(
								"_UI_PropertyDescriptor_description",
								"_UI_JaMoPPSoftwareElement_jamoppElement_feature",
								"_UI_JaMoPPSoftwareElement_type"),
						softwarePackage.Literals.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT,
						true, false, true, null, null, null));
	}

	/**
	 * This returns JaMoPPSoftwareElement.gif. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {

	    Commentable jamoppElement = ((JaMoPPSoftwareElement) object).getJamoppElement();

	    String image;

        if(jamoppElement instanceof Interface) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_INTERFACE;
        } else if(jamoppElement instanceof Enumeration) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_ENUM;
        } else if(jamoppElement instanceof Classifier) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CLASS;
        } else if(jamoppElement instanceof LocalVariableStatement) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_LOCAL_VARIABLE;
        } else if(jamoppElement instanceof Statement) {
            return getResourceLocator().getImage("ast/statement-single");
        } else if(jamoppElement instanceof Field) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_FIELD_DEFAULT;
        } else if(jamoppElement instanceof Import) {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_IMPDECL;
        } else if(jamoppElement instanceof org.emftext.language.java.containers.Package) {
	        image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_PACKDECL;
	    } else {
            image = org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_DEFAULT;
        }

		return JavaUI.getSharedImages().getImage(image);
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_JaMoPPSoftwareElement_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return JaMoPPVPMEditPlugin.INSTANCE;
	}

}
