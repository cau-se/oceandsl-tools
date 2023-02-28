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
package org.oceandsl.tools.esm;

import java.io.IOException;

import teetime.framework.Configuration;


import org.oceandsl.tools.esm.stages.EsmDataFlowAnalysisStage;
import org.oceandsl.tools.esm.stages.OutputStage;
import org.oceandsl.tools.esm.stages.ReadStage;


/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings parameterConfiguration) throws IOException {

        final ReadStage readStage = new ReadStage(parameterConfiguration.getInputModelPaths());
        final EsmDataFlowAnalysisStage dataFlow = new EsmDataFlowAnalysisStage();
        final OutputStage out = new OutputStage(parameterConfiguration.getOutputDirectory());

       this.connectPorts(readStage.getOutputPort(), dataFlow.getInputPort());
       this.connectPorts(dataFlow.getOutputPort(), out.getInputPort());
    }
}
