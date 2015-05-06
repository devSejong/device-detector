package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.type.*;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;

public class DeviceDetectorMatcher {
        public static Matcher<? super BaseDeviceType> isSameDeviceType(final DeviceType deviceType) {
            return new CustomMatcher<BaseDeviceType>(deviceType.name()) {
                @Override
                public boolean matches(Object o) {
                    return deviceType.equals(o);
                }
            };
        }


        public static  Matcher<? super BaseOSType> isSameOsType(final OSType osType) {
            return new CustomMatcher<BaseOSType>(osType.name()) {
                @Override
                public boolean matches(Object o) {
                    return osType.equals(o);
                }
            };
        }


        public static  Matcher<? super BaseBrowserType> isSameBrowserType(final BrowserType browserType) {
            return new CustomMatcher<BaseBrowserType>(browserType.name()) {
                @Override
                public boolean matches(Object o) {
                    return browserType.equals(o);
                }
            };
        }


    }