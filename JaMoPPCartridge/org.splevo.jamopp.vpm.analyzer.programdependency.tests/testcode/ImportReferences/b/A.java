package org.splevo.jamopp.vpm.analyzer.programdependency.tests;


import java.util.Map;
import java.util.HashMap;

public class A {

    public void doSth() {
        Integer myVar = 1;

        Map<String,String> options = new HashMap<String,String>();
        options.put("key", "value");

        System.out.println(myVar.toString());
    }

}
