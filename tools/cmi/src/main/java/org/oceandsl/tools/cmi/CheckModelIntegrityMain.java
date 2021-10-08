/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.cmi;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.architecture.model.ArchitectureModelManagementFactory;
import org.oceandsl.architecture.model.stages.utils.RepositoryUtils;

import kieker.analysis.stage.model.ModelRepository;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 *
 */
public class CheckModelIntegrityMain {

    public static void main(final String[] args) throws ConfigurationException {
        final Path path = Paths.get(args[0]);
        final ModelRepository repository = ArchitectureModelManagementFactory.loadModelRepository(path);
        // RepositoryUtils.print(repository);

        long missingSourceErrors = 0;
        for(Entry<Class<? extends EObject>,List<Class<? extends EObject>>> modelConfig : configureModels().entrySet()) {
            missingSourceErrors += CheckModelIntegrityMain.checkSource(repository.getModel(SourceModel.class),
                    repository.getModel(modelConfig.getKey()).eAllContents(), modelConfig.getValue());
        }

        System.out.printf("Number of missing source labels %s\n",missingSourceErrors);
    }

    private static Map<Class<? extends EObject>,List<Class<? extends EObject>>> configureModels() {
    	Map<Class<? extends EObject>,List<Class<? extends EObject>>> modelConfigs = new HashMap<>();
    	modelConfigs.put(TypeModel.class, new ArrayList<Class<? extends EObject>>());
    	modelConfigs.put(AssemblyModel.class, new ArrayList<Class<? extends EObject>>());
    	modelConfigs.put(DeploymentModel.class, new ArrayList<Class<? extends EObject>>());
    	List<Class<? extends EObject>> executionIgnoreList = new ArrayList<Class<? extends EObject>>();
    	executionIgnoreList.add(Tuple.class);
		modelConfigs.put(ExecutionModel.class, executionIgnoreList);
		return modelConfigs;
	}

	private static long checkSource(final SourceModel model, final TreeIterator<EObject> treeIterator, List<Class<? extends EObject>> ignoreList) {
    	long errorCount = 0;
        while (treeIterator.hasNext()) {
            final EObject object = treeIterator.next();
            if (!(object instanceof BasicEMap.Entry)) {
            	if (!isOnIgnoreList(ignoreList, object)) {
            	if (model.getSources().get(object) == null) {
	                System.out.println("Missing source reference for");
	                RepositoryUtils.print(object, "  ");
	                System.out.println("----");
	                errorCount++;
	            }
            	}
            }
        }
        
        return errorCount;
    }

	private static boolean isOnIgnoreList(List<Class<? extends EObject>> ignoreList, EObject object) {
		for (Class<? extends EObject> element : ignoreList) {
			if (element.isAssignableFrom(object.getClass())) 
				return true;
		}
		return false;
	}
}
