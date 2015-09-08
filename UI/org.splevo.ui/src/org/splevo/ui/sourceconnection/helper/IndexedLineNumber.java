package org.splevo.ui.sourceconnection.helper;

/**
 * Helper class that encapsulates a column index and a number.
 * 
 * @author André Wengert
 */
public class IndexedLineNumber {
    private int columnIndex;
    private int number;

    /**
     * Constructs an instance of class {@link NumbersTextPair} and initializes fields to default
     * values.
     */
    public IndexedLineNumber() {
        this.columnIndex = 0;
        this.number = 0;
    }

    /**
     * Constructs an instance of class {@link IndexedLineNumber}.
     * 
     * @param columnIndex
     *            a column index.
     * @param number
     *            a number.
     */
    public IndexedLineNumber(int columnIndex, int number) {
        this.columnIndex = columnIndex;
        this.number = number;
    }

    /**
     * Gets the column index.
     * 
     * @return the column index.
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Gest the number.
     * 
     * @return the number.
     */
    public int getNumber() {
        return number;
    }
}
