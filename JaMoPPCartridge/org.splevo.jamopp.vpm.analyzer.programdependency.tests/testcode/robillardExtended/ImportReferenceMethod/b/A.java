package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Currency;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class A {

    public void doSth() {
        Integer myVar = 1;

        // 1 reference: variable type to import
        // 1 reference: constructor call to import
        Map<String,String> options = new HashMap<String,String>();

        System.out.println(myVar.toString());
    }

    // 1 method return type to import
    public Properties newMethodReturnToImport() {
        return null;
    }

    // 1 method parameter type to import
    public void newMethodParameterToImport(Currency cur) {
    }

}
