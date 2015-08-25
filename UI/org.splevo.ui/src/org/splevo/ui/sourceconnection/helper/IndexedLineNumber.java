package org.splevo.ui.sourceconnection.helper;

public class IndexedLineNumber {
    private int columnIndex;
    private int number;

    public IndexedLineNumber() {
        this.columnIndex = 0;
        this.number = 0;
    }

    public IndexedLineNumber(int columnIndex, int number) {
        this.columnIndex = columnIndex;
        this.number = number;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getNumber() {
        return number;
    }
}
