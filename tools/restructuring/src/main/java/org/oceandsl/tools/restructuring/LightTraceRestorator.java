package org.oceandsl.tools.restructuring;

import kieker.model.analysismodel.assembly.AssemblyModel;

import org.oceandsl.tools.restructuring.mapper.AbstractComponentMapper;
import org.oceandsl.tools.restructuring.mapper.KuhnMatcherMapper;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class LightTraceRestorator {

    private final AssemblyModel original;
    private final AssemblyModel goal;

    public LightTraceRestorator(final AssemblyModel original, final AssemblyModel goal) {
        this.original = original;
        this.goal = goal;
    }

    public int getNumSteps() {
        // TODO find better names
        final AbstractComponentMapper mapper = new KuhnMatcherMapper(this.original, this.goal, "original", "goal");

        final RestructureStepFinder stepfinder = new RestructureStepFinder(mapper);
        stepfinder.findTransformation();

        return stepfinder.getNumberOfSteps();
    }

}
