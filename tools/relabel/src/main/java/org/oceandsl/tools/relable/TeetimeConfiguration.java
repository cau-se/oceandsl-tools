package org.oceandsl.tools.relable;

import org.oceandsl.analysis.stages.model.ModelRepositoryProducerStage;

import teetime.framework.Configuration;

/**
 * Pipe and Filter configuration relabling tool.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings settings) {
        final ModelRepositoryProducerStage readerStage = new ModelRepositoryProducerStage(settings.getInputDirectory());
        final ReplaceSourceLabelStage replaceSourceLabelStage = new ReplaceSourceLabelStage(settings.getReplacements());
        final ModelRepositoryWriterStage writerStage = new ModelRepositoryWriterStage(settings.getOutputDirectory());

        this.connectPorts(readerStage.getOutputPort(), replaceSourceLabelStage.getInputPort());
        this.connectPorts(replaceSourceLabelStage.getOutputPort(), writerStage.getInputPort());
    }
}
