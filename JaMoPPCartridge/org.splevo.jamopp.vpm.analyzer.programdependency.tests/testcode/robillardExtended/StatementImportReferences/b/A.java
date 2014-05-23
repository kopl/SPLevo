package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Formatter.BigDecimalLayoutForm;
import java.util.Map;
import java.util.HashMap;

public class A {

    public void doSth() {
        Integer myVar = 1;

        // 1 reference: variable type to interface import
        // 1 reference: constructor call to class import
        Map<String,String> options = new HashMap<String,String>();

        // 1 reference: variable type to enumeration import
        // 1 reference: variable initialzation to enumeration import
        BigDecimalLayoutForm form = BigDecimalLayoutForm.DECIMAL_FLOAT;

        System.out.println(myVar.toString());
    }

}
