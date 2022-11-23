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

import org.oceandsl.tools.sar.fxtran.AttributeType;
import org.oceandsl.tools.sar.fxtran.ENDeclLTType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.TDeclStmtType;
import org.oceandsl.tools.sar.fxtran.TSpecType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TDecl Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TDeclStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TDeclStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TDeclStmtTypeImpl#getENDeclLT <em>EN Decl LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TDeclStmtTypeImpl#getTSpec <em>TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TDeclStmtTypeImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TDeclStmtTypeImpl extends MinimalEObjectImpl.Container implements TDeclStmtType {
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
    protected TDeclStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getTDeclStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.TDECL_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getTDeclStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ENDeclLTType> getENDeclLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getTDeclStmtType_ENDeclLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TSpecType> getTSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getTDeclStmtType_TSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AttributeType> getAttribute() {
        return getGroup().list(FxtranPackage.eINSTANCE.getTDeclStmtType_Attribute());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.TDECL_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.TDECL_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.TDECL_STMT_TYPE__EN_DECL_LT:
                return ((InternalEList<?>)getENDeclLT()).basicRemove(otherEnd, msgs);
            case FxtranPackage.TDECL_STMT_TYPE__TSPEC:
                return ((InternalEList<?>)getTSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.TDECL_STMT_TYPE__ATTRIBUTE:
                return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.TDECL_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.TDECL_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.TDECL_STMT_TYPE__EN_DECL_LT:
                return getENDeclLT();
            case FxtranPackage.TDECL_STMT_TYPE__TSPEC:
                return getTSpec();
            case FxtranPackage.TDECL_STMT_TYPE__ATTRIBUTE:
                return getAttribute();
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
            case FxtranPackage.TDECL_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.TDECL_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.TDECL_STMT_TYPE__EN_DECL_LT:
                getENDeclLT().clear();
                getENDeclLT().addAll((Collection<? extends ENDeclLTType>)newValue);
                return;
            case FxtranPackage.TDECL_STMT_TYPE__TSPEC:
                getTSpec().clear();
                getTSpec().addAll((Collection<? extends TSpecType>)newValue);
                return;
            case FxtranPackage.TDECL_STMT_TYPE__ATTRIBUTE:
                getAttribute().clear();
                getAttribute().addAll((Collection<? extends AttributeType>)newValue);
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
            case FxtranPackage.TDECL_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.TDECL_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.TDECL_STMT_TYPE__EN_DECL_LT:
                getENDeclLT().clear();
                return;
            case FxtranPackage.TDECL_STMT_TYPE__TSPEC:
                getTSpec().clear();
                return;
            case FxtranPackage.TDECL_STMT_TYPE__ATTRIBUTE:
                getAttribute().clear();
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
            case FxtranPackage.TDECL_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.TDECL_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.TDECL_STMT_TYPE__EN_DECL_LT:
                return !getENDeclLT().isEmpty();
            case FxtranPackage.TDECL_STMT_TYPE__TSPEC:
                return !getTSpec().isEmpty();
            case FxtranPackage.TDECL_STMT_TYPE__ATTRIBUTE:
                return !getAttribute().isEmpty();
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

} //TDeclStmtTypeImpl
