package org.splevo.ui.sourceconnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.sourceconnection.helper.FileID;
import org.splevo.ui.sourceconnection.helper.FileLineNumberPair;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;
import org.splevo.ui.sourceconnection.helper.NumbersTextPair;
import org.splevo.ui.sourceconnection.helper.UnifiedPOI;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * This class represents a model for the {@link UnifiedDiffConnector}. It envelops all the data
 * being processed and offers means to refine and access them.
 * 
 * @author Andre
 */
public class UnifiedDiffConnectorContent {
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

    /** Logger instance */
    private static Logger LOGGER = Logger.getLogger(UnifiedDiffConnectorContent.class);

    /** The working copy for the unified difference */
    private File unifiedDiffFile;
    /** The name of the currently processed file */
    private String proccessedFileName;
    /** The variation points */
    private List<VariationPoint> variationPoints;
    /** The leading file */
    private FileID leadingFile;
    /** List of integration copy files already added */
    private List<FileID> integrationCopyList;
    /** List of POIs for the leading file */
    private List<UnifiedPOI> leadingPOIs;
    /** List of POIs for the integration copies */
    private List<UnifiedPOI> integrationPOIs;
    /** List of lines for the connected document */
    private List<NumbersTextPair> unifiedLines;

    /**
     * Constructs an instance of class {@link UnifiedDiffConnectorContent}.
     * 
     * @param splevoProject
     *            a reference to the current SPLevoProject instance.
     * @param variants
     *            a reference to the variants being processed.
     * @param unifiedDiffFile
     *            a reference to the unified difference working copy
     */
    public UnifiedDiffConnectorContent(SPLevoProject splevoProject, Set<Variant> variants, File unifiedDiffFile) {
        // initialize fields
        this.unifiedDiffFile = unifiedDiffFile;
        this.leadingFile = null;
        this.integrationCopyList = new ArrayList<FileID>();
        
        // extract file information
        VariationPointModel vpm = getVPM(splevoProject);
        this.proccessedFileName = getFileNameToProcess(variants);
        this.variationPoints = extractVariationPoints(vpm);
        this.leadingFile = extractLeadingFile(proccessedFileName);
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
     * Gets the name of the file that is currently processed from the given variants.
     * 
     * @param variants
     *            the given variants.
     * @return the file name.
     */
    private String getFileNameToProcess(Set<Variant> variants) {
        Variant firstVariant = variants.iterator().next();
        EObject locElement = firstVariant.getVariationPoint().getLocation().getWrappedElement();
        String filePath = locElement.eResource().getURI().toFileString();
        return new File(filePath).getName();
    }

    /**
     * Gets the variation point model from the given SPLevo project.
     * 
     * @param splevoProject
     *            the given SPLevo project.
     * @return the variation point model.
     */
    private VariationPointModel getVPM(SPLevoProject splevoProject) {
        int lastIndex = splevoProject.getVpmModelPaths().size() - 1;
        File vpmFile = new File(splevoProject.getVpmModelPaths().get(lastIndex));
        
        try {
            return VPMUtil.loadVariationPointModel(vpmFile, new ResourceSetImpl());
            
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
     * Extract the leading file for a given file name.
     * 
     * @param fileName
     *            the given file name.
     * @return the leading file.
     */
    private FileID extractLeadingFile(String fileName) {
        for (VariationPoint vp : variationPoints) {
            for (Variant var : vp.getVariants()) {
                IPath path = Path.fromOSString(var.getImplementingElements().get(0).getSourceLocation().getFilePath());
                String absfilePath = path.toOSString();
                if (leadingFile == null && var.getLeading() && isValidLeading(absfilePath, fileName)) {
                    return new FileID(new File(absfilePath), var.getId());
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
    private boolean isValidLeading(String absfilePath, String proccessedFileName) {
        if (new File(absfilePath).getName().equals(proccessedFileName)) {
            return true;
        }

        return false;
    }
    
    /**
     * Extract the integration copy files from a given variation point model.
     * 
     */
    private List<FileID> extractIntegrationFiles() {
        ArrayList<FileID> integrationCopies = new ArrayList<FileID>();
        for (VariationPoint vp : variationPoints) {
            for (Variant var : vp.getVariants()) {
                IPath path = Path.fromOSString(var.getImplementingElements().get(0).getSourceLocation().getFilePath());
                String absfilePath = path.toOSString();
                boolean isNewIntegration = !var.getLeading() && !containsFile(integrationCopies, var.getId())
                        && isValidIntegration(absfilePath);
                if (isNewIntegration) {
                    integrationCopies.add(new FileID(new File(absfilePath), var.getId()));
                }
            }
        }

        return integrationCopies;
    }
   
    /**
     * Utility method to check if a file collection already contains a file with the given
     * identifier.
     * 
     * @param collection
     *            the collection of instances of {@link File}.
     * @param id
     *            the given identifier.
     * @return {@code true} in case the collection contains a file with the given identifier,
     *         {@code false} otherwise.
     */
    private boolean containsFile(Collection<FileID> collection, String id) {
        for (FileID fileID : collection) {
            if (fileID.getID().equals(id)) {
                return true;
            }
        }
        
        return false;
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
     * Reads the leading file content to the used line data structure.
     * 
     * @return the list of line data structures {@link NumbersTextPair}.
     */
    public List<NumbersTextPair> readLeadingFileToData() {
        List<NumbersTextPair> leadingData = new ArrayList<NumbersTextPair>();
        try {
            String[] leadingContent = readFileToLines(leadingFile.getFile());
            for (int i = 0; i < leadingContent.length; i++) {
                List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                lineNumbers.add(new IndexedLineNumber(0, i + 1));
                leadingData.add(new NumbersTextPair(lineNumbers, leadingContent[i]));
            }
        } catch (IOException e) {
            LOGGER.error("An error occured while reading leading file \"" + leadingFile.getFile().getAbsolutePath() + "\"", e);
        }
        
        return leadingData;
    }
    
    /**
     * Reads the integration copies' content to a list of {@code String}-arrays.
     * 
     * @return the list of integration copies represented by arrays of <code>String</code>.
     */
    public List<String[]> readIntegrationCopies() {
        List<String[]> integrationCopiesAsArray = new ArrayList<String[]>();
        for (FileID integrationCopy : integrationCopyList) {
            String[] integrationCopyTextAsArray = null;
            try {
                integrationCopyTextAsArray = readFileToLines(integrationCopy.getFile());
            } catch (IOException e) {
                LOGGER.error("An error occured while reading integration copy \""
                        + integrationCopy.getFile().getAbsolutePath() + "\"", e);
            }
            
            integrationCopiesAsArray.add(integrationCopyTextAsArray);
        }
        
        return integrationCopiesAsArray;
    }

    /**
     * Reads the file contents of a given file as an array of string lines.
     * 
     * @param file
     *            the file to process
     * @return the string array containing all text lines
     * @throws IOException
     *             TODO Doc
     */
    private String[] readFileToLines(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        // FIXME: Implement more memory friendly method (i.e. mapping lines to Unicode characters)
        return new String(encoded).split("\\r?\\n");
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
     * Deletes the current unified difference working copy.
     * 
     * @throws CoreException
     *             in case the unified difference working copy could not be deleted.
     */
    public void deleteWorkingCopy() throws CoreException {
        if (unifiedDiffFile != null && unifiedDiffFile.exists()) {
            unifiedDiffFile.delete();
        }
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
            // contains multiple (but not leading) --> mixed
            if (!containsColumnIndex(lineNumbers, 0)) {
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.MIXED_DARK;
                } else {
                    marker = MarkerType.MIXED_LIGHT;
                }
            }
        } else {
            if (containsColumnIndex(lineNumbers, 0)) {
                // contains only leading (column 0) --> leading
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.LEADING_DARK;
                } else {
                    marker = MarkerType.LEADING_LIGHT;
                }
            } else if (containsColumnIndex(lineNumbers, 1)) {
                // contains only leading (column 1) --> integration 1
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.INTEGRATION1_DARK;
                } else {
                    marker = MarkerType.INTEGRATION1_LIGHT;
                }
            } else if (containsColumnIndex(lineNumbers, 2)) {
                // contains only leading (column 2) --> integration 2
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.INTEGRATION2_DARK;
                } else {
                    marker = MarkerType.INTEGRATION2_LIGHT;
                }
            } else if (containsColumnIndex(lineNumbers, 3)) {
                // contains only leading (column 3) --> integration 3
                if (isPOI(lineNumbers)) {
                    marker = MarkerType.INTEGRATION3_DARK;
                } else {
                    marker = MarkerType.INTEGRATION3_LIGHT;
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
        for (int i = 0; i < hasLineNumbers.length; i++) {
            hasLineNumbers[i] = false;
        }
        
        // look for line numbers in variation points
        for (IndexedLineNumber iLineNumber : lineNumbers) {
            List<Variant> vars = getVsForColumn(iLineNumber.getColumnIndex());
            for (Variant var : vars) {
                SoftwareElement sl = var.getImplementingElements().get(0); 
                // same line number in separate variation point
                if (sl.getSourceLocation().getStartLine() == iLineNumber.getNumber()) {
                    hasLineNumbers[lineNumbers.indexOf(iLineNumber)] = true;
                }
            }
        }
        
        for (boolean hasLineNumber: hasLineNumbers) {
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
    private List<Variant> getVsForColumn(int columnIndex) {
        List<Variant> vars = new ArrayList<Variant>();

        // get the leading of integration id for the given column index
        String idForColumn = leadingFile.getID();
        if (columnIndex > 0) {
            idForColumn = integrationCopyList.get(columnIndex - 1).getID();
        }

        // get the variations
        for (VariationPoint vp : variationPoints) {
            for (Variant var : vp.getVariants()) {
                if (var.getId().equals(idForColumn)) {
                    vars.add(var);
                }
            }
        }

        return vars;
    }
    
    /**
     * Retrives a list of files which correspond to the given connected line number.
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
     * Sets the unified text lines.
     * 
     * @param unifiedLines
     *            the new list unified lines.
     */
    public void setUnifiedLines(List<NumbersTextPair> unifiedLines) {
        this.unifiedLines = unifiedLines;
        if (unifiedDiffFile != null && unifiedDiffFile.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(unifiedDiffFile), "utf-8"))) {
                StringBuilder builder = new StringBuilder();
                for (String string : getUnifiedTextLines()) {
                    builder.append(string + "\n");
                }
                writer.write(builder.toString());
            } catch (IOException e) {
                LOGGER.warn("Error trying to write data to the unified difference working copy!", e);
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
