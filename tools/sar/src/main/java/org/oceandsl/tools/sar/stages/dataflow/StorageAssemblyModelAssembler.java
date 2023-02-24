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
package org.oceandsl.tools.sar.stages.dataflow;

import kieker.analysis.architecture.recovery.AbstractModelAssembler;
import kieker.analysis.architecture.recovery.events.StorageEvent;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.type.TypeModel;

import org.oceandsl.analysis.code.stages.IStorageEventAssembler;

/**
 * Model assembler for storage elements.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class StorageAssemblyModelAssembler extends AbstractModelAssembler implements IStorageEventAssembler {

    private final AssemblyModel assemblyModel;
    private final TypeModel typeModel;

    public StorageAssemblyModelAssembler(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @Override
    public void addStorage(final StorageEvent event) {
        final AssemblyComponent assemblyComponent = this.assemblyComponentSetUp(event);
        this.addStorage(assemblyComponent, event);
    }

    /**
     * This function retrieves a stored or new created AssemblyComponent. Depending on the given
     * identifier in the transfer-object a subroutine is called to set up a new AssemblyComponent
     * instance.
     *
     * @param storageEvent
     *            TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    private AssemblyComponent assemblyComponentSetUp(final StorageEvent storageEvent) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents()
                .get(storageEvent.getComponentSignature());
        if (assemblyComponent == null) {
            assemblyComponent = this.createAssemblyComponent(storageEvent.getComponentSignature());
        }
        return assemblyComponent;
    }

    /**
     * This function is used to create a new file AssemblyComponentObject and store it in the given
     * assembly model.
     *
     * @param signature
     *            assembly component signature
     * @return file component created and stored in the assembly model
     */
    private AssemblyComponent createAssemblyComponent(final String signature) {
        final AssemblyComponent assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        assemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(signature));
        assemblyComponent.setSignature(signature);

        this.assemblyModel.getComponents().put(signature, assemblyComponent);
        this.updateSourceModel(assemblyComponent);
        return assemblyComponent;
    }

    /**
     * This function adds an AssemblyStorage to a given component.
     *
     * @param assemblyComponent
     *            the storage should be stored in
     * @param event
     *            the storage event
     * @return the added operation. Useful for DEBUG Reasons
     */
    private AssemblyStorage addStorage(final AssemblyComponent assemblyComponent, final StorageEvent event) {
        final String storageSignature = event.getStorageSignature();
        AssemblyStorage assemblyStorage = assemblyComponent.getStorages().get(storageSignature);
        if (assemblyStorage == null) {
            assemblyStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
            assemblyStorage
                    .setStorageType(assemblyComponent.getComponentType().getProvidedStorages().get(storageSignature));
        }

        return assemblyStorage;
    }

}
