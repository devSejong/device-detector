package com.sandbox9.devicedetector;

/**
 * 기기식별 중 발생하는 예외
 * @author devSejong
 * @since 1.0
 */
public class DeviceDetectorException extends RuntimeException{

    public DeviceDetectorException(String message) {
        super(message);
    }

    public DeviceDetectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceDetectorException(Throwable cause) {
        super(cause);
    }
}
