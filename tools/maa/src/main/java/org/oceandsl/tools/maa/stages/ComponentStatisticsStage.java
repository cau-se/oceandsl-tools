package org.oceandsl.tools.maa.stages;

import java.util.Collection;

import kieker.analysis.architecture.recovery.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.IntegerValueHandler;
import org.oceandsl.analysis.code.stages.data.LongValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

/**
 * Calculate the size of a component by number of operations, how many of its operations are called
 * and how many external operations are calley by the component.
 *
 * @author Reiner Jung
 * @since 1.4
 */
public class ComponentStatisticsStage extends AbstractTransformation<ModelRepository, Table> {

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final AssemblyModel assemblyModel = element.getModel(AssemblyModel.class);
        final ExecutionModel executionModel = element.getModel(ExecutionModel.class);
        final Table table = new Table("component-statistics", new StringValueHandler("component"),
                new IntegerValueHandler("operations"), new LongValueHandler("provided"),
                new LongValueHandler("required"));

        int i = 0;
        for (final AssemblyComponent component : assemblyModel.getComponents().values()) {
            table.addRow(String.format("%s::%d", component.getComponentType().getSignature(), i++),
                    component.getOperations().size(),
                    this.countAllProvidedOperations(component, component.getOperations().values(),
                            executionModel.getAggregatedInvocations().values()),
                    this.countAllRequiredOperations(component, component.getOperations().values(),
                            executionModel.getAggregatedInvocations().values()));
        }
        this.outputPort.send(table);
    }

    private Long countAllProvidedOperations(final AssemblyComponent component,
            final Collection<AssemblyOperation> operations, final Collection<AggregatedInvocation> invocations) {
        return operations.stream().filter(operation -> this.isCalledExternally(component, operation, invocations))
                .count();
    }

    private boolean isCalledExternally(final AssemblyComponent component, final AssemblyOperation operation,
            final Collection<AggregatedInvocation> invocations) {
        return invocations.stream()
                .anyMatch(invocation -> invocation.getTarget().getAssemblyOperation().equals(operation)
                        && invocation.getSource().getComponent().getAssemblyComponent() != component);
    }

    private Long countAllRequiredOperations(final AssemblyComponent component,
            final Collection<AssemblyOperation> operations, final Collection<AggregatedInvocation> invocations) {
        return operations.stream().filter(operation -> this.callsExternally(component, operation, invocations)).count();
    }

    private boolean callsExternally(final AssemblyComponent component, final AssemblyOperation operation,
            final Collection<AggregatedInvocation> invocations) {
        return invocations.stream()
                .anyMatch(invocation -> invocation.getSource().getAssemblyOperation().equals(operation)
                        && invocation.getTarget().getComponent().getAssemblyComponent() != component);
    }

}
