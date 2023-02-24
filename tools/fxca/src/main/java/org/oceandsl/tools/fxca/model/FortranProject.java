package org.oceandsl.tools.fxca.model;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.oceandsl.tools.fxca.tools.IOUtils;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;
import org.xml.sax.SAXException;

import lombok.experimental.Delegate;

public class FortranProject implements List<FortranModule> {

	/**
	 * Actual list to which List-calls are delegated.
	 */
	private @Delegate List<FortranModule> fortranModules;
	private Map<String, FortranModule> moduleNamesToXMLs;

	/**
	 * Constructs Project Model with empty content.
	 */
	public FortranProject() {
		fortranModules = new ArrayList<>();	
		moduleNamesToXMLs = new HashMap<>();
	}

	public void addModule(final Path xmlFile) throws ParserConfigurationException, SAXException, IOException {
		final FortranModule module = new FortranModule(xmlFile);
		fortranModules.add(module);
		moduleNamesToXMLs.put(module.getModuleName(), module);
	}

	public void addModulesFromXMLs(final Path directory) throws IOException, ParserConfigurationException, SAXException {
		List<Path> xmlFiles = IOUtils.pathsInDirectory(directory, IOUtils.endsWith(".xml"));
		Collections.sort(xmlFiles);
		for (final Path xml : xmlFiles) {
			this.addModule(xml);
		}
	}

	/**
	 * Exports the call cable into CSV files.
	 * 
	 * @param PrintStream for table
	 * @param PrintStream for list of not-found entries
	 * @param globalModules list of modules that are available to all modules / files without explicit reference
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void exportCallTable(final PrintStream tableOut, final PrintStream notFoundOut, final List<FortranModule> globalModules) throws ParserConfigurationException, SAXException, IOException {
		tableOut.println("callerfilename,callermodule,callerfunction,calleefilename,calleemodule,calleefunction");
		notFoundOut.println("Calls to operations that could not be found:");
		for (FortranModule module : this) {
			for (final Pair<String, String> call : module.operationCalls()) {
				final String callerFunctionName = call.first;
				final String callerFileName = module.getXmlFilePath().toAbsolutePath().getFileName().toString();				
				final String calleeFunctionName = call.second;
				final FortranModule calleeXML = resolveCallee(module, calleeFunctionName, globalModules);
				final String calleeFileName = (calleeXML == null)? "<unknown>" : calleeXML.getXmlFilePath().toAbsolutePath().getFileName().toString();
				final String calleeModuleName = (calleeXML == null)? "<unknown>"  : calleeXML.getModuleName();
				tableOut.println(callerFileName + ", " + module.getModuleName() + ", " + callerFunctionName + ", " + calleeFileName + ", " + calleeModuleName + ", " + calleeFunctionName);
				if (calleeXML == null) {
					notFoundOut.println(callerFileName + ":" + callerFunctionName + " --> " +  calleeFunctionName);
				}
			}
		}
	}

	private FortranModule resolveCallee(final FortranModule xml, final String calleeFunctionName, final List<FortranModule> globalModules) {
		System.out.println("resolve Callee: " + calleeFunctionName + " from " + xml.getXmlFilePath());
		/* if ("MDS_WRITE_FIELD".equals(calleeFunctionName)) {
			System.exit(0);
		} */
		return ListTools.getUniqueElementIfNonEmpty(resolveCalleeModuleCandidates(xml, calleeFunctionName, globalModules), null);
	}

	private List<FortranModule> resolveCalleeModuleCandidates(final FortranModule callerModule, final String calleeOperationName, final List<FortranModule> globalModules) {
//
		final List<FortranModule> result = ListTools.ofM();
		final List<FortranModule> usedModules = ListTools.ofM(callerModule); // local definitions are always the first ones.
		
		for (final String usedModuleName : callerModule.getUsedModules()) {
			final FortranModule moduleXML = moduleNamesToXMLs.get(usedModuleName);
			if (moduleXML == null) {
				System.out.println("MODULE NOT FOUND: [" + usedModuleName + "]");
			}
			else {
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
