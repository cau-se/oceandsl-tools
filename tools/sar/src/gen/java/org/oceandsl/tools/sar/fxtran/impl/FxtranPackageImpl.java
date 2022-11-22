/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.oceandsl.tools.sar.fxtran.FxtranFactory;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FxtranPackageImpl extends EPackageImpl implements FxtranPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String packageFilename = "fxtran.ecore";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass actionStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass acValueLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass acValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass allocateStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass argNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass argSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass argTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass arrayConstructorETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass arrayRTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass arraySpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass aStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass attributeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass callStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseSelectorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseValueRangeLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseValueRangeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass caseValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass charSelectorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass charSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass closeSpecSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass closeSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass closeStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentDeclStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentRTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass conditionETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass connectSpecSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass connectSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass cycleStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass deallocateStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass derivedTSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass doStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass doVTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dummyArgLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass e1TypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass e2TypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elementLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elementTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elseIfStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endDoStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enDeclLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enDeclTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endForallStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endFunctionStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endInterfaceStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endModuleStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endProgramStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endSelectCaseStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endSubroutineStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endTStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enltTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ennTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass errorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fileTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass forallConstructStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass forallStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass forallTripletSpecLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass forallTripletSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ifStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ifThenStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass initETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inquireStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inquirySpecSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inquirySpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass interfaceStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intrinsicTSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ioControlSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ioControlTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iteratorDefinitionLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iteratorElementTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iteratorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kSelectorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass literalETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass lowerBoundTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass maskETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass moduleNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass moduleProcedureNLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass moduleStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namedETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namelistGroupNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namelistGroupObjLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namelistGroupObjNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namelistGroupObjTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namelistStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nullifyStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass objectTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass openStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass opETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass opTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass outputItemLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass outputItemTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parensETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parensRTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pointerAStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pointerStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass procedureDesignatorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass procedureStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass programNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass programStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass publicStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass readStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass renameLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass renameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resultSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass rltTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sectionSubscriptLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sectionSubscriptTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass selectCaseStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass shapeSpecLTTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass shapeSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stopStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass subroutineNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass subroutineStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tDeclStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass testETypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tnTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tSpecTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass upperBoundTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass useNTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass useStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vnTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass whereConstructStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass whereStmtTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass writeStmtTypeEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FxtranPackageImpl() {
        super(eNS_URI, FxtranFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link FxtranPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @generated
     */
    public static FxtranPackage init() {
        if (isInited) return (FxtranPackage)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredFxtranPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        FxtranPackageImpl theFxtranPackage = registeredFxtranPackage instanceof FxtranPackageImpl ? (FxtranPackageImpl)registeredFxtranPackage : new FxtranPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Load packages
        theFxtranPackage.loadPackage();

        // Fix loaded packages
        theFxtranPackage.fixPackageContents();

        // Mark meta-data to indicate it can't be changed
        theFxtranPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FxtranPackage.eNS_URI, theFxtranPackage);
        return theFxtranPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getActionStmtType() {
        if (actionStmtTypeEClass == null) {
            actionStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(0);
        }
        return actionStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getActionStmtType_ReturnStmt() {
        return (EAttribute)getActionStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_WhereStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_AStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_AllocateStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_CallStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_DeallocateStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getActionStmtType_ExitStmt() {
        return (EAttribute)getActionStmtType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_PointerAStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActionStmtType_CycleStmt() {
        return (EReference)getActionStmtType().getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAcValueLTType() {
        if (acValueLTTypeEClass == null) {
            acValueLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(1);
        }
        return acValueLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAcValueLTType_Mixed() {
        return (EAttribute)getAcValueLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAcValueLTType_Group() {
        return (EAttribute)getAcValueLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAcValueLTType_C() {
        return (EAttribute)getAcValueLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAcValueLTType_Cnt() {
        return (EAttribute)getAcValueLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAcValueLTType_AcValue() {
        return (EReference)getAcValueLTType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAcValueType() {
        if (acValueTypeEClass == null) {
            acValueTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(2);
        }
        return acValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAcValueType_LiteralE() {
        return (EReference)getAcValueType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAcValueType_NamedE() {
        return (EReference)getAcValueType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAcValueType_OpE() {
        return (EReference)getAcValueType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAcValueType_ParensE() {
        return (EReference)getAcValueType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAllocateStmtType() {
        if (allocateStmtTypeEClass == null) {
            allocateStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(3);
        }
        return allocateStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAllocateStmtType_Mixed() {
        return (EAttribute)getAllocateStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAllocateStmtType_ArgSpec() {
        return (EReference)getAllocateStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArgNType() {
        if (argNTypeEClass == null) {
            argNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(4);
        }
        return argNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgNType_N() {
        return (EReference)getArgNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgNType_K() {
        return (EAttribute)getArgNType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgNType_N1() {
        return (EAttribute)getArgNType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArgSpecType() {
        if (argSpecTypeEClass == null) {
            argSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(5);
        }
        return argSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgSpecType_Mixed() {
        return (EAttribute)getArgSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgSpecType_Group() {
        return (EAttribute)getArgSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgSpecType_Cnt() {
        return (EAttribute)getArgSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgSpecType_Arg() {
        return (EReference)getArgSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArgType() {
        if (argTypeEClass == null) {
            argTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(6);
        }
        return argTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgType_Mixed() {
        return (EAttribute)getArgType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArgType_Group() {
        return (EAttribute)getArgType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_ArgN() {
        return (EReference)getArgType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_ArrayConstructorE() {
        return (EReference)getArgType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_LiteralE() {
        return (EReference)getArgType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_NamedE() {
        return (EReference)getArgType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_OpE() {
        return (EReference)getArgType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_ParensE() {
        return (EReference)getArgType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgType_StringE() {
        return (EReference)getArgType().getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArrayConstructorEType() {
        if (arrayConstructorETypeEClass == null) {
            arrayConstructorETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(7);
        }
        return arrayConstructorETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArrayConstructorEType_Mixed() {
        return (EAttribute)getArrayConstructorEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArrayConstructorEType_Group() {
        return (EAttribute)getArrayConstructorEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArrayConstructorEType_C() {
        return (EAttribute)getArrayConstructorEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArrayConstructorEType_Cnt() {
        return (EAttribute)getArrayConstructorEType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArrayConstructorEType_AcValueLT() {
        return (EReference)getArrayConstructorEType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArrayRType() {
        if (arrayRTypeEClass == null) {
            arrayRTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(8);
        }
        return arrayRTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArrayRType_Mixed() {
        return (EAttribute)getArrayRType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArrayRType_SectionSubscriptLT() {
        return (EReference)getArrayRType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArraySpecType() {
        if (arraySpecTypeEClass == null) {
            arraySpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(9);
        }
        return arraySpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArraySpecType_Mixed() {
        return (EAttribute)getArraySpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArraySpecType_ShapeSpecLT() {
        return (EReference)getArraySpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAStmtType() {
        if (aStmtTypeEClass == null) {
            aStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(10);
        }
        return aStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAStmtType_E1() {
        return (EReference)getAStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAStmtType_A() {
        return (EAttribute)getAStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAStmtType_E2() {
        return (EReference)getAStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAttributeType() {
        if (attributeTypeEClass == null) {
            attributeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(11);
        }
        return attributeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeType_Mixed() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeType_Group() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAttributeType_ArraySpec() {
        return (EReference)getAttributeType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeType_AttributeN() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeType_IntentSpec() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCallStmtType() {
        if (callStmtTypeEClass == null) {
            callStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(12);
        }
        return callStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCallStmtType_Mixed() {
        return (EAttribute)getCallStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCallStmtType_Group() {
        return (EAttribute)getCallStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCallStmtType_ArgSpec() {
        return (EReference)getCallStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCallStmtType_Cnt() {
        return (EAttribute)getCallStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCallStmtType_ProcedureDesignator() {
        return (EReference)getCallStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseEType() {
        if (caseETypeEClass == null) {
            caseETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(13);
        }
        return caseETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseEType_NamedE() {
        return (EReference)getCaseEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseSelectorType() {
        if (caseSelectorTypeEClass == null) {
            caseSelectorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(14);
        }
        return caseSelectorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCaseSelectorType_Mixed() {
        return (EAttribute)getCaseSelectorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseSelectorType_CaseValueRangeLT() {
        return (EReference)getCaseSelectorType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseStmtType() {
        if (caseStmtTypeEClass == null) {
            caseStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(15);
        }
        return caseStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCaseStmtType_Mixed() {
        return (EAttribute)getCaseStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseStmtType_CaseSelector() {
        return (EReference)getCaseStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseValueRangeLTType() {
        if (caseValueRangeLTTypeEClass == null) {
            caseValueRangeLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(16);
        }
        return caseValueRangeLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCaseValueRangeLTType_Mixed() {
        return (EAttribute)getCaseValueRangeLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseValueRangeLTType_CaseValueRange() {
        return (EReference)getCaseValueRangeLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseValueRangeType() {
        if (caseValueRangeTypeEClass == null) {
            caseValueRangeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(17);
        }
        return caseValueRangeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseValueRangeType_CaseValue() {
        return (EReference)getCaseValueRangeType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCaseValueType() {
        if (caseValueTypeEClass == null) {
            caseValueTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(18);
        }
        return caseValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseValueType_LiteralE() {
        return (EReference)getCaseValueType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCaseValueType_StringE() {
        return (EReference)getCaseValueType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCharSelectorType() {
        if (charSelectorTypeEClass == null) {
            charSelectorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(19);
        }
        return charSelectorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCharSelectorType_Mixed() {
        return (EAttribute)getCharSelectorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSelectorType_CharSpec() {
        return (EReference)getCharSelectorType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCharSpecType() {
        if (charSpecTypeEClass == null) {
            charSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(20);
        }
        return charSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCharSpecType_Mixed() {
        return (EAttribute)getCharSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCharSpecType_Group() {
        return (EAttribute)getCharSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSpecType_ArgN() {
        return (EReference)getCharSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSpecType_Label() {
        return (EReference)getCharSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSpecType_LiteralE() {
        return (EReference)getCharSpecType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSpecType_NamedE() {
        return (EReference)getCharSpecType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCharSpecType_OpE() {
        return (EReference)getCharSpecType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCharSpecType_StarE() {
        return (EAttribute)getCharSpecType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCloseSpecSpecType() {
        if (closeSpecSpecTypeEClass == null) {
            closeSpecSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(21);
        }
        return closeSpecSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCloseSpecSpecType_Mixed() {
        return (EAttribute)getCloseSpecSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCloseSpecSpecType_CloseSpec() {
        return (EReference)getCloseSpecSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCloseSpecType() {
        if (closeSpecTypeEClass == null) {
            closeSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(22);
        }
        return closeSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCloseSpecType_Mixed() {
        return (EAttribute)getCloseSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCloseSpecType_Group() {
        return (EAttribute)getCloseSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCloseSpecType_ArgN() {
        return (EReference)getCloseSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCloseSpecType_LiteralE() {
        return (EReference)getCloseSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCloseSpecType_NamedE() {
        return (EReference)getCloseSpecType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCloseStmtType() {
        if (closeStmtTypeEClass == null) {
            closeStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(23);
        }
        return closeStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCloseStmtType_Mixed() {
        return (EAttribute)getCloseStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCloseStmtType_CloseSpecSpec() {
        return (EReference)getCloseStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentDeclStmtType() {
        if (componentDeclStmtTypeEClass == null) {
            componentDeclStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(24);
        }
        return componentDeclStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentDeclStmtType_Mixed() {
        return (EAttribute)getComponentDeclStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentDeclStmtType_Group() {
        return (EAttribute)getComponentDeclStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentDeclStmtType_ENDeclLT() {
        return (EReference)getComponentDeclStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentDeclStmtType_TSpec() {
        return (EReference)getComponentDeclStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentDeclStmtType_Attribute() {
        return (EReference)getComponentDeclStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentRType() {
        if (componentRTypeEClass == null) {
            componentRTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(25);
        }
        return componentRTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentRType_Mixed() {
        return (EAttribute)getComponentRType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentRType_Ct() {
        return (EAttribute)getComponentRType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConditionEType() {
        if (conditionETypeEClass == null) {
            conditionETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(26);
        }
        return conditionETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConditionEType_NamedE() {
        return (EReference)getConditionEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConditionEType_OpE() {
        return (EReference)getConditionEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConnectSpecSpecType() {
        if (connectSpecSpecTypeEClass == null) {
            connectSpecSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(27);
        }
        return connectSpecSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectSpecSpecType_Mixed() {
        return (EAttribute)getConnectSpecSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectSpecSpecType_Group() {
        return (EAttribute)getConnectSpecSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectSpecSpecType_Cnt() {
        return (EAttribute)getConnectSpecSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectSpecSpecType_ConnectSpec() {
        return (EReference)getConnectSpecSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConnectSpecType() {
        if (connectSpecTypeEClass == null) {
            connectSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(28);
        }
        return connectSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectSpecType_Mixed() {
        return (EAttribute)getConnectSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConnectSpecType_Group() {
        return (EAttribute)getConnectSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectSpecType_ArgN() {
        return (EReference)getConnectSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectSpecType_LiteralE() {
        return (EReference)getConnectSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectSpecType_NamedE() {
        return (EReference)getConnectSpecType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConnectSpecType_StringE() {
        return (EReference)getConnectSpecType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCycleStmtType() {
        if (cycleStmtTypeEClass == null) {
            cycleStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(29);
        }
        return cycleStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCycleStmtType_Mixed() {
        return (EAttribute)getCycleStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCycleStmtType_N() {
        return (EReference)getCycleStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDeallocateStmtType() {
        if (deallocateStmtTypeEClass == null) {
            deallocateStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(30);
        }
        return deallocateStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDeallocateStmtType_Mixed() {
        return (EAttribute)getDeallocateStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDeallocateStmtType_ArgSpec() {
        return (EReference)getDeallocateStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDerivedTSpecType() {
        if (derivedTSpecTypeEClass == null) {
            derivedTSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(31);
        }
        return derivedTSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDerivedTSpecType_Mixed() {
        return (EAttribute)getDerivedTSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDerivedTSpecType_TN() {
        return (EReference)getDerivedTSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        if (documentRootEClass == null) {
            documentRootEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(32);
        }
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_A() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_AStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_AcValue() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_AcValueLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ActionStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_AllocateStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Arg() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ArgN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ArgSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ArrayConstructorE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ArrayR() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ArraySpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Attribute() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_AttributeN() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_C() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CallStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseSelector() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseValue() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseValueRange() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CaseValueRangeLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CharSelector() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CharSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CloseSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CloseSpecSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CloseStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Cnt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ComponentDeclStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ComponentR() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ConditionE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ConnectSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ConnectSpecSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ContainsStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Cpp() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Ct() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_CycleStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_DeallocateStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_DerivedTSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(42);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_DoStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(43);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_DoV() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(44);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_DummyArgLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(45);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_E1() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(46);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_E2() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(47);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Element() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(48);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ElementLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(49);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ElseIfStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(50);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ElseStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(51);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ElseWhereStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(52);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(53);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ENDecl() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(54);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ENDeclLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(55);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ENLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(56);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ENN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(57);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndDoStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(58);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndForallStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(59);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndFunctionStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(60);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_EndIfStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(61);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndInterfaceStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(62);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndModuleStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(63);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndProgramStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(64);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndSelectCaseStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(65);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndSubroutineStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(66);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EndTStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(67);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_EndWhereStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(68);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Error() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(69);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ExitStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(70);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_File() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(71);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ForallConstructStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(72);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ForallStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(73);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ForallTripletSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(74);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ForallTripletSpecLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(75);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_FunctionN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(76);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_FunctionStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(77);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IfStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(78);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IfThenStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(79);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ImplicitNoneStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(80);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InitE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(81);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InquireStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(82);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InquirySpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(83);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InquirySpecSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(84);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_IntentSpec() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(85);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InterfaceStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(86);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IntrinsicTSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(87);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IoControl() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(88);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IoControlSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(89);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Iterator() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(90);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IteratorDefinitionLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(91);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_IteratorElement() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(92);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_K() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(93);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_KSelector() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(94);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_KSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(95);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_L() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(96);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Label() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(97);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LiteralE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(98);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LowerBound() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(99);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_MaskE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(100);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ModuleN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(101);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ModuleProcedureNLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(102);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ModuleStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(103);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_N() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(104);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_N1() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(105);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamedE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(106);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamelistGroupN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(107);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamelistGroupObj() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(108);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamelistGroupObjLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(109);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamelistGroupObjN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(110);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NamelistStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(111);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NullifyStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(112);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_O() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(113);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Object() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(114);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Op() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(115);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_OpE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(116);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_OpenStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(117);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_OutputItem() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(118);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_OutputItemLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(119);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ParensE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(120);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ParensR() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(121);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_PointerAStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(122);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_PointerStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(123);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Prefix() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(124);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_PrivateStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(125);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ProcedureDesignator() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(126);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ProcedureStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(127);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ProgramN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(128);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ProgramStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(129);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_PublicStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(130);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_RLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(131);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ReadStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(132);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Rename() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(133);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_RenameLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(134);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ResultSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(135);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_ReturnStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(136);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_S() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(137);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_SaveStmt() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(138);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SectionSubscript() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(139);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SectionSubscriptLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(140);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SelectCaseStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(141);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ShapeSpec() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(142);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ShapeSpecLT() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(143);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_StarE() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(144);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_StopCode() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(145);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_StopStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(146);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_StringE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(147);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SubroutineN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(148);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SubroutineStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(149);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TDeclStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(150);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(151);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(152);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TestE() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(153);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_UpperBound() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(154);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_UseN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(155);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_UseStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(156);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_V() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(157);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_VN() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(158);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_WhereConstructStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(159);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_WhereStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(160);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_WriteStmt() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(161);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDoStmtType() {
        if (doStmtTypeEClass == null) {
            doStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(33);
        }
        return doStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDoStmtType_Mixed() {
        return (EAttribute)getDoStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDoStmtType_Group() {
        return (EAttribute)getDoStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoStmtType_N() {
        return (EReference)getDoStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoStmtType_LowerBound() {
        return (EReference)getDoStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoStmtType_UpperBound() {
        return (EReference)getDoStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoStmtType_DoV() {
        return (EReference)getDoStmtType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoStmtType_TestE() {
        return (EReference)getDoStmtType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDoVType() {
        if (doVTypeEClass == null) {
            doVTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(34);
        }
        return doVTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDoVType_NamedE() {
        return (EReference)getDoVType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDummyArgLTType() {
        if (dummyArgLTTypeEClass == null) {
            dummyArgLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(35);
        }
        return dummyArgLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDummyArgLTType_Mixed() {
        return (EAttribute)getDummyArgLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDummyArgLTType_Group() {
        return (EAttribute)getDummyArgLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDummyArgLTType_ArgN() {
        return (EReference)getDummyArgLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDummyArgLTType_Cnt() {
        return (EAttribute)getDummyArgLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getE1Type() {
        if (e1TypeEClass == null) {
            e1TypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(36);
        }
        return e1TypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE1Type_NamedE() {
        return (EReference)getE1Type().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getE2Type() {
        if (e2TypeEClass == null) {
            e2TypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(37);
        }
        return e2TypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_ArrayConstructorE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_LiteralE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_NamedE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_OpE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_ParensE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getE2Type_StringE() {
        return (EReference)getE2Type().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElementLTType() {
        if (elementLTTypeEClass == null) {
            elementLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(38);
        }
        return elementLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementLTType_Mixed() {
        return (EAttribute)getElementLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementLTType_Group() {
        return (EAttribute)getElementLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementLTType_Cnt() {
        return (EAttribute)getElementLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementLTType_Element() {
        return (EReference)getElementLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElementType() {
        if (elementTypeEClass == null) {
            elementTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(39);
        }
        return elementTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementType_ArrayConstructorE() {
        return (EReference)getElementType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementType_Group() {
        return (EAttribute)getElementType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementType_NamedE() {
        return (EReference)getElementType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementType_OpE() {
        return (EReference)getElementType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementType_LiteralE() {
        return (EReference)getElementType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementType_StringE() {
        return (EReference)getElementType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElseIfStmtType() {
        if (elseIfStmtTypeEClass == null) {
            elseIfStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(40);
        }
        return elseIfStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElseIfStmtType_Mixed() {
        return (EAttribute)getElseIfStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElseIfStmtType_ConditionE() {
        return (EReference)getElseIfStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndDoStmtType() {
        if (endDoStmtTypeEClass == null) {
            endDoStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(41);
        }
        return endDoStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndDoStmtType_Mixed() {
        return (EAttribute)getEndDoStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndDoStmtType_N() {
        return (EReference)getEndDoStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getENDeclLTType() {
        if (enDeclLTTypeEClass == null) {
            enDeclLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(42);
        }
        return enDeclLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENDeclLTType_Mixed() {
        return (EAttribute)getENDeclLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENDeclLTType_Group() {
        return (EAttribute)getENDeclLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENDeclLTType_Cnt() {
        return (EAttribute)getENDeclLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENDeclLTType_ENDecl() {
        return (EReference)getENDeclLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getENDeclType() {
        if (enDeclTypeEClass == null) {
            enDeclTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(43);
        }
        return enDeclTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENDeclType_Mixed() {
        return (EAttribute)getENDeclType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENDeclType_Group() {
        return (EAttribute)getENDeclType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENDeclType_ArraySpec() {
        return (EReference)getENDeclType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENDeclType_ENN() {
        return (EReference)getENDeclType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENDeclType_InitE() {
        return (EReference)getENDeclType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndForallStmtType() {
        if (endForallStmtTypeEClass == null) {
            endForallStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(44);
        }
        return endForallStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndForallStmtType_Mixed() {
        return (EAttribute)getEndForallStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndForallStmtType_N() {
        return (EReference)getEndForallStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndFunctionStmtType() {
        if (endFunctionStmtTypeEClass == null) {
            endFunctionStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(45);
        }
        return endFunctionStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndFunctionStmtType_Mixed() {
        return (EAttribute)getEndFunctionStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndFunctionStmtType_FunctionN() {
        return (EReference)getEndFunctionStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndInterfaceStmtType() {
        if (endInterfaceStmtTypeEClass == null) {
            endInterfaceStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(46);
        }
        return endInterfaceStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndInterfaceStmtType_Mixed() {
        return (EAttribute)getEndInterfaceStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndInterfaceStmtType_N() {
        return (EReference)getEndInterfaceStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndModuleStmtType() {
        if (endModuleStmtTypeEClass == null) {
            endModuleStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(47);
        }
        return endModuleStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndModuleStmtType_Mixed() {
        return (EAttribute)getEndModuleStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndModuleStmtType_ModuleN() {
        return (EReference)getEndModuleStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndProgramStmtType() {
        if (endProgramStmtTypeEClass == null) {
            endProgramStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(48);
        }
        return endProgramStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndProgramStmtType_Mixed() {
        return (EAttribute)getEndProgramStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndProgramStmtType_ProgramN() {
        return (EReference)getEndProgramStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndSelectCaseStmtType() {
        if (endSelectCaseStmtTypeEClass == null) {
            endSelectCaseStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(49);
        }
        return endSelectCaseStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndSelectCaseStmtType_Mixed() {
        return (EAttribute)getEndSelectCaseStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndSelectCaseStmtType_N() {
        return (EReference)getEndSelectCaseStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndSubroutineStmtType() {
        if (endSubroutineStmtTypeEClass == null) {
            endSubroutineStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(50);
        }
        return endSubroutineStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndSubroutineStmtType_Mixed() {
        return (EAttribute)getEndSubroutineStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndSubroutineStmtType_SubroutineN() {
        return (EReference)getEndSubroutineStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndTStmtType() {
        if (endTStmtTypeEClass == null) {
            endTStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(51);
        }
        return endTStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndTStmtType_Mixed() {
        return (EAttribute)getEndTStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEndTStmtType_TN() {
        return (EReference)getEndTStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getENLTType() {
        if (enltTypeEClass == null) {
            enltTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(52);
        }
        return enltTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENLTType_Mixed() {
        return (EAttribute)getENLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENLTType_Group() {
        return (EAttribute)getENLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getENLTType_Cnt() {
        return (EAttribute)getENLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENLTType_EN() {
        return (EReference)getENLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getENNType() {
        if (ennTypeEClass == null) {
            ennTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(53);
        }
        return ennTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENNType_N() {
        return (EReference)getENNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getENType() {
        if (enTypeEClass == null) {
            enTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(54);
        }
        return enTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getENType_N() {
        return (EReference)getENType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getErrorType() {
        if (errorTypeEClass == null) {
            errorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(55);
        }
        return errorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getErrorType_Msg() {
        return (EAttribute)getErrorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFileType() {
        if (fileTypeEClass == null) {
            fileTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(56);
        }
        return fileTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_Group() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_C() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_AStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_AllocateStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_CallStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_DeallocateStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ExitStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_PointerAStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ReturnStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_WhereStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_TDeclStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_TStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_CaseStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_CloseStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ComponentDeclStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ContainsStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_Cpp() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_DoStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ElseIfStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ElseStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ElseWhereStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndTStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndDoStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndForallStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndFunctionStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_EndIfStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndInterfaceStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndSelectCaseStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndSubroutineStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_EndWhereStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ForallConstructStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ForallStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_FunctionStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_IfStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_IfThenStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_ImplicitNoneStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_InquireStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_InterfaceStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ModuleStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_NamelistStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_NullifyStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_OpenStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_PointerStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(42);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_PrivateStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(43);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ProcedureStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(44);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ProgramStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(45);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_PublicStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(46);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_ReadStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(47);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_SaveStmt() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(48);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_SelectCaseStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(49);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_StopStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(50);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_SubroutineStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(51);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_UseStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(52);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_WhereConstructStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(53);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_WriteStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(54);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndModuleStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(55);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFileType_EndProgramStmt() {
        return (EReference)getFileType().getEStructuralFeatures().get(56);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFileType_Name() {
        return (EAttribute)getFileType().getEStructuralFeatures().get(57);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getForallConstructStmtType() {
        if (forallConstructStmtTypeEClass == null) {
            forallConstructStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(57);
        }
        return forallConstructStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallConstructStmtType_Mixed() {
        return (EAttribute)getForallConstructStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallConstructStmtType_Group() {
        return (EAttribute)getForallConstructStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallConstructStmtType_N() {
        return (EReference)getForallConstructStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallConstructStmtType_ForallTripletSpecLT() {
        return (EReference)getForallConstructStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getForallStmtType() {
        if (forallStmtTypeEClass == null) {
            forallStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(58);
        }
        return forallStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallStmtType_Mixed() {
        return (EAttribute)getForallStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallStmtType_Group() {
        return (EAttribute)getForallStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallStmtType_ActionStmt() {
        return (EReference)getForallStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallStmtType_Cnt() {
        return (EAttribute)getForallStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallStmtType_ForallTripletSpecLT() {
        return (EReference)getForallStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallStmtType_MaskE() {
        return (EReference)getForallStmtType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getForallTripletSpecLTType() {
        if (forallTripletSpecLTTypeEClass == null) {
            forallTripletSpecLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(59);
        }
        return forallTripletSpecLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallTripletSpecLTType_Mixed() {
        return (EAttribute)getForallTripletSpecLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallTripletSpecLTType_ForallTripletSpec() {
        return (EReference)getForallTripletSpecLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getForallTripletSpecType() {
        if (forallTripletSpecTypeEClass == null) {
            forallTripletSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(60);
        }
        return forallTripletSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallTripletSpecType_Mixed() {
        return (EAttribute)getForallTripletSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getForallTripletSpecType_Group() {
        return (EAttribute)getForallTripletSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallTripletSpecType_LowerBound() {
        return (EReference)getForallTripletSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallTripletSpecType_UpperBound() {
        return (EReference)getForallTripletSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getForallTripletSpecType_V() {
        return (EReference)getForallTripletSpecType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFunctionNType() {
        if (functionNTypeEClass == null) {
            functionNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(61);
        }
        return functionNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionNType_N() {
        return (EReference)getFunctionNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFunctionStmtType() {
        if (functionStmtTypeEClass == null) {
            functionStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(62);
        }
        return functionStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionStmtType_Mixed() {
        return (EAttribute)getFunctionStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionStmtType_Group() {
        return (EAttribute)getFunctionStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionStmtType_DerivedTSpec() {
        return (EReference)getFunctionStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionStmtType_DummyArgLT() {
        return (EReference)getFunctionStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionStmtType_FunctionN() {
        return (EReference)getFunctionStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionStmtType_IntrinsicTSpec() {
        return (EReference)getFunctionStmtType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionStmtType_Prefix() {
        return (EAttribute)getFunctionStmtType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFunctionStmtType_ResultSpec() {
        return (EReference)getFunctionStmtType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIfStmtType() {
        if (ifStmtTypeEClass == null) {
            ifStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(63);
        }
        return ifStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIfStmtType_Mixed() {
        return (EAttribute)getIfStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIfStmtType_Group() {
        return (EAttribute)getIfStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIfStmtType_ActionStmt() {
        return (EReference)getIfStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIfStmtType_Cnt() {
        return (EAttribute)getIfStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIfStmtType_ConditionE() {
        return (EReference)getIfStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIfThenStmtType() {
        if (ifThenStmtTypeEClass == null) {
            ifThenStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(64);
        }
        return ifThenStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIfThenStmtType_Mixed() {
        return (EAttribute)getIfThenStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIfThenStmtType_ConditionE() {
        return (EReference)getIfThenStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInitEType() {
        if (initETypeEClass == null) {
            initETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(65);
        }
        return initETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInitEType_LiteralE() {
        return (EReference)getInitEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInitEType_NamedE() {
        return (EReference)getInitEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInitEType_OpE() {
        return (EReference)getInitEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInitEType_StringE() {
        return (EReference)getInitEType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInquireStmtType() {
        if (inquireStmtTypeEClass == null) {
            inquireStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(66);
        }
        return inquireStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInquireStmtType_Mixed() {
        return (EAttribute)getInquireStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInquireStmtType_InquirySpecSpec() {
        return (EReference)getInquireStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInquirySpecSpecType() {
        if (inquirySpecSpecTypeEClass == null) {
            inquirySpecSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(67);
        }
        return inquirySpecSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInquirySpecSpecType_Mixed() {
        return (EAttribute)getInquirySpecSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInquirySpecSpecType_InquirySpec() {
        return (EReference)getInquirySpecSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInquirySpecType() {
        if (inquirySpecTypeEClass == null) {
            inquirySpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(68);
        }
        return inquirySpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInquirySpecType_Mixed() {
        return (EAttribute)getInquirySpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInquirySpecType_Group() {
        return (EAttribute)getInquirySpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInquirySpecType_ArgN() {
        return (EReference)getInquirySpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInquirySpecType_NamedE() {
        return (EReference)getInquirySpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInterfaceStmtType() {
        if (interfaceStmtTypeEClass == null) {
            interfaceStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(69);
        }
        return interfaceStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInterfaceStmtType_Mixed() {
        return (EAttribute)getInterfaceStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInterfaceStmtType_N() {
        return (EReference)getInterfaceStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntrinsicTSpecType() {
        if (intrinsicTSpecTypeEClass == null) {
            intrinsicTSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(70);
        }
        return intrinsicTSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntrinsicTSpecType_TN() {
        return (EReference)getIntrinsicTSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntrinsicTSpecType_KSelector() {
        return (EReference)getIntrinsicTSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntrinsicTSpecType_CharSelector() {
        return (EReference)getIntrinsicTSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIoControlSpecType() {
        if (ioControlSpecTypeEClass == null) {
            ioControlSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(71);
        }
        return ioControlSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIoControlSpecType_Mixed() {
        return (EAttribute)getIoControlSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlSpecType_IoControl() {
        return (EReference)getIoControlSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIoControlType() {
        if (ioControlTypeEClass == null) {
            ioControlTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(72);
        }
        return ioControlTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIoControlType_Mixed() {
        return (EAttribute)getIoControlType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIoControlType_Group() {
        return (EAttribute)getIoControlType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlType_ArgN() {
        return (EReference)getIoControlType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlType_Label() {
        return (EReference)getIoControlType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlType_LiteralE() {
        return (EReference)getIoControlType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlType_NamedE() {
        return (EReference)getIoControlType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIoControlType_StringE() {
        return (EReference)getIoControlType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIteratorDefinitionLTType() {
        if (iteratorDefinitionLTTypeEClass == null) {
            iteratorDefinitionLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(73);
        }
        return iteratorDefinitionLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIteratorDefinitionLTType_Mixed() {
        return (EAttribute)getIteratorDefinitionLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIteratorDefinitionLTType_IteratorElement() {
        return (EReference)getIteratorDefinitionLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIteratorElementType() {
        if (iteratorElementTypeEClass == null) {
            iteratorElementTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(74);
        }
        return iteratorElementTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIteratorElementType_Mixed() {
        return (EAttribute)getIteratorElementType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIteratorElementType_Group() {
        return (EAttribute)getIteratorElementType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIteratorElementType_VN() {
        return (EReference)getIteratorElementType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIteratorElementType_LiteralE() {
        return (EReference)getIteratorElementType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIteratorElementType_NamedE() {
        return (EReference)getIteratorElementType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIteratorType() {
        if (iteratorTypeEClass == null) {
            iteratorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(75);
        }
        return iteratorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIteratorType_Mixed() {
        return (EAttribute)getIteratorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIteratorType_IteratorDefinitionLT() {
        return (EReference)getIteratorType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKSelectorType() {
        if (kSelectorTypeEClass == null) {
            kSelectorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(76);
        }
        return kSelectorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKSelectorType_Mixed() {
        return (EAttribute)getKSelectorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKSelectorType_KSpec() {
        return (EReference)getKSelectorType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKSpecType() {
        if (kSpecTypeEClass == null) {
            kSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(77);
        }
        return kSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKSpecType_Mixed() {
        return (EAttribute)getKSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKSpecType_Group() {
        return (EAttribute)getKSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKSpecType_N() {
        return (EReference)getKSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKSpecType_L() {
        return (EAttribute)getKSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKSpecType_LiteralE() {
        return (EReference)getKSpecType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKSpecType_NamedE() {
        return (EReference)getKSpecType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelType() {
        if (labelTypeEClass == null) {
            labelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(78);
        }
        return labelTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelType_Error() {
        return (EReference)getLabelType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLiteralEType() {
        if (literalETypeEClass == null) {
            literalETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(79);
        }
        return literalETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLiteralEType_Mixed() {
        return (EAttribute)getLiteralEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLiteralEType_Group() {
        return (EAttribute)getLiteralEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLiteralEType_KSpec() {
        return (EReference)getLiteralEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLiteralEType_L() {
        return (EAttribute)getLiteralEType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLowerBoundType() {
        if (lowerBoundTypeEClass == null) {
            lowerBoundTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(80);
        }
        return lowerBoundTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLowerBoundType_LiteralE() {
        return (EReference)getLowerBoundType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLowerBoundType_NamedE() {
        return (EReference)getLowerBoundType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLowerBoundType_OpE() {
        return (EReference)getLowerBoundType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMaskEType() {
        if (maskETypeEClass == null) {
            maskETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(81);
        }
        return maskETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMaskEType_OpE() {
        return (EReference)getMaskEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getModuleNType() {
        if (moduleNTypeEClass == null) {
            moduleNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(82);
        }
        return moduleNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getModuleNType_N() {
        return (EReference)getModuleNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getModuleProcedureNLTType() {
        if (moduleProcedureNLTTypeEClass == null) {
            moduleProcedureNLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(83);
        }
        return moduleProcedureNLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getModuleProcedureNLTType_Mixed() {
        return (EAttribute)getModuleProcedureNLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getModuleProcedureNLTType_N() {
        return (EReference)getModuleProcedureNLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getModuleStmtType() {
        if (moduleStmtTypeEClass == null) {
            moduleStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(84);
        }
        return moduleStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getModuleStmtType_Mixed() {
        return (EAttribute)getModuleStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getModuleStmtType_ModuleN() {
        return (EReference)getModuleStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamedEType() {
        if (namedETypeEClass == null) {
            namedETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(85);
        }
        return namedETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamedEType_Group() {
        return (EAttribute)getNamedEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamedEType_N() {
        return (EReference)getNamedEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamedEType_RLT() {
        return (EReference)getNamedEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamelistGroupNType() {
        if (namelistGroupNTypeEClass == null) {
            namelistGroupNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(86);
        }
        return namelistGroupNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistGroupNType_N() {
        return (EAttribute)getNamelistGroupNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamelistGroupObjLTType() {
        if (namelistGroupObjLTTypeEClass == null) {
            namelistGroupObjLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(87);
        }
        return namelistGroupObjLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistGroupObjLTType_Mixed() {
        return (EAttribute)getNamelistGroupObjLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistGroupObjLTType_Group() {
        return (EAttribute)getNamelistGroupObjLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistGroupObjLTType_C() {
        return (EAttribute)getNamelistGroupObjLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistGroupObjLTType_Cnt() {
        return (EAttribute)getNamelistGroupObjLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamelistGroupObjLTType_NamelistGroupObj() {
        return (EReference)getNamelistGroupObjLTType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamelistGroupObjNType() {
        if (namelistGroupObjNTypeEClass == null) {
            namelistGroupObjNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(88);
        }
        return namelistGroupObjNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamelistGroupObjNType_N() {
        return (EReference)getNamelistGroupObjNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamelistGroupObjType() {
        if (namelistGroupObjTypeEClass == null) {
            namelistGroupObjTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(89);
        }
        return namelistGroupObjTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamelistGroupObjType_NamelistGroupObjN() {
        return (EReference)getNamelistGroupObjType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamelistStmtType() {
        if (namelistStmtTypeEClass == null) {
            namelistStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(90);
        }
        return namelistStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistStmtType_Mixed() {
        return (EAttribute)getNamelistStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistStmtType_Group() {
        return (EAttribute)getNamelistStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamelistStmtType_Cnt() {
        return (EAttribute)getNamelistStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamelistStmtType_NamelistGroupN() {
        return (EReference)getNamelistStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNamelistStmtType_NamelistGroupObjLT() {
        return (EReference)getNamelistStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNType() {
        if (nTypeEClass == null) {
            nTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(91);
        }
        return nTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNType_Mixed() {
        return (EAttribute)getNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNType_Group() {
        return (EAttribute)getNType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNType_N() {
        return (EReference)getNType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNType_N1() {
        return (EAttribute)getNType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNType_Op() {
        return (EReference)getNType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNullifyStmtType() {
        if (nullifyStmtTypeEClass == null) {
            nullifyStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(92);
        }
        return nullifyStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNullifyStmtType_Mixed() {
        return (EAttribute)getNullifyStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNullifyStmtType_ArgSpec() {
        return (EReference)getNullifyStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getObjectType() {
        if (objectTypeEClass == null) {
            objectTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(93);
        }
        return objectTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getObjectType_File() {
        return (EReference)getObjectType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getObjectType_Openacc() {
        return (EAttribute)getObjectType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getObjectType_Openmp() {
        return (EAttribute)getObjectType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getObjectType_SourceForm() {
        return (EAttribute)getObjectType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getObjectType_SourceWidth() {
        return (EAttribute)getObjectType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOpenStmtType() {
        if (openStmtTypeEClass == null) {
            openStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(94);
        }
        return openStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpenStmtType_Mixed() {
        return (EAttribute)getOpenStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpenStmtType_Group() {
        return (EAttribute)getOpenStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpenStmtType_Cnt() {
        return (EAttribute)getOpenStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpenStmtType_ConnectSpecSpec() {
        return (EReference)getOpenStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOpEType() {
        if (opETypeEClass == null) {
            opETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(95);
        }
        return opETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpEType_Group() {
        return (EAttribute)getOpEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpEType_Cnt() {
        return (EAttribute)getOpEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_LiteralE() {
        return (EReference)getOpEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_NamedE() {
        return (EReference)getOpEType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_Op() {
        return (EReference)getOpEType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_OpE() {
        return (EReference)getOpEType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_ParensE() {
        return (EReference)getOpEType().getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOpEType_StringE() {
        return (EReference)getOpEType().getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOpType() {
        if (opTypeEClass == null) {
            opTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(96);
        }
        return opTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOpType_O() {
        return (EAttribute)getOpType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOutputItemLTType() {
        if (outputItemLTTypeEClass == null) {
            outputItemLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(97);
        }
        return outputItemLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOutputItemLTType_Mixed() {
        return (EAttribute)getOutputItemLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputItemLTType_OutputItem() {
        return (EReference)getOutputItemLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOutputItemType() {
        if (outputItemTypeEClass == null) {
            outputItemTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(98);
        }
        return outputItemTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputItemType_LiteralE() {
        return (EReference)getOutputItemType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputItemType_NamedE() {
        return (EReference)getOutputItemType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputItemType_OpE() {
        return (EReference)getOutputItemType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOutputItemType_StringE() {
        return (EReference)getOutputItemType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParensEType() {
        if (parensETypeEClass == null) {
            parensETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(99);
        }
        return parensETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensEType_Mixed() {
        return (EAttribute)getParensEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensEType_Group() {
        return (EAttribute)getParensEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensEType_Cnt() {
        return (EAttribute)getParensEType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParensEType_OpE() {
        return (EReference)getParensEType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParensEType_Iterator() {
        return (EReference)getParensEType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParensRType() {
        if (parensRTypeEClass == null) {
            parensRTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(100);
        }
        return parensRTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensRType_Mixed() {
        return (EAttribute)getParensRType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensRType_Group() {
        return (EAttribute)getParensRType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParensRType_ArgSpec() {
        return (EReference)getParensRType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getParensRType_Cnt() {
        return (EAttribute)getParensRType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParensRType_ElementLT() {
        return (EReference)getParensRType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPointerAStmtType() {
        if (pointerAStmtTypeEClass == null) {
            pointerAStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(101);
        }
        return pointerAStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPointerAStmtType_E1() {
        return (EReference)getPointerAStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointerAStmtType_A() {
        return (EAttribute)getPointerAStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPointerAStmtType_E2() {
        return (EReference)getPointerAStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPointerStmtType() {
        if (pointerStmtTypeEClass == null) {
            pointerStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(102);
        }
        return pointerStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointerStmtType_Mixed() {
        return (EAttribute)getPointerStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPointerStmtType_ENDeclLT() {
        return (EReference)getPointerStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcedureDesignatorType() {
        if (procedureDesignatorTypeEClass == null) {
            procedureDesignatorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(103);
        }
        return procedureDesignatorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcedureDesignatorType_NamedE() {
        return (EReference)getProcedureDesignatorType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcedureStmtType() {
        if (procedureStmtTypeEClass == null) {
            procedureStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(104);
        }
        return procedureStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcedureStmtType_Mixed() {
        return (EAttribute)getProcedureStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcedureStmtType_ModuleProcedureNLT() {
        return (EReference)getProcedureStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProgramNType() {
        if (programNTypeEClass == null) {
            programNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(105);
        }
        return programNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProgramNType_N() {
        return (EReference)getProgramNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProgramStmtType() {
        if (programStmtTypeEClass == null) {
            programStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(106);
        }
        return programStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProgramStmtType_Mixed() {
        return (EAttribute)getProgramStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProgramStmtType_ProgramN() {
        return (EReference)getProgramStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPublicStmtType() {
        if (publicStmtTypeEClass == null) {
            publicStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(107);
        }
        return publicStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPublicStmtType_Mixed() {
        return (EAttribute)getPublicStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPublicStmtType_ENLT() {
        return (EReference)getPublicStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getReadStmtType() {
        if (readStmtTypeEClass == null) {
            readStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(108);
        }
        return readStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReadStmtType_Mixed() {
        return (EAttribute)getReadStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReadStmtType_IoControlSpec() {
        return (EReference)getReadStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRenameLTType() {
        if (renameLTTypeEClass == null) {
            renameLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(109);
        }
        return renameLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRenameLTType_Mixed() {
        return (EAttribute)getRenameLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRenameLTType_Group() {
        return (EAttribute)getRenameLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRenameLTType_Cnt() {
        return (EAttribute)getRenameLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRenameLTType_Rename() {
        return (EReference)getRenameLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRenameType() {
        if (renameTypeEClass == null) {
            renameTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(110);
        }
        return renameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRenameType_UseN() {
        return (EReference)getRenameType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResultSpecType() {
        if (resultSpecTypeEClass == null) {
            resultSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(111);
        }
        return resultSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResultSpecType_Mixed() {
        return (EAttribute)getResultSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResultSpecType_N() {
        return (EReference)getResultSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRLTType() {
        if (rltTypeEClass == null) {
            rltTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(112);
        }
        return rltTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRLTType_Group() {
        return (EAttribute)getRLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRLTType_ArrayR() {
        return (EReference)getRLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRLTType_ComponentR() {
        return (EReference)getRLTType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRLTType_ParensR() {
        return (EReference)getRLTType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSectionSubscriptLTType() {
        if (sectionSubscriptLTTypeEClass == null) {
            sectionSubscriptLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(113);
        }
        return sectionSubscriptLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSectionSubscriptLTType_Mixed() {
        return (EAttribute)getSectionSubscriptLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSectionSubscriptLTType_SectionSubscript() {
        return (EReference)getSectionSubscriptLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSectionSubscriptType() {
        if (sectionSubscriptTypeEClass == null) {
            sectionSubscriptTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(114);
        }
        return sectionSubscriptTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSectionSubscriptType_Mixed() {
        return (EAttribute)getSectionSubscriptType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSectionSubscriptType_Group() {
        return (EAttribute)getSectionSubscriptType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSectionSubscriptType_LowerBound() {
        return (EReference)getSectionSubscriptType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSectionSubscriptType_UpperBound() {
        return (EReference)getSectionSubscriptType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSelectCaseStmtType() {
        if (selectCaseStmtTypeEClass == null) {
            selectCaseStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(115);
        }
        return selectCaseStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSelectCaseStmtType_Mixed() {
        return (EAttribute)getSelectCaseStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSelectCaseStmtType_Group() {
        return (EAttribute)getSelectCaseStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSelectCaseStmtType_N() {
        return (EReference)getSelectCaseStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSelectCaseStmtType_CaseE() {
        return (EReference)getSelectCaseStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getShapeSpecLTType() {
        if (shapeSpecLTTypeEClass == null) {
            shapeSpecLTTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(116);
        }
        return shapeSpecLTTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeSpecLTType_Mixed() {
        return (EAttribute)getShapeSpecLTType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getShapeSpecLTType_ShapeSpec() {
        return (EReference)getShapeSpecLTType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getShapeSpecType() {
        if (shapeSpecTypeEClass == null) {
            shapeSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(117);
        }
        return shapeSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeSpecType_Mixed() {
        return (EAttribute)getShapeSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeSpecType_Group() {
        return (EAttribute)getShapeSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getShapeSpecType_LowerBound() {
        return (EReference)getShapeSpecType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getShapeSpecType_UpperBound() {
        return (EReference)getShapeSpecType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStopStmtType() {
        if (stopStmtTypeEClass == null) {
            stopStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(118);
        }
        return stopStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStopStmtType_Mixed() {
        return (EAttribute)getStopStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStopStmtType_StopCode() {
        return (EAttribute)getStopStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringEType() {
        if (stringETypeEClass == null) {
            stringETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(119);
        }
        return stringETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStringEType_S() {
        return (EAttribute)getStringEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSubroutineNType() {
        if (subroutineNTypeEClass == null) {
            subroutineNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(120);
        }
        return subroutineNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSubroutineNType_N() {
        return (EReference)getSubroutineNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSubroutineStmtType() {
        if (subroutineStmtTypeEClass == null) {
            subroutineStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(121);
        }
        return subroutineStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSubroutineStmtType_Mixed() {
        return (EAttribute)getSubroutineStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSubroutineStmtType_Group() {
        return (EAttribute)getSubroutineStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSubroutineStmtType_Cnt() {
        return (EAttribute)getSubroutineStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSubroutineStmtType_DummyArgLT() {
        return (EReference)getSubroutineStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSubroutineStmtType_Prefix() {
        return (EAttribute)getSubroutineStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSubroutineStmtType_SubroutineN() {
        return (EReference)getSubroutineStmtType().getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTDeclStmtType() {
        if (tDeclStmtTypeEClass == null) {
            tDeclStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(122);
        }
        return tDeclStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTDeclStmtType_Mixed() {
        return (EAttribute)getTDeclStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTDeclStmtType_Group() {
        return (EAttribute)getTDeclStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTDeclStmtType_ENDeclLT() {
        return (EReference)getTDeclStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTDeclStmtType_TSpec() {
        return (EReference)getTDeclStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTDeclStmtType_Attribute() {
        return (EReference)getTDeclStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTestEType() {
        if (testETypeEClass == null) {
            testETypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(123);
        }
        return testETypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestEType_NamedE() {
        return (EReference)getTestEType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestEType_OpE() {
        return (EReference)getTestEType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTNType() {
        if (tnTypeEClass == null) {
            tnTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(124);
        }
        return tnTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTNType_Mixed() {
        return (EAttribute)getTNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTNType_N() {
        return (EReference)getTNType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTSpecType() {
        if (tSpecTypeEClass == null) {
            tSpecTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(125);
        }
        return tSpecTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTSpecType_DerivedTSpec() {
        return (EReference)getTSpecType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTSpecType_IntrinsicTSpec() {
        return (EReference)getTSpecType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTStmtType() {
        if (tStmtTypeEClass == null) {
            tStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(126);
        }
        return tStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTStmtType_Mixed() {
        return (EAttribute)getTStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTStmtType_Group() {
        return (EAttribute)getTStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTStmtType_TN() {
        return (EReference)getTStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTStmtType_Attribute() {
        return (EReference)getTStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUpperBoundType() {
        if (upperBoundTypeEClass == null) {
            upperBoundTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(127);
        }
        return upperBoundTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpperBoundType_LiteralE() {
        return (EReference)getUpperBoundType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpperBoundType_NamedE() {
        return (EReference)getUpperBoundType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpperBoundType_OpE() {
        return (EReference)getUpperBoundType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUseNType() {
        if (useNTypeEClass == null) {
            useNTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(128);
        }
        return useNTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUseNType_N() {
        return (EReference)getUseNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUseStmtType() {
        if (useStmtTypeEClass == null) {
            useStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(129);
        }
        return useStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUseStmtType_Mixed() {
        return (EAttribute)getUseStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUseStmtType_Group() {
        return (EAttribute)getUseStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUseStmtType_ModuleN() {
        return (EReference)getUseStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUseStmtType_RenameLT() {
        return (EReference)getUseStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVNType() {
        if (vnTypeEClass == null) {
            vnTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(130);
        }
        return vnTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVNType_VN() {
        return (EReference)getVNType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVNType_N() {
        return (EAttribute)getVNType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVType() {
        if (vTypeEClass == null) {
            vTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(131);
        }
        return vTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVType_NamedE() {
        return (EReference)getVType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getWhereConstructStmtType() {
        if (whereConstructStmtTypeEClass == null) {
            whereConstructStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(132);
        }
        return whereConstructStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWhereConstructStmtType_Mixed() {
        return (EAttribute)getWhereConstructStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getWhereConstructStmtType_MaskE() {
        return (EReference)getWhereConstructStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getWhereStmtType() {
        if (whereStmtTypeEClass == null) {
            whereStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(133);
        }
        return whereStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWhereStmtType_Mixed() {
        return (EAttribute)getWhereStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWhereStmtType_Group() {
        return (EAttribute)getWhereStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getWhereStmtType_ActionStmt() {
        return (EReference)getWhereStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWhereStmtType_Cnt() {
        return (EAttribute)getWhereStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getWhereStmtType_MaskE() {
        return (EReference)getWhereStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getWriteStmtType() {
        if (writeStmtTypeEClass == null) {
            writeStmtTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FxtranPackage.eNS_URI).getEClassifiers().get(134);
        }
        return writeStmtTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWriteStmtType_Mixed() {
        return (EAttribute)getWriteStmtType().getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWriteStmtType_Group() {
        return (EAttribute)getWriteStmtType().getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWriteStmtType_Cnt() {
        return (EAttribute)getWriteStmtType().getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getWriteStmtType_IoControlSpec() {
        return (EReference)getWriteStmtType().getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getWriteStmtType_OutputItemLT() {
        return (EReference)getWriteStmtType().getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FxtranFactory getFxtranFactory() {
        return (FxtranFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isLoaded = false;

    /**
     * Laods the package and any sub-packages from their serialized form.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void loadPackage() {
        if (isLoaded) return;
        isLoaded = true;

        URL url = getClass().getResource(packageFilename);
        if (url == null) {
            throw new RuntimeException("Missing serialized package: " + packageFilename);
        }
        URI uri = URI.createURI(url.toString());
        Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
        try {
            resource.load(null);
        }
        catch (IOException exception) {
            throw new WrappedException(exception);
        }
        initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
        createResource(eNS_URI);
    }


    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isFixed = false;

    /**
     * Fixes up the loaded package, to make it appear as if it had been programmatically built.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void fixPackageContents() {
        if (isFixed) return;
        isFixed = true;
        fixEClassifiers();
    }

    /**
     * Sets the instance class on the given classifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void fixInstanceClass(EClassifier eClassifier) {
        if (eClassifier.getInstanceClassName() == null) {
            eClassifier.setInstanceClassName("org.oceandsl.tools.sar.fxtran." + eClassifier.getName());
            setGeneratedClassName(eClassifier);
        }
    }

} //FxtranPackageImpl
