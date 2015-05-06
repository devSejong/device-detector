package com.sandbox9.devicedetector.dto;

import com.sandbox9.devicedetector.type.BaseDeviceType;

/**
 * 디바이스 DTO
 * @author devSejong
 * @since 1.0
 */
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
