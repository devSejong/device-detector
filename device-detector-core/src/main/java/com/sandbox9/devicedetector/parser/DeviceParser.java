package com.sandbox9.devicedetector.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.DeviceDetectorException;
import com.sandbox9.devicedetector.domain.Device;
import com.sandbox9.devicedetector.type.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceParser {
	private static final Logger logger = LoggerFactory.getLogger(DeviceParser.class);

	Set<DeviceParserData> deviceParserDatas;

	public DeviceParser() {
		//데이터를 읽어옴
		deviceParserDatas = new HashSet<>();

		Gson gson = new GsonBuilder().registerTypeAdapter(DeviceParserData.class, new JsonDeserializer<DeviceParserData>() {
			@Override
			public DeviceParserData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();

				String type = jsonObject.get("type").getAsString();
				String name = jsonObject.get("name").getAsString();
				JsonArray jsonPatterns = jsonObject.getAsJsonArray("patterns");

				String[] patterns = new String[jsonPatterns.size()];

				for (int i = 0; i < jsonPatterns.size(); i++) {
					patterns[i] = jsonPatterns.get(i).getAsString();
				}

				return new DeviceParserData(name, DeviceType.valueOf(type), patterns);
			}
		}).create();

		try {
			Type type = new TypeToken<Set<DeviceParserData>>() {}.getType();
			Set<DeviceParserData> parserDatas = gson.fromJson(new FileReader(new File(getClass().getResource("/deviceParserData.json").toURI())), type);

			this.deviceParserDatas = parserDatas;

		} catch (FileNotFoundException | URISyntaxException e) {
			throw new DeviceDetectorException("변환 중 에러 발생", e);
		}
	}

	public Device parse(String userAgentString) {
		boolean isDeviceExist = false;
		DeviceType deviceType = null;
		String deviceName = null;

		logger.debug("Finding Device. UserAgent = '{}', ", userAgentString);

		for (DeviceParserData deviceParserData : deviceParserDatas) {
			for (Pattern pattern : deviceParserData.getPatterns()) {
				Matcher matcher = pattern.matcher(userAgentString);
				if(logger.isDebugEnabled())
					logger.debug("pattern : '{}', isMatch : {}", pattern, matcher.matches());

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
