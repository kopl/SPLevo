package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Formatter.BigDecimalLayoutForm;

public class A {

    public void doSth() {
        Integer myVar = 1;

        // 1 reference: variable type to enumeration import
        BigDecimalLayoutForm form = null;

        System.out.println(myVar.toString());
    }

}
