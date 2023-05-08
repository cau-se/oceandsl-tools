package org.oceandsl.tools.restructuring.stages;

import java.nio.file.Path;

import teetime.framework.AbstractConsumerStage;

import org.oceandsl.tools.restructuring.stages.exec.OutputModelCreator;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.util.TransformationFactory;
import org.oceandsl.tools.restructuring.util.WriteModelUtils;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class RestructureModelSinkStage extends AbstractConsumerStage<RestructureStepFinder> {
    private final Path outputPath;

    public RestructureModelSinkStage(final Path outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    protected void execute(final RestructureStepFinder element) throws Exception {
        if (TransformationFactory.areSameModels(element.getGoal(), element.getOriginal())) {
            final OutputModelCreator output = new OutputModelCreator(element);
            output.createOutputModel();
            this.logger.info("Num of steps: {}", (element.getNumberOfSteps()));
            final String filename = String.format("%s-%s.xmi", element.getComponentMapper().getOriginalModelName(),
                    element.getComponentMapper().getGoalModelName());
            WriteModelUtils.writeModelRepository(this.outputPath, filename, output.getOutputModel());
        } else {
            this.logger.error("Faulty sequence");
        }
    }

}
