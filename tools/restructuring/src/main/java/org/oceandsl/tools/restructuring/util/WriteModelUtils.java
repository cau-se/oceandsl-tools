package org.oceandsl.tools.restructuring.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import restructuremodel.ComponentsTransformation;

public class WriteModelUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchitectureModelManagementUtils.class);
	
	
	public static void writeModelRepository(final Path outputDirectory,  ComponentsTransformation model)
	            throws IOException {

	        final Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
	        final Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
	        extensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl());

	        // store models
	        final ResourceSet resourceSet = new ResourceSetImpl();
	        //resourceSet.setResourceFactoryRegistry(registry);
	        //resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",new XMIResourceFactoryImpl());
	        if (!Files.exists(outputDirectory)) {
	            Files.createDirectory(outputDirectory);
	        }

	        writeModel(resourceSet, outputDirectory,
	               "outputmodel.xmi", model);

	    }

	    private static <T extends EObject> void writeModel(final ResourceSet resourceSet, final Path outputDirectory,
	            final String filename, final T model) {
	       LOGGER.info("Saving model {}", filename);

	        final File modelFile = createWriteModelFileHandle(outputDirectory, filename);

	        final Resource resource = resourceSet.createResource(URI.createFileURI(modelFile.getAbsolutePath()));
	        resource.getContents().add(model);

	        try {
	            resource.save(Collections.EMPTY_MAP);
	        } catch (final IOException e) {
	            LOGGER.error("Cannot write {} model to storage. Cause: {}",
	                    modelFile.getAbsoluteFile(), e.getLocalizedMessage());
	        }
	    }

	    private static File createReadModelFileHandle(final Path path, final String filename) {
	        return new File(path.toString() + File.separator + filename);
	    }

	    private static File createWriteModelFileHandle(final Path path, final String filename) {
	        return new File(path.toFile().getAbsolutePath() + File.separator + filename);
	    }
}
