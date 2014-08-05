/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt, Radoslav Yankov
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.wizard.consolidation.listener.PackagesCheckStateListener;
import org.splevo.ui.wizard.consolidation.provider.PackageLabelProvider;
import org.splevo.ui.wizard.consolidation.provider.PackagesTreeContentProvider;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;

/**
 * Third page of the New Consolidation Project Wizard in which java packages to be ignored have to
 * be selected.
 */
public class PackageScopeDefinitionWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;
    private CheckboxTreeViewer packagesTreeViewer;
    private SortedSet<IPackageFragment> javaPackages;
    private PackagesTreeContentProvider packagesTreeContentProvider;
    private PackagesCheckStateListener packagesCheckStateListener;

    private static Logger logger = Logger.getLogger(NewConsolidationProjectWizard.class);

    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    public PackageScopeDefinitionWizardPage(SPLevoProject projectConfiguration) {
        super("Package Scope Definition Page");
        setTitle("Package Scope Definition");
        setDescription("Select java packages which you want to be ignored.");

        this.projectConfiguration = projectConfiguration;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {
            javaPackages = getJavaPackages();

            packagesTreeContentProvider.setJavaPackages(javaPackages);
            packagesCheckStateListener.setJavaPackages(javaPackages);

            packagesTreeViewer.setInput(getRootpackages());
        }
    }

    @Override
    public void createControl(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 15;

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);

        Label label = new Label(container, SWT.NONE);
        label.setText("Select java packages to be ignored:");

        createPackagesTreeViewer(container);

        setControl(container);
    }

    /**
     * Create a check box tree viewer for the packages.
     * 
     * @param container
     *            Container in which the tree viewer will be created.
     */
    private void createPackagesTreeViewer(Composite container) {
        packagesTreeViewer = new CheckboxTreeViewer(container);
        packagesTreeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        packagesTreeViewer.setLabelProvider(new PackageLabelProvider());

        packagesTreeContentProvider = new PackagesTreeContentProvider();
        packagesTreeViewer.setContentProvider(packagesTreeContentProvider);

        packagesCheckStateListener = new PackagesCheckStateListener();
        packagesTreeViewer.addCheckStateListener(packagesCheckStateListener);
    }

    /**
     * Save the chosen packages to the SPLevo project configuration file.
     */
    public void saveChosenPackages() {
        Object[] checkedPackages = packagesTreeViewer.getCheckedElements();
        String packagesString = "";

        if (checkedPackages.length > 0) {
            for (Object checkedPackage : checkedPackages) {
                if (!packagesTreeViewer.getGrayed(checkedPackage)) {
                    packagesString = packagesString.concat(((IPackageFragment) checkedPackage).getElementName() + "\n");
                }
            }
            projectConfiguration.getDifferOptions()
                    .put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, packagesString.trim());
        }
    }

    /**
     * Get all java packages which are in the class path of the chosen projects without duplicates
     * and ordered by name (ascending).
     * 
     * @return A sorted set with all java packages.
     */
    private SortedSet<IPackageFragment> getJavaPackages() {
        SortedSet<IPackageFragment> javaPackagesSet = new TreeSet<IPackageFragment>(new PackagesComparator());

        for (IProject project : getAllChosenProjects()) {
            try {
                if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
                    for (IPackageFragment packageFragment : JavaCore.create(project).getPackageFragments()) {
                        if (!packageFragment.getElementName().equals("")) {
                            javaPackagesSet.add(packageFragment);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Exception thrown during getting all java packages "
                        + "which are in the class path of the chosen projects", e);
            }
        }
        return javaPackagesSet;
    }

    /**
     * Get all projects the user has chosen on the previous page.
     * 
     * @return List with all chosen projects.
     */
    private List<IProject> getAllChosenProjects() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        List<IProject> allChosenProjects = new ArrayList<IProject>();

        for (String chosenProjectName : projectConfiguration.getLeadingProjects()) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        for (String chosenProjectName : projectConfiguration.getIntegrationProjects()) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        return allChosenProjects;
    }

    /**
     * Get all root packages which have to be shown in the tree.
     * 
     * @return Array with all root packages.
     */
    private IPackageFragment[] getRootpackages() {
        List<IPackageFragment> rootPackages = new ArrayList<IPackageFragment>();

        for (IPackageFragment javaPackage : javaPackages) {
            if (packagesTreeContentProvider.getParentPackage(javaPackage) == null) {
                rootPackages.add(javaPackage);
            }
        }

        return rootPackages.toArray(new IPackageFragment[rootPackages.size()]);
    }
}