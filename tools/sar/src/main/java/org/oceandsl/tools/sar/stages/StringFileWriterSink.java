
package org.oceandsl.tools.sar.stages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import teetime.framework.AbstractConsumerStage;

/**
 * @author Reiner Jung
 * @since 1.1
 *
 */
public class StringFileWriterSink extends AbstractConsumerStage<String> {

	private final BufferedWriter outputStream;
	
	public StringFileWriterSink(Path path) throws IOException {
		this.outputStream = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
	}

	@Override
	protected void execute(String element) throws Exception {
		this.outputStream.write(element + "\n");
	}
	
	@Override
	protected void onTerminating() {
		try {
			this.outputStream.close();
		} catch (IOException e) {
			this.logger.error("Cannot close output stream.");
		}
		super.onTerminating();
	}

}
