/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.IJavaTextResource;
import org.splevo.commons.emf.ReplacementUtil;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.ResourceProcessor;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Iterables;

/**
 * Resource processor for JaMoPP resources for usage in refactorings. This processor reloads a given
 * resource and enables layout and location extraction if this is not enabled by default.
 */
public class JaMoPPResourceProcessor implements ResourceProcessor {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPResourceProcessor.class);

    @Override
    public void processBeforeRefactoring(Resource resource) {
        if (!JaMoPPSoftwareModelExtractor.EXTRACTOR_EXTRACT_LAYOUT_BY_DEFAULT && resource instanceof IJavaTextResource) {
            reloadResourceWithLayoutInformation(resource);
        }
    }

    @Override
    public void processVPMBeforeRefactoring(VariationPointModel variationPointModel) {
        for (JaMoPPSoftwareElement se : Iterables.filter(variationPointModel.getSoftwareElements(),
                JaMoPPSoftwareElement.class)) {
            replaceJaMoPPSoftwareElementWithCommentableSoftwareElement(se);
        }
    }

    private void replaceJaMoPPSoftwareElementWithCommentableSoftwareElement(JaMoPPSoftwareElement oldSoftwareElement) {
        final Commentable referencedElement = oldSoftwareElement.getJamoppElement();

        // add comment to source code
        final String elementID = RefactoringUtil.addCommentableSoftwareElementReference(referencedElement);
        saveJaMoPPModel(referencedElement.eResource());

        // replace old software element with new one in VPM
        CommentableSoftwareElement newSoftwareElement = RefactoringUtil.createCommentableSoftwareElement(
                referencedElement, elementID);
        Resource vpmResource = oldSoftwareElement.eResource();
        EcoreUtil.replace(oldSoftwareElement, newSoftwareElement);
        ReplacementUtil.replaceCrossReferences(oldSoftwareElement, newSoftwareElement, vpmResource.getResourceSet());
    }

    private void saveJaMoPPModel(Resource eResource) {
        try {
            eResource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processAfterRefactoring(Resource resource) {
        // if (resource instanceof IJavaTextResource) {
        // reloadResourceWithLayoutInformation(resource);
        // }
    }

    private void reloadResourceWithLayoutInformation(Resource resource) {
        // construct new load options
        Map<Object, Object> options = resource.getResourceSet().getLoadOptions();
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);

        // reload the resource with the new load options
        try {
            resource.unload();
            resource.load(options);
        } catch (IOException e) {
            LOGGER.error("Could not preprocess JaMoPP resource.", e);
        }
    }

}
