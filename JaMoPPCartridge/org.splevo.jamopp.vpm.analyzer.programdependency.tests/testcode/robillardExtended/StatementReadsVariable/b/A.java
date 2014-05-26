package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


public class A {

    public void doNewVarAssignment() {
        int existingVar = 0;

        // 1 reference: new var and assignment
        int a = 0;
        existingVar = a;

        // 1 reference: new additional var and assignment
        int b, additionalVar = 0;
        existingVar = additionalVar;
    }

    public void doNewVarMethodCall() {
        String existingVar = new String();

        // 1 reference: new var and method call
        String a = "";
        existingVar.equals(a);

        // 1 reference: new additional var and method call
        String b, additionalVar = "";
        existingVar.equals(additionalVar);
    }

}
