package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doNewVarAssignment() {
        int existingVar = 0;
    }

    public void doNewVarMethodCall() {
        String existingVar = new String();
    }

}
