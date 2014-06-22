package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import org.splevo.test.external.ExternalDouble;

public class A {

    public String doSth() {
        ExternalDouble myVar1 = new ExternalDouble(1);

        return myVar1.toString();
    }
}