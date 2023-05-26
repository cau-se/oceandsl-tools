/**
 *
 */
package org.oceandsl.tools.restructuring.stages;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.Table;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class AggregateModelEditDistanceStage
        extends AbstractTransformation<ResultRecord, Table<ModelEditDistanceEntry>> {

    private final Table<ModelEditDistanceEntry> table;

    public AggregateModelEditDistanceStage() {
        this.table = new Table<>("med-output", "original", "goal", "med");
    }

    @Override
    protected void execute(final ResultRecord element) throws Exception {
        this.table.getRows().add(new ModelEditDistanceEntry(element.getOriginalModelName(), element.getGoalModelName(),
                element.getNumberOfSteps()));
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.table);
        super.onTerminating();
    }
}
