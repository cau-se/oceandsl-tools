/**
 */
package restructuremodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Create Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link restructuremodel.CreateComponent#getComponentName <em>Component Name</em>}</li>
 * </ul>
 *
 * @see restructuremodel.RestructuremodelPackage#getCreateComponent()
 * @model
 * @generated
 */
public interface CreateComponent extends AbstractTransformationStep {
	/**
	 * Returns the value of the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Name</em>' attribute.
	 * @see #setComponentName(String)
	 * @see restructuremodel.RestructuremodelPackage#getCreateComponent_ComponentName()
	 * @model
	 * @generated
	 */
	String getComponentName();

	/**
	 * Sets the value of the '{@link restructuremodel.CreateComponent#getComponentName <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Name</em>' attribute.
	 * @see #getComponentName()
	 * @generated
	 */
	void setComponentName(String value);

} // CreateComponent
