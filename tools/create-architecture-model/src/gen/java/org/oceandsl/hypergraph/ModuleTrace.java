/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.ModuleTrace#getModule <em>Module</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getModuleTrace()
 * @model
 * @generated
 */
public interface ModuleTrace extends ModuleReference {
    /**
     * Returns the value of the '<em><b>Module</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Module</em>' reference.
     * @see #setModule(org.oceandsl.hypergraph.Module)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getModuleTrace_Module()
     * @model required="true" transient="true"
     * @generated
     */
    org.oceandsl.hypergraph.Module getModule();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.ModuleTrace#getModule <em>Module</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Module</em>' reference.
     * @see #getModule()
     * @generated
     */
    void setModule(org.oceandsl.hypergraph.Module value);

} // ModuleTrace
