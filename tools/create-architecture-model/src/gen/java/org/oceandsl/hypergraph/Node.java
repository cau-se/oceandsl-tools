/**
 */
package org.oceandsl.hypergraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.Node#getEdges <em>Edges</em>}</li>
 *   <li>{@link org.oceandsl.hypergraph.Node#getDerivedFrom <em>Derived From</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends NamedElement {
    /**
     * Returns the value of the '<em><b>Edges</b></em>' reference list.
     * The list contents are of type {@link org.oceandsl.hypergraph.Edge}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edges</em>' reference list.
     * @see org.oceandsl.hypergraph.HypergraphPackage#getNode_Edges()
     * @model
     * @generated
     */
    EList<Edge> getEdges();

    /**
     * Returns the value of the '<em><b>Derived From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Derived From</em>' containment reference.
     * @see #setDerivedFrom(NodeReference)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getNode_DerivedFrom()
     * @model containment="true" required="true"
     * @generated
     */
    NodeReference getDerivedFrom();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.Node#getDerivedFrom <em>Derived From</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived From</em>' containment reference.
     * @see #getDerivedFrom()
     * @generated
     */
    void setDerivedFrom(NodeReference value);

} // Node
