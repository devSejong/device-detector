package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.type.DeviceType;

import java.util.regex.Pattern;


public class DeviceParserData {
	private String deviceName;
	private DeviceType deviceType;
	private Pattern[] patterns;

	public DeviceParserData(String deviceName, DeviceType deviceType, String[] patterns) {
		this.deviceName = deviceName;
		this.deviceType = deviceType;

		Pattern[] internalPatterns = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++)
			internalPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);

		this.patterns = internalPatterns;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public Pattern[] getPatterns() {
		return patterns;
	}
}
