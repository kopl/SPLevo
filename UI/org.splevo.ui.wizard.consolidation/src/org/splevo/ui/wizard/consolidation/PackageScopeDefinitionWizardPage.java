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
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.splevo.project.SPLevoProject;

/**
 * Third page of the New Consolidation Project Wizard in which java packages to be ignored have to
 * be selected.
 * 
 * @author Radoslav Yankov
 */
@SuppressWarnings("restriction")
public class PackageScopeDefinitionWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;

    private CheckboxTreeViewer checkboxTreeViewer;

    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    public PackageScopeDefinitionWizardPage(SPLevoProject projectConfiguration) {
        super("Package Scope Definition Page");
        setTitle("Package Scope Definition");
        setDescription("Select the java packages to be ignored.");

        this.projectConfiguration = projectConfiguration;
    }

    @Override
    public void createControl(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 15;

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);

        Label label = new Label(container, SWT.NONE);
        label.setText("Select java packages to ignore:");

        checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
        checkboxTreeViewer.setContentProvider(new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean hasChildren(Object element) {
                if (element instanceof IPackageFragment) {
                    IPackageFragment packageFragment = (IPackageFragment) element;
                    return getChildren(packageFragment).length > 0;
                }
                return false;
            }

            @Override
            public Object getParent(Object element) {
                if (element instanceof IPackageFragment) {
                    IPackageFragment packageFragment = (IPackageFragment) element;
                    return packageFragment.getParent();
                }

                return null;
            }

            @Override
            public Object[] getElements(Object inputElement) {
                if (inputElement instanceof IPackageFragment[]) {
                    return (IPackageFragment[]) inputElement;
                }
                return null;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                if (parentElement instanceof IPackageFragment) {
                    IPackageFragment packageFragment = (IPackageFragment) parentElement;                   
                    List<IPackageFragment> result = new LinkedList<IPackageFragment>();
                    try {
                        for (IJavaElement javaElement : packageFragment.getChildren()) {
                            if (javaElement instanceof IPackageFragment) {
                                result.add((IPackageFragment) javaElement);
                            }
                        }
                    } catch (JavaModelException e) {
                        e.printStackTrace();
                    }
                    return result.toArray();
                }
                return null;
            }
        });
        
        checkboxTreeViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(Object element) {
                if (element instanceof IPackageFragment) {
                    IPackageFragment packageFragment = (IPackageFragment) element;
                    return packageFragment.getElementName();
                }                
                return null;
            }

            @Override
            public Image getImage(Object element) {
                JavaUILabelProvider provider = new JavaUILabelProvider();
                return provider.getImage(element);               
            }
        });

        Tree tree = checkboxTreeViewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        setControl(container);
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {
            checkboxTreeViewer.setInput(getJavaPackages());
        }
    }

    private IPackageFragment[] getJavaPackages() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        List<String> chosenProjectsNames = new ArrayList<String>();
        chosenProjectsNames.addAll(projectConfiguration.getLeadingProjects());
        chosenProjectsNames.addAll(projectConfiguration.getIntegrationProjects());

        List<IProject> allChosenProjects = new ArrayList<IProject>();

        for (String chosenProjectName : chosenProjectsNames) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        List<IPackageFragment> javaPackages = new ArrayList<IPackageFragment>();

        for (IProject project : allChosenProjects) {
            try {
                for (IPackageFragment packageFragment : JavaCore.create(project).getPackageFragments()) {
                    if (packageFragment.getKind() == IPackageFragmentRoot.K_SOURCE
                            && !javaPackages.contains(packageFragment)) {
                        javaPackages.add(packageFragment);
                    }
                }
            } catch (JavaModelException e) {
                e.printStackTrace();
            }
        }

        IPackageFragment[] packageFragments = new IPackageFragment[javaPackages.size()];
        packageFragments = javaPackages.toArray(packageFragments);

        return packageFragments;
    }
}