package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doUnchanged() {
        System.out.println(myVar.toString());

        // 1 reference from assignment to variable
        int variableDirectReference = 0;
        variableDirectReference = 12;

        // reference: variable declaration to nested assignement
        int variableNestedReference = 0;
        if(1 + 1 == 2) {
            variableNestedReference = 21;
        }
    }
}
