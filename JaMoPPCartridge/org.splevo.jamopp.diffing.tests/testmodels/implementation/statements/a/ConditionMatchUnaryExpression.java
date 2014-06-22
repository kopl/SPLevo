package org.splevo.tests.statements;

import java.util.List;

public class ConditionMatchUnaryExpression {

    public String doSth(List<String> a) {

        if(!(String.CASE_INSENSITIVE_ORDER.equals(a.iterator().next())
                && a.size() == ("test".length() + 1))) {
            return "existing conditional";
        }

        for (int i = 0; i < 10 && i >= 0; i++) {
            System.out.println(""+i);
        }
    }
}
