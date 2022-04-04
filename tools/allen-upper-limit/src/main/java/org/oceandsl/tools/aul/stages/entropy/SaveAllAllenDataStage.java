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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.mosim.refactorlizar.architecture.evaluation.codemetrics.CodeMetric;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Cohesion;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Complexity;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.Coupling;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.HyperGraphSize;
import org.mosim.refactorlizar.architecture.evaluation.codemetrics.LinesOfCode;

import teetime.framework.AbstractConsumerStage;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class SaveAllAllenDataStage extends AbstractConsumerStage<Map<Class<? extends CodeMetric>, CodeMetric>> {

    private static final String ALLEN_METRIC_FILENAME = "model-complexity.csv";
    private final BufferedWriter writer;
    private int counter;

    public SaveAllAllenDataStage(final Path outputDirectory) throws IOException {
        this.writer = Files.newBufferedWriter(outputDirectory.resolve(SaveAllAllenDataStage.ALLEN_METRIC_FILENAME));
        this.writer.write(String.format("%s; %s; %s ;%s ;%s\n", "nodes", "size", "complexity", "coupling", "cohesion"));
    }

    @Override
    protected void execute(final Map<Class<? extends CodeMetric>, CodeMetric> element) throws Exception {
        this.logger.info("Output line {}", ++this.counter);
        this.writer.write(String.format("%s; %s; %s; %s; %s\n", this.findElementInteger(LinesOfCode.class, element),
                this.findElement(HyperGraphSize.class, element), this.findElement(Complexity.class, element),
                this.findElement(Coupling.class, element), this.findElement(Cohesion.class, element)));
        this.writer.flush();
    }

    private String findElement(final Class<? extends CodeMetric> clazz,
            final Map<Class<? extends CodeMetric>, CodeMetric> metrics) {
        final CodeMetric metric = metrics.get(clazz);
        if (metric != null) {
            return String.format("%f", metric.getValue());
        } else {
            return "";
        }
    }

    private String findElementInteger(final Class<? extends CodeMetric> clazz,
            final Map<Class<? extends CodeMetric>, CodeMetric> metrics) {
        final CodeMetric metric = metrics.get(clazz);
        if (metric != null) {
            return String.format("%d", (int) metric.getValue());
        } else {
            return "";
        }
    }

    @Override
    protected void onTerminating() {
        try {
            this.writer.close();
        } catch (final IOException e) {
            this.logger.error("Could not close writer.");
        }
        super.onTerminating();
    }

}
