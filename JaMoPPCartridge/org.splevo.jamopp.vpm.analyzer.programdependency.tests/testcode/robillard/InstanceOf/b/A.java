package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doUnchanged() {
        System.out.println(myVar.toString());
    }

    public void newInstanceOfCheckMethod(Object obj) {
        if(obj instanceof B) {
            return;
        }
    }

}
