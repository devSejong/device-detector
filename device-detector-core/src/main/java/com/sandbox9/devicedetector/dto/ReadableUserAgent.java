package com.sandbox9.devicedetector.dto;

import com.sandbox9.devicedetector.type.BaseBrowserType;
import com.sandbox9.devicedetector.type.BaseDeviceType;
import com.sandbox9.devicedetector.type.BaseOSType;

/**
 * useragent를 파싱한 결과가 담기는 DTO
 * @author devSejong
 * @since 1.0
 */
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
