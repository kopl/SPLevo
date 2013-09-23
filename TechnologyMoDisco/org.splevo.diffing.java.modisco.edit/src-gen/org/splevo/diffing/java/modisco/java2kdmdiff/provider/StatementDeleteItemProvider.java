/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff.provider;

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
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;

import org.splevo.diffing.java.modisco.edit.images.ImageUtil;
import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.java.modisco.java2kdmdiff.StatementDelete;

/**
 * This is the item provider adapter for a {@link org.splevo.diffing.java.modisco.java2kdmdiff.StatementDelete} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementDeleteItemProvider extends StatementChangeItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementDeleteItemProvider(AdapterFactory adapterFactory) {
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

			addLeftContainerPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Left Container feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLeftContainerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_StatementDelete_leftContainer_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_StatementDelete_leftContainer_feature",
						"_UI_StatementDelete_type"),
				Java2KDMDiffPackage.Literals.STATEMENT_DELETE__LEFT_CONTAINER,
				true, false, true, null, null, null));
	}


    /**
     * This returns StatementDelete.gif. <!-- begin-user-doc --> Customized to provide type specific
     * delete icon. 
     * {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getImage(Object object) {
        StatementDelete statementDelete = (StatementDelete) object;
        if (statementDelete.getStatementRight() != null) {
            return ImageUtil.getASTDeleteIcon(statementDelete.getStatementRight(), this);
        } else {
            return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_STATEMENT);
        }
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> Customized label
     * method to provide more intuitive information about the statement inserted. 
     * {@inheritDoc} <!-- end-user-doc
     * -->
     * 
     * @generated not
     */
    @Override
    public String getText(Object object) {
        StatementDelete statementDelete = (StatementDelete) object;
        Statement statement = statementDelete.getStatementRight();
        if (statement instanceof VariableDeclarationStatement) {
            VariableDeclarationStatement varDeclStatement = (VariableDeclarationStatement) statement;
            StringBuilder builder = new StringBuilder();
            builder.append("VariableDeclarationStatement Delete (");
            for (VariableDeclarationFragment fragment : varDeclStatement.getFragments()) {
                builder.append(fragment.getName());
            }
            builder.append(")");
            return builder.toString();
        }
        return statementDelete.getStatementRight().getClass().getSimpleName() + " Delete";
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
