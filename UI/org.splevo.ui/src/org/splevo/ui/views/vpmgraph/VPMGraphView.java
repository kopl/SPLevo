package org.splevo.ui.views.vpmgraph;

import java.awt.Frame;
import java.util.Random;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.CloseFramePolicy;
import org.splevo.vpm.analyzer.graph.CustomEdgeLabelAttributeProxy;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * A viewer for a variation point model graph.
 * 
 * @author Benjamin Klatt
 * 
 */
public class VPMGraphView extends ViewPart {

    /** The id of the viewer. */
    public static final String ID = "org.splevo.ui.viewer.vpmgraph.VPMGraphViewer"; //$NON-NLS-1$

    /** The graph to present. */
    private VPMGraph vpmGraph = null;

    /** The toolkit for ui creation and management. */
    private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

    /** The root pane to draw content on. */
    private Frame frame = null;

    /** Action to filter nodes without any relationships from the graph. */
    private FilterSingleNodeAction filterSingleNodesAction;

    /** The default constructor. */
    public VPMGraphView() {
    }

    /**
     * Show a graph in this view.
     * 
     * @param graph
     *            The graph to show.
     */
    public void showGraph(VPMGraph graph) {
        createGraphViewer(graph);
    }

    /**
     * Create the graph viewer inside the view.
     * 
     * @param graph
     *            The graph to show.
     */
    private void createGraphViewer(VPMGraph graph) {
        this.vpmGraph = graph;

        setTitleToolTip("Graph: " + graph.getId());

        // create the graph viewer
        CustomEdgeLabelAttributeProxy proxy = new CustomEdgeLabelAttributeProxy(vpmGraph,
                RelationshipEdge.RELATIONSHIP_LABEL);
        Viewer v = new Viewer(proxy);
        // Viewer v = new Viewer(vpmGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        SpringBox layout = new SpringBox(false, new Random(0));
        v.enableAutoLayout(layout);
        v.setCloseFramePolicy(CloseFramePolicy.HIDE_ONLY);
        View view = v.addDefaultView(false);
        frame.add(view);

        setFocus();

    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     *            The parent ui element to create the view in.
     */
    @Override
    public void createPartControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.EMBEDDED);
        formToolkit.adapt(composite);
        formToolkit.paintBordersFor(composite);
        frame = SWT_AWT.new_Frame(composite);

        createActions();
        initializeToolBar();
        initializeMenu();
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        filterSingleNodesAction = new FilterSingleNodeAction(this);
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
        IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
        toolbarManager.add(filterSingleNodesAction);
    }

    /**
     * Initialize the menu.
     */
    @SuppressWarnings("unused")
    private void initializeMenu() {
        IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
    }

    @Override
    public void setFocus() {
        // Set the focus
    }

    /**
     * Gets the graph to present.
     * 
     * @return the vpmGraph
     */
    public VPMGraph getVpmGraph() {
        return vpmGraph;
    }
}
