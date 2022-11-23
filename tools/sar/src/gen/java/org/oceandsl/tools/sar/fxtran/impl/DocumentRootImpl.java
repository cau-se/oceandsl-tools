/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.tools.sar.fxtran.AStmtType;
import org.oceandsl.tools.sar.fxtran.AcValueLTType;
import org.oceandsl.tools.sar.fxtran.AcValueType;
import org.oceandsl.tools.sar.fxtran.ActionStmtType;
import org.oceandsl.tools.sar.fxtran.AllocateStmtType;
import org.oceandsl.tools.sar.fxtran.ArgNType;
import org.oceandsl.tools.sar.fxtran.ArgSpecType;
import org.oceandsl.tools.sar.fxtran.ArgType;
import org.oceandsl.tools.sar.fxtran.ArrayConstructorEType;
import org.oceandsl.tools.sar.fxtran.ArrayRType;
import org.oceandsl.tools.sar.fxtran.ArraySpecType;
import org.oceandsl.tools.sar.fxtran.AttributeType;
import org.oceandsl.tools.sar.fxtran.CallStmtType;
import org.oceandsl.tools.sar.fxtran.CaseEType;
import org.oceandsl.tools.sar.fxtran.CaseSelectorType;
import org.oceandsl.tools.sar.fxtran.CaseStmtType;
import org.oceandsl.tools.sar.fxtran.CaseValueRangeLTType;
import org.oceandsl.tools.sar.fxtran.CaseValueRangeType;
import org.oceandsl.tools.sar.fxtran.CaseValueType;
import org.oceandsl.tools.sar.fxtran.CharSelectorType;
import org.oceandsl.tools.sar.fxtran.CharSpecType;
import org.oceandsl.tools.sar.fxtran.CloseSpecSpecType;
import org.oceandsl.tools.sar.fxtran.CloseSpecType;
import org.oceandsl.tools.sar.fxtran.CloseStmtType;
import org.oceandsl.tools.sar.fxtran.ComponentDeclStmtType;
import org.oceandsl.tools.sar.fxtran.ComponentRType;
import org.oceandsl.tools.sar.fxtran.ConditionEType;
import org.oceandsl.tools.sar.fxtran.ConnectSpecSpecType;
import org.oceandsl.tools.sar.fxtran.ConnectSpecType;
import org.oceandsl.tools.sar.fxtran.CycleStmtType;
import org.oceandsl.tools.sar.fxtran.DeallocateStmtType;
import org.oceandsl.tools.sar.fxtran.DerivedTSpecType;
import org.oceandsl.tools.sar.fxtran.DoStmtType;
import org.oceandsl.tools.sar.fxtran.DoVType;
import org.oceandsl.tools.sar.fxtran.DocumentRoot;
import org.oceandsl.tools.sar.fxtran.DummyArgLTType;
import org.oceandsl.tools.sar.fxtran.E1Type;
import org.oceandsl.tools.sar.fxtran.E2Type;
import org.oceandsl.tools.sar.fxtran.ENDeclLTType;
import org.oceandsl.tools.sar.fxtran.ENDeclType;
import org.oceandsl.tools.sar.fxtran.ENLTType;
import org.oceandsl.tools.sar.fxtran.ENNType;
import org.oceandsl.tools.sar.fxtran.ENType;
import org.oceandsl.tools.sar.fxtran.ElementLTType;
import org.oceandsl.tools.sar.fxtran.ElementType;
import org.oceandsl.tools.sar.fxtran.ElseIfStmtType;
import org.oceandsl.tools.sar.fxtran.EndDoStmtType;
import org.oceandsl.tools.sar.fxtran.EndForallStmtType;
import org.oceandsl.tools.sar.fxtran.EndFunctionStmtType;
import org.oceandsl.tools.sar.fxtran.EndInterfaceStmtType;
import org.oceandsl.tools.sar.fxtran.EndModuleStmtType;
import org.oceandsl.tools.sar.fxtran.EndProgramStmtType;
import org.oceandsl.tools.sar.fxtran.EndSelectCaseStmtType;
import org.oceandsl.tools.sar.fxtran.EndSubroutineStmtType;
import org.oceandsl.tools.sar.fxtran.EndTStmtType;
import org.oceandsl.tools.sar.fxtran.ErrorType;
import org.oceandsl.tools.sar.fxtran.FileType;
import org.oceandsl.tools.sar.fxtran.ForallConstructStmtType;
import org.oceandsl.tools.sar.fxtran.ForallStmtType;
import org.oceandsl.tools.sar.fxtran.ForallTripletSpecLTType;
import org.oceandsl.tools.sar.fxtran.ForallTripletSpecType;
import org.oceandsl.tools.sar.fxtran.FunctionNType;
import org.oceandsl.tools.sar.fxtran.FunctionStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IfStmtType;
import org.oceandsl.tools.sar.fxtran.IfThenStmtType;
import org.oceandsl.tools.sar.fxtran.InitEType;
import org.oceandsl.tools.sar.fxtran.InquireStmtType;
import org.oceandsl.tools.sar.fxtran.InquirySpecSpecType;
import org.oceandsl.tools.sar.fxtran.InquirySpecType;
import org.oceandsl.tools.sar.fxtran.InterfaceStmtType;
import org.oceandsl.tools.sar.fxtran.IntrinsicTSpecType;
import org.oceandsl.tools.sar.fxtran.IoControlSpecType;
import org.oceandsl.tools.sar.fxtran.IoControlType;
import org.oceandsl.tools.sar.fxtran.IteratorDefinitionLTType;
import org.oceandsl.tools.sar.fxtran.IteratorElementType;
import org.oceandsl.tools.sar.fxtran.IteratorType;
import org.oceandsl.tools.sar.fxtran.KSelectorType;
import org.oceandsl.tools.sar.fxtran.KSpecType;
import org.oceandsl.tools.sar.fxtran.LabelType;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.LowerBoundType;
import org.oceandsl.tools.sar.fxtran.MaskEType;
import org.oceandsl.tools.sar.fxtran.ModuleNType;
import org.oceandsl.tools.sar.fxtran.ModuleProcedureNLTType;
import org.oceandsl.tools.sar.fxtran.ModuleStmtType;
import org.oceandsl.tools.sar.fxtran.NType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupNType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjLTType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjNType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjType;
import org.oceandsl.tools.sar.fxtran.NamelistStmtType;
import org.oceandsl.tools.sar.fxtran.NullifyStmtType;
import org.oceandsl.tools.sar.fxtran.ObjectType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.OpType;
import org.oceandsl.tools.sar.fxtran.OpenStmtType;
import org.oceandsl.tools.sar.fxtran.OutputItemLTType;
import org.oceandsl.tools.sar.fxtran.OutputItemType;
import org.oceandsl.tools.sar.fxtran.ParensEType;
import org.oceandsl.tools.sar.fxtran.ParensRType;
import org.oceandsl.tools.sar.fxtran.PointerAStmtType;
import org.oceandsl.tools.sar.fxtran.PointerStmtType;
import org.oceandsl.tools.sar.fxtran.ProcedureDesignatorType;
import org.oceandsl.tools.sar.fxtran.ProcedureStmtType;
import org.oceandsl.tools.sar.fxtran.ProgramNType;
import org.oceandsl.tools.sar.fxtran.ProgramStmtType;
import org.oceandsl.tools.sar.fxtran.PublicStmtType;
import org.oceandsl.tools.sar.fxtran.RLTType;
import org.oceandsl.tools.sar.fxtran.ReadStmtType;
import org.oceandsl.tools.sar.fxtran.RenameLTType;
import org.oceandsl.tools.sar.fxtran.RenameType;
import org.oceandsl.tools.sar.fxtran.ResultSpecType;
import org.oceandsl.tools.sar.fxtran.SectionSubscriptLTType;
import org.oceandsl.tools.sar.fxtran.SectionSubscriptType;
import org.oceandsl.tools.sar.fxtran.SelectCaseStmtType;
import org.oceandsl.tools.sar.fxtran.ShapeSpecLTType;
import org.oceandsl.tools.sar.fxtran.ShapeSpecType;
import org.oceandsl.tools.sar.fxtran.StopStmtType;
import org.oceandsl.tools.sar.fxtran.StringEType;
import org.oceandsl.tools.sar.fxtran.SubroutineNType;
import org.oceandsl.tools.sar.fxtran.SubroutineStmtType;
import org.oceandsl.tools.sar.fxtran.TDeclStmtType;
import org.oceandsl.tools.sar.fxtran.TNType;
import org.oceandsl.tools.sar.fxtran.TSpecType;
import org.oceandsl.tools.sar.fxtran.TStmtType;
import org.oceandsl.tools.sar.fxtran.TestEType;
import org.oceandsl.tools.sar.fxtran.UpperBoundType;
import org.oceandsl.tools.sar.fxtran.UseNType;
import org.oceandsl.tools.sar.fxtran.UseStmtType;
import org.oceandsl.tools.sar.fxtran.VNType;
import org.oceandsl.tools.sar.fxtran.VType;
import org.oceandsl.tools.sar.fxtran.WhereConstructStmtType;
import org.oceandsl.tools.sar.fxtran.WhereStmtType;
import org.oceandsl.tools.sar.fxtran.WriteStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getTSpec <em>TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getA <em>A</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAStmt <em>AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAcValue <em>Ac Value</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAcValueLT <em>Ac Value LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getActionStmt <em>Action Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAllocateStmt <em>Allocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArg <em>Arg</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArgN <em>Arg N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArgSpec <em>Arg Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArrayConstructorE <em>Array Constructor E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArrayR <em>Array R</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getArraySpec <em>Array Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getAttributeN <em>Attribute N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getC <em>C</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCallStmt <em>Call Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseE <em>Case E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseSelector <em>Case Selector</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseStmt <em>Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseValue <em>Case Value</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseValueRange <em>Case Value Range</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCaseValueRangeLT <em>Case Value Range LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCharSelector <em>Char Selector</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCharSpec <em>Char Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCloseSpec <em>Close Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCloseSpecSpec <em>Close Spec Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCloseStmt <em>Close Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getComponentDeclStmt <em>Component Decl Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getComponentR <em>Component R</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getConditionE <em>Condition E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getConnectSpec <em>Connect Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getConnectSpecSpec <em>Connect Spec Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getContainsStmt <em>Contains Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCpp <em>Cpp</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCt <em>Ct</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getCycleStmt <em>Cycle Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getDeallocateStmt <em>Deallocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getDerivedTSpec <em>Derived TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getDoStmt <em>Do Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getDoV <em>Do V</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getDummyArgLT <em>Dummy Arg LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getE1 <em>E1</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getE2 <em>E2</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getElementLT <em>Element LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getElseIfStmt <em>Else If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getElseStmt <em>Else Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getElseWhereStmt <em>Else Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEN <em>EN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getENDecl <em>EN Decl</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getENDeclLT <em>EN Decl LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getENLT <em>ENLT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getENN <em>ENN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndDoStmt <em>End Do Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndForallStmt <em>End Forall Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndFunctionStmt <em>End Function Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndIfStmt <em>End If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndInterfaceStmt <em>End Interface Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndModuleStmt <em>End Module Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndProgramStmt <em>End Program Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndSelectCaseStmt <em>End Select Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndSubroutineStmt <em>End Subroutine Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndTStmt <em>End TStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getEndWhereStmt <em>End Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getError <em>Error</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getExitStmt <em>Exit Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getForallConstructStmt <em>Forall Construct Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getForallStmt <em>Forall Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getForallTripletSpec <em>Forall Triplet Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getForallTripletSpecLT <em>Forall Triplet Spec LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getFunctionN <em>Function N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getFunctionStmt <em>Function Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIfStmt <em>If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIfThenStmt <em>If Then Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getImplicitNoneStmt <em>Implicit None Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getInitE <em>Init E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getInquireStmt <em>Inquire Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getInquirySpec <em>Inquiry Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getInquirySpecSpec <em>Inquiry Spec Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIntentSpec <em>Intent Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getInterfaceStmt <em>Interface Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIntrinsicTSpec <em>Intrinsic TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIoControl <em>Io Control</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIoControlSpec <em>Io Control Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIteratorDefinitionLT <em>Iterator Definition LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getIteratorElement <em>Iterator Element</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getK <em>K</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getKSelector <em>KSelector</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getKSpec <em>KSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getL <em>L</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getMaskE <em>Mask E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getModuleN <em>Module N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getModuleProcedureNLT <em>Module Procedure NLT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getModuleStmt <em>Module Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getN <em>N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getN1 <em>N1</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamelistGroupN <em>Namelist Group N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamelistGroupObj <em>Namelist Group Obj</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamelistGroupObjLT <em>Namelist Group Obj LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamelistGroupObjN <em>Namelist Group Obj N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNamelistStmt <em>Namelist Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getNullifyStmt <em>Nullify Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getO <em>O</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getObject <em>Object</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getOpenStmt <em>Open Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getOutputItem <em>Output Item</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getOutputItemLT <em>Output Item LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getParensE <em>Parens E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getParensR <em>Parens R</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getPointerAStmt <em>Pointer AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getPointerStmt <em>Pointer Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getPrivateStmt <em>Private Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getProcedureDesignator <em>Procedure Designator</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getProcedureStmt <em>Procedure Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getProgramN <em>Program N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getProgramStmt <em>Program Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getPublicStmt <em>Public Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getRLT <em>RLT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getReadStmt <em>Read Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getRename <em>Rename</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getRenameLT <em>Rename LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getResultSpec <em>Result Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getReturnStmt <em>Return Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getS <em>S</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSaveStmt <em>Save Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSectionSubscript <em>Section Subscript</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSectionSubscriptLT <em>Section Subscript LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSelectCaseStmt <em>Select Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getShapeSpec <em>Shape Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getShapeSpecLT <em>Shape Spec LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getStarE <em>Star E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getStopCode <em>Stop Code</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getStopStmt <em>Stop Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getStringE <em>String E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSubroutineN <em>Subroutine N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getSubroutineStmt <em>Subroutine Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getTDeclStmt <em>TDecl Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getTN <em>TN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getTStmt <em>TStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getTestE <em>Test E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getUseN <em>Use N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getUseStmt <em>Use Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getV <em>V</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getVN <em>VN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getWhereConstructStmt <em>Where Construct Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getWhereStmt <em>Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DocumentRootImpl#getWriteStmt <em>Write Stmt</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * The default value of the '{@link #getA() <em>A</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getA()
     * @generated
     * @ordered
     */
    protected static final String A_EDEFAULT = null;

    /**
     * The default value of the '{@link #getAttributeN() <em>Attribute N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeN()
     * @generated
     * @ordered
     */
    protected static final String ATTRIBUTE_N_EDEFAULT = null;

    /**
     * The default value of the '{@link #getC() <em>C</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getC()
     * @generated
     * @ordered
     */
    protected static final String C_EDEFAULT = null;

    /**
     * The default value of the '{@link #getCnt() <em>Cnt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCnt()
     * @generated
     * @ordered
     */
    protected static final String CNT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getContainsStmt() <em>Contains Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContainsStmt()
     * @generated
     * @ordered
     */
    protected static final String CONTAINS_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getCpp() <em>Cpp</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCpp()
     * @generated
     * @ordered
     */
    protected static final String CPP_EDEFAULT = null;

    /**
     * The default value of the '{@link #getCt() <em>Ct</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCt()
     * @generated
     * @ordered
     */
    protected static final String CT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getElseStmt() <em>Else Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElseStmt()
     * @generated
     * @ordered
     */
    protected static final String ELSE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getElseWhereStmt() <em>Else Where Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElseWhereStmt()
     * @generated
     * @ordered
     */
    protected static final String ELSE_WHERE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getEndIfStmt() <em>End If Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndIfStmt()
     * @generated
     * @ordered
     */
    protected static final String END_IF_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getEndWhereStmt() <em>End Where Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndWhereStmt()
     * @generated
     * @ordered
     */
    protected static final String END_WHERE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getExitStmt() <em>Exit Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExitStmt()
     * @generated
     * @ordered
     */
    protected static final String EXIT_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getImplicitNoneStmt() <em>Implicit None Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplicitNoneStmt()
     * @generated
     * @ordered
     */
    protected static final String IMPLICIT_NONE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getIntentSpec() <em>Intent Spec</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntentSpec()
     * @generated
     * @ordered
     */
    protected static final String INTENT_SPEC_EDEFAULT = null;

    /**
     * The default value of the '{@link #getK() <em>K</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getK()
     * @generated
     * @ordered
     */
    protected static final String K_EDEFAULT = null;

    /**
     * The default value of the '{@link #getL() <em>L</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getL()
     * @generated
     * @ordered
     */
    protected static final String L_EDEFAULT = null;

    /**
     * The default value of the '{@link #getN() <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected static final String N_EDEFAULT = null;

    /**
     * The default value of the '{@link #getO() <em>O</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getO()
     * @generated
     * @ordered
     */
    protected static final String O_EDEFAULT = null;

    /**
     * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrefix()
     * @generated
     * @ordered
     */
    protected static final String PREFIX_EDEFAULT = null;

    /**
     * The default value of the '{@link #getPrivateStmt() <em>Private Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrivateStmt()
     * @generated
     * @ordered
     */
    protected static final String PRIVATE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getReturnStmt() <em>Return Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnStmt()
     * @generated
     * @ordered
     */
    protected static final String RETURN_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getS() <em>S</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getS()
     * @generated
     * @ordered
     */
    protected static final String S_EDEFAULT = null;

    /**
     * The default value of the '{@link #getSaveStmt() <em>Save Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSaveStmt()
     * @generated
     * @ordered
     */
    protected static final String SAVE_STMT_EDEFAULT = null;

    /**
     * The default value of the '{@link #getStarE() <em>Star E</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStarE()
     * @generated
     * @ordered
     */
    protected static final String STAR_E_EDEFAULT = null;

    /**
     * The default value of the '{@link #getStopCode() <em>Stop Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStopCode()
     * @generated
     * @ordered
     */
    protected static final BigInteger STOP_CODE_EDEFAULT = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getDocumentRoot();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TSpecType getTSpec() {
        return (TSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_TSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTSpec(TSpecType newTSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_TSpec(), newTSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTSpec(TSpecType newTSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_TSpec(), newTSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getA() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_A(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setA(String newA) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_A(), newA);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AStmtType getAStmt() {
        return (AStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_AStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAStmt(AStmtType newAStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_AStmt(), newAStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAStmt(AStmtType newAStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_AStmt(), newAStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AcValueType getAcValue() {
        return (AcValueType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_AcValue(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAcValue(AcValueType newAcValue, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_AcValue(), newAcValue, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAcValue(AcValueType newAcValue) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_AcValue(), newAcValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AcValueLTType getAcValueLT() {
        return (AcValueLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_AcValueLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAcValueLT(AcValueLTType newAcValueLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_AcValueLT(), newAcValueLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAcValueLT(AcValueLTType newAcValueLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_AcValueLT(), newAcValueLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionStmtType getActionStmt() {
        return (ActionStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ActionStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetActionStmt(ActionStmtType newActionStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ActionStmt(), newActionStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActionStmt(ActionStmtType newActionStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ActionStmt(), newActionStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AllocateStmtType getAllocateStmt() {
        return (AllocateStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_AllocateStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAllocateStmt(AllocateStmtType newAllocateStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_AllocateStmt(), newAllocateStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAllocateStmt(AllocateStmtType newAllocateStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_AllocateStmt(), newAllocateStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgType getArg() {
        return (ArgType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Arg(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArg(ArgType newArg, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Arg(), newArg, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArg(ArgType newArg) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Arg(), newArg);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgNType getArgN() {
        return (ArgNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ArgN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArgN(ArgNType newArgN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ArgN(), newArgN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArgN(ArgNType newArgN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ArgN(), newArgN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArgSpecType getArgSpec() {
        return (ArgSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ArgSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArgSpec(ArgSpecType newArgSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ArgSpec(), newArgSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArgSpec(ArgSpecType newArgSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ArgSpec(), newArgSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArrayConstructorEType getArrayConstructorE() {
        return (ArrayConstructorEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayConstructorE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArrayConstructorE(ArrayConstructorEType newArrayConstructorE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayConstructorE(), newArrayConstructorE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArrayConstructorE(ArrayConstructorEType newArrayConstructorE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayConstructorE(), newArrayConstructorE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArrayRType getArrayR() {
        return (ArrayRType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayR(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArrayR(ArrayRType newArrayR, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayR(), newArrayR, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArrayR(ArrayRType newArrayR) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ArrayR(), newArrayR);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArraySpecType getArraySpec() {
        return (ArraySpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ArraySpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArraySpec(ArraySpecType newArraySpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ArraySpec(), newArraySpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArraySpec(ArraySpecType newArraySpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ArraySpec(), newArraySpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeType getAttribute() {
        return (AttributeType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Attribute(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttribute(AttributeType newAttribute, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Attribute(), newAttribute, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttribute(AttributeType newAttribute) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Attribute(), newAttribute);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAttributeN() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_AttributeN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttributeN(String newAttributeN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_AttributeN(), newAttributeN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getC() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_C(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setC(String newC) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_C(), newC);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStmtType getCallStmt() {
        return (CallStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CallStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallStmt(CallStmtType newCallStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CallStmt(), newCallStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallStmt(CallStmtType newCallStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CallStmt(), newCallStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseEType getCaseE() {
        return (CaseEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseE(CaseEType newCaseE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseE(), newCaseE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseE(CaseEType newCaseE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseE(), newCaseE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseSelectorType getCaseSelector() {
        return (CaseSelectorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseSelector(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseSelector(CaseSelectorType newCaseSelector, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseSelector(), newCaseSelector, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseSelector(CaseSelectorType newCaseSelector) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseSelector(), newCaseSelector);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseStmtType getCaseStmt() {
        return (CaseStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseStmt(CaseStmtType newCaseStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseStmt(), newCaseStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseStmt(CaseStmtType newCaseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseStmt(), newCaseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueType getCaseValue() {
        return (CaseValueType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValue(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseValue(CaseValueType newCaseValue, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValue(), newCaseValue, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseValue(CaseValueType newCaseValue) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValue(), newCaseValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueRangeType getCaseValueRange() {
        return (CaseValueRangeType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRange(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseValueRange(CaseValueRangeType newCaseValueRange, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRange(), newCaseValueRange, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseValueRange(CaseValueRangeType newCaseValueRange) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRange(), newCaseValueRange);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueRangeLTType getCaseValueRangeLT() {
        return (CaseValueRangeLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRangeLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseValueRangeLT(CaseValueRangeLTType newCaseValueRangeLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRangeLT(), newCaseValueRangeLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseValueRangeLT(CaseValueRangeLTType newCaseValueRangeLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CaseValueRangeLT(), newCaseValueRangeLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSelectorType getCharSelector() {
        return (CharSelectorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CharSelector(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCharSelector(CharSelectorType newCharSelector, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CharSelector(), newCharSelector, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharSelector(CharSelectorType newCharSelector) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CharSelector(), newCharSelector);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSpecType getCharSpec() {
        return (CharSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CharSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCharSpec(CharSpecType newCharSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CharSpec(), newCharSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharSpec(CharSpecType newCharSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CharSpec(), newCharSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseSpecType getCloseSpec() {
        return (CloseSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCloseSpec(CloseSpecType newCloseSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpec(), newCloseSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCloseSpec(CloseSpecType newCloseSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpec(), newCloseSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseSpecSpecType getCloseSpecSpec() {
        return (CloseSpecSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpecSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCloseSpecSpec(CloseSpecSpecType newCloseSpecSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpecSpec(), newCloseSpecSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCloseSpecSpec(CloseSpecSpecType newCloseSpecSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CloseSpecSpec(), newCloseSpecSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CloseStmtType getCloseStmt() {
        return (CloseStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CloseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCloseStmt(CloseStmtType newCloseStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CloseStmt(), newCloseStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCloseStmt(CloseStmtType newCloseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CloseStmt(), newCloseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCnt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Cnt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCnt(String newCnt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Cnt(), newCnt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentDeclStmtType getComponentDeclStmt() {
        return (ComponentDeclStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentDeclStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComponentDeclStmt(ComponentDeclStmtType newComponentDeclStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentDeclStmt(), newComponentDeclStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponentDeclStmt(ComponentDeclStmtType newComponentDeclStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentDeclStmt(), newComponentDeclStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentRType getComponentR() {
        return (ComponentRType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentR(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComponentR(ComponentRType newComponentR, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentR(), newComponentR, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponentR(ComponentRType newComponentR) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ComponentR(), newComponentR);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConditionEType getConditionE() {
        return (ConditionEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ConditionE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConditionE(ConditionEType newConditionE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ConditionE(), newConditionE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConditionE(ConditionEType newConditionE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ConditionE(), newConditionE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectSpecType getConnectSpec() {
        return (ConnectSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConnectSpec(ConnectSpecType newConnectSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpec(), newConnectSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnectSpec(ConnectSpecType newConnectSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpec(), newConnectSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectSpecSpecType getConnectSpecSpec() {
        return (ConnectSpecSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpecSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConnectSpecSpec(ConnectSpecSpecType newConnectSpecSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpecSpec(), newConnectSpecSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnectSpecSpec(ConnectSpecSpecType newConnectSpecSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ConnectSpecSpec(), newConnectSpecSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContainsStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ContainsStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContainsStmt(String newContainsStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ContainsStmt(), newContainsStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCpp() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Cpp(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCpp(String newCpp) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Cpp(), newCpp);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Ct(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCt(String newCt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Ct(), newCt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CycleStmtType getCycleStmt() {
        return (CycleStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_CycleStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCycleStmt(CycleStmtType newCycleStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_CycleStmt(), newCycleStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCycleStmt(CycleStmtType newCycleStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_CycleStmt(), newCycleStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DeallocateStmtType getDeallocateStmt() {
        return (DeallocateStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_DeallocateStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDeallocateStmt(DeallocateStmtType newDeallocateStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_DeallocateStmt(), newDeallocateStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeallocateStmt(DeallocateStmtType newDeallocateStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_DeallocateStmt(), newDeallocateStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedTSpecType getDerivedTSpec() {
        return (DerivedTSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_DerivedTSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDerivedTSpec(DerivedTSpecType newDerivedTSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_DerivedTSpec(), newDerivedTSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDerivedTSpec(DerivedTSpecType newDerivedTSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_DerivedTSpec(), newDerivedTSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoStmtType getDoStmt() {
        return (DoStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_DoStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDoStmt(DoStmtType newDoStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_DoStmt(), newDoStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDoStmt(DoStmtType newDoStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_DoStmt(), newDoStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoVType getDoV() {
        return (DoVType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_DoV(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDoV(DoVType newDoV, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_DoV(), newDoV, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDoV(DoVType newDoV) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_DoV(), newDoV);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DummyArgLTType getDummyArgLT() {
        return (DummyArgLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_DummyArgLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDummyArgLT(DummyArgLTType newDummyArgLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_DummyArgLT(), newDummyArgLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDummyArgLT(DummyArgLTType newDummyArgLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_DummyArgLT(), newDummyArgLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E1Type getE1() {
        return (E1Type)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_E1(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetE1(E1Type newE1, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_E1(), newE1, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setE1(E1Type newE1) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_E1(), newE1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E2Type getE2() {
        return (E2Type)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_E2(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetE2(E2Type newE2, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_E2(), newE2, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setE2(E2Type newE2) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_E2(), newE2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementType getElement() {
        return (ElementType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Element(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetElement(ElementType newElement, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Element(), newElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElement(ElementType newElement) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Element(), newElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementLTType getElementLT() {
        return (ElementLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ElementLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetElementLT(ElementLTType newElementLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ElementLT(), newElementLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElementLT(ElementLTType newElementLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ElementLT(), newElementLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElseIfStmtType getElseIfStmt() {
        return (ElseIfStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ElseIfStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetElseIfStmt(ElseIfStmtType newElseIfStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ElseIfStmt(), newElseIfStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElseIfStmt(ElseIfStmtType newElseIfStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ElseIfStmt(), newElseIfStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getElseStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ElseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElseStmt(String newElseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ElseStmt(), newElseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getElseWhereStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ElseWhereStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElseWhereStmt(String newElseWhereStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ElseWhereStmt(), newElseWhereStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENType getEN() {
        return (ENType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEN(ENType newEN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EN(), newEN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEN(ENType newEN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EN(), newEN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENDeclType getENDecl() {
        return (ENDeclType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ENDecl(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetENDecl(ENDeclType newENDecl, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ENDecl(), newENDecl, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setENDecl(ENDeclType newENDecl) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ENDecl(), newENDecl);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENDeclLTType getENDeclLT() {
        return (ENDeclLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ENDeclLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetENDeclLT(ENDeclLTType newENDeclLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ENDeclLT(), newENDeclLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setENDeclLT(ENDeclLTType newENDeclLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ENDeclLT(), newENDeclLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENLTType getENLT() {
        return (ENLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ENLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetENLT(ENLTType newENLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ENLT(), newENLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setENLT(ENLTType newENLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ENLT(), newENLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ENNType getENN() {
        return (ENNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ENN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetENN(ENNType newENN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ENN(), newENN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setENN(ENNType newENN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ENN(), newENN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndDoStmtType getEndDoStmt() {
        return (EndDoStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndDoStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndDoStmt(EndDoStmtType newEndDoStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndDoStmt(), newEndDoStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndDoStmt(EndDoStmtType newEndDoStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndDoStmt(), newEndDoStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndForallStmtType getEndForallStmt() {
        return (EndForallStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndForallStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndForallStmt(EndForallStmtType newEndForallStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndForallStmt(), newEndForallStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndForallStmt(EndForallStmtType newEndForallStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndForallStmt(), newEndForallStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndFunctionStmtType getEndFunctionStmt() {
        return (EndFunctionStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndFunctionStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndFunctionStmt(EndFunctionStmtType newEndFunctionStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndFunctionStmt(), newEndFunctionStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndFunctionStmt(EndFunctionStmtType newEndFunctionStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndFunctionStmt(), newEndFunctionStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEndIfStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndIfStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndIfStmt(String newEndIfStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndIfStmt(), newEndIfStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndInterfaceStmtType getEndInterfaceStmt() {
        return (EndInterfaceStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndInterfaceStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndInterfaceStmt(EndInterfaceStmtType newEndInterfaceStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndInterfaceStmt(), newEndInterfaceStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndInterfaceStmt(EndInterfaceStmtType newEndInterfaceStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndInterfaceStmt(), newEndInterfaceStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndModuleStmtType getEndModuleStmt() {
        return (EndModuleStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndModuleStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndModuleStmt(EndModuleStmtType newEndModuleStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndModuleStmt(), newEndModuleStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndModuleStmt(EndModuleStmtType newEndModuleStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndModuleStmt(), newEndModuleStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndProgramStmtType getEndProgramStmt() {
        return (EndProgramStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndProgramStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndProgramStmt(EndProgramStmtType newEndProgramStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndProgramStmt(), newEndProgramStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndProgramStmt(EndProgramStmtType newEndProgramStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndProgramStmt(), newEndProgramStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndSelectCaseStmtType getEndSelectCaseStmt() {
        return (EndSelectCaseStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndSelectCaseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndSelectCaseStmt(EndSelectCaseStmtType newEndSelectCaseStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndSelectCaseStmt(), newEndSelectCaseStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndSelectCaseStmt(EndSelectCaseStmtType newEndSelectCaseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndSelectCaseStmt(), newEndSelectCaseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndSubroutineStmtType getEndSubroutineStmt() {
        return (EndSubroutineStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndSubroutineStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndSubroutineStmt(EndSubroutineStmtType newEndSubroutineStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndSubroutineStmt(), newEndSubroutineStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndSubroutineStmt(EndSubroutineStmtType newEndSubroutineStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndSubroutineStmt(), newEndSubroutineStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndTStmtType getEndTStmt() {
        return (EndTStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndTStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndTStmt(EndTStmtType newEndTStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_EndTStmt(), newEndTStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndTStmt(EndTStmtType newEndTStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndTStmt(), newEndTStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEndWhereStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_EndWhereStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndWhereStmt(String newEndWhereStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_EndWhereStmt(), newEndWhereStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ErrorType getError() {
        return (ErrorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Error(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetError(ErrorType newError, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Error(), newError, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setError(ErrorType newError) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Error(), newError);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExitStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ExitStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExitStmt(String newExitStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ExitStmt(), newExitStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileType getFile() {
        return (FileType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_File(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFile(FileType newFile, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_File(), newFile, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFile(FileType newFile) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_File(), newFile);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallConstructStmtType getForallConstructStmt() {
        return (ForallConstructStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ForallConstructStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetForallConstructStmt(ForallConstructStmtType newForallConstructStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ForallConstructStmt(), newForallConstructStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setForallConstructStmt(ForallConstructStmtType newForallConstructStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ForallConstructStmt(), newForallConstructStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallStmtType getForallStmt() {
        return (ForallStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ForallStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetForallStmt(ForallStmtType newForallStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ForallStmt(), newForallStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setForallStmt(ForallStmtType newForallStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ForallStmt(), newForallStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallTripletSpecType getForallTripletSpec() {
        return (ForallTripletSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetForallTripletSpec(ForallTripletSpecType newForallTripletSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpec(), newForallTripletSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setForallTripletSpec(ForallTripletSpecType newForallTripletSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpec(), newForallTripletSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ForallTripletSpecLTType getForallTripletSpecLT() {
        return (ForallTripletSpecLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpecLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetForallTripletSpecLT(ForallTripletSpecLTType newForallTripletSpecLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpecLT(), newForallTripletSpecLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setForallTripletSpecLT(ForallTripletSpecLTType newForallTripletSpecLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ForallTripletSpecLT(), newForallTripletSpecLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionNType getFunctionN() {
        return (FunctionNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFunctionN(FunctionNType newFunctionN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionN(), newFunctionN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunctionN(FunctionNType newFunctionN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionN(), newFunctionN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionStmtType getFunctionStmt() {
        return (FunctionStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFunctionStmt(FunctionStmtType newFunctionStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionStmt(), newFunctionStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunctionStmt(FunctionStmtType newFunctionStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_FunctionStmt(), newFunctionStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IfStmtType getIfStmt() {
        return (IfStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IfStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIfStmt(IfStmtType newIfStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IfStmt(), newIfStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIfStmt(IfStmtType newIfStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IfStmt(), newIfStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IfThenStmtType getIfThenStmt() {
        return (IfThenStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IfThenStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIfThenStmt(IfThenStmtType newIfThenStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IfThenStmt(), newIfThenStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIfThenStmt(IfThenStmtType newIfThenStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IfThenStmt(), newIfThenStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getImplicitNoneStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ImplicitNoneStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImplicitNoneStmt(String newImplicitNoneStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ImplicitNoneStmt(), newImplicitNoneStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InitEType getInitE() {
        return (InitEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_InitE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInitE(InitEType newInitE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_InitE(), newInitE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInitE(InitEType newInitE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_InitE(), newInitE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquireStmtType getInquireStmt() {
        return (InquireStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_InquireStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInquireStmt(InquireStmtType newInquireStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_InquireStmt(), newInquireStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInquireStmt(InquireStmtType newInquireStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_InquireStmt(), newInquireStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquirySpecType getInquirySpec() {
        return (InquirySpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInquirySpec(InquirySpecType newInquirySpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpec(), newInquirySpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInquirySpec(InquirySpecType newInquirySpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpec(), newInquirySpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InquirySpecSpecType getInquirySpecSpec() {
        return (InquirySpecSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpecSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInquirySpecSpec(InquirySpecSpecType newInquirySpecSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpecSpec(), newInquirySpecSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInquirySpecSpec(InquirySpecSpecType newInquirySpecSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_InquirySpecSpec(), newInquirySpecSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIntentSpec() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IntentSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntentSpec(String newIntentSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IntentSpec(), newIntentSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InterfaceStmtType getInterfaceStmt() {
        return (InterfaceStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_InterfaceStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInterfaceStmt(InterfaceStmtType newInterfaceStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_InterfaceStmt(), newInterfaceStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInterfaceStmt(InterfaceStmtType newInterfaceStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_InterfaceStmt(), newInterfaceStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntrinsicTSpecType getIntrinsicTSpec() {
        return (IntrinsicTSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IntrinsicTSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIntrinsicTSpec(IntrinsicTSpecType newIntrinsicTSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IntrinsicTSpec(), newIntrinsicTSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntrinsicTSpec(IntrinsicTSpecType newIntrinsicTSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IntrinsicTSpec(), newIntrinsicTSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IoControlType getIoControl() {
        return (IoControlType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IoControl(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIoControl(IoControlType newIoControl, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IoControl(), newIoControl, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIoControl(IoControlType newIoControl) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IoControl(), newIoControl);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IoControlSpecType getIoControlSpec() {
        return (IoControlSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IoControlSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIoControlSpec(IoControlSpecType newIoControlSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IoControlSpec(), newIoControlSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIoControlSpec(IoControlSpecType newIoControlSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IoControlSpec(), newIoControlSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorType getIterator() {
        return (IteratorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Iterator(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIterator(IteratorType newIterator, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Iterator(), newIterator, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIterator(IteratorType newIterator) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Iterator(), newIterator);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorDefinitionLTType getIteratorDefinitionLT() {
        return (IteratorDefinitionLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorDefinitionLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIteratorDefinitionLT(IteratorDefinitionLTType newIteratorDefinitionLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorDefinitionLT(), newIteratorDefinitionLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIteratorDefinitionLT(IteratorDefinitionLTType newIteratorDefinitionLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorDefinitionLT(), newIteratorDefinitionLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IteratorElementType getIteratorElement() {
        return (IteratorElementType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorElement(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIteratorElement(IteratorElementType newIteratorElement, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorElement(), newIteratorElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIteratorElement(IteratorElementType newIteratorElement) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_IteratorElement(), newIteratorElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getK() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_K(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setK(String newK) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_K(), newK);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSelectorType getKSelector() {
        return (KSelectorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_KSelector(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKSelector(KSelectorType newKSelector, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_KSelector(), newKSelector, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKSelector(KSelectorType newKSelector) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_KSelector(), newKSelector);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSpecType getKSpec() {
        return (KSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_KSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKSpec(KSpecType newKSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_KSpec(), newKSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKSpec(KSpecType newKSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_KSpec(), newKSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getL() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_L(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setL(String newL) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_L(), newL);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelType getLabel() {
        return (LabelType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Label(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabel(LabelType newLabel, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Label(), newLabel, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(LabelType newLabel) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Label(), newLabel);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralEType getLiteralE() {
        return (LiteralEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_LiteralE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLiteralE(LiteralEType newLiteralE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_LiteralE(), newLiteralE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLiteralE(LiteralEType newLiteralE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_LiteralE(), newLiteralE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LowerBoundType getLowerBound() {
        return (LowerBoundType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_LowerBound(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLowerBound(LowerBoundType newLowerBound, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_LowerBound(), newLowerBound, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLowerBound(LowerBoundType newLowerBound) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_LowerBound(), newLowerBound);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MaskEType getMaskE() {
        return (MaskEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_MaskE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMaskE(MaskEType newMaskE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_MaskE(), newMaskE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMaskE(MaskEType newMaskE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_MaskE(), newMaskE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleNType getModuleN() {
        return (ModuleNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetModuleN(ModuleNType newModuleN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleN(), newModuleN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModuleN(ModuleNType newModuleN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleN(), newModuleN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleProcedureNLTType getModuleProcedureNLT() {
        return (ModuleProcedureNLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleProcedureNLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetModuleProcedureNLT(ModuleProcedureNLTType newModuleProcedureNLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleProcedureNLT(), newModuleProcedureNLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModuleProcedureNLT(ModuleProcedureNLTType newModuleProcedureNLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleProcedureNLT(), newModuleProcedureNLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModuleStmtType getModuleStmt() {
        return (ModuleStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetModuleStmt(ModuleStmtType newModuleStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleStmt(), newModuleStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModuleStmt(ModuleStmtType newModuleStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ModuleStmt(), newModuleStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getN() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_N(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN(String newN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_N(), newN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NType getN1() {
        return (NType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_N1(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetN1(NType newN1, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_N1(), newN1, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN1(NType newN1) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_N1(), newN1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamedEType getNamedE() {
        return (NamedEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamedE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamedE(NamedEType newNamedE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamedE(), newNamedE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamedE(NamedEType newNamedE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamedE(), newNamedE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupNType getNamelistGroupN() {
        return (NamelistGroupNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistGroupN(NamelistGroupNType newNamelistGroupN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupN(), newNamelistGroupN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistGroupN(NamelistGroupNType newNamelistGroupN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupN(), newNamelistGroupN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjType getNamelistGroupObj() {
        return (NamelistGroupObjType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObj(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistGroupObj(NamelistGroupObjType newNamelistGroupObj, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObj(), newNamelistGroupObj, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistGroupObj(NamelistGroupObjType newNamelistGroupObj) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObj(), newNamelistGroupObj);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjLTType getNamelistGroupObjLT() {
        return (NamelistGroupObjLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistGroupObjLT(NamelistGroupObjLTType newNamelistGroupObjLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjLT(), newNamelistGroupObjLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistGroupObjLT(NamelistGroupObjLTType newNamelistGroupObjLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjLT(), newNamelistGroupObjLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjNType getNamelistGroupObjN() {
        return (NamelistGroupObjNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistGroupObjN(NamelistGroupObjNType newNamelistGroupObjN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjN(), newNamelistGroupObjN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistGroupObjN(NamelistGroupObjNType newNamelistGroupObjN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistGroupObjN(), newNamelistGroupObjN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistStmtType getNamelistStmt() {
        return (NamelistStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistStmt(NamelistStmtType newNamelistStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistStmt(), newNamelistStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistStmt(NamelistStmtType newNamelistStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NamelistStmt(), newNamelistStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NullifyStmtType getNullifyStmt() {
        return (NullifyStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_NullifyStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNullifyStmt(NullifyStmtType newNullifyStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_NullifyStmt(), newNullifyStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullifyStmt(NullifyStmtType newNullifyStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_NullifyStmt(), newNullifyStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getO() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_O(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setO(String newO) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_O(), newO);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectType getObject() {
        return (ObjectType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Object(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetObject(ObjectType newObject, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Object(), newObject, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setObject(ObjectType newObject) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Object(), newObject);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpType getOp() {
        return (OpType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Op(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOp(OpType newOp, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Op(), newOp, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOp(OpType newOp) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Op(), newOp);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpEType getOpE() {
        return (OpEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_OpE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOpE(OpEType newOpE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_OpE(), newOpE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOpE(OpEType newOpE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_OpE(), newOpE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpenStmtType getOpenStmt() {
        return (OpenStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_OpenStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOpenStmt(OpenStmtType newOpenStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_OpenStmt(), newOpenStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOpenStmt(OpenStmtType newOpenStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_OpenStmt(), newOpenStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputItemType getOutputItem() {
        return (OutputItemType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItem(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOutputItem(OutputItemType newOutputItem, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItem(), newOutputItem, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOutputItem(OutputItemType newOutputItem) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItem(), newOutputItem);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputItemLTType getOutputItemLT() {
        return (OutputItemLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItemLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOutputItemLT(OutputItemLTType newOutputItemLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItemLT(), newOutputItemLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOutputItemLT(OutputItemLTType newOutputItemLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_OutputItemLT(), newOutputItemLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParensEType getParensE() {
        return (ParensEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ParensE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParensE(ParensEType newParensE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ParensE(), newParensE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParensE(ParensEType newParensE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ParensE(), newParensE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParensRType getParensR() {
        return (ParensRType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ParensR(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParensR(ParensRType newParensR, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ParensR(), newParensR, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParensR(ParensRType newParensR) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ParensR(), newParensR);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointerAStmtType getPointerAStmt() {
        return (PointerAStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_PointerAStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPointerAStmt(PointerAStmtType newPointerAStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_PointerAStmt(), newPointerAStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPointerAStmt(PointerAStmtType newPointerAStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_PointerAStmt(), newPointerAStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointerStmtType getPointerStmt() {
        return (PointerStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_PointerStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPointerStmt(PointerStmtType newPointerStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_PointerStmt(), newPointerStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPointerStmt(PointerStmtType newPointerStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_PointerStmt(), newPointerStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPrefix() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Prefix(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrefix(String newPrefix) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Prefix(), newPrefix);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPrivateStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_PrivateStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrivateStmt(String newPrivateStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_PrivateStmt(), newPrivateStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureDesignatorType getProcedureDesignator() {
        return (ProcedureDesignatorType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureDesignator(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProcedureDesignator(ProcedureDesignatorType newProcedureDesignator, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureDesignator(), newProcedureDesignator, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcedureDesignator(ProcedureDesignatorType newProcedureDesignator) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureDesignator(), newProcedureDesignator);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureStmtType getProcedureStmt() {
        return (ProcedureStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProcedureStmt(ProcedureStmtType newProcedureStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureStmt(), newProcedureStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcedureStmt(ProcedureStmtType newProcedureStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ProcedureStmt(), newProcedureStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProgramNType getProgramN() {
        return (ProgramNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProgramN(ProgramNType newProgramN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramN(), newProgramN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProgramN(ProgramNType newProgramN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramN(), newProgramN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProgramStmtType getProgramStmt() {
        return (ProgramStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProgramStmt(ProgramStmtType newProgramStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramStmt(), newProgramStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProgramStmt(ProgramStmtType newProgramStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ProgramStmt(), newProgramStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublicStmtType getPublicStmt() {
        return (PublicStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_PublicStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPublicStmt(PublicStmtType newPublicStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_PublicStmt(), newPublicStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPublicStmt(PublicStmtType newPublicStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_PublicStmt(), newPublicStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RLTType getRLT() {
        return (RLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_RLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRLT(RLTType newRLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_RLT(), newRLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRLT(RLTType newRLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_RLT(), newRLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReadStmtType getReadStmt() {
        return (ReadStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ReadStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetReadStmt(ReadStmtType newReadStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ReadStmt(), newReadStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReadStmt(ReadStmtType newReadStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ReadStmt(), newReadStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RenameType getRename() {
        return (RenameType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_Rename(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRename(RenameType newRename, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_Rename(), newRename, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRename(RenameType newRename) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_Rename(), newRename);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RenameLTType getRenameLT() {
        return (RenameLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_RenameLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRenameLT(RenameLTType newRenameLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_RenameLT(), newRenameLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRenameLT(RenameLTType newRenameLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_RenameLT(), newRenameLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultSpecType getResultSpec() {
        return (ResultSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ResultSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResultSpec(ResultSpecType newResultSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ResultSpec(), newResultSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResultSpec(ResultSpecType newResultSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ResultSpec(), newResultSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReturnStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ReturnStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturnStmt(String newReturnStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ReturnStmt(), newReturnStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getS() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_S(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setS(String newS) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_S(), newS);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSaveStmt() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SaveStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSaveStmt(String newSaveStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SaveStmt(), newSaveStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SectionSubscriptType getSectionSubscript() {
        return (SectionSubscriptType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscript(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSectionSubscript(SectionSubscriptType newSectionSubscript, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscript(), newSectionSubscript, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSectionSubscript(SectionSubscriptType newSectionSubscript) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscript(), newSectionSubscript);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SectionSubscriptLTType getSectionSubscriptLT() {
        return (SectionSubscriptLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscriptLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSectionSubscriptLT(SectionSubscriptLTType newSectionSubscriptLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscriptLT(), newSectionSubscriptLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSectionSubscriptLT(SectionSubscriptLTType newSectionSubscriptLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SectionSubscriptLT(), newSectionSubscriptLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SelectCaseStmtType getSelectCaseStmt() {
        return (SelectCaseStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SelectCaseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSelectCaseStmt(SelectCaseStmtType newSelectCaseStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_SelectCaseStmt(), newSelectCaseStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelectCaseStmt(SelectCaseStmtType newSelectCaseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SelectCaseStmt(), newSelectCaseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeSpecType getShapeSpec() {
        return (ShapeSpecType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpec(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetShapeSpec(ShapeSpecType newShapeSpec, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpec(), newShapeSpec, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShapeSpec(ShapeSpecType newShapeSpec) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpec(), newShapeSpec);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeSpecLTType getShapeSpecLT() {
        return (ShapeSpecLTType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpecLT(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetShapeSpecLT(ShapeSpecLTType newShapeSpecLT, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpecLT(), newShapeSpecLT, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShapeSpecLT(ShapeSpecLTType newShapeSpecLT) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_ShapeSpecLT(), newShapeSpecLT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStarE() {
        return (String)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_StarE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStarE(String newStarE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_StarE(), newStarE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getStopCode() {
        return (BigInteger)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_StopCode(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStopCode(BigInteger newStopCode) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_StopCode(), newStopCode);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StopStmtType getStopStmt() {
        return (StopStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_StopStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStopStmt(StopStmtType newStopStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_StopStmt(), newStopStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStopStmt(StopStmtType newStopStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_StopStmt(), newStopStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StringEType getStringE() {
        return (StringEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_StringE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStringE(StringEType newStringE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_StringE(), newStringE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringE(StringEType newStringE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_StringE(), newStringE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubroutineNType getSubroutineN() {
        return (SubroutineNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubroutineN(SubroutineNType newSubroutineN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineN(), newSubroutineN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubroutineN(SubroutineNType newSubroutineN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineN(), newSubroutineN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubroutineStmtType getSubroutineStmt() {
        return (SubroutineStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubroutineStmt(SubroutineStmtType newSubroutineStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineStmt(), newSubroutineStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubroutineStmt(SubroutineStmtType newSubroutineStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_SubroutineStmt(), newSubroutineStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TDeclStmtType getTDeclStmt() {
        return (TDeclStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_TDeclStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTDeclStmt(TDeclStmtType newTDeclStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_TDeclStmt(), newTDeclStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTDeclStmt(TDeclStmtType newTDeclStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_TDeclStmt(), newTDeclStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TNType getTN() {
        return (TNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_TN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTN(TNType newTN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_TN(), newTN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTN(TNType newTN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_TN(), newTN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TStmtType getTStmt() {
        return (TStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_TStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTStmt(TStmtType newTStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_TStmt(), newTStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTStmt(TStmtType newTStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_TStmt(), newTStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestEType getTestE() {
        return (TestEType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_TestE(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTestE(TestEType newTestE, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_TestE(), newTestE, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTestE(TestEType newTestE) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_TestE(), newTestE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpperBoundType getUpperBound() {
        return (UpperBoundType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_UpperBound(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUpperBound(UpperBoundType newUpperBound, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_UpperBound(), newUpperBound, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpperBound(UpperBoundType newUpperBound) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_UpperBound(), newUpperBound);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseNType getUseN() {
        return (UseNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_UseN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUseN(UseNType newUseN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_UseN(), newUseN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUseN(UseNType newUseN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_UseN(), newUseN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseStmtType getUseStmt() {
        return (UseStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_UseStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUseStmt(UseStmtType newUseStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_UseStmt(), newUseStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUseStmt(UseStmtType newUseStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_UseStmt(), newUseStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VType getV() {
        return (VType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_V(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetV(VType newV, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_V(), newV, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setV(VType newV) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_V(), newV);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VNType getVN() {
        return (VNType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_VN(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVN(VNType newVN, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_VN(), newVN, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVN(VNType newVN) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_VN(), newVN);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WhereConstructStmtType getWhereConstructStmt() {
        return (WhereConstructStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_WhereConstructStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhereConstructStmt(WhereConstructStmtType newWhereConstructStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_WhereConstructStmt(), newWhereConstructStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhereConstructStmt(WhereConstructStmtType newWhereConstructStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_WhereConstructStmt(), newWhereConstructStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WhereStmtType getWhereStmt() {
        return (WhereStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_WhereStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhereStmt(WhereStmtType newWhereStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_WhereStmt(), newWhereStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhereStmt(WhereStmtType newWhereStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_WhereStmt(), newWhereStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WriteStmtType getWriteStmt() {
        return (WriteStmtType)getMixed().get(FxtranPackage.eINSTANCE.getDocumentRoot_WriteStmt(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWriteStmt(WriteStmtType newWriteStmt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(FxtranPackage.eINSTANCE.getDocumentRoot_WriteStmt(), newWriteStmt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWriteStmt(WriteStmtType newWriteStmt) {
        ((FeatureMap.Internal)getMixed()).set(FxtranPackage.eINSTANCE.getDocumentRoot_WriteStmt(), newWriteStmt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.DOCUMENT_ROOT__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DOCUMENT_ROOT__TSPEC:
                return basicSetTSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ASTMT:
                return basicSetAStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE:
                return basicSetAcValue(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE_LT:
                return basicSetAcValueLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ACTION_STMT:
                return basicSetActionStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ALLOCATE_STMT:
                return basicSetAllocateStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARG:
                return basicSetArg(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARG_N:
                return basicSetArgN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARG_SPEC:
                return basicSetArgSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_CONSTRUCTOR_E:
                return basicSetArrayConstructorE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_R:
                return basicSetArrayR(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_SPEC:
                return basicSetArraySpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE:
                return basicSetAttribute(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CALL_STMT:
                return basicSetCallStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_E:
                return basicSetCaseE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_SELECTOR:
                return basicSetCaseSelector(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_STMT:
                return basicSetCaseStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE:
                return basicSetCaseValue(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE:
                return basicSetCaseValueRange(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE_LT:
                return basicSetCaseValueRangeLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SELECTOR:
                return basicSetCharSelector(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SPEC:
                return basicSetCharSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC:
                return basicSetCloseSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC_SPEC:
                return basicSetCloseSpecSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_STMT:
                return basicSetCloseStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_DECL_STMT:
                return basicSetComponentDeclStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_R:
                return basicSetComponentR(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CONDITION_E:
                return basicSetConditionE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC:
                return basicSetConnectSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC_SPEC:
                return basicSetConnectSpecSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__CYCLE_STMT:
                return basicSetCycleStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__DEALLOCATE_STMT:
                return basicSetDeallocateStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__DERIVED_TSPEC:
                return basicSetDerivedTSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__DO_STMT:
                return basicSetDoStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__DO_V:
                return basicSetDoV(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__DUMMY_ARG_LT:
                return basicSetDummyArgLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__E1:
                return basicSetE1(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__E2:
                return basicSetE2(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT:
                return basicSetElement(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT_LT:
                return basicSetElementLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ELSE_IF_STMT:
                return basicSetElseIfStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__EN:
                return basicSetEN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL:
                return basicSetENDecl(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL_LT:
                return basicSetENDeclLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ENLT:
                return basicSetENLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ENN:
                return basicSetENN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_DO_STMT:
                return basicSetEndDoStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_FORALL_STMT:
                return basicSetEndForallStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_FUNCTION_STMT:
                return basicSetEndFunctionStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_INTERFACE_STMT:
                return basicSetEndInterfaceStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_MODULE_STMT:
                return basicSetEndModuleStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_PROGRAM_STMT:
                return basicSetEndProgramStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_SELECT_CASE_STMT:
                return basicSetEndSelectCaseStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_SUBROUTINE_STMT:
                return basicSetEndSubroutineStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__END_TSTMT:
                return basicSetEndTStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ERROR:
                return basicSetError(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FILE:
                return basicSetFile(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FORALL_CONSTRUCT_STMT:
                return basicSetForallConstructStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FORALL_STMT:
                return basicSetForallStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC:
                return basicSetForallTripletSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC_LT:
                return basicSetForallTripletSpecLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_N:
                return basicSetFunctionN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_STMT:
                return basicSetFunctionStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__IF_STMT:
                return basicSetIfStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__IF_THEN_STMT:
                return basicSetIfThenStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INIT_E:
                return basicSetInitE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INQUIRE_STMT:
                return basicSetInquireStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC:
                return basicSetInquirySpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC_SPEC:
                return basicSetInquirySpecSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INTERFACE_STMT:
                return basicSetInterfaceStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__INTRINSIC_TSPEC:
                return basicSetIntrinsicTSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL:
                return basicSetIoControl(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL_SPEC:
                return basicSetIoControlSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR:
                return basicSetIterator(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_DEFINITION_LT:
                return basicSetIteratorDefinitionLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_ELEMENT:
                return basicSetIteratorElement(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__KSELECTOR:
                return basicSetKSelector(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__KSPEC:
                return basicSetKSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__LABEL:
                return basicSetLabel(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__LITERAL_E:
                return basicSetLiteralE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__LOWER_BOUND:
                return basicSetLowerBound(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__MASK_E:
                return basicSetMaskE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__MODULE_N:
                return basicSetModuleN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__MODULE_PROCEDURE_NLT:
                return basicSetModuleProcedureNLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__MODULE_STMT:
                return basicSetModuleStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__N1:
                return basicSetN1(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMED_E:
                return basicSetNamedE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_N:
                return basicSetNamelistGroupN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ:
                return basicSetNamelistGroupObj(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_LT:
                return basicSetNamelistGroupObjLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_N:
                return basicSetNamelistGroupObjN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_STMT:
                return basicSetNamelistStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__NULLIFY_STMT:
                return basicSetNullifyStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OBJECT:
                return basicSetObject(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OP:
                return basicSetOp(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OP_E:
                return basicSetOpE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OPEN_STMT:
                return basicSetOpenStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM:
                return basicSetOutputItem(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM_LT:
                return basicSetOutputItemLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PARENS_E:
                return basicSetParensE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PARENS_R:
                return basicSetParensR(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__POINTER_ASTMT:
                return basicSetPointerAStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__POINTER_STMT:
                return basicSetPointerStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_DESIGNATOR:
                return basicSetProcedureDesignator(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_STMT:
                return basicSetProcedureStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_N:
                return basicSetProgramN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_STMT:
                return basicSetProgramStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__PUBLIC_STMT:
                return basicSetPublicStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__RLT:
                return basicSetRLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__READ_STMT:
                return basicSetReadStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__RENAME:
                return basicSetRename(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__RENAME_LT:
                return basicSetRenameLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__RESULT_SPEC:
                return basicSetResultSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT:
                return basicSetSectionSubscript(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT_LT:
                return basicSetSectionSubscriptLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SELECT_CASE_STMT:
                return basicSetSelectCaseStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC:
                return basicSetShapeSpec(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC_LT:
                return basicSetShapeSpecLT(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__STOP_STMT:
                return basicSetStopStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__STRING_E:
                return basicSetStringE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_N:
                return basicSetSubroutineN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_STMT:
                return basicSetSubroutineStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__TDECL_STMT:
                return basicSetTDeclStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__TN:
                return basicSetTN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__TSTMT:
                return basicSetTStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__TEST_E:
                return basicSetTestE(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__UPPER_BOUND:
                return basicSetUpperBound(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__USE_N:
                return basicSetUseN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__USE_STMT:
                return basicSetUseStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__V:
                return basicSetV(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__VN:
                return basicSetVN(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__WHERE_CONSTRUCT_STMT:
                return basicSetWhereConstructStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__WHERE_STMT:
                return basicSetWhereStmt(null, msgs);
            case FxtranPackage.DOCUMENT_ROOT__WRITE_STMT:
                return basicSetWriteStmt(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FxtranPackage.DOCUMENT_ROOT__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                if (coreType) return getXMLNSPrefixMap();
                else return getXMLNSPrefixMap().map();
            case FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                if (coreType) return getXSISchemaLocation();
                else return getXSISchemaLocation().map();
            case FxtranPackage.DOCUMENT_ROOT__TSPEC:
                return getTSpec();
            case FxtranPackage.DOCUMENT_ROOT__A:
                return getA();
            case FxtranPackage.DOCUMENT_ROOT__ASTMT:
                return getAStmt();
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE:
                return getAcValue();
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE_LT:
                return getAcValueLT();
            case FxtranPackage.DOCUMENT_ROOT__ACTION_STMT:
                return getActionStmt();
            case FxtranPackage.DOCUMENT_ROOT__ALLOCATE_STMT:
                return getAllocateStmt();
            case FxtranPackage.DOCUMENT_ROOT__ARG:
                return getArg();
            case FxtranPackage.DOCUMENT_ROOT__ARG_N:
                return getArgN();
            case FxtranPackage.DOCUMENT_ROOT__ARG_SPEC:
                return getArgSpec();
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_CONSTRUCTOR_E:
                return getArrayConstructorE();
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_R:
                return getArrayR();
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_SPEC:
                return getArraySpec();
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE:
                return getAttribute();
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE_N:
                return getAttributeN();
            case FxtranPackage.DOCUMENT_ROOT__C:
                return getC();
            case FxtranPackage.DOCUMENT_ROOT__CALL_STMT:
                return getCallStmt();
            case FxtranPackage.DOCUMENT_ROOT__CASE_E:
                return getCaseE();
            case FxtranPackage.DOCUMENT_ROOT__CASE_SELECTOR:
                return getCaseSelector();
            case FxtranPackage.DOCUMENT_ROOT__CASE_STMT:
                return getCaseStmt();
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE:
                return getCaseValue();
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE:
                return getCaseValueRange();
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE_LT:
                return getCaseValueRangeLT();
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SELECTOR:
                return getCharSelector();
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SPEC:
                return getCharSpec();
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC:
                return getCloseSpec();
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC_SPEC:
                return getCloseSpecSpec();
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_STMT:
                return getCloseStmt();
            case FxtranPackage.DOCUMENT_ROOT__CNT:
                return getCnt();
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_DECL_STMT:
                return getComponentDeclStmt();
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_R:
                return getComponentR();
            case FxtranPackage.DOCUMENT_ROOT__CONDITION_E:
                return getConditionE();
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC:
                return getConnectSpec();
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC_SPEC:
                return getConnectSpecSpec();
            case FxtranPackage.DOCUMENT_ROOT__CONTAINS_STMT:
                return getContainsStmt();
            case FxtranPackage.DOCUMENT_ROOT__CPP:
                return getCpp();
            case FxtranPackage.DOCUMENT_ROOT__CT:
                return getCt();
            case FxtranPackage.DOCUMENT_ROOT__CYCLE_STMT:
                return getCycleStmt();
            case FxtranPackage.DOCUMENT_ROOT__DEALLOCATE_STMT:
                return getDeallocateStmt();
            case FxtranPackage.DOCUMENT_ROOT__DERIVED_TSPEC:
                return getDerivedTSpec();
            case FxtranPackage.DOCUMENT_ROOT__DO_STMT:
                return getDoStmt();
            case FxtranPackage.DOCUMENT_ROOT__DO_V:
                return getDoV();
            case FxtranPackage.DOCUMENT_ROOT__DUMMY_ARG_LT:
                return getDummyArgLT();
            case FxtranPackage.DOCUMENT_ROOT__E1:
                return getE1();
            case FxtranPackage.DOCUMENT_ROOT__E2:
                return getE2();
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT:
                return getElement();
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT_LT:
                return getElementLT();
            case FxtranPackage.DOCUMENT_ROOT__ELSE_IF_STMT:
                return getElseIfStmt();
            case FxtranPackage.DOCUMENT_ROOT__ELSE_STMT:
                return getElseStmt();
            case FxtranPackage.DOCUMENT_ROOT__ELSE_WHERE_STMT:
                return getElseWhereStmt();
            case FxtranPackage.DOCUMENT_ROOT__EN:
                return getEN();
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL:
                return getENDecl();
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL_LT:
                return getENDeclLT();
            case FxtranPackage.DOCUMENT_ROOT__ENLT:
                return getENLT();
            case FxtranPackage.DOCUMENT_ROOT__ENN:
                return getENN();
            case FxtranPackage.DOCUMENT_ROOT__END_DO_STMT:
                return getEndDoStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_FORALL_STMT:
                return getEndForallStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_FUNCTION_STMT:
                return getEndFunctionStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_IF_STMT:
                return getEndIfStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_INTERFACE_STMT:
                return getEndInterfaceStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_MODULE_STMT:
                return getEndModuleStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_PROGRAM_STMT:
                return getEndProgramStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_SELECT_CASE_STMT:
                return getEndSelectCaseStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_SUBROUTINE_STMT:
                return getEndSubroutineStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_TSTMT:
                return getEndTStmt();
            case FxtranPackage.DOCUMENT_ROOT__END_WHERE_STMT:
                return getEndWhereStmt();
            case FxtranPackage.DOCUMENT_ROOT__ERROR:
                return getError();
            case FxtranPackage.DOCUMENT_ROOT__EXIT_STMT:
                return getExitStmt();
            case FxtranPackage.DOCUMENT_ROOT__FILE:
                return getFile();
            case FxtranPackage.DOCUMENT_ROOT__FORALL_CONSTRUCT_STMT:
                return getForallConstructStmt();
            case FxtranPackage.DOCUMENT_ROOT__FORALL_STMT:
                return getForallStmt();
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC:
                return getForallTripletSpec();
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC_LT:
                return getForallTripletSpecLT();
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_N:
                return getFunctionN();
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_STMT:
                return getFunctionStmt();
            case FxtranPackage.DOCUMENT_ROOT__IF_STMT:
                return getIfStmt();
            case FxtranPackage.DOCUMENT_ROOT__IF_THEN_STMT:
                return getIfThenStmt();
            case FxtranPackage.DOCUMENT_ROOT__IMPLICIT_NONE_STMT:
                return getImplicitNoneStmt();
            case FxtranPackage.DOCUMENT_ROOT__INIT_E:
                return getInitE();
            case FxtranPackage.DOCUMENT_ROOT__INQUIRE_STMT:
                return getInquireStmt();
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC:
                return getInquirySpec();
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC_SPEC:
                return getInquirySpecSpec();
            case FxtranPackage.DOCUMENT_ROOT__INTENT_SPEC:
                return getIntentSpec();
            case FxtranPackage.DOCUMENT_ROOT__INTERFACE_STMT:
                return getInterfaceStmt();
            case FxtranPackage.DOCUMENT_ROOT__INTRINSIC_TSPEC:
                return getIntrinsicTSpec();
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL:
                return getIoControl();
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL_SPEC:
                return getIoControlSpec();
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR:
                return getIterator();
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_DEFINITION_LT:
                return getIteratorDefinitionLT();
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_ELEMENT:
                return getIteratorElement();
            case FxtranPackage.DOCUMENT_ROOT__K:
                return getK();
            case FxtranPackage.DOCUMENT_ROOT__KSELECTOR:
                return getKSelector();
            case FxtranPackage.DOCUMENT_ROOT__KSPEC:
                return getKSpec();
            case FxtranPackage.DOCUMENT_ROOT__L:
                return getL();
            case FxtranPackage.DOCUMENT_ROOT__LABEL:
                return getLabel();
            case FxtranPackage.DOCUMENT_ROOT__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.DOCUMENT_ROOT__LOWER_BOUND:
                return getLowerBound();
            case FxtranPackage.DOCUMENT_ROOT__MASK_E:
                return getMaskE();
            case FxtranPackage.DOCUMENT_ROOT__MODULE_N:
                return getModuleN();
            case FxtranPackage.DOCUMENT_ROOT__MODULE_PROCEDURE_NLT:
                return getModuleProcedureNLT();
            case FxtranPackage.DOCUMENT_ROOT__MODULE_STMT:
                return getModuleStmt();
            case FxtranPackage.DOCUMENT_ROOT__N:
                return getN();
            case FxtranPackage.DOCUMENT_ROOT__N1:
                return getN1();
            case FxtranPackage.DOCUMENT_ROOT__NAMED_E:
                return getNamedE();
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_N:
                return getNamelistGroupN();
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ:
                return getNamelistGroupObj();
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_LT:
                return getNamelistGroupObjLT();
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_N:
                return getNamelistGroupObjN();
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_STMT:
                return getNamelistStmt();
            case FxtranPackage.DOCUMENT_ROOT__NULLIFY_STMT:
                return getNullifyStmt();
            case FxtranPackage.DOCUMENT_ROOT__O:
                return getO();
            case FxtranPackage.DOCUMENT_ROOT__OBJECT:
                return getObject();
            case FxtranPackage.DOCUMENT_ROOT__OP:
                return getOp();
            case FxtranPackage.DOCUMENT_ROOT__OP_E:
                return getOpE();
            case FxtranPackage.DOCUMENT_ROOT__OPEN_STMT:
                return getOpenStmt();
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM:
                return getOutputItem();
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM_LT:
                return getOutputItemLT();
            case FxtranPackage.DOCUMENT_ROOT__PARENS_E:
                return getParensE();
            case FxtranPackage.DOCUMENT_ROOT__PARENS_R:
                return getParensR();
            case FxtranPackage.DOCUMENT_ROOT__POINTER_ASTMT:
                return getPointerAStmt();
            case FxtranPackage.DOCUMENT_ROOT__POINTER_STMT:
                return getPointerStmt();
            case FxtranPackage.DOCUMENT_ROOT__PREFIX:
                return getPrefix();
            case FxtranPackage.DOCUMENT_ROOT__PRIVATE_STMT:
                return getPrivateStmt();
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_DESIGNATOR:
                return getProcedureDesignator();
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_STMT:
                return getProcedureStmt();
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_N:
                return getProgramN();
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_STMT:
                return getProgramStmt();
            case FxtranPackage.DOCUMENT_ROOT__PUBLIC_STMT:
                return getPublicStmt();
            case FxtranPackage.DOCUMENT_ROOT__RLT:
                return getRLT();
            case FxtranPackage.DOCUMENT_ROOT__READ_STMT:
                return getReadStmt();
            case FxtranPackage.DOCUMENT_ROOT__RENAME:
                return getRename();
            case FxtranPackage.DOCUMENT_ROOT__RENAME_LT:
                return getRenameLT();
            case FxtranPackage.DOCUMENT_ROOT__RESULT_SPEC:
                return getResultSpec();
            case FxtranPackage.DOCUMENT_ROOT__RETURN_STMT:
                return getReturnStmt();
            case FxtranPackage.DOCUMENT_ROOT__S:
                return getS();
            case FxtranPackage.DOCUMENT_ROOT__SAVE_STMT:
                return getSaveStmt();
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT:
                return getSectionSubscript();
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT_LT:
                return getSectionSubscriptLT();
            case FxtranPackage.DOCUMENT_ROOT__SELECT_CASE_STMT:
                return getSelectCaseStmt();
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC:
                return getShapeSpec();
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC_LT:
                return getShapeSpecLT();
            case FxtranPackage.DOCUMENT_ROOT__STAR_E:
                return getStarE();
            case FxtranPackage.DOCUMENT_ROOT__STOP_CODE:
                return getStopCode();
            case FxtranPackage.DOCUMENT_ROOT__STOP_STMT:
                return getStopStmt();
            case FxtranPackage.DOCUMENT_ROOT__STRING_E:
                return getStringE();
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_N:
                return getSubroutineN();
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_STMT:
                return getSubroutineStmt();
            case FxtranPackage.DOCUMENT_ROOT__TDECL_STMT:
                return getTDeclStmt();
            case FxtranPackage.DOCUMENT_ROOT__TN:
                return getTN();
            case FxtranPackage.DOCUMENT_ROOT__TSTMT:
                return getTStmt();
            case FxtranPackage.DOCUMENT_ROOT__TEST_E:
                return getTestE();
            case FxtranPackage.DOCUMENT_ROOT__UPPER_BOUND:
                return getUpperBound();
            case FxtranPackage.DOCUMENT_ROOT__USE_N:
                return getUseN();
            case FxtranPackage.DOCUMENT_ROOT__USE_STMT:
                return getUseStmt();
            case FxtranPackage.DOCUMENT_ROOT__V:
                return getV();
            case FxtranPackage.DOCUMENT_ROOT__VN:
                return getVN();
            case FxtranPackage.DOCUMENT_ROOT__WHERE_CONSTRUCT_STMT:
                return getWhereConstructStmt();
            case FxtranPackage.DOCUMENT_ROOT__WHERE_STMT:
                return getWhereStmt();
            case FxtranPackage.DOCUMENT_ROOT__WRITE_STMT:
                return getWriteStmt();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FxtranPackage.DOCUMENT_ROOT__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TSPEC:
                setTSpec((TSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__A:
                setA((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ASTMT:
                setAStmt((AStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE:
                setAcValue((AcValueType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE_LT:
                setAcValueLT((AcValueLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ACTION_STMT:
                setActionStmt((ActionStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ALLOCATE_STMT:
                setAllocateStmt((AllocateStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG:
                setArg((ArgType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG_N:
                setArgN((ArgNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG_SPEC:
                setArgSpec((ArgSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_R:
                setArrayR((ArrayRType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_SPEC:
                setArraySpec((ArraySpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE:
                setAttribute((AttributeType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE_N:
                setAttributeN((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__C:
                setC((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CALL_STMT:
                setCallStmt((CallStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_E:
                setCaseE((CaseEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_SELECTOR:
                setCaseSelector((CaseSelectorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_STMT:
                setCaseStmt((CaseStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE:
                setCaseValue((CaseValueType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE:
                setCaseValueRange((CaseValueRangeType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE_LT:
                setCaseValueRangeLT((CaseValueRangeLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SELECTOR:
                setCharSelector((CharSelectorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SPEC:
                setCharSpec((CharSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC:
                setCloseSpec((CloseSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC_SPEC:
                setCloseSpecSpec((CloseSpecSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_STMT:
                setCloseStmt((CloseStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CNT:
                setCnt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_DECL_STMT:
                setComponentDeclStmt((ComponentDeclStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_R:
                setComponentR((ComponentRType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONDITION_E:
                setConditionE((ConditionEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC:
                setConnectSpec((ConnectSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC_SPEC:
                setConnectSpecSpec((ConnectSpecSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONTAINS_STMT:
                setContainsStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CPP:
                setCpp((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CT:
                setCt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CYCLE_STMT:
                setCycleStmt((CycleStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DEALLOCATE_STMT:
                setDeallocateStmt((DeallocateStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DERIVED_TSPEC:
                setDerivedTSpec((DerivedTSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DO_STMT:
                setDoStmt((DoStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DO_V:
                setDoV((DoVType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DUMMY_ARG_LT:
                setDummyArgLT((DummyArgLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__E1:
                setE1((E1Type)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__E2:
                setE2((E2Type)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT:
                setElement((ElementType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT_LT:
                setElementLT((ElementLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_IF_STMT:
                setElseIfStmt((ElseIfStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_STMT:
                setElseStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_WHERE_STMT:
                setElseWhereStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN:
                setEN((ENType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL:
                setENDecl((ENDeclType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL_LT:
                setENDeclLT((ENDeclLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ENLT:
                setENLT((ENLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ENN:
                setENN((ENNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_DO_STMT:
                setEndDoStmt((EndDoStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_FORALL_STMT:
                setEndForallStmt((EndForallStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_FUNCTION_STMT:
                setEndFunctionStmt((EndFunctionStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_IF_STMT:
                setEndIfStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_INTERFACE_STMT:
                setEndInterfaceStmt((EndInterfaceStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_MODULE_STMT:
                setEndModuleStmt((EndModuleStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_PROGRAM_STMT:
                setEndProgramStmt((EndProgramStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_SELECT_CASE_STMT:
                setEndSelectCaseStmt((EndSelectCaseStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_SUBROUTINE_STMT:
                setEndSubroutineStmt((EndSubroutineStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_TSTMT:
                setEndTStmt((EndTStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_WHERE_STMT:
                setEndWhereStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ERROR:
                setError((ErrorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EXIT_STMT:
                setExitStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FILE:
                setFile((FileType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_CONSTRUCT_STMT:
                setForallConstructStmt((ForallConstructStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_STMT:
                setForallStmt((ForallStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC:
                setForallTripletSpec((ForallTripletSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC_LT:
                setForallTripletSpecLT((ForallTripletSpecLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_N:
                setFunctionN((FunctionNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_STMT:
                setFunctionStmt((FunctionStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IF_STMT:
                setIfStmt((IfStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IF_THEN_STMT:
                setIfThenStmt((IfThenStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IMPLICIT_NONE_STMT:
                setImplicitNoneStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INIT_E:
                setInitE((InitEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRE_STMT:
                setInquireStmt((InquireStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC:
                setInquirySpec((InquirySpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC_SPEC:
                setInquirySpecSpec((InquirySpecSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTENT_SPEC:
                setIntentSpec((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTERFACE_STMT:
                setInterfaceStmt((InterfaceStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTRINSIC_TSPEC:
                setIntrinsicTSpec((IntrinsicTSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL:
                setIoControl((IoControlType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL_SPEC:
                setIoControlSpec((IoControlSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR:
                setIterator((IteratorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_DEFINITION_LT:
                setIteratorDefinitionLT((IteratorDefinitionLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_ELEMENT:
                setIteratorElement((IteratorElementType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__K:
                setK((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__KSELECTOR:
                setKSelector((KSelectorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__KSPEC:
                setKSpec((KSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__L:
                setL((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LABEL:
                setLabel((LabelType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LITERAL_E:
                setLiteralE((LiteralEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LOWER_BOUND:
                setLowerBound((LowerBoundType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MASK_E:
                setMaskE((MaskEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_N:
                setModuleN((ModuleNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_PROCEDURE_NLT:
                setModuleProcedureNLT((ModuleProcedureNLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_STMT:
                setModuleStmt((ModuleStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__N:
                setN((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__N1:
                setN1((NType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMED_E:
                setNamedE((NamedEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_N:
                setNamelistGroupN((NamelistGroupNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ:
                setNamelistGroupObj((NamelistGroupObjType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_LT:
                setNamelistGroupObjLT((NamelistGroupObjLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_N:
                setNamelistGroupObjN((NamelistGroupObjNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_STMT:
                setNamelistStmt((NamelistStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NULLIFY_STMT:
                setNullifyStmt((NullifyStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__O:
                setO((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OBJECT:
                setObject((ObjectType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OP:
                setOp((OpType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OP_E:
                setOpE((OpEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OPEN_STMT:
                setOpenStmt((OpenStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM:
                setOutputItem((OutputItemType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM_LT:
                setOutputItemLT((OutputItemLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_E:
                setParensE((ParensEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_R:
                setParensR((ParensRType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_ASTMT:
                setPointerAStmt((PointerAStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_STMT:
                setPointerStmt((PointerStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PREFIX:
                setPrefix((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PRIVATE_STMT:
                setPrivateStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_DESIGNATOR:
                setProcedureDesignator((ProcedureDesignatorType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_STMT:
                setProcedureStmt((ProcedureStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_N:
                setProgramN((ProgramNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_STMT:
                setProgramStmt((ProgramStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PUBLIC_STMT:
                setPublicStmt((PublicStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RLT:
                setRLT((RLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__READ_STMT:
                setReadStmt((ReadStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RENAME:
                setRename((RenameType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RENAME_LT:
                setRenameLT((RenameLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RESULT_SPEC:
                setResultSpec((ResultSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RETURN_STMT:
                setReturnStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__S:
                setS((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SAVE_STMT:
                setSaveStmt((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT:
                setSectionSubscript((SectionSubscriptType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT_LT:
                setSectionSubscriptLT((SectionSubscriptLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SELECT_CASE_STMT:
                setSelectCaseStmt((SelectCaseStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC:
                setShapeSpec((ShapeSpecType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC_LT:
                setShapeSpecLT((ShapeSpecLTType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STAR_E:
                setStarE((String)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STOP_CODE:
                setStopCode((BigInteger)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STOP_STMT:
                setStopStmt((StopStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STRING_E:
                setStringE((StringEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_N:
                setSubroutineN((SubroutineNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_STMT:
                setSubroutineStmt((SubroutineStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TDECL_STMT:
                setTDeclStmt((TDeclStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TN:
                setTN((TNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TSTMT:
                setTStmt((TStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TEST_E:
                setTestE((TestEType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__UPPER_BOUND:
                setUpperBound((UpperBoundType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__USE_N:
                setUseN((UseNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__USE_STMT:
                setUseStmt((UseStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__V:
                setV((VType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__VN:
                setVN((VNType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_CONSTRUCT_STMT:
                setWhereConstructStmt((WhereConstructStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_STMT:
                setWhereStmt((WhereStmtType)newValue);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WRITE_STMT:
                setWriteStmt((WriteStmtType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case FxtranPackage.DOCUMENT_ROOT__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                getXMLNSPrefixMap().clear();
                return;
            case FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                getXSISchemaLocation().clear();
                return;
            case FxtranPackage.DOCUMENT_ROOT__TSPEC:
                setTSpec((TSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__A:
                setA(A_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ASTMT:
                setAStmt((AStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE:
                setAcValue((AcValueType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE_LT:
                setAcValueLT((AcValueLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ACTION_STMT:
                setActionStmt((ActionStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ALLOCATE_STMT:
                setAllocateStmt((AllocateStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG:
                setArg((ArgType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG_N:
                setArgN((ArgNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARG_SPEC:
                setArgSpec((ArgSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_R:
                setArrayR((ArrayRType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_SPEC:
                setArraySpec((ArraySpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE:
                setAttribute((AttributeType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE_N:
                setAttributeN(ATTRIBUTE_N_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__C:
                setC(C_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CALL_STMT:
                setCallStmt((CallStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_E:
                setCaseE((CaseEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_SELECTOR:
                setCaseSelector((CaseSelectorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_STMT:
                setCaseStmt((CaseStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE:
                setCaseValue((CaseValueType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE:
                setCaseValueRange((CaseValueRangeType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE_LT:
                setCaseValueRangeLT((CaseValueRangeLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SELECTOR:
                setCharSelector((CharSelectorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SPEC:
                setCharSpec((CharSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC:
                setCloseSpec((CloseSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC_SPEC:
                setCloseSpecSpec((CloseSpecSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_STMT:
                setCloseStmt((CloseStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CNT:
                setCnt(CNT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_DECL_STMT:
                setComponentDeclStmt((ComponentDeclStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_R:
                setComponentR((ComponentRType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONDITION_E:
                setConditionE((ConditionEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC:
                setConnectSpec((ConnectSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC_SPEC:
                setConnectSpecSpec((ConnectSpecSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CONTAINS_STMT:
                setContainsStmt(CONTAINS_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CPP:
                setCpp(CPP_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CT:
                setCt(CT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__CYCLE_STMT:
                setCycleStmt((CycleStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DEALLOCATE_STMT:
                setDeallocateStmt((DeallocateStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DERIVED_TSPEC:
                setDerivedTSpec((DerivedTSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DO_STMT:
                setDoStmt((DoStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DO_V:
                setDoV((DoVType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__DUMMY_ARG_LT:
                setDummyArgLT((DummyArgLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__E1:
                setE1((E1Type)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__E2:
                setE2((E2Type)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT:
                setElement((ElementType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT_LT:
                setElementLT((ElementLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_IF_STMT:
                setElseIfStmt((ElseIfStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_STMT:
                setElseStmt(ELSE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_WHERE_STMT:
                setElseWhereStmt(ELSE_WHERE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN:
                setEN((ENType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL:
                setENDecl((ENDeclType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL_LT:
                setENDeclLT((ENDeclLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ENLT:
                setENLT((ENLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ENN:
                setENN((ENNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_DO_STMT:
                setEndDoStmt((EndDoStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_FORALL_STMT:
                setEndForallStmt((EndForallStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_FUNCTION_STMT:
                setEndFunctionStmt((EndFunctionStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_IF_STMT:
                setEndIfStmt(END_IF_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_INTERFACE_STMT:
                setEndInterfaceStmt((EndInterfaceStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_MODULE_STMT:
                setEndModuleStmt((EndModuleStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_PROGRAM_STMT:
                setEndProgramStmt((EndProgramStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_SELECT_CASE_STMT:
                setEndSelectCaseStmt((EndSelectCaseStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_SUBROUTINE_STMT:
                setEndSubroutineStmt((EndSubroutineStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_TSTMT:
                setEndTStmt((EndTStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__END_WHERE_STMT:
                setEndWhereStmt(END_WHERE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ERROR:
                setError((ErrorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__EXIT_STMT:
                setExitStmt(EXIT_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FILE:
                setFile((FileType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_CONSTRUCT_STMT:
                setForallConstructStmt((ForallConstructStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_STMT:
                setForallStmt((ForallStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC:
                setForallTripletSpec((ForallTripletSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC_LT:
                setForallTripletSpecLT((ForallTripletSpecLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_N:
                setFunctionN((FunctionNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_STMT:
                setFunctionStmt((FunctionStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IF_STMT:
                setIfStmt((IfStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IF_THEN_STMT:
                setIfThenStmt((IfThenStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IMPLICIT_NONE_STMT:
                setImplicitNoneStmt(IMPLICIT_NONE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INIT_E:
                setInitE((InitEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRE_STMT:
                setInquireStmt((InquireStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC:
                setInquirySpec((InquirySpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC_SPEC:
                setInquirySpecSpec((InquirySpecSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTENT_SPEC:
                setIntentSpec(INTENT_SPEC_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTERFACE_STMT:
                setInterfaceStmt((InterfaceStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__INTRINSIC_TSPEC:
                setIntrinsicTSpec((IntrinsicTSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL:
                setIoControl((IoControlType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL_SPEC:
                setIoControlSpec((IoControlSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR:
                setIterator((IteratorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_DEFINITION_LT:
                setIteratorDefinitionLT((IteratorDefinitionLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_ELEMENT:
                setIteratorElement((IteratorElementType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__K:
                setK(K_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__KSELECTOR:
                setKSelector((KSelectorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__KSPEC:
                setKSpec((KSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__L:
                setL(L_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LABEL:
                setLabel((LabelType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LITERAL_E:
                setLiteralE((LiteralEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__LOWER_BOUND:
                setLowerBound((LowerBoundType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MASK_E:
                setMaskE((MaskEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_N:
                setModuleN((ModuleNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_PROCEDURE_NLT:
                setModuleProcedureNLT((ModuleProcedureNLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_STMT:
                setModuleStmt((ModuleStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__N:
                setN(N_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__N1:
                setN1((NType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMED_E:
                setNamedE((NamedEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_N:
                setNamelistGroupN((NamelistGroupNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ:
                setNamelistGroupObj((NamelistGroupObjType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_LT:
                setNamelistGroupObjLT((NamelistGroupObjLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_N:
                setNamelistGroupObjN((NamelistGroupObjNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_STMT:
                setNamelistStmt((NamelistStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__NULLIFY_STMT:
                setNullifyStmt((NullifyStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__O:
                setO(O_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OBJECT:
                setObject((ObjectType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OP:
                setOp((OpType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OP_E:
                setOpE((OpEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OPEN_STMT:
                setOpenStmt((OpenStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM:
                setOutputItem((OutputItemType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM_LT:
                setOutputItemLT((OutputItemLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_E:
                setParensE((ParensEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_R:
                setParensR((ParensRType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_ASTMT:
                setPointerAStmt((PointerAStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_STMT:
                setPointerStmt((PointerStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PREFIX:
                setPrefix(PREFIX_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PRIVATE_STMT:
                setPrivateStmt(PRIVATE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_DESIGNATOR:
                setProcedureDesignator((ProcedureDesignatorType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_STMT:
                setProcedureStmt((ProcedureStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_N:
                setProgramN((ProgramNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_STMT:
                setProgramStmt((ProgramStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__PUBLIC_STMT:
                setPublicStmt((PublicStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RLT:
                setRLT((RLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__READ_STMT:
                setReadStmt((ReadStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RENAME:
                setRename((RenameType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RENAME_LT:
                setRenameLT((RenameLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RESULT_SPEC:
                setResultSpec((ResultSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__RETURN_STMT:
                setReturnStmt(RETURN_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__S:
                setS(S_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SAVE_STMT:
                setSaveStmt(SAVE_STMT_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT:
                setSectionSubscript((SectionSubscriptType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT_LT:
                setSectionSubscriptLT((SectionSubscriptLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SELECT_CASE_STMT:
                setSelectCaseStmt((SelectCaseStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC:
                setShapeSpec((ShapeSpecType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC_LT:
                setShapeSpecLT((ShapeSpecLTType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STAR_E:
                setStarE(STAR_E_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STOP_CODE:
                setStopCode(STOP_CODE_EDEFAULT);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STOP_STMT:
                setStopStmt((StopStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__STRING_E:
                setStringE((StringEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_N:
                setSubroutineN((SubroutineNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_STMT:
                setSubroutineStmt((SubroutineStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TDECL_STMT:
                setTDeclStmt((TDeclStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TN:
                setTN((TNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TSTMT:
                setTStmt((TStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__TEST_E:
                setTestE((TestEType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__UPPER_BOUND:
                setUpperBound((UpperBoundType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__USE_N:
                setUseN((UseNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__USE_STMT:
                setUseStmt((UseStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__V:
                setV((VType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__VN:
                setVN((VNType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_CONSTRUCT_STMT:
                setWhereConstructStmt((WhereConstructStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_STMT:
                setWhereStmt((WhereStmtType)null);
                return;
            case FxtranPackage.DOCUMENT_ROOT__WRITE_STMT:
                setWriteStmt((WriteStmtType)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FxtranPackage.DOCUMENT_ROOT__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
            case FxtranPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
            case FxtranPackage.DOCUMENT_ROOT__TSPEC:
                return getTSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__A:
                return A_EDEFAULT == null ? getA() != null : !A_EDEFAULT.equals(getA());
            case FxtranPackage.DOCUMENT_ROOT__ASTMT:
                return getAStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE:
                return getAcValue() != null;
            case FxtranPackage.DOCUMENT_ROOT__AC_VALUE_LT:
                return getAcValueLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__ACTION_STMT:
                return getActionStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__ALLOCATE_STMT:
                return getAllocateStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARG:
                return getArg() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARG_N:
                return getArgN() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARG_SPEC:
                return getArgSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_CONSTRUCTOR_E:
                return getArrayConstructorE() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_R:
                return getArrayR() != null;
            case FxtranPackage.DOCUMENT_ROOT__ARRAY_SPEC:
                return getArraySpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE:
                return getAttribute() != null;
            case FxtranPackage.DOCUMENT_ROOT__ATTRIBUTE_N:
                return ATTRIBUTE_N_EDEFAULT == null ? getAttributeN() != null : !ATTRIBUTE_N_EDEFAULT.equals(getAttributeN());
            case FxtranPackage.DOCUMENT_ROOT__C:
                return C_EDEFAULT == null ? getC() != null : !C_EDEFAULT.equals(getC());
            case FxtranPackage.DOCUMENT_ROOT__CALL_STMT:
                return getCallStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_E:
                return getCaseE() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_SELECTOR:
                return getCaseSelector() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_STMT:
                return getCaseStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE:
                return getCaseValue() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE:
                return getCaseValueRange() != null;
            case FxtranPackage.DOCUMENT_ROOT__CASE_VALUE_RANGE_LT:
                return getCaseValueRangeLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SELECTOR:
                return getCharSelector() != null;
            case FxtranPackage.DOCUMENT_ROOT__CHAR_SPEC:
                return getCharSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC:
                return getCloseSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_SPEC_SPEC:
                return getCloseSpecSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__CLOSE_STMT:
                return getCloseStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__CNT:
                return CNT_EDEFAULT == null ? getCnt() != null : !CNT_EDEFAULT.equals(getCnt());
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_DECL_STMT:
                return getComponentDeclStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__COMPONENT_R:
                return getComponentR() != null;
            case FxtranPackage.DOCUMENT_ROOT__CONDITION_E:
                return getConditionE() != null;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC:
                return getConnectSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__CONNECT_SPEC_SPEC:
                return getConnectSpecSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__CONTAINS_STMT:
                return CONTAINS_STMT_EDEFAULT == null ? getContainsStmt() != null : !CONTAINS_STMT_EDEFAULT.equals(getContainsStmt());
            case FxtranPackage.DOCUMENT_ROOT__CPP:
                return CPP_EDEFAULT == null ? getCpp() != null : !CPP_EDEFAULT.equals(getCpp());
            case FxtranPackage.DOCUMENT_ROOT__CT:
                return CT_EDEFAULT == null ? getCt() != null : !CT_EDEFAULT.equals(getCt());
            case FxtranPackage.DOCUMENT_ROOT__CYCLE_STMT:
                return getCycleStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__DEALLOCATE_STMT:
                return getDeallocateStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__DERIVED_TSPEC:
                return getDerivedTSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__DO_STMT:
                return getDoStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__DO_V:
                return getDoV() != null;
            case FxtranPackage.DOCUMENT_ROOT__DUMMY_ARG_LT:
                return getDummyArgLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__E1:
                return getE1() != null;
            case FxtranPackage.DOCUMENT_ROOT__E2:
                return getE2() != null;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT:
                return getElement() != null;
            case FxtranPackage.DOCUMENT_ROOT__ELEMENT_LT:
                return getElementLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_IF_STMT:
                return getElseIfStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__ELSE_STMT:
                return ELSE_STMT_EDEFAULT == null ? getElseStmt() != null : !ELSE_STMT_EDEFAULT.equals(getElseStmt());
            case FxtranPackage.DOCUMENT_ROOT__ELSE_WHERE_STMT:
                return ELSE_WHERE_STMT_EDEFAULT == null ? getElseWhereStmt() != null : !ELSE_WHERE_STMT_EDEFAULT.equals(getElseWhereStmt());
            case FxtranPackage.DOCUMENT_ROOT__EN:
                return getEN() != null;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL:
                return getENDecl() != null;
            case FxtranPackage.DOCUMENT_ROOT__EN_DECL_LT:
                return getENDeclLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__ENLT:
                return getENLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__ENN:
                return getENN() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_DO_STMT:
                return getEndDoStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_FORALL_STMT:
                return getEndForallStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_FUNCTION_STMT:
                return getEndFunctionStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_IF_STMT:
                return END_IF_STMT_EDEFAULT == null ? getEndIfStmt() != null : !END_IF_STMT_EDEFAULT.equals(getEndIfStmt());
            case FxtranPackage.DOCUMENT_ROOT__END_INTERFACE_STMT:
                return getEndInterfaceStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_MODULE_STMT:
                return getEndModuleStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_PROGRAM_STMT:
                return getEndProgramStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_SELECT_CASE_STMT:
                return getEndSelectCaseStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_SUBROUTINE_STMT:
                return getEndSubroutineStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_TSTMT:
                return getEndTStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__END_WHERE_STMT:
                return END_WHERE_STMT_EDEFAULT == null ? getEndWhereStmt() != null : !END_WHERE_STMT_EDEFAULT.equals(getEndWhereStmt());
            case FxtranPackage.DOCUMENT_ROOT__ERROR:
                return getError() != null;
            case FxtranPackage.DOCUMENT_ROOT__EXIT_STMT:
                return EXIT_STMT_EDEFAULT == null ? getExitStmt() != null : !EXIT_STMT_EDEFAULT.equals(getExitStmt());
            case FxtranPackage.DOCUMENT_ROOT__FILE:
                return getFile() != null;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_CONSTRUCT_STMT:
                return getForallConstructStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_STMT:
                return getForallStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC:
                return getForallTripletSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__FORALL_TRIPLET_SPEC_LT:
                return getForallTripletSpecLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_N:
                return getFunctionN() != null;
            case FxtranPackage.DOCUMENT_ROOT__FUNCTION_STMT:
                return getFunctionStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__IF_STMT:
                return getIfStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__IF_THEN_STMT:
                return getIfThenStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__IMPLICIT_NONE_STMT:
                return IMPLICIT_NONE_STMT_EDEFAULT == null ? getImplicitNoneStmt() != null : !IMPLICIT_NONE_STMT_EDEFAULT.equals(getImplicitNoneStmt());
            case FxtranPackage.DOCUMENT_ROOT__INIT_E:
                return getInitE() != null;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRE_STMT:
                return getInquireStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC:
                return getInquirySpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__INQUIRY_SPEC_SPEC:
                return getInquirySpecSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__INTENT_SPEC:
                return INTENT_SPEC_EDEFAULT == null ? getIntentSpec() != null : !INTENT_SPEC_EDEFAULT.equals(getIntentSpec());
            case FxtranPackage.DOCUMENT_ROOT__INTERFACE_STMT:
                return getInterfaceStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__INTRINSIC_TSPEC:
                return getIntrinsicTSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL:
                return getIoControl() != null;
            case FxtranPackage.DOCUMENT_ROOT__IO_CONTROL_SPEC:
                return getIoControlSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR:
                return getIterator() != null;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_DEFINITION_LT:
                return getIteratorDefinitionLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__ITERATOR_ELEMENT:
                return getIteratorElement() != null;
            case FxtranPackage.DOCUMENT_ROOT__K:
                return K_EDEFAULT == null ? getK() != null : !K_EDEFAULT.equals(getK());
            case FxtranPackage.DOCUMENT_ROOT__KSELECTOR:
                return getKSelector() != null;
            case FxtranPackage.DOCUMENT_ROOT__KSPEC:
                return getKSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__L:
                return L_EDEFAULT == null ? getL() != null : !L_EDEFAULT.equals(getL());
            case FxtranPackage.DOCUMENT_ROOT__LABEL:
                return getLabel() != null;
            case FxtranPackage.DOCUMENT_ROOT__LITERAL_E:
                return getLiteralE() != null;
            case FxtranPackage.DOCUMENT_ROOT__LOWER_BOUND:
                return getLowerBound() != null;
            case FxtranPackage.DOCUMENT_ROOT__MASK_E:
                return getMaskE() != null;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_N:
                return getModuleN() != null;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_PROCEDURE_NLT:
                return getModuleProcedureNLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__MODULE_STMT:
                return getModuleStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__N:
                return N_EDEFAULT == null ? getN() != null : !N_EDEFAULT.equals(getN());
            case FxtranPackage.DOCUMENT_ROOT__N1:
                return getN1() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMED_E:
                return getNamedE() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_N:
                return getNamelistGroupN() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ:
                return getNamelistGroupObj() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_LT:
                return getNamelistGroupObjLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_GROUP_OBJ_N:
                return getNamelistGroupObjN() != null;
            case FxtranPackage.DOCUMENT_ROOT__NAMELIST_STMT:
                return getNamelistStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__NULLIFY_STMT:
                return getNullifyStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__O:
                return O_EDEFAULT == null ? getO() != null : !O_EDEFAULT.equals(getO());
            case FxtranPackage.DOCUMENT_ROOT__OBJECT:
                return getObject() != null;
            case FxtranPackage.DOCUMENT_ROOT__OP:
                return getOp() != null;
            case FxtranPackage.DOCUMENT_ROOT__OP_E:
                return getOpE() != null;
            case FxtranPackage.DOCUMENT_ROOT__OPEN_STMT:
                return getOpenStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM:
                return getOutputItem() != null;
            case FxtranPackage.DOCUMENT_ROOT__OUTPUT_ITEM_LT:
                return getOutputItemLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_E:
                return getParensE() != null;
            case FxtranPackage.DOCUMENT_ROOT__PARENS_R:
                return getParensR() != null;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_ASTMT:
                return getPointerAStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__POINTER_STMT:
                return getPointerStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__PREFIX:
                return PREFIX_EDEFAULT == null ? getPrefix() != null : !PREFIX_EDEFAULT.equals(getPrefix());
            case FxtranPackage.DOCUMENT_ROOT__PRIVATE_STMT:
                return PRIVATE_STMT_EDEFAULT == null ? getPrivateStmt() != null : !PRIVATE_STMT_EDEFAULT.equals(getPrivateStmt());
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_DESIGNATOR:
                return getProcedureDesignator() != null;
            case FxtranPackage.DOCUMENT_ROOT__PROCEDURE_STMT:
                return getProcedureStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_N:
                return getProgramN() != null;
            case FxtranPackage.DOCUMENT_ROOT__PROGRAM_STMT:
                return getProgramStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__PUBLIC_STMT:
                return getPublicStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__RLT:
                return getRLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__READ_STMT:
                return getReadStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__RENAME:
                return getRename() != null;
            case FxtranPackage.DOCUMENT_ROOT__RENAME_LT:
                return getRenameLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__RESULT_SPEC:
                return getResultSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__RETURN_STMT:
                return RETURN_STMT_EDEFAULT == null ? getReturnStmt() != null : !RETURN_STMT_EDEFAULT.equals(getReturnStmt());
            case FxtranPackage.DOCUMENT_ROOT__S:
                return S_EDEFAULT == null ? getS() != null : !S_EDEFAULT.equals(getS());
            case FxtranPackage.DOCUMENT_ROOT__SAVE_STMT:
                return SAVE_STMT_EDEFAULT == null ? getSaveStmt() != null : !SAVE_STMT_EDEFAULT.equals(getSaveStmt());
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT:
                return getSectionSubscript() != null;
            case FxtranPackage.DOCUMENT_ROOT__SECTION_SUBSCRIPT_LT:
                return getSectionSubscriptLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__SELECT_CASE_STMT:
                return getSelectCaseStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC:
                return getShapeSpec() != null;
            case FxtranPackage.DOCUMENT_ROOT__SHAPE_SPEC_LT:
                return getShapeSpecLT() != null;
            case FxtranPackage.DOCUMENT_ROOT__STAR_E:
                return STAR_E_EDEFAULT == null ? getStarE() != null : !STAR_E_EDEFAULT.equals(getStarE());
            case FxtranPackage.DOCUMENT_ROOT__STOP_CODE:
                return STOP_CODE_EDEFAULT == null ? getStopCode() != null : !STOP_CODE_EDEFAULT.equals(getStopCode());
            case FxtranPackage.DOCUMENT_ROOT__STOP_STMT:
                return getStopStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__STRING_E:
                return getStringE() != null;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_N:
                return getSubroutineN() != null;
            case FxtranPackage.DOCUMENT_ROOT__SUBROUTINE_STMT:
                return getSubroutineStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__TDECL_STMT:
                return getTDeclStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__TN:
                return getTN() != null;
            case FxtranPackage.DOCUMENT_ROOT__TSTMT:
                return getTStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__TEST_E:
                return getTestE() != null;
            case FxtranPackage.DOCUMENT_ROOT__UPPER_BOUND:
                return getUpperBound() != null;
            case FxtranPackage.DOCUMENT_ROOT__USE_N:
                return getUseN() != null;
            case FxtranPackage.DOCUMENT_ROOT__USE_STMT:
                return getUseStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__V:
                return getV() != null;
            case FxtranPackage.DOCUMENT_ROOT__VN:
                return getVN() != null;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_CONSTRUCT_STMT:
                return getWhereConstructStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__WHERE_STMT:
                return getWhereStmt() != null;
            case FxtranPackage.DOCUMENT_ROOT__WRITE_STMT:
                return getWriteStmt() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl
