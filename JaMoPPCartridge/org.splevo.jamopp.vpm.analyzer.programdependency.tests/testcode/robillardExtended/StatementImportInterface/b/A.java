package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Map;

public class A {

    public void doSth() {
        Integer myVar = 1;

        // 1 reference: variable type to interface import
        Map<String,String> varInterface = null;

        System.out.println(myVar.toString());
    }

}
