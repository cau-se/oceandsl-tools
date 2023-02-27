/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

/**
 * @author Henning Schnoor
 *
 */

package org.oceandsl.tools.fxca.main;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;

import com.beust.jcommander.JCommander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.StatementNode;
import org.oceandsl.tools.fxca.tools.IOUtils;

/**
 * Tool to process fxtran XML files into a calltable, operation-definitions and a notfound file.
 *
 * @author Henning Schnoor
 * @since 1.3.0
 */
public final class FxcaMain {

    private static Logger LOGGER = LoggerFactory.getLogger(FxcaMain.class);

    /**
     * As suggested by PMD, make this a utility class that cannot be instantiated.
     */
    private FxcaMain() {
    }

    public static void main(final String[] args) throws IOException, ParserConfigurationException, Exception {
        final Settings settings = new Settings();
        JCommander.newBuilder().addObject(settings).build().parse(args);

        final List<FortranModule> namelessModules = new ArrayList<>();

        // final Predicate<Path> notRootPath = path ->
        // !path.toAbsolutePath().equals(rootPath.toAbsolutePath());
        final Predicate<Path> useDirectory = IOUtils.isDirectory;

        final List<Path> directories = new ArrayList<>();
        if (settings.isFlat()) {
            directories.addAll(settings.getInputDirectoryPaths());
        } else {
            for (final Path rootPath : settings.getInputDirectoryPaths()) {
                directories.addAll(IOUtils.pathsInDirectory(rootPath, useDirectory, useDirectory, true));
            }
        }
        FxcaMain.LOGGER.info("done scanning directories.");

        IOUtils.createDirectory(settings.getOutputDirectoryPath());

        final FortranProject projectModel = new FortranProject();
        for (final Path directory : directories) {
            projectModel.addModulesFromXMLs(directory);
            FxcaMain.LOGGER.info("Added modules from {}.", directory.toAbsolutePath());

            final PrintStream operationListOutput = IOUtils.printToFileAnd(System.out,
                    settings.getOutputDirectoryPath().resolve("operation-definitions.csv"));
            operationListOutput.println("file,operation");

            for (final FortranModule fortranModule : projectModel) {

                if (!fortranModule.isNamedModule()) {
                    namelessModules.add(fortranModule);
                }

                FxcaMain.LOGGER.info("operation declarations:");
                for (final String operationName : fortranModule.computeOperationDeclarations()) {
                    operationListOutput.println(fortranModule.getXmlFilePath().toAbsolutePath().getFileName().toString()
                            + "," + operationName);
                }

                FxcaMain.LOGGER.info("subroutine calls of {}: ", fortranModule.getXmlFilePath());
                fortranModule.subroutineCalls()
                        .forEach(pair -> FxcaMain.LOGGER.info("call: {} --> {}", pair.first, pair.second));

                FxcaMain.LOGGER.info("function calls of {}:", fortranModule.getXmlFilePath());
                fortranModule.functionCalls()
                        .forEach(pair -> FxcaMain.LOGGER.info("call: {} --> {}", pair.first, pair.second));

                FxcaMain.LOGGER.info("node types:");
                IOUtils.printWithCommas(
                        fortranModule.computeAllNodeAttributes(node -> StatementNode.nodeType(node.getNodeType())));

                FxcaMain.LOGGER.info("node names:");
                IOUtils.printWithCommas(fortranModule.computeAllNodeAttributes(node -> node.getNodeName()));
            }

            final PrintStream tableOutput = IOUtils.printToFileAnd(System.out,
                    settings.getOutputDirectoryPath().resolve("calltable.csv"));
            final PrintStream errorOutput = IOUtils.printToFileAnd(System.out,
                    settings.getOutputDirectoryPath().resolve("notfound.csv"));

            projectModel.exportCallTable(tableOutput, errorOutput, namelessModules);

            operationListOutput.close();
            tableOutput.close();
            errorOutput.close();

            FxcaMain.LOGGER.info("Done");
        }
    }
}
