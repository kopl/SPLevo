package org.splevo.jamopp.refactoring.java.ifelse.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.types.Void;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoringAllOR;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoringAllXOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Tests the {@link IfElseRefactoringAllOR} and {@link IfElseRefactoringAllXOR} classes. Checks
 * whether variables of same name and different types get handles correctly.
 */
public class IfElseRefactoringDifferentTypeVariablesTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 2 statements. The first statatement decalres a local int
     * variable.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but uses a string variable instead of an int.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseReusedStatementDifferentTypeOPTOR() throws Exception {
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllOR();

        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementDifferentType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTOR);

        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 2 statements. The first statatement decalres a local int
     * variable.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but uses a string variable instead of an int.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseReusedStatementDifferentTypeOPTXOR() throws Exception {
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementDifferentType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 3 statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but with a different initialization of the first statement.
     * 
     * <strong>Test Details</strong><br>
     * The refactored method body should contain the two conditions, each containing the method that
     * executes all statements of a variant.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testIfRefactoringOutputIsCorrectForReusedStatementDifferentTypeOPTOR() throws Exception {
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllOR();

        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementDifferentType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTOR);

        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;

        objectUnderTest.refactor(variationPoint);

        assertThat(leadingMethod.getStatements().size(), equalTo(2));
        assertThat(leadingMethod.getStatements().get(0), instanceOf(Condition.class));
        assertThat(leadingMethod.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) leadingMethod.getStatements().get(0);
        Condition cond2 = (Condition) leadingMethod.getStatements().get(1);

        assertThat(cond1.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getStatement(), instanceOf(Block.class));
        assertThat(cond1.getElseStatement(), nullValue());
        assertThat(cond2.getElseStatement(), nullValue());

        Block block1 = (Block) cond1.getStatement();
        Block block2 = (Block) cond2.getStatement();

        assertThat(block1.getStatements().size(), equalTo(1));
        assertThat(block2.getStatements().size(), equalTo(1));
        assertThat(block1.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(block2.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(((ExpressionStatement) block1.getStatements().get(0)).getExpression(), instanceOf(MethodCall.class));
        assertThat(((ExpressionStatement) block2.getStatements().get(0)).getExpression(), instanceOf(MethodCall.class));

        ClassMethod method1 = (ClassMethod) ((MethodCall) ((ExpressionStatement) block1.getStatements().get(0))
                .getExpression()).getTarget();
        ClassMethod method2 = (ClassMethod) ((MethodCall) ((ExpressionStatement) block2.getStatements().get(0))
                .getExpression()).getTarget();

        assertThat(method1.getTypeReference().getTarget(), instanceOf(Void.class));
        assertThat(method1.getTypeReference().getTarget(), instanceOf(Void.class));

        assertThat(method1.getStatements().size(), equalTo(2));
        assertThat(method2.getStatements().size(), equalTo(2));

        assertThat(method1.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        assertThat(method2.getStatements().get(0), instanceOf(LocalVariableStatement.class));

        assertThat(method1.getStatements().get(1), instanceOf(ExpressionStatement.class));
        assertThat(method2.getStatements().get(1), instanceOf(ExpressionStatement.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 3 statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but with a different initialization of the first statement.
     * 
     * <strong>Test Details</strong><br>
     * The refactored method body should contain one conditions with another condition as else, each
     * containing the method that executes all statements of a variant.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testIfRefactoringOutputIsCorrectForReusedStatementDifferentTypeOPTXOR() throws Exception {
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementDifferentType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;

        objectUnderTest.refactor(variationPoint);

        assertThat(leadingMethod.getStatements().size(), equalTo(1));
        assertThat(leadingMethod.getStatements().get(0), instanceOf(Condition.class));

        Condition cond1 = (Condition) leadingMethod.getStatements().get(0);
        assertThat(cond1.getElseStatement(), instanceOf(Condition.class));
        Condition cond2 = (Condition) cond1.getElseStatement();

        assertThat(cond1.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getElseStatement(), nullValue());

        Block block1 = (Block) cond1.getStatement();
        Block block2 = (Block) cond2.getStatement();

        assertThat(block1.getStatements().size(), equalTo(1));
        assertThat(block2.getStatements().size(), equalTo(1));
        assertThat(block1.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(block2.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(((ExpressionStatement) block1.getStatements().get(0)).getExpression(), instanceOf(MethodCall.class));
        assertThat(((ExpressionStatement) block2.getStatements().get(0)).getExpression(), instanceOf(MethodCall.class));

        ClassMethod method1 = (ClassMethod) ((MethodCall) ((ExpressionStatement) block1.getStatements().get(0))
                .getExpression()).getTarget();
        ClassMethod method2 = (ClassMethod) ((MethodCall) ((ExpressionStatement) block2.getStatements().get(0))
                .getExpression()).getTarget();

        assertThat(method1.getTypeReference().getTarget(), instanceOf(Void.class));
        assertThat(method1.getTypeReference().getTarget(), instanceOf(Void.class));

        assertThat(method1.getStatements().size(), equalTo(2));
        assertThat(method2.getStatements().size(), equalTo(2));

        assertThat(method1.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        assertThat(method2.getStatements().get(0), instanceOf(LocalVariableStatement.class));

        assertThat(method1.getStatements().get(1), instanceOf(ExpressionStatement.class));
        assertThat(method2.getStatements().get(1), instanceOf(ExpressionStatement.class));
    }
}
