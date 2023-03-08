package org.oceandsl.tools.fxca.stages;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.Pair;

public class CreateCallTableStage extends AbstractTransformation<FortranProject, Table> {

    private static final String SOURCE_PATH = "callerfilename";
    private static final String SOURCE_MODULE = "callermodule";
    private static final String SOURCE_OPERATION = "callerfunction";
    private static final String TARGET_PATH = "calleefilename";
    private static final String TARGET_MODULE = "calleemodule";
    private static final String TARGET_OPERATION = "calleefunction";

    private record Operation(String path, String moduleName, String operation) {
        Operation(final String path, final String moduleName, final String operation) {
            this.path = path;
            this.moduleName = moduleName;
            this.operation = operation;
        }
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table callsTable = new Table("calls", new StringValueHandler(CreateCallTableStage.SOURCE_PATH),
                new StringValueHandler(CreateCallTableStage.SOURCE_MODULE),
                new StringValueHandler(CreateCallTableStage.SOURCE_OPERATION),
                new StringValueHandler(CreateCallTableStage.TARGET_PATH),
                new StringValueHandler(CreateCallTableStage.TARGET_MODULE),
                new StringValueHandler(CreateCallTableStage.TARGET_OPERATION));

        final Table notFound = new Table("not-found", new StringValueHandler(CreateCallTableStage.SOURCE_PATH),
                new StringValueHandler(CreateCallTableStage.SOURCE_MODULE),
                new StringValueHandler(CreateCallTableStage.SOURCE_OPERATION),
                new StringValueHandler(CreateCallTableStage.TARGET_OPERATION));

        project.getModules().values().forEach(module -> {
            module.getCalls().forEach(call -> {
                final Pair<FortranModule, String> callerPair = call.getFirst();
                final Pair<FortranModule, String> calleePair = call.getSecond();

                if (callerPair != null) {
                    final Operation caller = this.composeOperation(callerPair);
                    if (calleePair != null) {
                        final Operation callee = this.composeOperation(calleePair);
                        try {
                            callsTable.addRow(caller.path, caller.moduleName, caller.operation, callee.path,
                                    callee.moduleName, callee.operation);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Error writing calls to table: {}", e.getLocalizedMessage());
                        }
                    } else {
                        this.logger.warn("Caller {} {} {} has no callee ", caller.path, caller.moduleName,
                                caller.operation);
                    }
                } else {
                    if (calleePair != null) {
                        final Operation callee = this.composeOperation(calleePair);
                        this.logger.warn("No caller for callee {} {} {}", callee.path, callee.moduleName,
                                callee.operation);
                    } else {
                        this.logger.error("Empty call");
                    }
                }
            });
        });

        this.outputPort.send(callsTable);
    }

    private Operation composeOperation(final Pair<FortranModule, String> operationDescription) {
        final FortranModule module = operationDescription.getFirst();

        final String callerOperation = operationDescription.getSecond();

        if (module.isNamedModule()) {
            return new Operation(module.getDocument().getBaseURI(), module.getModuleName(), callerOperation);
        } else {
            return new Operation(module.getDocument().getBaseURI(), "<no-module>", callerOperation);
        }

    }

}
