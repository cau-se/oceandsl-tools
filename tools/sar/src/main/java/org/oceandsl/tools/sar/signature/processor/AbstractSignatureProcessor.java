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
package org.oceandsl.tools.sar.signature.processor;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public abstract class AbstractSignatureProcessor {

    protected boolean caseInsensitive;
    protected String componentSignature;
    protected String operationSignature;

    public AbstractSignatureProcessor(final boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    protected String removeTrailingUnderscore(final String string) {
        if (string.endsWith("_")) {
            return string.substring(0, string.length() - 1);
        } else {
            return string;
        }
    }

    protected String convertToLowerCase(final String string) {
        return this.caseInsensitive ? string.toLowerCase() : string; // NOCS NOPMD
    }

    public abstract String getErrorMessage();

    public abstract boolean processSignatures(String path, String componentSignature, String operationSignature); // NOCS
                                                                                                                  // hiding
                                                                                                                  // fields

    public String getComponentSignature() {
        return this.componentSignature;
    }

    public String getOperationSignature() {
        return this.operationSignature;
    }
}
