package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

public class A {

    private int fieldInFor = 0;
    private int fieldInWhile = 0;
    private int fieldInDoWhile = 0;
    private int fieldInTry = 0;
    private int fieldInCatch = 0;

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

    public void newMethodInTry() {
        try {
            int a = fieldInTry;
        } catch(Exception e) {
            return;
        };
    }

    public void newMethodInCatch() {
        try {
            int b = 1;
        } catch (Exception e) {
            int a = fieldInCatch;
        }
    }

}
