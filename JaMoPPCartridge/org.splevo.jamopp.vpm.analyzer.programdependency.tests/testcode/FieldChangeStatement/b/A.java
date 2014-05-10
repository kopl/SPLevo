package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    public void doSth() {

        final String label = new String();

        final B b = new B();
        b.field1 = 0;
        label.concat(b.toString());
    }

}
