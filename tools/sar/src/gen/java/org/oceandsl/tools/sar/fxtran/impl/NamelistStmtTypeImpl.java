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
import org.oceandsl.tools.sar.fxtran.NamelistGroupNType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjLTType;
import org.oceandsl.tools.sar.fxtran.NamelistStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Namelist Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistStmtTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistStmtTypeImpl#getNamelistGroupN <em>Namelist Group N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistStmtTypeImpl#getNamelistGroupObjLT <em>Namelist Group Obj LT</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamelistStmtTypeImpl extends MinimalEObjectImpl.Container implements NamelistStmtType {
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
    protected NamelistStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getNamelistStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.NAMELIST_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getNamelistStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistStmtType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamelistGroupNType> getNamelistGroupN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistStmtType_NamelistGroupN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamelistGroupObjLTType> getNamelistGroupObjLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistStmtType_NamelistGroupObjLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.NAMELIST_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.NAMELIST_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_N:
                return ((InternalEList<?>)getNamelistGroupN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_OBJ_LT:
                return ((InternalEList<?>)getNamelistGroupObjLT()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.NAMELIST_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.NAMELIST_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.NAMELIST_STMT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_N:
                return getNamelistGroupN();
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_OBJ_LT:
                return getNamelistGroupObjLT();
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
            case FxtranPackage.NAMELIST_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_N:
                getNamelistGroupN().clear();
                getNamelistGroupN().addAll((Collection<? extends NamelistGroupNType>)newValue);
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_OBJ_LT:
                getNamelistGroupObjLT().clear();
                getNamelistGroupObjLT().addAll((Collection<? extends NamelistGroupObjLTType>)newValue);
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
            case FxtranPackage.NAMELIST_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_N:
                getNamelistGroupN().clear();
                return;
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_OBJ_LT:
                getNamelistGroupObjLT().clear();
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
            case FxtranPackage.NAMELIST_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.NAMELIST_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.NAMELIST_STMT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_N:
                return !getNamelistGroupN().isEmpty();
            case FxtranPackage.NAMELIST_STMT_TYPE__NAMELIST_GROUP_OBJ_LT:
                return !getNamelistGroupObjLT().isEmpty();
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

} //NamelistStmtTypeImpl
