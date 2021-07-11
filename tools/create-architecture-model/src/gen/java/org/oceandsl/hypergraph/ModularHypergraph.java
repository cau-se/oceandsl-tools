/**
 */
package org.oceandsl.hypergraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modular Hypergraph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.ModularHypergraph#getModules <em>Modules</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getModularHypergraph()
 * @model
 * @generated
 */
public interface ModularHypergraph extends Hypergraph {
    /**
     * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
     * The list contents are of type {@link org.oceandsl.hypergraph.Module}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Modules</em>' containment reference list.
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModularHypergraph_Modules()
     * @model containment="true"
     * @generated
     */
    EList<org.oceandsl.hypergraph.Module> getModules();

} // ModularHypergraph
