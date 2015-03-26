package org.splevo.refactoring.cheatsheets.images;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.cheatsheets.AbstractItemExtensionElement;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.splevo.refactoring.cheatsheets.RefactoringSheetsPlugin;

/**
 * This class implements an cheat sheet item extension to contribute custom images to cheat sheet steps.
 * 
 * To define the an image step:
 * <ul>
 * <li>store the image in the resources of this plug-in</li>
 * <li>annotate the step definition in the cheat sheet definition with an addition attribute 'imageStep' pointing to the
 * plug-in relative path of the image
 * </ul>
 * 
 * 
 * @author cwende
 *
 */
public class ImageStepElement extends AbstractItemExtensionElement implements PaintListener {

	private static final int MAX_IMAGE_WIDTH = 750;

	public ImageStepElement(String attributeName) {
		super(attributeName);
	}

	private String attributeValue;
	private Section section;
	private boolean itemWasAdded;

	@Override
	public void handleAttribute(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public void createControl(Composite composite) {
		section = (Section) composite.getParent();
		if (section != null) {
			// we add a listener that reacts on painting the affected section
			// it will add the image to the section body.
			section.addPaintListener(this);
		}

	}

	@Override
	public void dispose() {
		if (section != null && !section.isDisposed()) {
			section.removePaintListener(this);
		}
	}

	@Override
	public void paintControl(PaintEvent e) {
		Control client = section.getClient();
		// if the body of the section is initialized and we have
		// not added the image to this body yet
		// we will do so now
		if (client != null && !itemWasAdded) {
			itemWasAdded = true;
			Composite bodyComposite = (Composite) section.getClient();
			Image image = getStepImage();
			if (image == null) {
				Label label = new Label(bodyComposite, SWT.BORDER);
				label.setText("Unknown step image for url: " + attributeValue);
				return;
			}

			Label label = new Label(bodyComposite, SWT.BORDER | SWT.LEFT);
			label.setBackground(bodyComposite.getBackground());
			label.setImage(image);

			Control[] children = bodyComposite.getChildren();
			if (children.length > 0) {
				label.moveAbove(children[0]);
			}

			if (section != null) {
				section.removePaintListener(this);
			}
		}

	}

	/**
	 * Retrieve the steps image. If the image is not in the registry yet, register it first
	 * 
	 * @return the steps image.
	 */
	private Image getStepImage() {
		AbstractUIPlugin plugin = RefactoringSheetsPlugin.getDefault();
		ImageRegistry registry = plugin.getImageRegistry();

		Image image = registry.get(attributeValue);
		if (image == null) {
			Bundle bundle = Platform.getBundle(RefactoringSheetsPlugin.PLUGIN_ID);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(
					attributeValue), null));
			if (imageDescriptor != null) {
				registry.put(attributeValue, imageDescriptor);
			}
			image = registry.get(attributeValue);

		}
		int width = image.getImageData().width;
		if (width > MAX_IMAGE_WIDTH) {

			int height = image.getImageData().height;

			double scaleFactor = new Double(MAX_IMAGE_WIDTH) / width;
			final Image scaledTo300 = new Image(Display.getCurrent(), image.getImageData().scaledTo(
					(int) (width * scaleFactor), (int) (height * scaleFactor)));
			return scaledTo300;
		}

		return image;
	}
}
