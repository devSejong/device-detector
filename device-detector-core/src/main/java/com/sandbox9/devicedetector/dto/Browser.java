package com.sandbox9.devicedetector.dto;

import com.sandbox9.devicedetector.type.BaseBrowserType;

/**
 * 브라우저 DTO
 * @author devSejong
 * @since 1.0
 * */
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
