/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.sar.signature.processor;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileBasedSignatureProcessorTest {

    private static final String OPERATION = "doSomething()";
    private static final String COMPONENT = "SpecialComponent";
    private static final String SPECIAL_FILE_NAME = "specialFileName";
    private static final String PATH = "a/b/" + FileBasedSignatureProcessorTest.SPECIAL_FILE_NAME;

    @Test
    public void testCaseSensitive() { // NOPMD assertions is the assert
        final FileBasedSignatureProcessor processor = new FileBasedSignatureProcessor(false);
        final boolean result = processor.processSignatures(FileBasedSignatureProcessorTest.PATH,
                FileBasedSignatureProcessorTest.COMPONENT, FileBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(), FileBasedSignatureProcessorTest.SPECIAL_FILE_NAME,
                "component name should not be lower case");
        Assertions.assertEquals(processor.getOperationSignature(), FileBasedSignatureProcessorTest.OPERATION,
                "operation name should not be lower case");
    }

    @Test
    public void testCaseInsensitive() { // NOPMD assertions is the assert
        final FileBasedSignatureProcessor processor = new FileBasedSignatureProcessor(true);
        final boolean result = processor.processSignatures(FileBasedSignatureProcessorTest.PATH,
                FileBasedSignatureProcessorTest.COMPONENT, FileBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(),
                FileBasedSignatureProcessorTest.SPECIAL_FILE_NAME.toLowerCase(Locale.getDefault()),
                "component name should be lower case");
        Assertions.assertEquals(processor.getOperationSignature(),
                FileBasedSignatureProcessorTest.OPERATION.toLowerCase(Locale.getDefault()),
                "operation name should be lower case");
    }

}
