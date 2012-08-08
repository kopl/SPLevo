/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.compare.diff.metamodel.DiffPackage;
import org.eclipse.emf.compare.diff.provider.DiffGroupItemProvider;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange;
import org.splevo.diffing.emfcompare.util.ChangeUtil;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompilationUnitChangeItemProvider
	extends DiffGroupItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnitChangeItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__CLASS_CHANGES);
			childrenFeatures.add(KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES);
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
	 * This returns CompilationUnitChange.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CompilationUnitChange"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * Adds the number of sub changes to the string and tries to identify a compilation unit name
	 * from the sub changes.
	 * It might happen that there is no compilation unit in place. 
	 * In such a case, a default label will be added.
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		
		StringBuilder label = new StringBuilder();

		CompilationUnitChange compilationUnitChange = (CompilationUnitChange)object;
		label.append(compilationUnitChange.getSubchanges());
		label.append(" ");
		
		label.append(getString("_UI_CompilationUnitChange_type"));
		
		CompilationUnit compUnit = null;
		
		// check the concrete sub types
		if(compilationUnitChange.getClassChanges().size() > 0){
			ClassChange classChange = compilationUnitChange.getClassChanges().iterator().next();
			
			// try to get the compilation unit from class declaration change
			if(classChange.getClassDeclaractionChanges().size() > 0){
				ClassDeclarationChange classDeclarationChange = classChange.getClassDeclaractionChanges().iterator().next();
				compUnit = ChangeUtil.getCompilationUnit(classDeclarationChange);
			}
			
			// try to get the compilation unit from method declaration change
			if(classChange.getMethodChanges().size() > 0){
				MethodChange methodChange = classChange.getMethodChanges().iterator().next();
				
				// check the method declarations
				if(methodChange.getMethodDeclarationChanges().size() > 0){
					MethodDeclarationChange methodDeclarationChange = methodChange.getMethodDeclarationChanges().iterator().next();
					compUnit = ChangeUtil.getCompilationUnit(methodDeclarationChange);
				}
				
				// check the statement changes
				if(methodChange.getStatementChanges().size() > 0){
					StatementChange statementChange = methodChange.getStatementChanges().iterator().next();
					compUnit = ChangeUtil.getCompilationUnit(statementChange);
				}
			}
		}
		
		// check the common sub diff elements list if none specific type has been identified
		compUnit = ChangeUtil.getCompilationUnit(compilationUnitChange.getSubDiffElements().iterator().next());
		
		// append the compilation unit
		label.append(" ");
		if(compUnit != null){
			label.append(compUnit.getName());
		} else {
			label.append(getString("_UI_Unknown_type"));
		}

		return label.toString();
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

		switch (notification.getFeatureID(CompilationUnitChange.class)) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
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
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementOrderChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createStatementMove()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassModifierChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createImportInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createImportDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createClassChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodModifierChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createReturnTypeChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodParameterChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodInsert()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createMethodDelete()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createCompilationUnitChange()));

		newChildDescriptors.add
			(createChildParameter
				(DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS,
				 KDM2JavaDiffFactory.eINSTANCE.createPackageChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__CLASS_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createClassChange()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createImportInsert()));

		newChildDescriptors.add
			(createChildParameter
				(KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES,
				 KDM2JavaDiffFactory.eINSTANCE.createImportDelete()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == DiffPackage.Literals.DIFF_ELEMENT__SUB_DIFF_ELEMENTS ||
			childFeature == KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES ||
			childFeature == KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE__CLASS_CHANGES;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return KDM2JavaDiffEditPlugin.INSTANCE;
	}

}