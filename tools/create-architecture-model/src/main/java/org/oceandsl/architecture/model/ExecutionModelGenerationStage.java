package org.oceandsl.architecture.model;

import kieker.analysis.util.ComposedKey;
import kieker.analysisteetime.model.analysismodel.deployment.DeployedOperation;
import kieker.analysisteetime.model.analysismodel.execution.AggregatedInvocation;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionFactory;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

public class ExecutionModelGenerationStage extends AbstractConsumerStage<OperationCall> {

    private final ExecutionFactory factory = ExecutionFactory.eINSTANCE;

    private final ExecutionModel executionModel;

    private final OutputPort<OperationCall> outputPort = this.createOutputPort(OperationCall.class);

    public ExecutionModelGenerationStage(final ExecutionModel executionModel) {
        this.executionModel = executionModel;
    }

    @Override
    protected void execute(final OperationCall call) throws Exception {
        this.addExecution(call.getSourceOperation(), call.getTargetOperation());
        this.outputPort.send(call);
    }

    protected void addExecution(final DeployedOperation source, final DeployedOperation target) {
        final ComposedKey<DeployedOperation, DeployedOperation> key = ComposedKey.of(source, target);
        if (!this.executionModel.getAggregatedInvocations().containsKey(key)) {
            final AggregatedInvocation invocation = this.factory.createAggregatedInvocation();
            invocation.setSource(source);
            invocation.setTarget(target);

            this.executionModel.getAggregatedInvocations().put(key, invocation);
        }
    }

    public OutputPort<OperationCall> getOutputPort() {
        return this.outputPort;
    }

}
