/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.TypeTrace#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getTypeTrace()
 * @model
 * @generated
 */
public interface TypeTrace extends ModuleReference, NodeReference {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(Object)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getTypeTrace_Type()
     * @model required="true" transient="true"
     * @generated
     */
    Object getType();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.TypeTrace#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(Object value);

} // TypeTrace
