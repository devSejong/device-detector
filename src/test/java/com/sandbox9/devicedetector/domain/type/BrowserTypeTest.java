package com.sandbox9.devicedetector.domain.type;

import junit.framework.TestCase;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BrowserTypeTest extends TestCase {

    public void testGetType() throws Exception {
        //when
        BrowserType browserType = BrowserType.valueOf("CHROME_MOBILE");

        //then
        assertThat(browserType, is(BrowserType.CHROME_MOBILE));
    }
}