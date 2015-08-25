package org.splevo.ui.editors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.JFaceTextUtil;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;

public class UnifiedDiffRulerColumn extends LineNumberRulerColumn {
    /** A Logger instance */
    private static Logger LOGGER = Logger.getLogger(UnifiedDiffRulerColumn.class);
    /** Defines the right margin of the column */
    private final int COLUMN_RIGHT_MARGIN = 5;

    /** The index of the column inside the ruler */
    private int columnIndex;
    /** Line numbers indexed by column */
    private List<List<IndexedLineNumber>> indexedLineNumbersList = new ArrayList<List<IndexedLineNumber>>();
    /** The line to color mapping (used for the background color) */
    private Map<Integer, Color> linesToColorMapping;

    /**
     * Constructs a new unified difference ruler column.
     */
    public UnifiedDiffRulerColumn(int columnIndex, List<List<IndexedLineNumber>> indexedLineNumbersList,
            Map<Integer, Color> linesToColorMapping) {
        this.columnIndex = columnIndex;
        this.indexedLineNumbersList = indexedLineNumbersList;
        this.linesToColorMapping = linesToColorMapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintLine(int line, int y, int lineheight, GC gc, Display display) {
        int widgetLine = JFaceTextUtil.modelLineToWidgetLine(getParentRuler().getTextViewer(), line);

        // get the corresponding line number and background color for this column
        String text = "";
        Color bgColor = null;
        if (line < indexedLineNumbersList.size()) {
            bgColor = linesToColorMapping.get(line);
            List<IndexedLineNumber> curLine = indexedLineNumbersList.get(line);
            for (IndexedLineNumber lineNumber : curLine) {
                if (lineNumber.getColumnIndex() == columnIndex) {
                    text = createDisplayString(lineNumber.getNumber());
                }
            }
        }

        // get line indentation
        int[] indentations = null;
        try {
            indentations = getIndentations();
        } catch (Exception e) {
            LOGGER.error("Could not get unified column line indentation!", e);
        }
        
        // calculate proper positioning and draw number
        if (indentations != null) {
            int indentation = indentations[text.length()];
            int baselineBias = getBaselineBias(gc, widgetLine);
            if (bgColor != null) {
                gc.setBackground(bgColor);
                gc.drawString(text, indentation, y + baselineBias, false);
            } else {
                gc.drawString(text, indentation, y + baselineBias, true);
            }
        }
    }

    /**
     * Returns the difference between the baseline of the widget and the baseline as specified by
     * the font for <code>gc</code>. When drawing line numbers, the returned bias should be added to
     * obtain text lined up on the correct base line of the text widget.
     * 
     * @param gc
     *            the {@code GC} to get the font metrics from
     * @param widgetLine
     *            the widget line
     * @return the baseline bias to use when drawing text that is lined up with the text widget.
     */
    private int getBaselineBias(GC gc, int widgetLine) {
        ITextViewer textViewer = getParentRuler().getTextViewer();
        int offset = textViewer.getTextWidget().getOffsetAtLine(widgetLine);
        int widgetBaseline = textViewer.getTextWidget().getBaseline(offset);

        FontMetrics fm = gc.getFontMetrics();
        int fontBaseline = fm.getAscent() + fm.getLeading();
        int baselineBias = widgetBaseline - fontBaseline;
        return Math.max(0, baselineBias);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        int[] indentations = null;
        try {
            indentations = getIndentations();
        } catch (Exception e) {
            LOGGER.error("Could not get unified column width!", e);
        }
        return indentations[0] + COLUMN_RIGHT_MARGIN;
    }

    /**
     * Gets the private field {@code fIndentation} from the parent instance.
     * 
     * @return the field {@code fIndentation}.
     * @throws IllegalAccessException
     *             if the Field object is enforcing Java language access control and the field
     *             {@code fIndentation} is inaccessible.
     * @throws NoSuchFieldException
     *             if the field {@code fIndentation} is not found.
     */
    private int[] getIndentations() throws IllegalAccessException, NoSuchFieldException {
        // access private field via reflection
        Field privateField = LineNumberRulerColumn.class.getDeclaredField("fIndentation");
        privateField.setAccessible(true);
        int[] indentations = (int[]) privateField.get(this);
        privateField.setAccessible(false);
        return indentations;
    }
}
