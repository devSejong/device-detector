package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.domain.type.OSType;

public class OS {
	private OSType OSType;
	private String osName;
	private String version;

	public OS(com.sandbox9.devicedetector.domain.type.OSType OSType, String osName, String version) {
		this.OSType = OSType;
		this.osName = osName;
		this.version = version;
	}

	public OSType getOSType() {
		return OSType;
	}

	public void setOSType(com.sandbox9.devicedetector.domain.type.OSType OSType) {
		this.OSType = OSType;
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
