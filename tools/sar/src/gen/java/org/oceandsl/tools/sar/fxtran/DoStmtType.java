/**
 */
package org.oceandsl.tools.sar.fxtran;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Do Stmt Type</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getGroup <em>Group</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getN <em>N</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getLowerBound <em>Lower Bound</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getUpperBound <em>Upper Bound</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getDoV <em>Do V</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.DoStmtType#getTestE <em>Test E</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType()
 * @model extendedMetaData="name='do-stmt_._type' kind='mixed'"
 * @generated
 */
public interface DoStmtType extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list contents are of
     * type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>Group</b></em>' attribute list. The list contents are of
     * type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Group</em>' attribute list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        transient="true" volatile="true" derived="true" extendedMetaData="kind='group'
     *        name='group:1'"
     * @generated
     */
    FeatureMap getGroup();

    /**
     * Returns the value of the '<em><b>N</b></em>' containment reference list. The list contents
     * are of type {@link org.oceandsl.tools.sar.fxtran.NType}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>N</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_N()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='N' namespace='##targetNamespace'
     *        group='#group:1'"
     * @generated
     */
    EList<NType> getN();

    /**
     * Returns the value of the '<em><b>Lower Bound</b></em>' containment reference list. The list
     * contents are of type {@link org.oceandsl.tools.sar.fxtran.LowerBoundType}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lower Bound</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_LowerBound()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='lower-bound' namespace='##targetNamespace'
     *        group='#group:1'"
     * @generated
     */
    EList<LowerBoundType> getLowerBound();

    /**
     * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference list. The list
     * contents are of type {@link org.oceandsl.tools.sar.fxtran.UpperBoundType}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Upper Bound</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_UpperBound()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='upper-bound' namespace='##targetNamespace'
     *        group='#group:1'"
     * @generated
     */
    EList<UpperBoundType> getUpperBound();

    /**
     * Returns the value of the '<em><b>Do V</b></em>' containment reference list. The list contents
     * are of type {@link org.oceandsl.tools.sar.fxtran.DoVType}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Do V</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_DoV()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='do-V' namespace='##targetNamespace'
     *        group='#group:1'"
     * @generated
     */
    EList<DoVType> getDoV();

    /**
     * Returns the value of the '<em><b>Test E</b></em>' containment reference list. The list
     * contents are of type {@link org.oceandsl.tools.sar.fxtran.TestEType}. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Test E</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getDoStmtType_TestE()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='test-E' namespace='##targetNamespace'
     *        group='#group:1'"
     * @generated
     */
    EList<TestEType> getTestE();

} // DoStmtType
