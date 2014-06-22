package org.splevo.refactoring.tests.testcases;

public class TestClass{

    public void someMethod() {
        try {
            System.out.println(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}