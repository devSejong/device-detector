package com.sandbox9.devicedetector.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.DeviceDetectorException;
import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.domain.type.DefaultOSType;
import com.sandbox9.devicedetector.type.ExtendedOSType;
import com.sandbox9.devicedetector.type.OSType;
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

public class OSParser {
	private static final Logger logger = LoggerFactory.getLogger(OSParser.class);

	Set<OSParserData> osParserDatas;

	public OSParser() {
		//데이터를 읽어옴
		osParserDatas = new HashSet<>();

		Gson gson = new GsonBuilder().registerTypeAdapter(OSParserData.class, new JsonDeserializer<OSParserData>() {
			@Override
			public OSParserData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();

				String name = jsonObject.get("name").getAsString();
				String type = jsonObject.get("type").getAsString();
				JsonArray jsonPatterns = jsonObject.getAsJsonArray("patterns");

				String[] patterns = new String[jsonPatterns.size()];

				for (int i = 0; i < jsonPatterns.size(); i++) {
					patterns[i] = jsonPatterns.get(i).getAsString();
				}

				return new OSParserData(name, ExtendedOSType.valueOf(type), patterns);
			}
		}).create();

		try {
			Type type = new TypeToken<Set<OSParserData>>() {}.getType();
			Set<OSParserData> parserDatas = gson.fromJson(new FileReader(new File(getClass().getResource("/osParserData.json").toURI())), type);

			this.osParserDatas = parserDatas;

		} catch (FileNotFoundException | URISyntaxException e) {
			throw new DeviceDetectorException("변환 중 에러 발생", e);
		}

	}

	public OS parse(String userAgentString) {
		boolean isOSExist = false;
		OSType osType = null;
		String osName = null;
		String osVersion = null;

		logger.debug("Finding OS. UserAgent = '{}', ", userAgentString);

		for (OSParserData osParserData : osParserDatas) {
			for (Pattern pattern : osParserData.getPatterns()) {
				Matcher matcher = pattern.matcher(userAgentString);

				if(logger.isDebugEnabled())
					logger.debug("pattern : '{}', isMatch : {}", pattern, matcher.matches());

				if (matcher.matches()) {
					isOSExist = true;
					osName = osParserData.getOsName();
					osType = osParserData.getOsType();
					osVersion = matcher.group(1);

					break;
				}
			}

			if (isOSExist) break;
		}

		if (isOSExist)
			return new OS(osType, osName, osVersion);
		else
			return new OS(DefaultOSType.UNKNOWN, null, null);
	}
}
