## JaMoPP Refactoring

### Abstract
The refactoring component of SPLevo to consolidate code variations according to a given Variation Point Model (Variability Design) and configured Variability Realization Techniques.

### Technology Dependency
The refactoring is currently technology independend until a mature state of the requirements has been reached.

Each refactoring must provide 

#### Available Refactorings


##### If Else Basic
The if else basic refactoring changes two implementations with a statement
to a single if/else encapsulated one

Variant 1

    System.out.string("Leading");

Variant 2

    System.out.string("Integration");

Final

    if(INTEGRATION) {
	    System.out.string("Integration");
    } else {
	    System.out.string("Leading");
    }