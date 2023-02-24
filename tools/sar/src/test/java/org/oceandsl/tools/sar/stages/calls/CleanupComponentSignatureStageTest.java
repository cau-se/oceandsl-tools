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
package org.oceandsl.tools.sar.stages.calls;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.code.stages.data.CallerCallee;
import org.oceandsl.tools.sar.signature.processor.AbstractSignatureProcessor;

class CleanupComponentSignatureStageTest {

    private static final String SOURCE_PATH = "a/b/file1";
    private static final String SOURCE_MODULE = "source-module";
    private static final String SOURCE_SIGNATURE = "Type operation (Type, Type, Type) exceptions E1,E2";
    private static final String TARGET_PATH = "a/b/file2";
    private static final String TARGET_MODULE = "target-module";
    private static final String TARGET_SIGNATURE = "void operation ()";

    @Test
    public void test() {
        final List<AbstractSignatureProcessor> processors = new ArrayList<>();
        processors.add(new AbstractSignatureProcessor(false) {

            @Override
            public boolean processSignatures(final String path, final String componentSignature,
                    final String elementSignature) {
                this.componentSignature = path + "/" + componentSignature;
                this.elementSignature = elementSignature;
                return true;
            }

            @Override
            public String getErrorMessage() {
                return "error";
            }
        });

        final CleanupComponentSignatureStage stage = new CleanupComponentSignatureStage(processors);
        final CallerCallee sourceCall = new CallerCallee(CleanupComponentSignatureStageTest.SOURCE_PATH,
                CleanupComponentSignatureStageTest.SOURCE_MODULE, CleanupComponentSignatureStageTest.SOURCE_SIGNATURE,
                CleanupComponentSignatureStageTest.TARGET_PATH, CleanupComponentSignatureStageTest.TARGET_MODULE,
                CleanupComponentSignatureStageTest.TARGET_SIGNATURE);
        final CallerCallee resultCall = new CallerCallee(CleanupComponentSignatureStageTest.SOURCE_PATH,
                CleanupComponentSignatureStageTest.SOURCE_PATH + "/" + CleanupComponentSignatureStageTest.SOURCE_MODULE,
                CleanupComponentSignatureStageTest.SOURCE_SIGNATURE, CleanupComponentSignatureStageTest.TARGET_PATH,
                CleanupComponentSignatureStageTest.TARGET_PATH + "/" + CleanupComponentSignatureStageTest.TARGET_MODULE,
                CleanupComponentSignatureStageTest.TARGET_SIGNATURE);

        StageTester.test(stage).and().send(sourceCall).to(stage.getInputPort()).start();
        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(resultCall));
    }

}
