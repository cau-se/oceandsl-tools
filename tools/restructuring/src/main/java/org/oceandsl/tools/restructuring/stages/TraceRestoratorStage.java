package org.oceandsl.tools.restructuring.stages;

import org.oceandsl.tools.restructuring.EMappingStrategy;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.ComponentsMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.EmptyMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.KuhnMatcherMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.RandomMapper;
import org.oceandsl.tools.restructuring.util.TransformationFactory;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

public class TraceRestoratorStage extends AbstractConsumerStage<ModelRepository> {

	private EMappingStrategy strat;

	protected final OutputPort<AbstractMapper> compMapperOutputPort = this.createOutputPort();// original

	private ModelRepository goal;
	private ModelRepository original;

	public TraceRestoratorStage(EMappingStrategy strat) {
		this.strat = strat;
	}

	public OutputPort<AbstractMapper> getOutputPort() {
		return this.compMapperOutputPort;
	}

	@Override
	protected void execute(ModelRepository rep) throws Exception {
		if (this.original == null) {
			this.original = rep;
		} else {
			this.goal = rep;
			AssemblyModel o = this.original.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
			AssemblyModel g = this.goal.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
			AbstractMapper mapper;
			switch (strat) {
			case EMPTY:
				mapper = new EmptyMapper(o, g, original.getName(), goal.getName());
				break;
			case RANDOM:
				mapper = new RandomMapper(o, g, original.getName(), goal.getName());
				break;
			case KUHN:
				mapper = new KuhnMatcherMapper(o, g, original.getName(), goal.getName());
				break;
			case NORMAL:
			default:
				mapper = new ComponentsMapper(o, g, original.getName(), goal.getName());
				break;
			}

			if (TransformationFactory.areSameModels(o, g)) {
				this.logger.error("Identical models");
				throw new Exception();
			} else
				this.compMapperOutputPort.send(mapper);
		}
	}

}