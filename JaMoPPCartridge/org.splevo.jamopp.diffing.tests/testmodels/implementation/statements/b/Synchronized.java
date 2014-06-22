package org.splevo.tests.statements;

public class Synchronized {

    private Object synchronizationObject = new Byte[0];

    public void synchronizeOnField() {


        System.out.println("dummy change");

        synchronized (synchronizationObject) {
            return "synchronizeOnField";
        }
    }
}
