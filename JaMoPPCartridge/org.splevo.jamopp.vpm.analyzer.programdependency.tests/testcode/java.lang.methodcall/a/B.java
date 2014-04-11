package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import org.splevo.test.external.ExternalNumber;

public class B {

    public String doSthElse() {
        ExternalNumber myVarElse = new ExternalNumber(1);

        return myVarElse.toString();
    }

}
