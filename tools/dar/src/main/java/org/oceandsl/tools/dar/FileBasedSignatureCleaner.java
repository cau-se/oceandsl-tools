/***************************************************************************
 * Copyright 2021 Kieker Project (http://kieker-monitoring.net)
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
package org.oceandsl.tools.dar;

import java.nio.file.Path;
import java.nio.file.Paths;

import kieker.analysis.signature.AbstractSignatureCleaner;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class FileBasedSignatureCleaner extends AbstractSignatureCleaner {

    public FileBasedSignatureCleaner(final boolean caseInsensitive) {
        super(caseInsensitive);
    }

    @Override
    public String processComponentSignature(final String signature) {
        if ("<<no-file>>".equals(signature)) {
            return signature;
        } else {
            final Path path = Paths.get(signature);
            return this.convertToLowerCase(
                    this.removeTrailingUnderscore(path.getName(path.getNameCount() - 1).toString()));
        }
    }
}
