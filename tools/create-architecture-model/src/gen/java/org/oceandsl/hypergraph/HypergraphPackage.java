/**
 */
package org.oceandsl.hypergraph;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.oceandsl.hypergraph.HypergraphFactory
 * @model kind="package"
 * @generated
 */
public interface HypergraphPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "hypergraph";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://evaluation.se.cs.cau.de/hypergraph";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "hypergraph";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    HypergraphPackage eINSTANCE = org.oceandsl.hypergraph.impl.HypergraphPackageImpl.init();

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.HypergraphImpl <em>Hypergraph</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.HypergraphImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getHypergraph()
     * @generated
     */
    int HYPERGRAPH = 0;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPERGRAPH__NODES = 0;

    /**
     * The feature id for the '<em><b>Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPERGRAPH__EDGES = 1;

    /**
     * The number of structural features of the '<em>Hypergraph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPERGRAPH_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Hypergraph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HYPERGRAPH_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.ModularHypergraphImpl <em>Modular Hypergraph</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.ModularHypergraphImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModularHypergraph()
     * @generated
     */
    int MODULAR_HYPERGRAPH = 1;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULAR_HYPERGRAPH__NODES = HYPERGRAPH__NODES;

    /**
     * The feature id for the '<em><b>Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULAR_HYPERGRAPH__EDGES = HYPERGRAPH__EDGES;

    /**
     * The feature id for the '<em><b>Modules</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULAR_HYPERGRAPH__MODULES = HYPERGRAPH_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Modular Hypergraph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULAR_HYPERGRAPH_FEATURE_COUNT = HYPERGRAPH_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Modular Hypergraph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULAR_HYPERGRAPH_OPERATION_COUNT = HYPERGRAPH_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.NamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.NamedElementImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNamedElement()
     * @generated
     */
    int NAMED_ELEMENT = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__NAME = 0;

    /**
     * The number of structural features of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.ModuleImpl <em>Module</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.ModuleImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModule()
     * @generated
     */
    int MODULE = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE__NODES = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Derived From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE__DERIVED_FROM = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE__KIND = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Module</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Module</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.NodeImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNode()
     * @generated
     */
    int NODE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__EDGES = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Derived From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__DERIVED_FROM = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.EdgeImpl <em>Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.EdgeImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdge()
     * @generated
     */
    int EDGE = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Derived From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__DERIVED_FROM = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.NodeReferenceImpl <em>Node Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.NodeReferenceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNodeReference()
     * @generated
     */
    int NODE_REFERENCE = 9;

    /**
     * The number of structural features of the '<em>Node Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_REFERENCE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Node Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_REFERENCE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.NodeTraceImpl <em>Node Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.NodeTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNodeTrace()
     * @generated
     */
    int NODE_TRACE = 6;

    /**
     * The feature id for the '<em><b>Node</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TRACE__NODE = NODE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Node Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TRACE_FEATURE_COUNT = NODE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Node Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_TRACE_OPERATION_COUNT = NODE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.EdgeReferenceImpl <em>Edge Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.EdgeReferenceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdgeReference()
     * @generated
     */
    int EDGE_REFERENCE = 10;

    /**
     * The number of structural features of the '<em>Edge Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_REFERENCE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Edge Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_REFERENCE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.EdgeTraceImpl <em>Edge Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.EdgeTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdgeTrace()
     * @generated
     */
    int EDGE_TRACE = 7;

    /**
     * The feature id for the '<em><b>Edge</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TRACE__EDGE = EDGE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Edge Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TRACE_FEATURE_COUNT = EDGE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Edge Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_TRACE_OPERATION_COUNT = EDGE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.GenericTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getGenericTrace()
     * @generated
     */
    int GENERIC_TRACE = 8;

    /**
     * The feature id for the '<em><b>Resource Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_TRACE__RESOURCE_ID = NODE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Generic Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_TRACE_FEATURE_COUNT = NODE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Generic Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERIC_TRACE_OPERATION_COUNT = NODE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.ModuleReferenceImpl <em>Module Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.ModuleReferenceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModuleReference()
     * @generated
     */
    int MODULE_REFERENCE = 12;

    /**
     * The number of structural features of the '<em>Module Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_REFERENCE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Module Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_REFERENCE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.ModuleTraceImpl <em>Module Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.ModuleTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModuleTrace()
     * @generated
     */
    int MODULE_TRACE = 11;

    /**
     * The feature id for the '<em><b>Module</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_TRACE__MODULE = MODULE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Module Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_TRACE_FEATURE_COUNT = MODULE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Module Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODULE_TRACE_OPERATION_COUNT = MODULE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.TypeTraceImpl <em>Type Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.TypeTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getTypeTrace()
     * @generated
     */
    int TYPE_TRACE = 13;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_TRACE__TYPE = MODULE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Type Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_TRACE_FEATURE_COUNT = MODULE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Type Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_TRACE_OPERATION_COUNT = MODULE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.FieldTraceImpl <em>Field Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.FieldTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getFieldTrace()
     * @generated
     */
    int FIELD_TRACE = 14;

    /**
     * The feature id for the '<em><b>Field</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_TRACE__FIELD = EDGE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Field Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_TRACE_FEATURE_COUNT = EDGE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Field Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_TRACE_OPERATION_COUNT = EDGE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.MethodTraceImpl <em>Method Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.MethodTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getMethodTrace()
     * @generated
     */
    int METHOD_TRACE = 15;

    /**
     * The feature id for the '<em><b>Method</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_TRACE__METHOD = NODE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Method Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_TRACE_FEATURE_COUNT = NODE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Method Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_TRACE_OPERATION_COUNT = NODE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.CallerCalleeTraceImpl <em>Caller Callee Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.CallerCalleeTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getCallerCalleeTrace()
     * @generated
     */
    int CALLER_CALLEE_TRACE = 16;

    /**
     * The feature id for the '<em><b>Caller</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALLER_CALLEE_TRACE__CALLER = EDGE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Callee</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALLER_CALLEE_TRACE__CALLEE = EDGE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Caller Callee Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALLER_CALLEE_TRACE_FEATURE_COUNT = EDGE_REFERENCE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Caller Callee Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALLER_CALLEE_TRACE_OPERATION_COUNT = EDGE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.impl.ModelElementTraceImpl <em>Model Element Trace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.impl.ModelElementTraceImpl
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModelElementTrace()
     * @generated
     */
    int MODEL_ELEMENT_TRACE = 17;

    /**
     * The feature id for the '<em><b>Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT_TRACE__ELEMENT = EDGE_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model Element Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT_TRACE_FEATURE_COUNT = EDGE_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Model Element Trace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT_TRACE_OPERATION_COUNT = EDGE_REFERENCE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.oceandsl.hypergraph.EModuleKind <em>EModule Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oceandsl.hypergraph.EModuleKind
     * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEModuleKind()
     * @generated
     */
    int EMODULE_KIND = 18;


    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.Hypergraph <em>Hypergraph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Hypergraph</em>'.
     * @see org.oceandsl.hypergraph.Hypergraph
     * @generated
     */
    EClass getHypergraph();

    /**
     * Returns the meta object for the containment reference list '{@link org.oceandsl.hypergraph.Hypergraph#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Nodes</em>'.
     * @see org.oceandsl.hypergraph.Hypergraph#getNodes()
     * @see #getHypergraph()
     * @generated
     */
    EReference getHypergraph_Nodes();

    /**
     * Returns the meta object for the containment reference list '{@link org.oceandsl.hypergraph.Hypergraph#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edges</em>'.
     * @see org.oceandsl.hypergraph.Hypergraph#getEdges()
     * @see #getHypergraph()
     * @generated
     */
    EReference getHypergraph_Edges();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.ModularHypergraph <em>Modular Hypergraph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Modular Hypergraph</em>'.
     * @see org.oceandsl.hypergraph.ModularHypergraph
     * @generated
     */
    EClass getModularHypergraph();

    /**
     * Returns the meta object for the containment reference list '{@link org.oceandsl.hypergraph.ModularHypergraph#getModules <em>Modules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Modules</em>'.
     * @see org.oceandsl.hypergraph.ModularHypergraph#getModules()
     * @see #getModularHypergraph()
     * @generated
     */
    EReference getModularHypergraph_Modules();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.Module <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Module</em>'.
     * @see org.oceandsl.hypergraph.Module
     * @generated
     */
    EClass getModule();

    /**
     * Returns the meta object for the reference list '{@link org.oceandsl.hypergraph.Module#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.oceandsl.hypergraph.Module#getNodes()
     * @see #getModule()
     * @generated
     */
    EReference getModule_Nodes();

    /**
     * Returns the meta object for the containment reference '{@link org.oceandsl.hypergraph.Module#getDerivedFrom <em>Derived From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Derived From</em>'.
     * @see org.oceandsl.hypergraph.Module#getDerivedFrom()
     * @see #getModule()
     * @generated
     */
    EReference getModule_DerivedFrom();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.Module#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see org.oceandsl.hypergraph.Module#getKind()
     * @see #getModule()
     * @generated
     */
    EAttribute getModule_Kind();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see org.oceandsl.hypergraph.Node
     * @generated
     */
    EClass getNode();

    /**
     * Returns the meta object for the reference list '{@link org.oceandsl.hypergraph.Node#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see org.oceandsl.hypergraph.Node#getEdges()
     * @see #getNode()
     * @generated
     */
    EReference getNode_Edges();

    /**
     * Returns the meta object for the containment reference '{@link org.oceandsl.hypergraph.Node#getDerivedFrom <em>Derived From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Derived From</em>'.
     * @see org.oceandsl.hypergraph.Node#getDerivedFrom()
     * @see #getNode()
     * @generated
     */
    EReference getNode_DerivedFrom();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge</em>'.
     * @see org.oceandsl.hypergraph.Edge
     * @generated
     */
    EClass getEdge();

    /**
     * Returns the meta object for the containment reference '{@link org.oceandsl.hypergraph.Edge#getDerivedFrom <em>Derived From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Derived From</em>'.
     * @see org.oceandsl.hypergraph.Edge#getDerivedFrom()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_DerivedFrom();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Named Element</em>'.
     * @see org.oceandsl.hypergraph.NamedElement
     * @generated
     */
    EClass getNamedElement();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.NamedElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.oceandsl.hypergraph.NamedElement#getName()
     * @see #getNamedElement()
     * @generated
     */
    EAttribute getNamedElement_Name();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.NodeTrace <em>Node Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Trace</em>'.
     * @see org.oceandsl.hypergraph.NodeTrace
     * @generated
     */
    EClass getNodeTrace();

    /**
     * Returns the meta object for the reference '{@link org.oceandsl.hypergraph.NodeTrace#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Node</em>'.
     * @see org.oceandsl.hypergraph.NodeTrace#getNode()
     * @see #getNodeTrace()
     * @generated
     */
    EReference getNodeTrace_Node();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.EdgeTrace <em>Edge Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Trace</em>'.
     * @see org.oceandsl.hypergraph.EdgeTrace
     * @generated
     */
    EClass getEdgeTrace();

    /**
     * Returns the meta object for the reference '{@link org.oceandsl.hypergraph.EdgeTrace#getEdge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Edge</em>'.
     * @see org.oceandsl.hypergraph.EdgeTrace#getEdge()
     * @see #getEdgeTrace()
     * @generated
     */
    EReference getEdgeTrace_Edge();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.GenericTrace <em>Generic Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Generic Trace</em>'.
     * @see org.oceandsl.hypergraph.GenericTrace
     * @generated
     */
    EClass getGenericTrace();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.GenericTrace#getResourceId <em>Resource Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resource Id</em>'.
     * @see org.oceandsl.hypergraph.GenericTrace#getResourceId()
     * @see #getGenericTrace()
     * @generated
     */
    EAttribute getGenericTrace_ResourceId();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.NodeReference <em>Node Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Reference</em>'.
     * @see org.oceandsl.hypergraph.NodeReference
     * @generated
     */
    EClass getNodeReference();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.EdgeReference <em>Edge Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge Reference</em>'.
     * @see org.oceandsl.hypergraph.EdgeReference
     * @generated
     */
    EClass getEdgeReference();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.ModuleTrace <em>Module Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Module Trace</em>'.
     * @see org.oceandsl.hypergraph.ModuleTrace
     * @generated
     */
    EClass getModuleTrace();

    /**
     * Returns the meta object for the reference '{@link org.oceandsl.hypergraph.ModuleTrace#getModule <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Module</em>'.
     * @see org.oceandsl.hypergraph.ModuleTrace#getModule()
     * @see #getModuleTrace()
     * @generated
     */
    EReference getModuleTrace_Module();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.ModuleReference <em>Module Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Module Reference</em>'.
     * @see org.oceandsl.hypergraph.ModuleReference
     * @generated
     */
    EClass getModuleReference();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.TypeTrace <em>Type Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Type Trace</em>'.
     * @see org.oceandsl.hypergraph.TypeTrace
     * @generated
     */
    EClass getTypeTrace();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.TypeTrace#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.oceandsl.hypergraph.TypeTrace#getType()
     * @see #getTypeTrace()
     * @generated
     */
    EAttribute getTypeTrace_Type();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.FieldTrace <em>Field Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Trace</em>'.
     * @see org.oceandsl.hypergraph.FieldTrace
     * @generated
     */
    EClass getFieldTrace();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.FieldTrace#getField <em>Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field</em>'.
     * @see org.oceandsl.hypergraph.FieldTrace#getField()
     * @see #getFieldTrace()
     * @generated
     */
    EAttribute getFieldTrace_Field();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.MethodTrace <em>Method Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Trace</em>'.
     * @see org.oceandsl.hypergraph.MethodTrace
     * @generated
     */
    EClass getMethodTrace();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.MethodTrace#getMethod <em>Method</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Method</em>'.
     * @see org.oceandsl.hypergraph.MethodTrace#getMethod()
     * @see #getMethodTrace()
     * @generated
     */
    EAttribute getMethodTrace_Method();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.CallerCalleeTrace <em>Caller Callee Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Caller Callee Trace</em>'.
     * @see org.oceandsl.hypergraph.CallerCalleeTrace
     * @generated
     */
    EClass getCallerCalleeTrace();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCaller <em>Caller</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Caller</em>'.
     * @see org.oceandsl.hypergraph.CallerCalleeTrace#getCaller()
     * @see #getCallerCalleeTrace()
     * @generated
     */
    EAttribute getCallerCalleeTrace_Caller();

    /**
     * Returns the meta object for the attribute '{@link org.oceandsl.hypergraph.CallerCalleeTrace#getCallee <em>Callee</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Callee</em>'.
     * @see org.oceandsl.hypergraph.CallerCalleeTrace#getCallee()
     * @see #getCallerCalleeTrace()
     * @generated
     */
    EAttribute getCallerCalleeTrace_Callee();

    /**
     * Returns the meta object for class '{@link org.oceandsl.hypergraph.ModelElementTrace <em>Model Element Trace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model Element Trace</em>'.
     * @see org.oceandsl.hypergraph.ModelElementTrace
     * @generated
     */
    EClass getModelElementTrace();

    /**
     * Returns the meta object for the reference '{@link org.oceandsl.hypergraph.ModelElementTrace#getElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Element</em>'.
     * @see org.oceandsl.hypergraph.ModelElementTrace#getElement()
     * @see #getModelElementTrace()
     * @generated
     */
    EReference getModelElementTrace_Element();

    /**
     * Returns the meta object for enum '{@link org.oceandsl.hypergraph.EModuleKind <em>EModule Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>EModule Kind</em>'.
     * @see org.oceandsl.hypergraph.EModuleKind
     * @generated
     */
    EEnum getEModuleKind();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    HypergraphFactory getHypergraphFactory();

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
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.HypergraphImpl <em>Hypergraph</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.HypergraphImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getHypergraph()
         * @generated
         */
        EClass HYPERGRAPH = eINSTANCE.getHypergraph();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference HYPERGRAPH__NODES = eINSTANCE.getHypergraph_Nodes();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference HYPERGRAPH__EDGES = eINSTANCE.getHypergraph_Edges();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.ModularHypergraphImpl <em>Modular Hypergraph</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.ModularHypergraphImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModularHypergraph()
         * @generated
         */
        EClass MODULAR_HYPERGRAPH = eINSTANCE.getModularHypergraph();

        /**
         * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODULAR_HYPERGRAPH__MODULES = eINSTANCE.getModularHypergraph_Modules();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.ModuleImpl <em>Module</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.ModuleImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModule()
         * @generated
         */
        EClass MODULE = eINSTANCE.getModule();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODULE__NODES = eINSTANCE.getModule_Nodes();

        /**
         * The meta object literal for the '<em><b>Derived From</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODULE__DERIVED_FROM = eINSTANCE.getModule_DerivedFrom();

        /**
         * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MODULE__KIND = eINSTANCE.getModule_Kind();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.NodeImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNode()
         * @generated
         */
        EClass NODE = eINSTANCE.getNode();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE__EDGES = eINSTANCE.getNode_Edges();

        /**
         * The meta object literal for the '<em><b>Derived From</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE__DERIVED_FROM = eINSTANCE.getNode_DerivedFrom();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.EdgeImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Derived From</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__DERIVED_FROM = eINSTANCE.getEdge_DerivedFrom();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.NamedElementImpl <em>Named Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.NamedElementImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNamedElement()
         * @generated
         */
        EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.NodeTraceImpl <em>Node Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.NodeTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNodeTrace()
         * @generated
         */
        EClass NODE_TRACE = eINSTANCE.getNodeTrace();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NODE_TRACE__NODE = eINSTANCE.getNodeTrace_Node();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.EdgeTraceImpl <em>Edge Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.EdgeTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdgeTrace()
         * @generated
         */
        EClass EDGE_TRACE = eINSTANCE.getEdgeTrace();

        /**
         * The meta object literal for the '<em><b>Edge</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE_TRACE__EDGE = eINSTANCE.getEdgeTrace_Edge();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.GenericTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getGenericTrace()
         * @generated
         */
        EClass GENERIC_TRACE = eINSTANCE.getGenericTrace();

        /**
         * The meta object literal for the '<em><b>Resource Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GENERIC_TRACE__RESOURCE_ID = eINSTANCE.getGenericTrace_ResourceId();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.NodeReferenceImpl <em>Node Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.NodeReferenceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getNodeReference()
         * @generated
         */
        EClass NODE_REFERENCE = eINSTANCE.getNodeReference();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.EdgeReferenceImpl <em>Edge Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.EdgeReferenceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEdgeReference()
         * @generated
         */
        EClass EDGE_REFERENCE = eINSTANCE.getEdgeReference();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.ModuleTraceImpl <em>Module Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.ModuleTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModuleTrace()
         * @generated
         */
        EClass MODULE_TRACE = eINSTANCE.getModuleTrace();

        /**
         * The meta object literal for the '<em><b>Module</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODULE_TRACE__MODULE = eINSTANCE.getModuleTrace_Module();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.ModuleReferenceImpl <em>Module Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.ModuleReferenceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModuleReference()
         * @generated
         */
        EClass MODULE_REFERENCE = eINSTANCE.getModuleReference();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.TypeTraceImpl <em>Type Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.TypeTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getTypeTrace()
         * @generated
         */
        EClass TYPE_TRACE = eINSTANCE.getTypeTrace();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TYPE_TRACE__TYPE = eINSTANCE.getTypeTrace_Type();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.FieldTraceImpl <em>Field Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.FieldTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getFieldTrace()
         * @generated
         */
        EClass FIELD_TRACE = eINSTANCE.getFieldTrace();

        /**
         * The meta object literal for the '<em><b>Field</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FIELD_TRACE__FIELD = eINSTANCE.getFieldTrace_Field();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.MethodTraceImpl <em>Method Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.MethodTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getMethodTrace()
         * @generated
         */
        EClass METHOD_TRACE = eINSTANCE.getMethodTrace();

        /**
         * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METHOD_TRACE__METHOD = eINSTANCE.getMethodTrace_Method();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.CallerCalleeTraceImpl <em>Caller Callee Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.CallerCalleeTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getCallerCalleeTrace()
         * @generated
         */
        EClass CALLER_CALLEE_TRACE = eINSTANCE.getCallerCalleeTrace();

        /**
         * The meta object literal for the '<em><b>Caller</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CALLER_CALLEE_TRACE__CALLER = eINSTANCE.getCallerCalleeTrace_Caller();

        /**
         * The meta object literal for the '<em><b>Callee</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CALLER_CALLEE_TRACE__CALLEE = eINSTANCE.getCallerCalleeTrace_Callee();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.impl.ModelElementTraceImpl <em>Model Element Trace</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.impl.ModelElementTraceImpl
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getModelElementTrace()
         * @generated
         */
        EClass MODEL_ELEMENT_TRACE = eINSTANCE.getModelElementTrace();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODEL_ELEMENT_TRACE__ELEMENT = eINSTANCE.getModelElementTrace_Element();

        /**
         * The meta object literal for the '{@link org.oceandsl.hypergraph.EModuleKind <em>EModule Kind</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.oceandsl.hypergraph.EModuleKind
         * @see org.oceandsl.hypergraph.impl.HypergraphPackageImpl#getEModuleKind()
         * @generated
         */
        EEnum EMODULE_KIND = eINSTANCE.getEModuleKind();

    }

} //HypergraphPackage
