package com.sandbox9.devicedetector.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandbox9.devicedetector.domain.Browser;
import com.sandbox9.devicedetector.domain.type.DefaultBrowserType;
import com.sandbox9.devicedetector.type.BaseBrowserType;
import com.sandbox9.devicedetector.type.BrowserType;
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

public class BrowserParser {
    private static final Logger logger = LoggerFactory.getLogger(BrowserParser.class);

    private Set<BrowserParserData> browserParserDatas;

    public BrowserParser() {
        browserParserDatas = new HashSet<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(BrowserParserData.class, new JsonDeserializer<BrowserParserData>() {
            @Override
            public BrowserParserData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String type = jsonObject.get("type").getAsString();
                JsonArray jsonPatterns = jsonObject.getAsJsonArray("patterns");
                String[] patterns = new String[jsonPatterns.size()];
                for (int i = 0; i < jsonPatterns.size(); i++) {
                    patterns[i] = jsonPatterns.get(i).getAsString();
                }
                return new BrowserParserData(BrowserType.valueOf(type), patterns);
            }
        }).create();


        Type type = new TypeToken<Set<BrowserParserData>>() {}.getType();
        InputStream resourceInputStream = getClass().getResourceAsStream("/device-detector/browser.json");
        Reader reader = new InputStreamReader(resourceInputStream);
        this.browserParserDatas = gson.fromJson(reader, type);

        if(reader!=null){
            try {
                reader.close();
            } catch (IOException e) {}
        }

        if(resourceInputStream!=null) {
            try {
                resourceInputStream.close();
            } catch (IOException e) {}
        }
    }

    public Browser parse(String userAgentString) {
        boolean isBrowserExist = false;
        String version = null;
        BaseBrowserType browserType = null;

        logger.debug("Finding Browser. UserAgent = '{}', ", userAgentString);

        for (BrowserParserData browserParserData : browserParserDatas) {
            for (Pattern pattern : browserParserData.getPatterns()) {
                Matcher matcher = pattern.matcher(userAgentString);
                if(logger.isDebugEnabled())
                    logger.debug("pattern : '{}', isMatch : {}", pattern, matcher.matches());

                if (matcher.matches()) {
                    isBrowserExist = true;
                    version = matcher.group(1);
                    browserType = browserParserData.getBrowserType();

                    break;
                }
            }

            if (isBrowserExist) break;
        }

        if (isBrowserExist)
            return new Browser(browserType, version);
        else
            return new Browser(DefaultBrowserType.UNKNOWN, null);
    }

}
