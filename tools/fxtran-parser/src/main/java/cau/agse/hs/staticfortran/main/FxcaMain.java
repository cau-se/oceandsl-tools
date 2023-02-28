package cau.agse.hs.staticfortran.main;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cau.agse.hs.staticfortran.model.FortranModule;
import cau.agse.hs.staticfortran.model.FortranProject;
import cau.agse.hs.staticfortran.model.StatementNode;
import cau.agse.hs.utils.files.Directories;
import cau.agse.hs.utils.files.FileUtils;
import cau.agse.hs.utils.staging.IOStaging;

public class FxcaMain {

    static String[] UVicVersions = { "UVic-caucluster_2.6_fixed", "UVic-caucluster_2.7.1_fixed",
            "UVic-caucluster_2.7.2_fixed", "UVic-caucluster_2.7.3-fixed", "UVic-caucluster_2.7.4_fixed",
            "UVic-caucluster_2.7.5_fixed", "UVic-caucluster_2.7_fixed", "UVic-caucluster_2.8", "UVic-caucluster_2.9",
            "UVic-caucluster_2.9.1", "UVic-caucluster_2.9.2" };

    public static void main(final String[] args) throws SAXException, IOException, ParserConfigurationException {

        final List<FortranModule> namelessModules = new ArrayList<>();

        if (args.length != 2) {
            System.out.println("Tool requires two arguments:");
            System.out.println(" - path to fxtran-generated XML files");
            System.out.println(" - path to store output files");
            System.exit(0);
        }

        final Path rootPath = Paths.get(args[0]);
        final Path outputDir = Paths.get(args[1]);
        final boolean handleRoot = true;

        final Predicate<Path> notRootPath = path -> !path.toAbsolutePath().equals(rootPath.toAbsolutePath());
        final Predicate<Path> useDirectory = handleRoot ? Directories.isDirectory : notRootPath;

        final List<Path> directories = Directories.pathsInDirectory(rootPath, useDirectory, useDirectory, true);
        System.out.println("done scanning.");

        for (final Path directory : directories) {
            final FortranProject projectModel = new FortranProject();
            projectModel.addModulesFromXMLs(directory);
            System.out.println("Added modules from " + directory.toAbsolutePath() + ".");

            for (final FortranModule fortranModule : projectModel) {

                // System.out.println("function declarations:");
                // xml.functionDeclarations().forEach(System.out::println);

                System.out.println("subroutine calls of " + fortranModule.getXmlFilePath() + ": ");
                fortranModule.subroutineCalls()
                        .forEach(pair -> System.out.println("call: " + pair.first + " --> " + pair.second));

                System.out.println("function calls of " + fortranModule.getXmlFilePath() + ": ");
                fortranModule.functionCalls()
                        .forEach(pair -> System.out.println("call: " + pair.first + " --> " + pair.second));

                System.out.println("node types:");
                IOStaging.printWithCommas(
                        fortranModule.computeAllNodeAttributes(node -> StatementNode.nodeType(node.getNodeType())));

                System.out.println("node names:");
                IOStaging.printWithCommas(fortranModule.computeAllNodeAttributes(node -> node.getNodeName()));
            }

            FileUtils.createDirectory(outputDir);

            final PrintStream tableOutput = FileUtils.printToFileAnd(System.out, outputDir.resolve("calltable.csv"));
            final PrintStream errorOutput = FileUtils.printToFileAnd(System.out, outputDir.resolve("notfound.csv"));

            projectModel.exportCallTable(tableOutput, errorOutput, namelessModules);
        }
    }

}
