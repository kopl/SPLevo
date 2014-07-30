package org.splevo.jamopp.refactoring.java.ifelse.tests.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Utility class for JaMoPP refactoring tests.
 */
public final class RefactoringTestUtil {

    private static final String PATH_TESTCODE_FOLDER = "testcode/";

    private RefactoringTestUtil() {
    }

    /**
     * Generates a variation point according to the Statement_Default test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatemententDefaultCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_Default");

        assert (vpm.getVariationPointGroups().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().size() == 1);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Constructor_ExistingOneParam test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConstructorExistingOneParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Constructor_ExistingOneParam");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Method_AddSameParam test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getMethodAddSameParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Method_AddSameParam");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Method_AddDifferentParam test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getMethodAddDifferentParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Method_AddDifferentParam");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Constructor_AddTwoParam test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConstructorAddTwoParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Constructor_AddTwoParam");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Import_TwoDistinct test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getImportTwoDistinctCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Import_TwoDistinct");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Import_CommonMultiple test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getImportCommonMultipleCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Import_CommonMultiple");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_LocalVariableDiffTypes test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementLocalVarDiffTypesCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_LocalVariableDiffTypes");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0), vpm.getVariationPointGroups().get(2)
                        .getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_LocalVariableEqualType test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementLocalVarEqualTypeCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_LocalVariableEqualType");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedCatch test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedCatchCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedCatch");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedCondition test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedConditionCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedCondition");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedFor test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedForCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedFor");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedSwitchCase test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedSwitchCaseCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedSwitchCase");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedTry test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedTryCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedTry");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_NestedWhile test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementNestedWhileCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedWhile");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_OneAdd test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementOneAddCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_OneAdd");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Statement_OneEither test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatementOneEitherCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_OneEither");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Field_Default test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getFieldAddCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Field_Add");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Field_DifferentInitialValues test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getFieldDifferentInitialValuesCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Field_DifferentInitialValues");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Condition_AddCond test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConditionAddCondCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Condition_AddCond");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Condition_DifferentElseStatement test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConditionDifferentElseStatementCase(VariabilityType variabilityType)
            throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Condition_DifferentElseStatement");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Class_Merge test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getClassMergeCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Class_Merge");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Interface_Merge test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getInterfaceMergeCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Interface_Merge");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Enumeration_Add test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getEnumerationAddCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Enumeration_Add");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Enumeration_AddEnumConstant test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getEnumerationAddEnumConstantCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Enumeration_AddEnumConstant");
        assertVariationPointStructure(vpm);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generates a variation point according to the Block_Add test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getBlockAddCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Block_Add");
        assertVariationPointStructure(vpm);
        
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);
        
        return variationPoint;
    }

    /**
     * Generates a variation point according to the Block_Merge test case.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getBlockMergeCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Block_Merge");
        assertVariationPointStructure(vpm);
        
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);
        
        return variationPoint;
    }

    /**
     * Generates a variation point mock with a given variability type, extensibility, binding time,
     * location and two variants, each with one element.
     * 
     * @param varType
     *            The {@link VariabilityType}.
     * @param extensible
     *            The {@link Extensible}.
     * @param bt
     *            The {@link BindingTime}.
     * @param location
     *            The variation point location.
     * @param implEl1
     *            The implementing element of the first variant.
     * @param implEl2
     *            The implementing element of the second variant.
     * @return The {@link VariationPoint}.
     */
    public static VariationPoint getSimpleVPMock(VariabilityType varType, Extensible extensible, BindingTime bt,
            Commentable location, Commentable implEl1, Commentable implEl2) {
        VariationPoint vpMock = mock(VariationPoint.class);

        JaMoPPSoftwareElement locationElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        locationElement.setJamoppElement(location);

        JaMoPPSoftwareElement implElement1 = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        implElement1.setJamoppElement(implEl1);
        JaMoPPSoftwareElement implElement2 = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        implElement2.setJamoppElement(implEl2);

        Variant variant1 = variabilityFactory.eINSTANCE.createVariant();
        variant1.getImplementingElements().add(implElement1);
        Variant variant2 = variabilityFactory.eINSTANCE.createVariant();
        variant2.getImplementingElements().add(implElement2);
        EList<Variant> variants = new BasicEList<Variant>();
        variants.add(variant1);
        variants.add(variant2);

        when(vpMock.getVariabilityType()).thenReturn(varType);
        when(vpMock.getExtensibility()).thenReturn(extensible);
        when(vpMock.getBindingTime()).thenReturn(bt);
        when(vpMock.getLocation()).thenReturn(locationElement);
        when(vpMock.getVariants()).thenReturn(variants);
        return vpMock;
    }

    /**
     * Generates a mock for a field with test values.
     * 
     * @return The {@link Field} mock.
     */
    public static Field getFieldMock() {
        Field field = mock(Field.class);

        Class createdClass = ClassifiersFactory.eINSTANCE.createClass();
        createdClass.setName("MyClass");
        ClassifierReference classRef = TypesFactory.eINSTANCE.createClassifierReference();
        classRef.setTarget(createdClass);

        when(field.getTypeReference()).thenReturn(classRef).thenReturn(classRef);
        when(field.getName()).thenReturn("a").thenReturn("b");

        return field;
    }

    /**
     * Asserts the structure of the System.out.plintln() expressions used in the tests and returns
     * the value of the argument in the println method.
     * 
     * @param statement
     *            The System.out.plintln() {@link ExpressionStatement}.
     * @return The value as {@link BigInteger}.
     */
    public static BigInteger getValueOfSysoExpression(ExpressionStatement statement) {
        assertThat(statement, instanceOf(ExpressionStatement.class));
        Reference out = ((IdentifierReference) ((ExpressionStatement) statement).getExpression()).getNext();
        assertThat(out, notNullValue());
        Reference println = out.getNext();
        assertThat(println, notNullValue());
        assertThat(println, instanceOf(MethodCall.class));
        assertThat(((MethodCall) println).getArguments().size(), equalTo(1));
        assertThat(((MethodCall) println).getArguments().get(0), instanceOf(DecimalIntegerLiteral.class));

        BigInteger value = ((DecimalIntegerLiteral) ((MethodCall) println).getArguments().get(0)).getDecimalValue();
        return value;
    }

    private static void assertVariationPointStructure(VariationPointModel vpm) {
        assert (vpm.getVariationPointGroups().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().get(0).getVariants().size() == 2);
    }

    private static VariationPointModel initializeVariationPointModel(String folderName) throws Exception {
        String leadingPath = PATH_TESTCODE_FOLDER + folderName + "/leading/";
        String integrationPath = PATH_TESTCODE_FOLDER + folderName + "/integration/";

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(leadingPath).getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(integrationPath).getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    private static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

    private static void performRefinement(VariationPointModel vpm, RefinementType refinementType,
            VariationPoint... variationPoints) {
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(refinementType);

        for (VariationPoint variationPoint : variationPoints) {
            refinement.getVariationPoints().add(variationPoint);
        }

        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
    }

    private static void setUpVariationPoint(VariationPoint variationPoint, VariabilityType variabilityType) {
        variationPoint.setBindingTime(BindingTime.COMPILE_TIME);
        variationPoint.setExtensibility(Extensible.NO);
        variationPoint.setVariabilityType(variabilityType);
    }
}
