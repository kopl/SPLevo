package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public String field = newCalledMethod();

    public String newCalledMethod() {
        return "";
    }
}
