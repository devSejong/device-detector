package com.sandbox9.devicedetector.parser.browser;

import com.sandbox9.devicedetector.type.BaseBrowserType;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 브라우저 패턴 및 타입이 담긴 DTO
 * @author devSejong
 * @since 1.0
 */
public class BrowserParserData {

	private BaseBrowserType browserType;
	private Pattern[] patterns;

	public BrowserParserData(BaseBrowserType browserType, String[] patterns) {
		this.browserType = browserType;

		Pattern[] internalPatterns = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++)
			internalPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);

		this.patterns = internalPatterns;
	}

	public BaseBrowserType getBrowserType() {
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
