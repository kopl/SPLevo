package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import org.splevo.test.external.ExternalDouble;

public class B {

    public String doSthElse() {
        ExternalDouble myVarElse = new ExternalDouble(1);

        return myVarElse.toString();
    }

}