package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.domain.Browser;
import com.sandbox9.devicedetector.domain.Device;
import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.type.*;

public class ReadableUserAgent {
	private OS os;
	private Device device;
	private Browser browser;
	private String userAgentString;

	public ReadableUserAgent(OS os, Device device, Browser browser, String userAgentString) {
		this.os = os;
		this.device = device;
		this.browser = browser;
		this.userAgentString = userAgentString;
	}

	public ReadableUserAgent() {
	}

	public OS getOs() {
		return os;
	}

	public Device getDevice() {
		return device;
	}

	public Browser getBrowser() {
		return browser;
	}

	public BaseDeviceType getDeviceType() {
		return device.getDeviceType();
	}

	public BaseOSType getOSType() {
		return os.getOsType();
	}

	public BaseBrowserType getBrowserType() {
		return browser.getBrowserType();
	}

	public String getUserAgentString() {
		return userAgentString;
	}
}
