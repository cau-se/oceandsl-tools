/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.EdgeTrace#getEdge <em>Edge</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getEdgeTrace()
 * @model
 * @generated
 */
public interface EdgeTrace extends EdgeReference {
    /**
     * Returns the value of the '<em><b>Edge</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge</em>' reference.
     * @see #setEdge(Edge)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getEdgeTrace_Edge()
     * @model required="true" transient="true"
     * @generated
     */
    Edge getEdge();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.EdgeTrace#getEdge <em>Edge</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge</em>' reference.
     * @see #getEdge()
     * @generated
     */
    void setEdge(Edge value);

} // EdgeTrace
