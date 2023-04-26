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
package org.oceandsl.tools.sar;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;

import kieker.analysis.architecture.recovery.signature.IComponentSignatureExtractor;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypePackage;

import teetime.framework.Configuration;

import org.oceandsl.analysis.code.stages.CsvReaderStage;
import org.oceandsl.analysis.code.stages.IStorageSignatureExtractor;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.sar.stages.dataflow.StorageAssemblyModelAssembler;
import org.oceandsl.tools.sar.stages.dataflow.StorageDeploymentModelAssembler;
import org.oceandsl.tools.sar.stages.dataflow.StorageEventModelAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.StorageToStorageEventStage;
import org.oceandsl.tools.sar.stages.dataflow.StorageTypeModelAssembler;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TeetimeStorageConfiguration extends Configuration {

    public TeetimeStorageConfiguration(final Logger logger, final Settings settings, final ModelRepository repository)
            throws IOException, ValueConversionErrorException {

        final Path storagePath = settings.getInputFile().resolve(StaticArchitectureRecoveryMain.STORAGE_FILENAME);

        final CsvReaderStage<Storage> storagesReader = new CsvReaderStage<>(storagePath, settings.getSplitSymbol(),
                true, new StorageFactory());

        final StorageToStorageEventStage storageToStorageEventStage = new StorageToStorageEventStage(
                settings.getHostname());

        /** -- storage -- */
        final StorageEventModelAssemblerStage storageTypeModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageTypeModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel(),
                        this.createComponentSignatureExtractor(settings), this.createStorageSignatureExtractor()));
        final StorageEventModelAssemblerStage storageAssemblyModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageAssemblyModelAssembler(repository.getModel(TypePackage.Literals.TYPE_MODEL),
                        repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));
        final StorageEventModelAssemblerStage storageDeploymentModelAssemblerStage = new StorageEventModelAssemblerStage(
                new StorageDeploymentModelAssembler(repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL),
                        repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL),
                        repository.getModel(SourcePackage.Literals.SOURCE_MODEL), settings.getSourceLabel()));

        /** connecting ports. */
        this.connectPorts(storagesReader.getOutputPort(), storageToStorageEventStage.getInputPort());
        this.connectPorts(storageToStorageEventStage.getOutputPort(), storageTypeModelAssemblerStage.getInputPort());

        this.connectPorts(storageTypeModelAssemblerStage.getOutputPort(),
                storageAssemblyModelAssemblerStage.getInputPort());
        this.connectPorts(storageAssemblyModelAssemblerStage.getOutputPort(),
                storageDeploymentModelAssemblerStage.getInputPort());

    }

    private IComponentSignatureExtractor createComponentSignatureExtractor(final Settings settings) {
        return new IComponentSignatureExtractor() {

            @Override
            public void extract(final ComponentType componentType) {
                String signature = componentType.getSignature();
                if (signature == null) {
                    signature = "-- none --";
                }
                final Path path = Paths.get(signature);
                final String name = path.getName(path.getNameCount() - 1).toString();
                final String rest = path.getParent() == null ? settings.getExperimentName()
                        : settings.getExperimentName() + "." + path.getParent().toString();
                componentType.setName(name);
                componentType.setPackage(rest);
            }
        };
    }

    private IStorageSignatureExtractor createStorageSignatureExtractor() {
        return new IStorageSignatureExtractor() {

            @Override
            public void extract(final StorageType storageType) {
                final String name = storageType.getName();
                storageType.setName(name);
            }

        };
    }

}
