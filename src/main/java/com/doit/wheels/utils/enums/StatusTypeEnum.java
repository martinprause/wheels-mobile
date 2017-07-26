package com.doit.wheels.utils.enums;


public enum StatusTypeEnum {
    IN_CREATION("IN_CREATION"),
    CREATED("CREATED"),
    IN_PROCESS("IN_PROCESS"),
    PROCESSED("PROCESSED"),
    IN_DELIVERY("IN_DELIVERY"),
    DELIVERED("DELIVERED");

    private String value;

    StatusTypeEnum(String str) {
        this.value = str;
    }
}
