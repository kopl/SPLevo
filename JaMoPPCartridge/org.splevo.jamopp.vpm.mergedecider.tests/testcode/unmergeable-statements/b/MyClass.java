package org.splevo.tests.importdeclaration;

public class MyClass {

    /**
     * 3 statements where the middle one is not changed so they can not be merged by default.
     */
    public void helloWorld() {
        int a = 2; // changed
        int b = 1; // unchanged
        a = b + 2; // changed
    }
}