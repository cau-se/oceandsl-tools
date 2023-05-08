package org.oceandsl.tools.restructuring;

import java.io.IOException;

import teetime.framework.Configuration;

import org.oceandsl.analysis.architecture.stages.ModelRepositoryReaderStage;
import org.oceandsl.analysis.architecture.stages.ModelSource;
import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.restructuring.stages.AggregateModelEditDistanceStage;
import org.oceandsl.tools.restructuring.stages.RestructureModelSinkStage;
import org.oceandsl.tools.restructuring.stages.RestructurerStage;
import org.oceandsl.tools.restructuring.stages.TraceRestoratorStage;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings parameterConfiguration) throws IOException {

        final ModelSource modelSource = new ModelSource(parameterConfiguration.getInputModelPaths());
        final ModelRepositoryReaderStage modelReader = new ModelRepositoryReaderStage();
        final TraceRestoratorStage traceRestorator = new TraceRestoratorStage(parameterConfiguration.getMappingStrat());
        final RestructurerStage restructurer = new RestructurerStage();
        final RestructureModelSinkStage modelSink = new RestructureModelSinkStage(
                parameterConfiguration.getOutputDirectory());
        final AggregateModelEditDistanceStage aggregateStage = new AggregateModelEditDistanceStage();
        final TableCSVSink medSinkStage = new TableCSVSink(parameterConfiguration.getOutputDirectory(), true);

        this.connectPorts(modelSource.getOutputPort(), modelReader.getInputPort());

        this.connectPorts(modelReader.getOutputPort(), traceRestorator.getInputPort());

        this.connectPorts(traceRestorator.getOutputPort(), restructurer.getInputPort());

        this.connectPorts(restructurer.getStepsOutputPort(), modelSink.getInputPort()); // PlaceHolder

        this.connectPorts(restructurer.getNumberOfStepsOutputPort(), aggregateStage.getInputPort());
        this.connectPorts(aggregateStage.getOutputPort(), medSinkStage.getInputPort());
    }
}