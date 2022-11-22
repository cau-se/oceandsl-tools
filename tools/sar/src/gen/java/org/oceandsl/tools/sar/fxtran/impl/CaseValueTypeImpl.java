/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.CaseValueType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Case Value Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CaseValueTypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.CaseValueTypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CaseValueTypeImpl extends MinimalEObjectImpl.Container implements CaseValueType {
    /**
     * The cached value of the '{@link #getLiteralE() <em>Literal E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLiteralE()
     * @generated
     * @ordered
     */
    protected LiteralEType literalE;

    /**
     * The cached value of the '{@link #getStringE() <em>String E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringE()
     * @generated
     * @ordered
     */
    protected StringEType stringE;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CaseValueTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getCaseValueType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LiteralEType getLiteralE() {
        return literalE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLiteralE(LiteralEType newLiteralE, NotificationChain msgs) {
        LiteralEType oldLiteralE = literalE;
        literalE = newLiteralE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_TYPE__LITERAL_E, oldLiteralE, newLiteralE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLiteralE(LiteralEType newLiteralE) {
        if (newLiteralE != literalE) {
            NotificationChain msgs = null;
            if (literalE != null)
                msgs = ((InternalEObject)literalE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_TYPE__LITERAL_E, null, msgs);
            if (newLiteralE != null)
                msgs = ((InternalEObject)newLiteralE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_TYPE__LITERAL_E, null, msgs);
            msgs = basicSetLiteralE(newLiteralE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_TYPE__LITERAL_E, newLiteralE, newLiteralE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StringEType getStringE() {
        return stringE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStringE(StringEType newStringE, NotificationChain msgs) {
        StringEType oldStringE = stringE;
        stringE = newStringE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_TYPE__STRING_E, oldStringE, newStringE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringE(StringEType newStringE) {
        if (newStringE != stringE) {
            NotificationChain msgs = null;
            if (stringE != null)
                msgs = ((InternalEObject)stringE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_TYPE__STRING_E, null, msgs);
            if (newStringE != null)
                msgs = ((InternalEObject)newStringE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.CASE_VALUE_TYPE__STRING_E, null, msgs);
            msgs = basicSetStringE(newStringE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.CASE_VALUE_TYPE__STRING_E, newStringE, newStringE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.CASE_VALUE_TYPE__LITERAL_E:
                return basicSetLiteralE(null, msgs);
            case FxtranPackage.CASE_VALUE_TYPE__STRING_E:
                return basicSetStringE(null, msgs);
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
            case FxtranPackage.CASE_VALUE_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.CASE_VALUE_TYPE__STRING_E:
                return getStringE();
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
            case FxtranPackage.CASE_VALUE_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)newValue);
                return;
            case FxtranPackage.CASE_VALUE_TYPE__STRING_E:
                setStringE((StringEType)newValue);
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
            case FxtranPackage.CASE_VALUE_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)null);
                return;
            case FxtranPackage.CASE_VALUE_TYPE__STRING_E:
                setStringE((StringEType)null);
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
            case FxtranPackage.CASE_VALUE_TYPE__LITERAL_E:
                return literalE != null;
            case FxtranPackage.CASE_VALUE_TYPE__STRING_E:
                return stringE != null;
        }
        return super.eIsSet(featureID);
    }

} //CaseValueTypeImpl
