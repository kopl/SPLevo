package org.splevo.jamopp.refactoring.java.ifelse.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Tests for the {@link IfStaticConfigClassBlock} class.
 */
public class IfStaticConfigClassBlockTest {
    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests whether the canBeApplied method returns true for applicable variation points.
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createBlock();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createBlock();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassBlock refactoring = new IfStaticConfigClassBlock();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createBlock();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createBlock();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassBlock refactoring = new IfStaticConfigClassBlock();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvaliqdVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createBlock();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassBlock refactoring = new IfStaticConfigClassBlock();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the refactoring adds a missing class from the integration variant to the base
     * correctly.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseBlockAdd() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getBlockAddCase(VariabilityType.OPTXOR);
        IfStaticConfigClassBlock refactoring = new IfStaticConfigClassBlock();
        refactoring.refactor(vp, null);

        // location has one member
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(1));

        // member is a block
        assertThat(vpLocation.getMembers().get(0), instanceOf(Block.class));

        // assert block has the syso expression
        Block block = (Block) vpLocation.getMembers().get(0);
        assertThat(block.getStatements().size(), equalTo(1));
        assertThat(block.getStatements().get(0), instanceOf(ExpressionStatement.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
    
    /**
     * Tests whether the refactoring adds a missing class from the integration variant to the base
     * correctly.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseBlockMerge() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getBlockMergeCase(VariabilityType.OPTXOR);
        IfStaticConfigClassBlock refactoring = new IfStaticConfigClassBlock();
        refactoring.refactor(vp, null);
        
        // location has two members
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(2));
        
        // members are blocks
        assertThat(vpLocation.getMembers().get(0), instanceOf(Block.class));
        assertThat(vpLocation.getMembers().get(1), instanceOf(Block.class));
        
        // assert blocks have the syso expressions
        Block block1 = (Block) vpLocation.getMembers().get(0);
        Block block2 = (Block) vpLocation.getMembers().get(0);
        assertThat(block1.getStatements().size(), equalTo(1));
        assertThat(block2.getStatements().size(), equalTo(1));
        assertThat(block1.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(block2.getStatements().get(0), instanceOf(ExpressionStatement.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
