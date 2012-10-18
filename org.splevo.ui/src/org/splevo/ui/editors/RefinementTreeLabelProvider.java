package org.splevo.ui.editors;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.provider.RefinementItemProvider;
import org.splevo.vpm.refinement.provider.RefinementItemProviderAdapterFactory;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.provider.VariationPointItemProvider;
import org.splevo.vpm.variability.provider.variabilityItemProviderAdapterFactory;

public class RefinementTreeLabelProvider extends LabelProvider {
	
	/** Mapping to cache images loaded. */
	HashMap<Object, Image> imageMapping = new HashMap<Object, Image>();
	
	  @Override
	  public String getText(Object element) {
	    if (element instanceof VPMAnalysisConfiguration) {
	    	VPMAnalysisConfiguration analysisConfig = (VPMAnalysisConfiguration) element;
	      return analysisConfig.getAnalyzer().getName();
	    } else if (element instanceof Refinement) {
	    	//TODO optimize with EMF generated itemprovider
			// MergeItemProvider itemProvider = new MergeItemProvider(new RefinementItemProviderAdapterFactory());
			// return itemProvider.getText((Merge) element);
	    	Refinement refinement = (Refinement) element;
	    	StringBuilder labelBuilder = new StringBuilder();
	    	if(refinement.getType() == RefinementType.GROUPING){
	    		labelBuilder.append("Grouping (");
	    	} else {
	    		labelBuilder.append("Merge (");
	    	}
	    	labelBuilder.append(((Refinement) element).getVariationPoints().size());
	    	labelBuilder.append(" VPs)");
	    	return labelBuilder.toString();
	    } else if (element instanceof VariationPoint) {
	    	VariationPointItemProvider itemProvider = new VariationPointItemProvider(new variabilityItemProviderAdapterFactory());
	    	return itemProvider.getText((VariationPoint) element);
	    } else {
	    	throw new RuntimeException("Unknown Refiment Type: "+element.getClass());
	    }
	  }

	  @Override
	  public Image getImage(Object element) {
	    if (element instanceof VPMAnalysisConfiguration) {
	    	//TODO optimize with EMF generated itemprovider
	      return PlatformUI.getWorkbench().getSharedImages()
	          .getImage(ISharedImages.IMG_OBJ_FOLDER);
	    
	    } else if (element instanceof Refinement) {
	    	Refinement refinement = (Refinement) element;
	    	if(!imageMapping.containsKey(refinement.getType())){
	    		RefinementItemProvider itemProvider = new RefinementItemProvider(new RefinementItemProviderAdapterFactory());
		    	ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL((URL) itemProvider.getImage(element));
		    	imageMapping.put(refinement.getType(), imageDescriptor.createImage());
	    	}
	    	return imageMapping.get(refinement.getType());

	    } else if (element instanceof VariationPoint) {
	    	if(!imageMapping.containsKey(VPMAnalysisConfiguration.class)){
		    	VariationPointItemProvider itemProvider = new VariationPointItemProvider(new variabilityItemProviderAdapterFactory());
		    	ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL((URL) itemProvider.getImage(element));
		    	imageMapping.put(VPMAnalysisConfiguration.class, imageDescriptor.createImage());
	    	}
	    	return imageMapping.get(VPMAnalysisConfiguration.class);
	    }
    	//TODO optimize with EMF generated itemprovider
	    return PlatformUI.getWorkbench().getSharedImages()
	    .getImage(ISharedImages.IMG_OBJ_FILE);
	  }

}
