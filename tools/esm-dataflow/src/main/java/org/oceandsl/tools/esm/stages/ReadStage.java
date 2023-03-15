/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import teetime.framework.AbstractProducerStage;

public class ReadStage extends AbstractProducerStage<List<File>> {

    private final Path rootPath;

    public ReadStage(final Path list) {
        this.rootPath = list;
    }

    @Override
    protected void execute() throws Exception {

        final File folder = new File(this.rootPath.toAbsolutePath().toString());
        final List<File> files = Arrays.asList(folder.listFiles());
        // System.out.println("Files sent: "+ files.size());
        this.outputPort.send(files);
        this.workCompleted();

    }

}
