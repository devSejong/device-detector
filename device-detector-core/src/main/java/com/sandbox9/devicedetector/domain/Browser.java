package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.type.BaseBrowserType;

public class Browser {
	private BaseBrowserType browserType;
	private String version;

	public Browser(BaseBrowserType browserType, String version) {
		this.browserType = browserType;
		this.version = version;
	}

	public BaseBrowserType getBrowserType() {
		return browserType;
	}

	public String getVersion() {
		return version;
	}

}
