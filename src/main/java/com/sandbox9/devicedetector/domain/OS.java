package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.domain.type.OSType;

public class OS {
	private OSType osType;
	private String osName;
	private String version;

	public OS(com.sandbox9.devicedetector.domain.type.OSType osType, String osName, String version) {
		this.osType = osType;
		this.osName = osName;
		this.version = version;
	}

	public OSType getOsType() {
		return osType;
	}

	public void setOsType(com.sandbox9.devicedetector.domain.type.OSType osType) {
		this.osType = osType;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
