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

import org.oceandsl.tools.sar.fxtran.DummyArgLTType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.SubroutineNType;
import org.oceandsl.tools.sar.fxtran.SubroutineStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subroutine Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getCnt <em>Cnt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getDummyArgLT <em>Dummy Arg LT</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.SubroutineStmtTypeImpl#getSubroutineN <em>Subroutine N</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubroutineStmtTypeImpl extends MinimalEObjectImpl.Container implements SubroutineStmtType {
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
    protected SubroutineStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getSubroutineStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getSubroutineStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCnt() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSubroutineStmtType_Cnt());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DummyArgLTType> getDummyArgLT() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSubroutineStmtType_DummyArgLT());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getPrefix() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSubroutineStmtType_Prefix());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SubroutineNType> getSubroutineN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getSubroutineStmtType_SubroutineN());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SUBROUTINE_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SUBROUTINE_STMT_TYPE__DUMMY_ARG_LT:
                return ((InternalEList<?>)getDummyArgLT()).basicRemove(otherEnd, msgs);
            case FxtranPackage.SUBROUTINE_STMT_TYPE__SUBROUTINE_N:
                return ((InternalEList<?>)getSubroutineN()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__CNT:
                return getCnt();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__DUMMY_ARG_LT:
                return getDummyArgLT();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__PREFIX:
                return getPrefix();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__SUBROUTINE_N:
                return getSubroutineN();
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
            case FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__CNT:
                getCnt().clear();
                getCnt().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__DUMMY_ARG_LT:
                getDummyArgLT().clear();
                getDummyArgLT().addAll((Collection<? extends DummyArgLTType>)newValue);
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__PREFIX:
                getPrefix().clear();
                getPrefix().addAll((Collection<? extends String>)newValue);
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__SUBROUTINE_N:
                getSubroutineN().clear();
                getSubroutineN().addAll((Collection<? extends SubroutineNType>)newValue);
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
            case FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__CNT:
                getCnt().clear();
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__DUMMY_ARG_LT:
                getDummyArgLT().clear();
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__PREFIX:
                getPrefix().clear();
                return;
            case FxtranPackage.SUBROUTINE_STMT_TYPE__SUBROUTINE_N:
                getSubroutineN().clear();
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
            case FxtranPackage.SUBROUTINE_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__CNT:
                return !getCnt().isEmpty();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__DUMMY_ARG_LT:
                return !getDummyArgLT().isEmpty();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__PREFIX:
                return !getPrefix().isEmpty();
            case FxtranPackage.SUBROUTINE_STMT_TYPE__SUBROUTINE_N:
                return !getSubroutineN().isEmpty();
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

} //SubroutineStmtTypeImpl
