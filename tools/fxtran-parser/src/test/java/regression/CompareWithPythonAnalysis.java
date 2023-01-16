package regression;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import cau.agse.hs.staticfortran.model.FortranModuleModel;
import cau.agse.hs.utils.lists.misc.ListTools;
import cau.agse.hs.utils.misc.Pair;

public class CompareWithPythonAnalysis {

	public static List<String> functionDeclarationsInSediment = List.of(
			"bury",
			"calc_buff", 
			"calc_cal_c", 
			"calc_db", 
			"calc_dc", 
			"calc_do2", 
			"calc_k",
			"calss", 
			"co3",
			"co3ss", 
			"estimate_rc", 
			"get_resp", 
			"get_sed_ml_mass", 
			"my_saxpy", 
			"my_sgbfa", 
			"my_sgbsl", 
			"my_sscal", 
			"o2org", 
			"o2ss", 
			"orgc", 
			"pore_2_form", 
			"reset_pw", 
			"sed_const_cal", 
			"sed_diag", 
			"sed_ss", 
			"set_est_pore", 
			"set_pore", 
			"setup_pw", 
			"sldcon", 
			"sldfrc", 
			"tridiag"); 
	
	public List<String> functionCallsInSediment = List.of(
			"bury", "set_est_pore", 
			"bury", "get_sed_ml_mass", 
			"sed_ss", "o2org", 
			"sed_ss", "calss", 
			"sed_ss", "set_pore", 
			"sed_ss", "pore_2_form", 
			"sed_ss", "calc_do2", 
			"sed_ss", "calc_dc", 
			"sed_ss", "calc_db", 
			"sed_const_cal", "set_pore", 
			"sed_const_cal", "pore_2_form", 
			"sed_const_cal", "calc_do2", 
			"sed_const_cal", "calc_dc", 
			"sed_const_cal", "calc_db", 
			"sed_const_cal", "o2org", 
			"sed_const_cal", "co3ss", 
			"o2org", "orgc", 
			"o2org", "o2ss", 
			"o2org", "get_resp", 
			"orgc", "tridiag", 
			"orgc", "sldcon", 
			"o2ss", "tridiag", 
			"calss", "co3ss", 
			"calss", "sldcon", 
			"co3ss", "co3", 
			"co3ss", "sed_diag", 
			"co3ss", "reset_pw", 
			"co3ss", "reset_pw", 
			"co3ss", "reset_pw", 
			"co3", "my_sgbfa", 
			"co3", "my_sgbsl", 
			"my_sgbfa", "my_sscal", 
			"my_sgbfa", "my_saxpy", 
			"my_sgbsl", "my_saxpy", 
			"my_sgbsl", "my_saxpy"); 



	public void checkXMLFileHasExpectedListOfFunctionDeclarations(Path xmlFile, List<String> expectedDeclarations) throws ParserConfigurationException, SAXException, IOException {
		FortranModuleModel xml = new FortranModuleModel(xmlFile);
		List<String> declarations = new ArrayList<String>(xml.computeSubroutineDeclarations());
		// Collections.sort(expectedDeclarations);
		Collections.sort(declarations);
		assertEquals(expectedDeclarations, declarations);
		System.out.println("verified " + declarations.size() + " declarations.");
	}
	
	public void checkXMLFileHasExpectedListOfFunctionCalls(Path xmlFile, List<String> expectedCalls) throws ParserConfigurationException, SAXException, IOException {
		if ((expectedCalls.size() % 2) != 0) {
			throw new IllegalArgumentException("Requires sequence <caller>, <callee>, must be of even length.");
		}
		FortranModuleModel xml = new FortranModuleModel(xmlFile);
		List<Pair<String, String>> expectedCallPairs = ListTools.ofM();
		Iterator<String> units = expectedCalls.iterator();
		
		while(units.hasNext()) {
			expectedCallPairs.add(new Pair<>(units.next(), units.next()));
		}
		List<Pair<String, String>> actualCallPairs = xml.subroutineCalls();
		Collections.sort(actualCallPairs, Pair.getComparatorFirstSecond());
		Collections.sort(expectedCallPairs, Pair.getComparatorFirstSecond());
		assertEquals(expectedCallPairs, actualCallPairs);
		System.out.println("verified " + actualCallPairs.size() + " calls.");
	}


	// @Test TODO: Uses paths only used during development / debugging.
	public void test() throws ParserConfigurationException, SAXException, IOException {
		checkXMLFileHasExpectedListOfFunctionDeclarations(Paths.get("../../esm-preprocessed/UVic-caucluster_2.9.2/code/sediment.f.xml"), functionDeclarationsInSediment);
		checkXMLFileHasExpectedListOfFunctionCalls(Paths.get("../../esm-preprocessed/UVic-caucluster_2.9.2/code/sediment.f.xml"), functionCallsInSediment);
	}

}
