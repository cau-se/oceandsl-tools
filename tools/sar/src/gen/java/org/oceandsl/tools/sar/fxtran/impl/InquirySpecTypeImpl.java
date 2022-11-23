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
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.InquirySpecType;
import org.oceandsl.tools.sar.fxtran.NamedEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inquiry Spec Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InquirySpecTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InquirySpecTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InquirySpecTypeImpl#getArgN <em>Arg N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InquirySpecTypeImpl#getNamedE <em>Named E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InquirySpecTypeImpl extends MinimalEObjectImpl.Container implements InquirySpecType {
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
    protected InquirySpecTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getInquirySpecType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.INQUIRY_SPEC_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getInquirySpecType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgNType> getArgN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getInquirySpecType_ArgN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getInquirySpecType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.INQUIRY_SPEC_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.INQUIRY_SPEC_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.INQUIRY_SPEC_TYPE__ARG_N:
                return ((InternalEList<?>)getArgN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.INQUIRY_SPEC_TYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.INQUIRY_SPEC_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.INQUIRY_SPEC_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.INQUIRY_SPEC_TYPE__ARG_N:
                return getArgN();
            case FxtranPackage.INQUIRY_SPEC_TYPE__NAMED_E:
                return getNamedE();
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
            case FxtranPackage.INQUIRY_SPEC_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__ARG_N:
                getArgN().clear();
                getArgN().addAll((Collection<? extends ArgNType>)newValue);
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
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
            case FxtranPackage.INQUIRY_SPEC_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__ARG_N:
                getArgN().clear();
                return;
            case FxtranPackage.INQUIRY_SPEC_TYPE__NAMED_E:
                getNamedE().clear();
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
            case FxtranPackage.INQUIRY_SPEC_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.INQUIRY_SPEC_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.INQUIRY_SPEC_TYPE__ARG_N:
                return !getArgN().isEmpty();
            case FxtranPackage.INQUIRY_SPEC_TYPE__NAMED_E:
                return !getNamedE().isEmpty();
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

} //InquirySpecTypeImpl
