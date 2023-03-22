/**
 */
package restructuremodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Components Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link restructuremodel.ComponentsTransformation#getTransformations <em>Transformations</em>}</li>
 * </ul>
 *
 * @see restructuremodel.RestructuremodelPackage#getComponentsTransformation()
 * @model
 * @generated
 */
public interface ComponentsTransformation extends EObject {
	/**
	 * Returns the value of the '<em><b>Transformations</b></em>' containment reference list.
	 * The list contents are of type {@link restructuremodel.AbstractTransformationStep}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformations</em>' containment reference list.
	 * @see restructuremodel.RestructuremodelPackage#getComponentsTransformation_Transformations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<AbstractTransformationStep> getTransformations();

} // ComponentsTransformation
