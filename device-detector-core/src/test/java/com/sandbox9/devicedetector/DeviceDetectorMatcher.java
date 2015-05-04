package com.sandbox9.devicedetector;

import com.sandbox9.devicedetector.type.*;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;

public class DeviceDetectorMatcher {
        public static Matcher<? super DeviceType> isSameDeviceType(final ExtendedDeviceType deviceType) {
            return new CustomMatcher<DeviceType>(deviceType.name()) {
                @Override
                public boolean matches(Object o) {
                    return deviceType.equals(o);
                }
            };
        }


        public static  Matcher<? super OSType> isSameOsType(final ExtendedOSType osType) {
            return new CustomMatcher<OSType>(osType.name()) {
                @Override
                public boolean matches(Object o) {
                    return osType.equals(o);
                }
            };
        }


        public static  Matcher<? super BrowserType> isSameBrowserType(final ExtendedBrowserType browserType) {
            return new CustomMatcher<BrowserType>(browserType.name()) {
                @Override
                public boolean matches(Object o) {
                    return browserType.equals(o);
                }
            };
        }


    }