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
package org.oceandsl.tools.mop.operations;

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.tools.mop.EStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class ModelRepositoryMerger {

    final static Logger LOGGER = LoggerFactory.getLogger(ModelRepositoryMerger.class);

    public static void perform(final ModelRepository lastModelRepository, final ModelRepository mergeModelRepository,
            final EStrategy strategy) {
        ModelRepositoryMerger.mergeTypeModel(lastModelRepository.getModel(TypeModel.class),
                mergeModelRepository.getModel(TypeModel.class));
        ModelRepositoryMerger.mergeAssemblyModel(lastModelRepository.getModel(TypeModel.class),
                lastModelRepository.getModel(AssemblyModel.class), mergeModelRepository.getModel(AssemblyModel.class));
        ModelRepositoryMerger.mergeDeploymentModel(lastModelRepository.getModel(AssemblyModel.class),
                lastModelRepository.getModel(DeploymentModel.class),
                mergeModelRepository.getModel(DeploymentModel.class));
        ModelRepositoryMerger.mergeExecutionModel(lastModelRepository.getModel(DeploymentModel.class),
                lastModelRepository.getModel(ExecutionModel.class),
                mergeModelRepository.getModel(ExecutionModel.class));
        ModelRepositoryMerger.mergeStatisticsModel(lastModelRepository.getModel(StatisticsModel.class),
                mergeModelRepository.getModel(StatisticsModel.class));
        ModelRepositoryMerger.mergeSourceModel(lastModelRepository.getModel(SourceModel.class),
                mergeModelRepository.getModel(SourceModel.class));
    }

    private static void mergeTypeModel(final TypeModel model, final TypeModel mergeModel) {
        // add additional component types if necessary
        for (final ComponentType mergeType : mergeModel.getComponentTypes().values()) {
            if (!model.getComponentTypes().containsKey(mergeType.getSignature())) {
                model.getComponentTypes().put(mergeType.getSignature(), TypeModelCloneUtils.duplicate(mergeType));
            }
        }
        // now merge operations
        for (final ComponentType type : model.getComponentTypes().values()) {
            final ComponentType mergeType = mergeModel.getComponentTypes().get(type.getSignature());
            if (mergeType != null) {
                ModelRepositoryMerger.mergeTypeOperations(type, mergeType.getProvidedOperations());
                ModelRepositoryMerger.mergeTypeStorages(type, mergeType.getProvidedStorages());
            }
        }
    }

    private static void mergeTypeOperations(final ComponentType type,
            final EMap<String, OperationType> providedOperations) {
        for (final OperationType mergeOperation : providedOperations.values()) {
            if (!type.getProvidedOperations().containsKey(mergeOperation.getSignature())) {
                type.getProvidedOperations().put(mergeOperation.getSignature(),
                        TypeModelCloneUtils.duplicate(mergeOperation));
            }
        }
    }

    private static void mergeTypeStorages(final ComponentType type, final EMap<String, StorageType> providedStorages) {
        for (final StorageType mergeStorage : providedStorages.values()) {
            if (!type.getProvidedStorages().containsKey(mergeStorage.getName())) {
                type.getProvidedStorages().put(mergeStorage.getName(), TypeModelCloneUtils.duplicate(mergeStorage));
            }
        }
    }

    /** -- assembly model -- */

    private static void mergeAssemblyModel(final TypeModel typeModel, final AssemblyModel model,
            final AssemblyModel mergeModel) {
        // add additional component types if necessary
        for (final AssemblyComponent mergeComponent : mergeModel.getAssemblyComponents().values()) {
            if (!model.getAssemblyComponents().containsKey(mergeComponent.getSignature())) {
                model.getAssemblyComponents().put(mergeComponent.getSignature(),
                        AssemblyModelCloneUtils.duplicate(typeModel, mergeComponent));
            }
        }
        // now merge operations
        for (final AssemblyComponent component : model.getAssemblyComponents().values()) {
            final AssemblyComponent mergeComponent = mergeModel.getAssemblyComponents().get(component.getSignature());
            if (mergeComponent != null) {
                ModelRepositoryMerger.mergeAssemblyOperations(component, mergeComponent.getAssemblyOperations());
                ModelRepositoryMerger.mergeAssemblyStorages(component, mergeComponent.getAssemblyStorages());
            }
        }
    }

    private static void mergeAssemblyOperations(final AssemblyComponent component,
            final EMap<String, AssemblyOperation> assemblyOperations) {
        for (final AssemblyOperation mergeOperation : assemblyOperations.values()) {
            if (!component.getAssemblyOperations().containsKey(mergeOperation.getOperationType().getSignature())) {
                component.getAssemblyOperations().put(mergeOperation.getOperationType().getSignature(),
                        AssemblyModelCloneUtils.duplicate(component.getComponentType(), mergeOperation));
            }
        }
    }

    private static void mergeAssemblyStorages(final AssemblyComponent component,
            final EMap<String, AssemblyStorage> assemblyStorages) {
        for (final AssemblyStorage mergeStorage : assemblyStorages.values()) {
            if (!component.getAssemblyOperations().containsKey(mergeStorage.getStorageType().getName())) {
                component.getAssemblyStorages().put(mergeStorage.getStorageType().getName(),
                        AssemblyModelCloneUtils.duplicate(component.getComponentType(), mergeStorage));
            }
        }
    }

    /** -- deployment -- */

    private static void mergeDeploymentModel(final AssemblyModel assemblyModel, final DeploymentModel model,
            final DeploymentModel mergeModel) {
        // add additional contexts if necessary
        for (final DeploymentContext mergeDeploymentContext : mergeModel.getDeploymentContexts().values()) {
            if (!model.getDeploymentContexts().containsKey(mergeDeploymentContext.getName())) {
                model.getDeploymentContexts().put(mergeDeploymentContext.getName(),
                        DeploymentModelCloneUtils.duplicate(assemblyModel, mergeDeploymentContext));
            }
        }
        // now merge operations
        for (final DeploymentContext deploymentContext : model.getDeploymentContexts().values()) {
            final DeploymentContext mergeDeploymentContext = mergeModel.getDeploymentContexts()
                    .get(deploymentContext.getName());
            if (mergeDeploymentContext != null) {
                ModelRepositoryMerger.mergeDepolymentComponents(assemblyModel, deploymentContext,
                        mergeDeploymentContext.getComponents());
            }
        }

    }

    private static void mergeDepolymentComponents(final AssemblyModel assemblyModel,
            final DeploymentContext deploymentContext, final EMap<String, DeployedComponent> components) {
        for (final DeployedComponent mergeComponent : components.values()) {
            if (!deploymentContext.getComponents().containsKey(mergeComponent.getSignature())) {
                deploymentContext.getComponents().put(mergeComponent.getSignature(),
                        DeploymentModelCloneUtils.duplicate(assemblyModel, mergeComponent));
            } else {
                final DeployedComponent component = deploymentContext.getComponents()
                        .get(mergeComponent.getSignature());
                ModelRepositoryMerger.mergeDeploymentOperations(component.getAssemblyComponent(), component,
                        mergeComponent.getContainedOperations());
                ModelRepositoryMerger.mergeDeploymentStorages(component.getAssemblyComponent(), component,
                        mergeComponent.getContainedStorages());
            }
        }
    }

    private static void mergeDeploymentOperations(final AssemblyComponent assemblyComponent,
            final DeployedComponent component, final EMap<String, DeployedOperation> containedOperations) {
        for (final Entry<String, DeployedOperation> mergeOperation : containedOperations) {
            if (!component.getContainedOperations().containsKey(mergeOperation.getKey())) {
                component.getContainedOperations().put(mergeOperation.getKey(),
                        DeploymentModelCloneUtils.duplicate(assemblyComponent, mergeOperation.getValue()));
            }
        }
    }

    private static void mergeDeploymentStorages(final AssemblyComponent assemblyComponent,
            final DeployedComponent component, final EMap<String, DeployedStorage> containedStorages) {
        for (final Entry<String, DeployedStorage> mergeStorage : containedStorages) {
            if (!component.getContainedStorages().containsKey(mergeStorage.getKey())) {
                component.getContainedStorages().put(mergeStorage.getKey(),
                        DeploymentModelCloneUtils.duplicate(assemblyComponent, mergeStorage.getValue()));
            }
        }
    }

    /** -- execution model -- */

    private static void mergeExecutionModel(final DeploymentModel deploymentModel, final ExecutionModel model,
            final ExecutionModel mergeModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> entry : mergeModel
                .getAggregatedInvocations()) {
            if (!model.getAggregatedInvocations().containsKey(entry.getKey())) {
                final AggregatedInvocation value = ExecutionModelCloneUtils.duplicate(deploymentModel,
                        entry.getValue());
                final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getSource());
                key.setSecond(value.getTarget());
                model.getAggregatedInvocations().put(key, value);
            }
        }
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> entry : mergeModel
                .getAggregatedStorageAccesses()) {
            if (!model.getAggregatedStorageAccesses().containsKey(entry.getKey())) {
                final AggregatedStorageAccess value = ExecutionModelCloneUtils.duplicate(deploymentModel,
                        entry.getValue());
                final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getCode());
                key.setSecond(value.getStorage());
                model.getAggregatedStorageAccesses().put(key, value);
            }
        }
    }

    /** -- statistics model -- */
    private static void mergeStatisticsModel(final StatisticsModel model, final StatisticsModel mergeModel) {
        for (final Entry<EObject, Statistics> statistic : mergeModel.getStatistics()) {
            if (!model.getStatistics().containsKey(statistic.getKey())) {
                model.getStatistics().put(statistic.getKey(),
                        StatisticsModelCloneUtils.duplicate(statistic.getValue()));
            } else {
                final Statistics newStatistic = model.getStatistics().get(statistic.getKey());
                for (final Entry<EPredefinedUnits, StatisticRecord> statisticRecord : statistic.getValue()
                        .getStatistics()) {
                    if (!newStatistic.getStatistics().containsKey(statisticRecord.getKey())) {
                        newStatistic.getStatistics().put(statisticRecord.getKey(),
                                StatisticsModelCloneUtils.duplicate(statisticRecord.getValue()));
                    } else {
                        final StatisticRecord newRecord = newStatistic.getStatistics().get(statisticRecord.getKey());
                        for (final Entry<EPropertyType, Object> property : statisticRecord.getValue().getProperties()) {
                            if (!newRecord.getProperties().containsKey(property.getKey())) {
                                newRecord.getProperties().put(property.getKey(),
                                        StatisticsModelCloneUtils.duplicateObject(property.getValue()));
                            } else {
                                final Object newValue = StatisticsModelCloneUtils
                                        .compute(newRecord.getProperties().get(property.getKey()), property.getValue());
                                newRecord.getProperties().put(property.getKey(), newValue);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void mergeSourceModel(final SourceModel model, final SourceModel mergeModel) {
        for (final Entry<EObject, EList<String>> mergeSource : mergeModel.getSources()) {
            final EObject modelKey = ModelRepositoryMerger.findCorrespondingKey(model.getSources(),
                    mergeSource.getKey());
            if (modelKey != null) {
                final EList<String> modelSource = model.getSources().get(modelKey);
                if (modelSource == null) {
                    model.getSources().put(modelKey, mergeSource.getValue());
                } else {
                    ModelRepositoryMerger.mergeSources(modelSource, mergeSource.getValue());
                }

                for (final String source : model.getSources().get(modelKey)) {
                    ModelRepositoryMerger.LOGGER.debug("source {}", source);
                }
            } else {
                ModelRepositoryMerger.LOGGER.error("The merged model must contain all keys, but {} is missing",
                        mergeSource.getKey());
            }
        }
    }

    private static void mergeSources(final EList<String> modelSource, final EList<String> mergeSource) {
        for (final String value : mergeSource) {
            if (!modelSource.contains(value)) {
                modelSource.add(value);
            }
        }
    }

    private static EObject findCorrespondingKey(final EMap<EObject, EList<String>> sources, final EObject mergeKey) {
        for (final EObject key : sources.keySet()) {
            if (key.getClass().equals(mergeKey.getClass())) {
                if (mergeKey instanceof DeployedOperation) {
                    if (ModelRepositoryMerger.isEqual((DeployedOperation) mergeKey, (DeployedOperation) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof DeployedComponent) {
                    if (ModelRepositoryMerger.isEqual((DeployedComponent) mergeKey, (DeployedComponent) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof DeploymentContext) {
                    if (ModelRepositoryMerger.isEqual((DeploymentContext) mergeKey, (DeploymentContext) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof AssemblyOperation) {
                    if (ModelRepositoryMerger.isEqual((AssemblyOperation) mergeKey, (AssemblyOperation) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof AssemblyComponent) {
                    if (ModelRepositoryMerger.isEqual((AssemblyComponent) mergeKey, (AssemblyComponent) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof AssemblyOperation) {
                    if (ModelRepositoryMerger.isEqual((AssemblyOperation) mergeKey, (AssemblyOperation) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof ComponentType) {
                    if (ModelRepositoryMerger.isEqual((ComponentType) mergeKey, (ComponentType) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof OperationType) {
                    if (ModelRepositoryMerger.isEqual((OperationType) mergeKey, (OperationType) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof StorageType) {
                    if (ModelRepositoryMerger.isEqual((StorageType) mergeKey, (StorageType) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof AggregatedInvocation) {
                    if (ModelRepositoryMerger.isEqual((AggregatedInvocation) mergeKey, (AggregatedInvocation) key)) {
                        return key;
                    }
                } else if (mergeKey instanceof AggregatedStorageAccess) {
                    if (ModelRepositoryMerger.isEqual((AggregatedStorageAccess) mergeKey,
                            (AggregatedStorageAccess) key)) {
                        return key;
                    }
                } else {
                    System.err.println("Missing support for " + mergeKey.getClass().getCanonicalName());
                }
            }
        }
        return null;
    }

    private static boolean isEqual(final AggregatedStorageAccess mergeStorageAccess,
            final AggregatedStorageAccess storageAccess) {
        return ModelRepositoryMerger.isEqual(mergeStorageAccess.getCode(), storageAccess.getCode())
                && mergeStorageAccess.getDirection().equals(storageAccess.getDirection())
                && ModelRepositoryMerger.isEqual(mergeStorageAccess.getStorage(), storageAccess.getStorage());
    }

    private static boolean isEqual(final DeployedStorage mergeStorage, final DeployedStorage storage) {
        return ModelRepositoryMerger.isEqual(mergeStorage.getAssemblyStorage(), storage.getAssemblyStorage())
                && ModelRepositoryMerger.isEqual(mergeStorage.getComponent(), storage.getComponent());
    }

    private static boolean isEqual(final AssemblyStorage mergeAssemblyStorage, final AssemblyStorage assemblyStorage) {
        return ModelRepositoryMerger.isEqual(mergeAssemblyStorage.getStorageType(), assemblyStorage.getStorageType())
                && ModelRepositoryMerger.isEqual(mergeAssemblyStorage.getAssemblyComponent(),
                        assemblyStorage.getAssemblyComponent());
    }

    private static boolean isEqual(final AggregatedInvocation mergeInvocation, final AggregatedInvocation invocation) {
        return ModelRepositoryMerger.isEqual(mergeInvocation.getSource(), invocation.getSource())
                && ModelRepositoryMerger.isEqual(mergeInvocation.getTarget(), invocation.getTarget());
    }

    private static boolean isEqual(final DeployedOperation mergeKey, final DeployedOperation key) {
        return ModelRepositoryMerger.isEqual(mergeKey.getComponent(), key.getComponent())
                && ModelRepositoryMerger.isEqual(mergeKey.getAssemblyOperation(), key.getAssemblyOperation());
    }

    private static boolean isEqual(final AssemblyOperation mergeAssemblyOperation,
            final AssemblyOperation assemblyOperation) {
        return ModelRepositoryMerger.isEqual(mergeAssemblyOperation.getAssemblyComponent(),
                assemblyOperation.getAssemblyComponent())
                && ModelRepositoryMerger.isEqual(mergeAssemblyOperation.getOperationType(),
                        assemblyOperation.getOperationType());
    }

    private static boolean isEqual(final OperationType mergeOperationType, final OperationType operationType) {
        return mergeOperationType.getSignature().equals(operationType.getSignature()) && ModelRepositoryMerger
                .isEqual(mergeOperationType.getComponentType(), operationType.getComponentType());
    }

    private static boolean isEqual(final StorageType mergeStorageType, final StorageType storageType) {
        return mergeStorageType.getName().equals(storageType.getName());
        // && ModelRepositoryMerger.isEqual(mergeStorageType.getComponentType(),
        // storageType.getComponentType());
    }

    private static boolean isEqual(final AssemblyComponent mergeAssemblyComponent,
            final AssemblyComponent assemblyComponent) {
        return mergeAssemblyComponent.getSignature().equals(assemblyComponent.getSignature()) && ModelRepositoryMerger
                .isEqual(mergeAssemblyComponent.getComponentType(), assemblyComponent.getComponentType());
    }

    private static boolean isEqual(final ComponentType mergeComponentType, final ComponentType componentType) {
        return mergeComponentType.getSignature().equals(componentType.getSignature());
    }

    private static boolean isEqual(final DeployedComponent mergeComponent, final DeployedComponent component) {
        return ModelRepositoryMerger.isEqual(mergeComponent.getSignature(), component.getSignature())
                && ModelRepositoryMerger.isEqual(mergeComponent.getDeploymentContext(),
                        component.getDeploymentContext());
    }

    private static boolean isEqual(final String mergeSignature, final String signature) {
        if (mergeSignature == null) {
            return signature == null;
        } else {
            if (signature == null) {
                return false;
            } else {
                return mergeSignature.equals(signature);
            }
        }
    }

    private static boolean isEqual(final DeploymentContext mergeDeploymentContext,
            final DeploymentContext deploymentContext) {
        return mergeDeploymentContext.getName().equals(deploymentContext.getName());
    }
}
