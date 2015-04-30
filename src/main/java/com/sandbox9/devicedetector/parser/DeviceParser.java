package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.Device;
import com.sandbox9.devicedetector.domain.type.DeviceType;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceParser {

	Set<DeviceParserData> deviceParserDatas;

	public DeviceParser() {
		//데이터를 읽어옴
		deviceParserDatas = new HashSet<>();

		deviceParserDatas.add(new DeviceParserData("iPhone", DeviceType.PHONE, new String[]{
				".*iPhone.*"
		}));

		deviceParserDatas.add(new DeviceParserData("iPod", DeviceType.PHONE, new String[]{
				".*iPod.*"
		}));

		deviceParserDatas.add(new DeviceParserData("iPad", DeviceType.TABLET, new String[]{
				".*iPad.*"
		}));
		deviceParserDatas.add(new DeviceParserData("Galaxy S5", DeviceType.PHONE, new String[]{
				".*SM-G900.*"
		}));

	}

	public Device parse(String userAgentString) {
		boolean isDeviceExist = false;
		DeviceType deviceType = null;
		String deviceName = null;

		for (DeviceParserData deviceParserData : deviceParserDatas) {
			for (Pattern pattern : deviceParserData.getPatterns()) {
				Matcher matcher = pattern.matcher(userAgentString);

				if (matcher.matches()) {
					isDeviceExist = true;
					deviceName = deviceParserData.getDeviceName();
					deviceType = deviceParserData.getDeviceType();

					break;
				}
			}

			if (isDeviceExist) break;
		}

		Device device;

		if (isDeviceExist)
			device = new Device(deviceName, deviceType);
		else
			device = new Device(null, DeviceType.UNKNOWN);

		return device;
	}

}
