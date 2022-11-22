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
import org.oceandsl.tools.sar.fxtran.InitEType;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Init EType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InitETypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InitETypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InitETypeImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.InitETypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InitETypeImpl extends MinimalEObjectImpl.Container implements InitEType {
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
     * The cached value of the '{@link #getNamedE() <em>Named E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamedE()
     * @generated
     * @ordered
     */
    protected NamedEType namedE;

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
    protected InitETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getInitEType();
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__LITERAL_E, oldLiteralE, newLiteralE);
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
                msgs = ((InternalEObject)literalE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__LITERAL_E, null, msgs);
            if (newLiteralE != null)
                msgs = ((InternalEObject)newLiteralE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__LITERAL_E, null, msgs);
            msgs = basicSetLiteralE(newLiteralE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__LITERAL_E, newLiteralE, newLiteralE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__NAMED_E, oldNamedE, newNamedE);
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
                msgs = ((InternalEObject)namedE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__NAMED_E, null, msgs);
            if (newNamedE != null)
                msgs = ((InternalEObject)newNamedE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__NAMED_E, null, msgs);
            msgs = basicSetNamedE(newNamedE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__NAMED_E, newNamedE, newNamedE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__OP_E, oldOpE, newOpE);
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
                msgs = ((InternalEObject)opE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__OP_E, null, msgs);
            if (newOpE != null)
                msgs = ((InternalEObject)newOpE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__OP_E, null, msgs);
            msgs = basicSetOpE(newOpE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__OP_E, newOpE, newOpE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__STRING_E, oldStringE, newStringE);
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
                msgs = ((InternalEObject)stringE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__STRING_E, null, msgs);
            if (newStringE != null)
                msgs = ((InternalEObject)newStringE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.INIT_ETYPE__STRING_E, null, msgs);
            msgs = basicSetStringE(newStringE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.INIT_ETYPE__STRING_E, newStringE, newStringE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.INIT_ETYPE__LITERAL_E:
                return basicSetLiteralE(null, msgs);
            case FxtranPackage.INIT_ETYPE__NAMED_E:
                return basicSetNamedE(null, msgs);
            case FxtranPackage.INIT_ETYPE__OP_E:
                return basicSetOpE(null, msgs);
            case FxtranPackage.INIT_ETYPE__STRING_E:
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
            case FxtranPackage.INIT_ETYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.INIT_ETYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.INIT_ETYPE__OP_E:
                return getOpE();
            case FxtranPackage.INIT_ETYPE__STRING_E:
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
            case FxtranPackage.INIT_ETYPE__LITERAL_E:
                setLiteralE((LiteralEType)newValue);
                return;
            case FxtranPackage.INIT_ETYPE__NAMED_E:
                setNamedE((NamedEType)newValue);
                return;
            case FxtranPackage.INIT_ETYPE__OP_E:
                setOpE((OpEType)newValue);
                return;
            case FxtranPackage.INIT_ETYPE__STRING_E:
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
            case FxtranPackage.INIT_ETYPE__LITERAL_E:
                setLiteralE((LiteralEType)null);
                return;
            case FxtranPackage.INIT_ETYPE__NAMED_E:
                setNamedE((NamedEType)null);
                return;
            case FxtranPackage.INIT_ETYPE__OP_E:
                setOpE((OpEType)null);
                return;
            case FxtranPackage.INIT_ETYPE__STRING_E:
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
            case FxtranPackage.INIT_ETYPE__LITERAL_E:
                return literalE != null;
            case FxtranPackage.INIT_ETYPE__NAMED_E:
                return namedE != null;
            case FxtranPackage.INIT_ETYPE__OP_E:
                return opE != null;
            case FxtranPackage.INIT_ETYPE__STRING_E:
                return stringE != null;
        }
        return super.eIsSet(featureID);
    }

} //InitETypeImpl
