package org.splevo.ui.sourceconnection.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that encapsulates a list of indexed line numbers and a line text string.
 * 
 * @author André Wengert
 */
public class NumbersTextPair {
    private List<IndexedLineNumber> lineNumbers;
    private String lineText;

    /**
     * Constructs an instance of class {@link NumbersTextPair} and initializes fields to default
     * values.
     */
    public NumbersTextPair() {
        this.lineNumbers = new ArrayList<IndexedLineNumber>();
        this.lineText = "";
    }

    /**
     * Constructs an instance of class {@link NumbersTextPair} from a given instance.
     * 
     * @param numbersTextPair
     *            the given instance of {@link NumbersTextPair}.
     */
    public NumbersTextPair(NumbersTextPair numbersTextPair) {
        this.lineNumbers = new ArrayList<IndexedLineNumber>(numbersTextPair.getLineNumbers());
        this.lineText = numbersTextPair.getLineText();
    }

    /**
     * Constructs an instance of class {@link NumbersTextPair}.
     * 
     * @param lineNumbers
     *            a set of index line numbers.
     * @param lineText
     *            a line text string.
     */
    public NumbersTextPair(List<IndexedLineNumber> lineNumbers, String lineText) {
        this.lineNumbers = lineNumbers;
        this.lineText = lineText;
    }

    /**
     * Adds a given indexed line number to the current set.
     * 
     * @param lineNumber
     *            the given indexed line number.
     */
    public void addLineNumber(IndexedLineNumber lineNumber) {
        lineNumbers.add(lineNumber);
    }

    /**
     * Gets the set of indexed line numbers.
     * 
     * @return the set of indexed line numbers.
     */
    public List<IndexedLineNumber> getLineNumbers() {
        return lineNumbers;
    }

    /**
     * Gets the line text string.
     * 
     * @return the line text string.
     */
    public String getLineText() {
        return lineText;
    }
}
