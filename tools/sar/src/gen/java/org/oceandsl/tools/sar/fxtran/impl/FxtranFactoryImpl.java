/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.oceandsl.tools.sar.fxtran.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FxtranFactoryImpl extends EFactoryImpl implements FxtranFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FxtranFactory init() {
        try {
            FxtranFactory theFxtranFactory = (FxtranFactory)EPackage.Registry.INSTANCE.getEFactory(FxtranPackage.eNS_URI);
            if (theFxtranFactory != null) {
                return theFxtranFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FxtranFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FxtranFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case FxtranPackage.ACTION_STMT_TYPE: return createActionStmtType();
            case FxtranPackage.AC_VALUE_LT_TYPE: return createAcValueLTType();
            case FxtranPackage.AC_VALUE_TYPE: return createAcValueType();
            case FxtranPackage.ALLOCATE_STMT_TYPE: return createAllocateStmtType();
            case FxtranPackage.ARG_NTYPE: return createArgNType();
            case FxtranPackage.ARG_SPEC_TYPE: return createArgSpecType();
            case FxtranPackage.ARG_TYPE: return createArgType();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE: return createArrayConstructorEType();
            case FxtranPackage.ARRAY_RTYPE: return createArrayRType();
            case FxtranPackage.ARRAY_SPEC_TYPE: return createArraySpecType();
            case FxtranPackage.ASTMT_TYPE: return createAStmtType();
            case FxtranPackage.ATTRIBUTE_TYPE: return createAttributeType();
            case FxtranPackage.CALL_STMT_TYPE: return createCallStmtType();
            case FxtranPackage.CASE_ETYPE: return createCaseEType();
            case FxtranPackage.CASE_SELECTOR_TYPE: return createCaseSelectorType();
            case FxtranPackage.CASE_STMT_TYPE: return createCaseStmtType();
            case FxtranPackage.CASE_VALUE_RANGE_LT_TYPE: return createCaseValueRangeLTType();
            case FxtranPackage.CASE_VALUE_RANGE_TYPE: return createCaseValueRangeType();
            case FxtranPackage.CASE_VALUE_TYPE: return createCaseValueType();
            case FxtranPackage.CHAR_SELECTOR_TYPE: return createCharSelectorType();
            case FxtranPackage.CHAR_SPEC_TYPE: return createCharSpecType();
            case FxtranPackage.CLOSE_SPEC_SPEC_TYPE: return createCloseSpecSpecType();
            case FxtranPackage.CLOSE_SPEC_TYPE: return createCloseSpecType();
            case FxtranPackage.CLOSE_STMT_TYPE: return createCloseStmtType();
            case FxtranPackage.COMPONENT_DECL_STMT_TYPE: return createComponentDeclStmtType();
            case FxtranPackage.COMPONENT_RTYPE: return createComponentRType();
            case FxtranPackage.CONDITION_ETYPE: return createConditionEType();
            case FxtranPackage.CONNECT_SPEC_SPEC_TYPE: return createConnectSpecSpecType();
            case FxtranPackage.CONNECT_SPEC_TYPE: return createConnectSpecType();
            case FxtranPackage.CYCLE_STMT_TYPE: return createCycleStmtType();
            case FxtranPackage.DEALLOCATE_STMT_TYPE: return createDeallocateStmtType();
            case FxtranPackage.DERIVED_TSPEC_TYPE: return createDerivedTSpecType();
            case FxtranPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case FxtranPackage.DO_STMT_TYPE: return createDoStmtType();
            case FxtranPackage.DO_VTYPE: return createDoVType();
            case FxtranPackage.DUMMY_ARG_LT_TYPE: return createDummyArgLTType();
            case FxtranPackage.E1_TYPE: return createE1Type();
            case FxtranPackage.E2_TYPE: return createE2Type();
            case FxtranPackage.ELEMENT_LT_TYPE: return createElementLTType();
            case FxtranPackage.ELEMENT_TYPE: return createElementType();
            case FxtranPackage.ELSE_IF_STMT_TYPE: return createElseIfStmtType();
            case FxtranPackage.END_DO_STMT_TYPE: return createEndDoStmtType();
            case FxtranPackage.EN_DECL_LT_TYPE: return createENDeclLTType();
            case FxtranPackage.EN_DECL_TYPE: return createENDeclType();
            case FxtranPackage.END_FORALL_STMT_TYPE: return createEndForallStmtType();
            case FxtranPackage.END_FUNCTION_STMT_TYPE: return createEndFunctionStmtType();
            case FxtranPackage.END_INTERFACE_STMT_TYPE: return createEndInterfaceStmtType();
            case FxtranPackage.END_MODULE_STMT_TYPE: return createEndModuleStmtType();
            case FxtranPackage.END_PROGRAM_STMT_TYPE: return createEndProgramStmtType();
            case FxtranPackage.END_SELECT_CASE_STMT_TYPE: return createEndSelectCaseStmtType();
            case FxtranPackage.END_SUBROUTINE_STMT_TYPE: return createEndSubroutineStmtType();
            case FxtranPackage.END_TSTMT_TYPE: return createEndTStmtType();
            case FxtranPackage.ENLT_TYPE: return createENLTType();
            case FxtranPackage.ENN_TYPE: return createENNType();
            case FxtranPackage.EN_TYPE: return createENType();
            case FxtranPackage.ERROR_TYPE: return createErrorType();
            case FxtranPackage.FILE_TYPE: return createFileType();
            case FxtranPackage.FORALL_CONSTRUCT_STMT_TYPE: return createForallConstructStmtType();
            case FxtranPackage.FORALL_STMT_TYPE: return createForallStmtType();
            case FxtranPackage.FORALL_TRIPLET_SPEC_LT_TYPE: return createForallTripletSpecLTType();
            case FxtranPackage.FORALL_TRIPLET_SPEC_TYPE: return createForallTripletSpecType();
            case FxtranPackage.FUNCTION_NTYPE: return createFunctionNType();
            case FxtranPackage.FUNCTION_STMT_TYPE: return createFunctionStmtType();
            case FxtranPackage.IF_STMT_TYPE: return createIfStmtType();
            case FxtranPackage.IF_THEN_STMT_TYPE: return createIfThenStmtType();
            case FxtranPackage.INIT_ETYPE: return createInitEType();
            case FxtranPackage.INQUIRE_STMT_TYPE: return createInquireStmtType();
            case FxtranPackage.INQUIRY_SPEC_SPEC_TYPE: return createInquirySpecSpecType();
            case FxtranPackage.INQUIRY_SPEC_TYPE: return createInquirySpecType();
            case FxtranPackage.INTERFACE_STMT_TYPE: return createInterfaceStmtType();
            case FxtranPackage.INTRINSIC_TSPEC_TYPE: return createIntrinsicTSpecType();
            case FxtranPackage.IO_CONTROL_SPEC_TYPE: return createIoControlSpecType();
            case FxtranPackage.IO_CONTROL_TYPE: return createIoControlType();
            case FxtranPackage.ITERATOR_DEFINITION_LT_TYPE: return createIteratorDefinitionLTType();
            case FxtranPackage.ITERATOR_ELEMENT_TYPE: return createIteratorElementType();
            case FxtranPackage.ITERATOR_TYPE: return createIteratorType();
            case FxtranPackage.KSELECTOR_TYPE: return createKSelectorType();
            case FxtranPackage.KSPEC_TYPE: return createKSpecType();
            case FxtranPackage.LABEL_TYPE: return createLabelType();
            case FxtranPackage.LITERAL_ETYPE: return createLiteralEType();
            case FxtranPackage.LOWER_BOUND_TYPE: return createLowerBoundType();
            case FxtranPackage.MASK_ETYPE: return createMaskEType();
            case FxtranPackage.MODULE_NTYPE: return createModuleNType();
            case FxtranPackage.MODULE_PROCEDURE_NLT_TYPE: return createModuleProcedureNLTType();
            case FxtranPackage.MODULE_STMT_TYPE: return createModuleStmtType();
            case FxtranPackage.NAMED_ETYPE: return createNamedEType();
            case FxtranPackage.NAMELIST_GROUP_NTYPE: return createNamelistGroupNType();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE: return createNamelistGroupObjLTType();
            case FxtranPackage.NAMELIST_GROUP_OBJ_NTYPE: return createNamelistGroupObjNType();
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE: return createNamelistGroupObjType();
            case FxtranPackage.NAMELIST_STMT_TYPE: return createNamelistStmtType();
            case FxtranPackage.NTYPE: return createNType();
            case FxtranPackage.NULLIFY_STMT_TYPE: return createNullifyStmtType();
            case FxtranPackage.OBJECT_TYPE: return createObjectType();
            case FxtranPackage.OPEN_STMT_TYPE: return createOpenStmtType();
            case FxtranPackage.OP_ETYPE: return createOpEType();
            case FxtranPackage.OP_TYPE: return createOpType();
            case FxtranPackage.OUTPUT_ITEM_LT_TYPE: return createOutputItemLTType();
            case FxtranPackage.OUTPUT_ITEM_TYPE: return createOutputItemType();
            case FxtranPackage.PARENS_ETYPE: return createParensEType();
            case FxtranPackage.PARENS_RTYPE: return createParensRType();
            case FxtranPackage.POINTER_ASTMT_TYPE: return createPointerAStmtType();
            case FxtranPackage.POINTER_STMT_TYPE: return createPointerStmtType();
            case FxtranPackage.PROCEDURE_DESIGNATOR_TYPE: return createProcedureDesignatorType();
            case FxtranPackage.PROCEDURE_STMT_TYPE: return createProcedureStmtType();
            case FxtranPackage.PROGRAM_NTYPE: return createProgramNType();
            case FxtranPackage.PROGRAM_STMT_TYPE: return createProgramStmtType();
            case FxtranPackage.PUBLIC_STMT_TYPE: return createPublicStmtType();
            case FxtranPackage.READ_STMT_TYPE: return createReadStmtType();
            case FxtranPackage.RENAME_LT_TYPE: return createRenameLTType();
            case FxtranPackage.RENAME_TYPE: return createRenameType();
            case FxtranPackage.RESULT_SPEC_TYPE: return createResultSpecType();
            case FxtranPackage.RLT_TYPE: return createRLTType();
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE: return createSectionSubscriptLTType();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE: return createSectionSubscriptType();
            case FxtranPackage.SELECT_CASE_STMT_TYPE: return createSelectCaseStmtType();
            case FxtranPackage.SHAPE_SPEC_LT_TYPE: return createShapeSpecLTType();
            case FxtranPackage.SHAPE_SPEC_TYPE: return createShapeSpecType();
            case FxtranPackage.STOP_STMT_TYPE: return createStopStmtType();
            case FxtranPackage.STRING_ETYPE: return createStringEType();
            case FxtranPackage.SUBROUTINE_NTYPE: return createSubroutineNType();
            case FxtranPackage.SUBROUTINE_STMT_TYPE: return createSubroutineStmtType();
            case FxtranPackage.TDECL_STMT_TYPE: return createTDeclStmtType();
            case FxtranPackage.TEST_ETYPE: return createTestEType();
            case FxtranPackage.TN_TYPE: return createTNType();
            case FxtranPackage.TSPEC_TYPE: return createTSpecType();
            case FxtranPackage.TSTMT_TYPE: return createTStmtType();
            case FxtranPackage.UPPER_BOUND_TYPE: return createUpperBoundType();
            case FxtranPackage.USE_NTYPE: return createUseNType();
            case FxtranPackage.USE_STMT_TYPE: return createUseStmtType();
            case FxtranPackage.VN_TYPE: return createVNType();
            case FxtranPackage.VTYPE: return createVType();
            case FxtranPackage.WHERE_CONSTRUCT_STMT_TYPE: return createWhereConstructStmtType();
            case FxtranPackage.WHERE_STMT_TYPE: return createWhereStmtType();
            case FxtranPackage.WRITE_STMT_TYPE: return createWriteStmtType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionStmtType createActionStmtType() {
        ActionStmtTypeImpl actionStmtType = new ActionStmtTypeImpl();
        return actionStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AcValueLTType createAcValueLTType() {
        AcValueLTTypeImpl acValueLTType = new AcValueLTTypeImpl();
        return acValueLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AcValueType createAcValueType() {
        AcValueTypeImpl acValueType = new AcValueTypeImpl();
        return acValueType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AllocateStmtType createAllocateStmtType() {
        AllocateStmtTypeImpl allocateStmtType = new AllocateStmtTypeImpl();
        return allocateStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgNType createArgNType() {
        ArgNTypeImpl argNType = new ArgNTypeImpl();
        return argNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgSpecType createArgSpecType() {
        ArgSpecTypeImpl argSpecType = new ArgSpecTypeImpl();
        return argSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgType createArgType() {
        ArgTypeImpl argType = new ArgTypeImpl();
        return argType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArrayConstructorEType createArrayConstructorEType() {
        ArrayConstructorETypeImpl arrayConstructorEType = new ArrayConstructorETypeImpl();
        return arrayConstructorEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArrayRType createArrayRType() {
        ArrayRTypeImpl arrayRType = new ArrayRTypeImpl();
        return arrayRType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArraySpecType createArraySpecType() {
        ArraySpecTypeImpl arraySpecType = new ArraySpecTypeImpl();
        return arraySpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AStmtType createAStmtType() {
        AStmtTypeImpl aStmtType = new AStmtTypeImpl();
        return aStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeType createAttributeType() {
        AttributeTypeImpl attributeType = new AttributeTypeImpl();
        return attributeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStmtType createCallStmtType() {
        CallStmtTypeImpl callStmtType = new CallStmtTypeImpl();
        return callStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseEType createCaseEType() {
        CaseETypeImpl caseEType = new CaseETypeImpl();
        return caseEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseSelectorType createCaseSelectorType() {
        CaseSelectorTypeImpl caseSelectorType = new CaseSelectorTypeImpl();
        return caseSelectorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseStmtType createCaseStmtType() {
        CaseStmtTypeImpl caseStmtType = new CaseStmtTypeImpl();
        return caseStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueRangeLTType createCaseValueRangeLTType() {
        CaseValueRangeLTTypeImpl caseValueRangeLTType = new CaseValueRangeLTTypeImpl();
        return caseValueRangeLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueRangeType createCaseValueRangeType() {
        CaseValueRangeTypeImpl caseValueRangeType = new CaseValueRangeTypeImpl();
        return caseValueRangeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueType createCaseValueType() {
        CaseValueTypeImpl caseValueType = new CaseValueTypeImpl();
        return caseValueType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSelectorType createCharSelectorType() {
        CharSelectorTypeImpl charSelectorType = new CharSelectorTypeImpl();
        return charSelectorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSpecType createCharSpecType() {
        CharSpecTypeImpl charSpecType = new CharSpecTypeImpl();
        return charSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseSpecSpecType createCloseSpecSpecType() {
        CloseSpecSpecTypeImpl closeSpecSpecType = new CloseSpecSpecTypeImpl();
        return closeSpecSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseSpecType createCloseSpecType() {
        CloseSpecTypeImpl closeSpecType = new CloseSpecTypeImpl();
        return closeSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseStmtType createCloseStmtType() {
        CloseStmtTypeImpl closeStmtType = new CloseStmtTypeImpl();
        return closeStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentDeclStmtType createComponentDeclStmtType() {
        ComponentDeclStmtTypeImpl componentDeclStmtType = new ComponentDeclStmtTypeImpl();
        return componentDeclStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentRType createComponentRType() {
        ComponentRTypeImpl componentRType = new ComponentRTypeImpl();
        return componentRType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConditionEType createConditionEType() {
        ConditionETypeImpl conditionEType = new ConditionETypeImpl();
        return conditionEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectSpecSpecType createConnectSpecSpecType() {
        ConnectSpecSpecTypeImpl connectSpecSpecType = new ConnectSpecSpecTypeImpl();
        return connectSpecSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectSpecType createConnectSpecType() {
        ConnectSpecTypeImpl connectSpecType = new ConnectSpecTypeImpl();
        return connectSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CycleStmtType createCycleStmtType() {
        CycleStmtTypeImpl cycleStmtType = new CycleStmtTypeImpl();
        return cycleStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DeallocateStmtType createDeallocateStmtType() {
        DeallocateStmtTypeImpl deallocateStmtType = new DeallocateStmtTypeImpl();
        return deallocateStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedTSpecType createDerivedTSpecType() {
        DerivedTSpecTypeImpl derivedTSpecType = new DerivedTSpecTypeImpl();
        return derivedTSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoStmtType createDoStmtType() {
        DoStmtTypeImpl doStmtType = new DoStmtTypeImpl();
        return doStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoVType createDoVType() {
        DoVTypeImpl doVType = new DoVTypeImpl();
        return doVType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DummyArgLTType createDummyArgLTType() {
        DummyArgLTTypeImpl dummyArgLTType = new DummyArgLTTypeImpl();
        return dummyArgLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E1Type createE1Type() {
        E1TypeImpl e1Type = new E1TypeImpl();
        return e1Type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E2Type createE2Type() {
        E2TypeImpl e2Type = new E2TypeImpl();
        return e2Type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementLTType createElementLTType() {
        ElementLTTypeImpl elementLTType = new ElementLTTypeImpl();
        return elementLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementType createElementType() {
        ElementTypeImpl elementType = new ElementTypeImpl();
        return elementType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElseIfStmtType createElseIfStmtType() {
        ElseIfStmtTypeImpl elseIfStmtType = new ElseIfStmtTypeImpl();
        return elseIfStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndDoStmtType createEndDoStmtType() {
        EndDoStmtTypeImpl endDoStmtType = new EndDoStmtTypeImpl();
        return endDoStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENDeclLTType createENDeclLTType() {
        ENDeclLTTypeImpl enDeclLTType = new ENDeclLTTypeImpl();
        return enDeclLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENDeclType createENDeclType() {
        ENDeclTypeImpl enDeclType = new ENDeclTypeImpl();
        return enDeclType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndForallStmtType createEndForallStmtType() {
        EndForallStmtTypeImpl endForallStmtType = new EndForallStmtTypeImpl();
        return endForallStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndFunctionStmtType createEndFunctionStmtType() {
        EndFunctionStmtTypeImpl endFunctionStmtType = new EndFunctionStmtTypeImpl();
        return endFunctionStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndInterfaceStmtType createEndInterfaceStmtType() {
        EndInterfaceStmtTypeImpl endInterfaceStmtType = new EndInterfaceStmtTypeImpl();
        return endInterfaceStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndModuleStmtType createEndModuleStmtType() {
        EndModuleStmtTypeImpl endModuleStmtType = new EndModuleStmtTypeImpl();
        return endModuleStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndProgramStmtType createEndProgramStmtType() {
        EndProgramStmtTypeImpl endProgramStmtType = new EndProgramStmtTypeImpl();
        return endProgramStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndSelectCaseStmtType createEndSelectCaseStmtType() {
        EndSelectCaseStmtTypeImpl endSelectCaseStmtType = new EndSelectCaseStmtTypeImpl();
        return endSelectCaseStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndSubroutineStmtType createEndSubroutineStmtType() {
        EndSubroutineStmtTypeImpl endSubroutineStmtType = new EndSubroutineStmtTypeImpl();
        return endSubroutineStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndTStmtType createEndTStmtType() {
        EndTStmtTypeImpl endTStmtType = new EndTStmtTypeImpl();
        return endTStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENLTType createENLTType() {
        ENLTTypeImpl enltType = new ENLTTypeImpl();
        return enltType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENNType createENNType() {
        ENNTypeImpl ennType = new ENNTypeImpl();
        return ennType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENType createENType() {
        ENTypeImpl enType = new ENTypeImpl();
        return enType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ErrorType createErrorType() {
        ErrorTypeImpl errorType = new ErrorTypeImpl();
        return errorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileType createFileType() {
        FileTypeImpl fileType = new FileTypeImpl();
        return fileType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallConstructStmtType createForallConstructStmtType() {
        ForallConstructStmtTypeImpl forallConstructStmtType = new ForallConstructStmtTypeImpl();
        return forallConstructStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallStmtType createForallStmtType() {
        ForallStmtTypeImpl forallStmtType = new ForallStmtTypeImpl();
        return forallStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallTripletSpecLTType createForallTripletSpecLTType() {
        ForallTripletSpecLTTypeImpl forallTripletSpecLTType = new ForallTripletSpecLTTypeImpl();
        return forallTripletSpecLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallTripletSpecType createForallTripletSpecType() {
        ForallTripletSpecTypeImpl forallTripletSpecType = new ForallTripletSpecTypeImpl();
        return forallTripletSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionNType createFunctionNType() {
        FunctionNTypeImpl functionNType = new FunctionNTypeImpl();
        return functionNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionStmtType createFunctionStmtType() {
        FunctionStmtTypeImpl functionStmtType = new FunctionStmtTypeImpl();
        return functionStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IfStmtType createIfStmtType() {
        IfStmtTypeImpl ifStmtType = new IfStmtTypeImpl();
        return ifStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IfThenStmtType createIfThenStmtType() {
        IfThenStmtTypeImpl ifThenStmtType = new IfThenStmtTypeImpl();
        return ifThenStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InitEType createInitEType() {
        InitETypeImpl initEType = new InitETypeImpl();
        return initEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquireStmtType createInquireStmtType() {
        InquireStmtTypeImpl inquireStmtType = new InquireStmtTypeImpl();
        return inquireStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquirySpecSpecType createInquirySpecSpecType() {
        InquirySpecSpecTypeImpl inquirySpecSpecType = new InquirySpecSpecTypeImpl();
        return inquirySpecSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquirySpecType createInquirySpecType() {
        InquirySpecTypeImpl inquirySpecType = new InquirySpecTypeImpl();
        return inquirySpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InterfaceStmtType createInterfaceStmtType() {
        InterfaceStmtTypeImpl interfaceStmtType = new InterfaceStmtTypeImpl();
        return interfaceStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntrinsicTSpecType createIntrinsicTSpecType() {
        IntrinsicTSpecTypeImpl intrinsicTSpecType = new IntrinsicTSpecTypeImpl();
        return intrinsicTSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IoControlSpecType createIoControlSpecType() {
        IoControlSpecTypeImpl ioControlSpecType = new IoControlSpecTypeImpl();
        return ioControlSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IoControlType createIoControlType() {
        IoControlTypeImpl ioControlType = new IoControlTypeImpl();
        return ioControlType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorDefinitionLTType createIteratorDefinitionLTType() {
        IteratorDefinitionLTTypeImpl iteratorDefinitionLTType = new IteratorDefinitionLTTypeImpl();
        return iteratorDefinitionLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorElementType createIteratorElementType() {
        IteratorElementTypeImpl iteratorElementType = new IteratorElementTypeImpl();
        return iteratorElementType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorType createIteratorType() {
        IteratorTypeImpl iteratorType = new IteratorTypeImpl();
        return iteratorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSelectorType createKSelectorType() {
        KSelectorTypeImpl kSelectorType = new KSelectorTypeImpl();
        return kSelectorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSpecType createKSpecType() {
        KSpecTypeImpl kSpecType = new KSpecTypeImpl();
        return kSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelType createLabelType() {
        LabelTypeImpl labelType = new LabelTypeImpl();
        return labelType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralEType createLiteralEType() {
        LiteralETypeImpl literalEType = new LiteralETypeImpl();
        return literalEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LowerBoundType createLowerBoundType() {
        LowerBoundTypeImpl lowerBoundType = new LowerBoundTypeImpl();
        return lowerBoundType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MaskEType createMaskEType() {
        MaskETypeImpl maskEType = new MaskETypeImpl();
        return maskEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleNType createModuleNType() {
        ModuleNTypeImpl moduleNType = new ModuleNTypeImpl();
        return moduleNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleProcedureNLTType createModuleProcedureNLTType() {
        ModuleProcedureNLTTypeImpl moduleProcedureNLTType = new ModuleProcedureNLTTypeImpl();
        return moduleProcedureNLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleStmtType createModuleStmtType() {
        ModuleStmtTypeImpl moduleStmtType = new ModuleStmtTypeImpl();
        return moduleStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamedEType createNamedEType() {
        NamedETypeImpl namedEType = new NamedETypeImpl();
        return namedEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupNType createNamelistGroupNType() {
        NamelistGroupNTypeImpl namelistGroupNType = new NamelistGroupNTypeImpl();
        return namelistGroupNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjLTType createNamelistGroupObjLTType() {
        NamelistGroupObjLTTypeImpl namelistGroupObjLTType = new NamelistGroupObjLTTypeImpl();
        return namelistGroupObjLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjNType createNamelistGroupObjNType() {
        NamelistGroupObjNTypeImpl namelistGroupObjNType = new NamelistGroupObjNTypeImpl();
        return namelistGroupObjNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjType createNamelistGroupObjType() {
        NamelistGroupObjTypeImpl namelistGroupObjType = new NamelistGroupObjTypeImpl();
        return namelistGroupObjType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistStmtType createNamelistStmtType() {
        NamelistStmtTypeImpl namelistStmtType = new NamelistStmtTypeImpl();
        return namelistStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NType createNType() {
        NTypeImpl nType = new NTypeImpl();
        return nType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NullifyStmtType createNullifyStmtType() {
        NullifyStmtTypeImpl nullifyStmtType = new NullifyStmtTypeImpl();
        return nullifyStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectType createObjectType() {
        ObjectTypeImpl objectType = new ObjectTypeImpl();
        return objectType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpenStmtType createOpenStmtType() {
        OpenStmtTypeImpl openStmtType = new OpenStmtTypeImpl();
        return openStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpEType createOpEType() {
        OpETypeImpl opEType = new OpETypeImpl();
        return opEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpType createOpType() {
        OpTypeImpl opType = new OpTypeImpl();
        return opType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputItemLTType createOutputItemLTType() {
        OutputItemLTTypeImpl outputItemLTType = new OutputItemLTTypeImpl();
        return outputItemLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputItemType createOutputItemType() {
        OutputItemTypeImpl outputItemType = new OutputItemTypeImpl();
        return outputItemType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParensEType createParensEType() {
        ParensETypeImpl parensEType = new ParensETypeImpl();
        return parensEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParensRType createParensRType() {
        ParensRTypeImpl parensRType = new ParensRTypeImpl();
        return parensRType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointerAStmtType createPointerAStmtType() {
        PointerAStmtTypeImpl pointerAStmtType = new PointerAStmtTypeImpl();
        return pointerAStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointerStmtType createPointerStmtType() {
        PointerStmtTypeImpl pointerStmtType = new PointerStmtTypeImpl();
        return pointerStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureDesignatorType createProcedureDesignatorType() {
        ProcedureDesignatorTypeImpl procedureDesignatorType = new ProcedureDesignatorTypeImpl();
        return procedureDesignatorType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureStmtType createProcedureStmtType() {
        ProcedureStmtTypeImpl procedureStmtType = new ProcedureStmtTypeImpl();
        return procedureStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProgramNType createProgramNType() {
        ProgramNTypeImpl programNType = new ProgramNTypeImpl();
        return programNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProgramStmtType createProgramStmtType() {
        ProgramStmtTypeImpl programStmtType = new ProgramStmtTypeImpl();
        return programStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublicStmtType createPublicStmtType() {
        PublicStmtTypeImpl publicStmtType = new PublicStmtTypeImpl();
        return publicStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReadStmtType createReadStmtType() {
        ReadStmtTypeImpl readStmtType = new ReadStmtTypeImpl();
        return readStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RenameLTType createRenameLTType() {
        RenameLTTypeImpl renameLTType = new RenameLTTypeImpl();
        return renameLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RenameType createRenameType() {
        RenameTypeImpl renameType = new RenameTypeImpl();
        return renameType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSpecType createResultSpecType() {
        ResultSpecTypeImpl resultSpecType = new ResultSpecTypeImpl();
        return resultSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RLTType createRLTType() {
        RLTTypeImpl rltType = new RLTTypeImpl();
        return rltType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SectionSubscriptLTType createSectionSubscriptLTType() {
        SectionSubscriptLTTypeImpl sectionSubscriptLTType = new SectionSubscriptLTTypeImpl();
        return sectionSubscriptLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SectionSubscriptType createSectionSubscriptType() {
        SectionSubscriptTypeImpl sectionSubscriptType = new SectionSubscriptTypeImpl();
        return sectionSubscriptType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SelectCaseStmtType createSelectCaseStmtType() {
        SelectCaseStmtTypeImpl selectCaseStmtType = new SelectCaseStmtTypeImpl();
        return selectCaseStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeSpecLTType createShapeSpecLTType() {
        ShapeSpecLTTypeImpl shapeSpecLTType = new ShapeSpecLTTypeImpl();
        return shapeSpecLTType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeSpecType createShapeSpecType() {
        ShapeSpecTypeImpl shapeSpecType = new ShapeSpecTypeImpl();
        return shapeSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StopStmtType createStopStmtType() {
        StopStmtTypeImpl stopStmtType = new StopStmtTypeImpl();
        return stopStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StringEType createStringEType() {
        StringETypeImpl stringEType = new StringETypeImpl();
        return stringEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubroutineNType createSubroutineNType() {
        SubroutineNTypeImpl subroutineNType = new SubroutineNTypeImpl();
        return subroutineNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubroutineStmtType createSubroutineStmtType() {
        SubroutineStmtTypeImpl subroutineStmtType = new SubroutineStmtTypeImpl();
        return subroutineStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TDeclStmtType createTDeclStmtType() {
        TDeclStmtTypeImpl tDeclStmtType = new TDeclStmtTypeImpl();
        return tDeclStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestEType createTestEType() {
        TestETypeImpl testEType = new TestETypeImpl();
        return testEType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TNType createTNType() {
        TNTypeImpl tnType = new TNTypeImpl();
        return tnType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TSpecType createTSpecType() {
        TSpecTypeImpl tSpecType = new TSpecTypeImpl();
        return tSpecType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TStmtType createTStmtType() {
        TStmtTypeImpl tStmtType = new TStmtTypeImpl();
        return tStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpperBoundType createUpperBoundType() {
        UpperBoundTypeImpl upperBoundType = new UpperBoundTypeImpl();
        return upperBoundType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseNType createUseNType() {
        UseNTypeImpl useNType = new UseNTypeImpl();
        return useNType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseStmtType createUseStmtType() {
        UseStmtTypeImpl useStmtType = new UseStmtTypeImpl();
        return useStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VNType createVNType() {
        VNTypeImpl vnType = new VNTypeImpl();
        return vnType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VType createVType() {
        VTypeImpl vType = new VTypeImpl();
        return vType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WhereConstructStmtType createWhereConstructStmtType() {
        WhereConstructStmtTypeImpl whereConstructStmtType = new WhereConstructStmtTypeImpl();
        return whereConstructStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WhereStmtType createWhereStmtType() {
        WhereStmtTypeImpl whereStmtType = new WhereStmtTypeImpl();
        return whereStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WriteStmtType createWriteStmtType() {
        WriteStmtTypeImpl writeStmtType = new WriteStmtTypeImpl();
        return writeStmtType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FxtranPackage getFxtranPackage() {
        return (FxtranPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FxtranPackage getPackage() {
        return FxtranPackage.eINSTANCE;
    }

} //FxtranFactoryImpl
