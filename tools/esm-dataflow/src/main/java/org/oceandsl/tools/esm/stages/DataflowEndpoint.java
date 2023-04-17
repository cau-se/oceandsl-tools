package org.oceandsl.tools.esm.stages;

import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;

import lombok.Getter;

public class DataflowEndpoint implements IDataflowEndpoint {

    @Getter
    private final FortranModule module;

    @Getter
    private final FortranOperation operation;

    @Getter
    private EDirection direction;

    public DataflowEndpoint(final FortranModule module, final FortranOperation operation, final EDirection direction) {
        this.module = module;
        this.operation = operation;
        this.direction = direction;
    }

    @Override
    public String toString() {
        final String moduleName = this.module != null ? this.module.getFileName() : "<>";
        final String operationName = this.operation != null ? this.operation.getName() : "<>";
        return moduleName + "::" + operationName + " ~ " + this.direction.name();
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
