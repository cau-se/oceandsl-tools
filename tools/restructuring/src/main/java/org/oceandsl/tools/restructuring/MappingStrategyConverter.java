package org.oceandsl.tools.restructuring;

import java.util.Locale;

import com.beust.jcommander.IStringConverter;

public class MappingStrategyConverter implements IStringConverter<EMappingStrategy> {

	@Override
	public EMappingStrategy convert(String value) {
		return EMappingStrategy.valueOf(value.toLowerCase(Locale.getDefault()));
	}

}
