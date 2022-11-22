/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.CaseValueRangeType;
import org.oceandsl.tools.sar.fxtran.CaseValueType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Case Value Range Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CaseValueRangeTypeImpl#getCaseValue <em>Case Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CaseValueRangeTypeImpl extends MinimalEObjectImpl.Container implements CaseValueRangeType {
    /**
     * The cached value of the '{@link #getCaseValue() <em>Case Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCaseValue()
     * @generated
     * @ordered
     */
    protected CaseValueType caseValue;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CaseValueRangeTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getCaseValueRangeType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CaseValueType getCaseValue() {
        return caseValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCaseValue(CaseValueType newCaseValue, NotificationChain msgs) {
        CaseValueType oldCaseValue = caseValue;
        caseValue = newCaseValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE, oldCaseValue, newCaseValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCaseValue(CaseValueType newCaseValue) {
        if (newCaseValue != caseValue) {
            NotificationChain msgs = null;
            if (caseValue != null)
                msgs = ((InternalEObject)caseValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE, null, msgs);
            if (newCaseValue != null)
                msgs = ((InternalEObject)newCaseValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE, null, msgs);
            msgs = basicSetCaseValue(newCaseValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE, newCaseValue, newCaseValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE:
                return basicSetCaseValue(null, msgs);
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
            case FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE:
                return getCaseValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE:
                setCaseValue((CaseValueType)newValue);
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
            case FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE:
                setCaseValue((CaseValueType)null);
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
            case FxtranPackage.CASE_VALUE_RANGE_TYPE__CASE_VALUE:
                return caseValue != null;
        }
        return super.eIsSet(featureID);
    }

} //CaseValueRangeTypeImpl
