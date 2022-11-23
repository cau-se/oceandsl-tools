package org.oceandsl.tools.sar.signature.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileBasedSignatureProcessorTest {

    private static final String OPERATION = "doSomething()";
    private static final String COMPONENT = "SpecialComponent";
    private static final String SPECIAL_FILE_NAME = "specialFileName";
    private static final String PATH = "a/b/" + FileBasedSignatureProcessorTest.SPECIAL_FILE_NAME;

    @Test
    void testCaseSensitive() {
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
    void testCaseInsensitive() {
        final FileBasedSignatureProcessor processor = new FileBasedSignatureProcessor(true);
        final boolean result = processor.processSignatures(FileBasedSignatureProcessorTest.PATH,
                FileBasedSignatureProcessorTest.COMPONENT, FileBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(),
                FileBasedSignatureProcessorTest.SPECIAL_FILE_NAME.toLowerCase(), "component name should be lower case");
        Assertions.assertEquals(processor.getOperationSignature(),
                FileBasedSignatureProcessorTest.OPERATION.toLowerCase(), "operation name should be lower case");
    }

}
