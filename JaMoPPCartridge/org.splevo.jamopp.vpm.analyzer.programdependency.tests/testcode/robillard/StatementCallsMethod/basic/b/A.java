package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doUnchanged() {
        System.out.println(myVar.toString());
    }

    public void newCalledMethod() {}

    public void newCallingMethod(){
        newCalledMethod();
    }

    public void newIndependentMethod() {}

}
