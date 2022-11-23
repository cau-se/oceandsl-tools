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
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjLTType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Namelist Group Obj LT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjLTTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjLTTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjLTTypeImpl#getC <em>C</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjLTTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjLTTypeImpl#getNamelistGroupObj <em>Namelist Group Obj</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamelistGroupObjLTTypeImpl extends MinimalEObjectImpl.Container implements NamelistGroupObjLTType {
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
    protected NamelistGroupObjLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getNamelistGroupObjLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getNamelistGroupObjLTType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getC() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistGroupObjLTType_C());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistGroupObjLTType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamelistGroupObjType> getNamelistGroupObj() {
        return getGroup().list(FxtranPackage.eINSTANCE.getNamelistGroupObjLTType_NamelistGroupObj());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__NAMELIST_GROUP_OBJ:
                return ((InternalEList<?>)getNamelistGroupObj()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__C:
                return getC();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__NAMELIST_GROUP_OBJ:
                return getNamelistGroupObj();
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__C:
                getC().clear();
                getC().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__NAMELIST_GROUP_OBJ:
                getNamelistGroupObj().clear();
                getNamelistGroupObj().addAll((Collection<? extends NamelistGroupObjType>)newValue);
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__C:
                getC().clear();
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__NAMELIST_GROUP_OBJ:
                getNamelistGroupObj().clear();
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__C:
                return !getC().isEmpty();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.NAMELIST_GROUP_OBJ_LT_TYPE__NAMELIST_GROUP_OBJ:
                return !getNamelistGroupObj().isEmpty();
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

} //NamelistGroupObjLTTypeImpl
