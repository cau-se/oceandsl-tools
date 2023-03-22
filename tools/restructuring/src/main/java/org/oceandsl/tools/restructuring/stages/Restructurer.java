package org.oceandsl.tools.restructuring.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.transformations.AbstractTransformationStep;
import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.AbstractStage;
import teetime.framework.InputPort;
import teetime.framework.OutputPort;

public class Restructurer extends AbstractConsumerStage<AbstractMapper> {

	//protected final InputPort<ComponentsMapper> compMapperPort = this.createInputPort();

	protected final OutputPort<RestructureStepFinder> stepsOutputPort = this.createOutputPort();



	public OutputPort<RestructureStepFinder>getStepsOutputPort(){
		return this.stepsOutputPort;
	}
	@Override
	protected void execute(AbstractMapper mapper) throws Exception {
		RestructureStepFinder restructureStepsFinder = new RestructureStepFinder(mapper);
		restructureStepsFinder.findTransformation();
		this.stepsOutputPort.send(restructureStepsFinder);

	}
}
