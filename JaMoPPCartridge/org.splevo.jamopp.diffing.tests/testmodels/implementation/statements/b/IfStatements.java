package org.splevo.tests.statements;

import java.util.Iterator;
import java.util.LinkedList;

public class IfStatements {

    public void newIfStatementTest(String a) {

        // new if statement before
        if (a == null) {
            return;
        }

        if (a.length() > 1) {
            System.out.println();
        }
    }

    public void changedConditionIfStatementTest(String a) {

        if ("value1" == null) {
            return;
        }

        if ("valueB" == null) {
            return;
        }
    }

    public void unChangedConditionIfStatementTest(String a) {

        if (true && (toString() instanceof Object)) {
            return;
        }

        for (int i = 0; i < a.toCharArray().length; i++) {
            System.out.println(i);
        }

        boolean result = true;
        if (!result) {
            System.out.println(result);
        }

        int pos = 1;
        if (pos == -1) {
            return;
        }

        if (new Object() == this) {
            return;
        }

        if (new Object() == String.class) {

        }

        LinkedList<String> umlPredecessors = new LinkedList<String>();
        Iterator<String> it;
        it = (umlPredecessors != null) ? umlPredecessors.iterator() : null;
    }

}
