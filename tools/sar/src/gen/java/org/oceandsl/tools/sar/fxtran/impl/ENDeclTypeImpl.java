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

import org.oceandsl.tools.sar.fxtran.ArraySpecType;
import org.oceandsl.tools.sar.fxtran.ENDeclType;
import org.oceandsl.tools.sar.fxtran.ENNType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.InitEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EN Decl Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclTypeImpl#getArraySpec <em>Array Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclTypeImpl#getENN <em>ENN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclTypeImpl#getInitE <em>Init E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ENDeclTypeImpl extends MinimalEObjectImpl.Container implements ENDeclType {
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
    protected ENDeclTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getENDeclType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.EN_DECL_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getENDeclType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArraySpecType> getArraySpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getENDeclType_ArraySpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ENNType> getENN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getENDeclType_ENN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<InitEType> getInitE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getENDeclType_InitE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.EN_DECL_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_TYPE__ARRAY_SPEC:
                return ((InternalEList<?>)getArraySpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_TYPE__ENN:
                return ((InternalEList<?>)getENN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_TYPE__INIT_E:
                return ((InternalEList<?>)getInitE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.EN_DECL_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.EN_DECL_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.EN_DECL_TYPE__ARRAY_SPEC:
                return getArraySpec();
            case FxtranPackage.EN_DECL_TYPE__ENN:
                return getENN();
            case FxtranPackage.EN_DECL_TYPE__INIT_E:
                return getInitE();
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
            case FxtranPackage.EN_DECL_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.EN_DECL_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.EN_DECL_TYPE__ARRAY_SPEC:
                getArraySpec().clear();
                getArraySpec().addAll((Collection<? extends ArraySpecType>)newValue);
                return;
            case FxtranPackage.EN_DECL_TYPE__ENN:
                getENN().clear();
                getENN().addAll((Collection<? extends ENNType>)newValue);
                return;
            case FxtranPackage.EN_DECL_TYPE__INIT_E:
                getInitE().clear();
                getInitE().addAll((Collection<? extends InitEType>)newValue);
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
            case FxtranPackage.EN_DECL_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.EN_DECL_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.EN_DECL_TYPE__ARRAY_SPEC:
                getArraySpec().clear();
                return;
            case FxtranPackage.EN_DECL_TYPE__ENN:
                getENN().clear();
                return;
            case FxtranPackage.EN_DECL_TYPE__INIT_E:
                getInitE().clear();
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
            case FxtranPackage.EN_DECL_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.EN_DECL_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.EN_DECL_TYPE__ARRAY_SPEC:
                return !getArraySpec().isEmpty();
            case FxtranPackage.EN_DECL_TYPE__ENN:
                return !getENN().isEmpty();
            case FxtranPackage.EN_DECL_TYPE__INIT_E:
                return !getInitE().isEmpty();
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

} //ENDeclTypeImpl
