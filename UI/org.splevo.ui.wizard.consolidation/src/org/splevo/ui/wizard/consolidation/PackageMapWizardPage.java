/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;




//import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.wizard.consolidation.provider.PackageLabelProvider;
import org.splevo.ui.wizard.consolidation.provider.PackagesTreeContentProvider;
import org.splevo.ui.wizard.consolidation.util.PackageUtil;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;

/**
 * Fourth page of the New Consolidation Project Wizard in which java packages of the selected
 * Projects can be merged.
 */
public class PackageMapWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;
    private List<SortedSet<IPackageFragment>> javaPackages;
    private List<IProject> choosenProjects;
    private List<TreeViewer> packagesTreeViewerList;
    private List<Text> packageSelectionTextList;
    private Composite mainComposite;
    private Composite container;
    private List<IPackageFragment> packageFragmentFilterSelection;
    private List<MergedPackage> mergedPackages;
    private TableViewer table;
    private static final String SELECTION_INDEX = "selection";
    private static final String SELECTED_PACKAGE = "package";
    // private static Logger logger = Logger.getLogger(NewConsolidationProjectWizard.class);

    private ViewerFilter selectedFilter = new ViewerFilter() {
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {

            if (packageFragmentFilterSelection.contains(element)) {

                return false;
            }

            return true;
        }
    };

    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    protected PackageMapWizardPage(SPLevoProject projectConfiguration) {
        super("Map Packages Page");
        setTitle("Packages ");
        setDescription("link packages that should be mapped.");
        this.projectConfiguration = projectConfiguration;

    }

    @Override
    public void createControl(Composite parent) {
        WizardDialog d = ((WizardDialog) this.getWizard().getContainer());

        d.setMinimumPageSize(300, 400);
        container = new Composite(parent, SWT.BORDER);
        GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.horizontalSpacing = 5;
        container.setLayout(gridLayout);

        mainComposite = new Composite(container, SWT.NONE);
        mainComposite.setLayout(new GridLayout(1, true));
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        packageFragmentFilterSelection = new ArrayList<IPackageFragment>();
        mergedPackages = new ArrayList<MergedPackage>();

        setControl(container);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {

            choosenProjects = PackageUtil.getAllChosenProjects(projectConfiguration);

            Composite treesComposite = new Composite(mainComposite, SWT.FILL);
            treesComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            treesComposite.setLayout(new GridLayout(choosenProjects.size(), true));

            createPackagesTreeViewerLabels(treesComposite);
            createPackagesTreeViewer(treesComposite);
            createPackageSelectButtons(treesComposite);
            createSelectedPackagesText(treesComposite);
            createMergeButton(mainComposite);
            createMergeList(mainComposite);
            container.layout();
            container.setSize(container.computeSize(container.getSize().x, container.getSize().y, true));

        } else {
            for (Control control : mainComposite.getChildren()) {
                control.dispose();
            }
        }
    }

    private void createPackagesTreeViewerLabels(Composite composite) {
        for (IProject project : choosenProjects) {
            Label l = new Label(composite, SWT.NONE);
            l.setText(project.getName());
        }
    }

    private void createPackageSelectButtons(Composite composite) {
        for (int i = 0; i < choosenProjects.size(); i++) {
            Composite comp = new Composite(composite, SWT.NONE);
            comp.setLayout(new GridLayout(2, true));
            GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
            gridData1.horizontalAlignment = GridData.CENTER;
            comp.setLayoutData(gridData1);

            GridData gridData = new GridData();
            gridData.horizontalAlignment = GridData.CENTER;
            Button up = new Button(comp, SWT.NONE);
            up.setLayoutData(gridData);
            up.setText(" \u2191 ");

            up.setData(SELECTION_INDEX, new Integer(i));
            up.addMouseListener(new MouseListener() {

                @Override
                public void mouseUp(MouseEvent e) {
                    int index = (Integer) ((Button) e.getSource()).getData(SELECTION_INDEX);
                    clearTextSelection(packageSelectionTextList.get(index));
                    packagesTreeViewerList.get(index).refresh();
                    packagesTreeViewerList.get(index).expandAll();
                }

                @Override
                public void mouseDoubleClick(MouseEvent e) {
                }

                @Override
                public void mouseDown(MouseEvent e) {
                }
            });

            Button down = new Button(comp, SWT.NONE);
            down.setLayoutData(gridData);
            down.setText(" \u2193 ");
            down.setData(SELECTION_INDEX, new Integer(i));
            down.addMouseListener(new MouseListener() {

                @Override
                public void mouseUp(MouseEvent e) {
                    int index = (Integer) ((Button) e.getSource()).getData(SELECTION_INDEX);
                    clearTextSelection(packageSelectionTextList.get(index));

                    Object o = ((IStructuredSelection) packagesTreeViewerList.get(index).getSelection())
                            .getFirstElement();
                    if (o instanceof IPackageFragment) {
                        IPackageFragment fragment = (IPackageFragment) o;
                        packageFragmentFilterSelection.add(fragment);
                        packageSelectionTextList.get(index).setData(SELECTED_PACKAGE, fragment);
                        packageSelectionTextList.get(index).setText(PackageUtil.getName(fragment));
                    }
                    packagesTreeViewerList.get(index).refresh();

                }

                @Override
                public void mouseDoubleClick(MouseEvent e) {
                }

                @Override
                public void mouseDown(MouseEvent e) {
                }
            });
        }
    }

    private void clearTextSelection(Text text) {
        IPackageFragment pFragment = (IPackageFragment) text.getData(SELECTED_PACKAGE);
        if (pFragment != null) {
            packageFragmentFilterSelection.remove(pFragment);
            text.setData(SELECTED_PACKAGE, null);
            text.setText("");
        }
    }

    private void createSelectedPackagesText(Composite composite) {
        packageSelectionTextList = new ArrayList<Text>();
        for (int i = 0; i < choosenProjects.size(); i++) {
            Text text = new Text(composite, SWT.BORDER);
            text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
            text.setEditable(false);
            packageSelectionTextList.add(text);
        }
    }

    private void createMergeButton(Composite composite) {
        Composite comp = new Composite(composite, SWT.NONE);
        comp.setLayout(new GridLayout(1, true));
        GridData layoutData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        layoutData.horizontalAlignment = GridData.CENTER;
        comp.setLayoutData(layoutData);
        Button button = new Button(comp, SWT.NONE);
        button.setText(" \u2193 ");
        button.addMouseListener(new MouseListener() {

            @Override
            public void mouseUp(MouseEvent e) {
                List<IPackageFragment> fragmentList = new ArrayList<IPackageFragment>();
                for (int i = 0; i < packageSelectionTextList.size(); i++) {
                    IPackageFragment pFragment = (IPackageFragment) packageSelectionTextList.get(i).getData(
                            SELECTED_PACKAGE);
                    if (pFragment != null) {
                        fragmentList.add(pFragment);
                    }
                }
                if (fragmentList.size() == 2) {
                    mergedPackages.add(new MergedPackage(fragmentList.get(0), fragmentList.get(1)));
                    for (int i = 0; i < packageSelectionTextList.size(); i++) {
                        packageSelectionTextList.get(i).setText("");
                        packageSelectionTextList.get(i).setData(SELECTED_PACKAGE, null);
                    }
                } else {
                    MessageDialog.openInformation(getShell(), "Invalid Input", "there must be 2 packages selected!");
                }
                table.refresh();
            }

            @Override
            public void mouseDown(MouseEvent e) {
            }

            @Override
            public void mouseDoubleClick(MouseEvent e) {
            }
        });
    }

    private void createMergeList(Composite composite) {
        Composite comp = new Composite(composite, SWT.NONE);
        comp.setLayout(new GridLayout(2, false));
        GridData layoutData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        // layoutData.horizontalAlignment = GridData.CENTER;
        comp.setLayoutData(layoutData);
        Button delete = new Button(comp, SWT.NONE);
        delete.setText(" X ");
        delete.addMouseListener(new MouseListener() {

            @Override
            public void mouseUp(MouseEvent e) {
                if (table.getSelection().isEmpty()) {
                    return;
                }

                for (Object merged : ((StructuredSelection) table.getSelection()).toList()) {
                    if (!(merged instanceof MergedPackage)) {
                        continue;
                    }
                    for (IPackageFragment packageFragment : ((MergedPackage) merged).getPackages()) {
                        packageFragmentFilterSelection.remove(packageFragment);
                    }
                    mergedPackages.remove(merged);
                }
                table.getTable().remove(table.getTable().getSelectionIndices());
                table.getTable().update();

                for (TreeViewer tree : packagesTreeViewerList) {
                    tree.refresh();
                }
            }

            @Override
            public void mouseDown(MouseEvent e) {
            }

            @Override
            public void mouseDoubleClick(MouseEvent e) {
            }
        });
        table = new TableViewer(comp, SWT.BORDER | SWT.FULL_SELECTION);
        table.getTable().setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        table.setContentProvider(ArrayContentProvider.getInstance());
        table.setInput(mergedPackages);

    }

    /**
     * Get all java packages which are in the class path of the chosen projects without duplicates
     * and ordered by name (ascending).
     * 
     * @return A sorted set with all java packages.
     */
    private SortedSet<IPackageFragment> getJavaPackages(IProject project) {
        SortedSet<IPackageFragment> javaPackagesSet = new TreeSet<IPackageFragment>(new PackagesComparator());

        try {
            if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
                for (IPackageFragment packageFragment : JavaCore.create(project).getPackageFragments()) {
                    if (!packageFragment.getElementName().equals("")) {
                        javaPackagesSet.add(packageFragment);
                    }
                }
            }
        } catch (Exception e) {
            /*
             * logger.error("Exception thrown during getting all java packages " +
             * "which are in the class path of the chosen projects", e); } /*for (IPackageFragment p
             * : allJavaPackages) { if
             * (p.getJavaProject().getElementName().equals(project.getName())) {
             * javaPackagesSet.add(p); }
             */
        }
        return javaPackagesSet;
    }

    /**
     * Create a check box tree viewer for the packages.
     * 
     * @param container
     *            Container in which the tree viewer will be created.
     */
    private void createPackagesTreeViewer(Composite composite) {
        packagesTreeViewerList = new ArrayList<TreeViewer>();
        javaPackages = new ArrayList<SortedSet<IPackageFragment>>();
        for (IProject project : choosenProjects) {

            TreeViewer packagesTreeViewer = new TreeViewer(composite, SWT.BORDER);
            Tree leftTree = packagesTreeViewer.getTree();
            leftTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

            PackagesTreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();
            packagesTreeViewer.setLabelProvider(new PackageLabelProvider());
            packagesTreeViewer.setContentProvider(packagesTreeContentProvider);
            javaPackages.add(getJavaPackages(project));

            packagesTreeContentProvider.setJavaPackages(javaPackages.get(javaPackages.size() - 1));

            packagesTreeViewerList.add(packagesTreeViewer);
        }
        removeDuplicates();
        setInput();

    }
    
    private void setInput() {
        for (int i = 0; i < packagesTreeViewerList.size(); i++) {
            PackagesTreeContentProvider content = (PackagesTreeContentProvider) packagesTreeViewerList.get(i).
                    getContentProvider();
            List<IPackageFragment> list = PackageUtil.getRootpackages(content, javaPackages.get(i));
            packagesTreeViewerList.get(i).setInput(list.toArray(new IPackageFragment[list.size()]));
            packagesTreeViewerList.get(i).addFilter(selectedFilter);            
        }
    }
    
    private void removeDuplicates() {
        for (int i = 0; i < javaPackages.size(); i++) {
            SortedSet<IPackageFragment> temp = new TreeSet<IPackageFragment>(javaPackages.get(i));
            for (int j = 0; j < javaPackages.size(); j++) {
                if (i == j) {
                    continue;
                }
                javaPackages.get(i).removeAll(javaPackages.get(j));
                javaPackages.get(j).removeAll(temp);
            }
        }
    }
    
    /**
     * Save the mapped packages to the SPLevo project configuration file.
     */
    public void saveMergedPackages() {
        if (mergedPackages == null) {
            return;
        }
        String mapped = "";
        for (MergedPackage mPackage : mergedPackages) {
            mapped += mPackage.toString() + "\n";
        }
        projectConfiguration.getDifferOptions()
        .put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, mapped.trim());
    }
}
