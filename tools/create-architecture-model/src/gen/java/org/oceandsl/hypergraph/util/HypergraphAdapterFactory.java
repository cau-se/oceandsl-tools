/**
 */
package org.oceandsl.hypergraph.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.oceandsl.hypergraph.CallerCalleeTrace;
import org.oceandsl.hypergraph.Edge;
import org.oceandsl.hypergraph.EdgeReference;
import org.oceandsl.hypergraph.EdgeTrace;
import org.oceandsl.hypergraph.FieldTrace;
import org.oceandsl.hypergraph.GenericTrace;
import org.oceandsl.hypergraph.Hypergraph;
import org.oceandsl.hypergraph.HypergraphPackage;
import org.oceandsl.hypergraph.MethodTrace;
import org.oceandsl.hypergraph.ModelElementTrace;
import org.oceandsl.hypergraph.ModularHypergraph;
import org.oceandsl.hypergraph.ModuleReference;
import org.oceandsl.hypergraph.ModuleTrace;
import org.oceandsl.hypergraph.NamedElement;
import org.oceandsl.hypergraph.Node;
import org.oceandsl.hypergraph.NodeReference;
import org.oceandsl.hypergraph.NodeTrace;
import org.oceandsl.hypergraph.TypeTrace;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.oceandsl.hypergraph.HypergraphPackage
 * @generated
 */
public class HypergraphAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static HypergraphPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HypergraphAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = HypergraphPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected HypergraphSwitch<Adapter> modelSwitch =
        new HypergraphSwitch<Adapter>() {
            @Override
            public Adapter caseHypergraph(Hypergraph object) {
                return createHypergraphAdapter();
            }
            @Override
            public Adapter caseModularHypergraph(ModularHypergraph object) {
                return createModularHypergraphAdapter();
            }
            @Override
            public Adapter caseModule(org.oceandsl.hypergraph.Module object) {
                return createModuleAdapter();
            }
            @Override
            public Adapter caseNode(Node object) {
                return createNodeAdapter();
            }
            @Override
            public Adapter caseEdge(Edge object) {
                return createEdgeAdapter();
            }
            @Override
            public Adapter caseNamedElement(NamedElement object) {
                return createNamedElementAdapter();
            }
            @Override
            public Adapter caseNodeTrace(NodeTrace object) {
                return createNodeTraceAdapter();
            }
            @Override
            public Adapter caseEdgeTrace(EdgeTrace object) {
                return createEdgeTraceAdapter();
            }
            @Override
            public Adapter caseGenericTrace(GenericTrace object) {
                return createGenericTraceAdapter();
            }
            @Override
            public Adapter caseNodeReference(NodeReference object) {
                return createNodeReferenceAdapter();
            }
            @Override
            public Adapter caseEdgeReference(EdgeReference object) {
                return createEdgeReferenceAdapter();
            }
            @Override
            public Adapter caseModuleTrace(ModuleTrace object) {
                return createModuleTraceAdapter();
            }
            @Override
            public Adapter caseModuleReference(ModuleReference object) {
                return createModuleReferenceAdapter();
            }
            @Override
            public Adapter caseTypeTrace(TypeTrace object) {
                return createTypeTraceAdapter();
            }
            @Override
            public Adapter caseFieldTrace(FieldTrace object) {
                return createFieldTraceAdapter();
            }
            @Override
            public Adapter caseMethodTrace(MethodTrace object) {
                return createMethodTraceAdapter();
            }
            @Override
            public Adapter caseCallerCalleeTrace(CallerCalleeTrace object) {
                return createCallerCalleeTraceAdapter();
            }
            @Override
            public Adapter caseModelElementTrace(ModelElementTrace object) {
                return createModelElementTraceAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.Hypergraph <em>Hypergraph</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.Hypergraph
     * @generated
     */
    public Adapter createHypergraphAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.ModularHypergraph <em>Modular Hypergraph</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.ModularHypergraph
     * @generated
     */
    public Adapter createModularHypergraphAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.Module <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.Module
     * @generated
     */
    public Adapter createModuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.Node
     * @generated
     */
    public Adapter createNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.Edge
     * @generated
     */
    public Adapter createEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.NamedElement
     * @generated
     */
    public Adapter createNamedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.NodeTrace <em>Node Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.NodeTrace
     * @generated
     */
    public Adapter createNodeTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.EdgeTrace <em>Edge Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.EdgeTrace
     * @generated
     */
    public Adapter createEdgeTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.GenericTrace <em>Generic Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.GenericTrace
     * @generated
     */
    public Adapter createGenericTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.NodeReference <em>Node Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.NodeReference
     * @generated
     */
    public Adapter createNodeReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.EdgeReference <em>Edge Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.EdgeReference
     * @generated
     */
    public Adapter createEdgeReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.ModuleTrace <em>Module Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.ModuleTrace
     * @generated
     */
    public Adapter createModuleTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.ModuleReference <em>Module Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.ModuleReference
     * @generated
     */
    public Adapter createModuleReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.TypeTrace <em>Type Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.TypeTrace
     * @generated
     */
    public Adapter createTypeTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.FieldTrace <em>Field Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.FieldTrace
     * @generated
     */
    public Adapter createFieldTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.MethodTrace <em>Method Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.MethodTrace
     * @generated
     */
    public Adapter createMethodTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.CallerCalleeTrace <em>Caller Callee Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.CallerCalleeTrace
     * @generated
     */
    public Adapter createCallerCalleeTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.oceandsl.hypergraph.ModelElementTrace <em>Model Element Trace</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.oceandsl.hypergraph.ModelElementTrace
     * @generated
     */
    public Adapter createModelElementTraceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //HypergraphAdapterFactory
