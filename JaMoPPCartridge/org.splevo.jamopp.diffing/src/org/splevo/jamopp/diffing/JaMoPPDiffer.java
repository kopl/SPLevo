package org.splevo.jamopp.diffing;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.diff.IDiffEngine;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.postprocessor.BasicPostProcessorDescriptorImpl;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.compare.postprocessor.PostProcessorDescriptorRegistryImpl;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.commons.layout.LayoutPackage;
import org.emftext.language.java.JavaPackage;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.diffing.JavaDiffer;
import org.splevo.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.diffing.match.HierarchicalMatchEngine.IgnoreStrategy;
import org.splevo.diffing.match.HierarchicalMatchEngineFactory;
import org.splevo.diffing.match.HierarchicalStrategyResourceMatcher;
import org.splevo.jamopp.diffing.diff.JaMoPPFeatureFilter;
import org.splevo.jamopp.diffing.diff.JaMoPPDiffBuilder;
import org.splevo.jamopp.diffing.match.JaMoPPEqualityHelper;
import org.splevo.jamopp.diffing.match.JaMoPPEqualityStrategy;
import org.splevo.jamopp.diffing.match.JaMoPPIgnoreStrategy;
import org.splevo.jamopp.diffing.postprocessor.JaMoPPPostProcessor;
import org.splevo.jamopp.diffing.scope.JavaModelMatchScope;
import org.splevo.jamopp.diffing.scope.PackageIgnoreChecker;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * Differ for JaMoPP software models.
 * 
 * @author Benjamin Klatt
 * 
 */
public class JaMoPPDiffer extends JavaDiffer {

    private static final String LABEL = "JaMoPP Java Differ";
    private static final String ID = "org.splevo.jamopp.differ";
    private Logger logger = Logger.getLogger(JaMoPPDiffer.class);

    /**
     * Diffing identifies the MoDisco Java2KDM source model in the directory and performs the
     * modisco specific diffing. {@inheritDoc}
     * 
     * @return null if no supported source models available.
     * @throws DiffingNotSupportedException
     *             Thrown if no Java2Kdm models provided to compare.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Comparison doDiff(java.net.URI leadingModelDirectory, java.net.URI integrationModelDirectory,
            Map<String, Object> diffingOptions) throws DiffingException, DiffingNotSupportedException {

        this.logger.info("Load source models");
        ResourceSet resourceSetLeading = loadResourceSetRecursively(leadingModelDirectory);
        ResourceSet resourceSetIntegration = loadResourceSetRecursively(integrationModelDirectory);

        logger.debug("Diffing: Configure EMF Compare");
        final List<String> ignorePackages = (List<String>) diffingOptions.get(OPTION_JAVA_IGNORE_PACKAGES);
        PackageIgnoreChecker packageIgnoreChecker = new PackageIgnoreChecker(ignorePackages);

        SimilarityChecker similarityChecker = new SimilarityChecker();

        final LoadingCache<EObject, org.eclipse.emf.common.util.URI> cache = initEqualityCache();
        IEqualityHelper equalityHelper = new JaMoPPEqualityHelper(cache, similarityChecker);
        IMatchEngine.Factory.Registry matchEngineRegistry = initMatchEngine(equalityHelper, packageIgnoreChecker,
                similarityChecker);
        IPostProcessor.Descriptor.Registry<?> postProcessorRegistry = initPostProcessors(packageIgnoreChecker);
        IDiffEngine diffEngine = initDiffEngine(ignorePackages);

        EMFCompare comparator = initComparator(matchEngineRegistry, postProcessorRegistry, diffEngine);

        // Compare the two models
        // In comparison, the left side is always the changed one.
        // push in the integration model first
        IComparisonScope scope = new JavaModelMatchScope(resourceSetIntegration, resourceSetLeading,
                packageIgnoreChecker);

        logger.debug("Diffing: Perform comparison");
        Comparison comparisonModel = comparator.compare(scope);

        return comparisonModel;

    }

    /**
     * Initialize a cache to be used by the equality helper.
     * 
     * @return The ready to use cache.
     */
    private LoadingCache<EObject, org.eclipse.emf.common.util.URI> initEqualityCache() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(
                DefaultMatchEngine.DEFAULT_EOBJECT_URI_CACHE_MAX_SIZE);
        final LoadingCache<EObject, org.eclipse.emf.common.util.URI> cache = EqualityHelper
                .createDefaultCache(cacheBuilder);
        return cache;
    }

    /**
     * Initialize the post processors and build an according registry.
     * 
     * @param packageIgnoreChecker
     *            The checker if an element belongs to an ignored package.
     * @return The prepared registry with references to the post processors.
     */
    private IPostProcessor.Descriptor.Registry<String> initPostProcessors(PackageIgnoreChecker packageIgnoreChecker) {
        IPostProcessor customPostProcessor = new JaMoPPPostProcessor();
        Pattern any = Pattern.compile(".*");
        IPostProcessor.Descriptor descriptor = new BasicPostProcessorDescriptorImpl(customPostProcessor, any, any);
        IPostProcessor.Descriptor.Registry<String> postProcessorRegistry = new PostProcessorDescriptorRegistryImpl<String>();
        postProcessorRegistry.put(JaMoPPPostProcessor.class.getName(), descriptor);
        return postProcessorRegistry;
    }

    /**
     * Init the comparator instance to be used for comparison.
     * 
     * @param matchEngineRegistry
     *            The registry containing the match engines to be used.
     * @param postProcessorRegistry
     *            Registry for post processors to be executed.
     * @param diffEngine
     *            The diff engine to run.
     * @return The prepared comparator instance.
     */
    private EMFCompare initComparator(IMatchEngine.Factory.Registry matchEngineRegistry,
            IPostProcessor.Descriptor.Registry<?> postProcessorRegistry, IDiffEngine diffEngine) {
        EMFCompare.Builder builder = EMFCompare.builder();
        builder.setDiffEngine(diffEngine);
        builder.setMatchEngineFactoryRegistry(matchEngineRegistry);
        builder.setPostProcessorRegistry(postProcessorRegistry);
        EMFCompare comparator = builder.build();
        return comparator;
    }

    /**
     * Initialize the diff engine with the diff processor and feature filters to be used.
     * 
     * @param ignorePackages
     *            The java packages to ignore.
     * @return The ready-to-use diff engine.
     */
    private IDiffEngine initDiffEngine(final List<String> ignorePackages) {
        IDiffProcessor diffProcessor = new JaMoPPDiffBuilder();
        IDiffEngine diffEngine = new DefaultDiffEngine(diffProcessor) {
            @Override
            protected FeatureFilter createFeatureFilter() {
                return new JaMoPPFeatureFilter(ignorePackages);
            }
        };
        return diffEngine;
    }

    /**
     * Initialize and configure the match engines to be used.
     * 
     * @param equalityHelper
     *            The equality helper to be used during the diff process.
     * @param packageIgnoreChecker
     *            The package ignore checker to use in the match engine.
     * @param similarityChecker
     *            The similarity checker to use in the match engine.
     * 
     * @return The registry containing all prepared match engines
     */
    private IMatchEngine.Factory.Registry initMatchEngine(IEqualityHelper equalityHelper,
            PackageIgnoreChecker packageIgnoreChecker, SimilarityChecker similarityChecker) {

        EqualityStrategy equalityStrategy = new JaMoPPEqualityStrategy(similarityChecker);
        IgnoreStrategy ignoreStrategy = new JaMoPPIgnoreStrategy(packageIgnoreChecker);

        IMatchEngine.Factory matchEngineFactory = new HierarchicalMatchEngineFactory(equalityHelper, equalityStrategy,
                ignoreStrategy, new HierarchicalStrategyResourceMatcher());
        matchEngineFactory.setRanking(20);

        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(matchEngineFactory);

        return matchEngineRegistry;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public void init() {
		JavaPackage.eINSTANCE.eClass();
		LayoutPackage.eINSTANCE.eClass();
    }

    /**
     * Recursively load all files within a directory into a resource set.
     * 
     * @param baseDirectory
     *            The root directory to search files in.
     * @return The prepared resource set.
     */
    public static ResourceSet loadResourceSetRecursively(java.net.URI baseDirectory) {

        // load the required meta class packages
		JavaPackage.eINSTANCE.eClass();
		LayoutPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

        Collection<File> files = FileUtils.listFiles(new File(baseDirectory), new String[] { "xmi" }, true);
        for (File file : files) {
            URI uri = URI.createFileURI(file.getAbsolutePath());
            resourceSet.getResource(uri, true);
        }

        return resourceSet;
    }

}
