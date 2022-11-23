/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.ArrayConstructorEType;
import org.oceandsl.tools.sar.fxtran.E2Type;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.ParensEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>E2 Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getArrayConstructorE <em>Array Constructor E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getParensE <em>Parens E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.E2TypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class E2TypeImpl extends MinimalEObjectImpl.Container implements E2Type {
    /**
     * The cached value of the '{@link #getArrayConstructorE() <em>Array Constructor E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArrayConstructorE()
     * @generated
     * @ordered
     */
    protected ArrayConstructorEType arrayConstructorE;

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
     * The cached value of the '{@link #getParensE() <em>Parens E</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParensE()
     * @generated
     * @ordered
     */
    protected ParensEType parensE;

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
    protected E2TypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getE2Type();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArrayConstructorEType getArrayConstructorE() {
        return arrayConstructorE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArrayConstructorE(ArrayConstructorEType newArrayConstructorE, NotificationChain msgs) {
        ArrayConstructorEType oldArrayConstructorE = arrayConstructorE;
        arrayConstructorE = newArrayConstructorE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E, oldArrayConstructorE, newArrayConstructorE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArrayConstructorE(ArrayConstructorEType newArrayConstructorE) {
        if (newArrayConstructorE != arrayConstructorE) {
            NotificationChain msgs = null;
            if (arrayConstructorE != null)
                msgs = ((InternalEObject)arrayConstructorE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E, null, msgs);
            if (newArrayConstructorE != null)
                msgs = ((InternalEObject)newArrayConstructorE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E, null, msgs);
            msgs = basicSetArrayConstructorE(newArrayConstructorE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E, newArrayConstructorE, newArrayConstructorE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__LITERAL_E, oldLiteralE, newLiteralE);
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
                msgs = ((InternalEObject)literalE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__LITERAL_E, null, msgs);
            if (newLiteralE != null)
                msgs = ((InternalEObject)newLiteralE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__LITERAL_E, null, msgs);
            msgs = basicSetLiteralE(newLiteralE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__LITERAL_E, newLiteralE, newLiteralE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__NAMED_E, oldNamedE, newNamedE);
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
                msgs = ((InternalEObject)namedE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__NAMED_E, null, msgs);
            if (newNamedE != null)
                msgs = ((InternalEObject)newNamedE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__NAMED_E, null, msgs);
            msgs = basicSetNamedE(newNamedE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__NAMED_E, newNamedE, newNamedE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__OP_E, oldOpE, newOpE);
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
                msgs = ((InternalEObject)opE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__OP_E, null, msgs);
            if (newOpE != null)
                msgs = ((InternalEObject)newOpE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__OP_E, null, msgs);
            msgs = basicSetOpE(newOpE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__OP_E, newOpE, newOpE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParensEType getParensE() {
        return parensE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParensE(ParensEType newParensE, NotificationChain msgs) {
        ParensEType oldParensE = parensE;
        parensE = newParensE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__PARENS_E, oldParensE, newParensE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParensE(ParensEType newParensE) {
        if (newParensE != parensE) {
            NotificationChain msgs = null;
            if (parensE != null)
                msgs = ((InternalEObject)parensE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__PARENS_E, null, msgs);
            if (newParensE != null)
                msgs = ((InternalEObject)newParensE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__PARENS_E, null, msgs);
            msgs = basicSetParensE(newParensE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__PARENS_E, newParensE, newParensE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__STRING_E, oldStringE, newStringE);
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
                msgs = ((InternalEObject)stringE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__STRING_E, null, msgs);
            if (newStringE != null)
                msgs = ((InternalEObject)newStringE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.E2_TYPE__STRING_E, null, msgs);
            msgs = basicSetStringE(newStringE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.E2_TYPE__STRING_E, newStringE, newStringE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E:
                return basicSetArrayConstructorE(null, msgs);
            case FxtranPackage.E2_TYPE__LITERAL_E:
                return basicSetLiteralE(null, msgs);
            case FxtranPackage.E2_TYPE__NAMED_E:
                return basicSetNamedE(null, msgs);
            case FxtranPackage.E2_TYPE__OP_E:
                return basicSetOpE(null, msgs);
            case FxtranPackage.E2_TYPE__PARENS_E:
                return basicSetParensE(null, msgs);
            case FxtranPackage.E2_TYPE__STRING_E:
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
            case FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E:
                return getArrayConstructorE();
            case FxtranPackage.E2_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.E2_TYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.E2_TYPE__OP_E:
                return getOpE();
            case FxtranPackage.E2_TYPE__PARENS_E:
                return getParensE();
            case FxtranPackage.E2_TYPE__STRING_E:
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
            case FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)newValue);
                return;
            case FxtranPackage.E2_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)newValue);
                return;
            case FxtranPackage.E2_TYPE__NAMED_E:
                setNamedE((NamedEType)newValue);
                return;
            case FxtranPackage.E2_TYPE__OP_E:
                setOpE((OpEType)newValue);
                return;
            case FxtranPackage.E2_TYPE__PARENS_E:
                setParensE((ParensEType)newValue);
                return;
            case FxtranPackage.E2_TYPE__STRING_E:
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
            case FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)null);
                return;
            case FxtranPackage.E2_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)null);
                return;
            case FxtranPackage.E2_TYPE__NAMED_E:
                setNamedE((NamedEType)null);
                return;
            case FxtranPackage.E2_TYPE__OP_E:
                setOpE((OpEType)null);
                return;
            case FxtranPackage.E2_TYPE__PARENS_E:
                setParensE((ParensEType)null);
                return;
            case FxtranPackage.E2_TYPE__STRING_E:
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
            case FxtranPackage.E2_TYPE__ARRAY_CONSTRUCTOR_E:
                return arrayConstructorE != null;
            case FxtranPackage.E2_TYPE__LITERAL_E:
                return literalE != null;
            case FxtranPackage.E2_TYPE__NAMED_E:
                return namedE != null;
            case FxtranPackage.E2_TYPE__OP_E:
                return opE != null;
            case FxtranPackage.E2_TYPE__PARENS_E:
                return parensE != null;
            case FxtranPackage.E2_TYPE__STRING_E:
                return stringE != null;
        }
        return super.eIsSet(featureID);
    }

} //E2TypeImpl
