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

import org.oceandsl.tools.sar.fxtran.ActionStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.MaskEType;
import org.oceandsl.tools.sar.fxtran.WhereStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Where Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WhereStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WhereStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WhereStmtTypeImpl#getActionStmt <em>Action Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WhereStmtTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.WhereStmtTypeImpl#getMaskE <em>Mask E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WhereStmtTypeImpl extends MinimalEObjectImpl.Container implements WhereStmtType {
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
    protected WhereStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getWhereStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.WHERE_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getWhereStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ActionStmtType> getActionStmt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWhereStmtType_ActionStmt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWhereStmtType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<MaskEType> getMaskE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getWhereStmtType_MaskE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.WHERE_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WHERE_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WHERE_STMT_TYPE__ACTION_STMT:
                return ((InternalEList<?>)getActionStmt()).basicRemove(otherEnd, msgs);
            case FxtranPackage.WHERE_STMT_TYPE__MASK_E:
                return ((InternalEList<?>)getMaskE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.WHERE_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.WHERE_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.WHERE_STMT_TYPE__ACTION_STMT:
                return getActionStmt();
            case FxtranPackage.WHERE_STMT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.WHERE_STMT_TYPE__MASK_E:
                return getMaskE();
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
            case FxtranPackage.WHERE_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.WHERE_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.WHERE_STMT_TYPE__ACTION_STMT:
                getActionStmt().clear();
                getActionStmt().addAll((Collection<? extends ActionStmtType>)newValue);
                return;
            case FxtranPackage.WHERE_STMT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.WHERE_STMT_TYPE__MASK_E:
                getMaskE().clear();
                getMaskE().addAll((Collection<? extends MaskEType>)newValue);
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
            case FxtranPackage.WHERE_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.WHERE_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.WHERE_STMT_TYPE__ACTION_STMT:
                getActionStmt().clear();
                return;
            case FxtranPackage.WHERE_STMT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.WHERE_STMT_TYPE__MASK_E:
                getMaskE().clear();
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
            case FxtranPackage.WHERE_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.WHERE_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.WHERE_STMT_TYPE__ACTION_STMT:
                return !getActionStmt().isEmpty();
            case FxtranPackage.WHERE_STMT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.WHERE_STMT_TYPE__MASK_E:
                return !getMaskE().isEmpty();
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

} //WhereStmtTypeImpl
