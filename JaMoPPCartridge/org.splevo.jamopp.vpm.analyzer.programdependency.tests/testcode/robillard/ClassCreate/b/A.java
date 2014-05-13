package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doUnchanged() {
        System.out.println(myVar.toString());
    }

    public void newVariableTypeMethod() {
        Object castedValue = new B();
    }
}
