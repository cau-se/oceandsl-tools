package org.oceandsl.tools.mt.stages;

import kieker.analysis.generic.graph.clustering.Clustering;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.analysis.generic.data.MoveOperationEntry;

public class ConstructTableStage
        extends AbstractTransformation<Clustering<MoveOperationEntry>, Table<String, MoveOperationEntry>> {

    private final String label;

    public ConstructTableStage(final String label) {
        this.label = label;
    }

    @Override
    protected void execute(final Clustering<MoveOperationEntry> clustering) throws Exception {
        final Table<String, MoveOperationEntry> table = new Table<>(this.label);
        clustering.getClusters().forEach(cluster -> {
            cluster.forEach(entry -> {
                table.getRows().add(entry);
            });
        });
        this.outputPort.send(table);
    }

}
