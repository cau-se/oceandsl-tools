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
import org.oceandsl.tools.sar.fxtran.RenameType;
import org.oceandsl.tools.sar.fxtran.UseNType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rename Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.RenameTypeImpl#getUseN <em>Use N</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RenameTypeImpl extends MinimalEObjectImpl.Container implements RenameType {
    /**
     * The cached value of the '{@link #getUseN() <em>Use N</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUseN()
     * @generated
     * @ordered
     */
    protected UseNType useN;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RenameTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getRenameType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseNType getUseN() {
        return useN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUseN(UseNType newUseN, NotificationChain msgs) {
        UseNType oldUseN = useN;
        useN = newUseN;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.RENAME_TYPE__USE_N, oldUseN, newUseN);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUseN(UseNType newUseN) {
        if (newUseN != useN) {
            NotificationChain msgs = null;
            if (useN != null)
                msgs = ((InternalEObject)useN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.RENAME_TYPE__USE_N, null, msgs);
            if (newUseN != null)
                msgs = ((InternalEObject)newUseN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.RENAME_TYPE__USE_N, null, msgs);
            msgs = basicSetUseN(newUseN, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.RENAME_TYPE__USE_N, newUseN, newUseN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.RENAME_TYPE__USE_N:
                return basicSetUseN(null, msgs);
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
            case FxtranPackage.RENAME_TYPE__USE_N:
                return getUseN();
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
            case FxtranPackage.RENAME_TYPE__USE_N:
                setUseN((UseNType)newValue);
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
            case FxtranPackage.RENAME_TYPE__USE_N:
                setUseN((UseNType)null);
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
            case FxtranPackage.RENAME_TYPE__USE_N:
                return useN != null;
        }
        return super.eIsSet(featureID);
    }

} //RenameTypeImpl
