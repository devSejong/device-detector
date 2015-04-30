package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.domain.type.OSType;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSParser {
	Set<OSParserData> osParserDatas;

	public OSParser() {
		//데이터를 읽어옴
		osParserDatas = new HashSet<>();

		osParserDatas.add(new OSParserData("Android", OSType.ANDROID, new String[]{
				".*[A|a]ndroid.*"
		}));

		osParserDatas.add(new OSParserData("IOS", OSType.IOS, new String[]{
				".*[iPad|iPhone|iPod].*"
		}));

	}

	public OS parse(String userAgentString) {
		boolean isOSExist = false;
		OSType osType = null;
		String osName = null;
		String osVersion = null;

		for (OSParserData osParserData : osParserDatas) {
			for (Pattern pattern : osParserData.getPatterns()) {
				Matcher matcher = pattern.matcher(userAgentString);

				if (matcher.matches()) {
					isOSExist = true;
					osName = osParserData.getOsName();
					osType = osParserData.getOsType();
					osVersion = "TODO";

					break;
				}
			}

			if (isOSExist) break;
		}

		if (isOSExist)
			return new OS(osType, osName, osVersion);
		else
			return new OS(osType.UNKNOWN, null, null);
	}
}
