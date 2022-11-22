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
import org.oceandsl.tools.sar.fxtran.SectionSubscriptLTType;
import org.oceandsl.tools.sar.fxtran.SectionSubscriptType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Section Subscript LT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptLTTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptLTTypeImpl#getSectionSubscript <em>Section Subscript</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SectionSubscriptLTTypeImpl extends MinimalEObjectImpl.Container implements SectionSubscriptLTType {
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
    protected SectionSubscriptLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getSectionSubscriptLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SectionSubscriptType> getSectionSubscript() {
        return getMixed().list(FxtranPackage.eINSTANCE.getSectionSubscriptLTType_SectionSubscript());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__SECTION_SUBSCRIPT:
                return ((InternalEList<?>)getSectionSubscript()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__SECTION_SUBSCRIPT:
                return getSectionSubscript();
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
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__SECTION_SUBSCRIPT:
                getSectionSubscript().clear();
                getSectionSubscript().addAll((Collection<? extends SectionSubscriptType>)newValue);
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
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__SECTION_SUBSCRIPT:
                getSectionSubscript().clear();
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
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.SECTION_SUBSCRIPT_LT_TYPE__SECTION_SUBSCRIPT:
                return !getSectionSubscript().isEmpty();
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

} //SectionSubscriptLTTypeImpl
