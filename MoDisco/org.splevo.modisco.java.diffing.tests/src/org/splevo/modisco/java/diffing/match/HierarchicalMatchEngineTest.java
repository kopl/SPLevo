package org.splevo.modisco.java.diffing.match;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.junit.Before;
import org.junit.Test;
import org.splevo.diffing.JavaDiffer;
import org.splevo.modisco.java.diffing.AbstractDiffingTest;
import org.splevo.modisco.java.diffing.MoDiscoJavaEqualityHelper;
import org.splevo.modisco.java.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.modisco.java.diffing.match.HierarchicalMatchEngine.IgnoreStrategy;
import org.splevo.modisco.java.diffing.util.PackageIgnoreChecker;
import org.splevo.modisco.util.KDMUtil;
import org.splevo.modisco.util.SimilarityChecker;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * Test case for the hierarchical match engine. Ensures that the match stage is performed in the
 * expected way.
 */
public class HierarchicalMatchEngineTest extends AbstractDiffingTest {

    /** The package ignore checker to use. */
    private PackageIgnoreChecker packageIgnoreChecker;
    /** The equality helper to use. */
    private IEqualityHelper equalityHelper;

    /** The strategy to use to identify equality. */
    private EqualityStrategy equalityStrategy;

    /** The strategy to use to identify elements to ignore during matching. */
    private IgnoreStrategy ignoreStrategy;

    /**
     * Test the match engine to identify all matches in two equal models.
     */
    @Test
    public void testMatchEqualModel() throws Exception {
        File testDir1 = new File("testmodels/implementation/calculator/jscience");
        IComparisonScope scope = initScope(testDir1, testDir1);
        HierarchicalMatchEngine matchEngine = new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
                ignoreStrategy);
        Comparison comparison = matchEngine.match(scope, new BasicMonitor());

        assertThat("No matches created", comparison.getMatches().size(), is(not(0)));

        for (Match match : getAllMatches(comparison)) {
            if (match.getLeft() == null || match.getRight() == null) {
                fail("All elements should be matched: " + match);
            }
        }
    }

    /**
     * Test the match engine to identify all matches in two equal models.
     */
    @Test
    public void testOrderChanges() throws Exception {
        File testDir1 = new File("testmodels/implementation/orderchanges/a");
        File testDir2 = new File("testmodels/implementation/orderchanges/b");
        IComparisonScope scope = initScope(testDir1, testDir2);
        HierarchicalMatchEngine matchEngine = new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
                ignoreStrategy);
        Comparison comparison = matchEngine.match(scope, new BasicMonitor());

        assertThat("No matches created", comparison.getMatches().size(), is(not(0)));

        for (Match match : getAllMatches(comparison)) {
            EObject left = match.getLeft();
            EObject right = match.getRight();
            if (left == null || right == null) {
                fail("All elements should be matched: " + match);
            }

            if (left instanceof NamedElement) {
                String leftName = ((NamedElement) left).getName();
                String rightName = ((NamedElement) right).getName();
                assertThat("Elements with different names matched.", leftName, equalTo(rightName));
            }
        }
    }

    /**
     * Test the match engine to identify all matches in two equal models.
     */
    @Test
    public void testUnmatchedImport() throws Exception {
        File testDir1 = new File("testmodels/implementation/importDiffing/a");
        File testDir2 = new File("testmodels/implementation/importDiffing/b");
        IComparisonScope scope = initScope(testDir1, testDir2);
        HierarchicalMatchEngine matchEngine = new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
                ignoreStrategy);
        Comparison comparison = matchEngine.match(scope, new BasicMonitor());

        assertThat("No matches created", comparison.getMatches().size(), is(not(0)));

        int counterUnmatched = 0;

        for (Match match : getAllMatches(comparison)) {
            EObject left = match.getLeft();
            EObject right = match.getRight();
            if (left == null) {
                assertThat("ImportDeclaration expected as right", right, is(instanceOf(ImportDeclaration.class)));
                assertThat("Wrong import", ((ImportDeclaration) right).getImportedElement().getName(), is("BigInteger"));
                counterUnmatched++;
            }
            if (right == null) {
                assertThat("ImportDeclaration expected as left", left, is(instanceOf(ImportDeclaration.class)));
                assertThat("Wrong import", ((ImportDeclaration) left).getImportedElement().getName(), is("BigDecimal"));
                counterUnmatched++;
            }
        }
        assertThat("Wrong number of unmatched elements", counterUnmatched, is(equalTo(2)));
    }

    /**
     * Test the match engine to identify all matches in two equal models.
     */
    @Test
    public void testUnmatchedMethodDeclarations() throws Exception {
        File testDir1 = new File("testmodels/implementation/methoddeclaration/a");
        File testDir2 = new File("testmodels/implementation/methoddeclaration/b");
        IComparisonScope scope = initScope(testDir1, testDir2);
        HierarchicalMatchEngine matchEngine = new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
                ignoreStrategy);
        Comparison comparison = matchEngine.match(scope, new BasicMonitor());

        assertThat("No matches created", comparison.getMatches().size(), is(not(0)));

        int counterUnmatched = 0;

        for (Match match : getAllMatches(comparison)) {
            EObject left = match.getLeft();
            EObject right = match.getRight();
            if (left == null) {
                fail("Left model should always be filled");
            }
            if (right == null) {
                assertThat("MethodDeclaration expected as left", left, is(instanceOf(AbstractMethodDeclaration.class)));
                assertThat("Wrong method", ((AbstractMethodDeclaration) left).getName(), is("newMethod"));
                counterUnmatched++;
            }
        }
        assertThat("Wrong number of unmatched elements", counterUnmatched, is(equalTo(1)));
    }

    /**
     * Test the match engine to identify all matches in two equal models.
     */
    @Test
    public void testFieldDeclarations() throws Exception {
        File testDir1 = new File("testmodels/implementation/fielddeclaration.test/a");
        File testDir2 = new File("testmodels/implementation/fielddeclaration.test/b");
        IComparisonScope scope = initScope(testDir1, testDir2);
        HierarchicalMatchEngine matchEngine = new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
                ignoreStrategy);
        Comparison comparison = matchEngine.match(scope, new BasicMonitor());

        assertThat("No matches created", comparison.getMatches().size(), is(not(0)));

        int counterUnmatched = 0;

        for (Match match : getAllMatches(comparison)) {
            EObject left = match.getLeft();
            EObject right = match.getRight();
            if (left == null) {
                assertThat("Wrong right type", right,
                        is(anyOf(instanceOf(ArrayInitializer.class), instanceOf(FieldDeclaration.class))));
                if (right instanceof FieldDeclaration) {
                    assertThat("Wrong field", ((FieldDeclaration) right).getFragments().get(0).getName(),
                            is("removeField"));
                } else {
                    assertThat("Wrong array length", ((ArrayInitializer) right).getExpressions().size(), is(2));
                }
                counterUnmatched++;
            }
            if (right == null) {
                assertThat("Wrong left type", left,
                        is(anyOf(instanceOf(ArrayInitializer.class), instanceOf(FieldDeclaration.class))));
                if (left instanceof FieldDeclaration) {
                    assertThat("Wrong method", ((FieldDeclaration) left).getFragments().get(0).getName(),
                            is("newField"));
                } else {
                    assertThat("Wrong array length", ((ArrayInitializer) left).getExpressions().size(), is(3));
                }

                counterUnmatched++;
            }
        }
        assertThat("Wrong number of unmatched elements", counterUnmatched, is(equalTo(4)));
    }

    /**
     * Extract a list of all matches from a comparison model. This returns a plain list of the whole
     * match hierarchy.
     * 
     * @param comparison
     *            The comparison model to get the matches of.
     * @return The list of pure matches.
     */
    private List<Match> getAllMatches(Comparison comparison) {
        List<Match> matches = new ArrayList<Match>();
        Iterator<EObject> iterator = comparison.eAllContents();
        while (iterator.hasNext()) {
            EObject next = iterator.next();
            if (next instanceof Match) {
                matches.add((Match) next);
            }
        }
        return matches;
    }

    /**
     * Initialize the comparison scope for the base directories of the given files.
     * 
     * @param testDir1
     *            Base directory of the first model.
     * @param testDir2
     *            Base directory of the second model.
     * @return the initialized scope including the common package ignore checker
     */
    private IComparisonScope initScope(File testDir1, File testDir2) {
        ResourceSet resourceSetLeading = KDMUtil.loadResourceSetRecursively(testDir1.toURI());
        ResourceSet resourceSetIntegration = KDMUtil.loadResourceSetRecursively(testDir2.toURI());
        IComparisonScope scope = new JavaModelMatchScope(resourceSetIntegration, resourceSetLeading,
                packageIgnoreChecker);
        return scope;
    }

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUpMatchRequirements() {
        

        SimilarityChecker similarityChecker = new SimilarityChecker();

        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(
                DefaultMatchEngine.DEFAULT_EOBJECT_URI_CACHE_MAX_SIZE);
        final LoadingCache<EObject, org.eclipse.emf.common.util.URI> cache = EqualityHelper
                .createDefaultCache(cacheBuilder);
        equalityHelper = new MoDiscoJavaEqualityHelper(cache, similarityChecker);

        List<String> ignorePackages = (List<String>) diffOptions.get(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES);
        packageIgnoreChecker = new PackageIgnoreChecker(ignorePackages);

        equalityStrategy = new MoDiscoJavaEqualityStrategy(similarityChecker);
        ignoreStrategy = new MoDiscoJavaIgnoreStrategy(packageIgnoreChecker);

    }

}
