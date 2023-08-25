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
package org.oceandsl.tools.mt;

import java.io.IOException;

import teetime.framework.Configuration;

import org.oceandsl.analysis.generic.data.MoveOperationEntry;
import org.oceandsl.analysis.generic.source.CsvTableReaderProducerStage;
import org.oceandsl.analysis.generic.stages.SingleFileTableCsvSink;
import org.oceandsl.analysis.generic.stages.TableCsvSink;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings settings) throws IOException {
        final String inputFileName = settings.getInputTable().getFileName().toString();
        final CsvTableReaderProducerStage<String, MoveOperationEntry> csvTableReaderStage = new CsvTableReaderProducerStage<>(
                settings.getInputTable(), ';', '"', '\\', true, MoveOperationEntry.class,
                inputFileName.substring(0, inputFileName.lastIndexOf('.')));
        final SortModelStage sortModelStage = new SortModelStage(settings.getSortDescription());
        final SingleFileTableCsvSink<String, MoveOperationEntry> tableSink = new SingleFileTableCsvSink<>(
                settings.getOutputTable(), MoveOperationEntry.class, true, TableCsvSink.LF);

        this.connectPorts(csvTableReaderStage.getOutputPort(), sortModelStage.getInputPort());
        this.connectPorts(sortModelStage.getOutputPort(), tableSink.getInputPort());
    }
}
