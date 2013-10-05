package org.splevo.ui.views.vpmgraph;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.util.Random;

import javax.swing.JRootPane;

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

    /** Action to hide the edge labels. */
    private HideEdgeLabelAction hideEdgeLabelAction;

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

        // setting to enable an improved graph renderer
        // java.lang.System.setProperty("org.graphstream.ui.renderer",
        // "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        this.vpmGraph = graph;

        setTitleToolTip("Graph: " + graph.getId());

        // create the graph viewer
        CustomEdgeLabelAttributeProxy proxy = new CustomEdgeLabelAttributeProxy(vpmGraph,
                RelationshipEdge.RELATIONSHIP_LABEL);
        Viewer v = new Viewer(proxy);

        SpringBox layout = new SpringBox(false, new Random(0));
        v.enableAutoLayout(layout);
        v.setCloseFramePolicy(CloseFramePolicy.HIDE_ONLY);
        View view = v.addDefaultView(false);
        view.setFocusTraversalKeysEnabled(true);

        Panel panel = new Panel(new BorderLayout()) {

            private static final long serialVersionUID = 1L;

            public void update(java.awt.Graphics g) {
                /* Do not erase the background */
                paint(g);
            }
        };
        JRootPane root = new JRootPane();
        panel.add(root);

        root.getContentPane().add(view);

        frame.add(panel);
        frame.setVisible(true);

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

        Composite composite = new Composite(parent, SWT.NO_BACKGROUND | SWT.EMBEDDED);
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
        hideEdgeLabelAction = new HideEdgeLabelAction(this);
    }

    /**
     * Initialize the tool bar.
     */
    private void initializeToolBar() {
        IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
        toolbarManager.add(filterSingleNodesAction);
        toolbarManager.add(hideEdgeLabelAction);
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
