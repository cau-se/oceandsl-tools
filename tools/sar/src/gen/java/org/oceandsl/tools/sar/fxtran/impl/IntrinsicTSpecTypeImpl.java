/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.CharSelectorType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IntrinsicTSpecType;
import org.oceandsl.tools.sar.fxtran.KSelectorType;
import org.oceandsl.tools.sar.fxtran.TNType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intrinsic TSpec Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IntrinsicTSpecTypeImpl#getTN <em>TN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IntrinsicTSpecTypeImpl#getKSelector <em>KSelector</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IntrinsicTSpecTypeImpl#getCharSelector <em>Char Selector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntrinsicTSpecTypeImpl extends MinimalEObjectImpl.Container implements IntrinsicTSpecType {
    /**
     * The cached value of the '{@link #getTN() <em>TN</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTN()
     * @generated
     * @ordered
     */
    protected TNType tN;

    /**
     * The cached value of the '{@link #getKSelector() <em>KSelector</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKSelector()
     * @generated
     * @ordered
     */
    protected KSelectorType kSelector;

    /**
     * The cached value of the '{@link #getCharSelector() <em>Char Selector</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharSelector()
     * @generated
     * @ordered
     */
    protected CharSelectorType charSelector;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntrinsicTSpecTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getIntrinsicTSpecType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TNType getTN() {
        return tN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTN(TNType newTN, NotificationChain msgs) {
        TNType oldTN = tN;
        tN = newTN;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__TN, oldTN, newTN);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTN(TNType newTN) {
        if (newTN != tN) {
            NotificationChain msgs = null;
            if (tN != null)
                msgs = ((InternalEObject)tN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__TN, null, msgs);
            if (newTN != null)
                msgs = ((InternalEObject)newTN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__TN, null, msgs);
            msgs = basicSetTN(newTN, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__TN, newTN, newTN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSelectorType getKSelector() {
        return kSelector;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKSelector(KSelectorType newKSelector, NotificationChain msgs) {
        KSelectorType oldKSelector = kSelector;
        kSelector = newKSelector;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR, oldKSelector, newKSelector);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKSelector(KSelectorType newKSelector) {
        if (newKSelector != kSelector) {
            NotificationChain msgs = null;
            if (kSelector != null)
                msgs = ((InternalEObject)kSelector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR, null, msgs);
            if (newKSelector != null)
                msgs = ((InternalEObject)newKSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR, null, msgs);
            msgs = basicSetKSelector(newKSelector, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR, newKSelector, newKSelector));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSelectorType getCharSelector() {
        return charSelector;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCharSelector(CharSelectorType newCharSelector, NotificationChain msgs) {
        CharSelectorType oldCharSelector = charSelector;
        charSelector = newCharSelector;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR, oldCharSelector, newCharSelector);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharSelector(CharSelectorType newCharSelector) {
        if (newCharSelector != charSelector) {
            NotificationChain msgs = null;
            if (charSelector != null)
                msgs = ((InternalEObject)charSelector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR, null, msgs);
            if (newCharSelector != null)
                msgs = ((InternalEObject)newCharSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR, null, msgs);
            msgs = basicSetCharSelector(newCharSelector, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR, newCharSelector, newCharSelector));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__TN:
                return basicSetTN(null, msgs);
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR:
                return basicSetKSelector(null, msgs);
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR:
                return basicSetCharSelector(null, msgs);
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
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__TN:
                return getTN();
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR:
                return getKSelector();
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR:
                return getCharSelector();
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
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__TN:
                setTN((TNType)newValue);
                return;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR:
                setKSelector((KSelectorType)newValue);
                return;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR:
                setCharSelector((CharSelectorType)newValue);
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
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__TN:
                setTN((TNType)null);
                return;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR:
                setKSelector((KSelectorType)null);
                return;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR:
                setCharSelector((CharSelectorType)null);
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
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__TN:
                return tN != null;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__KSELECTOR:
                return kSelector != null;
            case FxtranPackage.INTRINSIC_TSPEC_TYPE__CHAR_SELECTOR:
                return charSelector != null;
        }
        return super.eIsSet(featureID);
    }

} //IntrinsicTSpecTypeImpl
