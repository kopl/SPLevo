package org.splevo.ui.refinementbrowser;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.ui.jdt.JavaEditorConnector;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

public class RefinementDetailsView extends Composite {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(RefinementDetailsView.class);

	/** The internal tree viewer to present the refined variation points. */
	private TreeViewer variationPointTreeViewer;

	/**
	 * Constructor to create the view.
	 * 
	 * @param parent
	 *            The parent ui element to present the view in.
	 */
	public RefinementDetailsView(Composite parent) {
		super(parent, SWT.FILL);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		variationPointTreeViewer = new TreeViewer(this, SWT.BORDER);
		variationPointTreeViewer.setLabelProvider(new ViewerLabelProvider());
		variationPointTreeViewer.setContentProvider(new TreeContentProvider());

		initContextMenu();
	}

	/**
	 * Create contents of the details page.
	 * 
	 * @param parent
	 */
	public void createContents(Composite parent) {
		parent.setEnabled(true);

	}

	/**
	 * Trigger the presentation of a specific refinement in the view.
	 * 
	 * @param refinement
	 *            The refinement to show.
	 */
	public void showRefinement(Refinement refinement) {
		variationPointTreeViewer.setInput(refinement);
	}

	/**
	 * The content provider for the tree providing access to the variation
	 * points and their child elements
	 */
	private static class TreeContentProvider implements ITreeContentProvider {

		private Refinement refinement = null;

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.refinement = (Refinement) newInput;
		}

		public Object[] getElements(Object inputElement) {
			return refinement.getVariationPoints().toArray();
		}

		/**
		 * Get the children for a provided parent element.
		 * 
		 * @param parentElement
		 *            The parent element.
		 * @return The list of identified children (empty list if no children
		 *         exist).
		 */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof Refinement) {
				return ((Refinement) parentElement).getVariationPoints()
						.toArray();
			} else if (parentElement instanceof VariationPoint) {
				return ((VariationPoint) parentElement).getVariants().toArray();
			} else if (parentElement instanceof Variant) {
				return ((Variant) parentElement).getSoftwareEntities()
						.toArray();
			} else {
				return new Object[] {};
			}
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

		public void dispose() {
		}
	}

	/**
	 * Label Provider for a variation point element.
	 */
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		/**
		 * Get the text label for a supplied element.
		 */
		public String getText(Object element) {
			if (element instanceof VariationPoint) {
				return "VariationPoint in "
						+ ((VariationPoint) element).getSoftwareEntity()
								.getClass().getSimpleName();
			} else if (element instanceof Variant) {
				return "Variant: " + ((Variant) element).getVariantId();
			} else if (element instanceof ASTNode) {
				ASTNode astNode = (ASTNode) element;
				return astNode.getClass().getSimpleName() + "("
						+ astNode.getOriginalCompilationUnit().getName() + ")";
			} else {
				return super.getText(element);
			}
		}
	}
	
	/**
	 * initialize the context menu
	 */
	private void initContextMenu() {

		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager manager) {

				// trigger the action to navigate to the source
				// location and open the Java Editor at the according location
				Action action = new Action() {

					public void run() {
						Object object = (((StructuredSelection) (variationPointTreeViewer
								.getSelection()))).getFirstElement();

						if (object instanceof EObject) {
							EObject eObject = (EObject) object;
							ASTNode astNode = (ASTNode) eObject;

							Refinement refinement = (Refinement) variationPointTreeViewer
									.getInput();
							VariationPointGroup vpg = (VariationPointGroup) refinement
									.getVariationPoints().get(0).eContainer();
							VariationPointModel vpm = (VariationPointModel) vpg
									.eContainer();

							// FIXME: Use variant specific java application
							// model
							// At the moment, always the leading model is used
							// this needs to be specific for the selected note.
							SourceConnector sourceConnector = new SourceConnector(
									vpm.getLeadingModel());
							JavaNodeSourceRegion sourceRegion = sourceConnector
									.findSourceRegion(astNode);
							System.err.println("SourceNodeRegion:"
									+ sourceRegion);

							if (sourceRegion != null) {
								JavaEditorConnector javaEditorConnector = new JavaEditorConnector();
								javaEditorConnector.openJavaEditor(
										sourceRegion, true);
							} else {
								logger.warn("No SourceRegion accessible.");
							}
						} else {
							logger.warn("A non-eObject has been selected");
						}
					}
				};
				action.setText("Open Source in Editor");
				manager.add(action);
			}
		});
		Menu menu = menuMgr.createContextMenu(variationPointTreeViewer
				.getTree());
		variationPointTreeViewer.getTree().setMenu(menu);
		
	}

	/**
	 * Get the active site to work with.
	 * 
	 * @return The active site.
	 */
	private IWorkbenchPartSite getSite() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getPartService().getActivePart().getSite();
	}
}
