package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.type.BaseOSType;

import java.util.Arrays;
import java.util.regex.Pattern;

public class OSParserData {
	private BaseOSType osType;
	private String osName;

	private Pattern[] patterns;

	public OSParserData(String osName, BaseOSType osType, String[] patterns) {
		this.osType = osType;

		Pattern[] internalPatterns = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++)
			internalPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);

		this.patterns = internalPatterns;
	}

	public BaseOSType getOsType() {
		return osType;
	}

	public String getOsName() {
		return osName;
	}

	public Pattern[] getPatterns() {
		return patterns;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("OSParserData{");
		sb.append("osType=").append(osType);
		sb.append(", osName='").append(osName).append('\'');
		sb.append(", patterns=").append(Arrays.toString(patterns));
		sb.append('}');
		return sb.toString();
	}
}
