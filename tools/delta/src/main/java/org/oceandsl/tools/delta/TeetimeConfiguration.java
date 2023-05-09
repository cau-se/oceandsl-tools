package org.oceandsl.tools.delta;

import java.io.IOException;

import teetime.framework.Configuration;

import org.oceandsl.analysis.generic.stages.TableCSVSink;
import org.oceandsl.tools.delta.stages.CompileRestructureTableStage;
import org.oceandsl.tools.delta.stages.ResturctureModelReader;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings settings) throws IOException {

        final ResturctureModelReader reader = new ResturctureModelReader(settings.getInputPath());

        final CompileRestructureTableStage processor = new CompileRestructureTableStage(
                settings.getOutputPath().getFileName().toString());

        final TableCSVSink sink = new TableCSVSink(settings.getOutputPath().getParent(), true);

        this.connectPorts(reader.getOutputPort(), processor.getInputPort());
        this.connectPorts(processor.getOutputPort(), sink.getInputPort());
    }
}