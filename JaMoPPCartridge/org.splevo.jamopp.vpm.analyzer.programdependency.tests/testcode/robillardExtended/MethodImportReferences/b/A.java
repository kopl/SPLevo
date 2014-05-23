package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Currency;
import java.util.Formatter.BigDecimalLayoutForm;
import java.util.Map;
import java.util.Properties;

public class A {

    // 1 return type to class import
    public Properties newMethodReturnToClassImport() {
        return null;
    }

    // 1 parameter type to class import
    public void newMethodParameterToClassImport(Currency cur) {
    }

    // 1 return type to interface import
    public Map<String,String> newMethodReturnToInterfaceImport() {
        return null;
    }

    // 1 parameter type to enumeration import
    public void newMethodParameterToInterfaceImport(BigDecimalLayoutForm form) {
    }
}
