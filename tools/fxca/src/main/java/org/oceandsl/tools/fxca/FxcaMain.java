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

package org.oceandsl.tools.fxca;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.beust.jcommander.JCommander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

import org.oceandsl.tools.fxca.tools.IOUtils;

/**
 * Tool to process fxtran XML files into a calltable, operation-definitions and a notfound file.
 *
 * @author Henning Schnoor
 * @since 1.3.0
 */
public final class FxcaMain extends AbstractService<TeetimeConfiguration, Settings> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxcaMain.class);
    private static final String OPERATION_DEFINITIONS = "operation-definitions.csv";
    private static final String CALL_TABLE = "calltable.csv";
    private static final String NOT_FOUND = "notfound.csv";

    /**
     * As suggested by PMD, make this a utility class that cannot be instantiated.
     */
    private FxcaMain() {
    }

    public static void main(final String[] args) {
        final FxcaMain main = new FxcaMain();
        try {
            final int exitCode = main.run("fxtran code analysis", "fxca", args, new Settings());
            System.exit(exitCode);
        } catch (final IllegalArgumentException e) {
            LoggerFactory.getLogger(FxcaMain.class).error("Configuration error: {}", e.getLocalizedMessage());
            System.exit(1);
        }
    }

//    private static void processModules(final FortranProject projectModel, final Path inputPath,
//            final Path outputDirectoryPath) {
//        final List<FortranModule> namelessModules = new ArrayList<>();
//
//        FxcaMain.LOGGER.info("Added modules from {}.", inputPath);
//
//        try (final OutputStream outputStream = Files
//                .newOutputStream(outputDirectoryPath.resolve(FxcaMain.OPERATION_DEFINITIONS))) {
//            final PrintStream operationListOutput = new PrintStream(outputStream);
//
//            operationListOutput.println("file,operation");
//
//            for (final FortranModule fortranModule : projectModel.getModules().values()) {
//                if (!fortranModule.isNamedModule()) {
//                    namelessModules.add(fortranModule);
//                }
//
//                FxcaMain.LOGGER.debug("operation declarations:");
//
//                try {
//                    for (final String operationName : fortranModule.computeOperationDeclarations()) {
//                        operationListOutput
//                                .println(fortranModule.getXmlFilePath().toAbsolutePath().getFileName().toString() + ","
//                                        + operationName);
//                    }
//                } catch (ParserConfigurationException | SAXException e) {
//                    FxcaMain.LOGGER.error("Cannot process module {}: {}", fortranModule.getModuleName(),
//                            e.getLocalizedMessage());
//                }
//
//                FxcaMain.LOGGER.debug("subroutine calls of {}: ", fortranModule.getXmlFilePath());
//                try {
//                    fortranModule.subroutineCalls()
//                            .forEach(pair -> FxcaMain.LOGGER.info("call: {} --> {}", pair.first, pair.second));
//                } catch (ParserConfigurationException | SAXException e) {
//                    FxcaMain.LOGGER.error("Cannot process subroutine calls for module {}: {}",
//                            fortranModule.getModuleName(), e.getLocalizedMessage());
//                }
//
//                FxcaMain.LOGGER.debug("function calls of {}:", fortranModule.getXmlFilePath());
//                try {
//                    fortranModule.functionCalls()
//                            .forEach(pair -> FxcaMain.LOGGER.info("call: {} --> {}", pair.first, pair.second));
//                } catch (ParserConfigurationException | SAXException e) {
//                    FxcaMain.LOGGER.error("Cannot process function calls for module {}: {}",
//                            fortranModule.getModuleName(), e.getLocalizedMessage());
//                }
//
//                FxcaMain.LOGGER.debug("node types:");
//                try {
//                    IOUtils.printWithCommas(
//                            fortranModule.computeAllNodeAttributes(node -> StatementNode.nodeType(node.getNodeType())));
//                } catch (ParserConfigurationException | SAXException e) {
//                    FxcaMain.LOGGER.error("Cannot output node attribute for module {}: {}",
//                            fortranModule.getModuleName(), e.getLocalizedMessage());
//                }
//
//                FxcaMain.LOGGER.debug("node names:");
//                try {
//                    IOUtils.printWithCommas(fortranModule.computeAllNodeAttributes(node -> node.getNodeName()));
//                } catch (ParserConfigurationException | SAXException e) {
//                    FxcaMain.LOGGER.error("Cannot output node names for module {}: {}", fortranModule.getModuleName(),
//                            e.getLocalizedMessage());
//                }
//            }
//
//            final PrintStream tableOutput = new PrintStream(
//                    Files.newOutputStream(outputDirectoryPath.resolve(FxcaMain.CALL_TABLE)));
//            final PrintStream errorOutput = new PrintStream(
//                    Files.newOutputStream(outputDirectoryPath.resolve(FxcaMain.NOT_FOUND)));
//
////            try {
////                projectModel.exportCallTable(tableOutput, errorOutput, namelessModules);
////            } catch (ParserConfigurationException | SAXException e) {
////                FxcaMain.LOGGER.error("Call table export failed: {}", e.getLocalizedMessage());
////            }
//
//            operationListOutput.close();
//            tableOutput.close();
//            errorOutput.close();
//        } catch (final IOException e) {
//            FxcaMain.LOGGER.error("Cannot write {} file: {}", FxcaMain.OPERATION_DEFINITIONS, e.getLocalizedMessage());
//        }
//    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        return new TeetimeConfiguration(this.settings);
    }

    @Override
    protected Path getConfigurationPath() {
        return null;
    }

    @Override
    protected boolean checkConfiguration(final Configuration configuration, final JCommander commander) {
        return true;
    }

    @Override
    protected boolean checkParameters(final JCommander commander) throws ConfigurationException {
        IOUtils.createDirectory(this.settings.getOutputDirectoryPath());

        final Predicate<Path> useDirectory = IOUtils.isDirectory;

        final List<Path> directories = new ArrayList<>();
        if (this.settings.isFlat()) {
            directories.addAll(this.settings.getInputDirectoryPaths());
        } else {
            for (final Path rootPath : this.settings.getInputDirectoryPaths()) {
                try {
                    directories.addAll(IOUtils.pathsInDirectory(rootPath, useDirectory, useDirectory, true));
                } catch (final IOException e) {
                    FxcaMain.LOGGER.error("Error scanning directory {}: {} ", rootPath, e.getLocalizedMessage());
                }
            }
        }

        return true;
    }

    @Override
    protected void shutdownService() {
    }
}
