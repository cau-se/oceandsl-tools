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
package org.oceandsl.tools.aul.stages.entropy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;

import org.mosim.refactorlizar.architecture.evaluation.codemetrics.CodeMetric;

import teetime.framework.AbstractConsumerStage;

/**
 * @author Reiner Jung
 *
 */
public class EntropyResultSink extends AbstractConsumerStage<Map<Class<? extends CodeMetric>, CodeMetric>> {

    private final BufferedWriter fileHandle;
    private final Path path;

    public EntropyResultSink(final Path path) throws IOException {
        this.fileHandle = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);
        this.path = path;
    }

    @Override
    protected void execute(final Map<Class<? extends CodeMetric>, CodeMetric> metrics) throws Exception {
        for (final Entry<Class<? extends CodeMetric>, CodeMetric> element : metrics.entrySet()) {
            this.fileHandle
                    .write(String.format("%s;%f", element.getKey().getCanonicalName(), element.getValue().getValue()));
        }
    }

    @Override
    protected void onTerminating() {
        try {
            this.fileHandle.flush();
            this.fileHandle.close();
        } catch (final IOException e) {
            this.logger.error("Could not close metric result file {}", this.path.getFileName().toString());
        }
        super.onTerminating();
    }
}
