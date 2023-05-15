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
package org.oceandsl.tools.cmi;

import java.util.ArrayList;

import kieker.analysis.architecture.repository.ModelRepository;

import teetime.framework.Configuration;
import teetime.framework.OutputPort;

import org.oceandsl.analysis.architecture.stages.ModelRepositoryProducerStage;
import org.oceandsl.tools.cmi.stages.CheckAssemblyModelStage;
import org.oceandsl.tools.cmi.stages.CheckDeploymentModelStage;
import org.oceandsl.tools.cmi.stages.CheckExecutionModelStage;
import org.oceandsl.tools.cmi.stages.CheckSourceMissingLabelStage;
import org.oceandsl.tools.cmi.stages.CheckSourceWithoutModelElementStage;
import org.oceandsl.tools.cmi.stages.CheckStatisticModelStage;
import org.oceandsl.tools.cmi.stages.CheckTypeModelStage;

/**
 * Pipe and Filter configuration for the model integrity checker.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings settings) {
        final ModelRepositoryProducerStage readerStage = new ModelRepositoryProducerStage(settings.getInputDirectory());

        OutputPort<ModelRepository> outputPort = readerStage.getOutputPort();

        if (settings.getChecks() == null) {
            settings.setChecks(new ArrayList<ECheck>());
        }
        if (settings.getChecks().isEmpty()) {
            settings.getChecks().add(ECheck.TYPE);
        }

        if (settings.getChecks().contains(ECheck.TYPE)) {
            outputPort = this.createTypeCheck(outputPort);
        }
        if (settings.getChecks().contains(ECheck.ASSEMBLY)) {
            outputPort = this.createAssemblyCheck(outputPort);
        }
        if (settings.getChecks().contains(ECheck.DEPLOYMENT)) {
            outputPort = this.createDeploymentCheck(outputPort);
        }
        if (settings.getChecks().contains(ECheck.EXECUTION)) {
            outputPort = this.createExecutionCheck(outputPort);
        }
        if (settings.getChecks().contains(ECheck.STATISTICS)) {
            outputPort = this.createStatisticsCheck(outputPort);
        }
        if (settings.getChecks().contains(ECheck.SOURCE)) {
            outputPort = this.createSourceCheck(outputPort);
        }
    }

    private OutputPort<ModelRepository> createTypeCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckTypeModelStage stage = new CheckTypeModelStage();
        this.connectPorts(outputPort, stage.getInputPort());

        return stage.getOutputPort();
    }

    private OutputPort<ModelRepository> createAssemblyCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckAssemblyModelStage stage = new CheckAssemblyModelStage();
        this.connectPorts(outputPort, stage.getInputPort());

        return stage.getOutputPort();
    }

    private OutputPort<ModelRepository> createDeploymentCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckDeploymentModelStage stage = new CheckDeploymentModelStage();
        this.connectPorts(outputPort, stage.getInputPort());

        return stage.getOutputPort();
    }

    private OutputPort<ModelRepository> createExecutionCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckExecutionModelStage stage = new CheckExecutionModelStage();
        this.connectPorts(outputPort, stage.getInputPort());

        return stage.getOutputPort();
    }

    private OutputPort<ModelRepository> createStatisticsCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckStatisticModelStage stage = new CheckStatisticModelStage();
        this.connectPorts(outputPort, stage.getInputPort());

        return stage.getOutputPort();
    }

    private OutputPort<ModelRepository> createSourceCheck(final OutputPort<ModelRepository> outputPort) {
        final CheckSourceMissingLabelStage checkSourceModelStage = new CheckSourceMissingLabelStage();
        final CheckSourceWithoutModelElementStage checkSourceWithoutModelElementStage = new CheckSourceWithoutModelElementStage();

        this.connectPorts(outputPort, checkSourceModelStage.getInputPort());
        this.connectPorts(checkSourceModelStage.getOutputPort(), checkSourceWithoutModelElementStage.getInputPort());

        return checkSourceWithoutModelElementStage.getOutputPort();
    }

}
