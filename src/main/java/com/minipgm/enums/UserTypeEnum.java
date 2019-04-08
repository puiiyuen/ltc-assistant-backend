package com.minipgm.enums;

public enum UserTypeEnum {
    ROOT("ROOT"),
    ADMIN("ADMIN"),
    STAFF("STAFF"),
    RESIDENT("RESIDENT"),
    RESFAMILY("RESFAMILY"),
    NOTYPE("NOTYPE");

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
