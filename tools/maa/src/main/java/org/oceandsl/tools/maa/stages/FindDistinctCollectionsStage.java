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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.util.Tuple;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.collection.Connections;
import kieker.model.collection.OperationCollection;

import teetime.stage.basic.AbstractTransformation;

/**
 * The stage receives a repository with a set of large collections of provided operations which may
 * have overlapping operations. The task of this stage is to identify a set of unique collections
 * where all operations of each collection are accessed by the same source.
 *
 * <ol>
 * <li>Make a list of all provided operations per target</li>
 * <li>for each operation identify all callers</li>
 * <li>Group all operations per target with the same callers (these are the interfaces)</li>
 * </ol>
 *
 * @author Reiner Jung
 * @since 1.3
 */
public class FindDistinctCollectionsStage extends
        AbstractTransformation<ModelRepository, Tuple<ModelRepository, Map<ComponentType, Map<Set<ComponentType>, Set<OperationType>>>>> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final Connections connections = repository.getModel(Connections.class);

        final Map<ComponentType, Set<OperationType>> targetOperationsMap = this.createTargetOperationsMap(connections);
        final Map<OperationType, Set<ComponentType>> operationCallerSetsMap = this
                .createOperationCallerSetsMap(connections);
        final Map<ComponentType, Map<Set<ComponentType>, Set<OperationType>>> protointerfaceSourceGroupedOperations = this
                .groupOperationsByCallerSet(targetOperationsMap, operationCallerSetsMap);

        this.outputPort.send(new Tuple<>(repository, protointerfaceSourceGroupedOperations));
    }

    private Map<ComponentType, Map<Set<ComponentType>, Set<OperationType>>> groupOperationsByCallerSet(
            final Map<ComponentType, Set<OperationType>> targetOperationsMap,
            final Map<OperationType, Set<ComponentType>> operationCallerSetsMap) {
        final Map<ComponentType, Map<Set<ComponentType>, Set<OperationType>>> protointerfaceSourceGroupedOperations = new HashMap<>();
        targetOperationsMap.entrySet().forEach(entry -> protointerfaceSourceGroupedOperations.put(entry.getKey(),
                this.createSourceGroupedOperations(entry.getValue(), operationCallerSetsMap)));
        return protointerfaceSourceGroupedOperations;
    }

    /**
     * Find all operations that have the same source set of ComponentType.
     *
     * @param targetOperations
     *            set containing all relevant operations
     * @param operationCallerSetsMap
     *            map containing operations to ComponentType set.
     * @return map containing ComponentType source sets with their OperationTypes
     */
    private Map<Set<ComponentType>, Set<OperationType>> createSourceGroupedOperations(
            final Set<OperationType> targetOperations,
            final Map<OperationType, Set<ComponentType>> operationCallerSetsMap) {
        final Map<Set<ComponentType>, Set<OperationType>> sourceGroupedOperations = new HashMap<>();
        targetOperations.forEach(targetOperation -> {
            final Set<ComponentType> sourceSet = operationCallerSetsMap.get(targetOperation);
            final Set<OperationType> operationTypeSet = this.findOperationSetBySourceSet(sourceSet,
                    sourceGroupedOperations);
            operationTypeSet.add(targetOperation);
        });

        return sourceGroupedOperations;
    }

    private Set<OperationType> findOperationSetBySourceSet(final Set<ComponentType> sourceSet,
            final Map<Set<ComponentType>, Set<OperationType>> sourceGroupedOperations) {
        final Optional<Entry<Set<ComponentType>, Set<OperationType>>> element = sourceGroupedOperations.entrySet()
                .stream().filter(entry -> this.compareSets(entry.getKey(), sourceSet)).findFirst();
        if (element.isEmpty()) {
            final Set<OperationType> operationTypeSet = new HashSet<>();
            sourceGroupedOperations.put(sourceSet, operationTypeSet);
            return operationTypeSet;
        } else {
            return element.get().getValue();
        }
    }

    private Map<OperationType, Set<ComponentType>> createOperationCallerSetsMap(final Connections connections) {
        final Map<OperationType, Set<ComponentType>> operationCallerSetsMap = new HashMap<>();
        for (final OperationCollection connection : connections.getConnections().values()) {
            for (final OperationType operation : connection.getOperations().values()) {
                Set<ComponentType> callerSet = operationCallerSetsMap.get(operation);
                if (callerSet == null) {
                    callerSet = new HashSet<>();
                    operationCallerSetsMap.put(operation, callerSet);
                }
                callerSet.add(connection.getCaller());
            }
        }
        return operationCallerSetsMap;
    }

    private Map<ComponentType, Set<OperationType>> createTargetOperationsMap(final Connections connections) {
        final Map<ComponentType, Set<OperationType>> targetOperationMap = new HashMap<>();
        for (final OperationCollection connection : connections.getConnections().values()) {
            for (final OperationType operation : connection.getOperations().values()) {
                Set<OperationType> operationSet = targetOperationMap.get(connection.getCallee());
                if (operationSet == null) {
                    operationSet = new HashSet<>();
                    targetOperationMap.put(connection.getCallee(), operationSet);
                }
                operationSet.add(operation);
            }
        }
        return targetOperationMap;
    }

    private boolean compareSets(final Set<ComponentType> existingSet, final Set<ComponentType> callerSet) {
        if (existingSet.size() == callerSet.size()) {
            return existingSet.stream().anyMatch(existingComponentType -> callerSet.contains(existingComponentType));
        } else {
            return false;
        }
    }

}
