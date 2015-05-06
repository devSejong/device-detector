package com.sandbox9.devicedetector.parser.os;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.dto.OS;
import com.sandbox9.devicedetector.dto.type.DefaultOSType;
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

/**
 * 객체 초기화시 device-detector-data 프로젝트에 정의된 OS 정보를 가져와 클래스 내부에 저장한다.
 * parse 메서드를 호출 시 저장된 정보를 기준으로 매칭되는 OS 정보를 반환한다.
 * @author devSejong
 * @since 1.0
 */
public class OSParser {
	private static final Logger logger = LoggerFactory.getLogger(OSParser.class);

	Set<OSParserData> osParserDatas;

	/**
	 * device-detector-core 프로젝트 내부 /device-detector/os.json을 해석하여 디바이스 정보를
	 * Set<OsParserData>의 형태로 변환하여 내부에 저장한다.
	 */
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

	/**
	 * userAgent를 기준으로 브라우저 정보를 식별하고 반환한다.
	 *
	 * @param userAgentString
	 * @return
	 */
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
