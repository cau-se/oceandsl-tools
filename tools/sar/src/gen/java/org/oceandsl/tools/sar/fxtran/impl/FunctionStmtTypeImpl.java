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

import org.oceandsl.tools.sar.fxtran.DerivedTSpecType;
import org.oceandsl.tools.sar.fxtran.DummyArgLTType;
import org.oceandsl.tools.sar.fxtran.FunctionNType;
import org.oceandsl.tools.sar.fxtran.FunctionStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IntrinsicTSpecType;
import org.oceandsl.tools.sar.fxtran.ResultSpecType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getDerivedTSpec <em>Derived TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getDummyArgLT <em>Dummy Arg LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getFunctionN <em>Function N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getIntrinsicTSpec <em>Intrinsic TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.FunctionStmtTypeImpl#getResultSpec <em>Result Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionStmtTypeImpl extends MinimalEObjectImpl.Container implements FunctionStmtType {
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
    protected FunctionStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getFunctionStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.FUNCTION_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getFunctionStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DerivedTSpecType> getDerivedTSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_DerivedTSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DummyArgLTType> getDummyArgLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_DummyArgLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FunctionNType> getFunctionN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_FunctionN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IntrinsicTSpecType> getIntrinsicTSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_IntrinsicTSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getPrefix() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_Prefix());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ResultSpecType> getResultSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getFunctionStmtType_ResultSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.FUNCTION_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__DERIVED_TSPEC:
                return ((InternalEList<?>)getDerivedTSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__DUMMY_ARG_LT:
                return ((InternalEList<?>)getDummyArgLT()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__FUNCTION_N:
                return ((InternalEList<?>)getFunctionN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__INTRINSIC_TSPEC:
                return ((InternalEList<?>)getIntrinsicTSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.FUNCTION_STMT_TYPE__RESULT_SPEC:
                return ((InternalEList<?>)getResultSpec()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.FUNCTION_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.FUNCTION_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.FUNCTION_STMT_TYPE__DERIVED_TSPEC:
                return getDerivedTSpec();
            case FxtranPackage.FUNCTION_STMT_TYPE__DUMMY_ARG_LT:
                return getDummyArgLT();
            case FxtranPackage.FUNCTION_STMT_TYPE__FUNCTION_N:
                return getFunctionN();
            case FxtranPackage.FUNCTION_STMT_TYPE__INTRINSIC_TSPEC:
                return getIntrinsicTSpec();
            case FxtranPackage.FUNCTION_STMT_TYPE__PREFIX:
                return getPrefix();
            case FxtranPackage.FUNCTION_STMT_TYPE__RESULT_SPEC:
                return getResultSpec();
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
            case FxtranPackage.FUNCTION_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__DERIVED_TSPEC:
                getDerivedTSpec().clear();
                getDerivedTSpec().addAll((Collection<? extends DerivedTSpecType>)newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__DUMMY_ARG_LT:
                getDummyArgLT().clear();
                getDummyArgLT().addAll((Collection<? extends DummyArgLTType>)newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__FUNCTION_N:
                getFunctionN().clear();
                getFunctionN().addAll((Collection<? extends FunctionNType>)newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__INTRINSIC_TSPEC:
                getIntrinsicTSpec().clear();
                getIntrinsicTSpec().addAll((Collection<? extends IntrinsicTSpecType>)newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__PREFIX:
                getPrefix().clear();
                getPrefix().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__RESULT_SPEC:
                getResultSpec().clear();
                getResultSpec().addAll((Collection<? extends ResultSpecType>)newValue);
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
            case FxtranPackage.FUNCTION_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__DERIVED_TSPEC:
                getDerivedTSpec().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__DUMMY_ARG_LT:
                getDummyArgLT().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__FUNCTION_N:
                getFunctionN().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__INTRINSIC_TSPEC:
                getIntrinsicTSpec().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__PREFIX:
                getPrefix().clear();
                return;
            case FxtranPackage.FUNCTION_STMT_TYPE__RESULT_SPEC:
                getResultSpec().clear();
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
            case FxtranPackage.FUNCTION_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__DERIVED_TSPEC:
                return !getDerivedTSpec().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__DUMMY_ARG_LT:
                return !getDummyArgLT().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__FUNCTION_N:
                return !getFunctionN().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__INTRINSIC_TSPEC:
                return !getIntrinsicTSpec().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__PREFIX:
                return !getPrefix().isEmpty();
            case FxtranPackage.FUNCTION_STMT_TYPE__RESULT_SPEC:
                return !getResultSpec().isEmpty();
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

} //FunctionStmtTypeImpl
