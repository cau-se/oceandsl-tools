package org.oceandsl.tools.esm.stages;

import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.model.IDataflowEndpoint;

import lombok.Getter;

public class DataflowEndpoint implements IDataflowEndpoint {

    @Getter
    private final FortranModule module;

    @Getter
    private final FortranOperation operation;

    @Getter
    private EDirection direction;

    @Getter
    private final IDataflowEndpoint endpoint;

    public DataflowEndpoint(final FortranModule module, final FortranOperation operation,
            final IDataflowEndpoint endpoint, final EDirection direction) {
        this.module = module;
        this.operation = operation;
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

    public void merge(final EDirection newDirection) {
        switch (this.direction) {
        case NONE:
            this.direction = newDirection;
            break;
        case BOTH:
            break;
        case READ:
            switch (newDirection) {
            case BOTH:
            case WRITE:
                this.direction = EDirection.BOTH;
                break;
            case READ:
            case NONE:
                break;
            }
        case WRITE:
            switch (newDirection) {
            case BOTH:
            case READ:
                this.direction = EDirection.BOTH;
                break;
            case WRITE:
            case NONE:
                break;
            }
        }
    }
}
