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
package org.oceandsl.tools.sar.stages;

import java.time.Duration;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import kieker.analysis.architecture.recovery.data.CallEvent;
import kieker.analysis.architecture.recovery.data.OperationEvent;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.code.stages.data.CallerCallee;

/**
 * Tesintg operation and call data stage..
 *
 * @author Reiner Jung
 * @since 1.3
 */
public class OperationAndCall4StaticDataStageTest {

    private static final String HOSTNAME = "test";
    private static final String SOURCE_PATH = "source/path";
    private static final String CALLER = "caller()";
    private static final String TARGET_PATH = "target/path";
    private static final String CALLEE = "callee()";

    @Test
    public void testExecuteCallerCallee() {
        final OperationAndCall4StaticDataStage stage = new OperationAndCall4StaticDataStage(
                OperationAndCall4StaticDataStageTest.HOSTNAME);

        final CallerCallee callerCallee = new CallerCallee(OperationAndCall4StaticDataStageTest.SOURCE_PATH,
                OperationAndCall4StaticDataStageTest.CALLER, OperationAndCall4StaticDataStageTest.TARGET_PATH,
                OperationAndCall4StaticDataStageTest.CALLEE);
        final OperationEvent firstOp = new OperationEvent(OperationAndCall4StaticDataStageTest.HOSTNAME,
                OperationAndCall4StaticDataStageTest.SOURCE_PATH, OperationAndCall4StaticDataStageTest.CALLER);
        final OperationEvent secondOp = new OperationEvent(OperationAndCall4StaticDataStageTest.HOSTNAME,
                OperationAndCall4StaticDataStageTest.TARGET_PATH, OperationAndCall4StaticDataStageTest.CALLEE);

        final CallEvent call = new CallEvent(firstOp, secondOp, Duration.ZERO);

        StageTester.test(stage).and().send(callerCallee).to(stage.getInputPort()).start();

        MatcherAssert.assertThat(stage.getOperationOutputPort(), StageTester.produces(firstOp, secondOp));
        MatcherAssert.assertThat(stage.getCallOutputPort(), StageTester.produces(call));
    }

}
