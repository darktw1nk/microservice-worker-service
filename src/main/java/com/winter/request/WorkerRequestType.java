package com.winter.request;

public enum WorkerRequestType {
    HIRE("hire"),
    FIRE("fire");

    private String type;

    WorkerRequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
