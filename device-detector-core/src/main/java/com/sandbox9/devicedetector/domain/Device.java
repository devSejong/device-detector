package com.sandbox9.devicedetector.domain;

import com.sandbox9.devicedetector.type.DeviceType;

public class Device {
	private DeviceType deviceType;
	private String name;

	public Device(String name, DeviceType deviceType) {
		this.name = name;
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public String getName() {
		return name;
	}
}
