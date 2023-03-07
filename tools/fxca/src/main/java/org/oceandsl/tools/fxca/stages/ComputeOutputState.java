package org.oceandsl.tools.fxca.stages;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.Pair;

public class ComputeOutputState extends AbstractConsumerStage<FortranProject> {

    private static final String SOURCE_PATH = "callerfilename";
    private static final String SOURCE_MODULE = "callermodule";
    private static final String SOURCE_OPERATION = "callerfunction";
    private static final String TARGET_PATH = "calleefilename";
    private static final String TARGET_MODULE = "calleemodule";
    private static final String TARGET_OPERATION = "calleefunction";

    private final OutputPort<Table> callsOutputPort = this.createOutputPort(Table.class);
    private final OutputPort<Table> callTableOutputPort = this.createOutputPort(Table.class);
    private final OutputPort<Table> notFoundOutputPort = this.createOutputPort(Table.class);

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table callsTable = new Table("calls", new StringValueHandler(SOURCE_PATH),
                new StringValueHandler(SOURCE_MODULE), new StringValueHandler(SOURCE_OPERATION),
                new StringValueHandler(TARGET_PATH), new StringValueHandler(TARGET_MODULE),
                new StringValueHandler(TARGET_OPERATION));
        project.getModules().values().forEach(module -> {
            module.getCalls().forEach(call -> {
                final Pair<FortranModule, String> caller = call.getFirst();
                final Pair<FortranModule, String> callee = call.getSecond();

                if (caller != null) {
                    final FortranModule callerModule = caller.getFirst();

                    final String callerPath;
                    final String callerModuleName;
                    final String callerOperation = caller.getSecond();

                    if (callerModule.isNamedModule()) {
                        callerPath = callerModule.getDocument().getBaseURI();
                        callerModuleName = callerModule.getModuleName();
                    } else {
                        callerPath = callerModule.getModuleName();
                        callerModuleName = "<no-module>";
                    }

                    if (callee != null) {
                        final FortranModule calleeModule = callee.getFirst();

                        final String calleePath;
                        final String calleeModuleName;
                        final String calleeOperation = callee.getSecond();

                        if (calleeModule.isNamedModule()) {
                            calleePath = calleeModule.getDocument().getBaseURI();
                            calleeModuleName = calleeModule.getModuleName();
                        } else {
                            calleePath = calleeModule.getModuleName();
                            calleeModuleName = "<no-module>";
                        }
                        try {
                            callsTable.addRow(callerPath, callerModuleName, callerOperation, calleePath,
                                    calleeModuleName, calleeOperation);
                        } catch (final ValueConversionErrorException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        this.logger.debug("Caller {} {} {} has no callee ", callerPath, callerModuleName,
                                callerOperation);
                    }
                } else {
                    if (callee != null) {
                        final FortranModule calleeModule = callee.getFirst();

                        final String calleePath;
                        final String calleeModuleName;
                        final String calleeOperation = callee.getSecond();

                        if (calleeModule.isNamedModule()) {
                            calleePath = calleeModule.getDocument().getBaseURI();
                            calleeModuleName = calleeModule.getModuleName();
                        } else {
                            calleePath = calleeModule.getModuleName();
                            calleeModuleName = "<no-module>";
                        }

                        this.logger.debug("No caller for callee {} {} {}", calleePath, calleeModuleName,
                                calleeOperation);
                    } else {
                        this.logger.debug("Empty call");
                    }
                }
            });
        });

        this.callsOutputPort.send(callsTable);
    }

}
