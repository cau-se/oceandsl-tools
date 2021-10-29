package org.oceandsl.tools.sar.stages;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.oceandsl.analysis.stages.staticdata.data.CallerCallee;
import org.oceandsl.analysis.stages.staticdata.data.ValueConversionErrorException;

import teetime.framework.test.StageTester;

public class FileBasedCleanupComponentSignatureStageTest {

    private static final String SOURCE_PATH = "source/path/First";
    private static final String CALLER = "Caller()";
    private static final String TARGET_PATH = "target/path/Second";
    private static final String CALLEE = "Callee()";

    @Test
    public void testConversionWithLowerCase() throws IOException, ValueConversionErrorException {
        final FileBasedCleanupComponentSignatureStage stage = new FileBasedCleanupComponentSignatureStage(true);

        final CallerCallee callerCallee = new CallerCallee(FileBasedCleanupComponentSignatureStageTest.SOURCE_PATH,
                FileBasedCleanupComponentSignatureStageTest.CALLER,
                FileBasedCleanupComponentSignatureStageTest.TARGET_PATH,
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        final CallerCallee resultCallerCallee = new CallerCallee("first",
                FileBasedCleanupComponentSignatureStageTest.CALLER, "second",
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        StageTester.test(stage).and().send(callerCallee).to(stage.getInputPort()).start();

        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(resultCallerCallee));
    }

    @Test
    public void testConversionWithoutLowerCase() throws IOException, ValueConversionErrorException {
        final FileBasedCleanupComponentSignatureStage stage = new FileBasedCleanupComponentSignatureStage(false);

        final CallerCallee callerCallee = new CallerCallee(FileBasedCleanupComponentSignatureStageTest.SOURCE_PATH,
                FileBasedCleanupComponentSignatureStageTest.CALLER,
                FileBasedCleanupComponentSignatureStageTest.TARGET_PATH,
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        final CallerCallee resultCallerCallee = new CallerCallee("First",
                FileBasedCleanupComponentSignatureStageTest.CALLER, "Second",
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        StageTester.test(stage).and().send(callerCallee).to(stage.getInputPort()).start();

        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(resultCallerCallee));
    }

}
