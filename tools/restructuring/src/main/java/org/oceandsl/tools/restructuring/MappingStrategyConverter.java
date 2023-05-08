package org.oceandsl.tools.restructuring;

import java.util.Locale;

import com.beust.jcommander.IStringConverter;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class MappingStrategyConverter implements IStringConverter<EMappingStrategy> {

    @Override
    public EMappingStrategy convert(final String value) {
        return EMappingStrategy.valueOf(value.toUpperCase(Locale.getDefault()));
    }

}
