package org.oceandsl.tools.sar.signature.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapBasedSignatureProcessorTest {

    private static final String OPERATION = "doSomething()";
    private static final String COMPONENT = "SpecialComponent";
    private static final String SPECIAL_FILE_NAME = "specialFileName";
    private static final String PATH = "a/b/" + MapBasedSignatureProcessorTest.SPECIAL_FILE_NAME;
    private static final List<Path> fileList = new ArrayList<>();
    private static File file;

    @BeforeAll
    public static void createTempfile() throws IOException {
        MapBasedSignatureProcessorTest.file = File.createTempFile("MapBasedSignatureProcessor", "");
        final BufferedWriter writer = Files.newBufferedWriter(MapBasedSignatureProcessorTest.file.toPath());
        writer.write(String.format("%s;%s;%s\n", MapBasedSignatureProcessorTest.COMPONENT,
                MapBasedSignatureProcessorTest.SPECIAL_FILE_NAME, MapBasedSignatureProcessorTest.OPERATION));
        writer.close();

        MapBasedSignatureProcessorTest.fileList.add(MapBasedSignatureProcessorTest.file.toPath());
    }

    @AfterAll
    public static void removeTempfile() {
        MapBasedSignatureProcessorTest.file.delete();
    }

    @Test
    void testCaseSensitive() throws IOException {
        final MapBasedSignatureProcessor processor = new MapBasedSignatureProcessor(
                MapBasedSignatureProcessorTest.fileList, false, ";");
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
    void testCaseInsensitive() throws IOException {
        final MapBasedSignatureProcessor processor = new MapBasedSignatureProcessor(
                MapBasedSignatureProcessorTest.fileList, true, ";");
        final boolean result = processor.processSignatures(MapBasedSignatureProcessorTest.PATH,
                MapBasedSignatureProcessorTest.COMPONENT, MapBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(),
                MapBasedSignatureProcessorTest.COMPONENT.toLowerCase(), "component name should not be lower case");
        Assertions.assertEquals(processor.getOperationSignature(),
                MapBasedSignatureProcessorTest.OPERATION.toLowerCase(), "operation name should not be lower case");
    }

}
