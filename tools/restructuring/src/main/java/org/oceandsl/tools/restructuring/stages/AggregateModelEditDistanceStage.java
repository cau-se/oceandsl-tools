/**
 *
 */
package org.oceandsl.tools.restructuring.stages;

import org.oceandsl.analysis.code.stages.data.IntegerValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class AggregateModelEditDistanceStage extends AbstractTransformation<ResultRecord, Table> {

	private final Table table;

	public AggregateModelEditDistanceStage() {
		this.table = new Table("med-output", new StringValueHandler("original"), new StringValueHandler("goal"),
				new IntegerValueHandler("med"));
	}

	@Override
	protected void execute(ResultRecord element) throws Exception {
		this.table.addRow(element.getOriginalModelName(), element.getGoalModelName(), element.getNumberOfSteps());
	}

	@Override
	protected void onTerminating() {
		this.outputPort.send(table);
		super.onTerminating();
	}
}
