/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.E1Type;
import org.oceandsl.tools.sar.fxtran.E2Type;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.PointerAStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pointer AStmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.PointerAStmtTypeImpl#getE1 <em>E1</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.PointerAStmtTypeImpl#getA <em>A</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.PointerAStmtTypeImpl#getE2 <em>E2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PointerAStmtTypeImpl extends MinimalEObjectImpl.Container implements PointerAStmtType {
    /**
     * The cached value of the '{@link #getE1() <em>E1</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getE1()
     * @generated
     * @ordered
     */
    protected E1Type e1;

    /**
     * The default value of the '{@link #getA() <em>A</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getA()
     * @generated
     * @ordered
     */
    protected static final String A_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getA() <em>A</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getA()
     * @generated
     * @ordered
     */
    protected String a = A_EDEFAULT;

    /**
     * The cached value of the '{@link #getE2() <em>E2</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getE2()
     * @generated
     * @ordered
     */
    protected E2Type e2;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PointerAStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getPointerAStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E1Type getE1() {
        return e1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetE1(E1Type newE1, NotificationChain msgs) {
        E1Type oldE1 = e1;
        e1 = newE1;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.POINTER_ASTMT_TYPE__E1, oldE1, newE1);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setE1(E1Type newE1) {
        if (newE1 != e1) {
            NotificationChain msgs = null;
            if (e1 != null)
                msgs = ((InternalEObject)e1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.POINTER_ASTMT_TYPE__E1, null, msgs);
            if (newE1 != null)
                msgs = ((InternalEObject)newE1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.POINTER_ASTMT_TYPE__E1, null, msgs);
            msgs = basicSetE1(newE1, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.POINTER_ASTMT_TYPE__E1, newE1, newE1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getA() {
        return a;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setA(String newA) {
        String oldA = a;
        a = newA;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.POINTER_ASTMT_TYPE__A, oldA, a));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public E2Type getE2() {
        return e2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetE2(E2Type newE2, NotificationChain msgs) {
        E2Type oldE2 = e2;
        e2 = newE2;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.POINTER_ASTMT_TYPE__E2, oldE2, newE2);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setE2(E2Type newE2) {
        if (newE2 != e2) {
            NotificationChain msgs = null;
            if (e2 != null)
                msgs = ((InternalEObject)e2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.POINTER_ASTMT_TYPE__E2, null, msgs);
            if (newE2 != null)
                msgs = ((InternalEObject)newE2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.POINTER_ASTMT_TYPE__E2, null, msgs);
            msgs = basicSetE2(newE2, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.POINTER_ASTMT_TYPE__E2, newE2, newE2));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.POINTER_ASTMT_TYPE__E1:
                return basicSetE1(null, msgs);
            case FxtranPackage.POINTER_ASTMT_TYPE__E2:
                return basicSetE2(null, msgs);
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
            case FxtranPackage.POINTER_ASTMT_TYPE__E1:
                return getE1();
            case FxtranPackage.POINTER_ASTMT_TYPE__A:
                return getA();
            case FxtranPackage.POINTER_ASTMT_TYPE__E2:
                return getE2();
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
            case FxtranPackage.POINTER_ASTMT_TYPE__E1:
                setE1((E1Type)newValue);
                return;
            case FxtranPackage.POINTER_ASTMT_TYPE__A:
                setA((String)newValue);
                return;
            case FxtranPackage.POINTER_ASTMT_TYPE__E2:
                setE2((E2Type)newValue);
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
            case FxtranPackage.POINTER_ASTMT_TYPE__E1:
                setE1((E1Type)null);
                return;
            case FxtranPackage.POINTER_ASTMT_TYPE__A:
                setA(A_EDEFAULT);
                return;
            case FxtranPackage.POINTER_ASTMT_TYPE__E2:
                setE2((E2Type)null);
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
            case FxtranPackage.POINTER_ASTMT_TYPE__E1:
                return e1 != null;
            case FxtranPackage.POINTER_ASTMT_TYPE__A:
                return A_EDEFAULT == null ? a != null : !A_EDEFAULT.equals(a);
            case FxtranPackage.POINTER_ASTMT_TYPE__E2:
                return e2 != null;
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
        result.append(" (a: ");
        result.append(a);
        result.append(')');
        return result.toString();
    }

} //PointerAStmtTypeImpl
