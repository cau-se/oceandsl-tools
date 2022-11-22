/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.DoVType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.NamedEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Do VType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoVTypeImpl#getNamedE <em>Named E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoVTypeImpl extends MinimalEObjectImpl.Container implements DoVType {
    /**
     * The cached value of the '{@link #getNamedE() <em>Named E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamedE()
     * @generated
     * @ordered
     */
    protected NamedEType namedE;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DoVTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getDoVType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamedEType getNamedE() {
        return namedE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamedE(NamedEType newNamedE, NotificationChain msgs) {
        NamedEType oldNamedE = namedE;
        namedE = newNamedE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.DO_VTYPE__NAMED_E, oldNamedE, newNamedE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamedE(NamedEType newNamedE) {
        if (newNamedE != namedE) {
            NotificationChain msgs = null;
            if (namedE != null)
                msgs = ((InternalEObject)namedE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.DO_VTYPE__NAMED_E, null, msgs);
            if (newNamedE != null)
                msgs = ((InternalEObject)newNamedE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.DO_VTYPE__NAMED_E, null, msgs);
            msgs = basicSetNamedE(newNamedE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.DO_VTYPE__NAMED_E, newNamedE, newNamedE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.DO_VTYPE__NAMED_E:
                return basicSetNamedE(null, msgs);
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
            case FxtranPackage.DO_VTYPE__NAMED_E:
                return getNamedE();
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
            case FxtranPackage.DO_VTYPE__NAMED_E:
                setNamedE((NamedEType)newValue);
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
            case FxtranPackage.DO_VTYPE__NAMED_E:
                setNamedE((NamedEType)null);
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
            case FxtranPackage.DO_VTYPE__NAMED_E:
                return namedE != null;
        }
        return super.eIsSet(featureID);
    }

} //DoVTypeImpl
