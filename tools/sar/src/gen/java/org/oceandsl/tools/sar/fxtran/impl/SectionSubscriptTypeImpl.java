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
import org.oceandsl.tools.sar.fxtran.LowerBoundType;
import org.oceandsl.tools.sar.fxtran.SectionSubscriptType;
import org.oceandsl.tools.sar.fxtran.UpperBoundType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Section Subscript Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptTypeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SectionSubscriptTypeImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SectionSubscriptTypeImpl extends MinimalEObjectImpl.Container implements SectionSubscriptType {
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
    protected SectionSubscriptTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getSectionSubscriptType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getSectionSubscriptType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LowerBoundType> getLowerBound() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSectionSubscriptType_LowerBound());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<UpperBoundType> getUpperBound() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSectionSubscriptType_UpperBound());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__LOWER_BOUND:
                return ((InternalEList<?>)getLowerBound()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__UPPER_BOUND:
                return ((InternalEList<?>)getUpperBound()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__LOWER_BOUND:
                return getLowerBound();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__UPPER_BOUND:
                return getUpperBound();
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
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__LOWER_BOUND:
                getLowerBound().clear();
                getLowerBound().addAll((Collection<? extends LowerBoundType>)newValue);
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__UPPER_BOUND:
                getUpperBound().clear();
                getUpperBound().addAll((Collection<? extends UpperBoundType>)newValue);
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
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__LOWER_BOUND:
                getLowerBound().clear();
                return;
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__UPPER_BOUND:
                getUpperBound().clear();
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
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__LOWER_BOUND:
                return !getLowerBound().isEmpty();
            case FxtranPackage.SECTION_SUBSCRIPT_TYPE__UPPER_BOUND:
                return !getUpperBound().isEmpty();
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

} //SectionSubscriptTypeImpl
