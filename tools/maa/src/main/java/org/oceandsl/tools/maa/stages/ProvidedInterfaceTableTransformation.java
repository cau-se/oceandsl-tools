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
package org.oceandsl.tools.maa.stages;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.ProvidedInterfaceType;
import kieker.model.analysismodel.type.TypeModel;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.stages.staticdata.data.StringValueHandler;
import org.oceandsl.analysis.stages.staticdata.data.Table;

/**
 * Generate table for interfaces in component type, interface name, operations.
 *
 * @author Reiner Jung
 * @since 1.2
 */
public class ProvidedInterfaceTableTransformation extends AbstractTransformation<ModelRepository, Table> {

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final Table table = new Table("interfaces", new StringValueHandler("component-type"),
                new StringValueHandler("provided-interface"), new StringValueHandler("operation"));
        final TypeModel typeModel = element.getModel(TypeModel.class);
        for (final ComponentType componentType : typeModel.getComponentTypes().values()) {
            for (final ProvidedInterfaceType providedInterfaceType : componentType.getProvidedInterfaceTypes()) {
                for (final OperationType operation : providedInterfaceType.getProvidedOperationTypes().values()) {
                    table.addRow(componentType.getSignature(), providedInterfaceType.getSignature(),
                            operation.getSignature());
                }
            }
        }
        this.outputPort.send(table);
    }

}
