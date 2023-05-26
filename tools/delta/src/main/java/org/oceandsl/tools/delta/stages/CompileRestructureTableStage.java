package org.oceandsl.tools.delta.stages;

import java.util.HashMap;
import java.util.Map;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.tools.restructuring.restructuremodel.CutOperation;
import org.oceandsl.tools.restructuring.restructuremodel.MoveOperation;
import org.oceandsl.tools.restructuring.restructuremodel.PasteOperation;
import org.oceandsl.tools.restructuring.restructuremodel.TransformationModel;

public class CompileRestructureTableStage extends AbstractTransformation<TransformationModel, Table<MoveOperationEntry>> {

    private final Table<MoveOperationEntry> table;
    private final Map<String, CutOperation> rememberCutOperation = new HashMap<>();

    public CompileRestructureTableStage(final String name) {
        this.table = new Table<>(name, "source-component", "target-component", "element");
    }

    @Override
    protected void execute(final TransformationModel element) throws Exception {
        element.getTransformations().forEach(action -> {
            if (action instanceof CutOperation) {
                final CutOperation cut = (CutOperation) action;
                this.rememberCutOperation.put(cut.getOperationName(), cut);
            } else if (action instanceof MoveOperation) {
                final MoveOperation move = (MoveOperation) action;
                final CutOperation cut = move.getCutOperation();
                final PasteOperation paste = move.getPasteOperation();

                this.table.getRows()
                        .add(new MoveOperationEntry(cut.getComponentName(), paste.getComponentName(), cut.getOperationName()));
            } else if (action instanceof PasteOperation) {
                final PasteOperation paste = (PasteOperation) action;
                if (this.rememberCutOperation.containsKey(paste.getOperationName())) {
                    final CutOperation cut = this.rememberCutOperation.get(paste.getOperationName());
                    this.table.getRows()
                            .add(new MoveOperationEntry(cut.getComponentName(), paste.getComponentName(), cut.getOperationName()));
                } else {
                    this.logger.error("Have paste without cut. {} {}", paste.getComponentName(),
                            paste.getOperationName());
                }
            } else {
                this.logger.debug("Got a {}", action.getClass());
            }
        });
        this.outputPort.send(this.table);
    }

}
