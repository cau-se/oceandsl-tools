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

import org.oceandsl.tools.sar.fxtran.ArgNType;
import org.oceandsl.tools.sar.fxtran.DummyArgLTType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dummy Arg LT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DummyArgLTTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DummyArgLTTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DummyArgLTTypeImpl#getArgN <em>Arg N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DummyArgLTTypeImpl#getCnt <em>Cnt</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DummyArgLTTypeImpl extends MinimalEObjectImpl.Container implements DummyArgLTType {
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
    protected DummyArgLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getDummyArgLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getDummyArgLTType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgNType> getArgN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDummyArgLTType_ArgN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDummyArgLTType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DUMMY_ARG_LT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DUMMY_ARG_LT_TYPE__ARG_N:
                return ((InternalEList<?>)getArgN()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__ARG_N:
                return getArgN();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__CNT:
                return getCnt();
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
            case FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__ARG_N:
                getArgN().clear();
                getArgN().addAll((Collection<? extends ArgNType>)newValue);
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
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
            case FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__ARG_N:
                getArgN().clear();
                return;
            case FxtranPackage.DUMMY_ARG_LT_TYPE__CNT:
                getCnt().clear();
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
            case FxtranPackage.DUMMY_ARG_LT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__ARG_N:
                return !getArgN().isEmpty();
            case FxtranPackage.DUMMY_ARG_LT_TYPE__CNT:
                return !getCnt().isEmpty();
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

} //DummyArgLTTypeImpl
