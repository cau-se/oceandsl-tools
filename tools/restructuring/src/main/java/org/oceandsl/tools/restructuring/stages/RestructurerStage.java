package org.oceandsl.tools.restructuring.stages;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

import org.oceandsl.tools.restructuring.mapper.BasicComponentMapper;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class RestructurerStage extends AbstractConsumerStage<BasicComponentMapper> {

    // protected final InputPort<ComponentsMapper> compMapperPort =
    // this.createInputPort();

    protected final OutputPort<RestructureStepFinder> stepsOutputPort = this.createOutputPort();
    private final OutputPort<ResultRecord> numberOfStepsOutputPort = this.createOutputPort(ResultRecord.class);

    public OutputPort<RestructureStepFinder> getStepsOutputPort() {
        return this.stepsOutputPort;
    }

    public OutputPort<ResultRecord> getNumberOfStepsOutputPort() {
        return this.numberOfStepsOutputPort;
    }

    @Override
    protected void execute(final BasicComponentMapper mapper) throws Exception {
        final RestructureStepFinder restructureStepsFinder = new RestructureStepFinder(mapper);
        restructureStepsFinder.findTransformation();

        this.stepsOutputPort.send(restructureStepsFinder);
        this.numberOfStepsOutputPort.send(new ResultRecord(mapper.getOriginalModelName(), mapper.getGoalModelName(),
                restructureStepsFinder.getNumberOfSteps()));
    }
}
