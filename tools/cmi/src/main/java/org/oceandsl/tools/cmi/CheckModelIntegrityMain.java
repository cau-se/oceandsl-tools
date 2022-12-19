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

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.type.TypePackage;

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
        for (final Entry<EClass, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain.configureModels()
                .entrySet()) {
            errors += CheckModelIntegrityMain.checkForSourcesForAllModelElements(
                    modelConfig.getKey().getInstanceTypeName(),
                    repository.getModel(SourcePackage.Literals.SOURCE_MODEL),
                    repository.getModel(repository.getModelDescriptor(modelConfig.getKey()).getRootClass())
                            .eAllContents(),
                    modelConfig.getValue());
        }
        System.out.printf("Number of missing source labels %s\n", errors); // NOPMD

        errors = CheckModelIntegrityMain.checkForSourcesWihtoutModelElements(repository);
        System.out.printf("Number of missing references to sources %s\n", errors); // NOPMD

        errors = 0;
        for (final Entry<EClass, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain.configureModels()
                .entrySet()) {
            errors += CheckModelIntegrityMain.checkReferences(modelConfig.getKey(),
                    repository.getModel(modelConfig.getKey()).eAllContents());
        }

        System.out.printf("Missing references %s\n", errors); // NOPMD

        errors = 0;
        for (final Entry<EClass, List<Class<? extends EObject>>> modelConfig : CheckModelIntegrityMain.configureModels()
                .entrySet()) {
            errors += CheckModelIntegrityMain
                    .missingSignature(repository.getModel(modelConfig.getKey()).eAllContents());
        }
        System.out.printf("Missing signature %s\n", errors); // NOPMD

        // CheckModelIntegrityMain.checkForDuplicateDeployedOperations(repository.getModel(DeploymentModel.class));

        CheckModelIntegrityMain
                .checkExecutionInvocationIntegrity(repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));
        CheckModelIntegrityMain
                .checkExecutionStorageDataflowIntegrity(repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));
        CheckModelIntegrityMain.checkExecutionOperationDataflowIntegrity(
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));
        CheckModelIntegrityMain
                .checkForDuplicateInvocations(repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL));
    }

    // private static void checkForDuplicateDeployedOperations(final DeploymentModel model) {
    // for (final DeploymentContext context : model.getDeploymentContexts().values()) {
    // for (final DeployedComponent component : context.getComponents().values()) {
    // component.getContainedOperations().keySet()
    // }
    // }
    // }

    private static void checkForDuplicateInvocations(final ExecutionModel model) {
        System.out.println("Check for duplicate invocations based on DeployedOperation"); // NOPMD
        final Map<DeployedOperation, Map<DeployedOperation, Invocation>> map = new HashMap<>();
        for (final Invocation invocation : model.getInvocations().values()) {
            Map<DeployedOperation, Invocation> targetMap = map.get(invocation.getCaller());
            if (targetMap == null) {
                targetMap = new HashMap<>();
                targetMap.put(invocation.getCallee(), invocation);
            } else if (targetMap.get(invocation.getCallee()) != null) {
                System.out.printf("Found duplicate %s -> %s\n", // NOPMD
                        invocation.getCaller().getAssemblyOperation().getOperationType().getName(),
                        invocation.getCallee().getAssemblyOperation().getOperationType().getName());
            }
        }

        System.out.println("Check for duplicate invocations based on DeployedOperation names"); // NOPMD
        final List<String> l = new ArrayList<>();
        for (final Invocation invocation : model.getInvocations().values()) {
            final String m = String.format("%s:%s:%s -> %s:%s:%s", // NOPMD
                    invocation.getCaller().getComponent().getContext().getName(),
                    invocation.getCaller().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getCaller().getAssemblyOperation().getOperationType().getSignature(),
                    invocation.getCallee().getComponent().getContext().getName(),
                    invocation.getCallee().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getCallee().getAssemblyOperation().getOperationType().getSignature());
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
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, Invocation> entry : model.getInvocations()) {
            final Tuple<DeployedOperation, DeployedOperation> tuple = entry.getKey();
            final Invocation invocation = entry.getValue();
            if (tuple.getFirst() != invocation.getCaller()) {
                System.out.printf("Caller does not match lookup key %s ++ %s\n", // NOPMD
                        RepositoryUtils.getName(tuple.getFirst()), RepositoryUtils.getName(invocation.getCaller()));

                errors++;
            }
            if (tuple.getSecond() != invocation.getCallee()) {
                System.out.printf("Callee does not match lookup key %s ++ %s\n", // NOPMD
                        RepositoryUtils.getName(tuple.getSecond()), RepositoryUtils.getName(invocation.getCallee()));

                final DeployedComponent keyComponent = tuple.getSecond().getComponent();
                final DeployedComponent targetComponent = invocation.getCallee().getComponent();
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

    private static void checkExecutionOperationDataflowIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, OperationDataflow> entry : model
                .getOperationDataflows()) {
            final Tuple<DeployedOperation, DeployedOperation> tuple = entry.getKey();
            final OperationDataflow operationDataflow = entry.getValue();
            if (tuple.getFirst() != operationDataflow.getCaller()) {
                System.out.printf("Caller does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(operationDataflow.getCaller().getComponent()),
                        RepositoryUtils.getName(operationDataflow.getCaller()));
                errors++;
            }
            if (tuple.getSecond() != operationDataflow.getCallee()) {
                System.out.printf("Storage does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(operationDataflow.getCallee().getComponent()),
                        RepositoryUtils.getName(operationDataflow.getCallee()));
                errors++;
            }
        }
        System.out.printf("Number of errors in execution model operation dataflows %d\n", errors); // NOPMD
    }

    private static void checkExecutionStorageDataflowIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, StorageDataflow> entry : model
                .getStorageDataflows()) {
            final Tuple<DeployedOperation, DeployedStorage> tuple = entry.getKey();
            final StorageDataflow storageDataflow = entry.getValue();
            if (tuple.getFirst() != storageDataflow.getCode()) {
                System.out.printf("Caller does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(storageDataflow.getCode().getComponent()),
                        RepositoryUtils.getName(storageDataflow.getCode()));
                errors++;
            }
            if (tuple.getSecond() != storageDataflow.getStorage()) {
                System.out.printf("Storage does not match %s:%s\n", // NOPMD
                        RepositoryUtils.getName(storageDataflow.getStorage().getComponent()),
                        RepositoryUtils.getName(storageDataflow.getStorage()));
                errors++;
            }
        }
        System.out.printf("Number of errors in execution model storage dataflows %d\n", errors); // NOPMD
    }

    private static long checkForSourcesWihtoutModelElements(final ModelRepository repository) {
        final SourceModel sourceModel = repository.getModel(SourcePackage.Literals.SOURCE_MODEL);
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
        for (final EClass modelRootClass : CheckModelIntegrityMain.configureModels().keySet()) {
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

    private static long checkReferences(final EClass modelClass, final TreeIterator<EObject> iterator) {
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

    private static Map<EClass, List<Class<? extends EObject>>> configureModels() {
        final Map<EClass, List<Class<? extends EObject>>> modelConfigs = new HashMap<>();
        modelConfigs.put(TypePackage.Literals.TYPE_MODEL, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(AssemblyPackage.Literals.ASSEMBLY_MODEL, new ArrayList<Class<? extends EObject>>());
        modelConfigs.put(DeploymentPackage.Literals.DEPLOYMENT_MODEL, new ArrayList<Class<? extends EObject>>());
        final List<Class<? extends EObject>> executionIgnoreList = new ArrayList<>();
        executionIgnoreList.add(Tuple.class);
        modelConfigs.put(ExecutionPackage.Literals.EXECUTION_MODEL, executionIgnoreList);

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
