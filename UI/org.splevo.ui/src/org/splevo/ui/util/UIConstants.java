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
    
    /** Unified Difference: The marker type identifiers */
    public static final String MARKERTYPE_MIXED_DARK = "org.splevo.ui.markerTypes.unifieddiff.mixedDark";
    public static final String MARKERTYPE_MIXED_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.mixedLight";
    public static final String MARKERTYPE_INTEGRATION3_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration3Dark";
    public static final String MARKERTYPE_INTEGRATION3_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration3Light";
    public static final String MARKERTYPE_INTEGRATION2_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration2Dark";
    public static final String MARKERTYPE_INTEGRATION2_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration2Light";
    public static final String MARKERTYPE_INTEGRATION1_DARK = "org.splevo.ui.markerTypes.unifieddiff.integration1Dark";
    public static final String MARKERTYPE_INTEGRATION1_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.integration1Light";
    public static final String MARKERTYPE_LEADING_DARK = "org.splevo.ui.markerTypes.unifieddiff.leadingDark";
    public static final String MARKERTYPE_LEADING_LIGHT = "org.splevo.ui.markerTypes.unifieddiff.leadingLight";
    
    /** Unified Difference: The id to marker type mapping */
    public static final Map<Integer, MarkerType> INDEX_TO_MARKERTYPE;
    static {
        Map<Integer, MarkerType> mapping = new HashMap<Integer, MarkerType>();
        mapping.put(0, MarkerType.LEADING_DARK);
        mapping.put(1, MarkerType.LEADING_LIGHT);
        mapping.put(2, MarkerType.INTEGRATION1_DARK);
        mapping.put(3, MarkerType.INTEGRATION1_LIGHT);
        mapping.put(4, MarkerType.INTEGRATION2_DARK);
        mapping.put(5, MarkerType.INTEGRATION2_LIGHT);
        mapping.put(6, MarkerType.INTEGRATION3_DARK);
        mapping.put(7, MarkerType.INTEGRATION3_LIGHT);
        INDEX_TO_MARKERTYPE = Collections.unmodifiableMap(mapping);
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
