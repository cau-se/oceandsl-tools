/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.tools.sar.fxtran.ArgNType;
import org.oceandsl.tools.sar.fxtran.ArgType;
import org.oceandsl.tools.sar.fxtran.ArrayConstructorEType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.ParensEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arg Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getArgN <em>Arg N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getArrayConstructorE <em>Array Constructor E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getParensE <em>Parens E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ArgTypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArgTypeImpl extends MinimalEObjectImpl.Container implements ArgType {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ArgTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getArgType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.ARG_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getArgType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgNType> getArgN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_ArgN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArrayConstructorEType> getArrayConstructorE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_ArrayConstructorE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LiteralEType> getLiteralE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_LiteralE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OpEType> getOpE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_OpE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ParensEType> getParensE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_ParensE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<StringEType> getStringE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getArgType_StringE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ARG_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__ARG_N:
                return ((InternalEList<?>)getArgN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__ARRAY_CONSTRUCTOR_E:
                return ((InternalEList<?>)getArrayConstructorE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__LITERAL_E:
                return ((InternalEList<?>)getLiteralE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__OP_E:
                return ((InternalEList<?>)getOpE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__PARENS_E:
                return ((InternalEList<?>)getParensE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.ARG_TYPE__STRING_E:
                return ((InternalEList<?>)getStringE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.ARG_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.ARG_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.ARG_TYPE__ARG_N:
                return getArgN();
            case FxtranPackage.ARG_TYPE__ARRAY_CONSTRUCTOR_E:
                return getArrayConstructorE();
            case FxtranPackage.ARG_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.ARG_TYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.ARG_TYPE__OP_E:
                return getOpE();
            case FxtranPackage.ARG_TYPE__PARENS_E:
                return getParensE();
            case FxtranPackage.ARG_TYPE__STRING_E:
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
            case FxtranPackage.ARG_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.ARG_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.ARG_TYPE__ARG_N:
                getArgN().clear();
                getArgN().addAll((Collection<? extends ArgNType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__ARRAY_CONSTRUCTOR_E:
                getArrayConstructorE().clear();
                getArrayConstructorE().addAll((Collection<? extends ArrayConstructorEType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__LITERAL_E:
                getLiteralE().clear();
                getLiteralE().addAll((Collection<? extends LiteralEType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__OP_E:
                getOpE().clear();
                getOpE().addAll((Collection<? extends OpEType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__PARENS_E:
                getParensE().clear();
                getParensE().addAll((Collection<? extends ParensEType>)newValue);
                return;
            case FxtranPackage.ARG_TYPE__STRING_E:
                getStringE().clear();
                getStringE().addAll((Collection<? extends StringEType>)newValue);
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
            case FxtranPackage.ARG_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.ARG_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.ARG_TYPE__ARG_N:
                getArgN().clear();
                return;
            case FxtranPackage.ARG_TYPE__ARRAY_CONSTRUCTOR_E:
                getArrayConstructorE().clear();
                return;
            case FxtranPackage.ARG_TYPE__LITERAL_E:
                getLiteralE().clear();
                return;
            case FxtranPackage.ARG_TYPE__NAMED_E:
                getNamedE().clear();
                return;
            case FxtranPackage.ARG_TYPE__OP_E:
                getOpE().clear();
                return;
            case FxtranPackage.ARG_TYPE__PARENS_E:
                getParensE().clear();
                return;
            case FxtranPackage.ARG_TYPE__STRING_E:
                getStringE().clear();
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
            case FxtranPackage.ARG_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.ARG_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.ARG_TYPE__ARG_N:
                return !getArgN().isEmpty();
            case FxtranPackage.ARG_TYPE__ARRAY_CONSTRUCTOR_E:
                return !getArrayConstructorE().isEmpty();
            case FxtranPackage.ARG_TYPE__LITERAL_E:
                return !getLiteralE().isEmpty();
            case FxtranPackage.ARG_TYPE__NAMED_E:
                return !getNamedE().isEmpty();
            case FxtranPackage.ARG_TYPE__OP_E:
                return !getOpE().isEmpty();
            case FxtranPackage.ARG_TYPE__PARENS_E:
                return !getParensE().isEmpty();
            case FxtranPackage.ARG_TYPE__STRING_E:
                return !getStringE().isEmpty();
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
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //ArgTypeImpl
