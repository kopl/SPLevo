package org.splevo.modisco.util;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.ArrayLengthAccess;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.CastExpression;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ConditionalExpression;
import org.eclipse.gmt.modisco.java.DoStatement;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.MethodRef;
import org.eclipse.gmt.modisco.java.MethodRefParameter;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.ParenthesizedExpression;
import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.SynchronizedStatement;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.ThisExpression;
import org.eclipse.gmt.modisco.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeLiteral;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.UnresolvedItemAccess;
import org.eclipse.gmt.modisco.java.UnresolvedTypeDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * Internal switch class to prove element similarity.
 * 
 * <p>
 * The similarity case methods do not need to check for null values. It is assumed that the calling
 * class does a null value check for the elements to compare in advanced, such as done by the
 * SimilarityChecker class.
 * </p>
 * 
 * <p>
 * Check strategy:<br>
 * First all "not-similar"-criteria are checked. If none hits, true will be returned.
 * </p>
 */
@SuppressWarnings("restriction")
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

    /**
     * Check expression statement similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>similarity statements expressions</li>
     * </ul>
     * 
     * @param expressionStatement1
     *            The expression statement to compare with the compare element.
     * @return True/False if the expression statements are similar or not.
     */
    @Override
    public Boolean caseExpressionStatement(ExpressionStatement expressionStatement1) {

        ExpressionStatement expressionStatement2 = (ExpressionStatement) compareElement;

        Expression expression1 = expressionStatement1.getExpression();
        Expression expression2 = expressionStatement2.getExpression();

        Boolean expressionSimilarity = similarityChecker.isSimilar(expression1, expression2);
        if (expressionSimilarity == Boolean.FALSE) {
            return expressionSimilarity;
        }

        return Boolean.TRUE;
    }

    /**
     * Check return statement similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>expressions similarity</li>
     * </ul>
     * 
     * @param returnStatement1
     *            The return statement to compare with the compare element.
     * @return True/False if the return statements are similar or not.
     */
    @Override
    public Boolean caseReturnStatement(ReturnStatement returnStatement1) {

        ReturnStatement returnStatement2 = (ReturnStatement) compareElement;

        Expression exp1 = returnStatement1.getExpression();
        Expression exp2 = returnStatement2.getExpression();

        return similarityChecker.isSimilar(exp1, exp2);
    }

    /**
     * Check synchronized statement similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>expression similarity</li>
     * </ul>
     * 
     * @param stmt1
     *            The synchronized statement to compare with the compare element.
     * @return True/False if the synchronized statements are similar or not.
     */
    @Override
    public Boolean caseSynchronizedStatement(SynchronizedStatement stmt1) {

        SynchronizedStatement stmt2 = (SynchronizedStatement) compareElement;

        Expression exp1 = stmt1.getExpression();
        Expression exp2 = stmt2.getExpression();

        return similarityChecker.isSimilar(exp1, exp2);
    }

    /**
     * Check throw statement similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>expressions similarity</li>
     * </ul>
     * 
     * @param throwStatement1
     *            The throw statement to compare with the compare element.
     * @return True/False if the throw statements are similar or not.
     */
    @Override
    public Boolean caseThrowStatement(ThrowStatement throwStatement1) {
        ThrowStatement throwStatement2 = (ThrowStatement) compareElement;

        Expression exp1 = throwStatement1.getExpression();
        Expression exp2 = throwStatement2.getExpression();

        return similarityChecker.isSimilar(exp1, exp2);
    }

    /**
     * Check import declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>similarity of the imported element.<br>
     * This is one of:
     * <ul>
     * <li>Type similarity</li>
     * <li>Package similarity</li>
     * <li>Method similarity</li>
     * </ul>
     * </li>
     * </ul>
     * 
     * @param importStatement
     *            The import statement to compare with the compare element.
     * @return True/False if the import statements are similar or not.
     */
    @Override
    public Boolean caseImportDeclaration(ImportDeclaration importStatement) {

        NamedElement importedElement1 = importStatement.getImportedElement();
        NamedElement importedElement2 = ((ImportDeclaration) compareElement).getImportedElement();

        Boolean importedSimilarity = similarityChecker.isSimilar(importedElement1, importedElement2);
        if (importedSimilarity != null) {
            return importedSimilarity;
        }

        logger.warn("Unhandled import type detected: " + importedElement1);
        return null;
    }

    /**
     * Check modifier similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>visibility modifier similarity</li>
     * <li>inheritance modifier similarity</li>
     * </ul>
     * 
     * @param modifier1
     *            The modifier to compare with the compare element.
     * @return True/False if the modifiers are similar or not.
     */
    @Override
    public Boolean caseModifier(Modifier modifier1) {

        Modifier modifier2 = (Modifier) compareElement;

        // check visibility modifier
        if (modifier2.getVisibility() != modifier1.getVisibility()) {
            return Boolean.FALSE;
        }

        // check inheritance modifier
        if (modifier1.getInheritance() != modifier2.getInheritance()) {
            return Boolean.FALSE;
        }

        if (modifier1.isNative() != modifier2.isNative()) {
            return Boolean.FALSE;
        }

        if (modifier1.isStatic() != modifier2.isStatic()) {
            return Boolean.FALSE;
        }

        if (modifier1.isStrictfp() != modifier2.isStrictfp()) {
            return Boolean.FALSE;
        }

        if (modifier1.isSynchronized() != modifier2.isSynchronized()) {
            return Boolean.FALSE;
        }

        if (modifier1.isTransient() != modifier2.isTransient()) {
            return Boolean.FALSE;
        }

        if (modifier1.isVolatile() != modifier2.isVolatile()) {
            return Boolean.FALSE;
        }

        Boolean containerSimilarity = similarityChecker.isSimilar(modifier1.eContainer(), modifier2.eContainer());
        if (containerSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check array type similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>type of array elements</li>
     * </ul>
     * 
     * @param array1
     *            The array type to compare with the compare element.
     * @return True/False if the array types are similar or not.
     */
    @Override
    public Boolean caseArrayType(ArrayType array1) {

        ArrayType array2 = (ArrayType) compareElement;

        Type type1 = array1.getElementType().getType();
        Type type2 = array2.getElementType().getType();

        return similarityChecker.isSimilar(type1, type2);
    }

    /**
     * Check wild card type similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>wild card name</li>
     * <li>wild card bound similarity</li>
     * </ul>
     * 
     * @param wildCardType1
     *            The array type to compare with the compare element.
     * @return True/False if the array types are similar or not.
     */
    @Override
    public Boolean caseWildCardType(WildCardType wildCardType1) {

        WildCardType wildCardType2 = (WildCardType) compareElement;

        if (wildCardType1.getName() != null && !wildCardType1.getName().equals(wildCardType2.getName())) {
            return Boolean.FALSE;
        }

        return similarityChecker.isSimilar(wildCardType1.getBound(), wildCardType2.getBound());
    }

    /**
     * Check variable declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>variable name</li>
     * <li>variable container (name space)</li>
     * </ul>
     * 
     * @param variableDeclaration1
     *            The variable declaration to compare with the compare element.
     * @return True/False if the variable declarations are similar or not.
     */
    @Override
    public Boolean caseVariableDeclaration(VariableDeclaration variableDeclaration1) {

        VariableDeclaration variableDeclaration2 = (VariableDeclaration) compareElement;

        // check the variables name equality
        if (!variableDeclaration1.getName().equals(variableDeclaration2.getName())) {
            return Boolean.FALSE;
        }

        // check variable declaration container
        EObject container1 = variableDeclaration1.eContainer();
        EObject container2 = variableDeclaration2.eContainer();
        Boolean containerSimilarity = similarityChecker.isSimilar(container1, container2);
        if (containerSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check abstract method declaration similarity. Similarity is checked by
     * <ul>
     * <li>name</li>
     * <li>parameter list size</li>
     * <li>parameter types</li>
     * <li>name</li>
     * <li>container for
     * <ul>
     * <li>AbstractTypeDeclaration</li>
     * <li>AnonymousClassDeclaration</li>
     * <li>Model</li>
     * </ul>
     * </li>
     * </ul>
     * 
     * @param method1
     *            The abstract method declaration to compare with the compare element.
     * @return True/False if the abstract method declarations are similar or not.
     */
    @Override
    public Boolean caseAbstractMethodDeclaration(AbstractMethodDeclaration method1) {

        AbstractMethodDeclaration method2 = (AbstractMethodDeclaration) compareElement;

        // if methods have different names they are not similar.
        if (!method1.getName().equals(method2.getName())) {
            return Boolean.FALSE;
        }

        if (method1.getParameters().size() != method2.getParameters().size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < method1.getParameters().size(); i++) {
            SingleVariableDeclaration var1 = method1.getParameters().get(i);
            SingleVariableDeclaration var2 = method2.getParameters().get(i);
            Type type1 = var1.getType().getType();
            Type type2 = var2.getType().getType();
            Boolean typeSimilarity = similarityChecker.isSimilar(type1, type2);
            if (typeSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        /* **************************************
         * methods as members of regular classes
         */
        if (method1.getAbstractTypeDeclaration() != null) {
            AbstractTypeDeclaration type1 = method1.getAbstractTypeDeclaration();
            AbstractTypeDeclaration type2 = method2.getAbstractTypeDeclaration();
            return similarityChecker.isSimilar(type1, type2);
        }

        /* **************************************
         * methods as members of anonymous classes
         */
        if (method1.getAnonymousClassDeclarationOwner() != null) {
            AnonymousClassDeclaration type1 = method1.getAnonymousClassDeclarationOwner();
            AnonymousClassDeclaration type2 = method2.getAnonymousClassDeclarationOwner();
            Boolean typeSimilarity = similarityChecker.isSimilar(type1, type2);
            if (typeSimilarity != null) {
                return typeSimilarity;
            }

        }

        /* **************************************
         * methods directly contained in the model with the same name are assumed to be similar
         */
        if (method1.eContainer() instanceof Model) {
            if (method2.eContainer() instanceof Model) {
                return Boolean.TRUE;
            } else {
                logger.warn("Methods with the same name contained in different containers: "
                        + method2.eContainer().getClass().getSimpleName());
                return Boolean.FALSE;
            }
        }

        logger.warn("MethodDeclaration in unknown container: " + method1.getName() + " : "
                + method1.eContainer().getClass().getSimpleName());
        return super.caseAbstractMethodDeclaration(method1);
    }

    /**
     * Check class instance creation similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>instance type similarity</li>
     * <li>number of constructor arguments</li>
     * <li>types of constructor arguments</li>
     * <li>container of the class instance creation</li>
     * </ul>
     * 
     * @param cic1
     *            The class instance creation to compare with the compare element.
     * @return True/False if the class instance creations are similar or not.
     */
    @Override
    public Boolean caseClassInstanceCreation(ClassInstanceCreation cic1) {

        ClassInstanceCreation cic2 = (ClassInstanceCreation) compareElement;

        // check the class instance types
        Boolean typeSimilarity = similarityChecker.isSimilar(cic1.getType().getType(), cic2.getType().getType());
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // check number of type arguments
        EList<Expression> cic1Args = cic1.getArguments();
        EList<Expression> cic2Args = cic2.getArguments();
        if (cic1Args.size() != cic2Args.size()) {
            return Boolean.FALSE;
        }

        // check the argument similarity
        for (int i = 0; i < cic1Args.size(); i++) {
            Boolean argumentSimilarity = similarityChecker.isSimilar(cic1Args.get(i), cic2Args.get(i));
            if (argumentSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        // container similarity
        if (cic1.eContainer() instanceof AbstractTypeDeclaration) {
            AbstractTypeDeclaration type1 = (AbstractTypeDeclaration) cic1.eContainer();
            AbstractTypeDeclaration type2 = (AbstractTypeDeclaration) cic2.eContainer();
            Boolean containerSimilarity = similarityChecker.isSimilar(type1, type2);
            if (containerSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check the similarity of two CompilationUnits.<br>
     * Similarity is checked by
     * <ul>
     * <li>Comparing their names</li>
     * </ul>
     * 
     * @param compUnit
     *            The compilation unit to compare with the compareElement.
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

    /**
     * Check package similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>full qualified package path</li>
     * </ul>
     * 
     * @param package1
     *            The package to compare with the compare element.
     * @return True/False if the packages are similar or not.
     */
    @Override
    public Boolean casePackage(Package package1) {
        Package package2 = (Package) compareElement;

        String packagePath1 = JavaModelUtil.buildPackagePath(package1);
        String packagePath2 = JavaModelUtil.buildPackagePath(package2);
        if (!packagePath1.equals(packagePath2)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check package access similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>accessed package similarity</li>
     * <li>compilation unit (location) similarity</li>
     * </ul>
     * 
     * @param access1
     *            The package access to compare with the compare element.
     * @return True/False if the package accesses are similar or not.
     */
    @Override
    public Boolean casePackageAccess(PackageAccess access1) {

        PackageAccess access2 = (PackageAccess) compareElement;

        Boolean packageSimilarity = similarityChecker.isSimilar(access1.getPackage(), access2.getPackage());
        if (packageSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean compUnitSimilarity = similarityChecker.isSimilar(access1.getOriginalCompilationUnit(),
                access2.getOriginalCompilationUnit());
        if (compUnitSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
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
        if (containerSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
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

    /**
     * Check if two if statements are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>similarity of the expressions</li>
     * </ul>
     * 
     * The then and else statements are not checked as part of the if statement check because this
     * is only about the container if statement similarity. The contained statements are checked in
     * a separate step of the compare process if the enclosing if statement matches.
     * 
     * @param ifStatement1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseIfStatement(IfStatement ifStatement1) {

        IfStatement ifStatement2 = (IfStatement) compareElement;

        Expression expression1 = ifStatement1.getExpression();
        Expression expression2 = ifStatement2.getExpression();
        Boolean expressionSimilarity = similarityChecker.isSimilar(expression1, expression2);
        if (expressionSimilarity == Boolean.FALSE) {
            return expressionSimilarity;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseSwitchStatement(SwitchStatement switch1) {

        SwitchStatement switch2 = (SwitchStatement) compareElement;

        Expression expression1 = switch1.getExpression();
        Expression expression2 = switch2.getExpression();
        Boolean expressionSimilarity = similarityChecker.isSimilar(expression1, expression2);
        if (expressionSimilarity == Boolean.FALSE) {
            return expressionSimilarity;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseSwitchCase(SwitchCase case1) {

        SwitchCase case2 = (SwitchCase) compareElement;

        Expression expression1 = case1.getExpression();
        Expression expression2 = case2.getExpression();

        Boolean expressionSimilarity = similarityChecker.isSimilar(expression1, expression2);
        if (expressionSimilarity == Boolean.FALSE) {
            return expressionSimilarity;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two for statements are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>number of initializer similarity</li>
     * <li>number of updater similarity</li>
     * <li>expression similarity</li>
     * </ul>
     * 
     * TODO Add updater and initializer checks
     * 
     * The body is not checked as part of the for statement check because this is only about the
     * container for statement similarity. The contained statements are checked in a separate step
     * of the compare process if the enclosing for statement matches.
     * 
     * @param stmt1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseForStatement(ForStatement stmt1) {

        ForStatement stmt2 = (ForStatement) compareElement;

        if (stmt1.getInitializers().size() != stmt2.getInitializers().size()) {
            return Boolean.FALSE;
        }

        if (stmt1.getUpdaters().size() != stmt2.getUpdaters().size()) {
            return Boolean.FALSE;
        }

        Boolean expressionSimilarity = similarityChecker.isSimilar(stmt1.getExpression(), stmt2.getExpression());
        if (expressionSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two enhanced for statements are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>expression similarity</li>
     * </ul>
     * 
     * The body is not checked as part of the for statement check because this is only about the
     * container enhanced for statement similarity. The contained statements are checked in a
     * separate step of the compare process if the enclosing for statement matches.
     * 
     * @param for1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseEnhancedForStatement(EnhancedForStatement for1) {

        EnhancedForStatement for2 = (EnhancedForStatement) compareElement;

        Boolean expressionSimilarity = similarityChecker.isSimilar(for1.getExpression(), for2.getExpression());
        if (expressionSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two while statements are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>expression similarity</li>
     * </ul>
     * 
     * The body is not checked as part of the while statement check because this is only about the
     * container enhanced for statement similarity. The contained statements are checked in a
     * separate step of the compare process if the enclosing for statement matches.
     * 
     * @param statement1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseWhileStatement(WhileStatement statement1) {

        WhileStatement statement2 = (WhileStatement) compareElement;

        Boolean expressionSimilarity = similarityChecker.isSimilar(statement1.getExpression(),
                statement2.getExpression());
        if (expressionSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two while statements are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>expression similarity</li>
     * </ul>
     * 
     * The body is not checked as part of the while statement check because this is only about the
     * container enhanced for statement similarity. The contained statements are checked in a
     * separate step of the compare process if the enclosing for statement matches.
     * 
     * @param statement1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseDoStatement(DoStatement statement1) {

        DoStatement statement2 = (DoStatement) compareElement;

        Boolean expressionSimilarity = similarityChecker.isSimilar(statement1.getExpression(),
                statement2.getExpression());
        if (expressionSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two if catch clauses are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>similarity of the caught exception type</li>
     * <li>container similarity</li>
     * </ul>
     * 
     * The then and else statements are not checked as part of the if statement check because this
     * is only about the container if statement similarity. The contained statements are checked in
     * a separate step of the compare process if the enclosing if statement matches.
     * 
     * @param cc1
     *            The catch clause to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseCatchClause(CatchClause cc1) {

        CatchClause cc2 = (CatchClause) compareElement;

        SingleVariableDeclaration varDecl1 = cc1.getException();
        SingleVariableDeclaration varDecl2 = cc2.getException();
        Boolean typeSimilarity = similarityChecker
                .isSimilar(varDecl1.getType().getType(), varDecl2.getType().getType());
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean containerSimilarity = similarityChecker.isSimilar(cc1.eContainer(), cc2.eContainer());
        if (containerSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check infix expression similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the operator</li>
     * <li>similarity of the left operand</li>
     * <li>similarity of the right operand</li>
     * </ul>
     * 
     * Not supported: Extended Operands.
     * 
     * @param exp1
     *            The infix expression to compare with the compare element.
     * @return True/False if the infix expressions are similar or not.
     */
    @Override
    public Boolean caseInfixExpression(InfixExpression exp1) {

        InfixExpression exp2 = (InfixExpression) compareElement;

        if (exp1.getOperator().compareTo(exp2.getOperator()) != 0) {
            return Boolean.FALSE;
        }

        Boolean leftOpSimilarity = similarityChecker.isSimilar(exp2.getLeftOperand(), exp1.getLeftOperand());
        if (leftOpSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean rightOpSimilarity = similarityChecker.isSimilar(exp2.getRightOperand(), exp1.getRightOperand());
        if (rightOpSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check instance of expression similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the left operand</li>
     * <li>similarity of the right operand</li>
     * </ul>
     * 
     * @param exp1
     *            The instance of expression to compare with the compare element.
     * @return True/False if the instance of expressions are similar or not.
     */
    @Override
    public Boolean caseInstanceofExpression(InstanceofExpression exp1) {
        InstanceofExpression exp2 = (InstanceofExpression) compareElement;

        Boolean leftOpSimilarity = similarityChecker.isSimilar(exp2.getLeftOperand(), exp1.getLeftOperand());
        if (leftOpSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean rightOpSimilarity = similarityChecker.isSimilar(exp2.getRightOperand(), exp1.getRightOperand());
        if (rightOpSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check assignment expression similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the operator</li>
     * <li>similarity of the left hand side</li>
     * <li>similarity of the right hand side</li>
     * </ul>
     * 
     * @param ass1
     *            The assignment expression to compare with the compare element.
     * @return True/False if the assignment expressions are similar or not.
     */
    @Override
    public Boolean caseAssignment(Assignment ass1) {

        Assignment ass2 = (Assignment) compareElement;

        if (ass1.getOperator().compareTo(ass1.getOperator()) != 0) {
            return Boolean.FALSE;
        }

        Boolean leftSimilarity = similarityChecker.isSimilar(ass1.getLeftHandSide(), ass2.getLeftHandSide());
        if (leftSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean rightSimilarity = similarityChecker.isSimilar(ass1.getRightHandSide(), ass2.getRightHandSide());
        if (rightSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check parenthesized expression similarity.
     * 
     * Similarity is checked by
     * <ul>
     * <li>similarity of the inner expression</li>
     * </ul>
     * 
     * @param exp1
     *            The parenthesized expression to compare with the compare element.
     * @return True/False if the parenthesized expressions are similar or not.
     */
    @Override
    public Boolean caseParenthesizedExpression(ParenthesizedExpression exp1) {
        ParenthesizedExpression exp2 = (ParenthesizedExpression) compareElement;
        return similarityChecker.isSimilar(exp1.getExpression(), exp2.getExpression());
    }

    /**
     * Check number literal similarity. Similarity is checked by
     * <ul>
     * <li>similarity of token value</li>
     * </ul>
     * 
     * @param literal1
     *            The number literal to compare with the compare element.
     * @return True/False if the number literals are similar or not.
     */
    @Override
    public Boolean caseNumberLiteral(NumberLiteral literal1) {
        NumberLiteral literal2 = (NumberLiteral) compareElement;
        return literal1.getTokenValue().equals(literal2.getTokenValue());
    }

    /**
     * Check character literal similarity. Similarity is checked by
     * <ul>
     * <li>similarity of escaped value</li>
     * </ul>
     * 
     * @param literal1
     *            The character literal to compare with the compare element.
     * @return True/False if the character literals are similar or not.
     */
    @Override
    public Boolean caseCharacterLiteral(CharacterLiteral literal1) {
        CharacterLiteral literal2 = (CharacterLiteral) compareElement;
        return literal1.getEscapedValue().equals(literal2.getEscapedValue());
    }

    /**
     * Check string literal similarity. Similarity is checked by
     * <ul>
     * <li>similarity of escaped value</li>
     * </ul>
     * 
     * @param literal1
     *            The string literal to compare with the compare element.
     * @return True/False if the string literals are similar or not.
     */
    @Override
    public Boolean caseStringLiteral(StringLiteral literal1) {
        StringLiteral literal2 = (StringLiteral) compareElement;
        return literal1.getEscapedValue().equals(literal2.getEscapedValue());
    }

    /**
     * Check type literal similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the boolean value</li>
     * </ul>
     * 
     * @param literal1
     *            The type literal to compare with the compare element.
     * @return True/False if the type literals are similar or not.
     */
    @Override
    public Boolean caseTypeLiteral(TypeLiteral literal1) {
        TypeLiteral literal2 = (TypeLiteral) compareElement;
        Boolean typeSimilarity = similarityChecker.isSimilar(literal1.getType(), literal2.getType());
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Check boolean literal similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the boolean value</li>
     * </ul>
     * 
     * @param literal1
     *            The boolean literal to compare with the compare element.
     * @return True/False if the boolean literals are similar or not.
     */
    @Override
    public Boolean caseBooleanLiteral(BooleanLiteral literal1) {
        BooleanLiteral literal2 = (BooleanLiteral) compareElement;
        return literal1.isValue() == literal2.isValue();
    }

    /**
     * Check cast expression similarity. Similarity is checked by
     * <ul>
     * <li>similarity of the type to case to</li>
     * <li>similarity of the expression that is casted</li>
     * </ul>
     * 
     * @param exp1
     *            The cast expression to compare with the compare element.
     * @return True/False if the cast expressions are similar or not.
     */
    @Override
    public Boolean caseCastExpression(CastExpression exp1) {
        CastExpression exp2 = (CastExpression) compareElement;
        Type type1 = exp1.getType().getType();
        Type type2 = exp2.getType().getType();

        Boolean typeSimilarity = similarityChecker.isSimilar(type1, type2);
        if (typeSimilarity) {
            return typeSimilarity;
        }

        return similarityChecker.isSimilar(exp1.getExpression(), exp2.getExpression());

    }

    /**
     * Check field access similarity. Similarity is checked by
     * <ul>
     * <li>field variable name</li>
     * </ul>
     * 
     * @param access1
     *            The field access to compare with the compare element.
     * @return True/False if the field accesses are similar or not.
     */
    @Override
    public Boolean caseFieldAccess(FieldAccess access1) {
        FieldAccess access2 = (FieldAccess) compareElement;

        SingleVariableAccess var1 = access1.getField();
        SingleVariableAccess var2 = access2.getField();

        return similarityChecker.isSimilar(var1, var2);
    }

    /**
     * Check single variable access similarity. Similarity is checked by
     * <ul>
     * <li>accessed variable declaration</li>
     * </ul>
     * 
     * @param varAccess1
     *            The single variable access to compare with the compare element.
     * @return True/False if the single variable accesses are similar or not.
     */
    @Override
    public Boolean caseSingleVariableAccess(SingleVariableAccess varAccess1) {

        SingleVariableAccess varAccess2 = (SingleVariableAccess) compareElement;

        VariableDeclaration decl1 = varAccess1.getVariable();
        VariableDeclaration decl2 = varAccess2.getVariable();
        Boolean varDeclSimilarity = similarityChecker.isSimilar(decl1, decl2);
        if (varDeclSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check type access similarity.
     * 
     * Similarity is checked by
     * <ul>
     * <li>name of the accessed type</li>
     * </ul>
     * 
     * The qualifier attribute of the TypeAccess is ignored because this is more a syntactical
     * aspect. The import aspect of the comparison is the similarity of the accessed type.
     * 
     * @param typeAccess1
     *            The type access to compare with the compare element.
     * @return True/False if the type accesses are similar or not.
     */
    @Override
    public Boolean caseTypeAccess(TypeAccess typeAccess1) {

        TypeAccess typeAccess2 = (TypeAccess) compareElement;

        // check the similarity of the accessed type
        Type type1 = typeAccess1.getType();
        Type type2 = typeAccess2.getType();
        Boolean typeSimilarity = similarityChecker.isSimilar(type1, type2);
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check variable declaration fragment similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>variable name</li>
     * <li>declaration container</li>
     * </ul>
     * 
     * @param varDeclFrag
     *            The variable declaration fragment to compare with the compare element.
     * @return True/False if the variable declaration fragments are similar or not.
     */
    @Override
    public Boolean caseVariableDeclarationFragment(VariableDeclarationFragment varDeclFrag) {

        VariableDeclarationFragment varDeclFrag2 = (VariableDeclarationFragment) compareElement;

        // name check
        if (varDeclFrag.getName() != null && !varDeclFrag.getName().equals(varDeclFrag2.getName())) {
            return Boolean.FALSE;
        }

        // container check
        Boolean containerSimilarity = similarityChecker.isSimilar(varDeclFrag.getVariablesContainer(),
                varDeclFrag2.getVariablesContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        logger.warn("variable declaration fragment container not supported: " + varDeclFrag.getVariablesContainer());

        return Boolean.TRUE;
    }

    /**
     * Check variable declaration expression similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>number of contained variable declaration fragments</li>
     * <li>declaration container</li>
     * </ul>
     * 
     * @param varDeclExp1
     *            The variable declaration expression to compare with the compare element.
     * @return True/False if the variable declaration expressions are similar or not.
     */
    @Override
    public Boolean caseVariableDeclarationExpression(VariableDeclarationExpression varDeclExp1) {

        VariableDeclarationExpression varDeclExp2 = (VariableDeclarationExpression) compareElement;

        if (varDeclExp1.getFragments().size() != varDeclExp2.getFragments().size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < varDeclExp1.getFragments().size(); i++) {
            VariableDeclarationFragment varDeclFrag1 = varDeclExp1.getFragments().get(i);
            VariableDeclarationFragment varDeclFrag2 = varDeclExp2.getFragments().get(i);

            if (varDeclFrag1.getName() != null && !varDeclFrag1.getName().equals(varDeclFrag2.getName())) {
                return Boolean.FALSE;
            } else if (varDeclFrag1.getName() == null && varDeclFrag2.getName() != null) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check field declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>number of fragments</li>
     * <li>fragment names</li>
     * <li>declared type</li>
     * <li>Container (name space)</li>
     * </ul>
     * 
     * @param fDecl1
     *            The field declaration to compare with the compare element.
     * @return True/False if the field declaration are similar or not.
     */
    @Override
    public Boolean caseFieldDeclaration(FieldDeclaration fDecl1) {

        FieldDeclaration fDecl2 = (FieldDeclaration) compareElement;

        // fragment check
        EList<VariableDeclarationFragment> fragments1 = fDecl1.getFragments();
        EList<VariableDeclarationFragment> fragments2 = fDecl2.getFragments();
        if (fragments1.size() != fragments2.size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < fragments1.size(); i++) {
            VariableDeclarationFragment fragment1 = fragments1.get(i);
            VariableDeclarationFragment fragment2 = fragments2.get(i);
            if (!fragment1.getName().equals(fragment2.getName())) {
                return Boolean.FALSE;
            }
        }

        // type check similarity
        // first check type access availability to prevent null pointer
        // exceptions
        TypeAccess access1 = fDecl1.getType();
        TypeAccess access2 = fDecl2.getType();
        Boolean similarity = similarityChecker.isSimilar(access1, access2);
        if (similarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // container check
        Boolean containerSimilarity = similarityChecker.isSimilar(fDecl1.eContainer(), fDecl2.eContainer());
        if (containerSimilarity != null) {
            return containerSimilarity;
        }

        return Boolean.TRUE;
    }

    /**
     * Check abstract type declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>name</li>
     * <li>package</li>
     * <li>Container (in case of inner classes)</li>
     * </ul>
     * 
     * @param type1
     *            The type declaration to compare with the compare element.
     * @return True/False if the type declarations are similar or not.
     */
    @Override
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration type1) {
        AbstractTypeDeclaration type2 = (AbstractTypeDeclaration) compareElement;

        // if the types names are different then they do not match
        if (type1.getName() != null && !type1.getName().equals(type2.getName())) {
            return Boolean.FALSE;
        }

        Package package1 = type1.getPackage();
        Package package2 = type2.getPackage();

        Boolean packageSimilarity = similarityChecker.isSimilar(package1, package2);
        if (packageSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check anonymous class declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>instance creation similarity</li>
     * </ul>
     * 
     * @param aClassDecl1
     *            The class creation to compare with the compare element.
     * @return True/False if the anonymous class declarations are similar or not.
     */
    @Override
    public Boolean caseAnonymousClassDeclaration(AnonymousClassDeclaration aClassDecl1) {

        AnonymousClassDeclaration aClassDecl2 = (AnonymousClassDeclaration) compareElement;

        ClassInstanceCreation ci1 = aClassDecl1.getClassInstanceCreation();
        ClassInstanceCreation ci2 = aClassDecl2.getClassInstanceCreation();
        Boolean creationSimilarity = similarityChecker.isSimilar(ci1, ci2);
        if (creationSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check annotation similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>number of values</li>
     * <li>annotation type</li>
     * <li>values similarity</li>
     * </ul>
     * 
     * @param annotation1
     *            The annotation to compare with the compare element.
     * @return True/False if the annotations are similar or not.
     */
    @Override
    public Boolean caseAnnotation(Annotation annotation1) {

        Annotation annotation2 = (Annotation) compareElement;

        if (annotation1.getValues().size() != annotation2.getValues().size()) {
            return Boolean.FALSE;
        }

        Type type1 = annotation1.getType().getType();
        Type type2 = annotation2.getType().getType();

        Boolean typeSimilarity = similarityChecker.isSimilar(type1, type2);
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < annotation1.getValues().size(); i++) {
            AnnotationMemberValuePair valuePair1 = annotation1.getValues().get(i);
            AnnotationMemberValuePair valuePair2 = annotation2.getValues().get(i);

            Boolean valueSimilarity = similarityChecker.isSimilar(valuePair1, valuePair2);
            if (valueSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check annotation member value pair similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>member name</li>
     * <li>member value expression</li>
     * </ul>
     * 
     * @param valuePair1
     *            The annotation member value pair to compare with the compare element.
     * @return True/False if the annotation member value pairs are similar or not.
     */
    @Override
    public Boolean caseAnnotationMemberValuePair(AnnotationMemberValuePair valuePair1) {

        AnnotationMemberValuePair valuePair2 = (AnnotationMemberValuePair) compareElement;

        if (valuePair1.getName() != null && !valuePair1.getName().equals(valuePair2.getName())) {
            return Boolean.FALSE;
        }

        Boolean valueSimilarity = similarityChecker.isSimilar(valuePair1.getValue(), valuePair2.getValue());
        if (valueSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check parameterized type similarity.<br>
     * 
     * Similarity is checked by
     * <ul>
     * <li>name</li>
     * <li>length of the argument list</li>
     * <li>pairwise argument type similarity</li>
     * </ul>
     * 
     * @param type1
     *            The parameterized type to compare with the compare element.
     * @return True/False if the parameterized types are similar or not.
     */
    @Override
    public Boolean caseParameterizedType(ParameterizedType type1) {

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
                    return Boolean.FALSE;
                }
            } else if (!accessedType1.getName().equals(accessedType2.getName())) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check primitive type similarity.<br>
     * 
     * Similarity is checked by
     * <ul>
     * <li>name</li>
     * </ul>
     * 
     * @param type1
     *            The parameterized type to compare with the compare element.
     * @return True/False if the parameterized types are similar or not.
     */
    @Override
    public Boolean casePrimitiveType(PrimitiveType type1) {
        PrimitiveType type2 = (PrimitiveType) compareElement;
        if (type1.getName() != null && !type1.getName().equals(type2.getName())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Check type parameter similarity.<br>
     * 
     * Type parameters are always considered as similar. They might differ because of their type
     * restriction (e.g. List<T extends ...>) but this is ignored for now.
     * 
     * @param param1
     *            The type parameter to compare with the compare element.
     * @return True/False if the type parameter are similar or not.
     */
    @Override
    public Boolean caseTypeParameter(TypeParameter param1) {
        return Boolean.TRUE;
    }

    /**
     * Check if two array length accesses are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>similarity of the accessed arrays</li>
     * </ul>
     * 
     * @param access1
     *            The array length access to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseArrayLengthAccess(ArrayLengthAccess access1) {
        ArrayLengthAccess access2 = (ArrayLengthAccess) compareElement;

        return similarityChecker.isSimilar(access1.getArray(), access2.getArray());
    }

    /**
     * Check if two array accesses are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>accessed array</li>
     * <li>accessed index</li>
     * </ul>
     * 
     * @param access1
     *            The statement to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseArrayAccess(ArrayAccess access1) {

        ArrayAccess access2 = (ArrayAccess) compareElement;

        Boolean arraySimilarity = similarityChecker.isSimilar(access1.getArray(), access2.getArray());
        if (arraySimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean indexSimilarity = similarityChecker.isSimilar(access1.getIndex(), access2.getIndex());
        if (indexSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two array creation expressions are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>array type</li>
     * </ul>
     * 
     * @param ac1
     *            The array creation expression to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseArrayCreation(ArrayCreation ac1) {
        ArrayCreation ac2 = (ArrayCreation) compareElement;

        TypeAccess ta1 = ac1.getType();
        TypeAccess ta2 = ac2.getType();

        return similarityChecker.isSimilar(ta1, ta2);
    }

    /**
     * Check if two array initializers are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>number of initializing expressions</li>
     * <li>container similarity</li>
     * </ul>
     * 
     * @param initializer1
     *            The array initializer to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseArrayInitializer(ArrayInitializer initializer1) {

        ArrayInitializer initializer2 = (ArrayInitializer) compareElement;

        if (initializer1.getExpressions().size() != initializer2.getExpressions().size()) {
            return Boolean.FALSE;
        }
        Boolean containerSimilarity = similarityChecker.isSimilar(initializer1.eContainer(),
                compareElement.eContainer());
        return containerSimilarity;
    }

    /**
     * Check if two conditional expressions are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>condition expression</li>
     * <li>then expression</li>
     * <li>else expression</li>
     * </ul>
     * 
     * @param exp1
     *            The conditional expression to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean caseConditionalExpression(ConditionalExpression exp1) {
        ConditionalExpression exp2 = (ConditionalExpression) compareElement;

        Boolean expSimilarity = similarityChecker.isSimilar(exp1.getExpression(), exp2.getExpression());
        if (expSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean expElseSimilarity = similarityChecker.isSimilar(exp1.getElseExpression(), exp2.getElseExpression());
        if (expElseSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        Boolean expThenSimilarity = similarityChecker.isSimilar(exp1.getThenExpression(), exp2.getThenExpression());
        if (expThenSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * This expressions are always assumed to be true.
     * 
     * Note: The type referenced by "this" might differ. But this is not checked at the moment.
     * 
     * @param thisExp
     *            The this expression to compare with the compare element.
     * @return True as a this expression is always assumed to be the same.
     */
    @Override
    public Boolean caseThisExpression(ThisExpression thisExp) {
        return Boolean.TRUE;
    }

    /**
     * Check if two prefix expressions are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>the prefix operand</li>
     * <li>the prefixed expression</li>
     * </ul>
     * 
     * @param exp1
     *            The prefix expression to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean casePrefixExpression(PrefixExpression exp1) {

        PrefixExpression exp2 = (PrefixExpression) compareElement;

        if (exp1.getOperator().compareTo(exp2.getOperator()) != 0) {
            return Boolean.FALSE;
        }

        Expression prefixExp1 = exp1.getOperand();
        Expression prefixExp2 = exp1.getOperand();
        Boolean operandSimilarity = similarityChecker.isSimilar(prefixExp1, prefixExp2);
        if (operandSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two postfix expressions are similar.
     * 
     * Similarity is checked by:
     * <ul>
     * <li>the postfix operand</li>
     * <li>the postfixed expression</li>
     * </ul>
     * 
     * @param exp1
     *            The postfix expression to compare with the compare element.
     * @return True/False whether they are similar or not.
     */
    @Override
    public Boolean casePostfixExpression(PostfixExpression exp1) {

        PostfixExpression exp2 = (PostfixExpression) compareElement;

        if (exp1.getOperator().compareTo(exp2.getOperator()) != 0) {
            return Boolean.FALSE;
        }

        Expression prefixExp1 = exp1.getOperand();
        Expression prefixExp2 = exp1.getOperand();
        Boolean operandSimilarity = similarityChecker.isSimilar(prefixExp1, prefixExp2);
        if (operandSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two method invocations are similar.<br>
     * This checks
     * <ul>
     * <li>invoked method</li>
     * <li>invocation arguments</li>
     * <li>invocation argument types</li>
     * </ul>
     * 
     * @param methodInvocation
     *            the method invocation to check
     * @return the boolean
     */
    @Override
    public Boolean caseAbstractMethodInvocation(AbstractMethodInvocation methodInvocation) {

        AbstractMethodInvocation methodInvocation2 = (AbstractMethodInvocation) compareElement;

        // Invoked Method
        AbstractMethodDeclaration method1 = methodInvocation.getMethod();
        AbstractMethodDeclaration method2 = methodInvocation2.getMethod();
        Boolean methodSimilarity = similarityChecker.isSimilar(method1, method2);
        if (methodSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // Argument / Parameter similarity
        if (methodInvocation.getArguments().size() != methodInvocation2.getArguments().size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < methodInvocation.getArguments().size(); i++) {
            Expression exp1 = methodInvocation.getArguments().get(i);
            Expression exp2 = methodInvocation2.getArguments().get(i);

            Boolean argumentSimilarity = similarityChecker.isSimilar(exp1, exp2);
            if (argumentSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        // Type argument similarity
        if (methodInvocation.getTypeArguments().size() != methodInvocation2.getTypeArguments().size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < methodInvocation.getTypeArguments().size(); i++) {
            TypeAccess access1 = methodInvocation.getTypeArguments().get(i);
            TypeAccess access2 = methodInvocation2.getTypeArguments().get(i);

            Boolean accessSimilarity = similarityChecker.isSimilar(access1, access2);
            if (accessSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two method invocations are similar.<br>
     * This checks
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

        Boolean abstractSimilarity = caseAbstractMethodInvocation(methodInvocation);
        if (abstractSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        MethodInvocation methodInvocation2 = (MethodInvocation) compareElement;

        Expression exp1 = methodInvocation.getExpression();
        Expression exp2 = methodInvocation2.getExpression();
        Boolean expSimilarity = similarityChecker.isSimilar(exp1, exp2);
        if (expSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two method references are similar.<br>
     * This checks
     * <ul>
     * <li>the referenced method</li>
     * <li>the reference parameters</li>
     * </ul>
     * 
     * @param methodRef1
     *            the method reference to check
     * @return the boolean
     */
    @Override
    public Boolean caseMethodRef(MethodRef methodRef1) {
        MethodRef methodRef2 = (MethodRef) compareElement;

        AbstractMethodDeclaration method1 = methodRef1.getMethod();
        AbstractMethodDeclaration method2 = methodRef2.getMethod();
        Boolean methodSimilarity = similarityChecker.isSimilar(method1, method2);
        if (methodSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        EList<MethodRefParameter> method1Params = methodRef1.getParameters();
        EList<MethodRefParameter> method2Params = methodRef1.getParameters();
        if (method1Params.size() != method2Params.size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < method1Params.size(); i++) {
            MethodRefParameter param1 = method1Params.get(i);
            MethodRefParameter param2 = method2Params.get(i);
            Boolean paramSimilarity = similarityChecker.isSimilar(param1, param2);
            if (paramSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check if two method parameters are similar.<br>
     * This checks
     * <ul>
     * <li>the parameter name</li>
     * <li>the parameter type</li>
     * </ul>
     * 
     * @param param1
     *            the parameter to check
     * @return the boolean
     */
    @Override
    public Boolean caseMethodRefParameter(MethodRefParameter param1) {
        MethodRefParameter param2 = (MethodRefParameter) compareElement;

        String name1 = param1.getName();
        String name2 = param2.getName();

        if ((name1 == null && name2 != null) || (name1 != null && !name1.equals(name2))) {
            return Boolean.FALSE;
        }
        Boolean typeSimilarity = similarityChecker.isSimilar(param1.getType().getType(), param2.getType().getType());
        if (typeSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check super constructor invocation similarity.<br>
     * 
     * Similarity is checked by
     * <ul>
     * <li>length of the argument list</li>
     * <li>pairwise argument similarity</li>
     * </ul>
     * 
     * @param sci1
     *            The super constructor invocation to compare with the compare element.
     * @return True/False if the super constructor invocations are similar or not.
     */
    @Override
    public Boolean caseSuperConstructorInvocation(SuperConstructorInvocation sci1) {

        SuperConstructorInvocation sci2 = (SuperConstructorInvocation) compareElement;

        if (sci1.getArguments().size() != sci2.getArguments().size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < sci1.getArguments().size(); i++) {
            Boolean argSimilarity = similarityChecker.isSimilar(sci1.getArguments().get(i), sci2.getArguments().get(i));
            if (argSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check super method invocation similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>invoked method similarity</li>
     * <li>length of the argument list</li>
     * <li>pairwise argument similarity</li>
     * </ul>
     * 
     * @param smi1
     *            The super method invocation to compare with the compare element.
     * @return True/False if the super method invocations are similar or not.
     */
    @Override
    public Boolean caseSuperMethodInvocation(SuperMethodInvocation smi1) {

        SuperMethodInvocation smi2 = (SuperMethodInvocation) compareElement;

        AbstractMethodDeclaration md1 = smi1.getMethod();
        AbstractMethodDeclaration md2 = smi2.getMethod();
        Boolean methodSimilarity = similarityChecker.isSimilar(md1, md2);
        if (methodSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        if (smi1.getArguments().size() != smi2.getArguments().size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < smi1.getArguments().size(); i++) {
            Boolean argSimilarity = similarityChecker.isSimilar(smi1.getArguments().get(i), smi2.getArguments().get(i));
            if (argSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Check null literal similarity.<br>
     * 
     * Null literals are always assumed to be similar.
     * 
     * @param literal
     *            The null literal to compare with the compare element.
     * @return True As null always means null.
     */
    @Override
    public Boolean caseNullLiteral(NullLiteral literal) {
        return Boolean.TRUE;
    }

    /**
     * Check unresolved type declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>name</li>
     * </ul>
     * 
     * @param type1
     *            The unresolved type declaration to compare with the compare element.
     * @return True/False if the unresolved type declarations are similar or not.
     */
    @Override
    public Boolean caseUnresolvedTypeDeclaration(UnresolvedTypeDeclaration type1) {

        UnresolvedTypeDeclaration type2 = (UnresolvedTypeDeclaration) compareElement;

        if (type1.getName().equals(type2.getName())) {
            return Boolean.TRUE;
        }

        // TODO Add package similarity check

        return Boolean.FALSE;
    }

    @Override
    public Boolean caseUnresolvedItem(UnresolvedItem item1) {

        UnresolvedItem item2 = (UnresolvedItem) compareElement;

        if (item1.getName().equals(item2.getName())) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean caseUnresolvedItemAccess(UnresolvedItemAccess access1) {

        UnresolvedItemAccess access2 = (UnresolvedItemAccess) compareElement;

        Boolean itemSimilarity = similarityChecker.isSimilar(access1.getElement(), access2.getElement());
        if (itemSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        // TODO: Check if qualifier should be checked as well

        return Boolean.TRUE;
    };

    /**
     * Check block similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>container similarity</li>
     * </ul>
     * 
     * @param block
     *            The block to compare with the compare element.
     * @return True/False if the blocks are similar or not.
     */
    @Override
    public Boolean caseBlock(Block block) {

        EObject container1 = block.eContainer();
        EObject container2 = ((Block) compareElement).eContainer();
        Boolean containerSimilarity = similarityChecker.isSimilar(container1, container2);
        if (containerSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check try statement similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>container similarity</li>
     * </ul>
     * 
     * @param tryStatement
     *            The try statement to compare with the compare element.
     * @return True/False if the try statements are similar or not.
     */
    @Override
    public Boolean caseTryStatement(TryStatement tryStatement) {
        Boolean parentSimilarity = similarityChecker.isSimilar(tryStatement.eContainer(),
                ((TryStatement) compareElement).eContainer());
        return parentSimilarity;
    }

    @Override
    public Boolean caseModel(Model object) {
        return Boolean.TRUE;
    }

    /**
     * TagElement elements are always ignored and true is returned. {@inheritDoc}
     */
    @Override
    public Boolean caseTagElement(TagElement object) {
        return Boolean.TRUE;
    }

    /**
     * Ignore comment elements by returning always true. TODO: Check if null would be more
     * appropriate. {@inheritDoc}
     */
    @Override
    public Boolean caseComment(Comment object) {
        return Boolean.TRUE;
    }

    /**
     * The default case for not explicitly handled elements always returns null to identify the open
     * decision.
     * 
     * @param object
     *            The object to compare with the compare element.
     * @return null
     */
    @Override
    public Boolean defaultCase(EObject object) {
        logger.debug("Unsupported element type: " + object.getClass());
        return null;
    }

}