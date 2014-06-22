package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import org.splevo.test.external.ExternalNumber;

public class A {

    public String doSth() {
        ExternalNumber myVar1 = new ExternalNumber(1);

        return myVar1.toString();
    }
}
