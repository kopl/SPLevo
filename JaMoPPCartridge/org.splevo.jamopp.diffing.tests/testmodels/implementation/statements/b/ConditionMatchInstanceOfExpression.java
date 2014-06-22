package org.splevo.tests.statements;

public class ConditionMatchInstanceOfExpression {

    public String doSth(Object a) {

        if(a instanceof String) {
            return "new instanceof conditional";
        }

        if(a instanceof Integer) {
            return "existing instanceof";
        }

        for (int i = 0; i < 10 && i >= 0; i++) {
            System.out.println(""+i);
        }
    }
}
