package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.type.BaseDeviceType;

public class Device {
	private BaseDeviceType deviceType;
	private String name;

	public Device(String name, BaseDeviceType deviceType) {
		this.name = name;
		this.deviceType = deviceType;
	}

	public BaseDeviceType getDeviceType() {
		return deviceType;
	}

	public String getName() {
		return name;
	}
}
