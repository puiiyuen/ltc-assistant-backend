package com.minipgm.enums;

public enum PaymentPlatformEnum {
    CONSOLE("CONSOLE"),
    APP("APP");

    private String value;

    PaymentPlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
