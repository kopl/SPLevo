/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.DifferenceKind;
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
import org.splevo.modisco.java.diffing.edit.images.ImageUtil;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * This is the item provider adapter for a {@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementChangeItemProvider extends Java2KDMDiffExtensionItemProvider implements
        IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
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

            addChangedStatementPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Changed Statement feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addChangedStatementPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_StatementChange_changedStatement_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_StatementChange_changedStatement_feature",
                        "_UI_StatementChange_type"), Java2KDMDiffPackage.Literals.STATEMENT_CHANGE__CHANGED_STATEMENT,
                true, false, true, null, null, null));
    }

    /**
     * This returns ClassDelete.gif. <!-- begin-user-doc --> Customized to provide a type specific
     * delete icon. {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getImage(Object object) {

        StatementChange statementChange = (StatementChange) object;
        if (statementChange.getKind() == DifferenceKind.DELETE) {
            if (statementChange.getChangedStatement() != null) {
                return ImageUtil.getASTDeleteIcon(statementChange.getChangedStatement(), this);
            } else {
                return ImageUtil.composeDeleteIcon(this, ImageUtil.ICON_STATEMENT);
            }

        } else if (statementChange.getKind() == DifferenceKind.ADD) {
            if (statementChange.getChangedStatement() != null) {
                return ImageUtil.getASTInsertIcon(statementChange.getChangedStatement(), this);
            } else {
                return ImageUtil.composeInsertIcon(this, ImageUtil.ICON_STATEMENT);
            }
        } else {
            return super.getImage(object);
        }
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * {@inheritDoc}
     * @generated not
     */
    @Override
    public String getText(Object object) {
        StatementChange statementChange = (StatementChange) object;

        String label = null;
        Statement statement = statementChange.getChangedStatement();
        if (statement != null) {

            if (statement instanceof VariableDeclarationStatement) {
                VariableDeclarationStatement vds = (VariableDeclarationStatement) statement;
                StringBuilder varNameBuilder = new StringBuilder();
                for (VariableDeclarationFragment declarationFragment : vds.getFragments()) {
                    if (varNameBuilder.length() > 0) {
                        varNameBuilder.append(",");
                    }
                    varNameBuilder.append(declarationFragment.getName());
                }

                label = "VariableDeclarationStatement (" + varNameBuilder + ")";
            } else if (statement instanceof ReturnStatement) {
                label = "ReturnStatement";
            } else {
                label = statement.getClass().toString();
            }
        }

        if (statementChange.getKind() == DifferenceKind.DELETE) {
            return getString("_UI_StatementDelete_type") + " " + label;
        } else if (statementChange.getKind() == DifferenceKind.ADD) {
            return getString("_UI_StatementInsert_type") + " " + label;
        } else {
            return getString("_UI_StatementChange_type") + " " + label;
        }
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
