/**
 */
package org.oceandsl.hypergraph.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oceandsl.hypergraph.HypergraphPackage;
import org.oceandsl.hypergraph.ModularHypergraph;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modular Hypergraph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.impl.ModularHypergraphImpl#getModules <em>Modules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModularHypergraphImpl extends HypergraphImpl implements ModularHypergraph {
    /**
     * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModules()
     * @generated
     * @ordered
     */
    protected EList<org.oceandsl.hypergraph.Module> modules;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModularHypergraphImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return HypergraphPackage.Literals.MODULAR_HYPERGRAPH;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<org.oceandsl.hypergraph.Module> getModules() {
        if (modules == null) {
            modules = new EObjectContainmentEList<org.oceandsl.hypergraph.Module>(org.oceandsl.hypergraph.Module.class, this, HypergraphPackage.MODULAR_HYPERGRAPH__MODULES);
        }
        return modules;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case HypergraphPackage.MODULAR_HYPERGRAPH__MODULES:
                return ((InternalEList<?>)getModules()).basicRemove(otherEnd, msgs);
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
            case HypergraphPackage.MODULAR_HYPERGRAPH__MODULES:
                return getModules();
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
            case HypergraphPackage.MODULAR_HYPERGRAPH__MODULES:
                getModules().clear();
                getModules().addAll((Collection<? extends org.oceandsl.hypergraph.Module>)newValue);
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
            case HypergraphPackage.MODULAR_HYPERGRAPH__MODULES:
                getModules().clear();
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
            case HypergraphPackage.MODULAR_HYPERGRAPH__MODULES:
                return modules != null && !modules.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ModularHypergraphImpl
