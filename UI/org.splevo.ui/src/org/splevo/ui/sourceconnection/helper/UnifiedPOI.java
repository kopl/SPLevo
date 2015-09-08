package org.splevo.ui.sourceconnection.helper;

/**
 * Helper class that encapsulates a file path string and a line number.
 * 
 * @author André Wengert
 */
public class UnifiedPOI {
    private final String filePath;
    private final int lineNumber;

    /**
     * Constructs an instance of class {@link UnifiedPOI}.
     * 
     * @param filePath
     *            a file path.
     * @param lineNumber
     *            a line number.
     */
    public UnifiedPOI(String filePath, int lineNumber) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the file path.
     * 
     * @return the file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the line number.
     * 
     * @return the line number.
     */
    public int getLineNumber() {
        return lineNumber;
    }
}
