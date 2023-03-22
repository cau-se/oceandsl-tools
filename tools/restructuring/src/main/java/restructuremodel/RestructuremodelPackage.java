/**
 */
package restructuremodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see restructuremodel.RestructuremodelFactory
 * @model kind="package"
 * @generated
 */
public interface RestructuremodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "restructuremodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://restructur.net/restructuremodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "restructuremodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RestructuremodelPackage eINSTANCE = restructuremodel.impl.RestructuremodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link restructuremodel.impl.ComponentsTransformationImpl <em>Components Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.ComponentsTransformationImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getComponentsTransformation()
	 * @generated
	 */
	int COMPONENTS_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Transformations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENTS_TRANSFORMATION__TRANSFORMATIONS = 0;

	/**
	 * The number of structural features of the '<em>Components Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENTS_TRANSFORMATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Components Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENTS_TRANSFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.AbstractTransformationStepImpl <em>Abstract Transformation Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.AbstractTransformationStepImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getAbstractTransformationStep()
	 * @generated
	 */
	int ABSTRACT_TRANSFORMATION_STEP = 1;

	/**
	 * The number of structural features of the '<em>Abstract Transformation Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Abstract Transformation Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.CreateComponentImpl <em>Create Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.CreateComponentImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getCreateComponent()
	 * @generated
	 */
	int CREATE_COMPONENT = 2;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_COMPONENT__COMPONENT_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Create Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_COMPONENT_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Create Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_COMPONENT_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.DeleteComponentImpl <em>Delete Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.DeleteComponentImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getDeleteComponent()
	 * @generated
	 */
	int DELETE_COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_COMPONENT__COMPONENT_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Delete Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_COMPONENT_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Delete Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_COMPONENT_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.CutOperationImpl <em>Cut Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.CutOperationImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getCutOperation()
	 * @generated
	 */
	int CUT_OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUT_OPERATION__COMPONENT_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUT_OPERATION__OPERATION_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cut Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUT_OPERATION_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Cut Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUT_OPERATION_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.PasteOperationImpl <em>Paste Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.PasteOperationImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getPasteOperation()
	 * @generated
	 */
	int PASTE_OPERATION = 5;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASTE_OPERATION__COMPONENT_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASTE_OPERATION__OPERATION_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Paste Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASTE_OPERATION_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Paste Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASTE_OPERATION_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.MoveOperationImpl <em>Move Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.MoveOperationImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getMoveOperation()
	 * @generated
	 */
	int MOVE_OPERATION = 6;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION__FROM = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION__TO = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION__OPERATION_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cut Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION__CUT_OPERATION = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Paste Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION__PASTE_OPERATION = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Move Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Move Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_OPERATION_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.MergeComponentImpl <em>Merge Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.MergeComponentImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getMergeComponent()
	 * @generated
	 */
	int MERGE_COMPONENT = 7;

	/**
	 * The feature id for the '<em><b>Merge Goal Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT__MERGE_GOAL_COMPONENT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT__COMPONENT_NAME = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT__OPERATIONS = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Delete Transformation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT__DELETE_TRANSFORMATION = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operation To Move</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT__OPERATION_TO_MOVE = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Merge Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Merge Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_COMPONENT_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link restructuremodel.impl.SplitComponentImpl <em>Split Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see restructuremodel.impl.SplitComponentImpl
	 * @see restructuremodel.impl.RestructuremodelPackageImpl#getSplitComponent()
	 * @generated
	 */
	int SPLIT_COMPONENT = 8;

	/**
	 * The feature id for the '<em><b>New Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT__NEW_COMPONENT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations To Move</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT__OPERATIONS_TO_MOVE = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Old Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT__OLD_COMPONENT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Create Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT__CREATE_COMPONENT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Move Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT__MOVE_OPERATIONS = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Split Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT_FEATURE_COUNT = ABSTRACT_TRANSFORMATION_STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Split Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_COMPONENT_OPERATION_COUNT = ABSTRACT_TRANSFORMATION_STEP_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link restructuremodel.ComponentsTransformation <em>Components Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Components Transformation</em>'.
	 * @see restructuremodel.ComponentsTransformation
	 * @generated
	 */
	EClass getComponentsTransformation();

	/**
	 * Returns the meta object for the containment reference list '{@link restructuremodel.ComponentsTransformation#getTransformations <em>Transformations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformations</em>'.
	 * @see restructuremodel.ComponentsTransformation#getTransformations()
	 * @see #getComponentsTransformation()
	 * @generated
	 */
	EReference getComponentsTransformation_Transformations();

	/**
	 * Returns the meta object for class '{@link restructuremodel.AbstractTransformationStep <em>Abstract Transformation Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Transformation Step</em>'.
	 * @see restructuremodel.AbstractTransformationStep
	 * @generated
	 */
	EClass getAbstractTransformationStep();

	/**
	 * Returns the meta object for class '{@link restructuremodel.CreateComponent <em>Create Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Component</em>'.
	 * @see restructuremodel.CreateComponent
	 * @generated
	 */
	EClass getCreateComponent();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.CreateComponent#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see restructuremodel.CreateComponent#getComponentName()
	 * @see #getCreateComponent()
	 * @generated
	 */
	EAttribute getCreateComponent_ComponentName();

	/**
	 * Returns the meta object for class '{@link restructuremodel.DeleteComponent <em>Delete Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Component</em>'.
	 * @see restructuremodel.DeleteComponent
	 * @generated
	 */
	EClass getDeleteComponent();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.DeleteComponent#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see restructuremodel.DeleteComponent#getComponentName()
	 * @see #getDeleteComponent()
	 * @generated
	 */
	EAttribute getDeleteComponent_ComponentName();

	/**
	 * Returns the meta object for class '{@link restructuremodel.CutOperation <em>Cut Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cut Operation</em>'.
	 * @see restructuremodel.CutOperation
	 * @generated
	 */
	EClass getCutOperation();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.CutOperation#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see restructuremodel.CutOperation#getComponentName()
	 * @see #getCutOperation()
	 * @generated
	 */
	EAttribute getCutOperation_ComponentName();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.CutOperation#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see restructuremodel.CutOperation#getOperationName()
	 * @see #getCutOperation()
	 * @generated
	 */
	EAttribute getCutOperation_OperationName();

	/**
	 * Returns the meta object for class '{@link restructuremodel.PasteOperation <em>Paste Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paste Operation</em>'.
	 * @see restructuremodel.PasteOperation
	 * @generated
	 */
	EClass getPasteOperation();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.PasteOperation#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see restructuremodel.PasteOperation#getComponentName()
	 * @see #getPasteOperation()
	 * @generated
	 */
	EAttribute getPasteOperation_ComponentName();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.PasteOperation#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see restructuremodel.PasteOperation#getOperationName()
	 * @see #getPasteOperation()
	 * @generated
	 */
	EAttribute getPasteOperation_OperationName();

	/**
	 * Returns the meta object for class '{@link restructuremodel.MoveOperation <em>Move Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Move Operation</em>'.
	 * @see restructuremodel.MoveOperation
	 * @generated
	 */
	EClass getMoveOperation();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.MoveOperation#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see restructuremodel.MoveOperation#getFrom()
	 * @see #getMoveOperation()
	 * @generated
	 */
	EAttribute getMoveOperation_From();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.MoveOperation#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see restructuremodel.MoveOperation#getTo()
	 * @see #getMoveOperation()
	 * @generated
	 */
	EAttribute getMoveOperation_To();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.MoveOperation#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see restructuremodel.MoveOperation#getOperationName()
	 * @see #getMoveOperation()
	 * @generated
	 */
	EAttribute getMoveOperation_OperationName();

	/**
	 * Returns the meta object for the containment reference '{@link restructuremodel.MoveOperation#getCutOperation <em>Cut Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cut Operation</em>'.
	 * @see restructuremodel.MoveOperation#getCutOperation()
	 * @see #getMoveOperation()
	 * @generated
	 */
	EReference getMoveOperation_CutOperation();

	/**
	 * Returns the meta object for the containment reference '{@link restructuremodel.MoveOperation#getPasteOperation <em>Paste Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Paste Operation</em>'.
	 * @see restructuremodel.MoveOperation#getPasteOperation()
	 * @see #getMoveOperation()
	 * @generated
	 */
	EReference getMoveOperation_PasteOperation();

	/**
	 * Returns the meta object for class '{@link restructuremodel.MergeComponent <em>Merge Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Merge Component</em>'.
	 * @see restructuremodel.MergeComponent
	 * @generated
	 */
	EClass getMergeComponent();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.MergeComponent#getMergeGoalComponent <em>Merge Goal Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge Goal Component</em>'.
	 * @see restructuremodel.MergeComponent#getMergeGoalComponent()
	 * @see #getMergeComponent()
	 * @generated
	 */
	EAttribute getMergeComponent_MergeGoalComponent();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.MergeComponent#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see restructuremodel.MergeComponent#getComponentName()
	 * @see #getMergeComponent()
	 * @generated
	 */
	EAttribute getMergeComponent_ComponentName();

	/**
	 * Returns the meta object for the attribute list '{@link restructuremodel.MergeComponent#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Operations</em>'.
	 * @see restructuremodel.MergeComponent#getOperations()
	 * @see #getMergeComponent()
	 * @generated
	 */
	EAttribute getMergeComponent_Operations();

	/**
	 * Returns the meta object for the containment reference '{@link restructuremodel.MergeComponent#getDeleteTransformation <em>Delete Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Delete Transformation</em>'.
	 * @see restructuremodel.MergeComponent#getDeleteTransformation()
	 * @see #getMergeComponent()
	 * @generated
	 */
	EReference getMergeComponent_DeleteTransformation();

	/**
	 * Returns the meta object for the containment reference list '{@link restructuremodel.MergeComponent#getOperationToMove <em>Operation To Move</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operation To Move</em>'.
	 * @see restructuremodel.MergeComponent#getOperationToMove()
	 * @see #getMergeComponent()
	 * @generated
	 */
	EReference getMergeComponent_OperationToMove();

	/**
	 * Returns the meta object for class '{@link restructuremodel.SplitComponent <em>Split Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Split Component</em>'.
	 * @see restructuremodel.SplitComponent
	 * @generated
	 */
	EClass getSplitComponent();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.SplitComponent#getNewComponent <em>New Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Component</em>'.
	 * @see restructuremodel.SplitComponent#getNewComponent()
	 * @see #getSplitComponent()
	 * @generated
	 */
	EAttribute getSplitComponent_NewComponent();

	/**
	 * Returns the meta object for the attribute list '{@link restructuremodel.SplitComponent#getOperationsToMove <em>Operations To Move</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Operations To Move</em>'.
	 * @see restructuremodel.SplitComponent#getOperationsToMove()
	 * @see #getSplitComponent()
	 * @generated
	 */
	EAttribute getSplitComponent_OperationsToMove();

	/**
	 * Returns the meta object for the attribute '{@link restructuremodel.SplitComponent#getOldComponent <em>Old Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Component</em>'.
	 * @see restructuremodel.SplitComponent#getOldComponent()
	 * @see #getSplitComponent()
	 * @generated
	 */
	EAttribute getSplitComponent_OldComponent();

	/**
	 * Returns the meta object for the containment reference '{@link restructuremodel.SplitComponent#getCreateComponent <em>Create Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Create Component</em>'.
	 * @see restructuremodel.SplitComponent#getCreateComponent()
	 * @see #getSplitComponent()
	 * @generated
	 */
	EReference getSplitComponent_CreateComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link restructuremodel.SplitComponent#getMoveOperations <em>Move Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Move Operations</em>'.
	 * @see restructuremodel.SplitComponent#getMoveOperations()
	 * @see #getSplitComponent()
	 * @generated
	 */
	EReference getSplitComponent_MoveOperations();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RestructuremodelFactory getRestructuremodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link restructuremodel.impl.ComponentsTransformationImpl <em>Components Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.ComponentsTransformationImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getComponentsTransformation()
		 * @generated
		 */
		EClass COMPONENTS_TRANSFORMATION = eINSTANCE.getComponentsTransformation();

		/**
		 * The meta object literal for the '<em><b>Transformations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENTS_TRANSFORMATION__TRANSFORMATIONS = eINSTANCE.getComponentsTransformation_Transformations();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.AbstractTransformationStepImpl <em>Abstract Transformation Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.AbstractTransformationStepImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getAbstractTransformationStep()
		 * @generated
		 */
		EClass ABSTRACT_TRANSFORMATION_STEP = eINSTANCE.getAbstractTransformationStep();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.CreateComponentImpl <em>Create Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.CreateComponentImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getCreateComponent()
		 * @generated
		 */
		EClass CREATE_COMPONENT = eINSTANCE.getCreateComponent();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_COMPONENT__COMPONENT_NAME = eINSTANCE.getCreateComponent_ComponentName();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.DeleteComponentImpl <em>Delete Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.DeleteComponentImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getDeleteComponent()
		 * @generated
		 */
		EClass DELETE_COMPONENT = eINSTANCE.getDeleteComponent();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_COMPONENT__COMPONENT_NAME = eINSTANCE.getDeleteComponent_ComponentName();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.CutOperationImpl <em>Cut Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.CutOperationImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getCutOperation()
		 * @generated
		 */
		EClass CUT_OPERATION = eINSTANCE.getCutOperation();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUT_OPERATION__COMPONENT_NAME = eINSTANCE.getCutOperation_ComponentName();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUT_OPERATION__OPERATION_NAME = eINSTANCE.getCutOperation_OperationName();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.PasteOperationImpl <em>Paste Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.PasteOperationImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getPasteOperation()
		 * @generated
		 */
		EClass PASTE_OPERATION = eINSTANCE.getPasteOperation();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PASTE_OPERATION__COMPONENT_NAME = eINSTANCE.getPasteOperation_ComponentName();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PASTE_OPERATION__OPERATION_NAME = eINSTANCE.getPasteOperation_OperationName();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.MoveOperationImpl <em>Move Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.MoveOperationImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getMoveOperation()
		 * @generated
		 */
		EClass MOVE_OPERATION = eINSTANCE.getMoveOperation();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_OPERATION__FROM = eINSTANCE.getMoveOperation_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_OPERATION__TO = eINSTANCE.getMoveOperation_To();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_OPERATION__OPERATION_NAME = eINSTANCE.getMoveOperation_OperationName();

		/**
		 * The meta object literal for the '<em><b>Cut Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOVE_OPERATION__CUT_OPERATION = eINSTANCE.getMoveOperation_CutOperation();

		/**
		 * The meta object literal for the '<em><b>Paste Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOVE_OPERATION__PASTE_OPERATION = eINSTANCE.getMoveOperation_PasteOperation();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.MergeComponentImpl <em>Merge Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.MergeComponentImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getMergeComponent()
		 * @generated
		 */
		EClass MERGE_COMPONENT = eINSTANCE.getMergeComponent();

		/**
		 * The meta object literal for the '<em><b>Merge Goal Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MERGE_COMPONENT__MERGE_GOAL_COMPONENT = eINSTANCE.getMergeComponent_MergeGoalComponent();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MERGE_COMPONENT__COMPONENT_NAME = eINSTANCE.getMergeComponent_ComponentName();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MERGE_COMPONENT__OPERATIONS = eINSTANCE.getMergeComponent_Operations();

		/**
		 * The meta object literal for the '<em><b>Delete Transformation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MERGE_COMPONENT__DELETE_TRANSFORMATION = eINSTANCE.getMergeComponent_DeleteTransformation();

		/**
		 * The meta object literal for the '<em><b>Operation To Move</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MERGE_COMPONENT__OPERATION_TO_MOVE = eINSTANCE.getMergeComponent_OperationToMove();

		/**
		 * The meta object literal for the '{@link restructuremodel.impl.SplitComponentImpl <em>Split Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see restructuremodel.impl.SplitComponentImpl
		 * @see restructuremodel.impl.RestructuremodelPackageImpl#getSplitComponent()
		 * @generated
		 */
		EClass SPLIT_COMPONENT = eINSTANCE.getSplitComponent();

		/**
		 * The meta object literal for the '<em><b>New Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_COMPONENT__NEW_COMPONENT = eINSTANCE.getSplitComponent_NewComponent();

		/**
		 * The meta object literal for the '<em><b>Operations To Move</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_COMPONENT__OPERATIONS_TO_MOVE = eINSTANCE.getSplitComponent_OperationsToMove();

		/**
		 * The meta object literal for the '<em><b>Old Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_COMPONENT__OLD_COMPONENT = eINSTANCE.getSplitComponent_OldComponent();

		/**
		 * The meta object literal for the '<em><b>Create Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPLIT_COMPONENT__CREATE_COMPONENT = eINSTANCE.getSplitComponent_CreateComponent();

		/**
		 * The meta object literal for the '<em><b>Move Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPLIT_COMPONENT__MOVE_OPERATIONS = eINSTANCE.getSplitComponent_MoveOperations();

	}

} //RestructuremodelPackage
