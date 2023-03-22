package org.oceandsl.tools.restructuring;
import java.io.IOException;

import teetime.framework.Configuration;

import org.oceandsl.analysis.architecture.stages.ModelRepositoryReaderStage;

import org.oceandsl.analysis.architecture.stages.ModelSource;
import org.oceandsl.tools.restructuring.stages.Restructurer;
import org.oceandsl.tools.restructuring.stages.SinkStage;
import org.oceandsl.tools.restructuring.stages.exec.TraceRestorator;


/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings parameterConfiguration) throws IOException {

        final ModelSource modelSource = new ModelSource(parameterConfiguration.getInputModelPaths());
        final ModelRepositoryReaderStage modelReader = new ModelRepositoryReaderStage();
        final TraceRestorator traceRestorator = new TraceRestorator(parameterConfiguration.getMappingStrat());
        final Restructurer restructurer = new Restructurer();
        final SinkStage modelSink = new SinkStage(parameterConfiguration.getOutputDirectory());
        
        
        
        this.connectPorts(modelSource.getOutputPort(), modelReader.getInputPort());
        
        this.connectPorts(modelReader.getOutputPort(), traceRestorator.getInputPort());

        this.connectPorts(traceRestorator.getOutputPort(), restructurer.getInputPort());
        
        this.connectPorts(restructurer.getStepsOutputPort(), modelSink.getInputPort()); // PlaceHolder
 
    }
}