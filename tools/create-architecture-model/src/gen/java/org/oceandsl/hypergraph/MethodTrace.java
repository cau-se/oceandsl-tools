/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.MethodTrace#getMethod <em>Method</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getMethodTrace()
 * @model
 * @generated
 */
public interface MethodTrace extends NodeReference {
    /**
     * Returns the value of the '<em><b>Method</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Method</em>' attribute.
     * @see #setMethod(Object)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getMethodTrace_Method()
     * @model required="true" transient="true"
     * @generated
     */
    Object getMethod();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.MethodTrace#getMethod <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method</em>' attribute.
     * @see #getMethod()
     * @generated
     */
    void setMethod(Object value);

} // MethodTrace
