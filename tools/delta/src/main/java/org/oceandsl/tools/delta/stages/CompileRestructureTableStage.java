package org.oceandsl.tools.delta.stages;

import java.util.HashMap;
import java.util.Map;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.restructuring.restructuremodel.CutOperation;
import org.oceandsl.tools.restructuring.restructuremodel.MoveOperation;
import org.oceandsl.tools.restructuring.restructuremodel.PasteOperation;
import org.oceandsl.tools.restructuring.restructuremodel.TransformationModel;

public class CompileRestructureTableStage extends AbstractTransformation<TransformationModel, Table> {

    private final Table table;
    private final Map<String, CutOperation> rememberCutOperation = new HashMap<>();

    public CompileRestructureTableStage(final String name) {
        this.table = new Table(name, new StringValueHandler("source-component"),
                new StringValueHandler("target-component"), new StringValueHandler("element"));
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
                try {
                    this.table.addRow(cut.getComponentName(), paste.getComponentName(), cut.getOperationName());
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Value conversion failed: {}", e.getLocalizedMessage());
                }
            } else if (action instanceof PasteOperation) {
                final PasteOperation paste = (PasteOperation) action;
                if (this.rememberCutOperation.containsKey(paste.getOperationName())) {
                    final CutOperation cut = this.rememberCutOperation.get(paste.getOperationName());
                    try {
                        this.table.addRow(cut.getComponentName(), paste.getComponentName(), cut.getOperationName());
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Value conversion failed: {}", e.getLocalizedMessage());
                    }
                } else {
                    this.logger.error("Have paste without cut. {} {}", paste.getComponentName(),
                            paste.getOperationName());
                }
            } else {
                this.logger.info("Got a {}", action.getClass());
            }
        });
    }

}
