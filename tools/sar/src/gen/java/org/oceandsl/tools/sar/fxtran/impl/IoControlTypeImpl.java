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
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.IoControlType;
import org.oceandsl.tools.sar.fxtran.LabelType;
import org.oceandsl.tools.sar.fxtran.LiteralEType;
import org.oceandsl.tools.sar.fxtran.NamedEType;
import org.oceandsl.tools.sar.fxtran.StringEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Io Control Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getArgN <em>Arg N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getLiteralE <em>Literal E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getNamedE <em>Named E</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.IoControlTypeImpl#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IoControlTypeImpl extends MinimalEObjectImpl.Container implements IoControlType {
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
    protected IoControlTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getIoControlType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.IO_CONTROL_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getIoControlType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ArgNType> getArgN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getIoControlType_ArgN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LabelType> getLabel() {
        return getGroup().list(FxtranPackage.eINSTANCE.getIoControlType_Label());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LiteralEType> getLiteralE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getIoControlType_LiteralE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedEType> getNamedE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getIoControlType_NamedE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<StringEType> getStringE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getIoControlType_StringE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.IO_CONTROL_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__ARG_N:
                return ((InternalEList<?>)getArgN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__LABEL:
                return ((InternalEList<?>)getLabel()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__LITERAL_E:
                return ((InternalEList<?>)getLiteralE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__NAMED_E:
                return ((InternalEList<?>)getNamedE()).basicRemove(otherEnd, msgs);
            case FxtranPackage.IO_CONTROL_TYPE__STRING_E:
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
            case FxtranPackage.IO_CONTROL_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.IO_CONTROL_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.IO_CONTROL_TYPE__ARG_N:
                return getArgN();
            case FxtranPackage.IO_CONTROL_TYPE__LABEL:
                return getLabel();
            case FxtranPackage.IO_CONTROL_TYPE__LITERAL_E:
                return getLiteralE();
            case FxtranPackage.IO_CONTROL_TYPE__NAMED_E:
                return getNamedE();
            case FxtranPackage.IO_CONTROL_TYPE__STRING_E:
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
            case FxtranPackage.IO_CONTROL_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__ARG_N:
                getArgN().clear();
                getArgN().addAll((Collection<? extends ArgNType>)newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__LABEL:
                getLabel().clear();
                getLabel().addAll((Collection<? extends LabelType>)newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__LITERAL_E:
                getLiteralE().clear();
                getLiteralE().addAll((Collection<? extends LiteralEType>)newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__NAMED_E:
                getNamedE().clear();
                getNamedE().addAll((Collection<? extends NamedEType>)newValue);
                return;
            case FxtranPackage.IO_CONTROL_TYPE__STRING_E:
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
            case FxtranPackage.IO_CONTROL_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__ARG_N:
                getArgN().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__LABEL:
                getLabel().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__LITERAL_E:
                getLiteralE().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__NAMED_E:
                getNamedE().clear();
                return;
            case FxtranPackage.IO_CONTROL_TYPE__STRING_E:
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
            case FxtranPackage.IO_CONTROL_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__ARG_N:
                return !getArgN().isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__LABEL:
                return !getLabel().isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__LITERAL_E:
                return !getLiteralE().isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__NAMED_E:
                return !getNamedE().isEmpty();
            case FxtranPackage.IO_CONTROL_TYPE__STRING_E:
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

} //IoControlTypeImpl
