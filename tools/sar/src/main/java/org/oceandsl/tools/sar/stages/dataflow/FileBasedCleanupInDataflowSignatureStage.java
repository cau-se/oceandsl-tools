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
package org.oceandsl.tools.sar.stages.dataflow;

import java.nio.file.Path;
import java.nio.file.Paths;

import teetime.stage.basic.AbstractTransformation;

/**
 * Cleanup names and make them lower case if requested.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class FileBasedCleanupInDataflowSignatureStage extends AbstractTransformation<DataAccess, DataAccess> {

    private final boolean caseInsensitive;

    public FileBasedCleanupInDataflowSignatureStage(final boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        element.setModule(this.convertToLowerCase(this.processSignature(element.getModule())));
        element.setOperation(this.convertToLowerCase(this.processSignature(element.getOperation())));
        element.setSharedData(this.convertToLowerCase(this.processSignature(element.getSharedData())));

        this.outputPort.send(element);
    }

    private String convertToLowerCase(final String string) {
        String value;
        if (string.endsWith("_")) {
            value = string.substring(0, string.length() - 1);
        } else {
            value = string;
        }
        return this.caseInsensitive ? value.toLowerCase() : value;
    }

    private String processSignature(final String signature) {
        if ("<<no-file>>".equals(signature)) {
            return signature;
        } else {
            final Path path = Paths.get(signature);
            return this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
        }
    }
}
