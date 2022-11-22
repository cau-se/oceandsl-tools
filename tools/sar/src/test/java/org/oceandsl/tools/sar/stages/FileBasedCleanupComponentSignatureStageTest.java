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

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.oceandsl.analysis.code.stages.data.CallerCallee;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;

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
import teetime.framework.test.StageTester;

/**
 * Text File based cleanup.
 *
 * @author Reiner Jung
 * @since 1.3
 */
public class FileBasedCleanupComponentSignatureStageTest {

    private static final String SOURCE_PATH = "source/path/First";
    private static final String SOURCE_MODULE = "sourceModule";
    private static final String CALLER = "Caller()";
    private static final String TARGET_PATH = "target/path/Second";
    private static final String TARGET_MODULE = "targetModule";
    private static final String CALLEE = "Callee()";

    @Test
    public void testConversionWithLowerCase() throws IOException, ValueConversionErrorException {
        final FileBasedCleanupComponentSignatureStage stage = new FileBasedCleanupComponentSignatureStage(true);

        final CallerCallee callerCallee = new CallerCallee(FileBasedCleanupComponentSignatureStageTest.SOURCE_PATH,
                FileBasedCleanupComponentSignatureStageTest.SOURCE_MODULE,
                FileBasedCleanupComponentSignatureStageTest.CALLER,
                FileBasedCleanupComponentSignatureStageTest.TARGET_PATH,
                FileBasedCleanupComponentSignatureStageTest.TARGET_MODULE,
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        final CallerCallee resultCallerCallee = new CallerCallee("first", "firstModule",
                FileBasedCleanupComponentSignatureStageTest.CALLER, "second", "secondModule",
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        StageTester.test(stage).and().send(callerCallee).to(stage.getInputPort()).start();

        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(resultCallerCallee));
    }

    @Test
    public void testConversionWithoutLowerCase() throws IOException, ValueConversionErrorException {
        final FileBasedCleanupComponentSignatureStage stage = new FileBasedCleanupComponentSignatureStage(false);

        final CallerCallee callerCallee = new CallerCallee(FileBasedCleanupComponentSignatureStageTest.SOURCE_PATH,
                FileBasedCleanupComponentSignatureStageTest.SOURCE_MODULE,
                FileBasedCleanupComponentSignatureStageTest.CALLER,
                FileBasedCleanupComponentSignatureStageTest.TARGET_PATH,
                FileBasedCleanupComponentSignatureStageTest.TARGET_MODULE,
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        final CallerCallee resultCallerCallee = new CallerCallee("First", "firstModule",
                FileBasedCleanupComponentSignatureStageTest.CALLER, "Second", "secoundModule",
                FileBasedCleanupComponentSignatureStageTest.CALLEE);

        StageTester.test(stage).and().send(callerCallee).to(stage.getInputPort()).start();

        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(resultCallerCallee));
    }

}
