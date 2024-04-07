package com.will.service.score_models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScorePlayer {
    private Integer id;
    private String name;
    private Point point;
    private int setScore;
    private int gameScore;

    public ScorePlayer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.point = Point.ZERO;
    }

    public void addPoint() {
        try {
            point = Point.values()[point.ordinal() + 1];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    public void increaseSetScore() {
        setScore++;
    }

    public void increaseGameScore() {
        gameScore++;
    }
}
