package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doSth() {
        Integer myVar = 1;

        myVar.byteValue();
        equals(myVar);

        System.out.println(myVar.toString());
    }

}
