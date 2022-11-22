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
import org.oceandsl.tools.sar.fxtran.KSpecType;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NType;
import org.oceandsl.tools.sar.fxtran.NamedEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KSpec Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getN <em>N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getL <em>L</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.KSpecTypeImpl#getNamedE <em>Named E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KSpecTypeImpl extends MinimalEObjectImpl.Container implements KSpecType {
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
    protected KSpecTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getKSpecType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.KSPEC_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getKSpecType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NType> getN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getKSpecType_N());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getL() {
        return getGroup().list(FxtranPackage.eINSTANCE.getKSpecType_L());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LiteralEType> getLiteralE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getKSpecType_LiteralE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getKSpecType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.KSPEC_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.KSPEC_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.KSPEC_TYPE__N:
                return ((InternalEList<?>)getN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.KSPEC_TYPE__LITERAL_E:
                return ((InternalEList<?>)getLiteralE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.KSPEC_TYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.KSPEC_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.KSPEC_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.KSPEC_TYPE__N:
                return getN();
            case FxtranPackage.KSPEC_TYPE__L:
                return getL();
            case FxtranPackage.KSPEC_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.KSPEC_TYPE__NAMED_E:
                return getNamedE();
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
            case FxtranPackage.KSPEC_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.KSPEC_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.KSPEC_TYPE__N:
                getN().clear();
                getN().addAll((Collection<? extends NType>)newValue);
                return;
            case FxtranPackage.KSPEC_TYPE__L:
                getL().clear();
                getL().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.KSPEC_TYPE__LITERAL_E:
                getLiteralE().clear();
                getLiteralE().addAll((Collection<? extends LiteralEType>)newValue);
                return;
            case FxtranPackage.KSPEC_TYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
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
            case FxtranPackage.KSPEC_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.KSPEC_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.KSPEC_TYPE__N:
                getN().clear();
                return;
            case FxtranPackage.KSPEC_TYPE__L:
                getL().clear();
                return;
            case FxtranPackage.KSPEC_TYPE__LITERAL_E:
                getLiteralE().clear();
                return;
            case FxtranPackage.KSPEC_TYPE__NAMED_E:
                getNamedE().clear();
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
            case FxtranPackage.KSPEC_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.KSPEC_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.KSPEC_TYPE__N:
                return !getN().isEmpty();
            case FxtranPackage.KSPEC_TYPE__L:
                return !getL().isEmpty();
            case FxtranPackage.KSPEC_TYPE__LITERAL_E:
                return !getLiteralE().isEmpty();
            case FxtranPackage.KSPEC_TYPE__NAMED_E:
                return !getNamedE().isEmpty();
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

} //KSpecTypeImpl
