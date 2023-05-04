/**
 */
package restructuremodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Split Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link restructuremodel.SplitComponent#getNewComponent <em>New Component</em>}</li>
 *   <li>{@link restructuremodel.SplitComponent#getOperationsToMove <em>Operations To Move</em>}</li>
 *   <li>{@link restructuremodel.SplitComponent#getOldComponent <em>Old Component</em>}</li>
 *   <li>{@link restructuremodel.SplitComponent#getCreateComponent <em>Create Component</em>}</li>
 *   <li>{@link restructuremodel.SplitComponent#getMoveOperations <em>Move Operations</em>}</li>
 * </ul>
 *
 * @see restructuremodel.RestructuremodelPackage#getSplitComponent()
 * @model
 * @generated
 */
public interface SplitComponent extends AbstractTransformationStep {
	/**
	 * Returns the value of the '<em><b>New Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Component</em>' attribute.
	 * @see #setNewComponent(String)
	 * @see restructuremodel.RestructuremodelPackage#getSplitComponent_NewComponent()
	 * @model
	 * @generated
	 */
	String getNewComponent();

	/**
	 * Sets the value of the '{@link restructuremodel.SplitComponent#getNewComponent <em>New Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Component</em>' attribute.
	 * @see #getNewComponent()
	 * @generated
	 */
	void setNewComponent(String value);

	/**
	 * Returns the value of the '<em><b>Operations To Move</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations To Move</em>' attribute list.
	 * @see restructuremodel.RestructuremodelPackage#getSplitComponent_OperationsToMove()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getOperationsToMove();

	/**
	 * Returns the value of the '<em><b>Old Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Component</em>' attribute.
	 * @see #setOldComponent(String)
	 * @see restructuremodel.RestructuremodelPackage#getSplitComponent_OldComponent()
	 * @model
	 * @generated
	 */
	String getOldComponent();

	/**
	 * Sets the value of the '{@link restructuremodel.SplitComponent#getOldComponent <em>Old Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Component</em>' attribute.
	 * @see #getOldComponent()
	 * @generated
	 */
	void setOldComponent(String value);

	/**
	 * Returns the value of the '<em><b>Create Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Component</em>' containment reference.
	 * @see #setCreateComponent(CreateComponent)
	 * @see restructuremodel.RestructuremodelPackage#getSplitComponent_CreateComponent()
	 * @model containment="true"
	 * @generated
	 */
	CreateComponent getCreateComponent();

	/**
	 * Sets the value of the '{@link restructuremodel.SplitComponent#getCreateComponent <em>Create Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Component</em>' containment reference.
	 * @see #getCreateComponent()
	 * @generated
	 */
	void setCreateComponent(CreateComponent value);

	/**
	 * Returns the value of the '<em><b>Move Operations</b></em>' containment reference list.
	 * The list contents are of type {@link restructuremodel.MoveOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Move Operations</em>' containment reference list.
	 * @see restructuremodel.RestructuremodelPackage#getSplitComponent_MoveOperations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<MoveOperation> getMoveOperations();

} // SplitComponent
