package org.splevo.ui.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.splevo.ui.sourceconnection.UnifiedDiffConnectorModel.MarkerType;

/**
 * A class that encapsulates a set of constants.
 * 
 * @author André Wengert
 */
public class UIConstants {
    /** Unified Difference: Working copy */
    public static final String TMP_FILE_NAME_WIHOUT_EXTENTION = "unifiedDiff_";
    public static final String TMP_FILE_EXTENTION = ".java"; // MUST be *.java or else java syntax highlighting won't work
    
    
    /** Unified Difference: The annotation identifiers */
    public static final String UNIFIED_DIFF_MARKERTYPE = "org.splevo.ui.markers.unifieddiff";
    public static final String ANNOTATION_MIXED_DARK = "org.splevo.ui.annotations.unifieddiff.mixedDark";
    public static final String ANNOTATION_MIXED_LIGHT = "org.splevo.ui.annotations.unifieddiff.mixedLight";
    public static final String ANNOTATION_INTEGRATION3_DARK = "org.splevo.ui.annotations.unifieddiff.integration3Dark";
    public static final String ANNOTATION_INTEGRATION3_LIGHT = "org.splevo.ui.annotations.unifieddiff.integration3Light";
    public static final String ANNOTATION_INTEGRATION2_DARK = "org.splevo.ui.annotations.unifieddiff.integration2Dark";
    public static final String ANNOTATION_INTEGRATION2_LIGHT = "org.splevo.ui.annotations.unifieddiff.integration2Light";
    public static final String ANNOTATION_INTEGRATION1_DARK = "org.splevo.ui.annotations.unifieddiff.integration1Dark";
    public static final String ANNOTATION_INTEGRATION1_LIGHT = "org.splevo.ui.annotations.unifieddiff.integration1Light";
    public static final String ANNOTATION_LEADING_DARK = "org.splevo.ui.annotations.unifieddiff.leadingDark";
    public static final String ANNOTATION_LEADING_LIGHT = "org.splevo.ui.annotations.unifieddiff.leadingLight";
    
    /** Unified Difference: Marker type to id mapping. */
    public static final Map<MarkerType, String> ANNOTATION_TO_ID;
    
    /** Unified Difference: The id to marker type mapping */
    public static final Map<Integer, MarkerType> INDEX_TO_MARKERTYPE;
    static {
        Map<Integer, MarkerType> indexTomarkerType = new HashMap<Integer, MarkerType>();
        indexTomarkerType.put(0, MarkerType.LEADING_DARK);
        indexTomarkerType.put(1, MarkerType.LEADING_LIGHT);
        indexTomarkerType.put(2, MarkerType.INTEGRATION1_DARK);
        indexTomarkerType.put(3, MarkerType.INTEGRATION1_LIGHT);
        indexTomarkerType.put(4, MarkerType.INTEGRATION2_DARK);
        indexTomarkerType.put(5, MarkerType.INTEGRATION2_LIGHT);
        indexTomarkerType.put(6, MarkerType.INTEGRATION3_DARK);
        indexTomarkerType.put(7, MarkerType.INTEGRATION3_LIGHT);
        INDEX_TO_MARKERTYPE = Collections.unmodifiableMap(indexTomarkerType);
        
        Map<MarkerType, String> annotationToId = new HashMap<MarkerType, String>();
        annotationToId.put(MarkerType.LEADING_DARK, ANNOTATION_LEADING_DARK);
        annotationToId.put(MarkerType.LEADING_LIGHT, ANNOTATION_LEADING_LIGHT);
        annotationToId.put(MarkerType.INTEGRATION1_DARK, ANNOTATION_INTEGRATION1_DARK);
        annotationToId.put(MarkerType.INTEGRATION1_LIGHT, ANNOTATION_INTEGRATION1_LIGHT);
        annotationToId.put(MarkerType.INTEGRATION2_DARK, ANNOTATION_INTEGRATION2_DARK);
        annotationToId.put(MarkerType.INTEGRATION2_LIGHT, ANNOTATION_INTEGRATION2_LIGHT);
        annotationToId.put(MarkerType.INTEGRATION3_DARK, ANNOTATION_INTEGRATION3_DARK);
        annotationToId.put(MarkerType.INTEGRATION3_LIGHT, ANNOTATION_INTEGRATION3_LIGHT);
        annotationToId.put(MarkerType.MIXED_DARK, ANNOTATION_MIXED_DARK);
        annotationToId.put(MarkerType.MIXED_LIGHT, ANNOTATION_MIXED_LIGHT);
        ANNOTATION_TO_ID = Collections.unmodifiableMap(annotationToId);
    }
    
    /** Unified Difference: The color keys for marker annotations */
    public static final String CKEY_MIXED_DARK = "mixedDarkAnnotation.color";
    public static final String CKEY_MIXED_LIGHT = "mixedLightAnnotation.color";
    public static final String CKEY_INTEGRATION3_DARK = "integration3DarkAnnotation.color";
    public static final String CKEY_INTEGRATION3_LIGHT = "integration3LightAnnotation.color";
    public static final String CKEY_INTEGRATION2_DARK = "integration2DarkAnnotation.color";
    public static final String CKEY_INTEGRATION2_LIGHT = "integration2LightAnnotation.color";
    public static final String CKEY_INTEGRATION1_DARK = "integration1DarkAnnotation.color";
    public static final String CKEY_INTEGRATION1_LIGHT = "integration1LightAnnotation.color";
    public static final String CKEY_LEADING_DARK = "leadingDarkAnnotation.color";
    public static final String CKEY_LEADING_LIGHT = "leadingLightAnnotation.color";
    
    /** Unified Difference: The id of the editor. */
    public static final String UNIFIED_DIFF_EDITOR_ID = "org.splevo.ui.editors.unifieddiffeditor"; //$NON-NLS-1$
    
}
