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
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

import teetime.stage.basic.AbstractTransformation;

public class CheckTypeModelStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        this.logger.info("Check type model");
        final TypeModel model = repository.getModel(TypePackage.Literals.TYPE_MODEL);

        model.getComponentTypes().entrySet().forEach(entry -> {
            final String name = entry.getKey();
            final ComponentType value = entry.getValue();
            if (value.getSignature() == null) {
                this.logger.error("Component type signature is null for {}", name);
            } else if (value.getSignature().isEmpty()) {
                this.logger.error("Component type signature is empty for {}", name);
            } else if (!value.getSignature().equals(name)) {
                this.logger.error("Component type key {} and signature {} differ", name, value.getSignature());
            }
        });

        final long missingSignatures = GenericCheckUtils.missingSignature(model.eAllContents(), this.logger);
        this.logger.info("Missing signatures in type model {}", missingSignatures);
        final long missingReferences = GenericCheckUtils.checkReferences(TypePackage.Literals.TYPE_MODEL,
                model.eAllContents());
        this.logger.info("Missing references in type model {}", missingReferences);

        this.outputPort.send(repository);
    }

}
