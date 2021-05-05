/**
 *
 */
package org.oceandsl.log.rewriter;

import java.io.IOException;

import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;

import kieker.analysis.sink.DataSinkStage;
import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;

/**
 * @author reiner
 *
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final LogRewriterSettings parameterConfiguration) throws IOException {

        final kieker.common.configuration.Configuration configuration = new kieker.common.configuration.Configuration();
        configuration.setProperty(LogsReaderCompositeStage.LOG_DIRECTORIES,
                parameterConfiguration.getInputFile().getCanonicalPath());
        configuration.setProperty("kieker.monitoring.name", "KIEKER");
        configuration.setProperty("kieker.monitoring.enabled", "true");
        configuration.setProperty("kieker.monitoring.initialExperimentId", "transcoded");
        configuration.setProperty("kieker.monitoring.metadata", "true");
        configuration.setProperty("kieker.monitoring.setLoggingTimestamp", "true");
        configuration.setProperty("kieker.monitoring.useShutdownHook", "true");
        configuration.setProperty("kieker.monitoring.jmx", "false");

        configuration.setProperty("kieker.monitoring.timer",
                kieker.monitoring.timer.SystemNanoTimer.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.timer.SystemMilliTimer.unit", "0");
        configuration.setProperty("kieker.monitoring.timer.SystemNanoTimer.unit", "0");
        configuration.setProperty("kieker.monitoring.writer",
                kieker.monitoring.writer.filesystem.FileWriter.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.core.controller.WriterController.RecordQueueFQN",
                org.jctools.queues.MpscArrayQueue.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.core.controller.WriterController.RecordQueueSize", "10000");
        configuration.setProperty("kieker.monitoring.core.controller.WriterController.RecordQueueInsertBehavior", "1");
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.customStoragePath",
                parameterConfiguration.getOutputFile().getCanonicalPath());
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.charsetName", "UTF-8");
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.maxEntriesInFile", "25000");
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.maxLogSize", "-1");
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.maxLogFiles", "-1");
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.mapFileHandler",
                kieker.monitoring.writer.filesystem.TextMapFileHandler.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.writer.filesystem.TextMapFileHandler.flush", "true");
        configuration.setProperty("kieker.monitoring.writer.filesystem.TextMapFileHandler.compression",
                kieker.monitoring.writer.compression.NoneCompressionFilter.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.logFilePoolHandler",
                kieker.monitoring.writer.filesystem.RotatingLogFilePoolHandler.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.logStreamHandler",
                kieker.monitoring.writer.filesystem.TextLogStreamHandler.class.getCanonicalName());
        configuration.setProperty("kieker.monitoring.writer.filesystem.FileWriter.flush", "true");
        configuration.setProperty("kieker.monitoring.writer.filesystem.BinaryFileWriter.bufferSize", "8192");
        configuration.setProperty("kieker.monitoring.writer.filesystem.BinaryFileWriter.compression",
                kieker.monitoring.writer.compression.NoneCompressionFilter.class.getCanonicalName());

        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(configuration);

        final RewriteBeforeAndAfterEventsStage processor = new RewriteBeforeAndAfterEventsStage(
                parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable(), false);

        final DataSinkStage writer = new DataSinkStage(configuration);

        this.connectPorts(reader.getOutputPort(), processor.getInputPort());
        this.connectPorts(processor.getOutputPort(), writer.getInputPort());
    }
}
