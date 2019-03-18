package com.minipgm.enums;

public enum PaymentPlatformEnum {
    CONSOLE("CONSOLE"),
    APP("APP"),
    NOT_AVAILABLE("NOT_AVAILABLE");

    private String value;

    PaymentPlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
