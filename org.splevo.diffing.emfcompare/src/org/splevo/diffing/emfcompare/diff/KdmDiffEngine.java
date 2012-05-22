package org.splevo.diffing.emfcompare.diff;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.UpdateReference;
import org.eclipse.emf.compare.match.internal.statistic.NameSimilarity;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.modisco.kdm.source.extension.ui.utils.Utils;

public class KdmDiffEngine extends GenericDiffEngine {

    @Override
    public DiffModel doDiff(MatchModel match, boolean threeWay) {
        System.out.println("starting diff 1");
        DiffModel diff = super.doDiff(match, threeWay);
        traverseDiffModel(diff);
        return diff;
    }

    @Override
    public DiffModel doDiff(MatchModel match, boolean threeWay, IMatchManager manager) {
        System.out.println("starting diff 2");
        DiffModel diff = super.doDiff(match, threeWay, manager);
        traverseDiffModel(diff);
        return diff;
    }

    @Override
    public DiffModel doDiffResourceSet(MatchModel match, boolean threeWay, CrossReferencer crossReferencer) {
        DiffModel diff = super.doDiffResourceSet(match, threeWay, crossReferencer);
        traverseDiffModel(diff);
        return diff;
    }

    @Override
    public DiffModel doDiffResourceSet(MatchModel match, boolean threeWay, IMatchManager manager) {
        DiffModel diff = super.doDiffResourceSet(match, threeWay, manager);
        traverseDiffModel(diff);
        return diff;
    }

    private void traverseDiffModel(DiffModel dModel) {
        System.out.println("traversing diff model " + dModel.getClass());
        /*for (DiffElement difference : dModel.getDifferences()) {
            System.out.println("found difference " + difference.toString() + " with class " + difference.getClass());
            for (DiffElement subElem : difference.getSubDiffElements()) {
                System.out.println("  sub-element " + subElem.toString());
            }
        }*/
        for (DiffElement ownedElem : dModel.getOwnedElements()) {
            /*System.out.println("owned element " + ownedElem.toString());
            for (DiffElement subElemtn : ownedElem.getSubDiffElements()) {
                System.out.println("   sub-elment " + subElemtn.toString());
            }*/
            traverseDiffElement(ownedElem, 0);
        }

    }

    private void traverseDiffElement(DiffElement dElem, int indent) {
        if (dElem instanceof DiffGroup || dElem instanceof ConflictingDiffElement) {
            //traverse next level

            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }
            System.out.println("entering inner node " + dElem.toString() + " of class " + dElem.getClass());
            for (DiffElement subElem : dElem.getSubDiffElements()) {
                traverseDiffElement(subElem, indent + 1);
            }
        } else {
            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }
            System.out.println("leaf change " + dElem.toString() + " of class " + dElem.getClass());
            if (dElem instanceof UpdateReference) {
                UpdateReference upRef = (UpdateReference) dElem;
                try {
                    Object leftObj = upRef.getLeftElement().eGet(upRef.getReference());
                    Object rightObj = upRef.getRightElement().eGet(upRef.getReference());
                    System.out.println("updated reference from " + upRef.getReference().getName() + " left " + leftObj + " right " + rightObj + " name " + NameSimilarity.findName(upRef.getLeftElement()));
                    System.out.println("pure left element " + upRef.getLeftElement());
                    if (leftObj instanceof TypeAccess && rightObj instanceof TypeAccess)  {
                        TypeAccess leftTypeAccess = (TypeAccess) leftObj;
                        Type leftType = leftTypeAccess.getType();
                        TypeAccess rightTypeAccess = (TypeAccess) rightObj;
                        Type rightType = rightTypeAccess.getType();
                        System.out.println("!!! refactoring from class " + leftType.getName() + " to " + rightType.getName());
                        if (upRef.getLeftElement() instanceof JavaNodeSourceRegion && upRef.getRightElement() instanceof JavaNodeSourceRegion) {
                            JavaNodeSourceRegion leftRegion = (JavaNodeSourceRegion) upRef.getLeftElement();
                            JavaNodeSourceRegion rightRegion = (JavaNodeSourceRegion) upRef.getRightElement();
                            //System.out.println("original source left " + leftRegion.toString());
                            //System.out.println("original source right " + rightRegion.toString());
                            System.out.println("left source");
                            printRegion(leftRegion);
                            System.out.println("right source");
                            printRegion(rightRegion);
                        }
                    }
                } catch (FactoryException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void printRegion(SourceRegion region) {
        SourceFile source = region.getFile();
        IFile sourceFile = Utils.getIFileFromSourceRegion(region);
        try {
            BufferedReader sourceReader = new BufferedReader(new InputStreamReader(sourceFile.getContents()));
            int lineNo = 0;
            for (lineNo = 0; lineNo < region.getStartLine(); lineNo++) {
                sourceReader.readLine();
            }
            while (lineNo <= region.getEndLine()) {
                String line = sourceReader.readLine();
                System.out.println(line);
                lineNo++;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
