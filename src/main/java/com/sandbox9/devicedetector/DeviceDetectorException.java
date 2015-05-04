package com.sandbox9.devicedetector;

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
