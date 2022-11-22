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
import org.oceandsl.tools.sar.fxtran.AttributeType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.AttributeTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.AttributeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.AttributeTypeImpl#getArraySpec <em>Array Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.AttributeTypeImpl#getAttributeN <em>Attribute N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.AttributeTypeImpl#getIntentSpec <em>Intent Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeTypeImpl extends MinimalEObjectImpl.Container implements AttributeType {
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
    protected AttributeTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getAttributeType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.ATTRIBUTE_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getAttributeType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArraySpecType> getArraySpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getAttributeType_ArraySpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getAttributeN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getAttributeType_AttributeN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getIntentSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getAttributeType_IntentSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ATTRIBUTE_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ATTRIBUTE_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ATTRIBUTE_TYPE__ARRAY_SPEC:
                return ((InternalEList<?>)getArraySpec()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.ATTRIBUTE_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.ATTRIBUTE_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.ATTRIBUTE_TYPE__ARRAY_SPEC:
                return getArraySpec();
            case FxtranPackage.ATTRIBUTE_TYPE__ATTRIBUTE_N:
                return getAttributeN();
            case FxtranPackage.ATTRIBUTE_TYPE__INTENT_SPEC:
                return getIntentSpec();
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
            case FxtranPackage.ATTRIBUTE_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__ARRAY_SPEC:
                getArraySpec().clear();
                getArraySpec().addAll((Collection<? extends ArraySpecType>)newValue);
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__ATTRIBUTE_N:
                getAttributeN().clear();
                getAttributeN().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__INTENT_SPEC:
                getIntentSpec().clear();
                getIntentSpec().addAll((Collection<? extends String>)newValue);
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
            case FxtranPackage.ATTRIBUTE_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__ARRAY_SPEC:
                getArraySpec().clear();
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__ATTRIBUTE_N:
                getAttributeN().clear();
                return;
            case FxtranPackage.ATTRIBUTE_TYPE__INTENT_SPEC:
                getIntentSpec().clear();
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
            case FxtranPackage.ATTRIBUTE_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.ATTRIBUTE_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.ATTRIBUTE_TYPE__ARRAY_SPEC:
                return !getArraySpec().isEmpty();
            case FxtranPackage.ATTRIBUTE_TYPE__ATTRIBUTE_N:
                return !getAttributeN().isEmpty();
            case FxtranPackage.ATTRIBUTE_TYPE__INTENT_SPEC:
                return !getIntentSpec().isEmpty();
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

} //AttributeTypeImpl
