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

import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.OpEType;
import org.oceandsl.tools.sar.fxtran.OpType;
import org.oceandsl.tools.sar.fxtran.ParensEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Op EType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getOpE <em>Op E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getParensE <em>Parens E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.OpETypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OpETypeImpl extends MinimalEObjectImpl.Container implements OpEType {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OpETypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getOpEType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, FxtranPackage.OP_ETYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LiteralEType> getLiteralE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_LiteralE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OpType> getOp() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_Op());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OpEType> getOpE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_OpE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ParensEType> getParensE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_ParensE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<StringEType> getStringE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getOpEType_StringE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.OP_ETYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__LITERAL_E:
                return ((InternalEList<?>)getLiteralE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__OP:
                return ((InternalEList<?>)getOp()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__OP_E:
                return ((InternalEList<?>)getOpE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__PARENS_E:
                return ((InternalEList<?>)getParensE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.OP_ETYPE__STRING_E:
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
            case FxtranPackage.OP_ETYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.OP_ETYPE__CNT:
                return getCnt();
            case FxtranPackage.OP_ETYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.OP_ETYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.OP_ETYPE__OP:
                return getOp();
            case FxtranPackage.OP_ETYPE__OP_E:
                return getOpE();
            case FxtranPackage.OP_ETYPE__PARENS_E:
                return getParensE();
            case FxtranPackage.OP_ETYPE__STRING_E:
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
            case FxtranPackage.OP_ETYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.OP_ETYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__LITERAL_E:
                getLiteralE().clear();
                getLiteralE().addAll((Collection<? extends LiteralEType>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__OP:
                getOp().clear();
                getOp().addAll((Collection<? extends OpType>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__OP_E:
                getOpE().clear();
                getOpE().addAll((Collection<? extends OpEType>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__PARENS_E:
                getParensE().clear();
                getParensE().addAll((Collection<? extends ParensEType>)newValue);
                return;
            case FxtranPackage.OP_ETYPE__STRING_E:
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
            case FxtranPackage.OP_ETYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.OP_ETYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.OP_ETYPE__LITERAL_E:
                getLiteralE().clear();
                return;
            case FxtranPackage.OP_ETYPE__NAMED_E:
                getNamedE().clear();
                return;
            case FxtranPackage.OP_ETYPE__OP:
                getOp().clear();
                return;
            case FxtranPackage.OP_ETYPE__OP_E:
                getOpE().clear();
                return;
            case FxtranPackage.OP_ETYPE__PARENS_E:
                getParensE().clear();
                return;
            case FxtranPackage.OP_ETYPE__STRING_E:
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
            case FxtranPackage.OP_ETYPE__GROUP:
                return group != null && !group.isEmpty();
            case FxtranPackage.OP_ETYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.OP_ETYPE__LITERAL_E:
                return !getLiteralE().isEmpty();
            case FxtranPackage.OP_ETYPE__NAMED_E:
                return !getNamedE().isEmpty();
            case FxtranPackage.OP_ETYPE__OP:
                return !getOp().isEmpty();
            case FxtranPackage.OP_ETYPE__OP_E:
                return !getOpE().isEmpty();
            case FxtranPackage.OP_ETYPE__PARENS_E:
                return !getParensE().isEmpty();
            case FxtranPackage.OP_ETYPE__STRING_E:
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
        result.append(" (group: ");
        result.append(group);
        result.append(')');
        return result.toString();
    }

} //OpETypeImpl
