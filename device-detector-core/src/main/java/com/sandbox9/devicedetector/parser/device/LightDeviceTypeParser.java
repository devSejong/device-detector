package com.sandbox9.devicedetector.parser.device;

import com.sandbox9.devicedetector.type.DeviceType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

//Spring mobile의 LiteDeviceResolver를 그대로 사용함.
public class LightDeviceTypeParser {

    private final List<String> mobileUserAgentPrefixes = new ArrayList<>();

    private final List<String> mobileUserAgentKeywords = new ArrayList<String>();

    private final List<String> tabletUserAgentKeywords = new ArrayList<String>();

    private final List<String> normalUserAgentKeywords = new ArrayList<String>();

    LightDeviceTypeParser() {
        init();
    }

    DeviceType parse(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        // UserAgent keyword detection of Normal devices
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            for (String keyword : normalUserAgentKeywords) {
                if (userAgent.contains(keyword)) {
                    return resolveFallback(request);
                }
            }
        }

        // UserAgent keyword detection of Tablet devices
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            // Android special case
            if (userAgent.contains("android") && !userAgent.contains("mobile")) {
                return DeviceType.TABLET;
            }
            // Kindle Fire special case
            if (userAgent.contains("silk") && !userAgent.contains("mobile")) {
                return DeviceType.TABLET;
            }
            for (String keyword : tabletUserAgentKeywords) {
                if (userAgent.contains(keyword)) {
                    return DeviceType.TABLET;
                }
            }
        }
        // UAProf detection
        if (request.getHeader("x-wap-profile") != null
                || request.getHeader("Profile") != null) {
            return DeviceType.PHONE;
        }
        // User-Agent prefix detection
        if (userAgent != null && userAgent.length() >= 4) {
            String prefix = userAgent.substring(0, 4).toLowerCase();
            if (mobileUserAgentPrefixes.contains(prefix)) {
                return DeviceType.PHONE;
            }
        }
        // Accept-header based detection
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("wap")) {
            return DeviceType.PHONE;
        }
        // UserAgent keyword detection for Mobile devices
        if (userAgent != null) {
            for (String keyword : mobileUserAgentKeywords) {
                if (userAgent.contains(keyword)) {
                    return DeviceType.PHONE;
                }
            }
        }
        // OperaMini special case
        @SuppressWarnings("rawtypes")
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            if (header.contains("OperaMini")) {
                return DeviceType.PHONE;
            }
        }
        return resolveFallback(request);
    }

    protected List<String> getMobileUserAgentPrefixes() {
        return mobileUserAgentPrefixes;
    }

    protected List<String> getMobileUserAgentKeywords() {
        return mobileUserAgentKeywords;
    }

    protected List<String> getTabletUserAgentKeywords() {
        return tabletUserAgentKeywords;
    }

    protected List<String> getNormalUserAgentKeywords() {
        return normalUserAgentKeywords;
    }

    protected void init() {
        getMobileUserAgentPrefixes().addAll(
                Arrays.asList(KNOWN_MOBILE_USER_AGENT_PREFIXES));
        getMobileUserAgentKeywords().addAll(
                Arrays.asList(KNOWN_MOBILE_USER_AGENT_KEYWORDS));
        getTabletUserAgentKeywords().addAll(
                Arrays.asList(KNOWN_TABLET_USER_AGENT_KEYWORDS));
    }

    protected DeviceType resolveFallback(HttpServletRequest request) {
        return DeviceType.PC;
    }

    private static final String[] KNOWN_MOBILE_USER_AGENT_PREFIXES = new String[] {
            "w3c ", "w3c-", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
            "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco",
            "eric", "hipt", "htc_", "inno", "ipaq", "ipod", "jigs", "kddi", "keji",
            "leno", "lg-c", "lg-d", "lg-g", "lge-", "lg/u", "maui", "maxo", "midp",
            "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-", "newt", "noki",
            "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage",
            "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-",
            "siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-",
            "tosh", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi",
            "wapp", "wapr", "webc", "winw", "winw", "xda ", "xda-" };

    private static final String[] KNOWN_MOBILE_USER_AGENT_KEYWORDS = new String[] {
            "blackberry", "webos", "ipod", "lge vx", "midp", "maemo", "mmp", "mobile",
            "netfront", "hiptop", "nintendo DS", "novarra", "openweb", "opera mobi",
            "opera mini", "palm", "psp", "phone", "smartphone", "symbian", "up.browser",
            "up.link", "wap", "windows ce" };

    private static final String[] KNOWN_TABLET_USER_AGENT_KEYWORDS = new String[] {
            "ipad", "playbook", "hp-tablet", "kindle" };

}