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
package org.oceandsl.tools.mktable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import teetime.framework.AbstractConsumerStage;

import org.oceandsl.analysis.generic.data.MoveOperationEntry;

public class CreateLaTeXTable extends AbstractConsumerStage<Optimization> {

    private BufferedWriter writer;
    private PrintWriter printWriter;

    public CreateLaTeXTable(final Path outputPath) {
        try {
            this.writer = Files.newBufferedWriter(outputPath);
            this.printWriter = new PrintWriter(this.writer);
            this.printWriter.println("\\documentclass{article}");
            this.printWriter.println("\\usepackage{multirow}");
            this.printWriter.println("\\begin{document}");
        } catch (final IOException e) {
            this.logger.error("Cannot write to {}, cause {}", outputPath.toString(), e.getLocalizedMessage());
        }
    }

    @Override
    protected void execute(final Optimization optimization) throws Exception {
        System.err.printf(">> %s %d\n", optimization.getName(), optimization.getList().size());
        final List<MoveOperationEntry> list = optimization.getList();
        final List<Integer> leftStops = new ArrayList<>();
        final List<Integer> rightStops = new ArrayList<>();
        int leftStop = 0;
        int rightStop = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getSourceComponentName().equals(list.get(i - 1).getSourceComponentName())) {
                leftStop++;
            } else {
                leftStops.add(leftStop);
                leftStop = 0;
            }
            if (list.get(i).getTargetComponentName().equals(list.get(i - 1).getTargetComponentName())) {
                rightStop++;
            } else {
                rightStops.add(rightStop);
                rightStop = 0;
            }
        }
        leftStops.add(leftStop);
        rightStops.add(rightStop);

        this.printWriter.printf("\\section{%s}\n", optimization.getName());
        this.printWriter.println("\\begin{tabular}{|r|c|l|}");
        this.printWriter.println("\\textbf{Source} & \\textbf{Operation} & \\textbf{Target} \\\\ \\hline");

        int lcount = 0;
        int lsum = 0;
        int rcount = 0;
        int rsum = 0;
        boolean leftStarted = false;
        boolean rightStarted = false;

        for (int i = 0; i < list.size(); i++) {
            final int leftSize = leftStops.get(lcount);
            final int rightSize = leftStops.get(rcount);

            boolean leftSideEnd = false;
            boolean rightSideEnd = false;

            final MoveOperationEntry entry = list.get(i);

            if (!leftStarted) {
                this.printWriter.printf("\\multirow{%d}{*}{%s} &\n", leftSize,
                        this.escape(entry.getSourceComponentName()));
                leftStarted = true;
            } else {
                this.printWriter.println("&");
                if (lsum + leftSize == i) {
                    lcount++;
                    lsum += leftSize;
                    leftSideEnd = true;
                }
            }

            this.printWriter.printf("%s &\n", this.escape(entry.getOperationName()));

            if (!rightStarted) {
                this.printWriter.printf("\\multirow{%d}{*|}{%s}\n", rightSize,
                        this.escape(entry.getTargetComponentName()));
                rightStarted = true;
            } else {
                if (rsum + rightSize == i) {
                    rcount++;
                    rsum += rightSize;
                    rightSideEnd = true;
                }
            }
            this.printWriter.println("\\\\");
            if (leftSideEnd && rightSideEnd) {
                this.printWriter.println("\\hline");
            } else if (leftSideEnd) {
                this.printWriter.println("\\cline{1-2}");
            } else if (rightSideEnd) {
                this.printWriter.println("\\cline{2-3}");
            } else {
                this.printWriter.println("\\cline{2-2}");
            }
        }
        this.printWriter.println("\\end{tabular}\n\n");
        this.printWriter.println("\\newpage");
        this.printWriter.flush();
    }

    private String escape(final String input) {
        return input.replace("_", "\\_");
    }

    @Override
    protected void onTerminating() {
        this.printWriter.println("\\end{document}");
        this.printWriter.close();
        try {
            this.writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        super.onTerminating();
    }

}
