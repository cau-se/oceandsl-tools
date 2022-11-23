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

import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IoControlSpecType;
import org.oceandsl.tools.sar.fxtran.OutputItemLTType;
import org.oceandsl.tools.sar.fxtran.WriteStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Write Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WriteStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WriteStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WriteStmtTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WriteStmtTypeImpl#getIoControlSpec <em>Io Control Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WriteStmtTypeImpl#getOutputItemLT <em>Output Item LT</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WriteStmtTypeImpl extends MinimalEObjectImpl.Container implements WriteStmtType {
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
    protected WriteStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getWriteStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.WRITE_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getWriteStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWriteStmtType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IoControlSpecType> getIoControlSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWriteStmtType_IoControlSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OutputItemLTType> getOutputItemLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWriteStmtType_OutputItemLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.WRITE_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WRITE_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WRITE_STMT_TYPE__IO_CONTROL_SPEC:
                return ((InternalEList<?>)getIoControlSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WRITE_STMT_TYPE__OUTPUT_ITEM_LT:
                return ((InternalEList<?>)getOutputItemLT()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.WRITE_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.WRITE_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.WRITE_STMT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.WRITE_STMT_TYPE__IO_CONTROL_SPEC:
                return getIoControlSpec();
            case FxtranPackage.WRITE_STMT_TYPE__OUTPUT_ITEM_LT:
                return getOutputItemLT();
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
            case FxtranPackage.WRITE_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.WRITE_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.WRITE_STMT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.WRITE_STMT_TYPE__IO_CONTROL_SPEC:
                getIoControlSpec().clear();
                getIoControlSpec().addAll((Collection<? extends IoControlSpecType>)newValue);
                return;
            case FxtranPackage.WRITE_STMT_TYPE__OUTPUT_ITEM_LT:
                getOutputItemLT().clear();
                getOutputItemLT().addAll((Collection<? extends OutputItemLTType>)newValue);
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
            case FxtranPackage.WRITE_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.WRITE_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.WRITE_STMT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.WRITE_STMT_TYPE__IO_CONTROL_SPEC:
                getIoControlSpec().clear();
                return;
            case FxtranPackage.WRITE_STMT_TYPE__OUTPUT_ITEM_LT:
                getOutputItemLT().clear();
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
            case FxtranPackage.WRITE_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.WRITE_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.WRITE_STMT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.WRITE_STMT_TYPE__IO_CONTROL_SPEC:
                return !getIoControlSpec().isEmpty();
            case FxtranPackage.WRITE_STMT_TYPE__OUTPUT_ITEM_LT:
                return !getOutputItemLT().isEmpty();
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

} //WriteStmtTypeImpl
