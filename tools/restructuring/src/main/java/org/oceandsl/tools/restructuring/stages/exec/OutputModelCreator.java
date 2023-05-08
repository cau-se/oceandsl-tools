
package org.oceandsl.tools.restructuring.stages.exec;

import java.util.List;

import org.oceandsl.tools.restructuring.transformations.AbstractTransformationStep;
import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.DeleteTransformation;
import org.oceandsl.tools.restructuring.transformations.MergeTransformation;
import org.oceandsl.tools.restructuring.transformations.MoveTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;
import org.oceandsl.tools.restructuring.transformations.SplitTransformation;

import restructuremodel.ComponentsTransformation;
import restructuremodel.CreateComponent;
import restructuremodel.CutOperation;
import restructuremodel.DeleteComponent;
import restructuremodel.MergeComponent;
import restructuremodel.MoveOperation;
import restructuremodel.PasteOperation;
import restructuremodel.RestructuremodelFactory;
import restructuremodel.SplitComponent;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class OutputModelCreator {

    private final RestructureStepFinder stepFinder;
    private final ComponentsTransformation outputModel;
    RestructuremodelFactory factory = RestructuremodelFactory.eINSTANCE;

    public OutputModelCreator(final RestructureStepFinder stepFinder) {
        this.stepFinder = stepFinder;
        this.outputModel = this.factory.createComponentsTransformation();
    }

    public ComponentsTransformation getOutputModel() {
        return this.outputModel;
    }

    public void createOutputModel() {
        final List<AbstractTransformationStep> transformations = this.stepFinder.getSteps();
        for (final AbstractTransformationStep step : transformations) {
            if (step instanceof CreateTransformation) {
                // System.out.println("dadsadre"+outputModel.getTransformations()!=null);
                this.outputModel.getTransformations().add(this.createCreateComponent((CreateTransformation) step));
            } else if (step instanceof DeleteTransformation) {
                this.outputModel.getTransformations().add(this.createDeleteComponent((DeleteTransformation) step));
            } else if (step instanceof CutTransformation) {
                this.outputModel.getTransformations().add(this.createCutOperation((CutTransformation) step));
            } else if (step instanceof PasteTransformation) {
                this.outputModel.getTransformations().add(this.createPasteOperation((PasteTransformation) step));
            } else if ((step instanceof MoveTransformation)) {
                this.outputModel.getTransformations().add(this.createMoveOperation((MoveTransformation) step));
            } else if (step instanceof SplitTransformation) {
                this.outputModel.getTransformations().add(this.createSplitComponent((SplitTransformation) step));
            } else if (step instanceof MergeTransformation) {
                this.outputModel.getTransformations().add(this.createMergeComponent((MergeTransformation) step));
            }
        }
    }

    public CreateComponent createCreateComponent(final CreateTransformation transformation) {
        final CreateComponent result = this.factory.createCreateComponent();
        result.setComponentName(transformation.getComponentName());
        return result;

    }

    public DeleteComponent createDeleteComponent(final DeleteTransformation transformation) {
        final DeleteComponent result = this.factory.createDeleteComponent();
        result.setComponentName(transformation.getComponentName());
        return result;

    }

    public CutOperation createCutOperation(final CutTransformation transformation) {
        final CutOperation result = this.factory.createCutOperation();
        result.setComponentName(transformation.getComponentName());
        result.setOperationName(transformation.getOperationName());
        return result;

    }

    public PasteOperation createPasteOperation(final PasteTransformation transformation) {
        final PasteOperation result = this.factory.createPasteOperation();
        result.setComponentName(transformation.getComponentName());
        result.setOperationName(transformation.getOperationName());
        return result;

    }

    public MoveOperation createMoveOperation(final MoveTransformation transformation) {
        final MoveOperation result = this.factory.createMoveOperation();
        result.setFrom(transformation.getCutTransformation().getComponentName());
        result.setTo(transformation.getPasteTransformation().getComponentName());
        result.setOperationName(transformation.getCutTransformation().getOperationName());

        final CutOperation cut = this.createCutOperation(transformation.getCutTransformation());
        final PasteOperation paste = this.createPasteOperation(transformation.getPasteTransformation());

        result.setCutOperation(cut);
        result.setPasteOperation(paste);

        return result;
    }

    public SplitComponent createSplitComponent(final SplitTransformation transformation) {
        final SplitComponent result = this.factory.createSplitComponent();
        result.setNewComponent(transformation.getCreateTransformation().getComponentName());
        result.setCreateComponent(this.createCreateComponent(transformation.getCreateTransformation()));
        final MoveTransformation move = (MoveTransformation) transformation.getMoveTransformation().get(0);
        result.setOldComponent(move.getCutTransformation().getComponentName());
        for (final AbstractTransformationStep mv : transformation.getMoveTransformation()) {
            final MoveTransformation tmp = (MoveTransformation) mv;
            result.getOperationsToMove().add(tmp.getCutTransformation().getOperationName());
            result.getMoveOperations().add(this.createMoveOperation(tmp));
        }

        return result;

    }

    public MergeComponent createMergeComponent(final MergeTransformation transformation) {
        final MergeComponent result = this.factory.createMergeComponent();
        result.setComponentName(transformation.getDeleteTransformation().getComponentName());
        result.setDeleteTransformation(this.createDeleteComponent(transformation.getDeleteTransformation()));
        final MoveTransformation move = (MoveTransformation) transformation.getMoveTransformations().get(0);
        result.setMergeGoalComponent(move.getPasteTransformation().getComponentName());
        for (final AbstractTransformationStep mv : transformation.getMoveTransformations()) {
            final MoveTransformation tmp = (MoveTransformation) mv;
            result.getOperations().add(tmp.getCutTransformation().getOperationName());
            result.getOperationToMove().add(this.createMoveOperation(tmp));
        }

        return result;
    }

}
