package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.type.BrowserType;

import java.util.Arrays;
import java.util.regex.Pattern;

public class BrowserParserData {

	private BrowserType browserType;
	private Pattern[] patterns;

	public BrowserParserData(BrowserType browserType, String[] patterns) {
		this.browserType = browserType;

		Pattern[] internalPatterns = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++)
			internalPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);

		this.patterns = internalPatterns;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public Pattern[] getPatterns() {
		return patterns;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BrowserParserData{");
		sb.append("browserType=").append(browserType);
		sb.append(", patterns=").append(Arrays.toString(patterns));
		sb.append('}');

		return sb.toString();
	}

}
