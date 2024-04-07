package com.will.service.score_models;

public enum Point {

    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private String value;

    Point(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
