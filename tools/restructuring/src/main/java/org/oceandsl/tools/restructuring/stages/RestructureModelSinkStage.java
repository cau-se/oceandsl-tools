package org.oceandsl.tools.restructuring.stages;

import java.nio.file.Path;

import org.oceandsl.tools.restructuring.stages.exec.OutputModelCreator;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.util.TransformationFactory;
import org.oceandsl.tools.restructuring.util.WriteModelUtils;

import teetime.framework.AbstractConsumerStage;

public class RestructureModelSinkStage extends AbstractConsumerStage<RestructureStepFinder> {
	private final Path outputPath;

	public RestructureModelSinkStage(final Path outputPath) {
		this.outputPath = outputPath;
	}

	@Override
	protected void execute(RestructureStepFinder element) throws Exception {
		if (TransformationFactory.areSameModels(element.getGoal(), element.getOrig())) {
			OutputModelCreator output = new OutputModelCreator(element);
			output.createOutputModel();
			logger.info("Num of steps: {}", (element.getNumSteps()));
			WriteModelUtils.writeModelRepository(outputPath, output.getOutputModel());
		} else {
			logger.error("Faulty sequence");
		}
	}

}
