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

import org.oceandsl.tools.sar.fxtran.ENDeclLTType;
import org.oceandsl.tools.sar.fxtran.ENDeclType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EN Decl LT Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclLTTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclLTTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclLTTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ENDeclLTTypeImpl#getENDecl <em>EN Decl</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ENDeclLTTypeImpl extends MinimalEObjectImpl.Container implements ENDeclLTType {
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
    protected ENDeclLTTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getENDeclLTType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.EN_DECL_LT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getENDeclLTType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getENDeclLTType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ENDeclType> getENDecl() {
        return getGroup().list(FxtranPackage.eINSTANCE.getENDeclLTType_ENDecl());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.EN_DECL_LT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_LT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.EN_DECL_LT_TYPE__EN_DECL:
                return ((InternalEList<?>)getENDecl()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.EN_DECL_LT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.EN_DECL_LT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.EN_DECL_LT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.EN_DECL_LT_TYPE__EN_DECL:
                return getENDecl();
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
            case FxtranPackage.EN_DECL_LT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__EN_DECL:
                getENDecl().clear();
                getENDecl().addAll((Collection<? extends ENDeclType>)newValue);
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
            case FxtranPackage.EN_DECL_LT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.EN_DECL_LT_TYPE__EN_DECL:
                getENDecl().clear();
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
            case FxtranPackage.EN_DECL_LT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.EN_DECL_LT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.EN_DECL_LT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.EN_DECL_LT_TYPE__EN_DECL:
                return !getENDecl().isEmpty();
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

} //ENDeclLTTypeImpl
