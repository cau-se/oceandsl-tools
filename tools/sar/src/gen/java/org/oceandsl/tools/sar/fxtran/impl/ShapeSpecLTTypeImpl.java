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
import org.oceandsl.tools.sar.fxtran.ShapeSpecLTType;
import org.oceandsl.tools.sar.fxtran.ShapeSpecType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shape Spec LT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ShapeSpecLTTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ShapeSpecLTTypeImpl#getShapeSpec <em>Shape Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShapeSpecLTTypeImpl extends MinimalEObjectImpl.Container implements ShapeSpecLTType {
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
    protected ShapeSpecLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getShapeSpecLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ShapeSpecType> getShapeSpec() {
        return getMixed().list(FxtranPackage.eINSTANCE.getShapeSpecLTType_ShapeSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__SHAPE_SPEC:
                return ((InternalEList<?>)getShapeSpec()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__SHAPE_SPEC:
                return getShapeSpec();
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
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__SHAPE_SPEC:
                getShapeSpec().clear();
                getShapeSpec().addAll((Collection<? extends ShapeSpecType>)newValue);
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
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__SHAPE_SPEC:
                getShapeSpec().clear();
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
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.SHAPE_SPEC_LT_TYPE__SHAPE_SPEC:
                return !getShapeSpec().isEmpty();
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

} //ShapeSpecLTTypeImpl
