/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.vpexplorer.providers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The ContentProvider for the VPExplorer.
 */
public class VPExplorerContentProvider extends TreeNodeContentProvider {

    /**
     * Proxy for a File contained in a VPExplorer. The proxy provides access
     * to the {@link VPExplorerContentProvider#getChildren()} method. This method
     * returns all child elements of the file.
     */
    public static class VPExplorerContentFileWithChildReference extends File {

        private static final long serialVersionUID = 7988300896390991983L;
        private final Function<VPExplorerContentFileWithChildReference, Object[]> callable;
        
        /**
         * Constructs a new proxy file.
         * @param realFile The file to be proxied.
         * @param getChildrenCallable The callback function to gather the file's children.
         */
        public VPExplorerContentFileWithChildReference(File realFile,
                Function<VPExplorerContentFileWithChildReference, Object[]> getChildrenCallable) {
            super(realFile.toURI());
            this.callable = getChildrenCallable;
        }
        
        /**
         * Constructs a new proxy file.
         * @param pathname The path of the proxied file.
         * @param getChildrenCallable The callback function to gather the file's children.
         */
        public VPExplorerContentFileWithChildReference(String pathname,
                Function<VPExplorerContentFileWithChildReference, Object[]> getChildrenCallable) {
            super(pathname);
            this.callable = getChildrenCallable;
        }
        
        /**
         * Returns the file's children with respect to the variation point model. The children
         * can be files again, variation points, variation groups, and so on.
         * @return The children of the file.
         */
        public Object[] getVPMChildren() {
            return callable.apply(this);
        }

        @Override
        public File getParentFile() {
            File parentFile = super.getParentFile();
            if (parentFile == null) {
                return null;
            }
            return new VPExplorerContentFileWithChildReference(parentFile, callable);
        }
        
    }
    
    private static Logger logger = Logger.getLogger(VPExplorerContentProvider.class);

    /**
     * Index to remember the variation points located in a file.
     *
     * DesignDecision Enable public access to index for accessing this statistic information.
     */
    private static HashMultimap<File, VariationPoint> fileVPIndex = HashMultimap.create();

    /** Index to remember the file containing a variation point. */
    private Map<VariationPoint, File> vpFileIndex = Maps.newLinkedHashMap();

    /** Index to remember the subfiles registered as containing variation points. */
    private HashMultimap<File, File> subFileIndex = HashMultimap.create();

    /** Index to remember the files and subfiles corresponding to a group. */
    private HashMultimap<VariationPointGroup, File> groupFileIndex = HashMultimap.create();

    /** Pointers to the files on the root level. */
    private Set<File> rootFiles = new HashSet<File>();

    /**
     * Get the set of {@link VariationPoint}s contained in a file.
     *
     * DesignDecision Enable public access to index for accessing this statistic information.
     *
     * @param file
     *            The file to get the VPs for.
     * @return The set of variation points. At least an empty list but not null.
     */
    public static Set<VariationPoint> getVPInFile(File file) {
        return fileVPIndex.get(file);
    }

    @Override
    public Object[] getChildren(Object parentElement) {

        if (parentElement instanceof VPExplorerContent) {
            return getChildren((VPExplorerContent) parentElement);

        } else if (parentElement instanceof File) {
            return getChildren((File) parentElement);

        } else if (parentElement instanceof FileWrapper) {
            return getChildren((FileWrapper) parentElement);

        } else if (parentElement instanceof VariationPointModel) {
            return getChildren((VariationPointModel) parentElement);

        } else if (parentElement instanceof VariationPointGroup) {
            return getChildren((VariationPointGroup) parentElement);

        } else if (parentElement instanceof VariationPoint) {
            return getChildren((VariationPoint) parentElement);

        } else if (parentElement instanceof Variant) {
            return getChildren((Variant) parentElement);

        } else if (parentElement instanceof SoftwareElement) {
            return getChildren((SoftwareElement) parentElement);

        } else {
            logger.warn("Unhandled Parent Element: " + parentElement.getClass().getSimpleName());
        }
        return new Object[0];
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(VPExplorerContent parentElement) {
        VPExplorer vpexplorer = (VPExplorer) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("org.splevo.ui.vpexplorer");

        if (parentElement.getVpm() != null) {
            if (vpexplorer.getShowGrouping()) {
                return parentElement.getVpm().getVariationPointGroups().toArray();
            } else {
                indexVariationPointLocations(parentElement);
                return rootFiles.toArray();
            }
        } else {
            return new Object[0];
        }
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(File parentElement) {
        File parentFile = (File) parentElement;
        List<Object> children = Lists.newArrayList();
        children.addAll(fileVPIndex.get(parentFile));
        children.addAll(subFileIndex.get(parentFile));
        return children.toArray();
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(FileWrapper parentElement) {
        File parentFile = parentElement.getFile();
        List<Object> children = Lists.newArrayList();

        Collection<File> childFileInGroup = (Collections2.filter(subFileIndex.get(parentFile),
                Predicates.in(groupFileIndex.get(parentElement.getGroup()))));

        for (File file : childFileInGroup) {
            children.add(new FileWrapper(parentElement, file, parentElement.getGroup()));
        }
        Collection<VariationPoint> childVPInGroup = (Collections2.filter(fileVPIndex.get(parentFile),
                Predicates.in(parentElement.getGroup().getVariationPoints())));
        children.addAll(childVPInGroup);

        return children.toArray();
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(VariationPointModel parentElement) {
        return parentElement.getVariationPointGroups().toArray();
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(VariationPointGroup parentElement) {
        VPExplorer vpexplorer = (VPExplorer) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("org.splevo.ui.vpexplorer");

        VariationPointGroup group = (VariationPointGroup) parentElement;
        Collection<FileWrapper> childFiles = new LinkedList<FileWrapper>();
        if (vpexplorer.getShowGrouping()) {
            Collection<File> files = Collections2.filter(rootFiles, Predicates.in(groupFileIndex.get(group)));
            for (File file : files) {
                childFiles.add(new FileWrapper(group, file, group));
            }
            return childFiles.toArray();
        } else {
            return group.getVariationPoints().toArray();
        }
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(VariationPoint parentElement) {
        return parentElement.getVariants().toArray();
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(Variant parentElement) {
        EList<SoftwareElement> implementingElements = parentElement.getImplementingElements();
        return implementingElements.toArray();
    }

    /**
     * @param parentElement
     * @return
     */
    private Object[] getChildren(SoftwareElement parentElement) {
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof VariationPoint) {
            return vpFileIndex.get((VariationPoint) element);
        } else if (element instanceof Variant) {
            return ((Variant) element).getVariationPoint();
        } else if (element instanceof SoftwareElement) {
            return ((SoftwareElement) element).eContainer();
        } else if (element instanceof File) {
            if (rootFiles.contains(element)) {
                return null;
            } else {
                return ((File) element).getParentFile();
            }
        } else if (element instanceof FileWrapper) {
            if (rootFiles.contains(((FileWrapper) element).getFile())) {
                return null;
            } else {
                return ((FileWrapper) element).getParent();
            }
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return this.getChildren(inputElement);
    }

    /**
     * Populates the CU locations map with all variation points and the location names of their
     * corresponding CUs.
     *
     * @param vpContent
     *            the VPcontent to be used as population source
     */
    private void indexVariationPointLocations(VPExplorerContent vpContent) {

        clearIndexes();
        
        String workspacePath = getNormalizedWorkspacePath();

        EList<VariationPointGroup> vpGroups = vpContent.getVpm().getVariationPointGroups();
        for (VariationPointGroup vpGroup : vpGroups) {
            EList<VariationPoint> vps = vpGroup.getVariationPoints();
            for (VariationPoint vp : vps) {
                // used the resource uri and not the
                // element wrappers location to save further navigation
                // and resolving overhead.
                EObject locationElement = vp.getLocation().getWrappedElement();
                String filePath = locationElement.eResource().getURI().toFileString();
                File sourceFile = new VPExplorerContentFileWithChildReference(filePath,
                        new Function<VPExplorerContentFileWithChildReference, Object[]>() {
                    @Override
                    public Object[] apply(VPExplorerContentFileWithChildReference arg0) {
                        return getChildren(arg0);
                    }
                });
                vpFileIndex.put(vp, sourceFile);
                fileVPIndex.put(sourceFile, vp);
                groupFileIndex.put(vpGroup, sourceFile);

                File parentFile = sourceFile.getParentFile();
                File childFile = sourceFile;
                if (parentFile == null) {
                    rootFiles.add(sourceFile);
                    continue;
                }
                while (parentFile != null) {

                    boolean isWorkspaceRoot = isWorkspaceRoot(workspacePath, parentFile);
                    if (isWorkspaceRoot) {
                        break;
                    }

                    subFileIndex.get(parentFile).add(childFile);
                    groupFileIndex.put(vpGroup, childFile);

                    childFile = parentFile;
                    parentFile = parentFile.getParentFile();
                }
                rootFiles.add(childFile);
                groupFileIndex.put(vpGroup, childFile);

            }
        }
    }

    private void clearIndexes() {
        fileVPIndex.clear();
        rootFiles.clear();
        vpFileIndex.clear();
        subFileIndex.clear();
        groupFileIndex.clear();
    }

    private String getNormalizedWorkspacePath() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        String workspaceBasePath = root.getLocation().toPortableString();
        workspaceBasePath = FilenameUtils.normalize(workspaceBasePath);
        return workspaceBasePath;
    }

    private boolean isWorkspaceRoot(String workspaceBasePath, File parentFile) {
        try {
            String parentPath = parentFile.getCanonicalPath();
            return IOCase.SYSTEM.checkEquals(parentPath, workspaceBasePath);
        } catch (IOException e) {
            return false;
        }
    }

}
