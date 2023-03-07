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
package org.oceandsl.analysis.graph.selector;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.exception.InternalErrorException;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.type.TypeFactory;

import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;

class IntersectSelectorTest {

    private static final String EXP_1_D = "exp1-dynamic";
    private static final String EXP_2_D = "exp2-dynamic";
    private static final String EXP_1_S = "exp1-static";
    private static final String EXP_2_S = "exp2-static";

    private final String[] groupA = { EXP_1_D };
    private final String[] groupB = { EXP_1_S };

    private final EObject object1d = TypeFactory.eINSTANCE.createOperationType();
    private final EObject object2d = TypeFactory.eINSTANCE.createOperationType();
    private final EObject object1d1s = TypeFactory.eINSTANCE.createOperationType();
    private final EObject object2s = TypeFactory.eINSTANCE.createOperationType();

    private final Invocation invocation = ExecutionFactory.eINSTANCE.createInvocation();
    private final OperationDataflow operationDataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
    private final StorageDataflow storageDataflow = ExecutionFactory.eINSTANCE.createStorageDataflow();

    private ModelRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = ArchitectureModelManagementUtils.createModelRepository("test");

        final SourceModel sourceModel = this.repository.getModel(SourcePackage.Literals.SOURCE_MODEL);

        sourceModel.getSources().put(this.object1d, this.list(EXP_1_D));
        sourceModel.getSources().put(this.object2d, this.list(EXP_1_D, EXP_2_D));
        sourceModel.getSources().put(this.object1d1s, this.list(EXP_1_D, EXP_1_S));
        sourceModel.getSources().put(this.object2s, this.list(EXP_1_S, EXP_2_S));

        sourceModel.getSources().put(this.invocation, this.list(EXP_1_D, EXP_2_D));
        sourceModel.getSources().put(this.operationDataflow, this.list(EXP_1_D, EXP_2_D));
        sourceModel.getSources().put(this.storageDataflow, this.list(EXP_1_D, EXP_2_D));
    }

    @Test
    void testNodeSelected() throws InternalErrorException {
        final IntersectSelector selector = new IntersectSelector(this.groupA, this.groupB);

        selector.setRepository(this.repository);

        Assertions.assertFalse(selector.nodeIsSelected(this.object1d), "node must not be selected, only one label");
        Assertions.assertFalse(selector.nodeIsSelected(this.object2d), "node must be selected, both labels present");
        Assertions.assertTrue(selector.nodeIsSelected(this.object1d1s),
                "node must not be selected, only one label matches");
        Assertions.assertFalse(selector.nodeIsSelected(this.object2s), "node must be selected, other group");
    }

    @Test
    void testColorGroupSelected() throws InternalErrorException {
        final IntersectSelector selector = new IntersectSelector(this.groupA, this.groupB);

        selector.setRepository(this.repository);

        Assertions.assertFalse(selector.isColorGroup(this.list(this.groupA), 0), "must not be group 0");
        Assertions.assertFalse(selector.isColorGroup(this.list(EXP_1_D, EXP_2_D, EXP_1_S, EXP_2_S), 0),
                "must be group 0");
        Assertions.assertTrue(selector.isColorGroup(this.list(this.groupA), 1), "must be group 1");
        Assertions.assertTrue(selector.isColorGroup(this.list(this.groupB), 2), "must be group 2");
    }

    @Test
    void testEdgeSelected() throws InternalErrorException {
        final IntersectSelector selector = new IntersectSelector(this.groupA, this.groupB);

        selector.setRepository(this.repository);

        Assertions.assertFalse(selector.edgeIsSelected(this.invocation), "invocation must not be selected");
        Assertions.assertTrue(selector.nodeIsSelected(this.operationDataflow), "operation dataflow must be selected");
        Assertions.assertFalse(selector.nodeIsSelected(this.storageDataflow), "storage dataflow must not be selected");
    }

    @Test
    void testMissingSourceModel() {
        final IntersectSelector selector = new IntersectSelector(this.groupA, this.groupB);
        final ModelRepository localRepository = new ModelRepository("example");
        try {
            selector.setRepository(localRepository);
            Assertions.fail("Missing source model must cause failure.");
        } catch (final InternalErrorException e) {
            // correct behavior
        }
    }

    private EList<String> list(final String... elements) {
        final BasicEList<String> list = new BasicEList<>();
        for (final String element : elements) {
            list.add(element);
        }
        return list;
    }

}
