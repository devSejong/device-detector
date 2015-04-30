package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.domain.Browser;
import com.sandbox9.devicedetector.domain.Device;
import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.domain.type.BrowserType;
import com.sandbox9.devicedetector.domain.type.DeviceType;
import com.sandbox9.devicedetector.domain.type.OSType;

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

	public DeviceType getDeviceType() {
		return device.getDeviceType();
	}

	public OSType getOSType() {
		return os.getOSType();
	}

	public BrowserType getBrowserType() {
		return browser.getBrowserType();
	}

	public String getUserAgentString() {
		return userAgentString;
	}
}
