package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.type.BaseOSType;

public class OS {
	private BaseOSType osType;
	private String osName;
	private String version;

	public OS(BaseOSType osType, String osName, String version) {
		this.osType = osType;
		this.osName = osName;
		this.version = version;
	}

	public BaseOSType getOsType() {
		return osType;
	}

	public void setOsType(BaseOSType osType) {
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
