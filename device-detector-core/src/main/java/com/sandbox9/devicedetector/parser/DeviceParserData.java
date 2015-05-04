package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.type.DeviceType;

import java.util.Arrays;
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DeviceParserData{");
		sb.append("deviceName='").append(deviceName).append('\'');
		sb.append(", deviceType=").append(deviceType);
		sb.append(", patterns=").append(Arrays.toString(patterns));
		sb.append('}');
		return sb.toString();
	}
}
