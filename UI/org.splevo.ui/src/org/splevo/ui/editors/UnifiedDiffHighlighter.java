package org.splevo.ui.editors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SimpleMarkerAnnotation;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorModel;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorModel.MarkerType;
import org.splevo.ui.util.UIConstants;

/**
 * This class is used to highlight (create markers) certain lines in the unified difference editor.
 * 
 * @author André Wengert
 *
 */
@SuppressWarnings("restriction")
public class UnifiedDiffHighlighter {
    /** The Logger instance */
    private static final Logger LOGGER = Logger.getLogger(UnifiedDiffHighlighter.class);

    /** The unified difference editor. */
    private final UnifiedDiffEditor editor;
    /** The unified difference editor input. */
    private final UnifiedDiffEditorInput editorInput;
    /** A marker type to get-marker-color method mapping. */
    private Map<MarkerType, IGetMarkerColor> markerTypeToGetMarkerColor;
    
    /**
     * Constructs an instance of class {@link UnifiedDiffHighlighter}.
     * 
     * @param editor
     *            a unified difference editor.
     */
    public UnifiedDiffHighlighter(UnifiedDiffEditor editor) {
        this.editor = editor;
        this.editorInput = (UnifiedDiffEditorInput) editor.getEditorInput();
        
        // initialize mapping
        initializeMarkerMapping();
        
    }
    
    /**
     * Initializes the marker type mappings.
     */
    private void initializeMarkerMapping() {
        markerTypeToGetMarkerColor = new HashMap<MarkerType, IGetMarkerColor>();
        markerTypeToGetMarkerColor.put(MarkerType.LEADING_DARK, new GetLeadingDarkColor());
        markerTypeToGetMarkerColor.put(MarkerType.LEADING_LIGHT, new GetLeadingLightColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION1_DARK, new GetIntegration1DarkColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION1_LIGHT, new GetIntegration1LightColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION2_DARK, new GetIntegration2DarkColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION2_LIGHT, new GetIntegration2LightColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION3_DARK, new GetIntegration3DarkColor());
        markerTypeToGetMarkerColor.put(MarkerType.INTEGRATION3_LIGHT, new GetIntegration3LightColor());
        markerTypeToGetMarkerColor.put(MarkerType.MIXED_DARK, new GetMixedDarkColor());
        markerTypeToGetMarkerColor.put(MarkerType.MIXED_LIGHT, new GetMixedLightColor());
    }

    /**
     * Highlights the differences within the unified difference editor.
     * 
     * @return the color to unified lines mapping.
     */
    @SuppressWarnings({ "unchecked" })
    public Map<Integer, Color> highlightLines() {
        // initialize local variables
        UnifiedDiffConnectorModel diffModel = editorInput.getDiffConnectorModel();
        Map<Integer, Color> colorToUnifiedLinesMapping = new HashMap<Integer, Color>();
        MarkerAnnotationPreferences maPrefs = EditorsPlugin.getDefault().getMarkerAnnotationPreferences();
        List<AnnotationPreference> aPrefs = (List<AnnotationPreference>) (List<?>) maPrefs.getAnnotationPreferences();
        IFile ressource = (IFile) editorInput.getAdapter(IFile.class);

        // add new markers from connector content
        int offset = 0;
        int lineCount = editor.getViewer().getTextWidget().getLineCount();
        int lineTextLength = 0;
        String lineText = "";
        for (int line = 0; line < lineCount - 1; line++) {
            lineText = editor.getViewer().getTextWidget().getLine(line);
            lineTextLength = lineText.length();

            // create marker according to type
            MarkerType markerType = diffModel.getMarkerTypeFor(line);
            if (markerType != MarkerType.NONE) {
                try {
                    IMarker marker = ressource.createMarker(UIConstants.UNIFIED_DIFF_MARKERTYPE);
                    TextSelection selection = new TextSelection(offset, lineTextLength);
                    createAnnotation(marker, selection, this.editor, markerType);
                    
                } catch (CoreException exception) {
                    LOGGER.error("An error occurred while creating a marker or setting an attribute for said marker!",
                            exception);
                }

                // get and add color to mapping
                IGetMarkerColor getMarkerColor = markerTypeToGetMarkerColor.get(markerType);
                colorToUnifiedLinesMapping.put(line, getMarkerColor.get(aPrefs)); 
            }
            
            offset += lineTextLength + 1;
        }

        return colorToUnifiedLinesMapping;
    }
    
    /**
     * Creates the corresponding annotation for a given marker according to the given marker type.
     * 
     * @param marker the given marker.
     * @param selection the selection to highlight.
     * @param editor the underlying editor.
     * @param markerType the given marker type.
     */
    private void createAnnotation(IMarker marker, ITextSelection selection, ITextEditor editor, MarkerType markerType) {
        IDocumentProvider idp = editor.getDocumentProvider();
        IDocument document = idp.getDocument(editorInput);
        IAnnotationModel annotationModel = idp.getAnnotationModel(editorInput);
        String annotationType = UIConstants.ANNOTATION_TO_ID.get(markerType);
        SimpleMarkerAnnotation annotation = new SimpleMarkerAnnotation(annotationType, marker);
        annotationModel.connect(document);
        annotationModel.addAnnotation(annotation, new Position(selection.getOffset(), selection.getLength()));
        annotationModel.disconnect(document);        
    }

    /* -----------------------------
     * CreateMarker strategy classes
     * ----------------------------- */
    
    /**
     * Interface for the strategy to create specific markers.
     * 
     * @author André Wengert
     */
    public interface ICreateMarkerMethod {
        /**
         * Creates a specific marker for the given resource.
         * 
         * @param resource
         *            the given resource.
         * @return the marker instance.
         * @throws CoreException
         *             in case the given resource does not exist.
         */
        IMarker create(IFile resource) throws CoreException;
    }

    /* -------------------------------
     * GetMarkerColor strategy classes
     * ------------------------------- */
    
    /**
     * Interface for the strategy to get a specific marker color from the annotation preferences.
     * 
     * @author André Wengert
     */
    public interface IGetMarkerColor {
        /**
         * Gets the specific marker color from the given annotation preferences.
         * 
         * @param annotationPreferences
         *            the given annotation preferences.
         * @return the marker color.
         */
        Color get(List<AnnotationPreference> annotationPreferences);
    }

    /**
     * Strategy implementation to get a marker color for a line of interest that only exists in the leading copy.
     * 
     * @author André Wengert
     */
    public class GetLeadingDarkColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_LEADING_DARK, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line that only exists in the leading copy.
     * 
     * @author André Wengert
     */
    public class GetLeadingLightColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_LEADING_LIGHT, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line of interest that only exists in the first integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration1DarkColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION1_DARK, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line that only exists in the first integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration1LightColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION1_LIGHT, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line of interest that only exists in the second integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration2DarkColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION2_DARK, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line that only exists in the second integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration2LightColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION2_LIGHT, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line of interest that only exists in the third integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration3DarkColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION3_DARK, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line that only exists in the third integration copy.
     * 
     * @author André Wengert
     */
    public class GetIntegration3LightColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_INTEGRATION3_LIGHT, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line of interest that exists in multiple copies but not the leading
     * copy.
     * 
     * @author André Wengert
     */
    public class GetMixedDarkColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_MIXED_DARK, annotationPreferences);
        }
    }
    
    /**
     * Strategy implementation to get a marker color for a line that exists in multiple copies but not the leading copy.
     * 
     * @author André Wengert
     */
    public class GetMixedLightColor implements IGetMarkerColor {
        @Override
        public Color get(List<AnnotationPreference> annotationPreferences) {
            return getColorValueFor(UIConstants.CKEY_MIXED_LIGHT, annotationPreferences);
        }
    }
    
    /**
     * Gets the respective color value from the annotation preferences based on the given color key.
     * 
     * @param colorKey
     *            the given color key.
     * @param annotationPreferences
     *            the annotation preferences.
     * @return the color.
     */
    private Color getColorValueFor(String colorKey, List<AnnotationPreference> annotationPreferences) {
        for (AnnotationPreference aPref : annotationPreferences) {
            if (colorKey.equals(aPref.getColorPreferenceKey())) {
                RGB rgb = aPref.getColorPreferenceValue();
                return new Color(Display.getCurrent(), rgb.red, rgb.green, rgb.blue);
            }
        }
        return null;
    }
}
