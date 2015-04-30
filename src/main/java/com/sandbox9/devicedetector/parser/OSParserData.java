package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.type.OSType;

import java.util.regex.Pattern;

public class OSParserData {
	private OSType osType;
	private String osName;

	private Pattern[] patterns;

	public OSParserData(String osName, OSType osType, String[] patterns) {
		this.osType = osType;

		Pattern[] internalPatterns = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++)
			internalPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);

		this.patterns = internalPatterns;
	}

	public OSType getOsType() {
		return osType;
	}

	public String getOsName() {
		return osName;
	}

	public Pattern[] getPatterns() {
		return patterns;
	}
}
