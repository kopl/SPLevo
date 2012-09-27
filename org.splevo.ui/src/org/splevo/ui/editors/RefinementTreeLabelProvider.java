package org.splevo.ui.editors;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Grouping;
import org.splevo.vpm.refinement.Merge;
import org.splevo.vpm.refinement.provider.GroupingItemProvider;
import org.splevo.vpm.refinement.provider.MergeItemProvider;
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
	    } else if (element instanceof Grouping) {
	    	//TODO optimize with EMF generated itemprovider
	    	return "Grouping ("+((Grouping) element).getVariationPoints().size()+" VPs)";
	    } else if (element instanceof Merge) {
	    	//TODO optimize with EMF generated itemprovider
//	    	MergeItemProvider itemProvider = new MergeItemProvider(new RefinementItemProviderAdapterFactory());
//	    	return itemProvider.getText((Merge) element);
	    	return "Merge ("+((Merge) element).getVariationPoints().size()+" VPs)";
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
	    
	    } else if (element instanceof Grouping) {
	    	if(!imageMapping.containsKey(Grouping.class)){
	    		GroupingItemProvider itemProvider = new GroupingItemProvider(new RefinementItemProviderAdapterFactory());
		    	ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL((URL) itemProvider.getImage(element));
		    	imageMapping.put(Grouping.class, imageDescriptor.createImage());
	    	}
	    	return imageMapping.get(Grouping.class);

	    } else if (element instanceof Merge) {
	    	if(!imageMapping.containsKey(Merge.class)){
	    		MergeItemProvider itemProvider = new MergeItemProvider(new RefinementItemProviderAdapterFactory());
		    	ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL((URL) itemProvider.getImage(element));
		    	imageMapping.put(Merge.class, imageDescriptor.createImage());
	    	}
	    	return imageMapping.get(Merge.class);

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
