package org.splevo.tests.statements;

public class ReturnStatementChanges {

    public void doSth(String a){
        
        if(a == null){
            return;
        }
        
        return;
    }
    
    public void noReturn(){
        return;
    }
    
    public String returnWithExpression(){
        return "Good Bye";
    }
    
    public String returnWithExpressionEqual(){
        return "Good Morning";
    }
    
}
