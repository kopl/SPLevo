import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.thread.ThreadProxyPipe;
import org.graphstream.ui.swingViewer.Viewer;

public class CustomLabel extends ThreadProxyPipe {

    /** graph. */
    private Graph graph;
    /** label key. */
    private String labelKey;

    /**
     * Blah.
     * 
     * @param graph
     *            ijopij
     * @param labelKey
     *            wfwef
     */
    public CustomLabel(final Graph graph, final String labelKey) {
        super(graph, true);
        System.err.println("called CustomLabel()");
        this.graph = graph;
        this.labelKey = labelKey;
    }

    @Override
    public void sendNodeAttributeAdded(String sourceId, long timeId, String nodeId, String attribute, Object value) {
        if (attribute.equals(this.labelKey)) {
            attribute = "ui.label";
            System.err.println("Attribute id switched from " + this.labelKey + " to ui.label");
        }
        super.sendNodeAttributeAdded(sourceId, timeId, nodeId, attribute, value);
    }

    public static void main(final String[] args) throws Exception {
        final Graph g = new DefaultGraph("g");
        final Node a = g.addNode("A");
        a.setAttribute("ui.label", "A");
        a.setAttribute("myLabel", "I'm A");

        final Viewer v = new Viewer(new CustomLabel(g, "myLabel"));
        v.enableAutoLayout();
        v.addDefaultView(true);
    }
}
