package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.domain.Browser;
import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.domain.Device;
import com.sandbox9.devicedetector.parser.BrowserParser;
import com.sandbox9.devicedetector.parser.DeviceParser;
import com.sandbox9.devicedetector.parser.OSParser;

public class UserAgentParser {
	private DeviceParser deviceParser;
	private OSParser osParser;
	private BrowserParser browserParser;

	public UserAgentParser() {
		deviceParser = new DeviceParser();
		osParser = new OSParser();
		browserParser = new BrowserParser();
	}

	public ReadableUserAgent parse(String userAgentString) {
		OS os = osParser.parse(userAgentString);
		Browser browser = browserParser.parse(userAgentString);
		Device device = deviceParser.parse(userAgentString);

		return new ReadableUserAgent(os, device, browser, userAgentString);
	}
}
