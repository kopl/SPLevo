package org.splevo.ui.sourceconnection.helper;

import java.util.ArrayList;
import java.util.List;

public class NumbersTextPair {
    private List<IndexedLineNumber> lineNumbers;
    private String lineText;

    public NumbersTextPair() {
        this.lineNumbers = new ArrayList<IndexedLineNumber>();
        this.lineText = "";
    }

    public NumbersTextPair(NumbersTextPair numbersTextPair) {
        this.lineNumbers = new ArrayList<IndexedLineNumber>(numbersTextPair.getLineNumbers());
        this.lineText = numbersTextPair.getLineText();
    }

    public NumbersTextPair(List<IndexedLineNumber> lineNumbers, String lineText) {
        this.lineNumbers = lineNumbers;
        this.lineText = lineText;
    }

    public void addLineNumber(IndexedLineNumber lineNumber) {
        lineNumbers.add(lineNumber);
    }

    public List<IndexedLineNumber> getLineNumbers() {
        return lineNumbers;
    }

    public String getLineText() {
        return lineText;
    }
}
