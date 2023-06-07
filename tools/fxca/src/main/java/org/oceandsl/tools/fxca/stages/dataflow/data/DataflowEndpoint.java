package org.oceandsl.tools.fxca.stages.dataflow.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import kieker.model.analysismodel.execution.EDirection;

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.model.IDataflowEndpoint;

public class DataflowEndpoint implements IDataflowEndpoint {

    @Getter
    private final String name;

    @Getter
    private final FortranModule module;

    @Getter
    private final FortranOperation operation;

    @Getter
    @Setter
    private EDirection direction;

    @Getter
    private final List<IDataflowEndpoint> sources = new ArrayList<>();

    @Getter
    private final IDataflowEndpoint endpoint;

    public DataflowEndpoint(final FortranModule module, final FortranOperation operation,
            final IDataflowEndpoint endpoint, final EDirection direction) {
        this.module = module;
        this.operation = operation;
        this.name = this.operation.getName();
        this.endpoint = endpoint;
        this.direction = direction;
    }

    @Override
    public String toString() {
        final String moduleName = this.module != null ? this.module.getFileName() : "<>";
        final String operationName = this.operation != null ? this.operation.getName() : "<>";
        final String endpointName = this.endpoint != null ? this.endpointName() : "<>";
        return moduleName + "::" + operationName + " ~ " + endpointName + ":" + this.direction.name();
    }

    private String endpointName() {
        if (this.endpoint instanceof FortranOperation) {
            return "<return-value>";
        } else if (this.endpoint instanceof FortranParameter) {
            return ((FortranParameter) this.endpoint).getName();
        } else if (this.endpoint instanceof FortranVariable) {
            return ((FortranVariable) this.endpoint).getName();
        } else {
            return "<" + this.endpoint.getClass().getSimpleName() + ">";
        }
    }

}
