package org.splevo.tests.statements;

import java.util.Iterator;
import java.util.LinkedList;

public class ConditionMatchRelationExpression {

    public String doSth(String[] a) {

        if(getClass().getName().length() + a.length > 0) {
            return "new conditional";
        }

        if (a.length() > 0) {
            return "existing conditional";
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(""+i);
        }
    }
}
