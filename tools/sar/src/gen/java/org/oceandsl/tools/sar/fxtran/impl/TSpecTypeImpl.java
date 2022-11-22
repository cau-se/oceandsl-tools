/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.DerivedTSpecType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IntrinsicTSpecType;
import org.oceandsl.tools.sar.fxtran.TSpecType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSpec Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TSpecTypeImpl#getDerivedTSpec <em>Derived TSpec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.TSpecTypeImpl#getIntrinsicTSpec <em>Intrinsic TSpec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TSpecTypeImpl extends MinimalEObjectImpl.Container implements TSpecType {
    /**
     * The cached value of the '{@link #getDerivedTSpec() <em>Derived TSpec</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDerivedTSpec()
     * @generated
     * @ordered
     */
    protected DerivedTSpecType derivedTSpec;

    /**
     * The cached value of the '{@link #getIntrinsicTSpec() <em>Intrinsic TSpec</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntrinsicTSpec()
     * @generated
     * @ordered
     */
    protected IntrinsicTSpecType intrinsicTSpec;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TSpecTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getTSpecType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedTSpecType getDerivedTSpec() {
        return derivedTSpec;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDerivedTSpec(DerivedTSpecType newDerivedTSpec, NotificationChain msgs) {
        DerivedTSpecType oldDerivedTSpec = derivedTSpec;
        derivedTSpec = newDerivedTSpec;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC, oldDerivedTSpec, newDerivedTSpec);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDerivedTSpec(DerivedTSpecType newDerivedTSpec) {
        if (newDerivedTSpec != derivedTSpec) {
            NotificationChain msgs = null;
            if (derivedTSpec != null)
                msgs = ((InternalEObject)derivedTSpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC, null, msgs);
            if (newDerivedTSpec != null)
                msgs = ((InternalEObject)newDerivedTSpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC, null, msgs);
            msgs = basicSetDerivedTSpec(newDerivedTSpec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC, newDerivedTSpec, newDerivedTSpec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntrinsicTSpecType getIntrinsicTSpec() {
        return intrinsicTSpec;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIntrinsicTSpec(IntrinsicTSpecType newIntrinsicTSpec, NotificationChain msgs) {
        IntrinsicTSpecType oldIntrinsicTSpec = intrinsicTSpec;
        intrinsicTSpec = newIntrinsicTSpec;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC, oldIntrinsicTSpec, newIntrinsicTSpec);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntrinsicTSpec(IntrinsicTSpecType newIntrinsicTSpec) {
        if (newIntrinsicTSpec != intrinsicTSpec) {
            NotificationChain msgs = null;
            if (intrinsicTSpec != null)
                msgs = ((InternalEObject)intrinsicTSpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC, null, msgs);
            if (newIntrinsicTSpec != null)
                msgs = ((InternalEObject)newIntrinsicTSpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC, null, msgs);
            msgs = basicSetIntrinsicTSpec(newIntrinsicTSpec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC, newIntrinsicTSpec, newIntrinsicTSpec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC:
                return basicSetDerivedTSpec(null, msgs);
            case FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC:
                return basicSetIntrinsicTSpec(null, msgs);
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
            case FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC:
                return getDerivedTSpec();
            case FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC:
                return getIntrinsicTSpec();
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
            case FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC:
                setDerivedTSpec((DerivedTSpecType)newValue);
                return;
            case FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC:
                setIntrinsicTSpec((IntrinsicTSpecType)newValue);
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
            case FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC:
                setDerivedTSpec((DerivedTSpecType)null);
                return;
            case FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC:
                setIntrinsicTSpec((IntrinsicTSpecType)null);
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
            case FxtranPackage.TSPEC_TYPE__DERIVED_TSPEC:
                return derivedTSpec != null;
            case FxtranPackage.TSPEC_TYPE__INTRINSIC_TSPEC:
                return intrinsicTSpec != null;
        }
        return super.eIsSet(featureID);
    }

} //TSpecTypeImpl
