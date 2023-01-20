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

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapBasedSignatureProcessorTest {

    private static final String OPERATION = "doSomething()";
    private static final String COMPONENT = "SpecialComponent";
    private static final String SPECIAL_FILE_NAME = "specialFileName";
    private static final String PATH = "a/b/" + MapBasedSignatureProcessorTest.SPECIAL_FILE_NAME;
    private static final List<Path> FILE_LIST = new ArrayList<>();
    private static File file;

    @BeforeAll
    public static void createTempfile() throws IOException {
        MapBasedSignatureProcessorTest.file = File.createTempFile("MapBasedSignatureProcessor", "");
        final BufferedWriter writer = Files.newBufferedWriter(MapBasedSignatureProcessorTest.file.toPath());
        writer.write(String.format("%s;%s;%s\n", MapBasedSignatureProcessorTest.COMPONENT,
                MapBasedSignatureProcessorTest.SPECIAL_FILE_NAME, MapBasedSignatureProcessorTest.OPERATION));
        writer.close();

        MapBasedSignatureProcessorTest.FILE_LIST.add(MapBasedSignatureProcessorTest.file.toPath());
    }

    @AfterAll
    public static void removeTempfile() {
        MapBasedSignatureProcessorTest.file.delete();
    }

    @Test
    public void testCaseSensitive() throws IOException { // NOPMD Assertions
        final MapBasedSignatureProcessor processor = new MapBasedSignatureProcessor(
                MapBasedSignatureProcessorTest.FILE_LIST, false, ";");
        final boolean result = processor.processSignatures(MapBasedSignatureProcessorTest.PATH,
                MapBasedSignatureProcessorTest.COMPONENT, MapBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(), MapBasedSignatureProcessorTest.COMPONENT,
                "component name should not be lower case");
        Assertions.assertEquals(processor.getOperationSignature(), MapBasedSignatureProcessorTest.OPERATION,
                "operation name should not be lower case");
    }

    @Test
    public void testCaseInsensitive() throws IOException { // NOPMD Assertions
        final MapBasedSignatureProcessor processor = new MapBasedSignatureProcessor(
                MapBasedSignatureProcessorTest.FILE_LIST, true, ";");
        final boolean result = processor.processSignatures(MapBasedSignatureProcessorTest.PATH,
                MapBasedSignatureProcessorTest.COMPONENT, MapBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(),
                MapBasedSignatureProcessorTest.COMPONENT.toLowerCase(Locale.getDefault()),
                "component name should not be lower case");
        Assertions.assertEquals(processor.getOperationSignature(),
                MapBasedSignatureProcessorTest.OPERATION.toLowerCase(Locale.getDefault()),
                "operation name should not be lower case");
    }

}
