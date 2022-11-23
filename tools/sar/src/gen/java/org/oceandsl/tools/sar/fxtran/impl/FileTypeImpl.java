/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.tools.sar.fxtran.AStmtType;
import org.oceandsl.tools.sar.fxtran.AllocateStmtType;
import org.oceandsl.tools.sar.fxtran.CallStmtType;
import org.oceandsl.tools.sar.fxtran.CaseStmtType;
import org.oceandsl.tools.sar.fxtran.CloseStmtType;
import org.oceandsl.tools.sar.fxtran.ComponentDeclStmtType;
import org.oceandsl.tools.sar.fxtran.DeallocateStmtType;
import org.oceandsl.tools.sar.fxtran.DoStmtType;
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
import org.oceandsl.tools.sar.fxtran.FileType;
import org.oceandsl.tools.sar.fxtran.ForallConstructStmtType;
import org.oceandsl.tools.sar.fxtran.ForallStmtType;
import org.oceandsl.tools.sar.fxtran.FunctionStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IfStmtType;
import org.oceandsl.tools.sar.fxtran.IfThenStmtType;
import org.oceandsl.tools.sar.fxtran.InquireStmtType;
import org.oceandsl.tools.sar.fxtran.InterfaceStmtType;
import org.oceandsl.tools.sar.fxtran.ModuleStmtType;
import org.oceandsl.tools.sar.fxtran.NamelistStmtType;
import org.oceandsl.tools.sar.fxtran.NullifyStmtType;
import org.oceandsl.tools.sar.fxtran.OpenStmtType;
import org.oceandsl.tools.sar.fxtran.PointerAStmtType;
import org.oceandsl.tools.sar.fxtran.PointerStmtType;
import org.oceandsl.tools.sar.fxtran.ProcedureStmtType;
import org.oceandsl.tools.sar.fxtran.ProgramStmtType;
import org.oceandsl.tools.sar.fxtran.PublicStmtType;
import org.oceandsl.tools.sar.fxtran.ReadStmtType;
import org.oceandsl.tools.sar.fxtran.SelectCaseStmtType;
import org.oceandsl.tools.sar.fxtran.StopStmtType;
import org.oceandsl.tools.sar.fxtran.SubroutineStmtType;
import org.oceandsl.tools.sar.fxtran.TDeclStmtType;
import org.oceandsl.tools.sar.fxtran.TStmtType;
import org.oceandsl.tools.sar.fxtran.UseStmtType;
import org.oceandsl.tools.sar.fxtran.WhereConstructStmtType;
import org.oceandsl.tools.sar.fxtran.WhereStmtType;
import org.oceandsl.tools.sar.fxtran.WriteStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getC <em>C</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getAStmt <em>AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getAllocateStmt <em>Allocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getCallStmt <em>Call Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getDeallocateStmt <em>Deallocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getExitStmt <em>Exit Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getPointerAStmt <em>Pointer AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getReturnStmt <em>Return Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getWhereStmt <em>Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getTDeclStmt <em>TDecl Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getTStmt <em>TStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getCaseStmt <em>Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getCloseStmt <em>Close Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getComponentDeclStmt <em>Component Decl Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getContainsStmt <em>Contains Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getCpp <em>Cpp</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getDoStmt <em>Do Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getElseIfStmt <em>Else If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getElseStmt <em>Else Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getElseWhereStmt <em>Else Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndTStmt <em>End TStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndDoStmt <em>End Do Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndForallStmt <em>End Forall Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndFunctionStmt <em>End Function Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndIfStmt <em>End If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndInterfaceStmt <em>End Interface Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndSelectCaseStmt <em>End Select Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndSubroutineStmt <em>End Subroutine Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndWhereStmt <em>End Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getForallConstructStmt <em>Forall Construct Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getForallStmt <em>Forall Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getFunctionStmt <em>Function Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getIfStmt <em>If Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getIfThenStmt <em>If Then Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getImplicitNoneStmt <em>Implicit None Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getInquireStmt <em>Inquire Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getInterfaceStmt <em>Interface Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getModuleStmt <em>Module Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getNamelistStmt <em>Namelist Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getNullifyStmt <em>Nullify Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getOpenStmt <em>Open Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getPointerStmt <em>Pointer Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getPrivateStmt <em>Private Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getProcedureStmt <em>Procedure Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getProgramStmt <em>Program Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getPublicStmt <em>Public Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getReadStmt <em>Read Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getSaveStmt <em>Save Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getSelectCaseStmt <em>Select Case Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getStopStmt <em>Stop Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getSubroutineStmt <em>Subroutine Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getUseStmt <em>Use Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getWhereConstructStmt <em>Where Construct Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getWriteStmt <em>Write Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndModuleStmt <em>End Module Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getEndProgramStmt <em>End Program Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FileTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FileTypeImpl extends MinimalEObjectImpl.Container implements FileType {
    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap group;

    /**
     * The cached value of the '{@link #getEndModuleStmt() <em>End Module Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndModuleStmt()
     * @generated
     * @ordered
     */
    protected EndModuleStmtType endModuleStmt;

    /**
     * The cached value of the '{@link #getEndProgramStmt() <em>End Program Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndProgramStmt()
     * @generated
     * @ordered
     */
    protected EndProgramStmtType endProgramStmt;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FileTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getFileType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, FxtranPackage.FILE_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getC() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_C());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AStmtType> getAStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_AStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AllocateStmtType> getAllocateStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_AllocateStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<CallStmtType> getCallStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_CallStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DeallocateStmtType> getDeallocateStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_DeallocateStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getExitStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ExitStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PointerAStmtType> getPointerAStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_PointerAStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getReturnStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ReturnStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<WhereStmtType> getWhereStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_WhereStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TDeclStmtType> getTDeclStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_TDeclStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TStmtType> getTStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_TStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<CaseStmtType> getCaseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_CaseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<CloseStmtType> getCloseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_CloseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ComponentDeclStmtType> getComponentDeclStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ComponentDeclStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getContainsStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ContainsStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCpp() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_Cpp());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DoStmtType> getDoStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_DoStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ElseIfStmtType> getElseIfStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ElseIfStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getElseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ElseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getElseWhereStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ElseWhereStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndTStmtType> getEndTStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndTStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndDoStmtType> getEndDoStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndDoStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndForallStmtType> getEndForallStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndForallStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndFunctionStmtType> getEndFunctionStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndFunctionStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getEndIfStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndIfStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndInterfaceStmtType> getEndInterfaceStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndInterfaceStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndSelectCaseStmtType> getEndSelectCaseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndSelectCaseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EndSubroutineStmtType> getEndSubroutineStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndSubroutineStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getEndWhereStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_EndWhereStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ForallConstructStmtType> getForallConstructStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ForallConstructStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ForallStmtType> getForallStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ForallStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FunctionStmtType> getFunctionStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_FunctionStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IfStmtType> getIfStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_IfStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IfThenStmtType> getIfThenStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_IfThenStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getImplicitNoneStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ImplicitNoneStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<InquireStmtType> getInquireStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_InquireStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<InterfaceStmtType> getInterfaceStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_InterfaceStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ModuleStmtType> getModuleStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ModuleStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamelistStmtType> getNamelistStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_NamelistStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NullifyStmtType> getNullifyStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_NullifyStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OpenStmtType> getOpenStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_OpenStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PointerStmtType> getPointerStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_PointerStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getPrivateStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_PrivateStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ProcedureStmtType> getProcedureStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ProcedureStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ProgramStmtType> getProgramStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ProgramStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PublicStmtType> getPublicStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_PublicStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ReadStmtType> getReadStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_ReadStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getSaveStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_SaveStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SelectCaseStmtType> getSelectCaseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_SelectCaseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<StopStmtType> getStopStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_StopStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SubroutineStmtType> getSubroutineStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_SubroutineStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<UseStmtType> getUseStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_UseStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<WhereConstructStmtType> getWhereConstructStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_WhereConstructStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<WriteStmtType> getWriteStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFileType_WriteStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndModuleStmtType getEndModuleStmt() {
        return endModuleStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndModuleStmt(EndModuleStmtType newEndModuleStmt, NotificationChain msgs) {
        EndModuleStmtType oldEndModuleStmt = endModuleStmt;
        endModuleStmt = newEndModuleStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.FILE_TYPE__END_MODULE_STMT, oldEndModuleStmt, newEndModuleStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndModuleStmt(EndModuleStmtType newEndModuleStmt) {
        if (newEndModuleStmt != endModuleStmt) {
            NotificationChain msgs = null;
            if (endModuleStmt != null)
                msgs = ((InternalEObject)endModuleStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.FILE_TYPE__END_MODULE_STMT, null, msgs);
            if (newEndModuleStmt != null)
                msgs = ((InternalEObject)newEndModuleStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.FILE_TYPE__END_MODULE_STMT, null, msgs);
            msgs = basicSetEndModuleStmt(newEndModuleStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.FILE_TYPE__END_MODULE_STMT, newEndModuleStmt, newEndModuleStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndProgramStmtType getEndProgramStmt() {
        return endProgramStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndProgramStmt(EndProgramStmtType newEndProgramStmt, NotificationChain msgs) {
        EndProgramStmtType oldEndProgramStmt = endProgramStmt;
        endProgramStmt = newEndProgramStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.FILE_TYPE__END_PROGRAM_STMT, oldEndProgramStmt, newEndProgramStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndProgramStmt(EndProgramStmtType newEndProgramStmt) {
        if (newEndProgramStmt != endProgramStmt) {
            NotificationChain msgs = null;
            if (endProgramStmt != null)
                msgs = ((InternalEObject)endProgramStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.FILE_TYPE__END_PROGRAM_STMT, null, msgs);
            if (newEndProgramStmt != null)
                msgs = ((InternalEObject)newEndProgramStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.FILE_TYPE__END_PROGRAM_STMT, null, msgs);
            msgs = basicSetEndProgramStmt(newEndProgramStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.FILE_TYPE__END_PROGRAM_STMT, newEndProgramStmt, newEndProgramStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.FILE_TYPE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.FILE_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__ASTMT:
                return ((InternalEList<?>)getAStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__ALLOCATE_STMT:
                return ((InternalEList<?>)getAllocateStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__CALL_STMT:
                return ((InternalEList<?>)getCallStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__DEALLOCATE_STMT:
                return ((InternalEList<?>)getDeallocateStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__POINTER_ASTMT:
                return ((InternalEList<?>)getPointerAStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__WHERE_STMT:
                return ((InternalEList<?>)getWhereStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__TDECL_STMT:
                return ((InternalEList<?>)getTDeclStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__TSTMT:
                return ((InternalEList<?>)getTStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__CASE_STMT:
                return ((InternalEList<?>)getCaseStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__CLOSE_STMT:
                return ((InternalEList<?>)getCloseStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__COMPONENT_DECL_STMT:
                return ((InternalEList<?>)getComponentDeclStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__DO_STMT:
                return ((InternalEList<?>)getDoStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__ELSE_IF_STMT:
                return ((InternalEList<?>)getElseIfStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_TSTMT:
                return ((InternalEList<?>)getEndTStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_DO_STMT:
                return ((InternalEList<?>)getEndDoStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_FORALL_STMT:
                return ((InternalEList<?>)getEndForallStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_FUNCTION_STMT:
                return ((InternalEList<?>)getEndFunctionStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_INTERFACE_STMT:
                return ((InternalEList<?>)getEndInterfaceStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_SELECT_CASE_STMT:
                return ((InternalEList<?>)getEndSelectCaseStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_SUBROUTINE_STMT:
                return ((InternalEList<?>)getEndSubroutineStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__FORALL_CONSTRUCT_STMT:
                return ((InternalEList<?>)getForallConstructStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__FORALL_STMT:
                return ((InternalEList<?>)getForallStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__FUNCTION_STMT:
                return ((InternalEList<?>)getFunctionStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__IF_STMT:
                return ((InternalEList<?>)getIfStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__IF_THEN_STMT:
                return ((InternalEList<?>)getIfThenStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__INQUIRE_STMT:
                return ((InternalEList<?>)getInquireStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__INTERFACE_STMT:
                return ((InternalEList<?>)getInterfaceStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__MODULE_STMT:
                return ((InternalEList<?>)getModuleStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__NAMELIST_STMT:
                return ((InternalEList<?>)getNamelistStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__NULLIFY_STMT:
                return ((InternalEList<?>)getNullifyStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__OPEN_STMT:
                return ((InternalEList<?>)getOpenStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__POINTER_STMT:
                return ((InternalEList<?>)getPointerStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__PROCEDURE_STMT:
                return ((InternalEList<?>)getProcedureStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__PROGRAM_STMT:
                return ((InternalEList<?>)getProgramStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__PUBLIC_STMT:
                return ((InternalEList<?>)getPublicStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__READ_STMT:
                return ((InternalEList<?>)getReadStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__SELECT_CASE_STMT:
                return ((InternalEList<?>)getSelectCaseStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__STOP_STMT:
                return ((InternalEList<?>)getStopStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__SUBROUTINE_STMT:
                return ((InternalEList<?>)getSubroutineStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__USE_STMT:
                return ((InternalEList<?>)getUseStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__WHERE_CONSTRUCT_STMT:
                return ((InternalEList<?>)getWhereConstructStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__WRITE_STMT:
                return ((InternalEList<?>)getWriteStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FILE_TYPE__END_MODULE_STMT:
                return basicSetEndModuleStmt(null, msgs);
            case FxtranPackage.FILE_TYPE__END_PROGRAM_STMT:
                return basicSetEndProgramStmt(null, msgs);
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
            case FxtranPackage.FILE_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.FILE_TYPE__C:
                return getC();
            case FxtranPackage.FILE_TYPE__ASTMT:
                return getAStmt();
            case FxtranPackage.FILE_TYPE__ALLOCATE_STMT:
                return getAllocateStmt();
            case FxtranPackage.FILE_TYPE__CALL_STMT:
                return getCallStmt();
            case FxtranPackage.FILE_TYPE__DEALLOCATE_STMT:
                return getDeallocateStmt();
            case FxtranPackage.FILE_TYPE__EXIT_STMT:
                return getExitStmt();
            case FxtranPackage.FILE_TYPE__POINTER_ASTMT:
                return getPointerAStmt();
            case FxtranPackage.FILE_TYPE__RETURN_STMT:
                return getReturnStmt();
            case FxtranPackage.FILE_TYPE__WHERE_STMT:
                return getWhereStmt();
            case FxtranPackage.FILE_TYPE__TDECL_STMT:
                return getTDeclStmt();
            case FxtranPackage.FILE_TYPE__TSTMT:
                return getTStmt();
            case FxtranPackage.FILE_TYPE__CASE_STMT:
                return getCaseStmt();
            case FxtranPackage.FILE_TYPE__CLOSE_STMT:
                return getCloseStmt();
            case FxtranPackage.FILE_TYPE__COMPONENT_DECL_STMT:
                return getComponentDeclStmt();
            case FxtranPackage.FILE_TYPE__CONTAINS_STMT:
                return getContainsStmt();
            case FxtranPackage.FILE_TYPE__CPP:
                return getCpp();
            case FxtranPackage.FILE_TYPE__DO_STMT:
                return getDoStmt();
            case FxtranPackage.FILE_TYPE__ELSE_IF_STMT:
                return getElseIfStmt();
            case FxtranPackage.FILE_TYPE__ELSE_STMT:
                return getElseStmt();
            case FxtranPackage.FILE_TYPE__ELSE_WHERE_STMT:
                return getElseWhereStmt();
            case FxtranPackage.FILE_TYPE__END_TSTMT:
                return getEndTStmt();
            case FxtranPackage.FILE_TYPE__END_DO_STMT:
                return getEndDoStmt();
            case FxtranPackage.FILE_TYPE__END_FORALL_STMT:
                return getEndForallStmt();
            case FxtranPackage.FILE_TYPE__END_FUNCTION_STMT:
                return getEndFunctionStmt();
            case FxtranPackage.FILE_TYPE__END_IF_STMT:
                return getEndIfStmt();
            case FxtranPackage.FILE_TYPE__END_INTERFACE_STMT:
                return getEndInterfaceStmt();
            case FxtranPackage.FILE_TYPE__END_SELECT_CASE_STMT:
                return getEndSelectCaseStmt();
            case FxtranPackage.FILE_TYPE__END_SUBROUTINE_STMT:
                return getEndSubroutineStmt();
            case FxtranPackage.FILE_TYPE__END_WHERE_STMT:
                return getEndWhereStmt();
            case FxtranPackage.FILE_TYPE__FORALL_CONSTRUCT_STMT:
                return getForallConstructStmt();
            case FxtranPackage.FILE_TYPE__FORALL_STMT:
                return getForallStmt();
            case FxtranPackage.FILE_TYPE__FUNCTION_STMT:
                return getFunctionStmt();
            case FxtranPackage.FILE_TYPE__IF_STMT:
                return getIfStmt();
            case FxtranPackage.FILE_TYPE__IF_THEN_STMT:
                return getIfThenStmt();
            case FxtranPackage.FILE_TYPE__IMPLICIT_NONE_STMT:
                return getImplicitNoneStmt();
            case FxtranPackage.FILE_TYPE__INQUIRE_STMT:
                return getInquireStmt();
            case FxtranPackage.FILE_TYPE__INTERFACE_STMT:
                return getInterfaceStmt();
            case FxtranPackage.FILE_TYPE__MODULE_STMT:
                return getModuleStmt();
            case FxtranPackage.FILE_TYPE__NAMELIST_STMT:
                return getNamelistStmt();
            case FxtranPackage.FILE_TYPE__NULLIFY_STMT:
                return getNullifyStmt();
            case FxtranPackage.FILE_TYPE__OPEN_STMT:
                return getOpenStmt();
            case FxtranPackage.FILE_TYPE__POINTER_STMT:
                return getPointerStmt();
            case FxtranPackage.FILE_TYPE__PRIVATE_STMT:
                return getPrivateStmt();
            case FxtranPackage.FILE_TYPE__PROCEDURE_STMT:
                return getProcedureStmt();
            case FxtranPackage.FILE_TYPE__PROGRAM_STMT:
                return getProgramStmt();
            case FxtranPackage.FILE_TYPE__PUBLIC_STMT:
                return getPublicStmt();
            case FxtranPackage.FILE_TYPE__READ_STMT:
                return getReadStmt();
            case FxtranPackage.FILE_TYPE__SAVE_STMT:
                return getSaveStmt();
            case FxtranPackage.FILE_TYPE__SELECT_CASE_STMT:
                return getSelectCaseStmt();
            case FxtranPackage.FILE_TYPE__STOP_STMT:
                return getStopStmt();
            case FxtranPackage.FILE_TYPE__SUBROUTINE_STMT:
                return getSubroutineStmt();
            case FxtranPackage.FILE_TYPE__USE_STMT:
                return getUseStmt();
            case FxtranPackage.FILE_TYPE__WHERE_CONSTRUCT_STMT:
                return getWhereConstructStmt();
            case FxtranPackage.FILE_TYPE__WRITE_STMT:
                return getWriteStmt();
            case FxtranPackage.FILE_TYPE__END_MODULE_STMT:
                return getEndModuleStmt();
            case FxtranPackage.FILE_TYPE__END_PROGRAM_STMT:
                return getEndProgramStmt();
            case FxtranPackage.FILE_TYPE__NAME:
                return getName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FxtranPackage.FILE_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.FILE_TYPE__C:
                getC().clear();
                getC().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__ASTMT:
                getAStmt().clear();
                getAStmt().addAll((Collection<? extends AStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__ALLOCATE_STMT:
                getAllocateStmt().clear();
                getAllocateStmt().addAll((Collection<? extends AllocateStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__CALL_STMT:
                getCallStmt().clear();
                getCallStmt().addAll((Collection<? extends CallStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__DEALLOCATE_STMT:
                getDeallocateStmt().clear();
                getDeallocateStmt().addAll((Collection<? extends DeallocateStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__EXIT_STMT:
                getExitStmt().clear();
                getExitStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__POINTER_ASTMT:
                getPointerAStmt().clear();
                getPointerAStmt().addAll((Collection<? extends PointerAStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__RETURN_STMT:
                getReturnStmt().clear();
                getReturnStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__WHERE_STMT:
                getWhereStmt().clear();
                getWhereStmt().addAll((Collection<? extends WhereStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__TDECL_STMT:
                getTDeclStmt().clear();
                getTDeclStmt().addAll((Collection<? extends TDeclStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__TSTMT:
                getTStmt().clear();
                getTStmt().addAll((Collection<? extends TStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__CASE_STMT:
                getCaseStmt().clear();
                getCaseStmt().addAll((Collection<? extends CaseStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__CLOSE_STMT:
                getCloseStmt().clear();
                getCloseStmt().addAll((Collection<? extends CloseStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__COMPONENT_DECL_STMT:
                getComponentDeclStmt().clear();
                getComponentDeclStmt().addAll((Collection<? extends ComponentDeclStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__CONTAINS_STMT:
                getContainsStmt().clear();
                getContainsStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__CPP:
                getCpp().clear();
                getCpp().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__DO_STMT:
                getDoStmt().clear();
                getDoStmt().addAll((Collection<? extends DoStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__ELSE_IF_STMT:
                getElseIfStmt().clear();
                getElseIfStmt().addAll((Collection<? extends ElseIfStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__ELSE_STMT:
                getElseStmt().clear();
                getElseStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__ELSE_WHERE_STMT:
                getElseWhereStmt().clear();
                getElseWhereStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_TSTMT:
                getEndTStmt().clear();
                getEndTStmt().addAll((Collection<? extends EndTStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_DO_STMT:
                getEndDoStmt().clear();
                getEndDoStmt().addAll((Collection<? extends EndDoStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_FORALL_STMT:
                getEndForallStmt().clear();
                getEndForallStmt().addAll((Collection<? extends EndForallStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_FUNCTION_STMT:
                getEndFunctionStmt().clear();
                getEndFunctionStmt().addAll((Collection<? extends EndFunctionStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_IF_STMT:
                getEndIfStmt().clear();
                getEndIfStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_INTERFACE_STMT:
                getEndInterfaceStmt().clear();
                getEndInterfaceStmt().addAll((Collection<? extends EndInterfaceStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_SELECT_CASE_STMT:
                getEndSelectCaseStmt().clear();
                getEndSelectCaseStmt().addAll((Collection<? extends EndSelectCaseStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_SUBROUTINE_STMT:
                getEndSubroutineStmt().clear();
                getEndSubroutineStmt().addAll((Collection<? extends EndSubroutineStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_WHERE_STMT:
                getEndWhereStmt().clear();
                getEndWhereStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__FORALL_CONSTRUCT_STMT:
                getForallConstructStmt().clear();
                getForallConstructStmt().addAll((Collection<? extends ForallConstructStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__FORALL_STMT:
                getForallStmt().clear();
                getForallStmt().addAll((Collection<? extends ForallStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__FUNCTION_STMT:
                getFunctionStmt().clear();
                getFunctionStmt().addAll((Collection<? extends FunctionStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__IF_STMT:
                getIfStmt().clear();
                getIfStmt().addAll((Collection<? extends IfStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__IF_THEN_STMT:
                getIfThenStmt().clear();
                getIfThenStmt().addAll((Collection<? extends IfThenStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__IMPLICIT_NONE_STMT:
                getImplicitNoneStmt().clear();
                getImplicitNoneStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__INQUIRE_STMT:
                getInquireStmt().clear();
                getInquireStmt().addAll((Collection<? extends InquireStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__INTERFACE_STMT:
                getInterfaceStmt().clear();
                getInterfaceStmt().addAll((Collection<? extends InterfaceStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__MODULE_STMT:
                getModuleStmt().clear();
                getModuleStmt().addAll((Collection<? extends ModuleStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__NAMELIST_STMT:
                getNamelistStmt().clear();
                getNamelistStmt().addAll((Collection<? extends NamelistStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__NULLIFY_STMT:
                getNullifyStmt().clear();
                getNullifyStmt().addAll((Collection<? extends NullifyStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__OPEN_STMT:
                getOpenStmt().clear();
                getOpenStmt().addAll((Collection<? extends OpenStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__POINTER_STMT:
                getPointerStmt().clear();
                getPointerStmt().addAll((Collection<? extends PointerStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__PRIVATE_STMT:
                getPrivateStmt().clear();
                getPrivateStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__PROCEDURE_STMT:
                getProcedureStmt().clear();
                getProcedureStmt().addAll((Collection<? extends ProcedureStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__PROGRAM_STMT:
                getProgramStmt().clear();
                getProgramStmt().addAll((Collection<? extends ProgramStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__PUBLIC_STMT:
                getPublicStmt().clear();
                getPublicStmt().addAll((Collection<? extends PublicStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__READ_STMT:
                getReadStmt().clear();
                getReadStmt().addAll((Collection<? extends ReadStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__SAVE_STMT:
                getSaveStmt().clear();
                getSaveStmt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__SELECT_CASE_STMT:
                getSelectCaseStmt().clear();
                getSelectCaseStmt().addAll((Collection<? extends SelectCaseStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__STOP_STMT:
                getStopStmt().clear();
                getStopStmt().addAll((Collection<? extends StopStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__SUBROUTINE_STMT:
                getSubroutineStmt().clear();
                getSubroutineStmt().addAll((Collection<? extends SubroutineStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__USE_STMT:
                getUseStmt().clear();
                getUseStmt().addAll((Collection<? extends UseStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__WHERE_CONSTRUCT_STMT:
                getWhereConstructStmt().clear();
                getWhereConstructStmt().addAll((Collection<? extends WhereConstructStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__WRITE_STMT:
                getWriteStmt().clear();
                getWriteStmt().addAll((Collection<? extends WriteStmtType>)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_MODULE_STMT:
                setEndModuleStmt((EndModuleStmtType)newValue);
                return;
            case FxtranPackage.FILE_TYPE__END_PROGRAM_STMT:
                setEndProgramStmt((EndProgramStmtType)newValue);
                return;
            case FxtranPackage.FILE_TYPE__NAME:
                setName((String)newValue);
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
            case FxtranPackage.FILE_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.FILE_TYPE__C:
                getC().clear();
                return;
            case FxtranPackage.FILE_TYPE__ASTMT:
                getAStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__ALLOCATE_STMT:
                getAllocateStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__CALL_STMT:
                getCallStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__DEALLOCATE_STMT:
                getDeallocateStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__EXIT_STMT:
                getExitStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__POINTER_ASTMT:
                getPointerAStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__RETURN_STMT:
                getReturnStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__WHERE_STMT:
                getWhereStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__TDECL_STMT:
                getTDeclStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__TSTMT:
                getTStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__CASE_STMT:
                getCaseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__CLOSE_STMT:
                getCloseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__COMPONENT_DECL_STMT:
                getComponentDeclStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__CONTAINS_STMT:
                getContainsStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__CPP:
                getCpp().clear();
                return;
            case FxtranPackage.FILE_TYPE__DO_STMT:
                getDoStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__ELSE_IF_STMT:
                getElseIfStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__ELSE_STMT:
                getElseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__ELSE_WHERE_STMT:
                getElseWhereStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_TSTMT:
                getEndTStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_DO_STMT:
                getEndDoStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_FORALL_STMT:
                getEndForallStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_FUNCTION_STMT:
                getEndFunctionStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_IF_STMT:
                getEndIfStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_INTERFACE_STMT:
                getEndInterfaceStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_SELECT_CASE_STMT:
                getEndSelectCaseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_SUBROUTINE_STMT:
                getEndSubroutineStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_WHERE_STMT:
                getEndWhereStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__FORALL_CONSTRUCT_STMT:
                getForallConstructStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__FORALL_STMT:
                getForallStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__FUNCTION_STMT:
                getFunctionStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__IF_STMT:
                getIfStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__IF_THEN_STMT:
                getIfThenStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__IMPLICIT_NONE_STMT:
                getImplicitNoneStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__INQUIRE_STMT:
                getInquireStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__INTERFACE_STMT:
                getInterfaceStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__MODULE_STMT:
                getModuleStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__NAMELIST_STMT:
                getNamelistStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__NULLIFY_STMT:
                getNullifyStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__OPEN_STMT:
                getOpenStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__POINTER_STMT:
                getPointerStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__PRIVATE_STMT:
                getPrivateStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__PROCEDURE_STMT:
                getProcedureStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__PROGRAM_STMT:
                getProgramStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__PUBLIC_STMT:
                getPublicStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__READ_STMT:
                getReadStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__SAVE_STMT:
                getSaveStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__SELECT_CASE_STMT:
                getSelectCaseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__STOP_STMT:
                getStopStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__SUBROUTINE_STMT:
                getSubroutineStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__USE_STMT:
                getUseStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__WHERE_CONSTRUCT_STMT:
                getWhereConstructStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__WRITE_STMT:
                getWriteStmt().clear();
                return;
            case FxtranPackage.FILE_TYPE__END_MODULE_STMT:
                setEndModuleStmt((EndModuleStmtType)null);
                return;
            case FxtranPackage.FILE_TYPE__END_PROGRAM_STMT:
                setEndProgramStmt((EndProgramStmtType)null);
                return;
            case FxtranPackage.FILE_TYPE__NAME:
                setName(NAME_EDEFAULT);
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
            case FxtranPackage.FILE_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case FxtranPackage.FILE_TYPE__C:
                return !getC().isEmpty();
            case FxtranPackage.FILE_TYPE__ASTMT:
                return !getAStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__ALLOCATE_STMT:
                return !getAllocateStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__CALL_STMT:
                return !getCallStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__DEALLOCATE_STMT:
                return !getDeallocateStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__EXIT_STMT:
                return !getExitStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__POINTER_ASTMT:
                return !getPointerAStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__RETURN_STMT:
                return !getReturnStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__WHERE_STMT:
                return !getWhereStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__TDECL_STMT:
                return !getTDeclStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__TSTMT:
                return !getTStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__CASE_STMT:
                return !getCaseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__CLOSE_STMT:
                return !getCloseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__COMPONENT_DECL_STMT:
                return !getComponentDeclStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__CONTAINS_STMT:
                return !getContainsStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__CPP:
                return !getCpp().isEmpty();
            case FxtranPackage.FILE_TYPE__DO_STMT:
                return !getDoStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__ELSE_IF_STMT:
                return !getElseIfStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__ELSE_STMT:
                return !getElseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__ELSE_WHERE_STMT:
                return !getElseWhereStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_TSTMT:
                return !getEndTStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_DO_STMT:
                return !getEndDoStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_FORALL_STMT:
                return !getEndForallStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_FUNCTION_STMT:
                return !getEndFunctionStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_IF_STMT:
                return !getEndIfStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_INTERFACE_STMT:
                return !getEndInterfaceStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_SELECT_CASE_STMT:
                return !getEndSelectCaseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_SUBROUTINE_STMT:
                return !getEndSubroutineStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_WHERE_STMT:
                return !getEndWhereStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__FORALL_CONSTRUCT_STMT:
                return !getForallConstructStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__FORALL_STMT:
                return !getForallStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__FUNCTION_STMT:
                return !getFunctionStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__IF_STMT:
                return !getIfStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__IF_THEN_STMT:
                return !getIfThenStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__IMPLICIT_NONE_STMT:
                return !getImplicitNoneStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__INQUIRE_STMT:
                return !getInquireStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__INTERFACE_STMT:
                return !getInterfaceStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__MODULE_STMT:
                return !getModuleStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__NAMELIST_STMT:
                return !getNamelistStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__NULLIFY_STMT:
                return !getNullifyStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__OPEN_STMT:
                return !getOpenStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__POINTER_STMT:
                return !getPointerStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__PRIVATE_STMT:
                return !getPrivateStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__PROCEDURE_STMT:
                return !getProcedureStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__PROGRAM_STMT:
                return !getProgramStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__PUBLIC_STMT:
                return !getPublicStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__READ_STMT:
                return !getReadStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__SAVE_STMT:
                return !getSaveStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__SELECT_CASE_STMT:
                return !getSelectCaseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__STOP_STMT:
                return !getStopStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__SUBROUTINE_STMT:
                return !getSubroutineStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__USE_STMT:
                return !getUseStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__WHERE_CONSTRUCT_STMT:
                return !getWhereConstructStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__WRITE_STMT:
                return !getWriteStmt().isEmpty();
            case FxtranPackage.FILE_TYPE__END_MODULE_STMT:
                return endModuleStmt != null;
            case FxtranPackage.FILE_TYPE__END_PROGRAM_STMT:
                return endProgramStmt != null;
            case FxtranPackage.FILE_TYPE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (group: ");
        result.append(group);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //FileTypeImpl
