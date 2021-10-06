/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.pp.log;

import java.io.IOException;

import org.oceandsl.analysis.CSVFunctionCallReaderStage;

import teetime.framework.Configuration;

/**
 * Pipe and Filter configuration for the static log preprocessor.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class FixStaticLogTeetimeConfiguration extends Configuration {

    public FixStaticLogTeetimeConfiguration(final Settings parameterConfiguration) throws IOException {
        final CSVFunctionCallReaderStage readCsvStage = new CSVFunctionCallReaderStage(
                parameterConfiguration.getInputPath(), ",");
        final CSVFunctionMapperStage functionMapperStage = new CSVFunctionMapperStage(
                parameterConfiguration.getMapPaths());
        final CorrectCallsStage correctCallsStage = new CorrectCallsStage();
        correctCallsStage.declareActive();
        final CSVWriterStage writeCsvStage = new CSVWriterStage(parameterConfiguration.getOutputFile().toPath());

        this.connectPorts(readCsvStage.getOutputPort(), correctCallsStage.getInputPort());
        this.connectPorts(functionMapperStage.getOutputPort(), correctCallsStage.getMapInputPort());
        this.connectPorts(correctCallsStage.getOutputPort(), writeCsvStage.getInputPort());
    }
}
