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
import org.oceandsl.tools.sar.fxtran.MaskEType;
import org.oceandsl.tools.sar.fxtran.OpEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mask EType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.MaskETypeImpl#getOpE <em>Op E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MaskETypeImpl extends MinimalEObjectImpl.Container implements MaskEType {
    /**
     * The cached value of the '{@link #getOpE() <em>Op E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOpE()
     * @generated
     * @ordered
     */
    protected OpEType opE;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MaskETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getMaskEType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpEType getOpE() {
        return opE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOpE(OpEType newOpE, NotificationChain msgs) {
        OpEType oldOpE = opE;
        opE = newOpE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.MASK_ETYPE__OP_E, oldOpE, newOpE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOpE(OpEType newOpE) {
        if (newOpE != opE) {
            NotificationChain msgs = null;
            if (opE != null)
                msgs = ((InternalEObject)opE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.MASK_ETYPE__OP_E, null, msgs);
            if (newOpE != null)
                msgs = ((InternalEObject)newOpE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.MASK_ETYPE__OP_E, null, msgs);
            msgs = basicSetOpE(newOpE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.MASK_ETYPE__OP_E, newOpE, newOpE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.MASK_ETYPE__OP_E:
                return basicSetOpE(null, msgs);
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
            case FxtranPackage.MASK_ETYPE__OP_E:
                return getOpE();
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
            case FxtranPackage.MASK_ETYPE__OP_E:
                setOpE((OpEType)newValue);
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
            case FxtranPackage.MASK_ETYPE__OP_E:
                setOpE((OpEType)null);
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
            case FxtranPackage.MASK_ETYPE__OP_E:
                return opE != null;
        }
        return super.eIsSet(featureID);
    }

} //MaskETypeImpl
