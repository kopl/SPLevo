package org.splevo.tests.importdeclaration;

public class MyClass {

    boolean start = true;

    public void helloWorld() {
        int new1 = 1;
        int fix = new1;
        if(start) {
            int uninterestingVar = 0;
        }
        int new2 = new1;
    }
}