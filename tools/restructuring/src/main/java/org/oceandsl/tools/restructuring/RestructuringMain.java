package org.oceandsl.tools.restructuring;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

public class RestructuringMain extends AbstractService<TeetimeConfiguration, Settings> {

	public static void main(final String[] args) {
		final RestructuringMain main = new RestructuringMain();
		try {
			final int exitCode = main.run("architecture model operations", "restructure", args, new Settings());
			System.exit(exitCode);
		} catch (final IllegalArgumentException e) {
			LoggerFactory.getLogger(RestructuringMain.class).error("Configuration error: {}", e.getLocalizedMessage());
			System.exit(1);
		}
	}

	@Override
	protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
		try {
			return new TeetimeConfiguration(this.settings);
		} catch (final IOException e) {
			throw new ConfigurationException(e);
		}
	}

	@Override
	protected Path getConfigurationPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean checkConfiguration(final Configuration configuration, final JCommander commander) {
		return true;
	}

	@Override
	protected boolean checkParameters(final JCommander commander) throws ConfigurationException {

		return true;
	}

	@Override
	protected void shutdownService() {
		// No special operation necessary.
	}
}