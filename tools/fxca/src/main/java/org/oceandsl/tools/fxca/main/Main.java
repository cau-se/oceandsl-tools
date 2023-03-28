package org.oceandsl.tools.fxca.main;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.StatementNode;
import org.oceandsl.tools.fxca.tools.IOUtils;
import org.xml.sax.SAXException;

/**
 * @author hschnoor
 * Main class.
 */
final public class Main {
	
	/**
	 * As suggested by PMD, make this a utility class that cannot be instantiated.
	 */
	private Main() {
	}
	
	public static void main(final String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		if (args.length != 2) {
			System.out.println("Tool requires two arguments:");
			System.out.println(" - path to fxtran-generated XML files");
			System.out.println(" - path to store output files");
			System.exit(0);
		}

		final Path rootPath = Paths.get(args[0]);
		final Path outputDir = Paths.get(args[1]);
		final List<FortranModule> namelessModules = new ArrayList<>();
		
		// final Predicate<Path> notRootPath = path -> !path.toAbsolutePath().equals(rootPath.toAbsolutePath());
		final Predicate<Path> useDirectory = IOUtils.isDirectory;
		
		
		final List<Path> directories = IOUtils.pathsInDirectory(rootPath, useDirectory, useDirectory, true);
		System.out.println("done scanning.");
		IOUtils.createDirectory(outputDir);

		final FortranProject projectModel = new FortranProject();
		for (final Path directory : directories) {
			projectModel.addModulesFromXMLs(directory);
			System.out.println("Added modules from " + directory.toAbsolutePath() + ".");
			
			final PrintStream operationListOutput = IOUtils.printToFileAnd(System.out, outputDir.resolve("operation-definitions.csv"));
			operationListOutput.println("file,operation");

			for (final FortranModule fortranModule : projectModel) {
				
				if (!fortranModule.isNamedModule()) {
					namelessModules.add(fortranModule);
				}

				System.out.println("operation declarations:");
				for (final String operationName : fortranModule.computeOperationDeclarations()) {
					operationListOutput.println(fortranModule.getXmlFilePath().toAbsolutePath().getFileName().toString() + "," + operationName);
				}

				System.out.println("subroutine calls of " + fortranModule.getXmlFilePath() + ": ");
				fortranModule.subroutineCalls().forEach(pair -> System.out.println("call: " + pair.first + " --> " + pair.second)); // NOPMD by hschnoor on 12/5/22, 12:42 PM
				
				System.out.println("function calls of " + fortranModule.getXmlFilePath() + ": ");
				fortranModule.functionCalls().forEach(pair -> System.out.println("call: " + pair.first + " --> " + pair.second));

				System.out.println("node types:");
				IOUtils.printWithCommas(fortranModule.computeAllNodeAttributes(node -> StatementNode.nodeType(node.getNodeType())));

				System.out.println("node names:");
				IOUtils.printWithCommas(fortranModule.computeAllNodeAttributes(node -> node.getNodeName()));
			}
		
			final PrintStream tableOutput = IOUtils.printToFileAnd(System.out, outputDir.resolve("calltable.csv"));
			final PrintStream errorOutput = IOUtils.printToFileAnd(System.out, outputDir.resolve("notfound.csv"));
			
			projectModel.exportCallTable(tableOutput, errorOutput, namelessModules);
			
			operationListOutput.close();
			tableOutput.close();
			errorOutput.close();
		}
	}
}
