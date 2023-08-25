package org.oceandsl.tools.mt.stages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;

import kieker.analysis.generic.graph.clustering.OPTICSDataGED;
import kieker.analysis.generic.graph.clustering.OpticsData;
import kieker.analysis.generic.graph.mtree.IDistanceFunction;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.analysis.generic.data.MoveOperationEntry;

public class ConvertTableToOpticsDataStage
        extends AbstractTransformation<Table<String, MoveOperationEntry>, List<OpticsData<MoveOperationEntry>>> {

    @Override
    protected void execute(final Table<String, MoveOperationEntry> table) throws Exception {
        final IDistanceFunction<MoveOperationEntry> distanceFunction = new IDistanceFunction<>() {

            final LevenshteinDistance distance = new LevenshteinDistance();

            @Override
            public double calculate(final MoveOperationEntry data1, final MoveOperationEntry data2) {
                final String left = data1.getOperationName();
                final String right = data2.getOperationName();
                return (double) this.distance.apply(left, right) / (double) (left.length() + right.length());
            }
        };

        final OPTICSDataGED<MoveOperationEntry> opticsDistance = new OPTICSDataGED<>(distanceFunction);

        final List<OpticsData<MoveOperationEntry>> results = new ArrayList<>();
        table.getRows().forEach(entry -> results.add(new OpticsData<>(entry, opticsDistance)));
        this.outputPort.send(results);
    }

}
