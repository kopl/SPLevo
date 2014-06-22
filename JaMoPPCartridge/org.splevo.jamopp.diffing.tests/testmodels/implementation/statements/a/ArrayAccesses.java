package org.splevo.tests.statements;

public class ArrayAccesses {

    public static void main(String[] args) {
        
        @SuppressWarnings("unused")
        String projectName;
        for(int i = 0; i < args.length; i++){
            if (args[i].equalsIgnoreCase("-print") 
                    && i + 1 < args.length) {
                projectName = args[++i];
            }
        }
        
        Object[] elements = new Object[args.length][2];
        
    }
}
