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
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjNType;
import org.oceandsl.tools.sar.fxtran.NamelistGroupObjType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Namelist Group Obj Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.NamelistGroupObjTypeImpl#getNamelistGroupObjN <em>Namelist Group Obj N</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamelistGroupObjTypeImpl extends MinimalEObjectImpl.Container implements NamelistGroupObjType {
    /**
     * The cached value of the '{@link #getNamelistGroupObjN() <em>Namelist Group Obj N</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamelistGroupObjN()
     * @generated
     * @ordered
     */
    protected NamelistGroupObjNType namelistGroupObjN;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NamelistGroupObjTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getNamelistGroupObjType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NamelistGroupObjNType getNamelistGroupObjN() {
        return namelistGroupObjN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamelistGroupObjN(NamelistGroupObjNType newNamelistGroupObjN, NotificationChain msgs) {
        NamelistGroupObjNType oldNamelistGroupObjN = namelistGroupObjN;
        namelistGroupObjN = newNamelistGroupObjN;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N, oldNamelistGroupObjN, newNamelistGroupObjN);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamelistGroupObjN(NamelistGroupObjNType newNamelistGroupObjN) {
        if (newNamelistGroupObjN != namelistGroupObjN) {
            NotificationChain msgs = null;
            if (namelistGroupObjN != null)
                msgs = ((InternalEObject)namelistGroupObjN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N, null, msgs);
            if (newNamelistGroupObjN != null)
                msgs = ((InternalEObject)newNamelistGroupObjN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N, null, msgs);
            msgs = basicSetNamelistGroupObjN(newNamelistGroupObjN, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N, newNamelistGroupObjN, newNamelistGroupObjN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N:
                return basicSetNamelistGroupObjN(null, msgs);
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N:
                return getNamelistGroupObjN();
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N:
                setNamelistGroupObjN((NamelistGroupObjNType)newValue);
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N:
                setNamelistGroupObjN((NamelistGroupObjNType)null);
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
            case FxtranPackage.NAMELIST_GROUP_OBJ_TYPE__NAMELIST_GROUP_OBJ_N:
                return namelistGroupObjN != null;
        }
        return super.eIsSet(featureID);
    }

} //NamelistGroupObjTypeImpl
