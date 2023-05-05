package org.oceandsl.tools.restructuring.stages;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

import org.oceandsl.tools.restructuring.EMappingStrategy;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractComponentMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.ComponentsMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.EmptyMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.KuhnMatcherMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.RandomMapper;
import org.oceandsl.tools.restructuring.util.TransformationFactory;

public class TraceRestoratorStage extends AbstractConsumerStage<ModelRepository> {

    private final EMappingStrategy strat;

    protected final OutputPort<AbstractComponentMapper> compMapperOutputPort = this.createOutputPort();// original

    private ModelRepository goal;
    private ModelRepository original;

    public TraceRestoratorStage(final EMappingStrategy strat) {
        this.strat = strat;
    }

    public OutputPort<AbstractComponentMapper> getOutputPort() {
        return this.compMapperOutputPort;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        if (this.original == null) {
            this.original = repository;
        } else {
            this.goal = repository;
            this.logger.info("Processing {} -> {}", this.original.getName(), this.goal.getName());
            final AssemblyModel o = this.original.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
            final AssemblyModel g = this.goal.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
            AbstractComponentMapper mapper;
            switch (this.strat) {
            case EMPTY:
                mapper = new EmptyMapper(o, g, this.original.getName(), this.goal.getName());
                break;
            case RANDOM:
                mapper = new RandomMapper(o, g, this.original.getName(), this.goal.getName());
                break;
            case KUHN:
                mapper = new KuhnMatcherMapper(o, g, this.original.getName(), this.goal.getName());
                break;
            case NORMAL:
            default:
                mapper = new ComponentsMapper(o, g, this.original.getName(), this.goal.getName());
                break;
            }

            if (TransformationFactory.areSameModels(o, g)) {
                this.logger.error("Identical models");
                throw new Exception();
            } else {
                this.compMapperOutputPort.send(mapper);
            }
        }
    }

}