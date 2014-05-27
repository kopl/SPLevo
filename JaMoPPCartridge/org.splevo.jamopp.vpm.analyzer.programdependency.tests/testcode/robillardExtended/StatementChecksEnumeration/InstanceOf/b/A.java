package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doSth(Object obj) {
        if(obj instanceof B) {
            return;
        }
    }

}
