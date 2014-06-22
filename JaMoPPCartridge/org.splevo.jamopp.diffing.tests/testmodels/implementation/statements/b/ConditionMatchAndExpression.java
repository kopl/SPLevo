package org.splevo.tests.statements;

import java.util.Iterator;
import java.util.LinkedList;

public class ConditionMatchAndExpression {

    public String doSth(String[] a) {

        if(true && a.length() > 0) {
            return "new conditional";
        }

        if (a.length() > 0 && true) {
            return "existing conditional";
        }

        for (int i = 0; i < 10 && i >= 0; i++) {
            System.out.println(""+i);
        }
    }
}
