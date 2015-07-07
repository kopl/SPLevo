package org.splevo.jamopp.refactoring;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.commons.layout.LayoutFactory;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.commons.layout.ReferenceLayoutInformation;
import org.emftext.commons.layout.impl.LayoutInformationImpl;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.IJavaTextResource;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.refactoring.ResourceProcessor;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

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
        // TODO replace JaMoPPSoftwareElement with newly created element that references via
        // comments (id to be used in the comment can be calculated by concatenating the id of the
        // variation point and the variant). Use the comment adding method in RefactoringUtil
        // (extract it and add it to this project).
    	for (VariationPointGroup vpgroup : variationPointModel.getVariationPointGroups()) {
    		for (VariationPoint vp : vpgroup.getVariationPoints()) {
    			for (Variant variant : vp.getVariants()) {
    				//addCommentToStatements(variant.getImplementingElements());
    				//setCommentableReferences(variant.getImplementingElements());
    				replaceJaMoPPSoftwareElementWithCommentableSoftwareElement(variant.getImplementingElements());
    			}
    		}
    	}
    }
    
    private void replaceJaMoPPSoftwareElementWithCommentableSoftwareElement(EList<SoftwareElement> implementingElements) {
    	for (SoftwareElement se : implementingElements) {
    		String comment = EcoreUtil.generateUUID();
    		Commentable statement = ((JaMoPPSoftwareElement) se).getJamoppElement();
    		
//    		LayoutInformation layoutinf = LayoutFactory.eINSTANCE.createReferenceLayoutInformation();
//    		layoutinf.setHiddenTokenText("");
//    		statement.getLayoutInformations().add(0, layoutinf);
			RefactoringUtil.addCommentBefore(statement, comment);
			
			saveJaMoPPModel(statement.eResource());
			
			EcoreUtil.replace(se, createCommentableJaMoPPElement(statement, comment));
			// TODO reference commentable software element to variant
    	}
    }

   /* private void setCommentableReferences(EList<SoftwareElement> implementingElements) {
		for (SoftwareElement se : implementingElements) {
			Commentable statement = ((JaMoPPSoftwareElement) se).getJamoppElement();
			EcoreUtil.replace(se, createCommentableJaMoPPElement(statement));
		}
	}*/

	private CommentableSoftwareElement createCommentableJaMoPPElement(Commentable statement, String id) {
		CommentableSoftwareElement commentable = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
		commentable.setCompilationUnit(statement.getContainingCompilationUnit());
		commentable.setId(id);
		
		return commentable;
	}

	/*private void addCommentToStatements(EList<SoftwareElement> implementingElements) {
		for (SoftwareElement se : implementingElements) {
			Commentable statement = ((JaMoPPSoftwareElement) se).getJamoppElement();
			RefactoringUtil.addCommentBefore(statement, EcoreUtil.generateUUID());
			saveJaMoPPModel(statement.eResource());
		}
	}*/

	private void saveJaMoPPModel(Resource eResource) {
		try {
			eResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void processAfterRefactoring(Resource resource) {
//        if (resource instanceof IJavaTextResource) {
//            reloadResourceWithLayoutInformation(resource);
//        }
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
