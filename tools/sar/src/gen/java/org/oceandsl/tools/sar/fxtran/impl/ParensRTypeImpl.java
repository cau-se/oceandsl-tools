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

import org.oceandsl.tools.sar.fxtran.ArgSpecType;
import org.oceandsl.tools.sar.fxtran.ElementLTType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.ParensRType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parens RType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ParensRTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ParensRTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ParensRTypeImpl#getArgSpec <em>Arg Spec</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ParensRTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ParensRTypeImpl#getElementLT <em>Element LT</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParensRTypeImpl extends MinimalEObjectImpl.Container implements ParensRType {
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
    protected ParensRTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getParensRType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.PARENS_RTYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getParensRType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgSpecType> getArgSpec() {
        return getGroup().list(FxtranPackage.eINSTANCE.getParensRType_ArgSpec());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getParensRType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ElementLTType> getElementLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getParensRType_ElementLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.PARENS_RTYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.PARENS_RTYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.PARENS_RTYPE__ARG_SPEC:
                return ((InternalEList<?>)getArgSpec()).basicRemove(otherEnd, msgs);
            case FxtranPackage.PARENS_RTYPE__ELEMENT_LT:
                return ((InternalEList<?>)getElementLT()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.PARENS_RTYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.PARENS_RTYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.PARENS_RTYPE__ARG_SPEC:
                return getArgSpec();
            case FxtranPackage.PARENS_RTYPE__CNT:
                return getCnt();
            case FxtranPackage.PARENS_RTYPE__ELEMENT_LT:
                return getElementLT();
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
            case FxtranPackage.PARENS_RTYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.PARENS_RTYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.PARENS_RTYPE__ARG_SPEC:
                getArgSpec().clear();
                getArgSpec().addAll((Collection<? extends ArgSpecType>)newValue);
                return;
            case FxtranPackage.PARENS_RTYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.PARENS_RTYPE__ELEMENT_LT:
                getElementLT().clear();
                getElementLT().addAll((Collection<? extends ElementLTType>)newValue);
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
            case FxtranPackage.PARENS_RTYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.PARENS_RTYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.PARENS_RTYPE__ARG_SPEC:
                getArgSpec().clear();
                return;
            case FxtranPackage.PARENS_RTYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.PARENS_RTYPE__ELEMENT_LT:
                getElementLT().clear();
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
            case FxtranPackage.PARENS_RTYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.PARENS_RTYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.PARENS_RTYPE__ARG_SPEC:
                return !getArgSpec().isEmpty();
            case FxtranPackage.PARENS_RTYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.PARENS_RTYPE__ELEMENT_LT:
                return !getElementLT().isEmpty();
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

} //ParensRTypeImpl
