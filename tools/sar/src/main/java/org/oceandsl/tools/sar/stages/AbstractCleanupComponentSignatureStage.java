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
package org.oceandsl.tools.sar.stages;

import org.oceandsl.analysis.CallerCallee;

import teetime.stage.basic.AbstractFilter;

/**
 * Sanitize component names in {@link CallerCallee} entries. This is helpful to cleanup names based
 * on file or module names. It allows to make all entries lower case.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public abstract class AbstractCleanupComponentSignatureStage extends AbstractFilter<CallerCallee> {

    protected final boolean caseInsensitive;

    public AbstractCleanupComponentSignatureStage(final boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    protected void execute(final CallerCallee event) throws Exception {
        event.setSourcePath(this.processComponentSignature(event.getSourcePath()));
        event.setTargetPath(this.processComponentSignature(event.getTargetPath()));

        this.outputPort.send(event);
    }

    protected abstract String processComponentSignature(String sourcePath);
}
