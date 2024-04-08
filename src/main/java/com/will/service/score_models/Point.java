package com.will.service.score_models;

import lombok.Getter;

@Getter
public enum Point {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String value;

    Point(String value) {
        this.value = value;
    }

    public Point increment() {
        int ordinal = this.ordinal();

        if (ordinal + 1 < Point.values().length) {
            return Point.values()[ordinal + 1];
        }
        throw new IllegalStateException();
    }
}
