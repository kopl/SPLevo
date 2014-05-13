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
package org.splevo.vpm.refinement;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Utility class to handle VariationPointModel models.
 */
public class RefinementUtil {

    /** The file extension to be used for refinement models. */
    public static final String REFINEMENT_FILE_EXTENSION = "refinement";

    /**
     * Load refinement model from the standard xmi file.
     *
     *
     * @param refinementFile
     *            The file object pointing to the main model file
     * @return the loaded refinement model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static RefinementModel load(File refinementFile) throws IOException {

        // load the required meta class packages
        RefinementPackage.eINSTANCE.eClass();

        // register the factory to be able to read xmi files
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(REFINEMENT_FILE_EXTENSION,
                new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createPlatformResourceURI(refinementFile.getPath(), true));
        r.load(null);
        EcoreUtil.resolveAll(rs);

        // convert the model to a java model
        EObject model = r.getContents().get(0);
        if (!(model instanceof RefinementModel)) {
            throw new IOException("Model is not a valid refinement model: " + model.getClass().getName());
        }
        RefinementModel splEvoProjectModel = (RefinementModel) model;

        return splEvoProjectModel;
    }

    /**
     * Save a refinement model to a specified file.
     *
     * @param refinementModel
     *            The project to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(RefinementModel refinementModel, File filePath) throws IOException {

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(RefinementUtil.REFINEMENT_FILE_EXTENSION, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(refinementModel);

        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Get all reasons relevant for the merge.
     *
     * Two possible cases:<br>
     * 1. Source and target contained in a list of variation points: move reason
     * 2. Source or target contained in a list of variation points: copy reason
     *
     *
     * @param originalRefinement
     *            The refinement to get the reasons from.
     * @param vps
     *            The variation points to check against.
     * @return The list of reasons.
     */
    public static List<RefinementReason> getReasons(Refinement originalRefinement, Collection<VariationPoint> vps) {

        List<RefinementReason> reasons = Lists.newArrayList();

        for (RefinementReason reason : originalRefinement.getReasons()) {
            boolean isSourceContained = vps.contains(reason.getSource());
            boolean isTargetContained = vps.contains(reason.getTarget());
            if (isSourceContained && isTargetContained) {
                reasons.add(reason);
            } else if (isSourceContained || isTargetContained) {
                RefinementReason copy = RefinementFactory.eINSTANCE.createRefinementReason();
                copy.setReason(reason.getReason());
                copy.setSource(reason.getSource());
                copy.setTarget(reason.getTarget());
                reasons.add(copy);
            }
        }

        return reasons;
    }
}