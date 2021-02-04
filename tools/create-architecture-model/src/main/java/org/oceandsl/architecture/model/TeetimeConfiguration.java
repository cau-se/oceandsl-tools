/**
 * 
 */
package org.oceandsl.architecture.model;

import java.io.IOException;

import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;

import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;

/**
 * @author reiner
 *
 */
public class TeetimeConfiguration extends Configuration {

	public TeetimeConfiguration(ArchitectureModelSettings parameterConfiguration) throws IOException {
				
		kieker.common.configuration.Configuration configuration = new kieker.common.configuration.Configuration();
		configuration.setProperty(LogsReaderCompositeStage.LOG_DIRECTORIES, 
				parameterConfiguration.getInputFile().getCanonicalPath());
		
		LogsReaderCompositeStage reader = new LogsReaderCompositeStage(configuration);
		
		RewriteBeforeAndAfterEventsStage processor = new RewriteBeforeAndAfterEventsStage(parameterConfiguration.getAddrlineExecutable(),
				parameterConfiguration.getModelExecutable());

		this.connectPorts(reader.getOutputPort(), processor.getInputPort());
	}
}
