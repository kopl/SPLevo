package org.splevo.tests.statements;

public class ConditionalVariable {

    protected void doSth(Collection c) {
        for (Iterator i = c.iterator(); i.hasNext(); ) {
            Object o = i.next();
            System.out.println(o);
        }
    }

}