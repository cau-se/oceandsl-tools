/**
 *
 */
package org.oceandsl.architecture.model;

import java.util.function.Function;

import kieker.analysis.statistics.StatisticsDecoratorStage;
import kieker.analysis.statistics.StatisticsModel;
import kieker.analysis.statistics.Units;
import kieker.analysis.statistics.calculating.CountCalculator;
import kieker.analysis.util.ComposedKey;
import kieker.analysisteetime.model.analysismodel.deployment.DeployedOperation;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;

/**
 * @author reiner
 *
 */
public class CountUniqueCalls extends StatisticsDecoratorStage<OperationCall> {

    public CountUniqueCalls(final StatisticsModel statisticsModel, final ExecutionModel executionModel) {
        super(statisticsModel, Units.RESPONSE_TIME, new CountCalculator<>(),
                CountUniqueCalls.createForAggregatedInvocation(executionModel));
    }

    public static final Function<OperationCall, Object> createForAggregatedInvocation(
            final ExecutionModel executionModel) {
        return operationCall -> {
            final ComposedKey<DeployedOperation, DeployedOperation> key = ComposedKey
                    .of(operationCall.getSourceOperation(), operationCall.getTargetOperation());
            return executionModel.getAggregatedInvocations().get(key);
        };
    }

}
