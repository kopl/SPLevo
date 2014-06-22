package org.splevo.tests.statements;

public class ClassStatementInsert {

    public void newStatement(){
        
        System.out.println(new Integer(0));
        
        System.out.println(new String());
        
        System.out.println(new Double(0));
        
        Boolean f = null;
        f = super.equals(new Object());
        
        int i = 0;
        int j = Integer.valueOf("0") + (i++); 
    }
    
    public void statementOrder(){
        
        int x, y;

        y = 10;
        x = 5;
        x = 3;
    }
    
}
