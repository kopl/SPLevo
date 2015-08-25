package org.splevo.ui.editors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorContent;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorContent.MarkerType;

/**
 * Implementation of a unified difference editor.
 * 
 * @author André Wengert
 */
@SuppressWarnings("restriction")
public class UnifiedDiffEditor extends TextEditor {
    /** The marker type identifiers */
    private final String MARKERTYPE_MIXED_DARK = "org.splevo.ui.markerTypes.unifieddiff.mixedDark";
    private final String MARKERTYPE_MIXED_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.mixedLight";
    private final String MARKERTYPE_INTEGRATION3_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration3Dark";
    private final String MARKERTYPE_INTEGRATION3_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration3Light";
    private final String MARKERTYPE_INTEGRATION2_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration2Dark";
    private final String MARKERTYPE_INTEGRATION2_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration2Light";
    private final String MARKERTYPE_INTEGRATION1_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration1Dark";
    private final String MARKERTYPE_INTEGRATION1_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration1Light";
    private final String MARKERTYPE_LEADING_DARK = "org.splevo.ui.markerTypes.unifieddiff.leadingDark";
    private final String MARKERTYPE_LEADING_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.leadingLight";
    /** The color keys for marker annotations */
    private final String CKEY_MIXED_DARK = "mixedDarkAnnotation.color";
    private final String CKEY_MIXED_LIGHT = "mixedLightAnnotation.color";
    private final String CKEY_INTEGRATION3_DARK = "integration3DarkAnnotation.color";
    private final String CKEY_INTEGRATION3_LIGHT = "integration3LightAnnotation.color";
    private final String CKEY_INTEGRATION2_DARK = "integration2DarkAnnotation.color";
    private final String CKEY_INTEGRATION2_LIGHT = "integration2LightAnnotation.color";
    private final String CKEY_INTEGRATION1_DARK = "integration1DarkAnnotation.color";
    private final String CKEY_INTEGRATION1_LIGHT = "integration1LightAnnotation.color";
    private final String CKEY_LEADING_DARK = "leadingDarkAnnotation.color";
    private final String CKEY_LEADING_LIGHT = "leadingLightAnnotation.color";
    /** The id of the editor. */
    public static final String ID = "org.splevo.ui.editors.unifieddiffeditor"; //$NON-NLS-1$
    /** A Logger instance */
    private static Logger LOGGER = Logger.getLogger(UnifiedDiffEditor.class);

    private UnifiedDiffEditorInput input;

    /** A reference to the editor ruler. */
    private CompositeRuler ruler;

    /**
     * Constructs an instance of class {@link UnifiedDiffEditor}.
     */
    public UnifiedDiffEditor() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeEditor() {
        super.initializeEditor();

        // install java source viewer configuration to allow java syntax highlighting
        JavaTextTools javaTextTools = JavaPlugin.getDefault().getJavaTextTools();
        JavaSourceViewerConfiguration sourceViewerConfiguration = new JavaSourceViewerConfiguration(
                javaTextTools.getColorManager(), JavaPlugin.getDefault().getCombinedPreferenceStore(), this,
                IJavaPartitions.JAVA_PARTITIONING);
        setSourceViewerConfiguration(sourceViewerConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        // Do nothing when 'Save' action is performed
        monitor.done();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
        // Do nothing when 'Save As' action is performed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDirty() {
        // Never mark editable file as dirty (="content has changed")
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEditable() {
        // Mark Editor as not editable.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSaveAsAllowed() {
        // Saving is not allowed
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();

        // delete working copy
        if (input instanceof UnifiedDiffEditorInput) {
            try {
                ((UnifiedDiffEditorInput) input).deleteWorkingCopy();
            } catch (CoreException e) {
                LOGGER.warn("Error trying to delete the unified difference working copy!", e);
            }
        } else {
            LOGGER.warn("The editor input of the UnifiedDiffEditor instance is not of type UnifiedDiffEditorInput."
                    + "The working unified difference working copy cannot be deleted!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editorContextMenuAboutToShow(IMenuManager menu) {
        menu.removeAll();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        // get the editor input
        if (getEditorInput() instanceof UnifiedDiffEditorInput) {
            input = (UnifiedDiffEditorInput) getEditorInput();
        } else {
            LOGGER.warn("The editor input of the UnifiedDiffEditor instance is not of type UnifiedDiffEditorInput but of type "
                    + getEditorInput().getClass().getSimpleName()
                    + "! This may lead to some operations not functioning as " + "intended.");
        }

        if (input instanceof UnifiedDiffEditorInput) {
            // set part name
            setPartName(((UnifiedDiffEditorInput) input).getName());

            // create line markers (difference highlights) and extract colors
            UnifiedDiffConnectorContent content = input.getUnfiedConnectorContent();
            Map<Integer, Color> unifiedLinesToColorMapping = highlightLinesFrom(content);

            // create unified columns
            int columnCount = content.getIntegrationCopyCount() + 1; // integration copies + leading
            for (int i = 0; i < columnCount; i++) {
//                TestColumn column = new TestColumn(i, content.getUnifiedLineNumbers(), unifiedLinesToColorMapping);
                UnifiedDiffRulerColumn column = new UnifiedDiffRulerColumn(i, content.getUnifiedLineNumbers(),
                        unifiedLinesToColorMapping);
                ruler.addDecorator(i, column);
            }
        } else {
            LOGGER.warn("The editor input of the UnifiedDiffEditor instance is not of type UnifiedDiffEditorInput!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IVerticalRuler createVerticalRuler() {
        // main purpose of this override is to get a reference to the vertical ruler object
        ruler = new CompositeRuler();
        return ruler;
    }

    /**
     * Highlights the current editor document according to the given connector content.
     * 
     * @param connectorContent
     *            the connector content.
     */
    private Map<Integer, Color> highlightLinesFrom(UnifiedDiffConnectorContent connectorContent) {
        // initialize local variables
        Map<Integer, Color> colorToUnifiedLinesMapping = new HashMap<Integer, Color>();
        MarkerAnnotationPreferences maPrefs = EditorsPlugin.getDefault().getMarkerAnnotationPreferences();
        @SuppressWarnings("unchecked")
        List<AnnotationPreference> aPrefs = (List<AnnotationPreference>) (List<?>) maPrefs.getAnnotationPreferences();
        IFile ressource = (IFile) getEditorInput().getAdapter(IFile.class);

        // add new markers from connector content
        int offset = 0;
        int lineCount = getSourceViewer().getTextWidget().getLineCount();
        int lineTextLength = 0;
        String lineText = "";
        for (int line = 0; line < lineCount - 1; line++) {
            lineText = getSourceViewer().getTextWidget().getLine(line);
            lineTextLength = lineText.length();

            // create marker according to type
            MarkerType markerType = connectorContent.getMarkerTypeFor(line);
            IMarker marker;
            try {
                // paint line according to type
                marker = createMarkerOfType(markerType, ressource);
                if (marker != null) {
                    marker.setAttribute(IMarker.CHAR_START, offset);
                    marker.setAttribute(IMarker.CHAR_END, offset + lineTextLength);
                }
            } catch (CoreException e) {
                LOGGER.error("Error while creating marker or setting an attribute for said marker!", e);
            }
            offset += lineTextLength + 1;

            // add color to mapping
            colorToUnifiedLinesMapping.put(line, getMarkerColor(markerType, aPrefs));
        }

        return colorToUnifiedLinesMapping;
    }

    /**
     * Creates a marker of given type for the given resource.
     * 
     * @param markerType
     *            the type of marker.
     * @param ressource
     *            the resource to add the marker to.
     * @return the created marker.
     * @throws CoreException
     *             in case the marker could not be created for the resource.
     */
    private IMarker createMarkerOfType(MarkerType markerType, IFile ressource) throws CoreException {
        IMarker marker = null;
        switch (markerType) {
        case LEADING_LIGHT:
            marker = ressource.createMarker(MARKERTYPE_LEADING_LIGHT);
            break;
        case LEADING_DARK:
            marker = ressource.createMarker(MARKERTYPE_LEADING_DARK);
            break;

        case INTEGRATION1_LIGHT:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION1_LIGHT);
            break;
        case INTEGRATION1_DARK:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION1_DARK);
            break;

        case INTEGRATION2_LIGHT:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION2_LIGHT);
            break;
        case INTEGRATION2_DARK:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION2_DARK);
            break;

        case INTEGRATION3_LIGHT:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION3_LIGHT);
            break;
        case INTEGRATION3_DARK:
            marker = ressource.createMarker(MARKERTYPE_INTEGRATION3_DARK);
            break;

        case MIXED_LIGHT:
            marker = ressource.createMarker(MARKERTYPE_MIXED_LIGHT);
            break;
        case MIXED_DARK:
            marker = ressource.createMarker(MARKERTYPE_MIXED_DARK);
            break;

        default:
            break;
        }

        return marker;
    }

    /**
     * Retrieves the respective marker color based on the given marker type from the annotation
     * preferences.
     * 
     * @param markerType
     *            the given marker type.
     * @param annotationPreferences
     *            the annotation preferences.
     * @return the marker color.
     */
    private Color getMarkerColor(MarkerType markerType, List<AnnotationPreference> annotationPreferences) {

        Color color = null;
        switch (markerType) {
        case LEADING_LIGHT:
            color = getColorValueFor(CKEY_LEADING_LIGHT, annotationPreferences);
            break;
        case LEADING_DARK:
            color = getColorValueFor(CKEY_LEADING_DARK, annotationPreferences);
            break;

        case INTEGRATION1_LIGHT:
            color = getColorValueFor(CKEY_INTEGRATION1_LIGHT, annotationPreferences);
            break;
        case INTEGRATION1_DARK:
            color = getColorValueFor(CKEY_INTEGRATION1_DARK, annotationPreferences);
            break;

        case INTEGRATION2_LIGHT:
            color = getColorValueFor(CKEY_INTEGRATION2_LIGHT, annotationPreferences);
            break;
        case INTEGRATION2_DARK:
            color = getColorValueFor(CKEY_INTEGRATION2_DARK, annotationPreferences);
            break;

        case INTEGRATION3_LIGHT:
            color = getColorValueFor(CKEY_INTEGRATION3_LIGHT, annotationPreferences);
            break;
        case INTEGRATION3_DARK:
            color = getColorValueFor(CKEY_INTEGRATION3_DARK, annotationPreferences);
            break;

        case MIXED_LIGHT:
            color = getColorValueFor(CKEY_MIXED_LIGHT, annotationPreferences);
            break;
        case MIXED_DARK:
            color = getColorValueFor(CKEY_MIXED_DARK, annotationPreferences);
            break;

        default:
            break;
        }

        return color;
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

    /**
     * Gets the unified difference connector content.
     * 
     * @return the unified difference connector content.
     */
    public UnifiedDiffConnectorContent getConnectorContent() {
        if (input != null && input instanceof UnifiedDiffEditorInput) {
            return input.getUnfiedConnectorContent();
        } else if (input == null && getEditorInput() instanceof UnifiedDiffEditorInput) {
            return ((UnifiedDiffEditorInput) getEditorInput()).getUnfiedConnectorContent();
        } else {
            LOGGER.error("The editor input of the UnifiedDiffEditor is not set or the instance is not of type"
                    + "UnifiedDiffEditorInput!");
            return null;
        }
    }
}
