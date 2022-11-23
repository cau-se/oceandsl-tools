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

import org.oceandsl.tools.sar.fxtran.AcValueLTType;
import org.oceandsl.tools.sar.fxtran.ArrayConstructorEType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Constructor EType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArrayConstructorETypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArrayConstructorETypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArrayConstructorETypeImpl#getC <em>C</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArrayConstructorETypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArrayConstructorETypeImpl#getAcValueLT <em>Ac Value LT</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayConstructorETypeImpl extends MinimalEObjectImpl.Container implements ArrayConstructorEType {
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
    protected ArrayConstructorETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getArrayConstructorEType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getArrayConstructorEType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getC() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArrayConstructorEType_C());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArrayConstructorEType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AcValueLTType> getAcValueLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArrayConstructorEType_AcValueLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__AC_VALUE_LT:
                return ((InternalEList<?>)getAcValueLT()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__C:
                return getC();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__CNT:
                return getCnt();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__AC_VALUE_LT:
                return getAcValueLT();
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
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__C:
                getC().clear();
                getC().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__AC_VALUE_LT:
                getAcValueLT().clear();
                getAcValueLT().addAll((Collection<? extends AcValueLTType>)newValue);
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
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__C:
                getC().clear();
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__AC_VALUE_LT:
                getAcValueLT().clear();
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
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__C:
                return !getC().isEmpty();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.ARRAY_CONSTRUCTOR_ETYPE__AC_VALUE_LT:
                return !getAcValueLT().isEmpty();
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

} //ArrayConstructorETypeImpl
