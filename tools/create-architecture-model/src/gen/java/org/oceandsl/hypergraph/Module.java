/**
 */
package org.oceandsl.hypergraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.Module#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.oceandsl.hypergraph.Module#getDerivedFrom <em>Derived From</em>}</li>
 *   <li>{@link org.oceandsl.hypergraph.Module#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends NamedElement {
    /**
     * Returns the value of the '<em><b>Nodes</b></em>' reference list.
     * The list contents are of type {@link org.oceandsl.hypergraph.Node}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nodes</em>' reference list.
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModule_Nodes()
     * @model
     * @generated
     */
    EList<Node> getNodes();

    /**
     * Returns the value of the '<em><b>Derived From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Derived From</em>' containment reference.
     * @see #setDerivedFrom(ModuleReference)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModule_DerivedFrom()
     * @model containment="true"
     * @generated
     */
    ModuleReference getDerivedFrom();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.Module#getDerivedFrom <em>Derived From</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived From</em>' containment reference.
     * @see #getDerivedFrom()
     * @generated
     */
    void setDerivedFrom(ModuleReference value);

    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.oceandsl.hypergraph.EModuleKind}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see org.oceandsl.hypergraph.EModuleKind
     * @see #setKind(EModuleKind)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModule_Kind()
     * @model required="true"
     * @generated
     */
    EModuleKind getKind();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.Module#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see org.oceandsl.hypergraph.EModuleKind
     * @see #getKind()
     * @generated
     */
    void setKind(EModuleKind value);

} // Module
