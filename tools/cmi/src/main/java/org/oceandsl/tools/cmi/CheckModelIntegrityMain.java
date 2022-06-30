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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import kieker.analysis.architecture.recovery.ModelRepository;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.TypeModel;

import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;
import org.oceandsl.analysis.architecture.RepositoryUtils;

/**
 * Checks the integrity of architecture models.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public final class CheckModelIntegrityMain {

    private CheckModelIntegrityMain() {
        // nothing to do here
    }

    public static void main(final String[] args) throws ConfigurationException {
        final Path path = Paths.get(args[0]);
        final ModelRepository repository = ArchitectureModelManagementUtils.loadModelRepository(path);
        // RepositoryUtils.print(repository);

        long errors = 0;
        for (final Entry<Class<? extends EObject>, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain
                .configureModels().entrySet()) {
            errors += CheckModelIntegrityMain.checkForSourcesForAllModelElements(
                    modelConfig.getKey().getCanonicalName(), repository.getModel(SourceModel.class),
                    repository.getModel(modelConfig.getKey()).eAllContents(), modelConfig.getValue());
        }
        System.out.printf("Number of missing source labels %s\n", errors); // NOPMD

        errors = CheckModelIntegrityMain.checkForSourcesWihtoutModelElements(repository);
        System.out.printf("Number of missing references to sources %s\n", errors); // NOPMD

        errors = 0;
        for (final Entry<Class<? extends EObject>, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain
                .configureModels().entrySet()) {
            errors += CheckModelIntegrityMain.checkReferences(modelConfig.getKey(),
                    repository.getModel(modelConfig.getKey()).eAllContents());
        }

        System.out.printf("Missing references %s\n", errors); // NOPMD

        errors = 0;
        for (final Entry<Class<? extends EObject>, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain
                .configureModels().entrySet()) {
            errors += CheckModelIntegrityMain
                    .missingSignature(repository.getModel(modelConfig.getKey()).eAllContents());
        }
        System.out.printf("Missing signature %s\n", errors); // NOPMD

        // CheckModelIntegrityMain.checkForDuplicateDeployedOperations(repository.getModel(DeploymentModel.class));

        CheckModelIntegrityMain.checkExecutionInvocationIntegrity(repository.getModel(ExecutionModel.class));
        CheckModelIntegrityMain.checkExecutionStorageAccessIntegrity(repository.getModel(ExecutionModel.class));
        CheckModelIntegrityMain.checkForDuplicateInvocations(repository.getModel(ExecutionModel.class));
    }

//    private static void checkForDuplicateDeployedOperations(final DeploymentModel model) {
//        for (final DeploymentContext context : model.getDeploymentContexts().values()) {
//            for (final DeployedComponent component : context.getComponents().values()) {
//                component.getContainedOperations().keySet()
//            }
//        }
//    }

    private static void checkForDuplicateInvocations(final ExecutionModel model) {
        System.out.println("Check for duplicate invocations based on DeployedOperation"); // NOPMD
        final Map<DeployedOperation, Map<DeployedOperation, AggregatedInvocation>> map = new HashMap<>();
        for (final AggregatedInvocation invocation : model.getAggregatedInvocations().values()) {
            Map<DeployedOperation, AggregatedInvocation> targetMap = map.get(invocation.getSource());
            if (targetMap == null) {
                targetMap = new HashMap<>();
                targetMap.put(invocation.getTarget(), invocation);
            } else {
                if (targetMap.get(invocation.getTarget()) != null) {
                    System.out.printf("Found duplicate %s -> %s\n", // NOPMD
                            invocation.getSource().getAssemblyOperation().getOperationType().getName(),
                            invocation.getTarget().getAssemblyOperation().getOperationType().getName());
                }
            }
        }

        System.out.println("Check for duplicate invocations based on DeployedOperation names"); // NOPMD
        final List<String> l = new ArrayList<>();
        for (final AggregatedInvocation invocation : model.getAggregatedInvocations().values()) {
            final String m = String.format("%s:%s:%s -> %s:%s:%s", // NOPMD
                    invocation.getSource().getComponent().getContext().getName(),
                    invocation.getSource().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getSource().getAssemblyOperation().getOperationType().getSignature(),
                    invocation.getTarget().getComponent().getContext().getName(),
                    invocation.getTarget().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getTarget().getAssemblyOperation().getOperationType().getSignature());
            boolean g = false;
            for (final String x : l) {
                if (x.equals(m)) {
                    System.out.printf("Found duplicate %s\n", m); // NOPMD
                    g = true;
                }
            }
            if (!g) {
                l.add(m);
            }
        }
    }

    private static void checkExecutionInvocationIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> entry : model
                .getAggregatedInvocations()) {
            final Tuple<DeployedOperation, DeployedOperation> tuple = entry.getKey();
            final AggregatedInvocation invocation = entry.getValue();
            if (tuple.getFirst() != invocation.getSource()) {
                System.out.printf("Caller does not match lookup key %s ++ %s\n", // NOPMD
                        RepositoryUtils.getName(tuple.getFirst()), RepositoryUtils.getName(invocation.getSource()));

                errors++;
            }
            if (tuple.getSecond() != invocation.getTarget()) {
                System.out.printf("Callee does not match lookup key %s ++ %s\n", // NOPMD
                        RepositoryUtils.getName(tuple.getSecond()), RepositoryUtils.getName(invocation.getTarget()));

                final DeployedComponent keyComponent = tuple.getSecond().getComponent();
                final DeployedComponent targetComponent = invocation.getTarget().getComponent();
                if (keyComponent != targetComponent) { // NOPMD objects must not be identical
                    System.out.printf("Callee component does not match lookup key component %s ++ %s\n", // NOPMD
                            RepositoryUtils.getName(keyComponent), RepositoryUtils.getName(targetComponent));
                    final DeploymentContext keyContext = keyComponent.getContext();
                    final DeploymentContext targetContext = targetComponent.getContext();
                    if (keyContext != targetContext) { // NOPMD objects must not be identical
                        System.out.printf("Callee context does not match lookup key context %s ++ %s\n", // NOPMD
                                RepositoryUtils.getName(keyContext), RepositoryUtils.getName(targetContext));
                        if (keyContext.eContainer() != targetContext.eContainer()) { // NOPMD
                            System.out.printf("Duplicate deployment models: %s ++ %s\n", // NOPMD
                                    keyContext.eResource().getURI(), targetContext.eResource().getURI());
                        }
                    }
                }

                errors++;
            }
        }
        System.out.printf("Number of errors in execution model invocations %d\n", errors); // NOPMD
    }

    private static void checkExecutionStorageAccessIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> entry : model
                .getAggregatedStorageAccesses()) {
            final Tuple<DeployedOperation, DeployedStorage> tuple = entry.getKey();
            final AggregatedStorageAccess storageAccesss = entry.getValue();
            if (tuple.getFirst() != storageAccesss.getCode()) {
                System.out.printf("Caller does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(storageAccesss.getCode().getComponent()),
                        RepositoryUtils.getName(storageAccesss.getCode()));
                errors++;
            }
            if (tuple.getSecond() != storageAccesss.getStorage()) {
                System.out.printf("Storage does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(storageAccesss.getStorage().getComponent()),
                        RepositoryUtils.getName(storageAccesss.getStorage()));
                errors++;
            }
        }
        System.out.printf("Number of errors in execution model storage accesses %d\n", errors); // NOPMD
    }

    private static long checkForSourcesWihtoutModelElements(final ModelRepository repository) {
        final SourceModel sourceModel = repository.getModel(SourceModel.class);
        long errors = 0;
        for (final EObject element : sourceModel.getSources().keySet()) {
            element.eCrossReferences();
            if (!CheckModelIntegrityMain.existsObjectForSource(element, repository)) {
                errors++;
            }
        }

        return errors;
    }

    private static boolean existsObjectForSource(final EObject element, final ModelRepository repository) {
        for (final Class<? extends EObject> modelRootClass : CheckModelIntegrityMain.configureModels().keySet()) {
            final EObject model = repository.getModel(modelRootClass);
            if (CheckModelIntegrityMain.modelContains(model, element)) {
                return true;
            }
        }
        System.out.printf("Object %s not found.\n", RepositoryUtils.getName(element)); // NOPMD
        return false;
    }

    private static boolean modelContains(final EObject model, final EObject element) {
        final TreeIterator<EObject> iterator = model.eAllContents();
        while (iterator.hasNext()) {
            final EObject modelElement = iterator.next();
            if (modelElement.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private static long missingSignature(final TreeIterator<EObject> iterator) {
        long errorCount = 0;
        while (iterator.hasNext()) {
            final EObject object = iterator.next();
            final EClass clazz = object.eClass();
            for (final EAttribute attribute : clazz.getEAllAttributes()) {
                if ("signature".equals(attribute.getName())) {
                    final Object value = object.eGet(attribute);
                    if (value == null) {
                        System.out.printf("Object %s of type %s has signature attribute, but no value\n", // NOPMD
                                CheckModelIntegrityMain.print(object), clazz.getName());
                        errorCount++;
                    }
                }
            }
        }
        return errorCount;
    }

    private static String print(final EObject object) {
        final EClass clazz = object.eClass();
        String result = "class " + clazz.getName();
        for (final EAttribute attribute : clazz.getEAllAttributes()) {
            result += " " + attribute.getName() + "=" + object.eGet(attribute);
        }
        for (final EReference reference : clazz.getEAllReferences()) {
            result += " " + reference.getEReferenceType().getName() + ":" + reference.getName() + "="
                    + object.eGet(reference);
        }
        return result;
    }

    private static long checkReferences(final Class<? extends EObject> modelClass,
            final TreeIterator<EObject> iterator) {
        System.out.printf("Model %s\n", modelClass.getName()); // NOPMD
        long errorCount = 0;
        while (iterator.hasNext()) {
            final EObject object = iterator.next();
            final EClass clazz = object.eClass();
            for (final EReference reference : clazz.getEAllReferences()) {
                final Object referencedObject = object.eGet(reference, true);
                if (referencedObject == null) {
                    System.out.printf("Missing referenced object: %s:%s -> %s:%s\n", // NOPMD
                            object.getClass().getSimpleName(), RepositoryUtils.getName(object),
                            reference.getEType().getName(), reference.getName());
                    errorCount++;

                }
            }
        }
        return errorCount;
    }

    private static Map<Class<? extends EObject>, List<Class<? extends EObject>>> configureModels() {
        final Map<Class<? extends EObject>, List<Class<? extends EObject>>> modelConfigs = new HashMap<>();
        modelConfigs.put(TypeModel.class, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(AssemblyModel.class, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(DeploymentModel.class, new ArrayList<Class<? extends EObject>>());
        final List<Class<? extends EObject>> executionIgnoreList = new ArrayList<>();
        executionIgnoreList.add(Tuple.class);
        modelConfigs.put(ExecutionModel.class, executionIgnoreList);
        return modelConfigs;
    }

    private static long checkForSourcesForAllModelElements(final String modelName, final SourceModel model,
            final TreeIterator<EObject> treeIterator, final List<Class<? extends EObject>> ignoreList) {
        System.out.printf("Source model entries %d\n", model.getSources().size()); // NOPMD
        long errorCount = 0;
        long objectCount = 0;
        while (treeIterator.hasNext()) {
            final EObject object = treeIterator.next();
            if (!(object instanceof BasicEMap.Entry)) {
                if (!CheckModelIntegrityMain.isOnIgnoreList(ignoreList, object)) { // NOPMD
                    objectCount++;
                    if (model.getSources().get(object) == null) {
                        System.out.println("Missing source reference for"); // NOPMD
                        RepositoryUtils.print(object, "  ");
                        System.out.println("----"); // NOPMD
                        errorCount++;
                    }
                }
            }
        }

        System.out.printf("Objects in model %s %d\n", modelName, objectCount); // NOPMD
        return errorCount;
    }

    private static boolean isOnIgnoreList(final List<Class<? extends EObject>> ignoreList, final EObject object) {
        for (final Class<? extends EObject> element : ignoreList) {
            if (element.isAssignableFrom(object.getClass())) {
                return true;
            }
        }
        return false;
    }
}
