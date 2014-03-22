package org.splevo.tests.importdeclaration;

public class MyClass {

    /**
     * 3 statements where the middle one is not changed so they can not be merged by default.
     */
    public void helloWorld() {
        int b = 1; // unchanged
    }
}