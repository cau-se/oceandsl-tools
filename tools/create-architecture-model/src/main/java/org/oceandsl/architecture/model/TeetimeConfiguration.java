/**
 *
 */
package org.oceandsl.architecture.model;

import java.io.IOException;

import org.oceandsl.analysis.RewriteBeforeAndAfterEventsStage;

import kieker.analysis.model.DeploymentModelAssemblerStage;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyFactory;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyModel;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentFactory;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentModel;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.IFlowRecord;
import kieker.tools.source.LogsReaderCompositeStage;
import teetime.framework.Configuration;
import teetime.stage.InstanceOfFilter;

/**
 * @author reiner
 *
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final ArchitectureModelSettings parameterConfiguration) throws IOException {

        final kieker.common.configuration.Configuration configuration = new kieker.common.configuration.Configuration();
        configuration.setProperty(LogsReaderCompositeStage.LOG_DIRECTORIES,
                parameterConfiguration.getInputFile().getCanonicalPath());

        final LogsReaderCompositeStage reader = new LogsReaderCompositeStage(configuration);

        final RewriteBeforeAndAfterEventsStage processor = new RewriteBeforeAndAfterEventsStage(
                parameterConfiguration.getAddrlineExecutable(), parameterConfiguration.getModelExecutable());
        final InstanceOfFilter<IMonitoringRecord, IFlowRecord> instanceOfFilter = new InstanceOfFilter<>(
                IFlowRecord.class);
        // final CreateCallsStage callsStage = new CreateCallsStage();
        // final CountUniqueCalls countStage = new CountUniqueCalls();
        final CountEvents<IFlowRecord> counter = new CountEvents<>(1000000);
        final AssemblyModel assemblyModel = AssemblyFactory.eINSTANCE.createAssemblyModel();
        final DeploymentModel deploymentModel = DeploymentFactory.eINSTANCE.createDeploymentModel();
        final DeploymentModelAssemblerStage deploymentModelAssemblerStage = new DeploymentModelAssemblerStage(
                assemblyModel, deploymentModel);

        this.connectPorts(reader.getOutputPort(), processor.getInputPort());
        this.connectPorts(processor.getOutputPort(), instanceOfFilter.getInputPort());
        this.connectPorts(instanceOfFilter.getMatchedOutputPort(), counter.getInputPort());
        this.connectPorts(counter.getOutputPort(), deploymentModelAssemblerStage.getInputPort());

    }
}
