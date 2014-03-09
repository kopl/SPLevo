package org.splevo.ui.refinementbrowser;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.provider.RefinementItemProvider;
import org.splevo.vpm.refinement.provider.RefinementItemProviderAdapterFactory;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * A label provider for the nodes presented in the refinement tree.
 * 
 * @author Christian Busch - based heavily on work of Benjamin Klatt
 * 
 */
public class CompleteRefinementTreeLabelProvider extends LabelProvider {

    /** Mapping to cache images loaded. */
    private final HashMap<Object, Image> imageMapping = new HashMap<Object, Image>();

    @Override
    public String getText(final Object element) {
        if (element instanceof Refinement) {
            // TODO optimize with EMF generated itemprovider
            // MergeItemProvider itemProvider = new MergeItemProvider(new
            // RefinementItemProviderAdapterFactory());
            // return itemProvider.getText((Merge) element);
            final Refinement refinement = (Refinement) element;
            final StringBuilder labelBuilder = new StringBuilder();
            if (refinement.getType() == RefinementType.GROUPING) {
                labelBuilder.append("Grouping (");
            } else {
                labelBuilder.append("Merge (");
            }
            labelBuilder.append(((Refinement) element).getVariationPoints().size());
            labelBuilder.append(" VPs)");
            return labelBuilder.toString();

        } else if (element instanceof VariationPoint) {
            return this.buildVariationPointLabel((VariationPoint) element);

        } else if (element instanceof Variant) {
            return "Variant: " + ((Variant) element).getVariantId();

        } else if (element instanceof SoftwareElement) {
            return ((SoftwareElement) element).getLabel();

        } else {
            throw new RuntimeException("Unknown Type: " + element.getClass());
        }
    }

    @Override
    public Image getImage(final Object element) {
        if (element instanceof Refinement) {
            final Refinement refinement = (Refinement) element;
            if (!this.imageMapping.containsKey(refinement.getType())) {
                final RefinementItemProvider itemProvider = new RefinementItemProvider(
                        new RefinementItemProviderAdapterFactory());
                final ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL((URL) itemProvider
                        .getImage(element));
                this.imageMapping.put(refinement.getType(), imageDescriptor.createImage());
            }
            return this.imageMapping.get(refinement.getType());
        }
        // TODO optimize with EMF generated itemprovider
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }

    /**
     * Builds the variation point label.
     * 
     * @param variationPoint
     *            the element
     * @return the string
     */
    private String buildVariationPointLabel(final VariationPoint variationPoint) {
        final SoftwareElement softwareElement = variationPoint.getLocation();
        return String.format("VariationPoint in %s", softwareElement.getLabel());
    }

}
