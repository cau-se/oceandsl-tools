package org.oceandsl.tools.restructuring;

import java.io.IOException;

import org.oceandsl.analysis.architecture.stages.ModelRepositoryReaderStage;
import org.oceandsl.analysis.architecture.stages.ModelSource;
import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.restructuring.stages.AggregateModelEditDistanceStage;
import org.oceandsl.tools.restructuring.stages.RestructureModelSinkStage;
import org.oceandsl.tools.restructuring.stages.RestructurerStage;
import org.oceandsl.tools.restructuring.stages.TraceRestoratorStage;

import teetime.framework.Configuration;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

	private static final String MED_RESULT_FILE_NAME = "med-result-file.csv";

	public TeetimeConfiguration(final Settings parameterConfiguration) throws IOException {

		final ModelSource modelSource = new ModelSource(parameterConfiguration.getInputModelPaths());
		final ModelRepositoryReaderStage modelReader = new ModelRepositoryReaderStage();
		final TraceRestoratorStage traceRestorator = new TraceRestoratorStage(parameterConfiguration.getMappingStrat());
		final RestructurerStage restructurer = new RestructurerStage();
		final RestructureModelSinkStage modelSink = new RestructureModelSinkStage(
				parameterConfiguration.getOutputDirectory());
		AggregateModelEditDistanceStage aggregateStage = new AggregateModelEditDistanceStage();
		TableCSVSink medSinkStage = new TableCSVSink(parameterConfiguration.getOutputDirectory(), MED_RESULT_FILE_NAME,
				true);

		this.connectPorts(modelSource.getOutputPort(), modelReader.getInputPort());

		this.connectPorts(modelReader.getOutputPort(), traceRestorator.getInputPort());

		this.connectPorts(traceRestorator.getOutputPort(), restructurer.getInputPort());

		this.connectPorts(restructurer.getStepsOutputPort(), modelSink.getInputPort()); // PlaceHolder

		this.connectPorts(restructurer.getNumberOfStepsOutputPort(), aggregateStage.getInputPort());
		this.connectPorts(aggregateStage.getOutputPort(), medSinkStage.getInputPort());
	}
}