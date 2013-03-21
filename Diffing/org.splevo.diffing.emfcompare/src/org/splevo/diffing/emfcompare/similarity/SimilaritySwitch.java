package org.splevo.diffing.emfcompare.similarity;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.UnresolvedTypeDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * Internal switch class to prove element similarity.
 */
public class SimilaritySwitch extends JavaSwitch<Boolean> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SimilaritySwitch.class);

    /** The object to compare the switched element with. */
    private EObject compareElement = null;

    /** Internal similarity checker to compare container elements etc. */
    private SimilarityChecker similarityChecker = new SimilarityChecker();

    /**
     * Constructor requiring the element to compare with.
     * 
     * @param compareElement
     *            The element to check the similarity with.
     */
    public SimilaritySwitch(EObject compareElement) {
        this.compareElement = compareElement;
    }

    @Override
    public Boolean caseExpressionStatement(ExpressionStatement expressionStatement1) {

        ExpressionStatement expressionStatement2 = (ExpressionStatement) compareElement;

        Expression expression1 = expressionStatement1.getExpression();
        Expression expression2 = expressionStatement2.getExpression();

        Boolean expressionSimilarity = similarityChecker.isSimilar(expression1, expression2);
        if (expressionSimilarity != null && expressionSimilarity == Boolean.FALSE) {
            return expressionSimilarity;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseImportDeclaration(ImportDeclaration object) {

        NamedElement importedElement1 = object.getImportedElement();
        NamedElement importedElement2 = ((ImportDeclaration) compareElement).getImportedElement();

        if (importedElement1 instanceof AbstractTypeDeclaration && importedElement2 instanceof AbstractTypeDeclaration) {

            AbstractTypeDeclaration atd1 = (AbstractTypeDeclaration) importedElement1;
            AbstractTypeDeclaration atd2 = (AbstractTypeDeclaration) importedElement2;

            return checkAbstractTypeDeclarationSimilarity(atd1, atd2);
        }

        if (importedElement1 instanceof Package && importedElement2 instanceof Package) {

            Package atd1 = (Package) importedElement1;
            Package atd2 = (Package) importedElement2;

            return checkPackageSimilarity(atd1, atd2);
        }

        if (importedElement1 instanceof MethodDeclaration && importedElement2 instanceof MethodDeclaration) {

            MethodDeclaration method1 = (MethodDeclaration) importedElement1;
            MethodDeclaration method2 = (MethodDeclaration) importedElement2;

            return similarityChecker.isSimilar(method1, method2);
        }

        logger.warn("Unhandled import type detected: " + importedElement1);
        return null;
    }

    @Override
    public Boolean caseModifier(Modifier object) {

        Modifier modifier1 = object;
        Modifier modifier2 = (Modifier) compareElement;

        // check visibility modifier
        if (modifier1.getVisibility() != null) {
            if (modifier2.getVisibility() == modifier1.getVisibility()) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }

        // check inheritance modifier
        if (modifier1.getInheritance() != null) {
            if (modifier2.getInheritance() == modifier1.getInheritance()) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }

        logger.warn("Unknown Modifier type: " + modifier1);
        return Boolean.FALSE;
    }

    /**
     * ArrayTypes are assumed to be similar if they represent an array of the same type.
     * 
     * @param arrayType
     *            The array type to compare
     * @return the boolean True/false/null whether it's similar or not or not decidable
     */
    @Override
    public Boolean caseArrayType(ArrayType arrayType) {

        ArrayType arrayType1 = arrayType;
        ArrayType arrayType2 = (ArrayType) compareElement;

        return similarityChecker
                .isSimilar(arrayType1.getElementType().getType(), arrayType2.getElementType().getType());
    }

    /**
     * WildCard types are assumed to be similar if their bound type is similar.
     * 
     * @param wildCardType
     *            The wild card type to compare
     * @return the boolean True/false/null whether it's similar or not or not decidable
     */
    @Override
    public Boolean caseWildCardType(WildCardType wildCardType) {
        WildCardType wildCardType1 = wildCardType;
        WildCardType wildCardType2 = (WildCardType) compareElement;

        // check that either both or none of the bounds should be null
        if (wildCardType1.getBound() == null) {
            if (wildCardType2.getBound() == null) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else if (wildCardType2 == null) {
            return Boolean.FALSE;
        }

        return similarityChecker.isSimilar(wildCardType1.getBound().getType(), wildCardType2.getBound().getType());
    }

    /**
     * Variables are assumed to be similar when they have the same names and are located in the same
     * container.
     * 
     * @param object
     *            the variable declaration to compare
     * @return true / false wether it is similar to the compare element.
     */
    @Override
    public Boolean caseVariableDeclaration(VariableDeclaration object) {

        VariableDeclaration variableDeclaration1 = object;
        VariableDeclaration variableDeclaration2 = (VariableDeclaration) compareElement;

        // check the variables name equality
        if (!variableDeclaration1.getName().equals(variableDeclaration2.getName())) {
            return Boolean.FALSE;
        }

        // check variable declaration container
        Boolean similarity = similarityChecker.isSimilar(variableDeclaration1.eContainer(),
                variableDeclaration2.eContainer());
        if (similarity != null) {
            return similarity;
        } else {
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean caseAbstractMethodDeclaration(AbstractMethodDeclaration object) {

        AbstractMethodDeclaration compareMethod = (AbstractMethodDeclaration) compareElement;

        // if methods have different names they are not similar.
        if (!object.getName().equals(compareMethod.getName())) {
            return false;
        }

        /* **************************************
         * methods as members of regular classes
         */
        if (object.getAbstractTypeDeclaration() != null) {
            AbstractTypeDeclaration type1 = object.getAbstractTypeDeclaration();
            AbstractTypeDeclaration type2 = compareMethod.getAbstractTypeDeclaration();
            return checkAbstractTypeDeclarationSimilarity(type1, type2);
        }

        /* **************************************
         * methods as members of anonymous classes
         */
        if (object.getAnonymousClassDeclarationOwner() != null) {
            AnonymousClassDeclaration type1 = object.getAnonymousClassDeclarationOwner();
            AnonymousClassDeclaration type2 = compareMethod.getAnonymousClassDeclarationOwner();
            Boolean result = checkAnonymousClassDeclarationSimilarity(type1, type2);
            if (result != null) {
                return result;
            }

        }

        /* **************************************
         * methods directly contained in the model with the same name are assumed to be similar
         */
        if (object.eContainer() instanceof Model) {
            if (compareMethod.eContainer() instanceof Model) {
                return Boolean.TRUE;
            } else {
                logger.warn("Methods with the same name contained in different containers: "
                        + compareMethod.eContainer().getClass().getSimpleName());
                return Boolean.FALSE;
            }
        }

        logger.warn("MethodDeclaration in unknown container: " + object.getName() + " : "
                + object.eContainer().getClass().getSimpleName());
        return super.caseAbstractMethodDeclaration(object);
    }

    /**
     * Check two abstract type declarations if they are similar.
     * 
     * It checks: 
     * - if one is null
     * - name similarity
     * - package similarity
     * - enclosing type similarity if packages are null and types are inner classes
     * 
     * @param type1
     *            The first type declaration to check.
     * @param type2
     *            The second type declaration to check.
     * @return true/false whether they are similar or not.
     */
    private Boolean checkAbstractTypeDeclarationSimilarity(AbstractTypeDeclaration type1, AbstractTypeDeclaration type2) {

        // if both types null: they are similar
        if (type1 == null && type2 == null) {
            return Boolean.TRUE;
        }

        // if only one is null: they are different
        if ((type1 == null && type2 != null) || (type1 != null && type2 == null)) {
            return Boolean.FALSE;
        }

        // if the types names are different then they do not match
        if (type1.getName() != null && !type1.getName().equals(type2.getName())) {
            return Boolean.FALSE;
        }

        Package package1 = type1.getPackage();
        Package package2 = type2.getPackage();

        // if only one containing package is null: FALSE
        if ((package1 == null && package2 != null) || (package1 != null && package2 == null)) {
            return Boolean.FALSE;

        // if none is null check the package names
        } else if (package1 != null && checkPackageSimilarity(package1, package2)) {
            return Boolean.TRUE;

            // if both packages are null check if both types are inner class of the same
            // enclosing class
        } else if (package1 == null && type1.eContainer() instanceof AbstractTypeDeclaration
                && type2.eContainer() instanceof AbstractTypeDeclaration) {
            AbstractTypeDeclaration enclosingAtd1 = (AbstractTypeDeclaration) type1.eContainer();
            AbstractTypeDeclaration enclosingAtd2 = (AbstractTypeDeclaration) type2.eContainer();
            return checkAbstractTypeDeclarationSimilarity(enclosingAtd1, enclosingAtd2);
        }

        return Boolean.FALSE;
    }

    /**
     * Check anonymous class declaration similarity.
     * 
     * @param type1
     *            the type1
     * @param type2
     *            the type2
     * @return the boolean
     */
    private Boolean checkAnonymousClassDeclarationSimilarity(AnonymousClassDeclaration type1,
            AnonymousClassDeclaration type2) {

        // if both types null: they are similar
        if (type1 == null && type2 == null) {
            return true;
        }

        // if only one is null: they are different
        if ((type1 == null && type2 != null) || (type1 != null && type2 == null)) {
            return false;
        }

        // if the parent class is again an anonymous one: recursively check this one
        if (type1.eContainer() instanceof AnonymousClassDeclaration) {
            return checkAnonymousClassDeclarationSimilarity((AnonymousClassDeclaration) type1.eContainer(),
                    (AnonymousClassDeclaration) type2.eContainer());
        }

        // if parent class are regular classes, return their similarity
        if (type1.eContainer() instanceof AbstractTypeDeclaration) {
            AbstractTypeDeclaration atd1 = (AbstractTypeDeclaration) type1.eContainer();
            AbstractTypeDeclaration atd2 = (AbstractTypeDeclaration) type2.eContainer();

            return checkAbstractTypeDeclarationSimilarity(atd1, atd2);
        }

        // if container is a class instance creation
        if (type1.eContainer() instanceof ClassInstanceCreation) {
            ClassInstanceCreation cic1 = (ClassInstanceCreation) type1.eContainer();
            ClassInstanceCreation cic2 = (ClassInstanceCreation) type2.eContainer();

            return similarityChecker.isSimilar(cic1, cic2);

        }

        logger.warn("Unknown anonymous class declaration container: [" + type1 + "] contained in ["
                + type1.eContainer() + "]");

        return null;
    }

    @Override
    public Boolean caseClassInstanceCreation(ClassInstanceCreation cic1) {

        ClassInstanceCreation cic2 = (ClassInstanceCreation) compareElement;

        // check the class instance types
        Boolean typeSimilarity = similarityChecker.isSimilar(cic1.getType().getType(), cic2.getType().getType());
        if (typeSimilarity != null && typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // check number of type arguments
        if (cic1.getArguments().size() != cic2.getArguments().size()) {
            return Boolean.FALSE;
        }

        // check the argument similarity
        for (int i = 0; i < cic1.getArguments().size(); i++) {
            Boolean argumentSimilarity = similarityChecker.isSimilar(cic1.getArguments().get(i), cic2.getArguments()
                    .get(i));
            if (Boolean.FALSE.equals(argumentSimilarity)) {
                return Boolean.FALSE;
            }
        }

        // check the class instance compilation units
        Boolean cuSimilarity = similarityChecker.isSimilar(cic1.getOriginalCompilationUnit(),
                cic2.getOriginalCompilationUnit());
        if (cuSimilarity != null && cuSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // **************************
        // Check Container similarity
        // **************************

        // Abstract type declaration container
        if (cic1.eContainer() instanceof AbstractTypeDeclaration) {
            return checkAbstractTypeDeclarationSimilarity((AbstractTypeDeclaration) cic1.eContainer(),
                    (AbstractTypeDeclaration) cic2.eContainer());
        }

        // logger.warn("Unknown container of ClassInstanceCreation: [" + cic1 + "] contained in [" +
        // cic1.eContainer()
        // + "]");

        return Boolean.TRUE;
    }

    /**
     * Check the similarity of two CompilationUnits.
     * Similarity is checked by
     * <ul>
     *  <li>Comparing their names</li>
     * </ul>
     * 
     * @param compUnit The compilation unit to compare with the compareElement.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseCompilationUnit(CompilationUnit compUnit) {

        CompilationUnit compareCompUnit = (CompilationUnit) compareElement;

        // The compilation unit name must be the same.
        if (!compUnit.getName().equals(compareCompUnit.getName())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean casePackage(Package packageElement) {
        Package referencePackage = (Package) compareElement;
        return checkPackageSimilarity(packageElement, referencePackage);
    }

    @Override
    public Boolean defaultCase(EObject object) {
        return null;
    }

    // TODO: Check for further PackageAccess similarity criteria
    @Override
    public Boolean casePackageAccess(PackageAccess packageAccess) {

        PackageAccess referencePackageAccess = (PackageAccess) compareElement;

        if (packageAccess == referencePackageAccess) {
            return true;
        } else if (checkPackageSimilarity(referencePackageAccess.getPackage(), packageAccess.getPackage())
                && (packageAccess.getOriginalCompilationUnit() == packageAccess.getOriginalCompilationUnit() || packageAccess
                        .getOriginalCompilationUnit().getName()
                        .equals(referencePackageAccess.getOriginalCompilationUnit().getName()))) {
            return true;
        }

        return false;
    }

    /**
     * Check SingleVariableDeclarations similarity. They are assumed to be similar if they have the
     * same variable name and are located in the same container.
     * 
     * @param varDeclaration1
     *            The variable declaration to compare.
     * @return the boolean True/false whether they are similar ot null if it could not be decided.
     */
    @Override
    public Boolean caseSingleVariableDeclaration(SingleVariableDeclaration varDeclaration1) {

        SingleVariableDeclaration varDeclaration2 = (SingleVariableDeclaration) compareElement;

        // Check name similarity
        if (varDeclaration1.getName() != null && !varDeclaration1.getName().equals(varDeclaration2.getName())) {
            return Boolean.FALSE;
        }

        // check container similarity
        Boolean containerSimilarity = similarityChecker.isSimilar(varDeclaration1.eContainer(),
                varDeclaration2.eContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseVariableDeclarationStatement(VariableDeclarationStatement varDeclStatement1) {

        VariableDeclarationStatement varDeclStatement2 = (VariableDeclarationStatement) compareElement;

        // check fragment count
        if (varDeclStatement1.getFragments().size() != varDeclStatement2.getFragments().size()) {
            return Boolean.FALSE;
        }

        // check container similarity
        Boolean containerSimilarity = similarityChecker.isSimilar(varDeclStatement1.eContainer(),
                varDeclStatement2.eContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        // check declared fragments
        for (int i = 0; i < varDeclStatement1.getFragments().size(); i++) {
            VariableDeclarationFragment frag1 = varDeclStatement1.getFragments().get(i);
            VariableDeclarationFragment frag2 = varDeclStatement2.getFragments().get(i);
            if (frag1.getName() != null && !frag1.getName().equals(frag2.getName())) {
                return Boolean.FALSE;
            } else if (frag1.getName() == null && frag2.getName() != null) {
                return Boolean.FALSE;
            }
        }

        // TODO: Check VariableDeclarationStatement position in container

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseTypeAccess(TypeAccess object) {

        TypeAccess typeAccess1 = object;
        TypeAccess typeAccess2 = (TypeAccess) compareElement;

        // check the similarity of the accessed type
        Type type1 = typeAccess1.getType();
        Type type2 = typeAccess2.getType();
        if (!type1.getClass().equals(type2.getClass())) {
            return Boolean.FALSE;
        }
        if (type1.getName() == null) {
            if (type2.getName() != null) {
                return Boolean.FALSE;
            }
        } else if (!type1.getName().equals(type2.getName())) {
            return Boolean.FALSE;
        }

        // check the container
        return similarityChecker.isSimilar(typeAccess1.eContainer(), typeAccess2.eContainer());

        // check the accessing type
        // if (!typeAccess1.eContainer().getClass().equals(typeAccess2.eContainer().getClass())) {
        // return Boolean.FALSE;
        // }
        // SimilaritySwitch similaritySwitchContainer = new
        // SimilaritySwitch(typeAccess1.eContainer());
        // Boolean similarContainer = similaritySwitchContainer.doSwitch(typeAccess2.eContainer());
        // if (similarContainer == Boolean.FALSE) {
        // return Boolean.FALSE;
        // }
        //
        // return Boolean.TRUE;
    }

    @Override
    public Boolean caseVariableDeclarationFragment(VariableDeclarationFragment varDeclFrag) {

        VariableDeclarationFragment varDeclFrag2 = (VariableDeclarationFragment) compareElement;

        // name check
        if (varDeclFrag.getName() != null && !varDeclFrag.getName().equals(varDeclFrag2.getName())) {
            return Boolean.FALSE;
        }

        // container check
        Boolean containerSimilarity = similarityChecker.isSimilar(varDeclFrag.eContainer(), varDeclFrag2.eContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        logger.warn("variable declaration fragment container not supported: " + varDeclFrag.eContainer());

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseVariableDeclarationExpression(VariableDeclarationExpression variableDeclarationExpression1) {

        VariableDeclarationExpression variableDeclarationExpression2 = (VariableDeclarationExpression) compareElement;

        if (variableDeclarationExpression1.getFragments().size() != variableDeclarationExpression2.getFragments()
                .size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < variableDeclarationExpression1.getFragments().size(); i++) {
            VariableDeclarationFragment varDeclFrag1 = variableDeclarationExpression1.getFragments().get(i);
            VariableDeclarationFragment varDeclFrag2 = variableDeclarationExpression2.getFragments().get(i);

            if (varDeclFrag1.getName() != null && !varDeclFrag1.getName().equals(varDeclFrag2.getName())) {
                return Boolean.FALSE;
            } else if (varDeclFrag1.getName() == null && varDeclFrag2.getName() != null) {
                return Boolean.FALSE;
            }
        }

        // TODO check namespace (container)
        return Boolean.TRUE;
    }

    @Override
    public Boolean caseFieldDeclaration(FieldDeclaration fDecl) {

        FieldDeclaration fDecl2 = (FieldDeclaration) compareElement;

        // fragment check
        if (fDecl.getFragments().size() != fDecl2.getFragments().size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < fDecl.getFragments().size(); i++) {
            VariableDeclarationFragment fragment1 = fDecl.getFragments().get(i);
            VariableDeclarationFragment fragment2 = fDecl2.getFragments().get(i);
            if (!fragment1.getName().equals(fragment2.getName())) {
                return Boolean.FALSE;
            }
        }

        // type check
        if (fDecl.getType() != null && fDecl2.getType() != null) {
            Boolean typeSimilarity = similarityChecker.isSimilar(fDecl.getType().getType(), fDecl2.getType()
                    .getType());
            if (typeSimilarity != null && typeSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        } else if ((fDecl.getType() != null && fDecl2.getType() == null)
                || (fDecl.getType() == null && fDecl2.getType() != null)) {
            return Boolean.FALSE;
        }

        // container check
        Boolean containerSimilarity = similarityChecker.isSimilar(fDecl.eContainer(), fDecl2.eContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        return Boolean.TRUE;
    }

    /**
     * Check the similarity of an abstract type declaration.
     * 
     * They should only match if they are in the same package and have the same name This might be
     * further enhanced in the future by comparing their contained fields, methods, etc.
     * 
     * @param object
     *            The type declaration to check.
     * @return true/false whether the element match or not.
     */
    @Override
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
        AbstractTypeDeclaration type1 = object;
        AbstractTypeDeclaration type2 = (AbstractTypeDeclaration) compareElement;

        return checkAbstractTypeDeclarationSimilarity(type1, type2);
    }

    @Override
    public Boolean caseParameterizedType(ParameterizedType object) {

        ParameterizedType type1 = object;
        ParameterizedType type2 = (ParameterizedType) compareElement;

        // check that the names are equal
        if (!type1.getName().equals(type2.getName())) {
            return Boolean.FALSE;
        }

        // check that the type has the same number of arguments
        if (type1.getTypeArguments().size() != type2.getTypeArguments().size()) {
            return Boolean.FALSE;
        }

        // for each argument of type1, check it's equal to the corresponding
        // one in the second type.
        // type arguments must be ordered.
        for (int i = 0; i < type1.getTypeArguments().size(); i++) {
            Type accessedType1 = type1.getTypeArguments().get(i).getType();
            Type accessedType2 = type2.getTypeArguments().get(i).getType();

            if (accessedType1.getName() == null) {
                if (accessedType2.getName() != null) {
                    return false;
                }
            } else if (!accessedType1.getName().equals(accessedType2.getName())) {
                return false;
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean casePrimitiveType(PrimitiveType object) {
        return Boolean.TRUE;
    }

    /**
     * Type parameters are always considered as similar. They might defer because of their type
     * restriction (e.g. List<T extends ...>) but this is ignored for now.
     * 
     * @param object
     *            the object to check the similarity for.
     * @return TRUE
     */
    @Override
    public Boolean caseTypeParameter(TypeParameter object) {
        return Boolean.TRUE;
    }

    /**
     * Check if two method invocations are similar. This checks
     * <ul>
     * <li>the method name</li>
     * <li>the method declaring type</li>
     * <li>the method parameters</li>
     * <li>the method return type</li>
     * </ul>
     * 
     * @param methodInvocation
     *            the method invocation to check
     * @return the boolean
     */
    @Override
    public Boolean caseMethodInvocation(MethodInvocation methodInvocation) {

        MethodInvocation methodInvocation2 = (MethodInvocation) compareElement;

        // check the methods names
        AbstractMethodDeclaration method1 = methodInvocation.getMethod();
        AbstractMethodDeclaration method2 = methodInvocation2.getMethod();
        if (!method1.getName().equals(method2.getName())) {
            return Boolean.FALSE;
        }

        // check the methods declaring types
        AbstractTypeDeclaration type1 = method1.getAbstractTypeDeclaration();
        AbstractTypeDeclaration type2 = method2.getAbstractTypeDeclaration();
        AnonymousClassDeclaration acd1 = method1.getAnonymousClassDeclarationOwner();
        AnonymousClassDeclaration acd2 = method2.getAnonymousClassDeclarationOwner();

        if ((type1 != null && type2 == null) || (type1 == null && type2 != null)) {
            logger.debug("methodInvocations not similar: only one invokes a method declared in a type: [m1: "
                    + method1.getName() + " m2: " + method2.getName() + "]");
            return Boolean.FALSE;

        } else if (type1 != null && type2 != null) {
            String fqnType1 = JavaModelUtil.buildFullQualifiedName(type1);
            String fqnType2 = JavaModelUtil.buildFullQualifiedName(type2);
            if (!fqnType1.equals(fqnType2)) {
                return Boolean.FALSE;
            }
        } else if ((acd1 != null && acd2 == null) || (acd1 == null && acd2 != null)) {
            logger.debug("methodInvocations not similar: only one invokes a method declared in a type: [m1: "
                    + method1.getName() + " m2: " + method2.getName() + "]");
            return Boolean.FALSE;

        } else if (acd1 != null && acd2 != null) {
            String fqnAcd1 = JavaModelUtil.buildFullQualifiedName(acd1);
            String fqnAcd2 = JavaModelUtil.buildFullQualifiedName(acd2);
            if ((fqnAcd1 == null && fqnAcd2 != null) || (fqnAcd1 != null && !fqnAcd1.equals(fqnAcd2))) {
                return Boolean.FALSE;
            }
        } else {
            logger.warn("Unknown method declaration container: [" + method1 + "|" + method1.eContainer() + "]");
        }

        // TODO check if the proactive parameter check is really necessary
        // or of this is done by emf compare by default

        // check parameter count
        if (method1.getParameters().size() != method2.getParameters().size()) {
            logger.debug("methodInvocations not similar because of different parameter counts ");
            return Boolean.FALSE;
        }

        // check parameter types
        // TODO check not only the type names but also the type packages
        for (int i = 0; i < method1.getParameters().size(); i++) {
            SingleVariableDeclaration varDecl1 = method1.getParameters().get(i);
            SingleVariableDeclaration varDecl2 = method1.getParameters().get(i);

            String var1TypeName = varDecl1.getType().getType().getName();
            String var2TypeName = varDecl2.getType().getType().getName();
            if (!var1TypeName.equals(var2TypeName)) {
                logger.debug("methodInvocations not similar because of different parameter types ");
                return Boolean.FALSE;
            }
        }

        // TODO check the variable access
        for (int i = 0; i < methodInvocation.getArguments().size(); i++) {
            Expression methodArgument1 = methodInvocation.getArguments().get(i);
            Expression methodArgument2 = methodInvocation2.getArguments().get(i);

            Boolean argumentSimilarity = similarityChecker.isSimilar(methodArgument1, methodArgument2);
            if (argumentSimilarity != null && argumentSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Null literals are always assumed to be similar.
     * {@inheritDoc}
     */
    @Override
    public Boolean caseNullLiteral(NullLiteral object) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean caseSingleVariableAccess(SingleVariableAccess singleVatriableAccess1) {

        SingleVariableAccess singleVatriableAccess2 = (SingleVariableAccess) compareElement;

        String name1 = singleVatriableAccess1.getVariable().getName();
        String name2 = singleVatriableAccess2.getVariable().getName();

        if (name1 != null && !name1.equals(name2)) {
            return Boolean.FALSE;
        } else if (name1 == null && name2 != null) {
            return Boolean.FALSE;
        }

        // TODO check variable declaration namespace (container)

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseUnresolvedTypeDeclaration(UnresolvedTypeDeclaration object) {

        UnresolvedTypeDeclaration type1 = object;
        UnresolvedTypeDeclaration type2 = (UnresolvedTypeDeclaration) compareElement;
        
        if (type1.getName().equals(type2.getName())) {
            return Boolean.TRUE;
        }
        
        // TODO Add package similarity check

        return Boolean.FALSE;
    }

    /**
     * ICheck two package elements for similarity.
     * 
     * Check
     * - package name
     * - package path
     * 
     * @param packageElement
     *            the package element
     * @param referencePackage
     *            the reference package
     * @return the check result.
     */
    private Boolean checkPackageSimilarity(Package packageElement, Package referencePackage) {

        // similar packages are similar by default.
        if (packageElement == referencePackage) {
            return true;

            // packages with same name and same parent packages name are considered as similar.
        } else if (packageElement != null && referencePackage != null) {

            String packagePath1 = JavaModelUtil.buildPackagePath(packageElement);
            String packagePath2 = JavaModelUtil.buildPackagePath(referencePackage);
            if (packagePath1.equals(packagePath2)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        }

        return false;
    }

}