package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.type.BrowserType;
import com.sandbox9.devicedetector.type.DeviceType;
import com.sandbox9.devicedetector.type.OSType;
import org.junit.Test;

import static com.sandbox9.devicedetector.DeviceDetectorMatcher.*;
import static com.sandbox9.devicedetector.type.BrowserType.CHROME_MOBILE;
import static com.sandbox9.devicedetector.type.DeviceType.PHONE;
import static com.sandbox9.devicedetector.type.OSType.ANDROID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserAgentParserTest {
    UserAgentParser userAgentParser = new UserAgentParser();

    @Test
    public void iPhone6_테스트() {
        String userAgentString = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12A366 Safari/600.1.4";
        ReadableUserAgent ua = userAgentParser.parse(userAgentString);

        assertThat(ua.getDeviceType(), isSameDeviceType(DeviceType.PHONE));
        assertThat(ua.getOSType(), isSameOsType(OSType.IOS));
        assertThat(ua.getOs().getVersion(), is("8_0_2"));
        assertThat(ua.getBrowserType(), isSameBrowserType(BrowserType.SAFARI_MOBILE));
        assertThat(ua.getBrowser().getVersion(), is("600.1.4"));
        assertThat(ua.getDevice().getName(), is("iPhone"));
    }


    @Test
    public void Galaxy5s_테스트() {
        String userAgentString = "Mozilla/5.0 (Linux; Android 5.0; SM-G900H Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.96 Mobile Safari/537.36";
        ReadableUserAgent ua = userAgentParser.parse(userAgentString);

		assertThat(ua.getDeviceType(), isSameDeviceType(PHONE));
		assertThat(ua.getOSType(), isSameOsType(ANDROID));
		assertThat(ua.getBrowserType(), isSameBrowserType(CHROME_MOBILE));
		assertThat(ua.getBrowser().getVersion(), is("41.0.2272.96"));
		assertThat(ua.getDevice().getName(), is("Galaxy S5"));
		assertThat(ua.getOs().getVersion(), is("5.0"));
    }
}
