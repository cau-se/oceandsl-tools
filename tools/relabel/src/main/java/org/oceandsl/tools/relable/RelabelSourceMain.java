package org.oceandsl.tools.relable;

import java.io.File;
import java.nio.file.Files;

import com.beust.jcommander.JCommander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.common.configuration.Configuration;
import kieker.common.exception.ConfigurationException;
import kieker.tools.common.AbstractService;

/**
 * Architecture analysis main class.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class RelabelSourceMain extends AbstractService<TeetimeConfiguration, Settings> {

    /** logger for all tools. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName()); // NOPMD

    public static void main(final String[] args) {
        final RelabelSourceMain main = new RelabelSourceMain();
        try {
            final int exitCode = main.run("relabel source information", "relabel", args, new Settings());
            java.lang.System.exit(exitCode);
        } catch (final IllegalArgumentException e) {
            LoggerFactory.getLogger(RelabelSourceMain.class).error("Configuration error: {}", e.getLocalizedMessage());
            java.lang.System.exit(1);
        }
    }

    @Override
    protected boolean checkParameters(final JCommander commander) throws ConfigurationException {
        if (!Files.isDirectory(this.parameterConfiguration.getInputDirectory())) {
            this.logger.error("Input path {} is not a directory", this.parameterConfiguration.getInputDirectory());
            return false;
        }

        if (!Files.isDirectory(this.parameterConfiguration.getOutputDirectory())) {
            this.logger.error("Output path {} is not directory", this.parameterConfiguration.getOutputDirectory());
            return false;
        }

        if (this.parameterConfiguration.getReplacements().size() == 0) {
            this.logger.error("Need to specify at least one replacement rule.");
            return false;
        }

        return true;
    }

    @Override
    protected TeetimeConfiguration createTeetimeConfiguration() throws ConfigurationException {
        return new TeetimeConfiguration(this.parameterConfiguration);
    }

    @Override
    protected File getConfigurationFile() {
        return null;
    }

    @Override
    protected boolean checkConfiguration(final Configuration configuration, final JCommander commander) {
        return true;
    }

    @Override
    protected void shutdownService() {
    }

}
