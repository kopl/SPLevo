package org.splevo.modisco.java.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.modisco.java.vpm.software.softwareFactory;
import org.splevo.vpm.analyzer.programstructure.ProgramStructureProvider;
import org.splevo.vpm.software.SoftwareElement;

/**
 * Program structure provider specific to the MoDisco Java technology.
 */
public class MoDiscoJavaProgramStructureProvider implements ProgramStructureProvider {

    /** The AST node traverser for building the AST node index. */
    private ASTNodeChildrenSelector astNodeTraverser = new ASTNodeChildrenSelector();

    /** Switch to get the referrer for an ast node. */
    private ReferringASTNodeSwitch referringASTNodeSwitch = new ReferringASTNodeSwitch();

    /**
     * A cache to preventing to create multiple software elements for the same ASTNode.
     */
    private Map<ASTNode, MoDiscoJavaSoftwareElement> softwareElementCache = new LinkedHashMap<ASTNode, MoDiscoJavaSoftwareElement>();

    /**
     * Get a list of MoDiscoJavaSoftwareElements that link to sub elements of the provided software
     * element. Only if a MoDiscoSoftwareElement is provided, sub elements are discovered by the
     * ASTNode linked by the provided software element. {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
	public List<SoftwareElement> getRelevantSubElements(SoftwareElement softwareElement) {

        List<SoftwareElement> subElements = new ArrayList<SoftwareElement>();

        if (softwareElement instanceof MoDiscoJavaSoftwareElement) {
            MoDiscoJavaSoftwareElement parentElement = (MoDiscoJavaSoftwareElement) softwareElement;
            List<ASTNode> subASTNodes = astNodeTraverser.doSwitch(parentElement.getAstNode());

            for (ASTNode astNode : subASTNodes) {
                MoDiscoJavaSoftwareElement element = buildSoftwareElement(parentElement, astNode);
                subElements.add(element);
            }

        }

        return subElements;
    }

    @Override
    @SuppressWarnings("restriction")
	public List<SoftwareElement> getReferringSoftwareElements(SoftwareElement referredSoftwareElement) {
        List<SoftwareElement> referringElements = new ArrayList<SoftwareElement>();

        if (referredSoftwareElement instanceof MoDiscoJavaSoftwareElement) {
            MoDiscoJavaSoftwareElement moDiscoElement = (MoDiscoJavaSoftwareElement) referredSoftwareElement;
            List<ASTNode> referringASTNodes = referringASTNodeSwitch.doSwitch(moDiscoElement.getAstNode());
            for (ASTNode astNode : referringASTNodes) {
                MoDiscoJavaSoftwareElement element = buildSoftwareElement(moDiscoElement, astNode);
                referringElements.add(element);
            }
        }
        return referringElements;
    }

    /**
     * Build a new MoDisco software element for an ASTNode.
     *
     * @param parentElement
     *            The template for common settings.
     * @param astNode
     *            The ASTNode to link.
     * @return The prepared element.
     */
    private MoDiscoJavaSoftwareElement buildSoftwareElement(MoDiscoJavaSoftwareElement parentElement, ASTNode astNode) {

        if (!softwareElementCache.containsKey(astNode)) {
            MoDiscoJavaSoftwareElement element = softwareFactory.eINSTANCE.createMoDiscoJavaSoftwareElement();
            element.setAstNode(astNode);
            element.setJavaApplicationModel(parentElement.getJavaApplicationModel());
            softwareElementCache.put(astNode, element);
        }

        return softwareElementCache.get(astNode);
    }
}
