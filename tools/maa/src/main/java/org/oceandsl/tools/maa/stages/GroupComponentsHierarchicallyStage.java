/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.utils.MapFileReader;
import org.oceandsl.analysis.utils.StringValueConverter;

/**
 * @author Reiner Jung
 *
 */
public class GroupComponentsHierarchicallyStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    // File to module map
    private final Map<String, String> componentMap = new HashMap<>();

    public GroupComponentsHierarchicallyStage(final List<Path> componentMapFiles, final String separator,
            final boolean caseInsensitive) throws IOException {
        for (final Path componentMapFile : componentMapFiles) {
            this.logger.info("Reading map file {}", componentMapFile.toString());
            final MapFileReader<String, String> mapFileReader = new MapFileReader<>(componentMapFile, separator,
                    this.componentMap, new StringValueConverter(caseInsensitive, 1),
                    new StringValueConverter(caseInsensitive, 0));
            mapFileReader.read();
        }
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final AssemblyModel assemblyModel = element.getModel(AssemblyModel.class);
        this.componentMap.values().forEach(componentName -> {
            if (!assemblyModel.getAssemblyComponents().containsKey(componentName)) {
                final AssemblyComponent component = this.createAssemblyComponent(componentName);
                this.addSubComponents(component, assemblyModel.getAssemblyComponents());
                assemblyModel.getAssemblyComponents().put(componentName, component);
            }
        });
    }

    private void addSubComponents(final AssemblyComponent component, final EMap<String, AssemblyComponent> components) {
        this.componentMap.entrySet().forEach(entry -> {
            if (entry.getValue().equals(component.getSignature())) {
                component.getContainedComponents().add(components.get(entry.getKey()));
            }
        });
    }

    private AssemblyComponent createAssemblyComponent(final String componentName) {
        final AssemblyComponent component = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        component.setSignature(componentName);

        return component;
    }
}
