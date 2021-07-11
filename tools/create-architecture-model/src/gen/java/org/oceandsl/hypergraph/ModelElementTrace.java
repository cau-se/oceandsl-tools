/**
 */
package org.oceandsl.hypergraph;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.ModelElementTrace#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getModelElementTrace()
 * @model
 * @generated
 */
public interface ModelElementTrace extends EdgeReference, ModuleReference, NodeReference {
    /**
     * Returns the value of the '<em><b>Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Element</em>' reference.
     * @see #setElement(EObject)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModelElementTrace_Element()
     * @model transient="true"
     * @generated
     */
    EObject getElement();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.ModelElementTrace#getElement <em>Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Element</em>' reference.
     * @see #getElement()
     * @generated
     */
    void setElement(EObject value);

} // ModelElementTrace
