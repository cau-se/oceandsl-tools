package org.oceandsl.tools.sar.signature.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModuleBasedSignatureProcessorTest {

    private static final String OPERATION = "doSomething()";
    private static final String COMPONENT = "SpecialComponent";
    private static final String SPECIAL_FILE_NAME = "specialFileName";
    private static final String PATH = "a/b/" + ModuleBasedSignatureProcessorTest.SPECIAL_FILE_NAME;

    @Test
    void testCaseSensitive() {
        final ModuleBasedSignatureProcessor processor = new ModuleBasedSignatureProcessor(false);
        final boolean result = processor.processSignatures(ModuleBasedSignatureProcessorTest.PATH,
                ModuleBasedSignatureProcessorTest.COMPONENT, ModuleBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(), ModuleBasedSignatureProcessorTest.COMPONENT,
                "component name should not be lower case");
        Assertions.assertEquals(processor.getOperationSignature(), ModuleBasedSignatureProcessorTest.OPERATION,
                "operation name should not be lower case");
    }

    @Test
    void testCaseInsensitive() {
        final ModuleBasedSignatureProcessor processor = new ModuleBasedSignatureProcessor(true);
        final boolean result = processor.processSignatures(ModuleBasedSignatureProcessorTest.PATH,
                ModuleBasedSignatureProcessorTest.COMPONENT, ModuleBasedSignatureProcessorTest.OPERATION);
        Assertions.assertTrue(result, "File-based processor should never fail.");
        Assertions.assertEquals(processor.getErrorMessage(), null, "there should never be an error message");
        Assertions.assertEquals(processor.getComponentSignature(),
                ModuleBasedSignatureProcessorTest.COMPONENT.toLowerCase(), "component name should be lower case");
        Assertions.assertEquals(processor.getOperationSignature(),
                ModuleBasedSignatureProcessorTest.OPERATION.toLowerCase(), "operation name should be lower case");
    }

}
