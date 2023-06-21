package org.oceandsl.tools.maa.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.type.ProvidedInterfaceType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

public class SimilarMethodSetMergeInterfaceStage extends AbstractMergeInterfaceStage {

    private final int methodDistance;

    public SimilarMethodSetMergeInterfaceStage(final int methodDistance) {
        this.methodDistance = methodDistance;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final TypeModel typeModel = repository.getModel(TypePackage.Literals.TYPE_MODEL);
        typeModel.getComponentTypes().values().forEach(componentType -> componentType.getProvidedInterfaceTypes()
                .stream().map(iface -> this.computeDistances(iface, componentType.getProvidedInterfaceTypes()))).;

    }

    private List<MethodDistance> computeDistances(final ProvidedInterfaceType iface,
            final EList<ProvidedInterfaceType> providedInterfaceTypes) {
        return providedInterfaceTypes.stream().map(secondIface -> this.computeDistance(iface, secondIface))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private MethodDistance computeDistance(final ProvidedInterfaceType firstIface,
            final ProvidedInterfaceType secondIface) {
        return new MethodDistance(firstIface, secondIface,
                this.countMissingMethods(firstIface, secondIface) + this.countMissingMethods(secondIface, firstIface));
    }

    private long countMissingMethods(final ProvidedInterfaceType firstIface, final ProvidedInterfaceType secondIface) {
        return firstIface.getProvidedOperationTypes().values().stream()
                .map(operation -> secondIface.getProvidedOperationTypes().containsValue(operation))
                .filter(element -> !element).count();
    }

    private class MethodDistance {

        private final ProvidedInterfaceType firstIface;
        private final ProvidedInterfaceType secondIface;
        private final long distance;

        public MethodDistance(final ProvidedInterfaceType firstIface, final ProvidedInterfaceType secondIface,
                final long distance) {
            this.firstIface = firstIface;
            this.secondIface = secondIface;
            this.distance = distance;
        }

    }

}
