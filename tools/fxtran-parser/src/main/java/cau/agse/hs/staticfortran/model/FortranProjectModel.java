package cau.agse.hs.staticfortran.model;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import cau.agse.hs.tools.DataStructureTools;
import cau.agse.hs.utils.files.Directories;
import cau.agse.hs.utils.lists.misc.ListTools;
import cau.agse.hs.utils.misc.Pair;
import cau.agse.hs.utils.staging.IOStaging;
import lombok.experimental.Delegate;

public class FortranProjectModel implements List<FortranModuleModel> {

	@Delegate List<FortranModuleModel> fortranModules;

	Map<String, FortranModuleModel> moduleNamesToXMLs;

	public FortranProjectModel() {
		fortranModules = new ArrayList<>();	
		moduleNamesToXMLs = new HashMap<>();
	}

	public void addModule(Path xmlFile) throws ParserConfigurationException, SAXException, IOException {
		FortranModuleModel module = new FortranModuleModel(xmlFile);
		fortranModules.add(module);
		moduleNamesToXMLs.put(module.moduleName, module);
	}

	public void addModulesFromXMLs(Path directory) throws IOException, ParserConfigurationException, SAXException {
		List<Path> xmlFiles = Directories.pathsInDirectory(directory, Directories.endsWith(".xml"));
		Collections.sort(xmlFiles);
		for (Path xml : xmlFiles) {
			this.addModule(xml);
		}
	}

	public void exportCallTable(PrintStream tableOut, PrintStream notFoundOut) throws ParserConfigurationException, SAXException, IOException {
		tableOut.println("callerfilename,callermodule,callerfunction,calleefilename,calleemodule,calleefunction");
		notFoundOut.println("Calls to operations that could not be found:");
		for (FortranModuleModel module : this) {
			for (Pair<String, String> call : module.operationCalls()) {
				String callerFunctionName = call.first;
				String callerFileName = module.getXmlFilePath().toAbsolutePath().getFileName().toString();				
				String calleeFunctionName = call.second;
				FortranModuleModel calleeXML = resolveCallee(module, calleeFunctionName);
				String calleeFileName = (calleeXML == null)? "<unknown>" : calleeXML.getXmlFilePath().toAbsolutePath().getFileName().toString();
				String calleeModuleName = (calleeXML == null)? "<unknown>"  : calleeXML.moduleName;
				tableOut.println(callerFileName + ", " + module.moduleName + ", " + callerFunctionName + ", " + calleeFileName + ", " + calleeModuleName + ", " + calleeFunctionName);
				if (calleeXML == null) {
					notFoundOut.println(callerFileName + ":" + callerFunctionName + " --> " +  calleeFunctionName);
				}
			}
		}
	}

	private FortranModuleModel resolveCallee(FortranModuleModel xml, String calleeFunctionName) {
		return DataStructureTools.getUniqueElementIfNonEmpty(resolveCalleeModuleCandidates(xml, calleeFunctionName), null);
	}

	private List<FortranModuleModel> resolveCalleeModuleCandidates(FortranModuleModel callerModule, String calleeOperationName) {

		List<FortranModuleModel> result = ListTools.ofM();
		List<FortranModuleModel> usedModules = ListTools.ofM(callerModule); // local definitions are always the first ones.

		for (String usedModuleName : callerModule.getUsedModules()) {
			FortranModuleModel moduleXML = moduleNamesToXMLs.get(usedModuleName);
			if (moduleXML == null) {
				System.out.println("MODULE NOT FOUND: [" + usedModuleName + "]");
			}
			else {
				usedModules.add(moduleXML);
			}
		}

		for (FortranModuleModel usedModule : usedModules) {
			if (usedModule.getSpecifiedOperations().contains(calleeOperationName)) {
				result.add(usedModule);
			}
		}
		return result;
	}
}
