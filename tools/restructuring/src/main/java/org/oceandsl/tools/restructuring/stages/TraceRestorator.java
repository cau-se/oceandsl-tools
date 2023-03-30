package org.oceandsl.tools.restructuring.stages;

import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.ComponentsMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.EmptyMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.Matcher;
import org.oceandsl.tools.restructuring.stages.exec.mapper.RandomMapper;
import org.oceandsl.tools.restructuring.util.TransformationFactory;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.type.TypePackage;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;





public class TraceRestorator extends AbstractConsumerStage<ModelRepository> {

	private String strat;
	
	protected final OutputPort<AbstractMapper> compMapperOutputPort = this.createOutputPort();//original
	
	private ModelRepository goal;
	private ModelRepository original;
    
	
	
	public TraceRestorator(String strat) {
		this.strat = strat;
	}
	
	public OutputPort<AbstractMapper> getOutputPort(){
	    return this.compMapperOutputPort;
	    
	}
    
	
	@Override
	protected void execute(ModelRepository rep) throws Exception {
		
		if(this.original==null) {
			this.original = rep;
		}else {
			this.goal = rep;
			AssemblyModel o = this.original.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
			AssemblyModel g = this.goal.getModel(AssemblyPackage.eINSTANCE.getAssemblyModel());
			AbstractMapper mapper = new ComponentsMapper(o,g);
			if (strat.equals("normal")) {
			 mapper = new ComponentsMapper(o, g);
			}else if (strat.equals("empty")){
				 mapper = new EmptyMapper(o,g);
			}else if((strat.equals("rand"))) {
				 mapper = new RandomMapper(o,g);
			}else if((strat.equals("kuhn"))) {
				 mapper = new Matcher(o,g);
			}
			if(TransformationFactory.areSameModels(o, g))
				throw new Exception();
			this.compMapperOutputPort.send(mapper);
		}
		
		
	}
	


}