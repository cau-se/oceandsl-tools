package org.oceandsl.tools.fxca.stages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;

/**
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 */
public class CallTableStage extends AbstractTransformation<FortranProject, Table> {

    @Override
    protected void execute(final FortranProject element) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * Exports the call cable into CSV files.
     *
     * @param PrintStream
     *            for table
     * @param PrintStream
     *            for list of not-found entries
     * @param globalModules
     *            list of modules that are available to all modules / files without explicit
     *            reference
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void exportCallTable(final PrintStream tableOut, final PrintStream notFoundOut,
            final List<FortranModule> globalModules) throws ParserConfigurationException, SAXException, IOException {
        tableOut.println("callerfilename,callermodule,callerfunction,calleefilename,calleemodule,calleefunction");
        this.logger.debug("Calls to operations that could not be found:");
        for (final FortranModule module : this) {
            for (final Pair<String, String> call : module.operationCalls()) {
                final String callerFunctionName = call.first;
                final String callerFileName = module.getXmlFilePath().toAbsolutePath().getFileName().toString();
                final String calleeFunctionName = call.second;
                final FortranModule calleeXML = this.resolveCallee(module, calleeFunctionName, globalModules);
                final String calleeFileName = (calleeXML == null) ? "<unknown>"
                        : calleeXML.getXmlFilePath().toAbsolutePath().getFileName().toString();
                final String calleeModuleName = (calleeXML == null) ? "<unknown>" : calleeXML.getModuleName();
                tableOut.println(callerFileName + ", " + module.getModuleName() + ", " + callerFunctionName + ", "
                        + calleeFileName + ", " + calleeModuleName + ", " + calleeFunctionName);
                if (calleeXML == null) {
                    notFoundOut.println(callerFileName + ":" + callerFunctionName + " --> " + calleeFunctionName);
                }
            }
        }
    }

    private FortranModule resolveCallee(final FortranModule xml, final String calleeFunctionName,
            final List<FortranModule> globalModules) {
        this.logger.debug("resolve Callee: {} from {}", calleeFunctionName, xml.getXmlFilePath());
        /*
         * if ("MDS_WRITE_FIELD".equals(calleeFunctionName)) { System.exit(0); }
         */
        return ListTools.getUniqueElementIfNonEmpty(
                this.resolveCalleeModuleCandidates(xml, calleeFunctionName, globalModules), null);
    }

    private List<FortranModule> resolveCalleeModuleCandidates(final FortranModule callerModule,
            final String calleeOperationName, final List<FortranModule> globalModules) {

        final List<FortranModule> result = ListTools.ofM();
        final List<FortranModule> usedModules = ListTools.ofM(callerModule); // local definitions
                                                                             // are always the first
                                                                             // ones.

        for (final String usedModuleName : callerModule.getUsedModules()) {
            final FortranModule moduleXML = this.moduleNames.get(usedModuleName);
            if (moduleXML == null) {
                this.logger.warn("MODULE NOT FOUND: [{}]", usedModuleName);
            } else {
                usedModules.add(moduleXML);
            }
        }

        if (globalModules != null) {
            usedModules.addAll(globalModules);
        }

        for (final FortranModule usedModule : usedModules) {
            if (usedModule.getSpecifiedOperations().contains(calleeOperationName)) {
                result.add(usedModule);
            }
        }
        return result;
    }

}
