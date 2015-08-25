//SPLEVO_REF ID_Class
public class CommentedExampleClass {

    //SPLEVO_REF ID_Field
    private int singleLine = 0;

    public void doSth() {
        
        //SPLEVO_REF ID_INNER_CLASS
        class ABC {
            //SPLEVO_REF ID_INNER_FIELD
            private int otherField = 1;
        }
    }
    
    //SPLEVO_REF ID_Method
    public void commentabelSoftwareElementTest() {
        //SPLEVO_REF ID_Statement
        System.out.println("Hello World");
    }
}
