/**
 */
package org.oceandsl.hypergraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Caller Callee Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCallee <em>Callee</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.hypergraph.HypergraphPackage#getCallerCalleeTrace()
 * @model
 * @generated
 */
public interface CallerCalleeTrace extends EdgeReference {
    /**
     * Returns the value of the '<em><b>Caller</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Caller</em>' attribute.
     * @see #setCaller(Object)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getCallerCalleeTrace_Caller()
     * @model required="true" transient="true"
     * @generated
     */
    Object getCaller();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCaller <em>Caller</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Caller</em>' attribute.
     * @see #getCaller()
     * @generated
     */
    void setCaller(Object value);

    /**
     * Returns the value of the '<em><b>Callee</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Callee</em>' attribute.
     * @see #setCallee(Object)
     * @see org.oceandsl.hypergraph.HypergraphPackage#getCallerCalleeTrace_Callee()
     * @model required="true" transient="true"
     * @generated
     */
    Object getCallee();

    /**
     * Sets the value of the '{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCallee <em>Callee</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Callee</em>' attribute.
     * @see #getCallee()
     * @generated
     */
    void setCallee(Object value);

} // CallerCalleeTrace
