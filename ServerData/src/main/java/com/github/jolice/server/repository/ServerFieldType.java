package com.github.jolice.server.repository;

public enum ServerFieldType {

    MAP("map"), ACTIVE("active"), ENABLED("enabled"), ONLINE("onlinePlayers");

    private String fieldName;

    ServerFieldType(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
