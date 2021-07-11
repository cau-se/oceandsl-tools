/**
 */
package org.oceandsl.hypergraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.oceandsl.hypergraph.HypergraphPackage;
import org.oceandsl.hypergraph.ModuleTrace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.impl.ModuleTraceImpl#getModule <em>Module</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModuleTraceImpl extends ModuleReferenceImpl implements ModuleTrace {
    /**
     * The cached value of the '{@link #getModule() <em>Module</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModule()
     * @generated
     * @ordered
     */
    protected org.oceandsl.hypergraph.Module module;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModuleTraceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return HypergraphPackage.Literals.MODULE_TRACE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.oceandsl.hypergraph.Module getModule() {
        if (module != null && module.eIsProxy()) {
            InternalEObject oldModule = (InternalEObject)module;
            module = (org.oceandsl.hypergraph.Module)eResolveProxy(oldModule);
            if (module != oldModule) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, HypergraphPackage.MODULE_TRACE__MODULE, oldModule, module));
            }
        }
        return module;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.oceandsl.hypergraph.Module basicGetModule() {
        return module;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModule(org.oceandsl.hypergraph.Module newModule) {
        org.oceandsl.hypergraph.Module oldModule = module;
        module = newModule;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, HypergraphPackage.MODULE_TRACE__MODULE, oldModule, module));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case HypergraphPackage.MODULE_TRACE__MODULE:
                if (resolve) return getModule();
                return basicGetModule();
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
            case HypergraphPackage.MODULE_TRACE__MODULE:
                setModule((org.oceandsl.hypergraph.Module)newValue);
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
            case HypergraphPackage.MODULE_TRACE__MODULE:
                setModule((org.oceandsl.hypergraph.Module)null);
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
            case HypergraphPackage.MODULE_TRACE__MODULE:
                return module != null;
        }
        return super.eIsSet(featureID);
    }

} //ModuleTraceImpl
