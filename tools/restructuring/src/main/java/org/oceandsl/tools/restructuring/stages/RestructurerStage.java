package org.oceandsl.tools.restructuring.stages;

import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

public class RestructurerStage extends AbstractConsumerStage<AbstractMapper> {

	// protected final InputPort<ComponentsMapper> compMapperPort =
	// this.createInputPort();

	protected final OutputPort<RestructureStepFinder> stepsOutputPort = this.createOutputPort();
	private OutputPort<ResultRecord> numberOfStepsOutputPort = this.createOutputPort(ResultRecord.class);

	public OutputPort<RestructureStepFinder> getStepsOutputPort() {
		return this.stepsOutputPort;
	}

	public OutputPort<ResultRecord> getNumberOfStepsOutputPort() {
		return this.numberOfStepsOutputPort;
	}

	@Override
	protected void execute(AbstractMapper mapper) throws Exception {
		RestructureStepFinder restructureStepsFinder = new RestructureStepFinder(mapper);
		restructureStepsFinder.findTransformation();
		this.stepsOutputPort.send(restructureStepsFinder);
		this.numberOfStepsOutputPort.send(new ResultRecord(mapper.getOriginalModelName(), mapper.getGoalModelName(),
				restructureStepsFinder.getNumSteps()));
	}
}
