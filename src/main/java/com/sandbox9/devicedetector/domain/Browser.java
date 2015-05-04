package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.domain.type.BrowserType;

public class Browser {
	private BrowserType browserType;
	private String version;

	public Browser(BrowserType browserType, String version) {
		this.browserType = browserType;
		this.version = version;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public String getVersion() {
		return version;
	}

}
