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
package org.oceandsl.tools.cmi.stages;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;

import teetime.stage.basic.AbstractTransformation;

public class CheckAssemblyModelStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        this.logger.info("Check assembly model");

        final AssemblyModel assemblyModel = repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);

        final long missingSignatures = GenericCheckUtils.missingSignature(assemblyModel.eAllContents(), this.logger);
        this.logger.info("Missing signatures in assembly model {}", missingSignatures);

        final long missingReferences = GenericCheckUtils.checkReferences(AssemblyPackage.Literals.ASSEMBLY_MODEL,
                assemblyModel.eAllContents());
        this.logger.info("Missing references in assembly model {}", missingReferences);

        this.outputPort.send(repository);
    }

}
