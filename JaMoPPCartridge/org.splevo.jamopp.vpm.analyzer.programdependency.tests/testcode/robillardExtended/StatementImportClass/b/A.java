package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import java.util.HashMap;

public class A {

    public void doSth() {
        Integer myVar = 1;

        // 1 reference: constructor call to class import
        Object varClass = new HashMap<String,String>();

        System.out.println(myVar.toString());
    }

}
