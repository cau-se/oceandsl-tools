package org.oceandsl.log.rewriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTestMain {

	public static void main(String[] args) {
		String string = "MAIN_ at ??:?";
		final Pattern pattern = Pattern.compile("^(\\w+) at ([\\w\\?]+):([\\d\\?]*)$");
		Matcher matcher = pattern.matcher(string);

		boolean result = matcher.find();
		
		System.out.println(matcher.groupCount());
		System.out.println(result);
		System.out.println(matcher.group(0));
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		System.out.println(matcher.group(3));
	}

}
