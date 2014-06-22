package org.splevo.tests.statements;

public class Synchronized {

    private Object synchronizationObject = new Byte[0];

    public void synchronizeOnField() {


        synchronized (synchronizationObject) {
            return "synchronizeOnField";
        }
    }
}
