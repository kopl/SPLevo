package org.splevo.ui.sourceconnection.helper;

public class UnifiedPOI {
    private String filePath;
    private int lineNumber;
    
    public UnifiedPOI(String filePath, int lineNumber) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
