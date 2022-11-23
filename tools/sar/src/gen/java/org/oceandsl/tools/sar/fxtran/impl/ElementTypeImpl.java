/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.tools.sar.fxtran.ArrayConstructorEType;
import org.oceandsl.tools.sar.fxtran.ElementType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getArrayConstructorE <em>Array Constructor E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ElementTypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElementTypeImpl extends MinimalEObjectImpl.Container implements ElementType {
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
     * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap group;

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
    protected ElementTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getElementType();
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E, oldArrayConstructorE, newArrayConstructorE);
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
                msgs = ((InternalEObject)arrayConstructorE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E, null, msgs);
            if (newArrayConstructorE != null)
                msgs = ((InternalEObject)newArrayConstructorE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E, null, msgs);
            msgs = basicSetArrayConstructorE(newArrayConstructorE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E, newArrayConstructorE, newArrayConstructorE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, FxtranPackage.ELEMENT_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getElementType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OpEType> getOpE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getElementType_OpE());
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__LITERAL_E, oldLiteralE, newLiteralE);
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
                msgs = ((InternalEObject)literalE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__LITERAL_E, null, msgs);
            if (newLiteralE != null)
                msgs = ((InternalEObject)newLiteralE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__LITERAL_E, null, msgs);
            msgs = basicSetLiteralE(newLiteralE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__LITERAL_E, newLiteralE, newLiteralE));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__STRING_E, oldStringE, newStringE);
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
                msgs = ((InternalEObject)stringE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__STRING_E, null, msgs);
            if (newStringE != null)
                msgs = ((InternalEObject)newStringE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ELEMENT_TYPE__STRING_E, null, msgs);
            msgs = basicSetStringE(newStringE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ELEMENT_TYPE__STRING_E, newStringE, newStringE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E:
                return basicSetArrayConstructorE(null, msgs);
            case FxtranPackage.ELEMENT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ELEMENT_TYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ELEMENT_TYPE__OP_E:
                return ((InternalEList<?>)getOpE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ELEMENT_TYPE__LITERAL_E:
                return basicSetLiteralE(null, msgs);
            case FxtranPackage.ELEMENT_TYPE__STRING_E:
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
            case FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E:
                return getArrayConstructorE();
            case FxtranPackage.ELEMENT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.ELEMENT_TYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.ELEMENT_TYPE__OP_E:
                return getOpE();
            case FxtranPackage.ELEMENT_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.ELEMENT_TYPE__STRING_E:
                return getStringE();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)newValue);
                return;
            case FxtranPackage.ELEMENT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.ELEMENT_TYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
                return;
            case FxtranPackage.ELEMENT_TYPE__OP_E:
                getOpE().clear();
                getOpE().addAll((Collection<? extends OpEType>)newValue);
                return;
            case FxtranPackage.ELEMENT_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)newValue);
                return;
            case FxtranPackage.ELEMENT_TYPE__STRING_E:
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
            case FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E:
                setArrayConstructorE((ArrayConstructorEType)null);
                return;
            case FxtranPackage.ELEMENT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.ELEMENT_TYPE__NAMED_E:
                getNamedE().clear();
                return;
            case FxtranPackage.ELEMENT_TYPE__OP_E:
                getOpE().clear();
                return;
            case FxtranPackage.ELEMENT_TYPE__LITERAL_E:
                setLiteralE((LiteralEType)null);
                return;
            case FxtranPackage.ELEMENT_TYPE__STRING_E:
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
            case FxtranPackage.ELEMENT_TYPE__ARRAY_CONSTRUCTOR_E:
                return arrayConstructorE != null;
            case FxtranPackage.ELEMENT_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case FxtranPackage.ELEMENT_TYPE__NAMED_E:
                return !getNamedE().isEmpty();
            case FxtranPackage.ELEMENT_TYPE__OP_E:
                return !getOpE().isEmpty();
            case FxtranPackage.ELEMENT_TYPE__LITERAL_E:
                return literalE != null;
            case FxtranPackage.ELEMENT_TYPE__STRING_E:
                return stringE != null;
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
        result.append(" (group: ");
        result.append(group);
        result.append(')');
        return result.toString();
    }

} //ElementTypeImpl
