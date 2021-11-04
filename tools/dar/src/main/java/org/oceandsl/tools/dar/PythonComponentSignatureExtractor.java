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
package org.oceandsl.tools.dar;

import kieker.analysis.signature.IComponentSignatureExtractor;
import kieker.model.analysismodel.type.ComponentType;

/**
 * Extract component signatures from Python classnames.
 *
 * @author Reiner Jung
 * @since 1.2
 */
public class PythonComponentSignatureExtractor implements IComponentSignatureExtractor {

    @Override
    public void extract(final ComponentType componentType) {
        String signature = componentType.getSignature();
        if (signature == null) {
            signature = "-- none --";
        }

        if (signature.equals("<unknown>")) {
            componentType.setName(signature);
            componentType.setPackage("none");
        } else {
            final String[] segments = signature.split("\\.");
            final String rest = signature.substring(0, signature.lastIndexOf('.') - 1);

            componentType.setName(segments[segments.length - 1]);
            componentType.setPackage(rest);

        }
    }

}
