/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.ArgNType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.NType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arg NType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgNTypeImpl#getN <em>N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgNTypeImpl#getK <em>K</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgNTypeImpl#getN1 <em>N1</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArgNTypeImpl extends MinimalEObjectImpl.Container implements ArgNType {
    /**
     * The cached value of the '{@link #getN() <em>N</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected NType n;

    /**
     * The default value of the '{@link #getK() <em>K</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getK()
     * @generated
     * @ordered
     */
    protected static final String K_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getK() <em>K</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getK()
     * @generated
     * @ordered
     */
    protected String k = K_EDEFAULT;

    /**
     * The default value of the '{@link #getN1() <em>N1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN1()
     * @generated
     * @ordered
     */
    protected static final String N1_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getN1() <em>N1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN1()
     * @generated
     * @ordered
     */
    protected String n1 = N1_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ArgNTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getArgNType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NType getN() {
        return n;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetN(NType newN, NotificationChain msgs) {
        NType oldN = n;
        n = newN;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ARG_NTYPE__N, oldN, newN);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN(NType newN) {
        if (newN != n) {
            NotificationChain msgs = null;
            if (n != null)
                msgs = ((InternalEObject)n).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ARG_NTYPE__N, null, msgs);
            if (newN != null)
                msgs = ((InternalEObject)newN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ARG_NTYPE__N, null, msgs);
            msgs = basicSetN(newN, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ARG_NTYPE__N, newN, newN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getK() {
        return k;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setK(String newK) {
        String oldK = k;
        k = newK;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ARG_NTYPE__K, oldK, k));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getN1() {
        return n1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN1(String newN1) {
        String oldN1 = n1;
        n1 = newN1;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ARG_NTYPE__N1, oldN1, n1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ARG_NTYPE__N:
                return basicSetN(null, msgs);
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
            case FxtranPackage.ARG_NTYPE__N:
                return getN();
            case FxtranPackage.ARG_NTYPE__K:
                return getK();
            case FxtranPackage.ARG_NTYPE__N1:
                return getN1();
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
            case FxtranPackage.ARG_NTYPE__N:
                setN((NType)newValue);
                return;
            case FxtranPackage.ARG_NTYPE__K:
                setK((String)newValue);
                return;
            case FxtranPackage.ARG_NTYPE__N1:
                setN1((String)newValue);
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
            case FxtranPackage.ARG_NTYPE__N:
                setN((NType)null);
                return;
            case FxtranPackage.ARG_NTYPE__K:
                setK(K_EDEFAULT);
                return;
            case FxtranPackage.ARG_NTYPE__N1:
                setN1(N1_EDEFAULT);
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
            case FxtranPackage.ARG_NTYPE__N:
                return n != null;
            case FxtranPackage.ARG_NTYPE__K:
                return K_EDEFAULT == null ? k != null : !K_EDEFAULT.equals(k);
            case FxtranPackage.ARG_NTYPE__N1:
                return N1_EDEFAULT == null ? n1 != null : !N1_EDEFAULT.equals(n1);
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
        result.append(" (k: ");
        result.append(k);
        result.append(", n1: ");
        result.append(n1);
        result.append(')');
        return result.toString();
    }

} //ArgNTypeImpl
