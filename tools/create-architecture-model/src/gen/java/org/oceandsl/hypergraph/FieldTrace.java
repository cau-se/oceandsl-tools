/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.FieldTrace#getField <em>Field</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getFieldTrace()
 * @model
 * @generated
 */
public interface FieldTrace extends EdgeReference {
    /**
     * Returns the value of the '<em><b>Field</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Field</em>' attribute.
     * @see #setField(Object)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getFieldTrace_Field()
     * @model required="true" transient="true"
     * @generated
     */
    Object getField();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.FieldTrace#getField <em>Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Field</em>' attribute.
     * @see #getField()
     * @generated
     */
    void setField(Object value);

} // FieldTrace
