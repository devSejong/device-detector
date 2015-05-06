package com.sandbox9.devicedetector.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.dto.Device;
import com.sandbox9.devicedetector.dto.type.DefaultDeviceType;
import com.sandbox9.devicedetector.type.BaseDeviceType;
import com.sandbox9.devicedetector.type.DeviceType;
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
 * 객체 초기화시 device-detector-data 프로젝트에 정의된 디바이스 정보를 가져와 클래스 내부에 저장한다.
 * parse 메서드를 호출 시 저장된 정보를 기준으로 매칭되는 디바이스 정보를 반환한다.
 * @author devSejong
 * @since 1.0
 */
public class DeviceParser {
    private static final Logger logger = LoggerFactory.getLogger(DeviceParser.class);

    Set<DeviceParserData> deviceParserDatas;

    /**
     * device-detector-core 프로젝트 내부 /device-detector/device.json을 해석하여 디바이스 정보를
     * Set<DeviceParserData>의 형태로 변환하여 내부에 저장한다.
     */
    public DeviceParser() {
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

        Type type = new TypeToken<Set<DeviceParserData>>() {}.getType();
        InputStream resourceInputStream = getClass().getResourceAsStream("/device-detector/device.json");
        Reader reader = new InputStreamReader(resourceInputStream);

        Set<DeviceParserData> parserDatas = gson.fromJson(reader, type);

        this.deviceParserDatas = parserDatas;

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
    public Device parse(String userAgentString) {
        boolean isDeviceExist = false;
        BaseDeviceType deviceType = null;
        String deviceName = null;

        logger.debug("Finding Device. UserAgent = '{}', ", userAgentString);

        for (DeviceParserData deviceParserData : deviceParserDatas) {
            for (Pattern pattern : deviceParserData.getPatterns()) {
                Matcher matcher = pattern.matcher(userAgentString);
                if (logger.isDebugEnabled())
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
            device = new Device(null, DefaultDeviceType.UNKNOWN);

        return device;
    }

}
