/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.VNType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>VN Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.VNTypeImpl#getVN <em>VN</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.VNTypeImpl#getN <em>N</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VNTypeImpl extends MinimalEObjectImpl.Container implements VNType {
    /**
     * The cached value of the '{@link #getVN() <em>VN</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVN()
     * @generated
     * @ordered
     */
    protected VNType vN;

    /**
     * The default value of the '{@link #getN() <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected static final String N_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getN() <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected String n = N_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VNTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getVNType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VNType getVN() {
        return vN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVN(VNType newVN, NotificationChain msgs) {
        VNType oldVN = vN;
        vN = newVN;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.VN_TYPE__VN, oldVN, newVN);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVN(VNType newVN) {
        if (newVN != vN) {
            NotificationChain msgs = null;
            if (vN != null)
                msgs = ((InternalEObject)vN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.VN_TYPE__VN, null, msgs);
            if (newVN != null)
                msgs = ((InternalEObject)newVN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.VN_TYPE__VN, null, msgs);
            msgs = basicSetVN(newVN, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.VN_TYPE__VN, newVN, newVN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getN() {
        return n;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN(String newN) {
        String oldN = n;
        n = newN;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.VN_TYPE__N, oldN, n));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.VN_TYPE__VN:
                return basicSetVN(null, msgs);
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
            case FxtranPackage.VN_TYPE__VN:
                return getVN();
            case FxtranPackage.VN_TYPE__N:
                return getN();
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
            case FxtranPackage.VN_TYPE__VN:
                setVN((VNType)newValue);
                return;
            case FxtranPackage.VN_TYPE__N:
                setN((String)newValue);
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
            case FxtranPackage.VN_TYPE__VN:
                setVN((VNType)null);
                return;
            case FxtranPackage.VN_TYPE__N:
                setN(N_EDEFAULT);
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
            case FxtranPackage.VN_TYPE__VN:
                return vN != null;
            case FxtranPackage.VN_TYPE__N:
                return N_EDEFAULT == null ? n != null : !N_EDEFAULT.equals(n);
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
        result.append(" (n: ");
        result.append(n);
        result.append(')');
        return result.toString();
    }

} //VNTypeImpl
