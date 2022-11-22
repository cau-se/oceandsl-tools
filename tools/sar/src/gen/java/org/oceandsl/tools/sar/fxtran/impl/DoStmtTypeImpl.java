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

import org.oceandsl.tools.sar.fxtran.DoStmtType;
import org.oceandsl.tools.sar.fxtran.DoVType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.LowerBoundType;
import org.oceandsl.tools.sar.fxtran.NType;
import org.oceandsl.tools.sar.fxtran.TestEType;
import org.oceandsl.tools.sar.fxtran.UpperBoundType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Do Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getN <em>N</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getDoV <em>Do V</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.DoStmtTypeImpl#getTestE <em>Test E</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoStmtTypeImpl extends MinimalEObjectImpl.Container implements DoStmtType {
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
    protected DoStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getDoStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, FxtranPackage.DO_STMT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getGroup() {
        return (FeatureMap)getMixed().<FeatureMap.Entry>list(FxtranPackage.eINSTANCE.getDoStmtType_Group());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NType> getN() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDoStmtType_N());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LowerBoundType> getLowerBound() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDoStmtType_LowerBound());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<UpperBoundType> getUpperBound() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDoStmtType_UpperBound());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DoVType> getDoV() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDoStmtType_DoV());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TestEType> getTestE() {
        return getGroup().list(FxtranPackage.eINSTANCE.getDoStmtType_TestE());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.DO_STMT_TYPE__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__GROUP:
                return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__N:
                return ((InternalEList<?>)getN()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__LOWER_BOUND:
                return ((InternalEList<?>)getLowerBound()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__UPPER_BOUND:
                return ((InternalEList<?>)getUpperBound()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__DO_V:
                return ((InternalEList<?>)getDoV()).basicRemove(otherEnd, msgs);
            case FxtranPackage.DO_STMT_TYPE__TEST_E:
                return ((InternalEList<?>)getTestE()).basicRemove(otherEnd, msgs);
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
            case FxtranPackage.DO_STMT_TYPE__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case FxtranPackage.DO_STMT_TYPE__GROUP:
                if (coreType) return getGroup();
                return ((FeatureMap.Internal)getGroup()).getWrapper();
            case FxtranPackage.DO_STMT_TYPE__N:
                return getN();
            case FxtranPackage.DO_STMT_TYPE__LOWER_BOUND:
                return getLowerBound();
            case FxtranPackage.DO_STMT_TYPE__UPPER_BOUND:
                return getUpperBound();
            case FxtranPackage.DO_STMT_TYPE__DO_V:
                return getDoV();
            case FxtranPackage.DO_STMT_TYPE__TEST_E:
                return getTestE();
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
            case FxtranPackage.DO_STMT_TYPE__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__GROUP:
                ((FeatureMap.Internal)getGroup()).set(newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__N:
                getN().clear();
                getN().addAll((Collection<? extends NType>)newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__LOWER_BOUND:
                getLowerBound().clear();
                getLowerBound().addAll((Collection<? extends LowerBoundType>)newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__UPPER_BOUND:
                getUpperBound().clear();
                getUpperBound().addAll((Collection<? extends UpperBoundType>)newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__DO_V:
                getDoV().clear();
                getDoV().addAll((Collection<? extends DoVType>)newValue);
                return;
            case FxtranPackage.DO_STMT_TYPE__TEST_E:
                getTestE().clear();
                getTestE().addAll((Collection<? extends TestEType>)newValue);
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
            case FxtranPackage.DO_STMT_TYPE__MIXED:
                getMixed().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__GROUP:
                getGroup().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__N:
                getN().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__LOWER_BOUND:
                getLowerBound().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__UPPER_BOUND:
                getUpperBound().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__DO_V:
                getDoV().clear();
                return;
            case FxtranPackage.DO_STMT_TYPE__TEST_E:
                getTestE().clear();
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
            case FxtranPackage.DO_STMT_TYPE__MIXED:
                return mixed != null && !mixed.isEmpty();
            case FxtranPackage.DO_STMT_TYPE__GROUP:
                return !getGroup().isEmpty();
            case FxtranPackage.DO_STMT_TYPE__N:
                return !getN().isEmpty();
            case FxtranPackage.DO_STMT_TYPE__LOWER_BOUND:
                return !getLowerBound().isEmpty();
            case FxtranPackage.DO_STMT_TYPE__UPPER_BOUND:
                return !getUpperBound().isEmpty();
            case FxtranPackage.DO_STMT_TYPE__DO_V:
                return !getDoV().isEmpty();
            case FxtranPackage.DO_STMT_TYPE__TEST_E:
                return !getTestE().isEmpty();
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

} //DoStmtTypeImpl
