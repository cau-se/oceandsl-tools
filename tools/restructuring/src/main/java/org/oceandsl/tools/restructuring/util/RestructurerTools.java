package org.oceandsl.tools.restructuring.util;

import java.util.Map.Entry;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class RestructurerTools {

    public static AssemblyModel cloneModel(final AssemblyModel model) {
        final AssemblyFactory factory = AssemblyFactory.eINSTANCE;
        final AssemblyModel result = factory.createAssemblyModel();

        for (final Entry<String, AssemblyComponent> e : model.getComponents().entrySet()) {
            final AssemblyComponent comp = factory.createAssemblyComponent();
            result.getComponents().put(e.getKey(), comp);
            for (final Entry<String, AssemblyOperation> op : e.getValue().getOperations().entrySet()) {
                final AssemblyOperation o = factory.createAssemblyOperation();
                result.getComponents().get(e.getKey()).getOperations().put(op.getKey(), o);
            }
        }
        if (!TransformationFactory.areSameModels(model, result)) {
            throw new Error("Models were not clonned succesfully!");
        }

        return result;
    }

    public static AssemblyModel alterComponentNames(final AssemblyModel model) {
        final String prefix = "_";
        final AssemblyFactory factory = AssemblyFactory.eINSTANCE;
        final AssemblyModel result = factory.createAssemblyModel();

        for (final Entry<String, AssemblyComponent> e : model.getComponents().entrySet()) {
            final AssemblyComponent comp = factory.createAssemblyComponent();
            result.getComponents().put(prefix + e.getKey(), comp);
            for (final Entry<String, AssemblyOperation> op : e.getValue().getOperations().entrySet()) {
                final AssemblyOperation o = factory.createAssemblyOperation();
                result.getComponents().get("_" + e.getKey()).getOperations().put(op.getKey(), o);
            }
        }
        if (!TransformationFactory.areSameModels(model, result)) {
            throw new Error("Models were not clonned succesfully!");
        }

        return result;
    }
}
