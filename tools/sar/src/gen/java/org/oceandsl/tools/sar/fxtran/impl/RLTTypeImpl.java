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

import org.oceandsl.tools.sar.fxtran.ArrayRType;
import org.oceandsl.tools.sar.fxtran.ComponentRType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.ParensRType;
import org.oceandsl.tools.sar.fxtran.RLTType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RLT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.RLTTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.RLTTypeImpl#getArrayR <em>Array R</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.RLTTypeImpl#getComponentR <em>Component R</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.RLTTypeImpl#getParensR <em>Parens R</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RLTTypeImpl extends MinimalEObjectImpl.Container implements RLTType {
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
    protected RLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getRLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        if (group == null) {
            group = new BasicFeatureMap(this, FxtranPackage.RLT_TYPE__GROUP);
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArrayRType> getArrayR() {
        return getGroup().list(FxtranPackage.eINSTANCE.getRLTType_ArrayR());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ComponentRType> getComponentR() {
        return getGroup().list(FxtranPackage.eINSTANCE.getRLTType_ComponentR());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ParensRType> getParensR() {
        return getGroup().list(FxtranPackage.eINSTANCE.getRLTType_ParensR());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.RLT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.RLT_TYPE__ARRAY_R:
                return ((InternalEList<?>)getArrayR()).basicRemove(otherEnd, msgs);
            case FxtranPackage.RLT_TYPE__COMPONENT_R:
                return ((InternalEList<?>)getComponentR()).basicRemove(otherEnd, msgs);
            case FxtranPackage.RLT_TYPE__PARENS_R:
                return ((InternalEList<?>)getParensR()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.RLT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.RLT_TYPE__ARRAY_R:
                return getArrayR();
            case FxtranPackage.RLT_TYPE__COMPONENT_R:
                return getComponentR();
            case FxtranPackage.RLT_TYPE__PARENS_R:
                return getParensR();
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
            case FxtranPackage.RLT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.RLT_TYPE__ARRAY_R:
                getArrayR().clear();
                getArrayR().addAll((Collection<? extends ArrayRType>)newValue);
                return;
            case FxtranPackage.RLT_TYPE__COMPONENT_R:
                getComponentR().clear();
                getComponentR().addAll((Collection<? extends ComponentRType>)newValue);
                return;
            case FxtranPackage.RLT_TYPE__PARENS_R:
                getParensR().clear();
                getParensR().addAll((Collection<? extends ParensRType>)newValue);
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
            case FxtranPackage.RLT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.RLT_TYPE__ARRAY_R:
                getArrayR().clear();
                return;
            case FxtranPackage.RLT_TYPE__COMPONENT_R:
                getComponentR().clear();
                return;
            case FxtranPackage.RLT_TYPE__PARENS_R:
                getParensR().clear();
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
            case FxtranPackage.RLT_TYPE__GROUP:
                return group != null && !group.isEmpty();
            case FxtranPackage.RLT_TYPE__ARRAY_R:
                return !getArrayR().isEmpty();
            case FxtranPackage.RLT_TYPE__COMPONENT_R:
                return !getComponentR().isEmpty();
            case FxtranPackage.RLT_TYPE__PARENS_R:
                return !getParensR().isEmpty();
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

} //RLTTypeImpl
