package org.splevo.tests.importdeclaration;

public class MyClass {

    boolean start = true;

    public void helloWorld() {
        int orig1 = 1;
        int fix = orig1;
        if(start) {
            int uninterestingVar = 0;
        }
    }
}