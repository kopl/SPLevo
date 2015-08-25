package org.splevo.ui.sourceconnection;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;
import org.splevo.ui.sourceconnection.helper.NumbersTextPair;
import org.splevo.vpm.variability.Variant;

/**
 * 
 * @author Andre
 */
public class UnifiedDiffConnector {
    /** Defines how to connect. */
    public enum ConnectionMethod {
        BY_CHUNKS,
        BY_LINE
    }
    /** Defines from what file to connect. */
    public enum ConnectPattern {
        BOTH,
        LEADING,
        INTEGRATION
    }
    
    /** A Logger instance */
    private static Logger LOGGER = Logger.getLogger(UnifiedDiffConnector.class);
    /** The base content the unified diff connector works on. */
    private UnifiedDiffConnectorContent connectorContent;

    /**
     * Constructs an instance of class {@link UnifiedDiffConnector}.
     * 
     * @param splevoProject
     *            a reference to the current SPLevoProject instance.
     * @param variants
     *            a reference to the variants being processed.
     * @param unifiedDiffFile
     *            a reference to the unified difference working copy
     */
    public UnifiedDiffConnector(SPLevoProject splevoProject, Set<Variant> variants, File unifiedDiffFile) {
        // initialize fields
        this.connectorContent = new UnifiedDiffConnectorContent(splevoProject, variants, unifiedDiffFile);
    }
    
    /**
     * Constructs an instance of class {@link UnifiedDiffConnector}.
     * 
     * @param splevoProject
     *            a reference to the current SPLevoProject instance.
     * @param variants
     *            a reference to the variants being processed.
     */
    public UnifiedDiffConnector(SPLevoProject splevoProject, Set<Variant> variants) {
        // initialize fields
        this.connectorContent = new UnifiedDiffConnectorContent(splevoProject, variants, null);
    }
    
    /**
     * Connects the leading and integration copies to a unified difference according to the given
     * method.
     * 
     * @param connectionMethod
     *            the given connection method.
     */
    public void connect(ConnectionMethod connectionMethod) {
        switch (connectionMethod) {
        case BY_LINE:
            connectByLine();
            break;
        
        case BY_CHUNKS:
            connectByChunks();
            break;

        default:
            connectByChunks();
            break;
        }
    }
    
    /**
     * Connects leading with all integration copies by chunks.
     */
    private void connectByChunks() {
        // get text lines from leading file and integration copy files
        List<NumbersTextPair> unifiedLinesToRead = connectorContent.readLeadingFileToData();
        List<String[]> integrations = connectorContent.readIntegrationCopies();
        
        // connect each integration with previously connected
        for (String[] integration : integrations) {
            // initialize local variables
            List<NumbersTextPair> unifiedLinesToWrite = new ArrayList<NumbersTextPair>();
            int leadingLines = unifiedLinesToRead.size();
            int integrationLines = integration.length;
            int integrationColumnIndex = integrations.indexOf(integration) + 1;
            
            // compute length of the longest common subsequences (LCS)
            int[][] lcs = new int[leadingLines + 1][integrationLines + 1];
            for (int i = leadingLines - 1; i >= 0; i--) {
                for (int j = integrationLines - 1; j >= 0; j--) {
                    if (unifiedLinesToRead.get(i).getLineText().equals(integration[j])) {
                        lcs[i][j] = lcs[i + 1][j + 1] + 1;
                    } else {
                        lcs[i][j] = Math.max(lcs[i + 1][j], lcs[i][j + 1]);
                    }
                }
            }

            // compare lines and evaluate using LCS
            int i = 0, j = 0;
            while (i < leadingLines && j < integrationLines) {
                String leadingLine = unifiedLinesToRead.get(i).getLineText();
                if (leadingLine.equals(integration[j])) {
                    // same (|)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                    j++;
                } else if (lcs[i + 1][j] >= lcs[i][j + 1]) {
                    // not in integration (>)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                } else {
                    // not in leading (<)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }

            // append remainder of the respective file (that is not exhausted)
            while (i < leadingLines || j < integrationLines) {
                if (j == integrationLines) {
                    // not in integration (>)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, unifiedLinesToRead.get(i).getLineText()));
                    i++;
                } else if (i == leadingLines) {
                    // not in leading (<)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }
            
            // assign new source to read from
            unifiedLinesToRead = unifiedLinesToWrite;
        }
        
        // post-processing step:
        // - identify variants not respected by textual difference
        unifiedLinesToRead = reconnect(unifiedLinesToRead);
        
        // store connected result
        connectorContent.setUnifiedLines(unifiedLinesToRead);
    }
    
    /**
     * Connects leading with all integration copies by line.
     */
    private void connectByLine() {
        // get text lines from leading file and integration copy files
        List<NumbersTextPair> unifiedLinesToRead = connectorContent.readLeadingFileToData();
        List<String[]> integrations = connectorContent.readIntegrationCopies();
        
        // connect each integration with previously connected
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
                    // same (|)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    i++;
                    j++;
                } else {
                    // not in integration (>)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, leadingLine));
                    
                    // not in leading (<) --> add integration copy line at the end of current difference block
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
                    // not in integration (>)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.addAll(unifiedLinesToRead.get(i).getLineNumbers());
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, unifiedLinesToRead.get(i).getLineText()));
                    i++;
                } else if (i == leadingLines) {
                    // not in leading (<)
                    List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                    lineNumbers.add(new IndexedLineNumber(integrationColumnIndex, j + 1));
                    unifiedLinesToWrite.add(new NumbersTextPair(lineNumbers, integration[j]));
                    j++;
                }
            }

            // assign new source to read from
            unifiedLinesToRead = unifiedLinesToWrite;
        }
        
        // post-processing step:
        // - identify variants not respected by textual difference
        unifiedLinesToRead = reconnect(unifiedLinesToRead);

        // store connected result
        connectorContent.setUnifiedLines(unifiedLinesToRead);
    }

    /**
     * Processes the unified lines after the initial unified difference has finished. This was
     * introduced to alter the unified lines due to some special cases the textual difference is not
     * able to identify.
     * 
     * @param unifiedLines
     *            the list of unified lines from the initial unified difference.
     * @return an altered list of unified lines.
     */
    private List<NumbersTextPair> reconnect(List<NumbersTextPair> unifiedLines) {
        // special case 1: variation points are identified for textually identical line.
        List<NumbersTextPair> unifiedLinesToWrite = new ArrayList<NumbersTextPair>(unifiedLines);
        for (NumbersTextPair unifiedLine : unifiedLines) {
            // look for shared unified lines
            if (unifiedLine.getLineNumbers().size() > 1) {
                if (connectorContent.hasVPsFor(unifiedLine.getLineNumbers())) {
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

        // special case 2: ... (if there are others)
        // ...

        return unifiedLinesToWrite;
    }
    
    /**
     * Gets the connector content.
     * 
     * @return the connector content object
     */
    public UnifiedDiffConnectorContent getConnectorContent() {
        return this.connectorContent;
    }
    
    /**
     * Gets the unified lines as a single text string from the connector content.
     * 
     * @return the unified lines as text string.
     */
    public String getUnifiedText() {
        StringBuilder builder = new StringBuilder();
        for (String string : connectorContent.getUnifiedTextLines()) {
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
        return connectorContent.getProccessedFileName();
    }
}
