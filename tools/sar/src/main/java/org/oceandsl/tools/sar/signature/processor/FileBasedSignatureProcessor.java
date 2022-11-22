/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
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

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class FileBasedSignatureProcessor extends AbstractSignatureProcessor {

    public FileBasedSignatureProcessor(final boolean caseInsensitive) {
        super(caseInsensitive);
    }

    @Override
    public void processSignatures(final String pathString, final String componentSignature,
            final String operationSignature) {
        if ("<<no-file>>".equals(pathString)) {
            this.componentSignature = pathString;
        } else {
            final Path path = Paths.get(pathString);
            this.componentSignature = this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
        }

        this.operationSignature = this.convertToLowerCase(operationSignature);
    }

    @Override
    public void close() {
        // nothing to be done here
    }

}
