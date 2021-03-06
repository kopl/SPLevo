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

import java.util.List;
import java.util.SortedSet;

//import org.apache.log4j.Logger;
import org.eclipse.jdt.core.IPackageFragment;
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
import org.splevo.ui.wizard.consolidation.util.PackageUtil;

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

    //private static Logger logger = Logger.getLogger(NewConsolidationProjectWizard.class);

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
            javaPackages = PackageUtil.getJavaPackages(projectConfiguration);

            packagesTreeContentProvider.setJavaPackages(javaPackages);
            packagesCheckStateListener.setJavaPackages(javaPackages);
            List<IPackageFragment> list = PackageUtil.getRootpackages(packagesTreeContentProvider, javaPackages);

            packagesTreeViewer.setInput(list.toArray(new IPackageFragment[list.size()]));
        }
    }
    
    @Override
    public boolean canFlipToNextPage() {        
        int count = 0;
        List<SortedSet<IPackageFragment>> packages = PackageUtil.getNonDuplicatesJavaPackages(projectConfiguration);
        for (SortedSet<IPackageFragment> s : packages) {
            if (s.isEmpty()) {
                count++;
            }
        }
        if ((packages.size() - count) < 2) {
            return false;
        }
        return true;
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
            for (int i = 0; i < checkedPackages.length; i++) {
                if (!packagesTreeViewer.getGrayed(checkedPackages[i])) {
                    List<IPackageFragment> subPackages = packagesCheckStateListener.
                            getAllSubPackages((IPackageFragment) checkedPackages[i]);
                                        
                    if (subPackages.size() > 0) {                    
                        packagesString = packagesString.
                                concat(((IPackageFragment) checkedPackages[i]).getElementName() + ".*\n");
                        
                        i += subPackages.size();
                    } else {                        
                        packagesString = packagesString.
                                concat(((IPackageFragment) checkedPackages[i]).getElementName() + "\n");
                    }                                                                              
                }
            }
            projectConfiguration.getDifferOptions()
                    .put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, packagesString.trim());
        }
    }
}