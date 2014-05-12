package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    private int fieldInFor = 0;
    private int fieldInWhile = 0;
    private int fieldInDoWhile = 0;

    public void doUnchanged() {
        System.out.println(myVar.toString());
    }

    public void newMethodInFor() {
        for(int i = 0; i < 10; i++) {
            int a = fieldInFor;
        }
    }

    public void newMethodInWhile() {
        while(true) {
            int a = fieldInWhile;
        }
    }

    public void newMethodInDoWhile() {
        do {
            int a = fieldInDoWhile;
        } while(true);
    }

}
