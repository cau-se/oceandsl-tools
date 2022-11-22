/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.tools.sar.fxtran.ArgSpecType;
import org.oceandsl.tools.sar.fxtran.CallStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.ProcedureDesignatorType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CallStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CallStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CallStmtTypeImpl#getArgSpec <em>Arg Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CallStmtTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CallStmtTypeImpl#getProcedureDesignator <em>Procedure Designator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallStmtTypeImpl extends MinimalEObjectImpl.Container implements CallStmtType {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CallStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getCallStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.CALL_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getCallStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgSpecType> getArgSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getCallStmtType_ArgSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getCallStmtType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ProcedureDesignatorType> getProcedureDesignator() {
        return getGroup().list(FxtranPackage.eINSTANCE.getCallStmtType_ProcedureDesignator());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.CALL_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.CALL_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.CALL_STMT_TYPE__ARG_SPEC:
                return ((InternalEList<?>)getArgSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.CALL_STMT_TYPE__PROCEDURE_DESIGNATOR:
                return ((InternalEList<?>)getProcedureDesignator()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.CALL_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.CALL_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.CALL_STMT_TYPE__ARG_SPEC:
                return getArgSpec();
            case FxtranPackage.CALL_STMT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.CALL_STMT_TYPE__PROCEDURE_DESIGNATOR:
                return getProcedureDesignator();
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
            case FxtranPackage.CALL_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.CALL_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.CALL_STMT_TYPE__ARG_SPEC:
                getArgSpec().clear();
                getArgSpec().addAll((Collection<? extends ArgSpecType>)newValue);
                return;
            case FxtranPackage.CALL_STMT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.CALL_STMT_TYPE__PROCEDURE_DESIGNATOR:
                getProcedureDesignator().clear();
                getProcedureDesignator().addAll((Collection<? extends ProcedureDesignatorType>)newValue);
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
            case FxtranPackage.CALL_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.CALL_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.CALL_STMT_TYPE__ARG_SPEC:
                getArgSpec().clear();
                return;
            case FxtranPackage.CALL_STMT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.CALL_STMT_TYPE__PROCEDURE_DESIGNATOR:
                getProcedureDesignator().clear();
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
            case FxtranPackage.CALL_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.CALL_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.CALL_STMT_TYPE__ARG_SPEC:
                return !getArgSpec().isEmpty();
            case FxtranPackage.CALL_STMT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.CALL_STMT_TYPE__PROCEDURE_DESIGNATOR:
                return !getProcedureDesignator().isEmpty();
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

} //CallStmtTypeImpl
