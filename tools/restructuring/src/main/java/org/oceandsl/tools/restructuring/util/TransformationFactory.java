package org.oceandsl.tools.restructuring.util;

import java.util.HashSet;
import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;

import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.DeleteTransformation;
import org.oceandsl.tools.restructuring.transformations.MergeTransformation;
import org.oceandsl.tools.restructuring.transformations.MoveTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;
import org.oceandsl.tools.restructuring.transformations.SplitTransformation;

public class TransformationFactory {

    public static CreateTransformation createCreateTransformation(final String componentName,
            final AssemblyModel model) {

        final CreateTransformation result = new CreateTransformation(model);
        result.setComponentName(componentName); // For the new component in original

        return result;
    }

    public static DeleteTransformation createDeleteTransformation(final String componentName,
            final AssemblyModel model) {
        final DeleteTransformation result = new DeleteTransformation(model);
        result.setComponentName(componentName);
        return result;
    }

    public static CutTransformation createCutTransformation(final String componentName, final String operationName,
            final AssemblyModel model) {

        final CutTransformation result = new CutTransformation(model);
        result.setComponentName(componentName); // For the new component in original
        result.setOperationName(operationName);

        return result;
    }

    public static PasteTransformation createPasteTransformation(final String componentName, final String operationName,
            final AssemblyModel model) {

        final PasteTransformation result = new PasteTransformation(model);
        result.setComponentName(componentName); // For the new component in original
        result.setOperationName(operationName);

        return result;
    }

    public static MoveTransformation createMoveTransformation(final String fromComponent, final String toComponent,
            final String operationName, final AssemblyModel model) {
        final CutTransformation from = createCutTransformation(fromComponent, operationName, model);
        final PasteTransformation to = createPasteTransformation(toComponent, operationName, model);

        final MoveTransformation result = new MoveTransformation(model);
        result.add(from);
        result.add(to);

        return result;
    }

    public static SplitTransformation createSplitTransformation(final String componentOld, final String componentNew,
            final String operationName, final AssemblyModel model) {

        final CreateTransformation newComponent = createCreateTransformation(componentNew, model);
        final MoveTransformation to = createMoveTransformation(componentOld, componentNew, operationName, model);

        final SplitTransformation result = new SplitTransformation(model);
        result.add(newComponent);
        result.add(to);

        return result;
    }

    public static MergeTransformation createMergeTransformation(final String componentOld, final String componentNew,
            final String operationName, final AssemblyModel model) {

        final DeleteTransformation newComponent = createDeleteTransformation(componentNew, model);
        final MoveTransformation to = createMoveTransformation(componentOld, componentNew, operationName, model);

        final MergeTransformation result = new MergeTransformation(model);
        result.add(newComponent);
        result.add(to);

        return result;
    }

    public static boolean areSameComponents(final AssemblyComponent a, final AssemblyComponent b) {
        final Set<String> opsA = a.getOperations().keySet();
        final Set<String> opsB = b.getOperations().keySet();

        return new HashSet<>(opsA).equals(new HashSet<>(opsB));

    }

    /**
     * Check wether two models are structurally equal.
     *
     * @param a
     *            first model
     * @param b
     *            second model
     * @return returns true if they are structurally equal
     */
    public static boolean areSameModels(final AssemblyModel a, final AssemblyModel b) {
        if (a.getComponents().size() != b.getComponents().size()) {
            return false;
        }
        for (final AssemblyComponent bComponent : b.getComponents().values()) {
            if (!containsComponent(a, bComponent)) {
                return false;
            }
        }

        return true;
    }

    private static boolean containsComponent(final AssemblyModel model, final AssemblyComponent matchingComponent) {
        for (final AssemblyComponent aComponent : model.getComponents().values()) {
            if (areSameComponents(aComponent, matchingComponent)) {
                return true;
            }
        }
        return false;
    }
}
