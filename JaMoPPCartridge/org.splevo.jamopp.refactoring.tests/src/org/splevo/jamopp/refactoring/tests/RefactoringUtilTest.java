package org.splevo.jamopp.refactoring.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests for the RefactoringUtil class.
 */
public class RefactoringUtilTest {

    /**
     * Initializes logging functionality.
     */
    @BeforeClass
    public static void init() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests the removeCommentableSoftwareElementReference method for a reference with a trailing
     * line break.
     * 
     * @throws Exception
     *             In case of an error during loading the test model.
     */
    @Test
    public void testRemoveCommentableSoftwareElementReferenceWithTrailingLineBreak() throws Exception {
        CompilationUnit cu = loadTestCompilationUnit("commentableSoftwareElements");
        Modifier m = cu.getClassifiers().get(0).getModifiers().get(0);

        CommentableSoftwareElement cse = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
        cse.setCompilationUnit(cu);
        cse.setType(CompilationUnit.class);
        cse.setId("UNIQUE_ID_10");

        String comment = getHiddenTokenText(m);
        assertThat(comment, equalTo(" /* SPLEVO_REF UNIQUE_ID_10 */\n"));

        RefactoringUtil.removeCommentableSoftwareElementReference(cse);

        assertThat(getHiddenTokenText(m), equalTo(""));
    }

    /**
     * Tests the removeCommentableSoftwareElementReference method for a reference with a leading and
     * trailing line break.
     * 
     * @throws Exception
     *             In case of an error during loading the test model.
     */
    @Test
    public void testRemoveCommentableSoftwareElementReferenceWithLeadingAndTrailingLineBreak() throws Exception {
        CompilationUnit cu = loadTestCompilationUnit("commentableSoftwareElements");
        Modifier m = cu.getClassifiers().get(0).getMethods().get(0).getModifiers().get(0);

        CommentableSoftwareElement cse = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
        cse.setCompilationUnit(cu);
        cse.setType(Method.class);
        cse.setId("UNIQUE_ID_50");

        String comment = getHiddenTokenText(m);
        assertThat(
                comment,
                equalTo("\n    \n    /* SPLEVO_REF UNIQUE_ID_50 */\n    /**\n     * This method does nothing. \n     */\n    "));

        RefactoringUtil.removeCommentableSoftwareElementReference(cse);

        assertThat(getHiddenTokenText(m), equalTo("\n    \n    /**\n     * This method does nothing. \n     */\n    "));
    }

    /**
     * Tests the removeCommentableSoftwareElementReference method for a reference with a leading
     * line break.
     * 
     * @throws Exception
     *             In case of an error during loading the test model.
     */
    @Test
    public void testRemoveCommentableSoftwareElementReferenceWithLeadingLineBreak() throws Exception {
        CompilationUnit cu = loadTestCompilationUnit("commentableSoftwareElements");
        Modifier m = cu.getClassifiers().get(0).getMethods().get(1).getModifiers().get(0);

        CommentableSoftwareElement cse = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
        cse.setCompilationUnit(cu);
        cse.setType(Method.class);
        cse.setId("UNIQUE_ID_100");

        String comment = getHiddenTokenText(m);
        assertThat(comment, equalTo("\n    \n    /* SPLEVO_REF UNIQUE_ID_100 */"));

        RefactoringUtil.removeCommentableSoftwareElementReference(cse);

        assertThat(getHiddenTokenText(m), equalTo("\n    \n    "));
    }

    private static String getHiddenTokenText(Commentable commentable) {
        return getHiddenTokenText(commentable, true);
    }

    private static String getHiddenTokenText(Commentable commentable, boolean normalizeLineEndings) {
        LayoutInformation layoutInformation = Iterables.getFirst(commentable.getLayoutInformations(), null);
        String hiddenTokenText = layoutInformation.getHiddenTokenText();
        if (!normalizeLineEndings) {
            return hiddenTokenText;
        }
        return hiddenTokenText.replaceAll("\\r\\n", "\n");
    }

    private static CompilationUnit loadTestCompilationUnit(String folderName) throws SoftwareModelExtractionException {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        ResourceSet rs = extractor.extractSoftwareModel(Lists.newArrayList("testcode/" + folderName),
                new NullProgressMonitor(), null, true);
        return (CompilationUnit) rs.getResources().get(0).getContents().get(0);
    }

}
