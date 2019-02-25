package com.minipgm.user.enums;

public enum UserTypeEnum {
    ROOT("root"),
    ADMIN("admin"),
    RESIDENT("resident"),
    RESFAMILY("resfamily"),
    NOTYPE("notype");

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
