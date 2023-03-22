package org.oceandsl.tools.restructuring.stages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;
import org.oceandsl.tools.restructuring.stages.exec.OutputModelCreator;
import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.util.TransformationFactory;
import org.oceandsl.tools.restructuring.util.WriteModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import teetime.framework.AbstractConsumerStage;

public class SinkStage extends AbstractConsumerStage<RestructureStepFinder>{
	private final Path outputPath;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchitectureModelManagementUtils.class);
	public SinkStage(final Path outputPath) {
        this.outputPath = outputPath;
    }
	@Override
	protected void execute(RestructureStepFinder element) throws Exception {
		if(TransformationFactory.areSameModels(element.getGoal(), element.getOrig())) {
		OutputModelCreator output = new OutputModelCreator(element);
		output.createOutputModel();
		System.out.println("Num of steps: "+(element.getNumStep()));
		WriteModelUtils.writeModelRepository(outputPath, output.getOutputModel());
		}else {
			System.out.println("Sequence falty");
		}
		

	}




}
