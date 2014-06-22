package org.splevo.tests.statements;

public class StaticInitialization {

    public static final String ID1 = "ID1";

    static{

        putDefaultShortcut(ID1, null, new String());

    }

    private static void putDefaultShortcut(String id, Object defaultValue, Object action){

    }
}
