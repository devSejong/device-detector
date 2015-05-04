package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.type.BrowserType;
import com.sandbox9.devicedetector.type.DeviceType;
import com.sandbox9.devicedetector.type.OSType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserAgentParserTest {
	UserAgentParser userAgentParser = new UserAgentParser();

	@Test
	public void iPhone6_테스트(){
		String userAgentString = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12A366 Safari/600.1.4";
		ReadableUserAgent userAgent = userAgentParser.parse(userAgentString);

		assertThat(userAgent.getDeviceType(), is(DeviceType.PHONE));
		assertThat(userAgent.getOSType(), is(OSType.IOS));
		assertThat(userAgent.getOs().getVersion(), is("8_0_2"));
		assertThat(userAgent.getBrowserType(), is(BrowserType.SAFARI_MOBILE));
		assertThat(userAgent.getBrowser().getVersion(), is("600.1.4"));
		assertThat(userAgent.getDevice().getName(), is("iPhone"));
	}

	@Test
	public void Galaxy5s_테스트(){
		String userAgentString = "Mozilla/5.0 (Linux; Android 5.0; SM-G900H Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.96 Mobile Safari/537.36";
		ReadableUserAgent userAgent = userAgentParser.parse(userAgentString);

		assertThat(userAgent.getDeviceType(), is(DeviceType.PHONE));
		assertThat(userAgent.getOSType(), is(OSType.ANDROID));
		assertThat(userAgent.getBrowserType(), is(BrowserType.CHROME_MOBILE));
		assertThat(userAgent.getBrowser().getVersion(), is("41.0.2272.96"));
		assertThat(userAgent.getDevice().getName(), is("Galaxy S5"));
		assertThat(userAgent.getOs().getVersion(), is("5.0"));
	}

}
