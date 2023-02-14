/**
 */
package org.oceandsl.tools.sar.fxtran;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Iterator Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.oceandsl.tools.sar.fxtran.IteratorType#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.IteratorType#getIteratorDefinitionLT <em>Iterator
 * Definition LT</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getIteratorType()
 * @model extendedMetaData="name='iterator_._type' kind='mixed'"
 * @generated
 */
public interface IteratorType extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list contents are of
     * type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getIteratorType_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>Iterator Definition LT</b></em>' containment reference list.
     * The list contents are of type {@link org.oceandsl.tools.sar.fxtran.IteratorDefinitionLTType}.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Iterator Definition LT</em>' containment reference list.
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getIteratorType_IteratorDefinitionLT()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='iterator-definition-LT'
     *        namespace='##targetNamespace'"
     * @generated
     */
    EList<IteratorDefinitionLTType> getIteratorDefinitionLT();

} // IteratorType
