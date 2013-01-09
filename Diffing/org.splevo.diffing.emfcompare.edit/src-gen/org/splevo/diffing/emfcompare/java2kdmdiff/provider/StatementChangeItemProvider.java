/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementChangeItemProvider
	extends Java2KDMDiffExtensionItemProvider
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
	public StatementChangeItemProvider(AdapterFactory adapterFactory) {
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

            addStatementRightPropertyDescriptor(object);
            addStatementLeftPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Statement Right feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addStatementRightPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_StatementChange_statementRight_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_StatementChange_statementRight_feature", "_UI_StatementChange_type"),
                 Java2KDMDiffPackage.Literals.STATEMENT_CHANGE__STATEMENT_RIGHT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Statement Left feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addStatementLeftPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_StatementChange_statementLeft_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_StatementChange_statementLeft_feature", "_UI_StatementChange_type"),
                 Java2KDMDiffPackage.Literals.STATEMENT_CHANGE__STATEMENT_LEFT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This returns StatementChange.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/StatementChange"));
    }

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		StatementChange statementChange = (StatementChange)object;
		String statementName = "";
		Statement statement = null;
		if(statementChange.getStatementRight() != null){
			statement = statementChange.getStatementRight();
		} else {
			statement = statementChange.getStatementLeft();
		}
		
		if(statement instanceof VariableDeclarationStatement){
			VariableDeclarationStatement vds = (VariableDeclarationStatement) statement;
			StringBuilder varNameBuilder = new StringBuilder();
			for (VariableDeclarationFragment declarationFragment : vds.getFragments()) {
				if(varNameBuilder.length() > 0){
					varNameBuilder.append(",");
				}
				varNameBuilder.append(declarationFragment.getName());
			}
			
			statementName = "VariableDeclarationStatement ("+varNameBuilder+")";
		} else if(statement instanceof ReturnStatement){
			statementName = "ReturnStatement";
		} else {
			statementName = statement.getClass().toString();
		}
		return getString("_UI_StatementChange_type", new Object[] {statementName});
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
