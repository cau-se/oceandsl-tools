package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public abstract class AbstractTransformationStep {
    protected AssemblyModel model;

    public AbstractTransformationStep(final AssemblyModel model) {
        this.model = model;
    }

    public AssemblyModel getModel() {
        return this.model;
    }

    public abstract void applyTransformation(AssemblyModel model);
}
