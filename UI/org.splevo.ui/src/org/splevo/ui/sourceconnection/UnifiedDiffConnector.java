package org.splevo.ui.sourceconnection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.splevo.project.SPLevoProject;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;
import org.splevo.ui.sourceconnection.helper.NumbersTextPair;
import org.splevo.vpm.variability.Variant;

/**
 * This class is mainly used to calculate the unified difference based on the model it initializes.
 * 
 * @author André Wengert
 */
public class UnifiedDiffConnector {
    /** Defines how to connect. */
    public enum DiffMethod {
        BY_BLOCKS,
        BY_LINE
    }
    
    /** The Logger instance */
    //private static final Logger LOGGER = Logger.getLogger(UnifiedDiffConnector.class);
    
    /** The base content the unified differ works on. */
    private final UnifiedDiffConnectorModel diffModel;

    /**
     * Constructs an instance of class {@link UnifiedDiffConnector}.
     * 
     * @param splevoProject
     *            a reference to the current SPLevoProject instance.
     * @param variants
     *            a reference to the variants being processed.
     * @param unifiedDiffFile
     *            a reference to the unified difference working copy
     * @param fileName 
     */
    public UnifiedDiffConnector(SPLevoProject splevoProject, Set<Variant> variants, File unifiedDiffFile, String fileName) {
        this.diffModel = new UnifiedDiffConnectorModel(splevoProject, variants, unifiedDiffFile, fileName);
    }
    
    /**
     * Calculates the unified difference between the leading and integration copies according to the given method.
     * 
     * @param diffMethod
     *            the given difference method.
     */
    public void calculateDifference(DiffMethod diffMethod) {
        switch (diffMethod) {
        case BY_LINE:
            calculateByLine();
            break;

        case BY_BLOCKS:
            calculateByBlocks();
            break;

        default:
            calculateByBlocks();
            break;
        }
    }
    
    /**
     * Calculates the unified difference by blocks.
     */
    private void calculateByBlocks() {
        // get text lines from leading file and integration copy files
        List<NumbersTextPair> unifiedLinesToRead = diffModel.getLeadingCopy();
        List<String[]> integrations = diffModel.getIntegrationCopies();
        
        // diff each integration with previously unified files
        for (String[] integration : integrations) {
            // initialize local variables
            List<NumbersTextPair> unifiedLinesToWrite = new ArrayList<NumbersTextPair>();
            int leadingLines = unifiedLinesToRead.size();
            int integrationLines = integration.length;
            int integrationColumnIndex = integrations.indexOf(integration) + 1;
            
            // compute length of the longest common subsequences (LCS)
            int[][] lcs = computeLCSTable(unifiedLinesToRead, integration);

            // compare lines and evaluate using LCS table
            int i = 0, j = 0;
            while (i < leadingLines && j < integrationLines) {
                String leadingLine = unifiedLinesToRead.get(i).getLineText();
                if (leadingLine.equals(integration[j])) {
                    // in both
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                    j++;
                } else if (lcs[i + 1][j] >= lcs[i][j + 1]) {
                    // not in integration
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                } else {
                    // not in leading
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }

            // append remainder of the respective file (that is not exhausted)
            while (i < leadingLines || j < integrationLines) {
                if (j == integrationLines) {
                    // not in integration
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, unifiedLinesToRead.get(i).getLineText()));
                    i++;
                } else if (i == leadingLines) {
                    // not in leading
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }
            
            // assign new source to read from
            unifiedLinesToRead = unifiedLinesToWrite;
        }
        
        // post-processing step: Identify variants not respected by textual difference
        unifiedLinesToRead = postProcess(unifiedLinesToRead);
        
        // store connected result
        diffModel.setUnifiedLines(unifiedLinesToRead);
    }

    /**
     * Computes the longest common subsequence (lcs) table. Instead of simple characters in this
     * case whole strings are compared. {@link https://en.wikipedia.org/wiki/Longest_common_subsequence_problem}
     * 
     * @param firstSet
     *            the first set of lines to compare
     * @param secondSet
     *            the second set of lines to compare
     * @return the lcs table.
     */
    private int[][] computeLCSTable(List<NumbersTextPair> firstSet, String[] secondSet) {
        int firstSetLineCount = firstSet.size();
        int secondSetLineCount = secondSet.length;
        int[][] lcs = new int[firstSetLineCount + 1][secondSetLineCount + 1];
        for (int i = firstSetLineCount - 1; i >= 0; i--) {
            for (int j = secondSetLineCount - 1; j >= 0; j--) {
                if (firstSet.get(i).getLineText().equals(secondSet[j])) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i + 1][j], lcs[i][j + 1]);
                }
            }
        }
        return lcs;
    }
    
    /**
     * Calculates the unified difference by line.
     */
    private void calculateByLine() {
        // get text lines from leading file and integration copy files
        List<NumbersTextPair> unifiedLinesToRead = diffModel.getLeadingCopy();
        List<String[]> integrations = diffModel.getIntegrationCopies();
        
        // diff each integration with previously unified lines
        for (String[] integration : integrations) {
            // initialize local variables
            List<NumbersTextPair> unifiedLinesToWrite = new ArrayList<NumbersTextPair>();
            int leadingLines = unifiedLinesToRead.size();
            int integrationLines = integration.length;
            int integrationColumnIndex = integrations.indexOf(integration) + 1;
            
            // compare lines
            int i = 0, j = 0;
            while (i < leadingLines && j < integrationLines) {
                String leadingLine = unifiedLinesToRead.get(i).getLineText();
                if (leadingLine.equals(integration[j])) {
                    // in both
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                    j++;
                } else {
                    // not in integration
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    
                    // not in leading --> add integration copy line at the end of current difference block
                    boolean isLastLeadingLine = (i == leadingLines - 1);
                    if (isLastLeadingLine) {
                        lineNumbers = new ArrayList<IndexedLineNumber>();
                        lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                        unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                        j++;
                    } else {
                        // check for the end of the current difference block
                        boolean nextLeadingLineHasSEQLineNumer = false;
                        if (i + 1 < leadingLines) {
                            for (IndexedLineNumber lineNumber : unifiedLinesToRead.get(i + 1).getLineNumbers()) {
                                if (lineNumber.getNumber() <= j) {
                                    nextLeadingLineHasSEQLineNumer = true;
                                    break;
                                }
                            }
                        }
                        if (!nextLeadingLineHasSEQLineNumer) {
                            lineNumbers = new ArrayList<IndexedLineNumber>();
                            lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                            unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                            j++;
                        }
                    }
                    i++;
                }
            }
            
            // append remainder of the respective file (that is not exhausted)
            while (i < leadingLines || j < integrationLines) {
                if (j == integrationLines) {
                    // not in integration
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, unifiedLinesToRead.get(i).getLineText()));
                    i++;
                } else if (i == leadingLines) {
                    // not in leading
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }

            // assign new source to read from
            unifiedLinesToRead = unifiedLinesToWrite;
        }
        
        // post-processing step: Identify variants not respected by textual difference
        unifiedLinesToRead = postProcess(unifiedLinesToRead);

        // store connected result
        diffModel.setUnifiedLines(unifiedLinesToRead);
    }


    /**
     * Processes the unified lines after the initial unified difference has finished. This was
     * introduced to alter the unified lines due to some special case the textual difference is not
     * able to identify.
     * 
     * @param unifiedLines
     *            the list of unified lines from the initial unified difference.
     * @return an altered list of unified lines.
     */
    private List<NumbersTextPair> postProcess(List<NumbersTextPair> unifiedLines) {
        List<NumbersTextPair> unifiedLinesToWrite = new ArrayList<NumbersTextPair>(unifiedLines);
        for (NumbersTextPair unifiedLine : unifiedLines) {
            // look for shared unified lines
            if (unifiedLine.getLineNumbers().size() > 1) {
                if (diffModel.hasVPsFor(unifiedLine.getLineNumbers())) {
                    // separate shared lines
                    int index = unifiedLines.indexOf(unifiedLine);
                    String sharedLineText = unifiedLines.get(index).getLineText();
                    List<IndexedLineNumber> iLineNumbers = unifiedLine.getLineNumbers();
                    unifiedLinesToWrite.remove(index);
                    for (IndexedLineNumber iLineNumber : iLineNumbers) {
                        List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                        lineNumbers.add(new IndexedLineNumber(iLineNumber.getColumnIndex(), iLineNumber.getNumber()));
                        unifiedLinesToWrite.add(index, new NumbersTextPair(lineNumbers, sharedLineText));
                        index++;
                    }
                }
            }
        }
        
        return unifiedLinesToWrite;
    }
    
    /**
     * Gets the connector content.
     * 
     * @return the connector content object
     */
    public UnifiedDiffConnectorModel getDiffConnectorModel() {
        return this.diffModel;
    }
    
    /**
     * Gets the unified lines as a single text string from the connector content.
     * 
     * @return the unified lines as text string.
     */
    public String getUnifiedText() {
        StringBuilder builder = new StringBuilder();
        for (String string : diffModel.getUnifiedTextLines()) {
            builder.append(string + "\n");
        }

        return builder.toString();
    }
        
    /**
     * Gets the name of the file being differenced from the connector content.
     * 
     * @return the file name.
     */
    public String getProccessedFileName() {
        return diffModel.getProccessedFileName();
    }
}
