package org.splevo.tests.methoddeclaration;

public class ClassA {
    
    public void newMethod(){
        System.out.println("a");
    }
    
    /**
     * Method to check the matching of an invocation of 
     * an anonymous' class method.
     */
    public void anonymousClassMethodInvocation(){
        
        Comparable<String> comp = new Comparable<String>() {
            @Override
            public int compareTo(String arg0) {
                return 0;
            }
        };
        
        comp.compareTo("Hello");
        
    }

}