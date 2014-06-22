package org.splevo.tests.statements;

public class TryCatch {

    public String similarTryCatch(){
        try{
            return "hello";
        } catch (RuntimeException e){
            return "e";
        } catch (Exception iea){
            return "iea";
        }
    }

    public String changedTryCatch(){
        try{
            return "hello";
        } catch (RuntimeException e){
            return "e";
        }
    }
    
}
