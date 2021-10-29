package org.oceandsl.tools.sar.stages;

import java.time.Duration;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import kieker.analysis.stage.model.data.CallEvent;
import kieker.analysis.stage.model.data.OperationEvent;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.stages.staticdata.data.CallerCallee;

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
