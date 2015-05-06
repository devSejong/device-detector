package com.sandbox9.devicedetector.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.domain.OS;
import com.sandbox9.devicedetector.domain.type.DefaultOSType;
import com.sandbox9.devicedetector.type.OSType;
import com.sandbox9.devicedetector.type.BaseOSType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSParser {
	private static final Logger logger = LoggerFactory.getLogger(OSParser.class);

	Set<OSParserData> osParserDatas;

	public OSParser() {
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

				return new OSParserData(name, OSType.valueOf(type), patterns);
			}
		}).create();

		Type type = new TypeToken<Set<OSParserData>>() {}.getType();
		InputStream resourceInputStream = getClass().getResourceAsStream("/device-detector/os.json");
		Reader reader = new InputStreamReader(resourceInputStream);

		Set<OSParserData> parserDatas = gson.fromJson(reader, type);

		this.osParserDatas = parserDatas;

		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

		if (resourceInputStream != null) {
			try {
				resourceInputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public OS parse(String userAgentString) {
		boolean isOSExist = false;
		BaseOSType osType = null;
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
