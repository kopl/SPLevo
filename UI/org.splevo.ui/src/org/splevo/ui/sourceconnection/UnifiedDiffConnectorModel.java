package org.splevo.ui.sourceconnection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.splevo.commons.emf.SPLevoResourceSet;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.sourceconnection.helper.FileLineNumberPair;
import org.splevo.ui.sourceconnection.helper.FileWithID;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;
import org.splevo.ui.sourceconnection.helper.NumbersTextPair;
import org.splevo.ui.sourceconnection.helper.UnifiedPOI;
import org.splevo.ui.util.CollectionUtil;
import org.splevo.ui.util.UIConstants;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * This class represents a model for the {@link UnifiedDiffConnector}. It envelops all the data
 * being processed and offers means to refine and access them.
 * 
 * @author Andre
 */
public class UnifiedDiffConnectorModel {
    /** The type of marker used for highlighting. */
    public enum MarkerType {
        NONE,
        LEADING_LIGHT,
        LEADING_DARK,
        INTEGRATION1_LIGHT,
        INTEGRATION1_DARK,
        INTEGRATION2_LIGHT,
        INTEGRATION2_DARK,
        INTEGRATION3_LIGHT,
        INTEGRATION3_DARK,
        MIXED_LIGHT,
        MIXED_DARK
    }

    /** The Logger instance */
    private static final Logger LOGGER = Logger.getLogger(UnifiedDiffConnectorModel.class);

    /** The working copy for the unified difference */
    private File unifiedDiffFile;
    /** The name of the currently processed file */
    private String proccessedFileName;
    /** The set of variation points */
    private List<VariationPoint> variationPoints;
    /** The id to variants mapping */
    private Multimap<String, Variant> idToVariants;
    /** The leading file */
    private FileWithID leadingFile;
    /** List of integration copy files */
    private List<FileWithID> integrationCopyList;
    /** List of POIs for the leading file */
    private List<UnifiedPOI> leadingPOIs;
    /** List of POIs for the integration copy files */
    private List<UnifiedPOI> integrationPOIs;
    /** List of lines for the unified document */
    private List<NumbersTextPair> unifiedLines;

    /**
     * Constructs an instance of class {@link UnifiedDiffConnectorModel}.
     * 
     * @param splevoProject
     *            a reference to the current SPLevoProject instance.
     * @param variants
     *            a reference to the variants being processed.
     * @param unifiedDiffFile
     *            a reference to the unified difference working copy
     * @param fileName 
     */
    public UnifiedDiffConnectorModel(SPLevoProject splevoProject, Set<Variant> variants, File unifiedDiffFile, String fileName) {
        this.unifiedDiffFile = unifiedDiffFile;
        this.proccessedFileName = fileName;
        
        // extract file information
        VariationPointModel vpm = getVPM(splevoProject);
        this.variationPoints = extractVariationPoints(vpm);
        //this.proccessedFileName = getFileNameToProcess(variants);
        this.idToVariants = createIdToVariantMapping(variationPoints);
        this.leadingFile = extractLeadingFile();
        this.integrationCopyList = extractIntegrationFiles();
        
        // extract points of interest (for darker highlighting)
        leadingPOIs = new ArrayList<UnifiedPOI>();
        integrationPOIs = new ArrayList<UnifiedPOI>();
        for (Variant var : variants) {
            IPath path = Path.fromOSString(var.getImplementingElements().get(0).getSourceLocation().getFilePath());
            String filePath = path.toOSString();
            int lineNumber = var.getImplementingElements().get(0).getSourceLocation().getStartLine();
            if (var.getLeading()) {
                leadingPOIs.add(new UnifiedPOI(filePath, lineNumber));
            } else {
                integrationPOIs.add(new UnifiedPOI(filePath, lineNumber));
            }
        }
    }

    /**
     * Gets the variation point model from the given SPLevo project.
     * 
     * @param splevoProject
     *            the given SPLevo project.
     * @return the variation point model.
     */
    private VariationPointModel getVPM(SPLevoProject splevoProject) {
        int lastIndex = splevoProject.getVpmModelReferences().size() - 1;
        File vpmFile = new File(splevoProject.getVpmModelReferences().get(lastIndex).getPath());
        
        try {
            return VPMUtil.loadVariationPointModel(vpmFile, new SPLevoResourceSet());
            
        } catch (IOException e) {
            LOGGER.error("Could not load variation point model located at \"" + vpmFile.getAbsolutePath() + "\".", e);
        }
        
        return  null;
    }
   
    /**
     * Extracts all variation points from the given variation point model.
     * 
     * @param vpm
     *            the given variation point model.
     * @return a list of variation points.
     */
    private List<VariationPoint> extractVariationPoints(VariationPointModel vpm) {
        List<VariationPoint> vps = new ArrayList<VariationPoint>();
        for (VariationPointGroup vpGroup : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpGroup.getVariationPoints()) {
                vps.add(vp);
            }
        }
        return vps;
    }
   
    /**
     * Creates a id to variants mapping from the given variation points.
     * 
     * @param vps
     *            the given variation points.
     * @return the id to variants mapping
     */
    private Multimap<String, Variant> createIdToVariantMapping(List<VariationPoint> vps) {
        Multimap<String, Variant> mapping = ArrayListMultimap.create();
        for (VariationPoint vp : vps) {
            for (Variant variant : vp.getVariants()) {
                mapping.put(variant.getId(), variant);
            }
        }
        return mapping;
    }
    
    /**
     * Extract the leading file.
     * 
     * @return the leading file.
     */
    private FileWithID extractLeadingFile() {
        for (VariationPoint vp : variationPoints) {
            for (Variant var : vp.getVariants()) {
                IPath path = Path.fromOSString(var.getImplementingElements().get(0).getSourceLocation().getFilePath());
                String absfilePath = path.toOSString();
                if (leadingFile == null && var.getLeading() && isValidLeading(absfilePath)) {
                    return new FileWithID(new File(absfilePath), var.getId());
                }
            }
        }

        return null;
    }
   
    /**
     * Checks if the given leading file corresponds to the file to currently process.
     * 
     * @param absfilePath
     *            the given absolute leading file path.
     * @return {@code true} in case the given leading file corresponds to the current file to
     *         process, {@code false} otherwise.
     */
    private boolean isValidLeading(String absfilePath) {
        if (FilenameUtils.getBaseName(absfilePath).equals(proccessedFileName)) {
            return true;
        }

        return false;
    }
    
    /**
     * Extract the integration copy files from a given variation point model.
     */
    private List<FileWithID> extractIntegrationFiles() {
        ArrayList<FileWithID> integrationCopies = new ArrayList<FileWithID>();
        for (VariationPoint vp : variationPoints) {
            for (Variant var : vp.getVariants()) {
                IPath path = Path.fromOSString(var.getImplementingElements().get(0).getSourceLocation().getFilePath());
                String absfilePath = path.toOSString();
                boolean isNewIntegration = !var.getLeading()
                        && !CollectionUtil.containsFileWithId(var.getId(), integrationCopies)
                        && isValidIntegration(absfilePath);
                if (isNewIntegration) {
                    integrationCopies.add(new FileWithID(new File(absfilePath), var.getId()));
                }
            }
        }

        return integrationCopies;
    }
      
    /**
     * Checks if the given integration copy file path corresponds to the current leading file.
     * 
     * @param absfilePath
     *            the given absolute integration copy file path.
     * @return {@code true} in case the given integration copy file path corresponds to the current
     *         leading file, {@code false} otherwise.
     */
    private boolean isValidIntegration(String absfilePath) {
        if (leadingFile != null) {
            if (new File(absfilePath).getName().equals(leadingFile.getFile().getName())) {
                return true;
            }
        } else {
            LOGGER.error("No leading file is set to check the integration copy with!");
        }
        
        return false;
    }

    /**
     * Issues the file handler to read the leading copy from file and return it as a set.
     * The leading copy is represented as a set of lines.
     * 
     * @return the leading copy.
     */
    public List<NumbersTextPair> getLeadingCopy() {
        return UnifiedDiffFileHandler.getInstance().readLeadingFileToData(leadingFile);
    }
    
    /**
     * Issues the file handler to read all integration copies from file and return them as a set.
     * Each integration copy is represented as a set of lines.
     * 
     * @return the set of integration copies.
     */
    public List<String[]> getIntegrationCopies() {
        return UnifiedDiffFileHandler.getInstance().readIntegrationCopies(integrationCopyList);
    }

    /**
     * Gets the number of currently stored integration copies.
     * 
     * @return the integration copy count
     */
    public int getIntegrationCopyCount() {
        return integrationCopyList.size();
    }
    
    /**
     * Gets the unified difference marker type for a given unified line.
     * 
     * @param line
     *            the given line number.
     * @return the marker type.
     */
    public MarkerType getMarkerTypeFor(int line) {
        MarkerType marker = MarkerType.NONE;
        List<IndexedLineNumber> lineNumbers = unifiedLines.get(line).getLineNumbers();
        if (lineNumbers.size() > 1) {
            boolean isMixed = !containsColumnIndex(lineNumbers, 0);
            if (isMixed) {
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.MIXED_DARK;
                } else {
                    marker = MarkerType.MIXED_LIGHT;
                }
            }
        } else {
            // contains only one copy
            int colCount = integrationCopyList.size() + 1;
            for (int i = 0; i < colCount; i++) {
                if (containsColumnIndex(lineNumbers, i)) {
                    if (isPOI(lineNumbers)) {
                        marker = UIConstants.INDEX_TO_MARKERTYPE.get(i * 2);
                    } else {
                        marker = UIConstants.INDEX_TO_MARKERTYPE.get(i * 2 + 1);
                    }
                }
            }
        }

        return marker;
    }

    /**
     * Checks if a given index is contained within a list of indexed line numbers.
     * 
     * @param lineNumbers
     *            the indexed line numbers.
     * @param index
     *            the given index to check.
     * @return {@code true} in case the index is found, otherwise {@code false}.
     */
    private boolean containsColumnIndex(List<IndexedLineNumber> lineNumbers, int index) {
        for (IndexedLineNumber lineNumber : lineNumbers) {
            if (lineNumber != null && lineNumber.getColumnIndex() == index) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given indexed line numbers correspond to a POI.
     * 
     * @param lineNumbers
     *            the given indexed line numbers.
     * @return {@code true} if the line numbers correspond to a POI, otherwise {@code false}.
     */
    private boolean isPOI(List<IndexedLineNumber> lineNumbers) {
        for (IndexedLineNumber lineNumber : lineNumbers) {
            if (poiExists(lineNumber.getColumnIndex(), lineNumber.getNumber())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a POI exists for the given column index and line number.
     * 
     * @param columnIndex
     *            the given column index.
     * @param lineNumber
     *            the given line number.
     * @return {@code true} if the line is a POI, otherwise {@code false}.
     */
    private boolean poiExists(int columnIndex, int lineNumber) {
        if (columnIndex == 0) {
            // leading
            for (UnifiedPOI poi : leadingPOIs) {
                if (poi.getLineNumber() == lineNumber) {
                    return true;
                }
            }
        } else {
            // integration
            for (UnifiedPOI poi : integrationPOIs) {
                if (indexOfIntegrationCopy(poi.getFilePath()) == columnIndex && poi.getLineNumber() == lineNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the index of the integration copy for the given file path. Returns {@code -1} in case
     * the list of integration copies does not contain an entry for the given file path.
     * 
     * @param filePath
     *            the given file path.
     * @return the index of the integration copy within the list of all involved integraion copies.
     */
    private int indexOfIntegrationCopy(String filePath) {
        for (int i = 0; i < integrationCopyList.size(); i++) {
            if (integrationCopyList.get(i).getFile().getAbsolutePath().equals(filePath)) {
                return i + 1;
            }
        }
        return -1;
    }
    
    /**
     * Checks if variants exist for all line numbers included within the given list of indexed line
     * numbers.
     * 
     * @param lineNumbers
     *            the given list of indexed line numbers.
     * @return {@code true} if variants exist for all line numbers, otherwise {@code false}.
     */
    public boolean hasVPsFor(List<IndexedLineNumber> lineNumbers) {
        // initialize search range
        boolean[] hasLineNumbers = new boolean[lineNumbers.size()];
        Arrays.fill(hasLineNumbers, false);
        
        // look for line numbers in variation points
        for (IndexedLineNumber iLineNumber : lineNumbers) {
            List<Variant> variants = getVariantsForColumn(iLineNumber.getColumnIndex());
            for (Variant variant : variants) {
                SoftwareElement swElement = variant.getImplementingElements().get(0); 
                // same line number in separate variation point
                if (swElement.getSourceLocation().getStartLine() == iLineNumber.getNumber()) {
                    hasLineNumbers[lineNumbers.indexOf(iLineNumber)] = true;
                }
            }
        }
        
        for (boolean hasLineNumber : hasLineNumbers) {
            if (!hasLineNumber) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Gets all variants for the given column index which identifies the specific leading file or
     * integration copy.
     * 
     * @param columnIndex
     *            the given column index.
     * @return a list of variants.
     */
    private List<Variant> getVariantsForColumn(int columnIndex) {
        // get the leading or integration id for the given column index
        String idForColumn = leadingFile.getID();
        if (columnIndex > 0) {
            idForColumn = integrationCopyList.get(columnIndex - 1).getID();
        }
        return (List<Variant>) idToVariants.get(idForColumn);
    }
    
    /**
     * Retrieves a list of files which correspond to the given unified line number.
     * 
     * @param unifiedLineNumber
     *            the line number in the connected lines document
     * @return the list of files
     */
    public List<FileLineNumberPair> getFileInformationFor(int unifiedLineNumber) {
        List<FileLineNumberPair> fileInfos = new ArrayList<FileLineNumberPair>();
        if (unifiedLineNumber <= unifiedLines.size()) {
            // get the unified line
            NumbersTextPair unifiedLine = unifiedLines.get(unifiedLineNumber);
            for (IndexedLineNumber lineNumber : unifiedLine.getLineNumbers()) {
                // get the file corresponding to the unified line
                int sourceLineNumber = lineNumber.getNumber();
                if (lineNumber.getColumnIndex() == 0) {
                    // leading file
                    FileLineNumberPair fileInfo = new FileLineNumberPair(leadingFile.getFile(), sourceLineNumber);
                    fileInfos.add(fileInfo);
                } else {
                    // integration copies
                    File integrationFile = integrationCopyList.get(lineNumber.getColumnIndex() - 1).getFile();
                    FileLineNumberPair fileInfo = new FileLineNumberPair(integrationFile, sourceLineNumber);
                    fileInfos.add(fileInfo);
                }
            }
        } else {
            // Do nothing
            LOGGER.warn("The connected line " + unifiedLineNumber + " does not exist!");
        }

        return fileInfos;
    }
    
    /**
     * Sets the unified text lines and writes the to the working copy.
     * 
     * @param unifiedLines
     *            the new list unified lines.
     */
    public void setUnifiedLines(List<NumbersTextPair> unifiedLines) {
        this.unifiedLines = unifiedLines;
        if (unifiedDiffFile != null && unifiedDiffFile.exists()) {
            try {
                UnifiedDiffFileHandler.getInstance().writeUnifiedLines(getUnifiedTextLines(), unifiedDiffFile);
            } catch (IOException exception) {
                LOGGER.error("An error occured while writing data to the unified difference working copy!", exception);
            }
        }
    }

    /**
     * Gets the unified text lines.
     * 
     * @return the list of unified text lines.
     */
    public List<String> getUnifiedTextLines() {
        List<String> unifiedTextLines = new ArrayList<String>();
        for (NumbersTextPair numberTextPair : unifiedLines) {
            unifiedTextLines.add(numberTextPair.getLineText());
        }

        return unifiedTextLines;
    }
    
    /**
     * Gets the line numbers according to the unified text lines.
     * 
     * @return the list of multiple line numbers (per column).
     */
    public List<List<IndexedLineNumber>> getUnifiedLineNumbers() {
        List<List<IndexedLineNumber>> unifiedLineNumbers = new ArrayList<List<IndexedLineNumber>>();
        for (NumbersTextPair lineData : unifiedLines) {
            unifiedLineNumbers.add(lineData.getLineNumbers());
        }

        return unifiedLineNumbers;
    }
    
    /**
     * Gets the name of the currently processed file.
     * 
     * @return the name.
     */
    public String getProccessedFileName() {
        return proccessedFileName;
    }
    
    /**
     * Gets the lines (or points) of interest for the leading file.
     * 
     * @return the lines (or points) of interest.
     */
    public List<UnifiedPOI> getLeadingPOIs() {
        return leadingPOIs;
    }

    /**
     * Gets the lines (or points) of interest for the integration copies.
     * 
     * @return the lines (or points) of interest.
     */
    public List<UnifiedPOI> getIntegrationPOIs() {
        return integrationPOIs;
    }
    
}
