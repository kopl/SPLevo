package org.splevo.refactoring.tests.testcases;

public class TestClass{

    public void someMethod() {
        try {
            System.out.println(1);
        } catch (Exception e) {
            throw new IllegalAccessError(e);
        }
    }
}