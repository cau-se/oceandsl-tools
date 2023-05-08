package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.impl.AssemblyFactoryImpl;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class CreateTransformation extends AtomicTransformation {

    private String componentName;
    AssemblyFactoryImpl fac = new AssemblyFactoryImpl();

    public CreateTransformation(final AssemblyModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    public void setComponentName(final String componentName) {
        this.componentName = componentName;
    }

    public String getComponentName() {
        return this.componentName;
    }

    @Override
    public void applyTransformation(final AssemblyModel model) {

        // Entry<String, AssemblyComponent> entry = Map.entry(this.componentName,
        // fac.createAssemblyComponent());
        model.getComponents().put(this.componentName, this.fac.createAssemblyComponent());
        this.model = model;
        // this.model.getComponents().add(entry);

    }

}
