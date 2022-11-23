/**
 */
package org.oceandsl.tools.sar.fxtran.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.oceandsl.tools.sar.fxtran.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.oceandsl.tools.sar.fxtran.FxtranPackage
 * @generated
 */
public class FxtranSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static FxtranPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FxtranSwitch() {
        if (modelPackage == null) {
            modelPackage = FxtranPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case FxtranPackage.ACTION_STMT_TYPE: {
                ActionStmtType actionStmtType = (ActionStmtType)theEObject;
                T result = caseActionStmtType(actionStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.AC_VALUE_LT_TYPE: {
                AcValueLTType acValueLTType = (AcValueLTType)theEObject;
                T result = caseAcValueLTType(acValueLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.AC_VALUE_TYPE: {
                AcValueType acValueType = (AcValueType)theEObject;
                T result = caseAcValueType(acValueType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ALLOCATE_STMT_TYPE: {
                AllocateStmtType allocateStmtType = (AllocateStmtType)theEObject;
                T result = caseAllocateStmtType(allocateStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARG_NTYPE: {
                ArgNType argNType = (ArgNType)theEObject;
                T result = caseArgNType(argNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARG_SPEC_TYPE: {
                ArgSpecType argSpecType = (ArgSpecType)theEObject;
                T result = caseArgSpecType(argSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARG_TYPE: {
                ArgType argType = (ArgType)theEObject;
                T result = caseArgType(argType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE: {
                ArrayConstructorEType arrayConstructorEType = (ArrayConstructorEType)theEObject;
                T result = caseArrayConstructorEType(arrayConstructorEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARRAY_RTYPE: {
                ArrayRType arrayRType = (ArrayRType)theEObject;
                T result = caseArrayRType(arrayRType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ARRAY_SPEC_TYPE: {
                ArraySpecType arraySpecType = (ArraySpecType)theEObject;
                T result = caseArraySpecType(arraySpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ASTMT_TYPE: {
                AStmtType aStmtType = (AStmtType)theEObject;
                T result = caseAStmtType(aStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ATTRIBUTE_TYPE: {
                AttributeType attributeType = (AttributeType)theEObject;
                T result = caseAttributeType(attributeType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CALL_STMT_TYPE: {
                CallStmtType callStmtType = (CallStmtType)theEObject;
                T result = caseCallStmtType(callStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_ETYPE: {
                CaseEType caseEType = (CaseEType)theEObject;
                T result = caseCaseEType(caseEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_SELECTOR_TYPE: {
                CaseSelectorType caseSelectorType = (CaseSelectorType)theEObject;
                T result = caseCaseSelectorType(caseSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_STMT_TYPE: {
                CaseStmtType caseStmtType = (CaseStmtType)theEObject;
                T result = caseCaseStmtType(caseStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_VALUE_RANGE_LT_TYPE: {
                CaseValueRangeLTType caseValueRangeLTType = (CaseValueRangeLTType)theEObject;
                T result = caseCaseValueRangeLTType(caseValueRangeLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_VALUE_RANGE_TYPE: {
                CaseValueRangeType caseValueRangeType = (CaseValueRangeType)theEObject;
                T result = caseCaseValueRangeType(caseValueRangeType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CASE_VALUE_TYPE: {
                CaseValueType caseValueType = (CaseValueType)theEObject;
                T result = caseCaseValueType(caseValueType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CHAR_SELECTOR_TYPE: {
                CharSelectorType charSelectorType = (CharSelectorType)theEObject;
                T result = caseCharSelectorType(charSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CHAR_SPEC_TYPE: {
                CharSpecType charSpecType = (CharSpecType)theEObject;
                T result = caseCharSpecType(charSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CLOSE_SPEC_SPEC_TYPE: {
                CloseSpecSpecType closeSpecSpecType = (CloseSpecSpecType)theEObject;
                T result = caseCloseSpecSpecType(closeSpecSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CLOSE_SPEC_TYPE: {
                CloseSpecType closeSpecType = (CloseSpecType)theEObject;
                T result = caseCloseSpecType(closeSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CLOSE_STMT_TYPE: {
                CloseStmtType closeStmtType = (CloseStmtType)theEObject;
                T result = caseCloseStmtType(closeStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.COMPONENT_DECL_STMT_TYPE: {
                ComponentDeclStmtType componentDeclStmtType = (ComponentDeclStmtType)theEObject;
                T result = caseComponentDeclStmtType(componentDeclStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.COMPONENT_RTYPE: {
                ComponentRType componentRType = (ComponentRType)theEObject;
                T result = caseComponentRType(componentRType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CONDITION_ETYPE: {
                ConditionEType conditionEType = (ConditionEType)theEObject;
                T result = caseConditionEType(conditionEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CONNECT_SPEC_SPEC_TYPE: {
                ConnectSpecSpecType connectSpecSpecType = (ConnectSpecSpecType)theEObject;
                T result = caseConnectSpecSpecType(connectSpecSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CONNECT_SPEC_TYPE: {
                ConnectSpecType connectSpecType = (ConnectSpecType)theEObject;
                T result = caseConnectSpecType(connectSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.CYCLE_STMT_TYPE: {
                CycleStmtType cycleStmtType = (CycleStmtType)theEObject;
                T result = caseCycleStmtType(cycleStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DEALLOCATE_STMT_TYPE: {
                DeallocateStmtType deallocateStmtType = (DeallocateStmtType)theEObject;
                T result = caseDeallocateStmtType(deallocateStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DERIVED_TSPEC_TYPE: {
                DerivedTSpecType derivedTSpecType = (DerivedTSpecType)theEObject;
                T result = caseDerivedTSpecType(derivedTSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DO_STMT_TYPE: {
                DoStmtType doStmtType = (DoStmtType)theEObject;
                T result = caseDoStmtType(doStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DO_VTYPE: {
                DoVType doVType = (DoVType)theEObject;
                T result = caseDoVType(doVType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.DUMMY_ARG_LT_TYPE: {
                DummyArgLTType dummyArgLTType = (DummyArgLTType)theEObject;
                T result = caseDummyArgLTType(dummyArgLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.E1_TYPE: {
                E1Type e1Type = (E1Type)theEObject;
                T result = caseE1Type(e1Type);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.E2_TYPE: {
                E2Type e2Type = (E2Type)theEObject;
                T result = caseE2Type(e2Type);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ELEMENT_LT_TYPE: {
                ElementLTType elementLTType = (ElementLTType)theEObject;
                T result = caseElementLTType(elementLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ELEMENT_TYPE: {
                ElementType elementType = (ElementType)theEObject;
                T result = caseElementType(elementType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ELSE_IF_STMT_TYPE: {
                ElseIfStmtType elseIfStmtType = (ElseIfStmtType)theEObject;
                T result = caseElseIfStmtType(elseIfStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_DO_STMT_TYPE: {
                EndDoStmtType endDoStmtType = (EndDoStmtType)theEObject;
                T result = caseEndDoStmtType(endDoStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.EN_DECL_LT_TYPE: {
                ENDeclLTType enDeclLTType = (ENDeclLTType)theEObject;
                T result = caseENDeclLTType(enDeclLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.EN_DECL_TYPE: {
                ENDeclType enDeclType = (ENDeclType)theEObject;
                T result = caseENDeclType(enDeclType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_FORALL_STMT_TYPE: {
                EndForallStmtType endForallStmtType = (EndForallStmtType)theEObject;
                T result = caseEndForallStmtType(endForallStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_FUNCTION_STMT_TYPE: {
                EndFunctionStmtType endFunctionStmtType = (EndFunctionStmtType)theEObject;
                T result = caseEndFunctionStmtType(endFunctionStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_INTERFACE_STMT_TYPE: {
                EndInterfaceStmtType endInterfaceStmtType = (EndInterfaceStmtType)theEObject;
                T result = caseEndInterfaceStmtType(endInterfaceStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_MODULE_STMT_TYPE: {
                EndModuleStmtType endModuleStmtType = (EndModuleStmtType)theEObject;
                T result = caseEndModuleStmtType(endModuleStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_PROGRAM_STMT_TYPE: {
                EndProgramStmtType endProgramStmtType = (EndProgramStmtType)theEObject;
                T result = caseEndProgramStmtType(endProgramStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_SELECT_CASE_STMT_TYPE: {
                EndSelectCaseStmtType endSelectCaseStmtType = (EndSelectCaseStmtType)theEObject;
                T result = caseEndSelectCaseStmtType(endSelectCaseStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_SUBROUTINE_STMT_TYPE: {
                EndSubroutineStmtType endSubroutineStmtType = (EndSubroutineStmtType)theEObject;
                T result = caseEndSubroutineStmtType(endSubroutineStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.END_TSTMT_TYPE: {
                EndTStmtType endTStmtType = (EndTStmtType)theEObject;
                T result = caseEndTStmtType(endTStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ENLT_TYPE: {
                ENLTType enltType = (ENLTType)theEObject;
                T result = caseENLTType(enltType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ENN_TYPE: {
                ENNType ennType = (ENNType)theEObject;
                T result = caseENNType(ennType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.EN_TYPE: {
                ENType enType = (ENType)theEObject;
                T result = caseENType(enType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ERROR_TYPE: {
                ErrorType errorType = (ErrorType)theEObject;
                T result = caseErrorType(errorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FILE_TYPE: {
                FileType fileType = (FileType)theEObject;
                T result = caseFileType(fileType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FORALL_CONSTRUCT_STMT_TYPE: {
                ForallConstructStmtType forallConstructStmtType = (ForallConstructStmtType)theEObject;
                T result = caseForallConstructStmtType(forallConstructStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FORALL_STMT_TYPE: {
                ForallStmtType forallStmtType = (ForallStmtType)theEObject;
                T result = caseForallStmtType(forallStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FORALL_TRIPLET_SPEC_LT_TYPE: {
                ForallTripletSpecLTType forallTripletSpecLTType = (ForallTripletSpecLTType)theEObject;
                T result = caseForallTripletSpecLTType(forallTripletSpecLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FORALL_TRIPLET_SPEC_TYPE: {
                ForallTripletSpecType forallTripletSpecType = (ForallTripletSpecType)theEObject;
                T result = caseForallTripletSpecType(forallTripletSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FUNCTION_NTYPE: {
                FunctionNType functionNType = (FunctionNType)theEObject;
                T result = caseFunctionNType(functionNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.FUNCTION_STMT_TYPE: {
                FunctionStmtType functionStmtType = (FunctionStmtType)theEObject;
                T result = caseFunctionStmtType(functionStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.IF_STMT_TYPE: {
                IfStmtType ifStmtType = (IfStmtType)theEObject;
                T result = caseIfStmtType(ifStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.IF_THEN_STMT_TYPE: {
                IfThenStmtType ifThenStmtType = (IfThenStmtType)theEObject;
                T result = caseIfThenStmtType(ifThenStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INIT_ETYPE: {
                InitEType initEType = (InitEType)theEObject;
                T result = caseInitEType(initEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INQUIRE_STMT_TYPE: {
                InquireStmtType inquireStmtType = (InquireStmtType)theEObject;
                T result = caseInquireStmtType(inquireStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INQUIRY_SPEC_SPEC_TYPE: {
                InquirySpecSpecType inquirySpecSpecType = (InquirySpecSpecType)theEObject;
                T result = caseInquirySpecSpecType(inquirySpecSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INQUIRY_SPEC_TYPE: {
                InquirySpecType inquirySpecType = (InquirySpecType)theEObject;
                T result = caseInquirySpecType(inquirySpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INTERFACE_STMT_TYPE: {
                InterfaceStmtType interfaceStmtType = (InterfaceStmtType)theEObject;
                T result = caseInterfaceStmtType(interfaceStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.INTRINSIC_TSPEC_TYPE: {
                IntrinsicTSpecType intrinsicTSpecType = (IntrinsicTSpecType)theEObject;
                T result = caseIntrinsicTSpecType(intrinsicTSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.IO_CONTROL_SPEC_TYPE: {
                IoControlSpecType ioControlSpecType = (IoControlSpecType)theEObject;
                T result = caseIoControlSpecType(ioControlSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.IO_CONTROL_TYPE: {
                IoControlType ioControlType = (IoControlType)theEObject;
                T result = caseIoControlType(ioControlType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ITERATOR_DEFINITION_LT_TYPE: {
                IteratorDefinitionLTType iteratorDefinitionLTType = (IteratorDefinitionLTType)theEObject;
                T result = caseIteratorDefinitionLTType(iteratorDefinitionLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ITERATOR_ELEMENT_TYPE: {
                IteratorElementType iteratorElementType = (IteratorElementType)theEObject;
                T result = caseIteratorElementType(iteratorElementType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.ITERATOR_TYPE: {
                IteratorType iteratorType = (IteratorType)theEObject;
                T result = caseIteratorType(iteratorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.KSELECTOR_TYPE: {
                KSelectorType kSelectorType = (KSelectorType)theEObject;
                T result = caseKSelectorType(kSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.KSPEC_TYPE: {
                KSpecType kSpecType = (KSpecType)theEObject;
                T result = caseKSpecType(kSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.LABEL_TYPE: {
                LabelType labelType = (LabelType)theEObject;
                T result = caseLabelType(labelType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.LITERAL_ETYPE: {
                LiteralEType literalEType = (LiteralEType)theEObject;
                T result = caseLiteralEType(literalEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.LOWER_BOUND_TYPE: {
                LowerBoundType lowerBoundType = (LowerBoundType)theEObject;
                T result = caseLowerBoundType(lowerBoundType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.MASK_ETYPE: {
                MaskEType maskEType = (MaskEType)theEObject;
                T result = caseMaskEType(maskEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.MODULE_NTYPE: {
                ModuleNType moduleNType = (ModuleNType)theEObject;
                T result = caseModuleNType(moduleNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.MODULE_PROCEDURE_NLT_TYPE: {
                ModuleProcedureNLTType moduleProcedureNLTType = (ModuleProcedureNLTType)theEObject;
                T result = caseModuleProcedureNLTType(moduleProcedureNLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.MODULE_STMT_TYPE: {
                ModuleStmtType moduleStmtType = (ModuleStmtType)theEObject;
                T result = caseModuleStmtType(moduleStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMED_ETYPE: {
                NamedEType namedEType = (NamedEType)theEObject;
                T result = caseNamedEType(namedEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMELIST_GROUP_NTYPE: {
                NamelistGroupNType namelistGroupNType = (NamelistGroupNType)theEObject;
                T result = caseNamelistGroupNType(namelistGroupNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE: {
                NamelistGroupObjLTType namelistGroupObjLTType = (NamelistGroupObjLTType)theEObject;
                T result = caseNamelistGroupObjLTType(namelistGroupObjLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMELIST_GROUP_OBJ_NTYPE: {
                NamelistGroupObjNType namelistGroupObjNType = (NamelistGroupObjNType)theEObject;
                T result = caseNamelistGroupObjNType(namelistGroupObjNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE: {
                NamelistGroupObjType namelistGroupObjType = (NamelistGroupObjType)theEObject;
                T result = caseNamelistGroupObjType(namelistGroupObjType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NAMELIST_STMT_TYPE: {
                NamelistStmtType namelistStmtType = (NamelistStmtType)theEObject;
                T result = caseNamelistStmtType(namelistStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NTYPE: {
                NType nType = (NType)theEObject;
                T result = caseNType(nType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.NULLIFY_STMT_TYPE: {
                NullifyStmtType nullifyStmtType = (NullifyStmtType)theEObject;
                T result = caseNullifyStmtType(nullifyStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OBJECT_TYPE: {
                ObjectType objectType = (ObjectType)theEObject;
                T result = caseObjectType(objectType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OPEN_STMT_TYPE: {
                OpenStmtType openStmtType = (OpenStmtType)theEObject;
                T result = caseOpenStmtType(openStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OP_ETYPE: {
                OpEType opEType = (OpEType)theEObject;
                T result = caseOpEType(opEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OP_TYPE: {
                OpType opType = (OpType)theEObject;
                T result = caseOpType(opType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OUTPUT_ITEM_LT_TYPE: {
                OutputItemLTType outputItemLTType = (OutputItemLTType)theEObject;
                T result = caseOutputItemLTType(outputItemLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.OUTPUT_ITEM_TYPE: {
                OutputItemType outputItemType = (OutputItemType)theEObject;
                T result = caseOutputItemType(outputItemType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PARENS_ETYPE: {
                ParensEType parensEType = (ParensEType)theEObject;
                T result = caseParensEType(parensEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PARENS_RTYPE: {
                ParensRType parensRType = (ParensRType)theEObject;
                T result = caseParensRType(parensRType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.POINTER_ASTMT_TYPE: {
                PointerAStmtType pointerAStmtType = (PointerAStmtType)theEObject;
                T result = casePointerAStmtType(pointerAStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.POINTER_STMT_TYPE: {
                PointerStmtType pointerStmtType = (PointerStmtType)theEObject;
                T result = casePointerStmtType(pointerStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PROCEDURE_DESIGNATOR_TYPE: {
                ProcedureDesignatorType procedureDesignatorType = (ProcedureDesignatorType)theEObject;
                T result = caseProcedureDesignatorType(procedureDesignatorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PROCEDURE_STMT_TYPE: {
                ProcedureStmtType procedureStmtType = (ProcedureStmtType)theEObject;
                T result = caseProcedureStmtType(procedureStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PROGRAM_NTYPE: {
                ProgramNType programNType = (ProgramNType)theEObject;
                T result = caseProgramNType(programNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PROGRAM_STMT_TYPE: {
                ProgramStmtType programStmtType = (ProgramStmtType)theEObject;
                T result = caseProgramStmtType(programStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.PUBLIC_STMT_TYPE: {
                PublicStmtType publicStmtType = (PublicStmtType)theEObject;
                T result = casePublicStmtType(publicStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.READ_STMT_TYPE: {
                ReadStmtType readStmtType = (ReadStmtType)theEObject;
                T result = caseReadStmtType(readStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.RENAME_LT_TYPE: {
                RenameLTType renameLTType = (RenameLTType)theEObject;
                T result = caseRenameLTType(renameLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.RENAME_TYPE: {
                RenameType renameType = (RenameType)theEObject;
                T result = caseRenameType(renameType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.RESULT_SPEC_TYPE: {
                ResultSpecType resultSpecType = (ResultSpecType)theEObject;
                T result = caseResultSpecType(resultSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.RLT_TYPE: {
                RLTType rltType = (RLTType)theEObject;
                T result = caseRLTType(rltType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE: {
                SectionSubscriptLTType sectionSubscriptLTType = (SectionSubscriptLTType)theEObject;
                T result = caseSectionSubscriptLTType(sectionSubscriptLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE: {
                SectionSubscriptType sectionSubscriptType = (SectionSubscriptType)theEObject;
                T result = caseSectionSubscriptType(sectionSubscriptType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SELECT_CASE_STMT_TYPE: {
                SelectCaseStmtType selectCaseStmtType = (SelectCaseStmtType)theEObject;
                T result = caseSelectCaseStmtType(selectCaseStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SHAPE_SPEC_LT_TYPE: {
                ShapeSpecLTType shapeSpecLTType = (ShapeSpecLTType)theEObject;
                T result = caseShapeSpecLTType(shapeSpecLTType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SHAPE_SPEC_TYPE: {
                ShapeSpecType shapeSpecType = (ShapeSpecType)theEObject;
                T result = caseShapeSpecType(shapeSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.STOP_STMT_TYPE: {
                StopStmtType stopStmtType = (StopStmtType)theEObject;
                T result = caseStopStmtType(stopStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.STRING_ETYPE: {
                StringEType stringEType = (StringEType)theEObject;
                T result = caseStringEType(stringEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SUBROUTINE_NTYPE: {
                SubroutineNType subroutineNType = (SubroutineNType)theEObject;
                T result = caseSubroutineNType(subroutineNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.SUBROUTINE_STMT_TYPE: {
                SubroutineStmtType subroutineStmtType = (SubroutineStmtType)theEObject;
                T result = caseSubroutineStmtType(subroutineStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.TDECL_STMT_TYPE: {
                TDeclStmtType tDeclStmtType = (TDeclStmtType)theEObject;
                T result = caseTDeclStmtType(tDeclStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.TEST_ETYPE: {
                TestEType testEType = (TestEType)theEObject;
                T result = caseTestEType(testEType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.TN_TYPE: {
                TNType tnType = (TNType)theEObject;
                T result = caseTNType(tnType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.TSPEC_TYPE: {
                TSpecType tSpecType = (TSpecType)theEObject;
                T result = caseTSpecType(tSpecType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.TSTMT_TYPE: {
                TStmtType tStmtType = (TStmtType)theEObject;
                T result = caseTStmtType(tStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.UPPER_BOUND_TYPE: {
                UpperBoundType upperBoundType = (UpperBoundType)theEObject;
                T result = caseUpperBoundType(upperBoundType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.USE_NTYPE: {
                UseNType useNType = (UseNType)theEObject;
                T result = caseUseNType(useNType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.USE_STMT_TYPE: {
                UseStmtType useStmtType = (UseStmtType)theEObject;
                T result = caseUseStmtType(useStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.VN_TYPE: {
                VNType vnType = (VNType)theEObject;
                T result = caseVNType(vnType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.VTYPE: {
                VType vType = (VType)theEObject;
                T result = caseVType(vType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.WHERE_CONSTRUCT_STMT_TYPE: {
                WhereConstructStmtType whereConstructStmtType = (WhereConstructStmtType)theEObject;
                T result = caseWhereConstructStmtType(whereConstructStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.WHERE_STMT_TYPE: {
                WhereStmtType whereStmtType = (WhereStmtType)theEObject;
                T result = caseWhereStmtType(whereStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FxtranPackage.WRITE_STMT_TYPE: {
                WriteStmtType writeStmtType = (WriteStmtType)theEObject;
                T result = caseWriteStmtType(writeStmtType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Action Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Action Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActionStmtType(ActionStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ac Value LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ac Value LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAcValueLTType(AcValueLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ac Value Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ac Value Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAcValueType(AcValueType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Allocate Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Allocate Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAllocateStmtType(AllocateStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Arg NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Arg NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArgNType(ArgNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Arg Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Arg Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArgSpecType(ArgSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Arg Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Arg Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArgType(ArgType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Array Constructor EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Array Constructor EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArrayConstructorEType(ArrayConstructorEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Array RType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Array RType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArrayRType(ArrayRType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Array Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Array Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArraySpecType(ArraySpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>AStmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>AStmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAStmtType(AStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Attribute Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Attribute Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAttributeType(AttributeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Call Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Call Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCallStmtType(CallStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseEType(CaseEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case Selector Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseSelectorType(CaseSelectorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseStmtType(CaseStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case Value Range LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case Value Range LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseValueRangeLTType(CaseValueRangeLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case Value Range Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case Value Range Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseValueRangeType(CaseValueRangeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case Value Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case Value Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCaseValueType(CaseValueType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Char Selector Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Char Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCharSelectorType(CharSelectorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Char Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Char Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCharSpecType(CharSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Close Spec Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Close Spec Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCloseSpecSpecType(CloseSpecSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Close Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Close Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCloseSpecType(CloseSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Close Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Close Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCloseStmtType(CloseStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component Decl Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component Decl Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentDeclStmtType(ComponentDeclStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component RType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component RType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentRType(ComponentRType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Condition EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Condition EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionEType(ConditionEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Connect Spec Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connect Spec Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConnectSpecSpecType(ConnectSpecSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Connect Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connect Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConnectSpecType(ConnectSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cycle Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cycle Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCycleStmtType(CycleStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Deallocate Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Deallocate Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeallocateStmtType(DeallocateStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Derived TSpec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived TSpec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDerivedTSpecType(DerivedTSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Do Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Do Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoStmtType(DoStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Do VType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Do VType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoVType(DoVType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dummy Arg LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dummy Arg LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDummyArgLTType(DummyArgLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>E1 Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>E1 Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseE1Type(E1Type object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>E2 Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>E2 Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseE2Type(E2Type object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementLTType(ElementLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementType(ElementType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Else If Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Else If Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElseIfStmtType(ElseIfStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Do Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Do Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndDoStmtType(EndDoStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EN Decl LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EN Decl LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseENDeclLTType(ENDeclLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EN Decl Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EN Decl Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseENDeclType(ENDeclType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Forall Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Forall Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndForallStmtType(EndForallStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Function Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Function Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndFunctionStmtType(EndFunctionStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Interface Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Interface Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndInterfaceStmtType(EndInterfaceStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Module Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Module Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndModuleStmtType(EndModuleStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Program Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Program Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndProgramStmtType(EndProgramStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Select Case Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Select Case Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndSelectCaseStmtType(EndSelectCaseStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Subroutine Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Subroutine Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndSubroutineStmtType(EndSubroutineStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End TStmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End TStmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndTStmtType(EndTStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>ENLT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ENLT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseENLTType(ENLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>ENN Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ENN Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseENNType(ENNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EN Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EN Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseENType(ENType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Error Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Error Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseErrorType(ErrorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>File Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFileType(FileType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Forall Construct Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Forall Construct Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForallConstructStmtType(ForallConstructStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Forall Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Forall Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForallStmtType(ForallStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Forall Triplet Spec LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Forall Triplet Spec LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForallTripletSpecLTType(ForallTripletSpecLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Forall Triplet Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Forall Triplet Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseForallTripletSpecType(ForallTripletSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionNType(FunctionNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionStmtType(FunctionStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>If Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>If Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIfStmtType(IfStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>If Then Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>If Then Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIfThenStmtType(IfThenStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Init EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Init EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitEType(InitEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Inquire Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inquire Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInquireStmtType(InquireStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Inquiry Spec Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inquiry Spec Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInquirySpecSpecType(InquirySpecSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Inquiry Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inquiry Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInquirySpecType(InquirySpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interface Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interface Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterfaceStmtType(InterfaceStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Intrinsic TSpec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intrinsic TSpec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntrinsicTSpecType(IntrinsicTSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Io Control Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Io Control Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIoControlSpecType(IoControlSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Io Control Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Io Control Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIoControlType(IoControlType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Iterator Definition LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Iterator Definition LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIteratorDefinitionLTType(IteratorDefinitionLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Iterator Element Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Iterator Element Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIteratorElementType(IteratorElementType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Iterator Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Iterator Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIteratorType(IteratorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KSelector Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KSelector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKSelectorType(KSelectorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KSpec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KSpec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKSpecType(KSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelType(LabelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLiteralEType(LiteralEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lower Bound Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lower Bound Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLowerBoundType(LowerBoundType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mask EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mask EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMaskEType(MaskEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Module NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Module NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModuleNType(ModuleNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Module Procedure NLT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Module Procedure NLT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModuleProcedureNLTType(ModuleProcedureNLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Module Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Module Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModuleStmtType(ModuleStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamedEType(NamedEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namelist Group NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namelist Group NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamelistGroupNType(NamelistGroupNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namelist Group Obj LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namelist Group Obj LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamelistGroupObjLTType(NamelistGroupObjLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namelist Group Obj NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namelist Group Obj NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamelistGroupObjNType(NamelistGroupObjNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namelist Group Obj Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namelist Group Obj Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamelistGroupObjType(NamelistGroupObjType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Namelist Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Namelist Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamelistStmtType(NamelistStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNType(NType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Nullify Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Nullify Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNullifyStmtType(NullifyStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Object Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Object Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseObjectType(ObjectType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Open Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Open Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOpenStmtType(OpenStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Op EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Op EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOpEType(OpEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Op Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Op Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOpType(OpType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Output Item LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Output Item LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutputItemLTType(OutputItemLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Output Item Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Output Item Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutputItemType(OutputItemType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parens EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parens EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParensEType(ParensEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parens RType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parens RType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParensRType(ParensRType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pointer AStmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pointer AStmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePointerAStmtType(PointerAStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pointer Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pointer Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePointerStmtType(PointerStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Procedure Designator Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Procedure Designator Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProcedureDesignatorType(ProcedureDesignatorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Procedure Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Procedure Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProcedureStmtType(ProcedureStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Program NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Program NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProgramNType(ProgramNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Program Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Program Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProgramStmtType(ProgramStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Public Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Public Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePublicStmtType(PublicStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Read Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Read Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReadStmtType(ReadStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Rename LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Rename LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRenameLTType(RenameLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Rename Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Rename Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRenameType(RenameType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Result Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResultSpecType(ResultSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RLT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RLT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRLTType(RLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Section Subscript LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Section Subscript LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSectionSubscriptLTType(SectionSubscriptLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Section Subscript Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Section Subscript Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSectionSubscriptType(SectionSubscriptType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Case Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Case Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectCaseStmtType(SelectCaseStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape Spec LT Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape Spec LT Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeSpecLTType(ShapeSpecLTType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape Spec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape Spec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeSpecType(ShapeSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stop Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stop Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStopStmtType(StopStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringEType(StringEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Subroutine NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Subroutine NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubroutineNType(SubroutineNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Subroutine Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Subroutine Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubroutineStmtType(SubroutineStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TDecl Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TDecl Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTDeclStmtType(TDeclStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Test EType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Test EType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTestEType(TestEType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TN Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TN Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTNType(TNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TSpec Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TSpec Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTSpecType(TSpecType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TStmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TStmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTStmtType(TStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Upper Bound Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Upper Bound Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUpperBoundType(UpperBoundType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Use NType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Use NType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUseNType(UseNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Use Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Use Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUseStmtType(UseStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>VN Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>VN Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVNType(VNType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>VType</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>VType</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVType(VType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Where Construct Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Where Construct Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWhereConstructStmtType(WhereConstructStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Where Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Where Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWhereStmtType(WhereStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Write Stmt Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Write Stmt Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWriteStmtType(WriteStmtType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //FxtranSwitch
