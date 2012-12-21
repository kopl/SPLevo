package org.splevo.vpm.refinement.simplelocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.splevo.vpm.refinement.AnalyzerConfigurationType;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Analyzer to identify variation points located in the same source entity to be merged together.
 * 
 * Note: The analysis does not consider interrupting source entities at the moment. For example, if
 * variation points are about multiple varying statements located in the same block and there is a
 * statement they depend on in between, the statements might not be allowed to be merged. This will
 * be added later on.
 * 
 * Block { varyingStatement v1; staticStatement s1; varyingStatement v2; }
 * 
 * In this case, we have to consider, if there is a dependency between s1, v1 and/or v2. This is
 * important how variation points can be introduced into this code to not break any dependencies
 * here.
 * 
 */
public class VPLocationAnalyzer implements VPMRefinementAnalyzer {

    /** The id of the configuration to choose between recommending merges or groupings. */
    public static final String CONFIG_ID_MERGE_GROUP = "org.splevo.vpm.refinement.simplelocation.groupmerge";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPLocationAnalyzer.class);

    /** The configurations of the analyzer. */
    private Map<String, Object> configurations = new HashMap<String, Object>();

    /**
     * Constructor initializing the analyzer default configuration.
     */
    public VPLocationAnalyzer() {
        this.configurations.put(CONFIG_ID_MERGE_GROUP, RefinementType.GROUPING);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.splevo.vpm.refinement.analyzer.VPMRefinementAnalysis#analyze(org.splevo.vpm.variability.VariationPointModel)
     */
    @Override
    public List<Refinement> analyze(VariationPointModel vpm) {

        // init the result list
        List<Refinement> refinements = new ArrayList<Refinement>();

        // init the grouping candidates
        Map<ASTNode, List<VariationPoint>> vpGroupCandidates = groupVPs(vpm);

        // check groups with more than one entry
        // and define an optional grouping for them
        for (ASTNode astNode : vpGroupCandidates.keySet()) {
            if (vpGroupCandidates.get(astNode).size() > 1) {
                Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
                refinement.setType(RefinementType.GROUPING);
                for (VariationPoint variationPoint : vpGroupCandidates.get(astNode)) {
                    refinement.getVariationPoints().add(variationPoint);
                }
                refinements.add(refinement);
            } else {
                logger.info("No group candidates found.");
            }
        }

        return refinements;
    }

    /**
     * Build a map grouping variation points located in the same software entity.
     * 
     * @param vpm
     *            The model to read the variation points from.
     * @return The prepared map.
     */
    private Map<ASTNode, List<VariationPoint>> groupVPs(VariationPointModel vpm) {
        Map<ASTNode, List<VariationPoint>> vpGroupCandidates = new HashMap<ASTNode, List<VariationPoint>>();
        for (VariationPointGroup variationPointGroup : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : variationPointGroup.getVariationPoints()) {
                ASTNode astNode = vp.getEnclosingSoftwareEntity();
                // TODO: Different ast nodes for the import declarations are created!
                if (!vpGroupCandidates.containsKey(astNode)) {
                    vpGroupCandidates.put(astNode, new ArrayList<VariationPoint>());
                }
                vpGroupCandidates.get(astNode).add(vp);
            }
        }
        return vpGroupCandidates;
    }

    @Override
    public String getName() {
        return "Basic Location VPM Analyzer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, AnalyzerConfigurationType> getAvailableConfigurations() {
        Map<String, AnalyzerConfigurationType> configs = new HashMap<String, AnalyzerConfigurationType>();
        configs.put(CONFIG_ID_MERGE_GROUP, AnalyzerConfigurationType.GROUP_MERGE);
        return configs;
    }

    @Override
    public Map<String, Object> getConfigurations() {
        return this.configurations;
    }

    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configs = new HashMap<String, String>();
        configs.put(CONFIG_ID_MERGE_GROUP, "Group or Merge");
        return configs;
    }

}
