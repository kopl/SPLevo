package org.splevo.modisco.util;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.modisco.java.composition.javaapplication.Java2Directory;
import org.eclipse.modisco.java.composition.javaapplication.Java2File;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.modisco.kdm.source.extension.ASTNodeSourceRegion;

/**
 * A connector to the implementation representation of the software.
 * 
 * @author Benjamin Klatt
 * 
 */
@SuppressWarnings("restriction")
public class SourceConnector {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SourceConnector.class);

    /**
     * The over-arching java application composition model for the source look up.
     */
    private JavaApplication compositionModel = null;

    /** The similarity checker to compare ast nodes during the source region lookup. */
    private SimilarityChecker similarityChecker = new SimilarityChecker();

    /**
     * Constructor requiring the application model to evaluate the connections.
     * 
     * @param compositionModel
     *            The composition model to connect.
     */
    public SourceConnector(JavaApplication compositionModel) {
        this.compositionModel = compositionModel;
    }

    /**
     * Find the source region for a provided AST node.
     * 
     * The implementation has been copied and modified from the MoDisco GetASTNodeSourceRegion query
     * from the java.composition plugin.
     * 
     * @see org.eclipse.modisco.java.composition.javaapplication.queries.GetASTNodeSourceRegion
     * 
     * @param astNode
     *            The ASTNode to look up.
     * @return The detected source region or null if none was found.
     */
    public JavaNodeSourceRegion findSourceRegion(ASTNode astNode) {
        JavaNodeSourceRegion result = null;

        CompilationUnit cu = astNode.getOriginalCompilationUnit();
        if (cu == null) {
            logger.warn("No CompilationUnit available for astNode: " + astNode);
            return null;
        }

        // MoDisco bug 332323: in default package, the package reference is null
        // for the compilation unit but not for the type declaration
        Package targetPackage = cu.getPackage();
        if (targetPackage == null) {
            // find target package from the ast node ...
            AbstractTypeDeclaration typeDeclaration = findTypeDeclaration(astNode);
            if (typeDeclaration != null) {
                targetPackage = typeDeclaration.getPackage();
            }
        }

        Java2Directory java2Directory = null;
        for (Java2Directory java2Dir : compositionModel.getJava2DirectoryChildren()) {

            // To ensure the proxy object has been resolved before comparison
            // it is stored in a separate variable and "equals()" is used.
            Package currentPackage = java2Dir.getJavaPackage();
            if (packagesAreEqual(currentPackage, targetPackage)) {
                java2Directory = java2Dir;
                break;
            }
        }

        Java2File java2File = null;
        if (java2Directory != null) {
            for (Java2File java2File2 : java2Directory.getJava2FileChildren()) {
                if (compUnitsAreEqual(java2File2.getJavaUnit(), cu)) {
                    java2File = java2File2;
                    break;
                }
            }
        }
        if (java2File != null) {
            for (ASTNodeSourceRegion astNodeSR : java2File.getChildren()) {
                if (astNodesAreEqual((ASTNode) astNodeSR.getNode(), astNode)) {
                    result = (JavaNodeSourceRegion) astNodeSR;
                    break;
                }
            }
        }
        return result;

    }

    /**
     * Check if two ast nodes are similar.
     * 
     * TODO: This might need to be improved for other ASTNode types or the loading of AST models
     * must be optimized.
     * 
     * @param node1
     *            The first node to compare.
     * @param node2
     *            The second node to compare.
     * @return True if they are equal, false if not.
     */
    private boolean astNodesAreEqual(ASTNode node1, ASTNode node2) {
        Boolean astNodeSimilarity = similarityChecker.isSimilar(node1, node2);
        if (astNodeSimilarity != null) {
            return astNodeSimilarity.booleanValue();
        }
        return astNodesAreEqual((ASTNode) node1.eContainer(), (ASTNode) node2.eContainer());

        // // check null similarity
        // if (node1 == null) {
        // return (node2 == null);
        // } else if (node2 == null) {
        // return false;
        // }
        //
        // // class check
        // if (node1.getClass() != node2.getClass()) {
        // return false;
        // }
        //
        // if (node1 instanceof Package) {
        // return packagesAreEqual((Package) node1, (Package) node2);
        // }
        //
        // if (node1 instanceof CompilationUnit) {
        // return compUnitsAreEqual((CompilationUnit) node1, (CompilationUnit) node2);
        // }
        //
        // // name check if applicable
        // if (node1 instanceof NamedElement) {
        // NamedElement ne1 = (NamedElement) node1;
        // NamedElement ne2 = (NamedElement) node2;
        //
        // if (ne1.getName() != null) {
        // if (!ne1.getName().equals(ne2.getName())) {
        // return false;
        // }
        // } else if (ne2.getName() != null) {
        // return false;
        // }
        // }
        //
        // return astNodesAreEqual((ASTNode) node1.eContainer(), (ASTNode) node2.eContainer());
    }

    /**
     * Check if two compilation units are similar.
     * 
     * @param cu1
     *            The first compilation unit to match.
     * @param cu2
     *            The second compilation unit to match.
     * @return True if they are equal, false if not.
     */
    private boolean compUnitsAreEqual(CompilationUnit cu1, CompilationUnit cu2) {

        // check null similarity
        if (cu1 == null) {
            return (cu2 == null);
        } else if (cu2 == null) {
            return false;
        }

        // package similarity
        if (!packagesAreEqual(cu1.getPackage(), cu2.getPackage())) {
            return false;
        }

        // file name similarity
        if (cu1.getName() != null && !cu1.getName().equals(cu2.getName())) {
            return false;
        }

        return true;
    }

    /**
     * Check if two packages are equal.
     * 
     * @param pck1
     *            The first package to compare.
     * @param pck2
     *            The second package to compare.
     * @return True if they are equal, false otherwise.
     */
    private boolean packagesAreEqual(Package pck1, Package pck2) {

        // check null similarity
        if (pck1 == null) {
            return (pck2 == null);
        } else if (pck2 == null) {
            return false;
        }

        // check name similarity
        if (pck1.getName() != null && !pck1.getName().equals(pck2.getName())) {
            return false;
        }

        // check parent similarity
        return packagesAreEqual(pck1.getPackage(), pck2.getPackage());
    }

    /**
     * Method to find a parent type declaration that has the same package as this ast node.
     * 
     * @param astNode
     *            The ast node to search the type declaration for.
     * @return The found abstract declaration type.
     */
    private AbstractTypeDeclaration findTypeDeclaration(ASTNode astNode) {
        AbstractTypeDeclaration result = null;
        if (astNode != null) {
            if (astNode instanceof AbstractTypeDeclaration) {
                result = (AbstractTypeDeclaration) astNode;
            } else {
                /*
                 * we don't have to check the compilation unit as it has been done earlier in call
                 * sequence if this method has to be used from a different context, a check will
                 * have to be done here
                 */
                CompilationUnit cu = astNode.getOriginalCompilationUnit();
                if (cu.getTypes().size() > 0) {
                    // get the first type
                    result = cu.getTypes().get(0);
                }
            }
        }
        return result;
    }

}
