package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    private int fieldDirectReference = 0;

    private int fieldNestedReference = 0;

    public void doUnchanged() {
        System.out.println(myVar.toString());
    }

    public void newMethodFieldDirectReference() {
        int a = fieldDirectReference;
    }

    public void newMethodFieldNestedReference() {
        if(1 + 1 == 2) {
            int a = fieldNestedReference;
        }
    }

}
