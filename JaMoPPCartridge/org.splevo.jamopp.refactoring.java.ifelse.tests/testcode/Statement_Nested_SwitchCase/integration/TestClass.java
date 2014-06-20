package org.splevo.refactoring.tests.testcases;

public class TestClass{

    public void someMethod(int i) {
        switch(i){
            case 0:
                System.out.println(1);
                System.out.println(2);
                break;
            default:
                return;
        }
    }
}