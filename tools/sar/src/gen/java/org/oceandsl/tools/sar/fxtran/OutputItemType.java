/**
 */
package org.oceandsl.tools.sar.fxtran;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Output Item Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getLiteralE <em>Literal E</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getNamedE <em>Named E</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getOpE <em>Op E</em>}</li>
 * <li>{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getStringE <em>String E</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getOutputItemType()
 * @model extendedMetaData="name='output-item_._type' kind='elementOnly'"
 * @generated
 */
public interface OutputItemType extends EObject {
    /**
     * Returns the value of the '<em><b>Literal E</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Literal E</em>' containment reference.
     * @see #setLiteralE(LiteralEType)
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getOutputItemType_LiteralE()
     * @model containment="true" extendedMetaData="kind='element' name='literal-E'
     *        namespace='##targetNamespace'"
     * @generated
     */
    LiteralEType getLiteralE();

    /**
     * Sets the value of the '{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getLiteralE
     * <em>Literal E</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Literal E</em>' containment reference.
     * @see #getLiteralE()
     * @generated
     */
    void setLiteralE(LiteralEType value);

    /**
     * Returns the value of the '<em><b>Named E</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Named E</em>' containment reference.
     * @see #setNamedE(NamedEType)
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getOutputItemType_NamedE()
     * @model containment="true" extendedMetaData="kind='element' name='named-E'
     *        namespace='##targetNamespace'"
     * @generated
     */
    NamedEType getNamedE();

    /**
     * Sets the value of the '{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getNamedE
     * <em>Named E</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Named E</em>' containment reference.
     * @see #getNamedE()
     * @generated
     */
    void setNamedE(NamedEType value);

    /**
     * Returns the value of the '<em><b>Op E</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Op E</em>' containment reference.
     * @see #setOpE(OpEType)
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getOutputItemType_OpE()
     * @model containment="true" extendedMetaData="kind='element' name='op-E'
     *        namespace='##targetNamespace'"
     * @generated
     */
    OpEType getOpE();

    /**
     * Sets the value of the '{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getOpE <em>Op
     * E</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Op E</em>' containment reference.
     * @see #getOpE()
     * @generated
     */
    void setOpE(OpEType value);

    /**
     * Returns the value of the '<em><b>String E</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>String E</em>' containment reference.
     * @see #setStringE(StringEType)
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getOutputItemType_StringE()
     * @model containment="true" extendedMetaData="kind='element' name='string-E'
     *        namespace='##targetNamespace'"
     * @generated
     */
    StringEType getStringE();

    /**
     * Sets the value of the '{@link org.oceandsl.tools.sar.fxtran.OutputItemType#getStringE
     * <em>String E</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>String E</em>' containment reference.
     * @see #getStringE()
     * @generated
     */
    void setStringE(StringEType value);

} // OutputItemType
