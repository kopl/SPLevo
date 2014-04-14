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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The ContentProvider for the VPExplorer.
 */
public class VPExplorerContentProvider extends TreeNodeContentProvider {

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

    /** Pointers to the files on the root level. */
    private List<File> rootFiles = Lists.newArrayList();

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
        VPExplorer vpexplorer = (VPExplorer) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("org.splevo.ui.vpexplorer");

        if (parentElement instanceof VPExplorerContent) {
            VPExplorerContent vpContent = (VPExplorerContent) parentElement;
            if (vpContent.getVpm() != null) {
                if (vpexplorer.getShowGrouping()) {
                    return vpContent.getVpm().getVariationPointGroups().toArray();
                } else {
                    indexVariationPointLocations(vpContent);
                    return rootFiles.toArray();
                }
            } else {
                return new Object[0];
            }

        } else if (parentElement instanceof File) {
            File parentFile = (File) parentElement;
            List<Object> children = Lists.newArrayList();
            children.addAll(fileVPIndex.get(parentFile));
            children.addAll(subFileIndex.get(parentFile));
            return children.toArray();

        } else if (parentElement instanceof VariationPointModel) {
            VariationPointModel vpm = (VariationPointModel) parentElement;
            return vpm.getVariationPointGroups().toArray();

        } else if (parentElement instanceof VariationPointGroup) {
            VariationPointGroup group = (VariationPointGroup) parentElement;
            return group.getVariationPoints().toArray();

        } else if (parentElement instanceof VariationPoint) {
            return ((VariationPoint) parentElement).getVariants().toArray();

        } else if (parentElement instanceof Variant) {
            EList<SoftwareElement> implementingElements = ((Variant) parentElement).getImplementingElements();
            return implementingElements.toArray();

        } else if (parentElement instanceof SoftwareElement) {
            return new Object[0];

        } else {
            logger.warn("Unhandled Parent Element: " + parentElement.getClass().getSimpleName());
        }
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

        fileVPIndex.clear();

        String workspacePath = getNormalizedWorkspacePath();

        EList<VariationPointGroup> vpGroups = vpContent.getVpm().getVariationPointGroups();
        for (VariationPointGroup vpGroup : vpGroups) {
            EList<VariationPoint> vps = vpGroup.getVariationPoints();
            for (VariationPoint vp : vps) {
                SourceLocation location = vp.getLocation().getSourceLocation();
                File sourceFile = new File(location.getFilePath());
                vpFileIndex.put(vp, sourceFile);
                fileVPIndex.put(sourceFile, vp);

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

                    childFile = parentFile;
                    parentFile = parentFile.getParentFile();
                }
                rootFiles.add(childFile);

            }
        }
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
