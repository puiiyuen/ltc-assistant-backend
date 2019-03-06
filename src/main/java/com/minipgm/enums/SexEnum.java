package com.minipgm.enums;

public enum SexEnum {
    MALE("MALE"), FEMALE("FEMALE");

    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
